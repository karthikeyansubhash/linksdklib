// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.deviceusage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.deviceusage.printer.PrinterInfo;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.SLog;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Class for providing interfaces to retrieve usage information from a printer.
 *
 * @since API 5
 */
@DeviceApi
public final class DeviceUsageService {
    private static final String TAG = DeviceUsagelet.TAG;

    private DeviceUsageService() {
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, DeviceUsageService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @exception java.lang.NullPointerException Calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 5
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(DeviceUsagelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(DeviceUsagelet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(DeviceUsagelet.CONTENT_URI,
                        DeviceUsagelet.Method.IS_SUPPORTED,
                        null,
                        extras);

        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(DeviceUsagelet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(DeviceUsagelet.IS_SUPPORTED_EXTRA);
    }

    /**
     * <p>Returns usage information from a printer.</p>
     *
     * @param context The Context in which the application is running. If it's null, configuration will not be retrieved.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving usage information.
     * @return {@link DeviceUsageInfo} object consisting of device's usage information
     *  @exception java.lang.NullPointerException Calling a method on a null reference(context or result) or
     *  trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 5
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    @SuppressLint("RestrictedApi")
    @Nullable
    public static synchronized DeviceUsageInfo getDeviceUsageInfo(@NonNull final Context context,
                                                                  @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(DeviceUsagelet.Param.PACKAGE_NAME, packageName);
        extras.putInt(DeviceUsagelet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }

        try {
            final Bundle bundle =
                    context.getContentResolver().call(DeviceUsagelet.CONTENT_URI, DeviceUsagelet.Method.GET_DEVICEUSAGE, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(bundle, result);

                if (bundle.containsKey(Result.KEY_RESULT)) {
                    final String deviceUsageData = bundle.getString(Result.KEY_RESULT);
                    if (!TextUtils.isEmpty(deviceUsageData)) {
                        try {
                            JSONObject jsonObject = new JSONObject(deviceUsageData).getJSONObject("subunits");
                            SLog.d(TAG, "DeviceUsageData is not empty");

                            return JsonParser.getInstance().fromJson(jsonObject.toString(), DeviceUsageInfo.class);

                        } catch (Throwable throwable) {
                            SLog.d(TAG, "DeviceUsageData is invalid");
                            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SYSTEM_ERROR, "DeviceUsageData is invalid:" + throwable.getMessage());
                        }
                    } else {
                        SLog.d(TAG, "DeviceUsage information is null");
                    }
                }
            }
        } catch (Throwable e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }
}
