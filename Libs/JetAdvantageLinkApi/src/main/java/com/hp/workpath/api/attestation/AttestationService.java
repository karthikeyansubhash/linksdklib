// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.attestation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.JsonParser;

/**
 * <p>AttestationService provides interfaces for communicating with HP attestation server.
 * Key concept of attestation is HP Token Proxy delegates the request to the target service instead of
 * application, so application doesn’t need to keep any secret inside.
 * This service has the dependency with HP systems, so application should request the integration to eco-systems
 * to join HP App Attestation feature.
 * </p>
 *
 * @since API 3
 */
@DeviceApi
public final class AttestationService {

    private AttestationService() {
    }

    /**
     * <p>Returns application token.</p>
     *
     * @param context The Context in which the application is running. If it's null, a client can not get application token.
     * @param result  (optional) Indicates reason when errors occurred.
     * @return {@link AppToken AppToken} object consisting of application token.
     * @throws NullPointerException Returns error if calling a method on a null reference(context/result) or
     *                              trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @Nullable
    public static AppToken getAppToken(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            Bundle extras = new Bundle();
            String packageName = context.getPackageName();
            extras.putString(Attestationlet.Keys.PACKAGE_NAME, packageName);
            extras.putInt(Attestationlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

            final Bundle returnBundle = context.getContentResolver()
                    .call(Attestationlet.CONTENT_URI,
                            Attestationlet.Method.GET_TOKEN,
                            null,
                            extras);

            if (null == returnBundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                // returnBundle.setClassLoader(AppToken.class.getClassLoader()); //TODO 190315 Needs to decide the management of the version policy for AppToken
                Result.parse(returnBundle, result);

                if (result.getCode() == Result.RESULT_OK && returnBundle.containsKey(Result.KEY_RESULT)) {
                    return JsonParser.getInstance().fromJson(returnBundle.getString(Result.KEY_RESULT), AppToken.class);
                } else {
                    Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, "Failed to get application token: " + result.getCause());
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, AttestationService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @throws NullPointerException Returns error if calling a method on a null reference(context) or
     *                              trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        extras.putInt(Attestationlet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(Attestationlet.CONTENT_URI,
                        Attestationlet.Method.IS_SUPPORTED,
                        null,
                        extras);
        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(Attestationlet.ATT_SUPPORT_EXTRA) && returnBundle.getBoolean(Attestationlet.ATT_SUPPORT_EXTRA);
    }

}
