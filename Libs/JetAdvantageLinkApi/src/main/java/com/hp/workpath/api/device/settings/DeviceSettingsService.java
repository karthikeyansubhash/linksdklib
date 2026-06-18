// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.settings;

import android.annotation.RequiresPermission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.utils.SLog;

import static com.hp.workpath.api.Workpath.permissions.DISABLE_PRINTING_PORTS_PERMISSION;

/**
 * <p>
 * Provides interfaces for updating device settings. Device Setting service allow to update following device settings
 * <ul>
 * <li>Enable External Printing</li>
 * <li>Disable External Printing</li>
 * </ul>
 * </p>
 *
 * @since API 2
 */
@SuppressWarnings("unused")
public class DeviceSettingsService {
    private DeviceSettingsService() {
    }

    /**
     * <p>Requests to enable printing ports.</p>
     *
     * @Deprecated This method is no longer supported due to security reasons.
     * @since API 2
     */
    @Deprecated
    public static void enableExternalPrinting(@NonNull Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(DeviceSettingslet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(DeviceSettingslet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }
        try {
            final Bundle bundle =
                    context.getContentResolver().call(DeviceSettingslet.CONTENT_URI, DeviceSettingslet.Method.ENABLE_PRINMTING, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(bundle, result);
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }
    }

    /**
     * <p>Requests to Disable printing ports.</p>
     *
     * requiresPermission (value = "com.hp.workpath.permission.admin.DISABLE_PRINTING_PORTS")
     *
     * @param context The Context in which the application is running.
     * @param result  (optional) Indicates any errors which occurred while operation.
     * @throws NullPointerException Returns error if calling a method on a null reference(context) or
     *                              trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 2
     */
    @RequiresPermission(DISABLE_PRINTING_PORTS_PERMISSION)
    public static void disableExternalPrinting(@NonNull Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(DeviceSettingslet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(DeviceSettingslet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }
        try {
            final Bundle bundle =
                    context.getContentResolver().call(DeviceSettingslet.CONTENT_URI, DeviceSettingslet.Method.DISABLE_PRINMTING, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(bundle, result);
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, DeviceSettingsService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @throws NullPointerException Returns error if calling a method on a null reference(context) or
     *                              trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 2
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        Bundle extras = new Bundle();
        extras.putInt(DeviceSettingslet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        try {
            final Bundle returnBundle = context.getContentResolver()
                    .call(DeviceSettingslet.CONTENT_URI,
                            DeviceSettingslet.Method.IS_SUPPORTED,
                            null,
                            extras);
            return returnBundle != null
                    && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                    && returnBundle.containsKey(DeviceSettingslet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(DeviceSettingslet.IS_SUPPORTED_EXTRA);
        } catch (Exception e) {
            SLog.e(DeviceSettingslet.TAG, "Failed to call service", e);
        }

        return false;
    }
}
