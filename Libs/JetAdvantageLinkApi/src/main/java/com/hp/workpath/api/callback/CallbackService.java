// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.callback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Platform;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.jetadvantage.link.common.services.ServicesToLibraryService;

import java.security.Signature;

/**
 * <p>CallbackService provides interfaces to allow interactions between application and Workpath SDK Service.</p>
 *
 * @since API 1
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
@MobileApi
public class CallbackService {
    /**
     * <p>Requests to register callback function to be called when it's operating.</p>
     *
     * @param context The Context in which the application is running. If it's null, callback function will not be registered.
     * @param attributes Includes callback method to be called by Workpath SDK Service
     * @exception NullPointerException if context or attributes are null.
     * @since API 1
     */
    @SuppressLint("RestrictedApi")
    public static synchronized void register(@NonNull final Context context, @NonNull final CallbackAttributes attributes) {
        Preconditions.checkNotNull(context, "Context must be provided");
        Preconditions.checkNotNull(attributes, "CallbackAttributes must be provided");

        if (attributes.mSigningCallback != null) {
            ServicesToLibraryService.scanEncryptCallback = new ServicesToLibraryService.ScanEncryptCallback() {
                @Override
                public byte[] sign(Signature signature, byte[] data) {
                    return attributes.mSigningCallback.sign(signature, data);
                }
            };
        }
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, CallbackService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true.
     * @exception NullPointerException Returns error if context is null.
     *
     * @since API 1
     */
    @SuppressLint("RestrictedApi")
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        return Platform.isMobile();
    }
}
