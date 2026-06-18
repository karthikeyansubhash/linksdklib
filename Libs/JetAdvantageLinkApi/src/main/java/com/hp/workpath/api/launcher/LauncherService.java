// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.launcher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.Result;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.utils.SLog;

/**
 * Provides interfaces to launch an app with actions.
 *
 * @since API 2
 */
@SuppressWarnings("unused")
public class LauncherService {
    private LauncherService() {}

    /**
     * Requests to launch an application specified with launcher action
     * @param context The Context in which the application is running.
     * @param action The action name for launching.
     * @param result (optional) Indicates any errors which occurred while operation
     * @exception NullPointerException if context or action is null
     *
     * @since API 2
     */
    @SuppressWarnings("unused")
    @SuppressLint("RestrictedApi")
    public static void launch(@NonNull final Context context, @NonNull final LaunchAction action, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);
        Preconditions.checkNotNull(action, "Endpoint(LaunchAction) for launching must be provided");

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Launcherlet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(Launcherlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        extras.putSerializable(Launcherlet.Keys.KEY_LAUNCH_ACTION, com.hp.jetadvantage.link.api.launcher.LaunchAction.valueOf(action.name()));

        if (result == null) {
            result = new Result();
        }
        try {
            final Bundle bundle =
                    context.getContentResolver().call(Launcherlet.CONTENT_URI, Launcherlet.Method.LAUNCH, null, extras);

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
     * Requests to launch an application specified with uuid
     * @param context The Context in which the application is running.
     * @param uuid main application id or sub application id
     * @param result (optional) Indicates any errors which occurred while operation
     * @exception NullPointerException if context or uuid is null
     *
     * @since API 3
     *
     * <p><b>Note:</b> Starting from firmware version 25.9.2.2, applications can be launched in the background.
     * However, the com.hp.workpath.permission.LAUNCH_PERMISSION must be declared, and this is only
     * possible from the home screen or message center screen of the control panel.</p>
     *
     */
    @SuppressWarnings("unused")
    @SuppressLint("RestrictedApi")
    public static void launch(@NonNull final Context context, @NonNull final String uuid, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);
        Preconditions.checkNotNull(uuid, "Uuid must be provided");

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Launcherlet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(Launcherlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        extras.putString(Launcherlet.Keys.KEY_APPLICATION_UUID, uuid);

        if (result == null) {
            result = new Result();
        }
        try {
            final Bundle bundle =
                    context.getContentResolver().call(Launcherlet.CONTENT_URI, Launcherlet.Method.APPLICATION, null, extras);

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
     * If it's not supported, LauncherService operations will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true.
     * @exception NullPointerException Returns error if context is null.
     * @since API 2
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        Bundle extras = new Bundle();
        extras.putInt(Launcherlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        try {
            final Bundle returnBundle = context.getContentResolver()
                    .call(Launcherlet.CONTENT_URI,
                            Launcherlet.Method.IS_SUPPORTED,
                            null,
                            extras);
            return returnBundle != null
                    && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                    && returnBundle.containsKey(Launcherlet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Launcherlet.IS_SUPPORTED_EXTRA);
        } catch (Exception e) {
            SLog.e(Launcherlet.TAG, "Failed to call service", e);
        }

        return false;
    }

}
