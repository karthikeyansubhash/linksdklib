// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Workpath;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.CommonApi;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.api.Result;

import java.lang.reflect.Type;

/**
 * <p>
 * DeviceService provides interfaces to get device information.
 * </p>
 *
 * @since API 1
 */
@CommonApi
public class DeviceService {
    private DeviceService() {
    }

    /**
     * <p>Returns value to be retrieved with the attribute from the device</p>
     *
     * @param context The Context in which the application is running. If it's null, getting information will be failed.
     * @param key     {@link DeviceAttributeBase} descriptor for retrieving information
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving information from the device.
     * @return String value containing the requested attribute from the device.
     * @exception NullPointerException     if context or key are null
     * @exception IllegalArgumentException if key is not of String returned type
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static synchronized String getString(@NonNull final Context context,
                                                @NonNull final DeviceAttributeBase key,
                                                @Nullable Result result) {
        Preconditions.checkNotNull(context, "Context must be provided");
        Preconditions.checkNotNull(key, "Key must be provided");

        if (key.getResultClass() != String.class) {
            throw new IllegalArgumentException("Key must be of String returned type");
        }

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Devicelet.Param.PACKAGE_NAME, packageName);
        extras.putInt(Devicelet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }

        try {
            extras.putString(Devicelet.Param.KEY_ATTRIBUTE_NAME, key.name());

            final Bundle bundle =
                    context.getContentResolver().call(Devicelet.CONTENT_URI, Devicelet.Method.GET_ATTRIBUTE, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                parseResult(bundle, result);

                if (bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getString(Result.KEY_RESULT);
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }

    /**
     * <p>Returns the list of values to be retrieved with the attribute from the device</p>
     *
     * @param context The Context in which the application is running. If it's null, getting information will be failed.
     * @param key     {@link DeviceAttributeBase} descriptor for interested information
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving information from the device.
     * @return String the list of requested information from the device.
     * @exception NullPointerException if context or key are null
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    @Nullable
    public static synchronized String[] getStringArray(@NonNull final Context context,
                                                       @NonNull final DeviceAttributeBase key,
                                                       @Nullable Result result) {

        Preconditions.checkNotNull(context, "Context must be provided");
        Preconditions.checkNotNull(key, "Key must be provided");

        if (key.getResultClass() == String.class || key.getResultClass() == String[].class) {
            //Only valid case
        } else {
            throw new IllegalArgumentException("Key must be of String/String array returned type");
        }

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Devicelet.Param.PACKAGE_NAME, packageName);
        extras.putInt(Devicelet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }

        try {
            extras.putString(Devicelet.Param.KEY_ATTRIBUTE_NAME, key.name());

            final Bundle bundle =
                    context.getContentResolver().call(Devicelet.CONTENT_URI, Devicelet.Method.GET_ATTRIBUTE, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                parseResult(bundle, result);

                if (bundle.containsKey(Result.KEY_RESULT)) {
                    String jsonData = bundle.getString(Result.KEY_RESULT);
                    //below code is to convert string like ["a" , "b" , "c"] to string array
                    return JsonParser.getInstance().fromJson(jsonData, String[].class);
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, DeviceService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @exception NullPointerException Returns error if context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        final Bundle returnBundle = context.getContentResolver()
                .call(Devicelet.CONTENT_URI,
                        Devicelet.Method.IS_SUPPORTED,
                        null,
                        null);

        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(Devicelet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Devicelet.IS_SUPPORTED_EXTRA);
    }

    /**
     * @hide internal used
     */
    private static void parseResult(Bundle bundle, Result result){
        int code = Result.RESULT_OK;
        Result.ErrorCode errorCode = null;
        String cause = null;
        // Error
        if (bundle.containsKey(Result.KEY_CODE)) {
            code = bundle.getInt(Result.KEY_CODE);
        }

        // ErrorCode
        if (bundle.containsKey(Result.KEY_ERROR_CODE)) {
            errorCode = Result.ErrorCode.valueOf(bundle.get(Result.KEY_ERROR_CODE).toString());
        }

        // optional
        if (bundle.containsKey(Result.KEY_CAUSE)) {
            cause = bundle.getString(Result.KEY_CAUSE);
        }
        Result.pack(result, code, errorCode, cause);
    }
}
