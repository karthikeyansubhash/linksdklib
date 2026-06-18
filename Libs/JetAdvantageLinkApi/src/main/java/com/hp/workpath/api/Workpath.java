// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.printer.intent.PrintRequestIntent;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.CommonApi;
import com.hp.workpath.common.mfpsversion.helper.ApiLevel;

/**
 * <p>This class includes all initialization API that uses HP Workpath SDK service.</p>
 * <p>All clients have to call <b>initialize()</b> method before calling HP Workpath SDK, then need to catch the result to notify the success or error to a user.</p>
 *
 * @since API 1
 */
@CommonApi
public class Workpath {

    private static final String TAG = "Workpath";

    private static final Workpath INSTANCE = new Workpath();

    private static final String SERVICES_PERMISSION = "com.hp.jetadvantage.link.permission.SERVICES_PERMISSION";

    private boolean isInitialized = false;

    private Workpath() {
    }

    /**
     * <p>Permissions of Workpath SDK service</p>
     */
    public static final class permissions {
        /**
         * <p>Permission to access DeviceEventsService</p>
         *
         * @since API 5
         */
        public static final String SDK_ACCESS_DEVICE_EVENTS_PERMISSION = "com.hp.workpath.permission.sdk.ACCESS_DEVICE_EVENTS_PERMISSION";
        /**
         * <p>Permission to access DeviceUsageService</p>
         *
         * @since API 5
         */
        public static final String SDK_ACCESS_DEVICE_USAGE_PERMISSION = "com.hp.workpath.permission.sdk.ACCESS_DEVICE_USAGE_PERMISSION";
        /**
         * <p>Permission to access StatisticsService</p>
         *
         * @since API 5
         */
        public static final String SDK_ACCESS_STATISTICS_PERMISSION = "com.hp.workpath.permission.sdk.ACCESS_STATISTICS_PERMISSION";
        /**
         * <p>Permission to access SuppliesService</p>
         *
         * @since API 5
         */
        public static final String SDK_ACCESS_SUPPLIES_PERMISSION = "com.hp.workpath.permission.sdk.ACCESS_SUPPLIES_PERMISSION";
        /**
         * <p>Permission to access {@link com.hp.workpath.api.device.settings.DeviceSettingsService#disableExternalPrinting(Context, Result)}}</p>
         *
         * @since API 9
         */
        public static final String DISABLE_PRINTING_PORTS_PERMISSION = "com.hp.workpath.permission.admin.DISABLE_PRINTING_PORTS";
        /**
         * <p>Permission to receive sign in/out event</p>
         *
         * @since API 9
         */
        public static final String RECEIVE_SIGN_IN_OUT_EVENT = "com.hp.workpath.permission.RECEIVE_SIGN_IN_OUT_EVENT";
        /**
         * <p>Permission to receive configuration changed event</p>
         *
         * @since API 9
         */
        public static final String RECEIVE_CONFIG_CHANGED_EVENT = "com.hp.workpath.permission.RECEIVE_CONFIG_CHANGED_EVENT";
        /**
         * <p>Permission to receive walk-up job completed event</p>
         *
         * @since API 9
         */
        public static final String RECEIVE_JOB_COMPLETED_EVENT = "com.hp.workpath.permission.RECEIVE_JOB_COMPLETED_EVENT";
        /**
         * <p>Permission to receive sleep/wake up event</p>
         *
         * @since API 9
         */
        public static final String RECEIVE_SLEEP_WAKEUP_EVENT = "com.hp.workpath.permission.RECEIVE_SLEEP_WAKEUP_EVENT";
    }

    /**
     * <p>Actions of Workpath SDK service</p>
     */
    public static final class actions {
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when a sign-in event occurs.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_SIGN_IN_OUT_EVENT</p>
         *
         * @since API 9
         */
        public static final String SIGN_IN = "com.hp.workpath.action.SIGN_IN";
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when a sign-out event occurs.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_SIGN_IN_OUT_EVENT</p>
         *
         * @since API 9
         */
        public static final String SIGN_OUT = "com.hp.workpath.action.SIGN_OUT";
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when a walk-up job is completed.</p>
         * <p>Note: This action is triggered when walk-up jobs are completed normally, as well as in cases such as job cancellation or scan failure.<br>
         *     However, background print jobs and internal jobs (such as Report generation or Configuration page printing) do not trigger this action.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_JOB_COMPLETED_EVENT</p>
         *
         * @since API 9
         */
        public static final String JOB_COMPLETED = "com.hp.workpath.action.JOB_COMPLETED";
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when the app's configuration changes.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_CONFIG_CHANGED_EVENT</p>
         *
         * @since API 9
         */
        public static final String CONFIG_CHANGED = "com.hp.workpath.action.CONFIG_CHANGED";
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when the device wakes up from sleep mode.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_SLEEP_WAKEUP_EVENT</p>
         *
         * @since API 9
         */
        public static final String WAKE_UP = "com.hp.workpath.action.WAKE_UP";
        /**
         * <p>Action sent as a broadcast intent by the Workpath service to an app with a receiver defined in the manifest when the device enters sleep mode.</p>
         * <p>Required permission: com.hp.workpath.permission.RECEIVE_SLEEP_WAKEUP_EVENT</p>
         *
         * @since API 9
         */
        public static final String SLEEP = "com.hp.workpath.action.SLEEP";
    }

    /**
     * <p>Returns an instance of Workpath SDK service when service is ready to use.</p>
     *
     * @return Workpath The object of Workpath SDK service
     */
    @SuppressWarnings("unused")
    public static Workpath getInstance() {
        return INSTANCE;
    }

    /**
     * <p>Provides the version code of Workpath SDK API which is running in an application.</p>
     *
     * @return versionCode The version code of Workpath SDK API
     * @since API 1
     */
    @SuppressWarnings({"unused", "SameReturnValue"})
    public int getVersionCode() {
        return Sdk.VERSION.PLATFORM_LEVEL;
    }

    /**
     * </p>Provides the version name of Workpath SDK API which is running in an application.</p>
     *
     * @return versionName The version name of Workpath SDK API
     * @since API 1
     */
    @SuppressWarnings({"unused", "SameReturnValue"})
    public String getVersionName() {
        return Sdk.VERSION.VERSION_NAME;
    }

    /**
     * <p>Initializes Workpath SDK service. If it fails, method throws an exception that includes the information of the reasons.
     * Reasons are :
     * <ul>
     * <li>SDK service is not installed on a printer</li>
     * <li>SDK service needs to update</li>
     * <li>SDK service needs a permission</li>
     * </ul>
     * </p>
     *
     * @param context The Context in which the application is running. If it's null, initialization will be failed.
     * @exception NullPointerException     If context is null
     * @exception SsdkUnsupportedException If SDK needs to update or SDK is not compatible version or HP Workpath SDK Services is not installed properly
     * @exception SecurityException        If SDK doesn't have the permission to access an application
     * @since API 1
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    @SuppressLint("RestrictedApi")
    public void initialize(@NonNull final Context context) throws SsdkUnsupportedException, SecurityException {
        Preconditions.checkNotNull(context, "If context is null, application can not access to Workpath SDK.");

        // Check if Workpath Services is present in the system
        if (!isInstalled(context.getPackageManager())) {
            throw new SsdkUnsupportedException("Workpath SDK is not installed",
                    SsdkUnsupportedException.LIBRARY_NOT_INSTALLED);
        }

        SecurityException securityException = null;
        VersionState state = VersionState.COMPATIBLE;

        try {
            // Workpath Services application is installed, check the version
            state = getVersionState(context.getContentResolver());
        } catch (SecurityException e) {
            securityException = e;
        } catch (Throwable e) {
            throw new SsdkUnsupportedException("Workpath SDK is not installed",
                    SsdkUnsupportedException.LIBRARY_NOT_INSTALLED);
        }

        if (securityException != null) {
            if (securityException.getMessage().contains(SERVICES_PERMISSION)) {
                state = VersionState.MFP_SERVICES_NEEDS_UPDATE;
            } else {
                throw securityException;
            }
        }

        if (VersionState.MFP_SERVICES_NEEDS_UPDATE == state) {
            throw new SsdkUnsupportedException("Workpath SDK requires to be updated",
                    SsdkUnsupportedException.LIBRARY_UPDATE_IS_REQUIRED);
        }

        isInitialized = true;
    }

    /**
     * @hide internal use
     */
    @SuppressLint("RestrictedApi")
    public void checkPreconditions(Context context) {
        Preconditions.checkNotNull(context, "Context must be provided.");
        Preconditions.checkNotNull(context.getContentResolver(), "ContentResolver is NULL. Invalid access.");
        Preconditions.checkState(isInitialized, "Workpath SDK is not initialized.");
    }

    /**
     * Compares between the client version and the Support Library versions
     */
    @CommonApi
    private enum VersionState {
        /**
         * The Dependent Package version matches the client's version.
         */
        COMPATIBLE,
        /**
         * The Dependent Package version has a version which is older compared
         * to the client's version.
         */
        MFP_SERVICES_NEEDS_UPDATE,
    }

    /**
     * <p>Determines whether the Dependent Package or HP Workpath Services is installed.</p>
     *
     * @param packageMgr PackageManager used in looking for the Dependent Package APK.
     * @return True, if Dependent Package or HP Workpath Services is installed.
     * Otherwise, false.
     */
    @SuppressLint("RestrictedApi")
    private static boolean isInstalled(final PackageManager packageMgr) {
        Preconditions.checkNotNull(packageMgr, "PackageManager is necessary to verify that dependent packages are installed");

        final Intent intent = new Intent();
        intent.setAction(PrintRequestIntent.ACTION);
        return !packageMgr.queryBroadcastReceivers(intent, PackageManager.GET_RESOLVED_FILTER).isEmpty();
    }

    /**
     * Determines how the client version and the Dependent Package version
     * compare to each other.
     *
     * @param cr ContentResolver used in looking for the Dependent Package version.
     * @return The version difference between Dependent Package and this library
     */
    private static VersionState getVersionState(final ContentResolver cr) {
        final int mfpsSdkLevel = ApiLevel.get(cr);
        if (Sdk.VERSION.LEVEL > mfpsSdkLevel) {
            return VersionState.MFP_SERVICES_NEEDS_UPDATE;
        }

        return VersionState.COMPATIBLE;
    }
}
