// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.common.services.SvcmgtServicesToLibraryService;
import com.hp.workpath.common.Platform;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Class for managing WebServices.
 *
 * Webservice needs to extend AbstractWebServices and register its Callback so that the service could handle GET/PUT/POST/DELETE methods with authenticated method.
 *
 * Note: WebService does not support concurrent processing so you should not run heavy work that takes too long time to be done on a method.
 *
 * @since API 6
 */
@DeviceApi
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class WebServices {
    /**
     * Method to register the webservice
     *
     * @param context The Context in which the application is running.
     * @param attributes Object for WebServicesAttributes builder
     * @exception java.lang.NullPointerException Calling a method on a null reference(context or attributes) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     *
     * @since API 6
     */
    @SuppressLint("RestrictedApi")
    public static synchronized void register(@NonNull final Context context, @NonNull final WebServicesAttributes attributes) {
        Preconditions.checkNotNull(context, "Context must be provided");
        Preconditions.checkNotNull(attributes, "CallbackAttributes must be provided");

        if (attributes.mHttpCallback != null) {
            SvcmgtServicesToLibraryService.callback = new SvcmgtServicesToLibraryService.Callback() {
                @Override
                public String get(String request, String requestBody) {
                    return attributes.mHttpCallback.get(request, requestBody);
                }
            };
        }
    }

    /**
     * This method is needed to determine if the service supported or not. If it's not supported, Webservice operation will be failed.
     *
     * @param context The Context in which the application is running.
     * @return result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @exception  java.lang.NullPointerException Calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     *
     * @since API 6
     */
    @SuppressLint("RestrictedApi")
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        return Platform.isMobile();
    }
} 