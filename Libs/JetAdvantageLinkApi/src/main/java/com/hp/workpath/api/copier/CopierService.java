// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.ILetObserver;
import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.copier.intent.CopyToRequestIntent;
import com.hp.workpath.api.copier.intent.ReleaseRequestIntent;
import com.hp.workpath.api.job.JobInfo;
import com.hp.workpath.api.job.JobService;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.SLog;
import com.hp.workpath.common.utils.SecurityUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * CopierService provides an easy to use interface to initiate built-in tasks
 * related to the copier. In its simplest form, a copying task can be
 * initiated by simply calling {@link #submit submit(Context, CopyAttributes,
 * CopyletAttributes)}. For more control of attributes or to present its own UI,
 * {@link CopyletAttributes
 * CopyletAttributes} has to be built prior to calling {@link #submit
 * submit(Context, CopyAttributes, CopyletAttributes)}.
 * </p>
 * <p>
 * <p>
 * A call to {@link #submit submit(Context, CopyAttributes, CopyletAttributes)}
 * starts the copy. The copy is long
 * running and asynchronous by nature, and details of its progress are
 * communicated to the calling entity via a
 * {@link JobService.AbstractJobletObserver
 * AbstractJobletObserver}. The application must create an object of
 * {@link JobService.AbstractJobletObserver
 * AbstractJobletObserver} and register/unregister it based on the component's
 * life cycle, i.e. Activity or Service.
 * </p>
 * <p>
 * Depending upon the task (see <a href="#SupportedTasks">SupportedTask</a>)
 * being submitted, its completion is communicated with a call to
 * {@link ILetObserver#onComplete(String, JobInfo)
 * ILetObserver.onComplete(String, JobInfo)}
 * </p>
 * <p>
 * <h3>Selecting Attributes</h3>
 * <p>
 * For finer control of the attributes passed to {@link #submit
 * submit(Context, CopyAttributes, CopyletAttributes)}, note that
 * the application must determine the existing capability of the
 * device. These capabilities differ per devices.
 * A call to {@link #getCapabilities getCapabilities(Context, Result)}
 * Retrieves these capabilities to the caller.
 * </p>
 * <p>
 * <h3>Submitting a Job</h3>
 * <p>
 * The following is to show how to select attributes and create a
 * copy job:
 * </p>
 * <p>
 * <script src=
 * "https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"
 * ></script>
 * <p>
 * <pre class="prettyprint" style="word-wrap: break-word; white-space: pre-wrap; white-space:-moz-pre-wrap; white-space:-pre-wrap; white-space:-o-pre-wrap; word-break:break-all;">
 * private static final class CopyAsyncTask extends AsyncTask&lt;Void, Void, Void&gt; {
 * <p>
 * protected Void doInBackground(final Void... params) {
 * Result result = new Result();
 * final CopyAttributesCaps caps = CopierService.getCapabilities(context, result);
 * <p>
 * CopyAttributes attributes = null;
 * // Obtain Caps to build Copy Attributes
 * final CopyAttributesCaps caps = activity.getCapabilities();
 * <p>
 * if (caps == null) {
 * mErrorMsg = activity.getString(R.string.capabilities_not_loaded);
 * return null;
 * }
 * <p>
 * attributes = buildCopyAttributes(caps);
 * <p>
 * final CopyletAttributes taskAttribs = new CopyletAttributes.Builder()
 * .setShowSettingsUi(settingsUi)
 * .build();
 * <p>
 * // Submit the job
 * final String rid = CopierService.submit(activity, attributes, taskAttribs);
 * Log.i(TAG, "Job submitted with rid = " + rid);
 * }
 * }
 * </pre>
 * <p>
 * <a name="SupportedTasks"></a> <h3 name="SupportedTasks">Supported Tasks</h3>
 * <ul>
 * <li><a href="#C2N">Normal copy </a></li>
 * <li><a href="#C2S">Copy to store </a></li>
 * </ul>
 * <p>
 * <a name="C2N"></a> <h3>Normal copy</h3>
 * <p>
 * When this task is deployed, the documents are scanned in the local storage and printed from the device.
 * </p>
 * <p>
 * <a name="C2S"></a> <h3>Copy to store</h3>
 * <p>
 * When this task is deployed, the documents are scanned and stored in the internal storage.
 * </p>
 * <p>
 * <h3 name="JobMonitoring">Monitoring the Job</h3> After submitting the job, it
 * can be monitored by using the
 * {@link JobService#monitorJobInForeground
 * monitorJobInForeground(Activity, int, JobletAttributes, Intent)}.
 * <h3 name="JobCancelation">Canceling the Job</h3> To cancel a submitted job,
 * use {@link JobService#cancelJob
 * cancelJob(Context, int)}
 * <p>
 * <p>
 * Copy job supports stored job operation as belows.
 * </p>
 * <ul>
 * <li><a href="#C2R">Release stored copy job </a></li>
 * <li><a href="#C2D">Delete stored copy job </a></li>
 * </ul>
 * <p>
 * <a name="C2R"></a> <h3>Release stored copy job</h3>
 * <p>
 * When this task is deployed, the stored document is printed from the device.
 * </p>
 * <p>
 * <a name="C2D"></a> <h3>Delete stored coyp job</h3>
 * <p>
 * When this task is deployed, the stored document is deleted from the internal storage.
 * </p>
 *
 * @since API 3
 */
public class CopierService {
    private static final String TAG = Copylet.TAG;

    private CopierService() {
    }

    /**
     * <p>Submits a copy job asynchronously to the Printer using the provided copy
     * attributes. If
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} is present, the
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} will receive call backs indicating job
     * progress, completion and errors.</p>
     * </p>
     *
     * @param context        The Context object for your activity or application.
     * @param copyAttributes Attributes related to copy.
     *                       <p>CopyAttributes default values:
     *                       The default value can be figured out by {@link CopierService#getDefaults(Context, Result)}
     *                       <ul>
     *                       <li>ColorMode = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Color/Black menu.
     *                       If MFP is mono model, the default value is {@link CopyAttributes.ColorMode#GRAY}. If MFP is color model, the default value is {@link CopyAttributes.ColorMode#AUTO}.</li>
     *                       <li>Orientation = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Content Orientation menu.</li>
     *                       <li>Duplex = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Sides > Output Sides menu.</li>
     *                       <li>ScanSize = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Original Size menu.</li>
     *                       <li>ScanCustomLength = 0.0f</li>
     *                       <li>ScanCustomWidth = 0.0f</li>
     *                       <li>ScanSource = {@link CopyAttributes.ScanSource#ADF}</li>
     *                       <li>CopyPreview = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Preview menu.</li>
     *                       <li>BackgroundCleanup = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Adjustment > Background Cleanup menu.</li>
     *                       <li>ContrastAdjustment = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Adjustment > Contrast menu.</li>
     *                       <li>DarknessAdjustment = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Adjustment > Darkness menu.</li>
     *                       <li>SharpnessAdjustment = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Adjustment > Sharpness menu.</li>
     *                       <li>PaperSize = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Paper Selection > Paper Size menu.</li>
     *                       <li>PrintCustomLength = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Paper Selection > Paper Size > Custom > Y Dimension menu.</li>
     *                       <li>PrintCustomWidth = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Paper Selection > Paper Size > Custom > X Dimension menu.</li>
     *                       <li>Copies = 1. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Copies menu.</li>
     *                       <li>CollateMode = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Collate menu.</li>
     *                       <li>PaperSource = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Paper Selection > Paper Tray menu.</li>
     *                       <li>PaperType = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Paper Selection > Paper Type menu.</li>
     *                       <li>ScaleMode = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Reduce/Enlarge menu.</li>
     *                       <li>ScalePercent = 100. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Reduce/Enlarge > Manual > Maintain aspect ratio menu.</li>
     *                       <li>TextGraphicsOptimization = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Optimize Text/Picture menu.</li>
     *                       <li>JobAssemblyMode = {@link CopyAttributes.JobAssemblyMode#OFF}</li>
     *                       <li>JobExecutionMode = {@link CopyAttributes.JobExecutionMode#NORMAL}</li>
     *                       <li>NumberUpMode = {@link CopyAttributes.NumberUpMode#ONE_UP}</li>
     *                       <li>NumberUpDirection = {@link CopyAttributes.NumberUpDirection#TO_BOTTOM_TO_RIGHT}</li>
     *                       <li>OutputBin = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Output Bin menu. This menu is shown in EWS when finisher is installed.</li>
     *                       <li>ProgressDialogMode = {@link CopyAttributes.ProgressDialogMode#OFF}</li>
     *                       <li>EraseMarginUnit = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Erase Edges > Use inches menu.</li>
     *                       <li>EraseBackBottom = 0.0f</li>
     *                       <li>EraseBackLeft = 0.0f</li>
     *                       <li>EraseBackRight = 0.0f</li>
     *                       <li>EraseBackTop = 0.0f</li>
     *                       <li>EraseFrontBottom = 0.0f</li>
     *                       <li>EraseFrontLeft = 0.0f</li>
     *                       <li>EraseFrontRight = 0.0f</li>
     *                       <li>EraseFrontTop = 0.0f</li>
     *                       <li>CaptureMode = {@link CopyAttributes.CaptureMode#STANDARD}</li>
     *                       <li>ImageShiftReduceToFit = {@link CopyAttributes.ImageShiftReduceToFit#FALSE}</li>
     *                       <li>ImageShiftUnits = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Shift > Use inches menu. If Use inches is checked, unit is inch. Otherwise, unit is millimeter.</li>
     *                       <li>ImageShiftXFront = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Shift > Front Side > Horizontal menu.</li>
     *                       <li>ImageShiftYFront = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Shift > Front Side > Vertical menu.</li>
     *                       <li>ImageShiftXBack = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Shift > Front Side > Horizontal menu. For back-side options, set Output Sides to 2-sided.</li>
     *                       <li>ImageShiftYBack = 0.0f. This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Image Shift > Front Side > Vertical menu. For back-side options, set Output Sides to 2-sided.</li>
     *                       <li>BookletBordersEachPage = {@link CopyAttributes.BookletBordersEachPage#FALSE}</li>
     *                       <li>BookletFinishingOption = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Booklet Format and EWS > Copy/Print > Copy Settings > Default Job Options > Fold and Stitch. This menu is shown in EWS when finisher is installed.</li>
     *                       <li>BookletFormat = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Booklet Format.</li>
     *                       <li>StapleOption = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Staple menu. This menu is shown in EWS when finisher to support staple feature is installed.</li>
     *                       <li>PunchMode = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Staple/Hole Punch menu. This menu is shown in EWS when finisher to support Hole Punch feature is installed.</li>
     *                       <li>FoldMode = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Fold menu. This menu is shown in EWS when finisher to support Fold feature is installed.</li>
     *                       <li>StampPosition = Empty</li>
     *                       <li>WatermarkDarkness = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Darkness menu or EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Darkness menu.</li>
     *                       <li>WatermarkText = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Watermark Text menu or EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Secure Watermark Text menu.</li>
     *                       <li>WatermarkRotate45 = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Rotate text 45 degrees menu.
     *                       This option is only supported when the Watermark Type is Secure. In all other cases, the setting will be ignored and the watermark rotation will be 45 degrees.</li>
     *                       <li>WatermarkType = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type menu.</li>
     *                       <li>WatermarkTextSize = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Text Size menu and EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Text Size menu.</li>
     *                       <li>WatermarkTransparency = 0</li>
     *                       <li>WatermarkFont = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Text Font menu or EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure >  Text Font menu.</li>
     *                       <li>WatermarkBackgroundColor = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Background Color menu.</li>
     *                       <li>WatermarkTextColor = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Text Color menu.</li>
     *                       <li>WatermarkOnlyFirstPage = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > First page only menu or EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > First page only menu.</li>
     *                       <li>WatermarkMessageType = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Text > Watermark Text menu or EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Secure Watermark Text menu. Custom Watermark values can be added in EWS > Copy/Print >  Copy Settings > Watermark > Custom Watermark Text. </li>
     *                       <li>WatermarkBackgroundPattern = This default value can be changed in EWS > Copy/Print > Copy Settings > Default Job Options > Watermark > Watermark Type as Secure > Background Pattern menu.</li>
     *                       </ul>
     *                       </p>
     * @param letAttributes  Attributes related to how the {@link CopierService} should
     *                       behave.
     * @return a unique request ID to track this request.
     * @exception NullPointerException     if context or CopyAttributes are null with disabled Show Setting UI.
     * @exception IllegalArgumentException if incompatible attributes are provided
     *                                  (e.g. {@link CopyAttributes} has Settings UI false and {@link CopyAttributes} are null).
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static String submit(@NonNull final Context context, final CopyAttributes copyAttributes, final CopyletAttributes letAttributes) {
        Workpath.getInstance().checkPreconditions(context);

        if (letAttributes == null || !letAttributes.mShowSettingsUi) {
            Preconditions.checkNotNull(copyAttributes, "CopyAttributes must be provided");
        }

        final String rid = UUID.randomUUID().toString();

        String appId = SecurityUtility.loadApplicationIdFromAssets(context);

        final CopyToRequestIntent intent = new CopyToRequestIntent();
        final CopyToRequestIntent.IntentParams params =
                new CopyToRequestIntent.IntentParams(copyAttributes, letAttributes, rid, context.getPackageName(), appId,
                        null, null, Sdk.VERSION.LEVEL);
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        context.sendOrderedBroadcast(intent, null);
        return rid;
    }

    /**
     * <p>
     * This synchronous operation returns all copy capabilities supported by the device.
     * </p>
     *
     * @param context The Context object for your activity or application.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving capabilities.
     * @return Capabilities found both on the Printer and in the CopierService or null.
     * @exception NullPointerException Calling a method on a null reference(context or result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public static synchronized CopyAttributesCaps getCapabilities(@NonNull final Context context,
                                                                  @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            final ContentResolver resolver = context.getContentResolver();
            Bundle extras = new Bundle();
            extras.putInt(Copylet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
            final Bundle bundle = resolver.call(Copylet.getContentUri(resolver),
                    Copylet.Method.GET_CAPS, null, extras);

            Result.parse(bundle, result);

            if (result.getCode() == Result.RESULT_OK) {
                final String jsonStr = bundle.getString(Result.KEY_RESULT);
                return JsonParser.getInstance().fromJson(jsonStr, CopyAttributesCaps.class);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
            SLog.e(TAG, "Failed to deserialize: " + e.getMessage());
        }

        return null;
    }

    /**
     * Retrieves the device default values for copy attributes. If the value is DEFAULT, it means it is not supported attribute from the device.
     *
     * @param context The Context object for your activity or application.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving defaults.
     * @return CopyAttributes containing default values for submitting a job or null.
     * @exception NullPointerException Calling a method on a null reference(context or result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public static synchronized CopyAttributes getDefaults(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            final ContentResolver resolver = context.getContentResolver();
            Bundle extras = new Bundle();
            extras.putInt(Copylet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
            final Bundle bundle = resolver.call(Copylet.getContentUri(resolver),
                    Copylet.Method.GET_DEFAULTS, null, extras);

            Result.parse(bundle, result);

            if (result.getCode() == Result.RESULT_OK) {
                final String jsonStr = bundle.getString(Result.KEY_RESULT);
                return JsonParser.getInstance().fromJson(jsonStr, CopyAttributes.class);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
            SLog.e(TAG, "Failed to deserialize: " + e.getMessage());
        }
        return null;
    }

    /**
     * <p>
     * Submits a release copy job asynchronously to the printer using the provided stored job
     * attributes. If {@link JobService.AbstractJobletObserver AbstractJobletObserver} is present,
     * the {@link JobService.AbstractJobletObserver AbstractJobletObserver} will receive
     * call backs indicating job
     * progress, completion and errors.</p>
     * </p>
     *
     * @param context             The Context object for your activity or application.
     * @param storedJobAttributes Attributes related to stored job.
     * @return Request id which is used to correspond {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} callbacks with the original submit.
     * @exception NullPointerException Calling a method on a null reference(context or storedJobAttributes) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * Retrieves error if rid value is null.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static String releaseStoredJob(@NonNull final Context context, @NonNull final StoredJobAttributes storedJobAttributes) {
        Workpath.getInstance().checkPreconditions(context);
        Preconditions.checkNotNull(storedJobAttributes, "StoredJobAttributes must be provided");

        final String rid = UUID.randomUUID().toString();

        String appId = SecurityUtility.loadApplicationIdFromAssets(context);

        final ReleaseRequestIntent intent = new ReleaseRequestIntent();
        final ReleaseRequestIntent.IntentParams params =
                new ReleaseRequestIntent.IntentParams(storedJobAttributes, rid, context.getPackageName(), appId,
                        null, null, Sdk.VERSION.LEVEL);
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        context.sendOrderedBroadcast(intent, null);
        return rid;
    }

    /**
     * <p>
     * Deletes a stored job with the specified job ID.
     * </p>
     *
     * @param context              The Context object for your activity or application.
     * @param jobId                Stored job id to delete
     * <p>
     * <ul>
     * <li>There is no minimum & maximum length</li>
     * <li>Instance variable assigned as empty string for empty input</li>
     * <li>Instance variable assigned as NULL for null input</li>
     * <li>Expected input - String value</li>
     * </ul>
     * </p>
     * @param storedJobCredentials Password used while to storing job
     * @param result               (optional) Indicates any errors which occurred while
     *                             deleting stored job.
     * @exception NullPointerException Calling a method on a null reference(context or jobId or storedJobCredentials or result)
     * or trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static void deleteStoredJob(@NonNull final Context context, @NonNull final String jobId,
                                       @Nullable JobCredentialsAttributes storedJobCredentials, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);
        Preconditions.checkNotNull(jobId, "Stored job ID must be provided");

        if (result == null) {
            result = new Result();
        }

        try {
            final String rid = UUID.randomUUID().toString();

            String appId = SecurityUtility.loadApplicationIdFromAssets(context);

            final com.hp.jetadvantage.link.api.copier.intent.DeleteRequestIntent intent = new com.hp.jetadvantage.link.api.copier.intent.DeleteRequestIntent();
            final com.hp.jetadvantage.link.api.copier.intent.DeleteRequestIntent.IntentParams params =
                    new com.hp.jetadvantage.link.api.copier.intent.DeleteRequestIntent.IntentParams(storedJobCredentials, rid, context.getPackageName(), appId,
                            null, null, Sdk.VERSION.LEVEL);
            intent.putIntentParams(params);
            intent.setPackage(Sdk.SERVICES_PACKAGE);

            Bundle extras = new Bundle();
            extras.putInt(Copylet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
            extras.putString(Copylet.Keys.KEY_STORED_JOB_ID, jobId);
            extras.putParcelable(Copylet.Keys.KEY_DELETE_REQ, intent);

            final ContentResolver resolver = context.getContentResolver();
            final Bundle bundle = resolver
                    .call(Copylet.getContentUri(resolver),
                            Copylet.Method.DELETE_JOB,
                            null,
                            extras);

            Result.parse(bundle, result);
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
        }
    }

    /**
     * <p>
     * List outs the stored jobs to release or delete it.
     * </p>
     *
     * @param context The Context object for your activity or application.
     * @param result  (optional) Indicates any errors which occurred while
     *                enumerating stored job.
     * @return List of storedJobAttributes to release or delete or null.
     * @exception NullPointerException Calling a method on a null reference(context or result) or trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public static List<StoredJobInfo> enumerateStoredJob(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            final ContentResolver resolver = context.getContentResolver();
            Bundle extras = new Bundle();
            extras.putInt(Copylet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
            final Bundle bundle = resolver.call(Copylet.getContentUri(resolver),
                    Copylet.Method.ENUMERATE_JOBS, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                bundle.setClassLoader(StoredJobInfo.class.getClassLoader());
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    ArrayList<com.hp.jetadvantage.link.api.copier.StoredJobInfo> tmpStoredJobInfos = bundle.getParcelableArrayList(Result.KEY_RESULT);
                    List<StoredJobInfo> storedJobInfos = new ArrayList<StoredJobInfo>();
                    for (com.hp.jetadvantage.link.api.copier.StoredJobInfo storedJobInfo : tmpStoredJobInfos) {
                        storedJobInfos.add(StoredJobInfo.CREATOR_OBJ.createFromObject(storedJobInfo));
                    }

                    return storedJobInfos;
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, CopierService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @exception NullPointerException Calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public static boolean isSupported(final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        extras.putInt(Copylet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        try {
            final ContentResolver resolver = context.getContentResolver();
            final Bundle returnBundle = resolver
                    .call(Copylet.getContentUri(resolver),
                            Copylet.Method.IS_SUPPORTED,
                            null,
                            extras);

            return returnBundle != null
                    && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                    && returnBundle.containsKey(Copylet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Copylet.IS_SUPPORTED_EXTRA);
        } catch (SecurityException se) {
            throw se;
        } catch (Exception e) {
            SLog.e(TAG, "Failed to get supported status: " + e.getMessage());
        }

        return false;
    }
}