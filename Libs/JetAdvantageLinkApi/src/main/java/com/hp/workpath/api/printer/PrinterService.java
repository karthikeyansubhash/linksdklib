// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.printer;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;
import android.util.Log;

import com.hp.jetadvantage.link.common.model.ApiType;
import com.hp.workpath.api.FileProvider;
import com.hp.workpath.api.ILetObserver;
import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.job.JobInfo;
import com.hp.workpath.api.job.JobService;
import com.hp.workpath.api.printer.intent.PrintRequestIntent;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.NetworkUtility;
import com.hp.workpath.common.utils.SLog;
import com.hp.workpath.common.utils.SecurityUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * <p>
 * PrinterService provides an easy to use interface to initiate printing related
 * built-in tasks. In its simplest form, the printing task can be initiated by
 * simply calling {@link #submit submit(Context, PrintAttributes,
 * PrintletAttributes)}. For more control of attributes or to present its own UI,
 * the {@link PrintletAttributes
 * PrintletAttributes} must be built prior to calling {@link #submit
 * submit(Context, PrintAttributes, PrintletAttributes)}.
 * </p>
 * <p>
 * <p>
 * A call to {@link #submit submit(Context, PrintAttributes,
 * PrintletAttributes)} starts the printing task. The printing process is long
 * running and asynchronous by nature, and details of its progress are
 * communicated to the calling entity via a
 * {@link JobService.AbstractJobletObserver
 * AbstractJobletObserver}. The application must create an object of
 * {@link JobService.AbstractJobletObserver
 * AbstractJobletObserver} and register/unregister it based on the component's
 * life cycle, i.e. Activity or Service.
 * </p>
 * <p>
 * <p>
 * Depending upon the task (see <a href="#SupportedTasks">SupportedTask</a>)
 * being submitted, its completion is communicated via a call to
 * {@link ILetObserver#onComplete(String, JobInfo)
 * ILetObserver.onComplete(String, JobInfo)}
 * </p>
 * <p>
 * <h3>Selecting Attributes</h3>
 * <p>
 * For finer control of the attributes passed to {@link #submit
 * submit(Context, PrintAttributes, PrintletAttributes)}, note that
 * the application must determine the existing capability of the
 * device. These capabilities differ per devices.
 * A call to {@link #getCapabilities getCapabilities(Context, Result)}
 * Retrieves these capabilities to the caller.
 * </p>
 * <p>
 * <h3>Submitting a Job</h3>
 * <p>
 * The following is an excerpt showing how to select attributes and submit a
 * print job:
 * </p>
 * <p>
 * <script src=
 * "https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"
 * ></script>
 * <p>
 * <pre class="prettyprint" style="word-wrap: break-word; white-space: pre-wrap; white-space:-moz-pre-wrap; white-space:-pre-wrap; white-space:-o-pre-wrap; word-break:break-all;">
 * private static final class PrintAsyncTask extends AsyncTask&lt;Void, Void, Void&gt; {
 * <p>
 * protected Void doInBackground(final Void... params) {
 * Result result = new Result();
 * final PrintAttributesCaps caps = PrinterService.getCapabilities(
 * mContext, result);
 * <p>
 * if (null == caps) {
 * // Unable to retrieve capabilities. Need to look at the result to
 * // see what happened.
 * return null;
 * }
 * <p>
 * try {
 * final Duplex duplex =
 * Duplex.valueOf(mPrefs.getString(PrintConfigureFragment.PREF_DUPLEX_MODE, Duplex.DEFAULT.name()));
 * <p>
 * .....
 * <p>
 * // Create the print attributes
 * final PrintAttributes attributes =
 * new PrintAttributes.PrintFromStorageBuilder(Uri.fromFile(new File(filePath)))
 * .setColorMode(cm)
 * .setDuplex(duplex)
 * .setAutoFit(af)
 * .setStapleMode(sm)
 * .setPaperSource(psrc)
 * .setPaperSize(psz)
 * .setDocumentFormat(dfmt)
 * .setCopies(copies)
 * .build(caps);
 * <p>
 * // Create the printlet attributes
 * final PrintletAttributes taskAttribs = new PrintletAttributes.Builder().build();
 * <p>
 * // Submit the job
 * PrinterService.submit(mContext, attributes, taskAttribs);
 * <p>
 * } catch (final CapabilitiesExceededException cee) {
 * // Either an attribute was outside of the capabilities or the
 * // capabilities were empty.
 * }
 * }
 * }
 * </pre>
 * <p>
 * <a name="SupportedTasks"></a> <h3 name="SupportedTasks">Supported Tasks</h3>
 * <ul>
 * <li><a href="#PFS">Print from local Storage</a></li>
 * <li><a href="#PFH">Print from HTTP</a></li>
 * <li><a href="#PFM">Print from Stream</a></li>
 * <li><a href="#PFU">Print from Usb</a></li>
 * </ul>
 * <p>
 * <a name="PFS"></a> <h3>Print from local Storage</h3>
 * <p>
 * When executed on the device, this task prints a local
 * file using the given attributes. A local file is one which resides on the
 * local file system of the printer.
 * </p>
 * <p>
 * <a name="PFH"></a> <h3>Print from HTTP</h3>
 * <p>
 * When executed on the device, this task prints a remote
 * file using the given http url.
 * </p>
 * <p>
 * <a name="PFM"></a> <h3>Print from Stream</h3>
 * <p>
 * When executed on the device, this task prints from the given streams.
 * </p>
 * <p>
 * <a name="PFU"></a> <h3>Print from Usb</h3>
 * <p>
 * When executed on the device, this task prints a file from the given Usb url.
 * </p>
 * <p>
 * <h3 name="JobMonitoring">Monitoring the Job</h3> After submitting the job, it
 * can be monitored by using the
 * {@link JobService#monitorJobInForeground
 * monitorJobInForeground(Activity, int, JobletAttributes, Intent)} API and providing
 * the job Id.
 * <p>
 * <h3 name="JobCancelation">Canceling the Job</h3> To cancel a submitted job,
 * you can use
 * {@link JobService#cancelJob
 * cancelJob(Context, int)}
 *
 * @since API 1
 */
public final class PrinterService {
    private static final String TAG = Printlet.TAG;

    private PrinterService() {
    }

    private static void checkJobCount(@NonNull final Context context, int numOfNewJobs) {
        Bundle extras = new Bundle();
        extras.putInt(Printlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final ContentResolver resolver = context.getContentResolver();
        final Bundle bundle = resolver.call(Printlet.getContentUri(ApiType.OXP),
                Printlet.Method.GET_AVAILABLE_JOB_COUNT, null, extras);

        int availableJobCount = bundle.containsKey(Printlet.GET_AVAILABLE_JOB_COUNT_EXTRA) ? bundle.getInt(Printlet.GET_AVAILABLE_JOB_COUNT_EXTRA) : 0;

        if (availableJobCount < numOfNewJobs) {
            Log.e(TAG, "Too many jobs are registered. Please try later. availableJobCount, numOfNewJobs = " + availableJobCount + ", " + numOfNewJobs);
            throw new IllegalArgumentException("Too many jobs are registered. Please try later. The available : " + availableJobCount + ", requested : " + numOfNewJobs);
        }
    }

    @SuppressLint("RestrictedApi")
    private static String sendPrintAttributes(@NonNull final Context context, @Nullable final PrintAttributes printAttributes, @Nullable final PrintletAttributes letAttributes, String rid) {
        Workpath.getInstance().checkPreconditions(context);
        //Validation logic when job is requested without settingUI.
        Uri fileUri = null;
        if (letAttributes == null || !letAttributes.mShowSettingsUi) {
            Preconditions.checkNotNull(printAttributes, "PrintAttributes must be provided");

            if (printAttributes.mSource != PrintAttributes.Source.STREAM) {
                Preconditions.checkArgument(printAttributes.mFileUri != null && !Uri.EMPTY.equals(printAttributes.mFileUri),
                        "File Uri should be provided if Settings UI is disabled.");
                Preconditions.checkArgument(printAttributes.mSource == PrintAttributes.Source.HTTP || !"/".equals(printAttributes.mFileUri.getEncodedPath()),
                        "File Uri should be provided if Settings UI is disabled.");
                // Validate provided uri, but don't store received value
                PrintAttributes.validateUri(printAttributes.mSource, printAttributes.mFileUri, printAttributes.mDocumentFormat);

                if (printAttributes.mSource == PrintAttributes.Source.STORAGE) {
                    final File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    if (printAttributes.mFileUri.getPath().startsWith(downloadFolder.getAbsolutePath())) { //For download directory
                    } else {
                        try {
                            File newFile = new File(printAttributes.mFileUri.toString());
                            fileUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".provider", newFile);

                            context.getApplicationContext().grantUriPermission(Sdk.SERVICES_PACKAGE, fileUri,
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            Log.d(TAG, "Permission is granted to " + fileUri);
                        } catch (Exception e) {
                            Log.e(TAG, "An error while getting a fileUri." + e.getMessage());
                        }
                    }
                }
            } else {
                Preconditions.checkNotNull(printAttributes.mPrintInputStream, "Input stream must be provided");

                if (printAttributes.mDocumentFormat == PrintAttributes.DocumentFormat.JPEG) {
                    throw new IllegalArgumentException("DocumentFormat doesn't correspond the file type");
                }

                try {
                    int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
                    if (targetSdkVersion > Build.VERSION_CODES.O) {
                        Log.d(TAG, "PrintFromStream is over o");
                        NetworkUtility.createSocket(rid, printAttributes.mPrintInputStream);
                    } else {
                        Log.d(TAG, "PrintFromStream is under o");
                        NetworkUtility.createServerSocket(rid, printAttributes.mPrintInputStream);
                    }
                } catch (Throwable throwable) {
                    Log.e(TAG, "PrintFromStream error " + throwable.getMessage());
                    NetworkUtility.createServerSocket(rid, printAttributes.mPrintInputStream);
                }
            }
        }

        SLog.v(TAG, "submit for " + rid);
        String appId = SecurityUtility.loadApplicationIdFromAssets(context);

        final PrintRequestIntent intent = new PrintRequestIntent();
        PrintRequestIntent.IntentParams params = null;
        if (printAttributes != null && printAttributes.mSource == PrintAttributes.Source.STORAGE && fileUri != null) {
            params = new PrintRequestIntent.IntentParams(
                    printAttributes, letAttributes, rid, context.getPackageName(), null, appId, null, null, Sdk.VERSION.LEVEL, fileUri.toString());
        } else {
            params = new PrintRequestIntent.IntentParams(
                    printAttributes, letAttributes, rid, context.getPackageName(), null, appId, null, null, Sdk.VERSION.LEVEL);
        }
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        context.sendOrderedBroadcast(intent, null);
        return rid;
    }

    /**
     * <p>Submits a print job asynchronously to the Printer using the provided
     * print attributes. If
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} is present, the
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} will receive call backs indicating job
     * progress, completion and errors.</p>
     * <p>
     * <p>This task can accept multiple requests.</p>
     *
     * @param context         The Context object for your activity or application.
     * @param printAttributes Attributes related to print.
     *                        <p>PrintAttributes default values:
     *                        The default value can be figured out by {@link PrinterService#getDefaults(Context, Result)}
     *                        <ul>
     *                        <li>ColorMode = If printer is mono model, the default value is {@link PrintAttributes.ColorMode#MONO}. If printer is color model, the default value is {@link PrintAttributes.ColorMode#COLOR}.</li>
     *                        <li>Duplex = This default value can be changed in EWS > Copy/Print > Default Print Options > Output Sides menu.</li>
     *                        <li>AutoFit = {@link PrintAttributes.AutoFit#TRUE}</li>
     *                        <li>Copies = 1. This default value can be changed in EWS > Copy/Print > Default Print Options > Number of Copies menu.</li>
     *                        <li>FileUri = Empty</li>
     *                        <li>StapleMode = This default value can be changed in EWS > Copy/Print > Default Print Options > Staple menu. This menu is shown in EWS when finisher to support Staple feature is installed.</li>
     *                        <li>PaperSource = {@link PrintAttributes.PaperSource#AUTO}</li>
     *                        <li>PaperSize = This default value can be changed in EWS > Copy/Print > Default Print Options > Default Paper Size menu.</li>
     *                        <li>PaperType = This default value can be changed in EWS > Copy/Print > Default Print Options > Paper Type menu.</li>
     *                        <li>DocumentFormat = {@link PrintAttributes.DocumentFormat#PDF}</li>
     *                        <li>CollateMode = {@link PrintAttributes.CollateMode#UNCOLLATED}</li>
     *                        <li>JobName = null</li>
     *                        <li>Orientation = {@link PrintAttributes.Orientation#PORTRAIT}</li>
     *                        <li>PrintQuality = This default value can be changed in EWS > Copy/Print > Default Print Options > Quality and Speed menu.</li>
     *                        <li>OutputBin = This default value can be changed in EWS > Copy/Print > Default Print Options > Output Bin menu. This menu is shown in EWS when finisher is installed. This default value is different according to printing engine and finisher installation.</li>
     *                        <li>StartPageRanges = 0</li>
     *                        <li>EndPageRanges = 0</li>
     *                        <li>Finishings = This default value can be changed in EWS > Copy/Print > Default Print Options > Hole Punch, Staple, Fold menu. Hole Punch, Staple and Fold menus are shown in EWS when finisher to support Hole Punch, Staple and Fold feature is installed.</li>
     *                        </ul>
     *                        </p>
     * @param letAttributes   Attributes related to how the {@link PrinterService} should
     *                        behave.
     * @return <p>a unique request ID to track this request
     * <ul>
     * <li>Return value shouldn't be empty or null.</li>
     * </ul>
     * </p>
     * @exception NullPointerException     if context or PrintAttributes are null with disabled Show Setting UI
     * @exception IllegalArgumentException if incompatible attributes are provided
     *                                  (e.g. {@link PrintAttributes} has Settings UI false and {@link PrintAttributes} are null)
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static String submit(@NonNull final Context context, @Nullable final PrintAttributes printAttributes, @Nullable final PrintletAttributes letAttributes) {
        final String rid = UUID.randomUUID().toString();

        checkJobCount(context, 1);

        if (letAttributes == null || !letAttributes.mShowSettingsUi) {
            Preconditions.checkNotNull(printAttributes, "PrintAttributes must be provided");
            if (printAttributes.mSource == PrintAttributes.Source.STREAM && letAttributes.getBackgroundJob()) {
                throw new IllegalArgumentException("STREAM source could not be a background job.");
            }
        }
        return sendPrintAttributes(context, printAttributes, letAttributes, rid);
    }

    /**
     * <p>Submits a print job asynchronously to the Printer using the provided
     * print attributes. If
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} is present, the
     * {@link JobService.AbstractJobletObserver
     * AbstractJobletObserver} will receive call backs indicating job
     * progress, completion and errors.</p>
     * <p>
     * <p>This task can accept multiple requests.</p>
     *
     * <p>Note: When more than one batch is submitted (e.g., batch1, batch2, batch3), the batches will be queued and processed in FIFO (First-In-First-Out) order.
     * This means that batch1 will be processed first, followed by batch2, and then batch3.
     * The next batch will start processing only after the previous batch has completed.</p>
     *
     * @param context         The Context object for your activity or application.
     * @param printAttributes Attributes related to print.
     * @param letAttributes   Attributes related to how the {@link PrinterService} should
     *                        behave.
     * @return <p>a unique request ID to track this request
     * <ul>
     * <li>Return value shouldn't be empty or null.</li>
     * </ul>
     * </p>
     * @exception NullPointerException     if context or PrintAttributes are null with disabled Show Setting UI
     * @exception IllegalArgumentException if incompatible attributes are provided
     *                                  (e.g. {@link PrintAttributes} has Settings UI false and {@link PrintAttributes} are null)
     * @since API 7
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static String submit(@NonNull final Context context, @Nullable final ArrayList<PrintAttributes> printAttributes, @Nullable final PrintletAttributes letAttributes) {
        final String rid = UUID.randomUUID().toString();

        checkJobCount(context, printAttributes.size());

        for (PrintAttributes printAttribute : printAttributes) {
            if (printAttribute.mSource == PrintAttributes.Source.STREAM) {
                throw new IllegalArgumentException("STREAM source is not supported for this function.");
            }
        }

        for (PrintAttributes printAttribute : printAttributes) {
            sendPrintAttributes(context, printAttribute, letAttributes, rid);
        }

        return rid;
    }

    /**
     * <p>This synchronous operation returns the intersection of Printer and
     * PrinterService capabilities. In this example, for the color mode attribute:</p><ul>
     * <li>The color modes supported from the Printer are {mono, auto}</li>
     * <li>The color modes of the {@link PrinterService} are {mono, color}</li><ul>
     * <p>Therefore, the color mode capabilities will include only {mono}.</p>
     * <p/>
     * <i>Note that for some attributes, the intersection of values may result in empty capabilities.</i></p>
     *
     * @param context The Context object for your activity or application.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving capabilities.
     * @return Capabilities found both on the Printer and in the PrinterService
     * or null.
     * @exception NullPointerException if context is null
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static synchronized PrintAttributesCaps getCapabilities(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            Bundle extras = new Bundle();
            extras.putInt(Printlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

            final ContentResolver resolver = context.getContentResolver();
            final Bundle bundle = resolver.call(Printlet.getContentUri(resolver),
                    Printlet.Method.GET_CAPS, null, extras);

            Result.parse(bundle, result);

            if (result.getCode() == Result.RESULT_OK) {
                final String jsonStr = bundle.getString(Result.KEY_RESULT);
                return JsonParser.getInstance().fromJson(jsonStr, PrintAttributesCaps.class);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the device default values for print attributes. If the value is DEFAULT, it means it is not supported attribute from the device.
     *
     * @param context The Context object for your activity or application.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving defaults.
     * @return PrinterAttributes containing default values for submitting a job
     * @exception NullPointerException if context is null
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static synchronized PrintAttributes getDefaults(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            Bundle extras = new Bundle();
            extras.putInt(Printlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

            final ContentResolver resolver = context.getContentResolver();
            final Bundle bundle = resolver.call(Printlet.getContentUri(resolver),
                    Printlet.Method.GET_DEFAULTS, null, extras);

            Result.parse(bundle, result);

            if (result.getCode() == Result.RESULT_OK) {
                final String jsonStr = bundle.getString(Result.KEY_RESULT);
                return JsonParser.getInstance().fromJson(jsonStr, PrintAttributes.class);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
        }
        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, PrinterService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return <p>boolean Returns result of supported.
     * <ul>
     * <li>If service is supported, method returns true.</li>
     * <li>If service is not supported, method returns false.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if context is null.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        extras.putInt(Printlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        try {
            final ContentResolver resolver = context.getContentResolver();
            final Bundle returnBundle = resolver
                    .call(Printlet.getContentUri(resolver),
                            Printlet.Method.IS_SUPPORTED,
                            null,
                            extras);

            return returnBundle != null
                    && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                    && returnBundle.containsKey(Printlet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Printlet.IS_SUPPORTED_EXTRA);
        } catch (SecurityException se) {
            throw se;
        } catch (Exception e) {
            SLog.e(TAG, "Failed to get supported status :" + e.getMessage());
        }

        return false;
    }
}
