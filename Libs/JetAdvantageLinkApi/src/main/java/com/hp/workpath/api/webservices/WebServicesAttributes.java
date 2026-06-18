// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import android.annotation.SuppressLint;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Class for building web service attributes.
 * @since API 6
 */
@DeviceApi
public class WebServicesAttributes {
    final Callback.HttpCallback mHttpCallback;

    /**
     * Creates an instance of WebServicesAttributes with builder
     * @param builder Builder for creating WebServicesAttributes containing HttpCallback parameters.
     *
     * @since API 6
     */
    WebServicesAttributes(final Builder builder) {
        this.mHttpCallback = builder.mHttpCallback;
    }

    /**
     * Builder for creating an instance of {@link WebServicesAttributes}.
     * @since API 6
     */
    public static class Builder {
        Callback.HttpCallback mHttpCallback = null;

        /**
         * Method to set httpCallback
         *
         * @param httpCallback Http Callback interface object
         * @return WebServicesAttributes builder for method chaining
         * @exception java.lang.NullPointerException Calling a method on a null reference(httpCallback) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * Retrieves error if httpCallback is null.
         *
         * @since API 6
         */
        @SuppressLint("RestrictedApi")
        public Builder setSigningCallback(Callback.HttpCallback httpCallback) {
            mHttpCallback = Preconditions.checkNotNull(httpCallback);
            return this;
        }

        /**
         * Combines all of the attributes in this into a WebServicesAttributes object.
         * @return WebServicesAttributes object containing all of the attributes.
         *
         * @since API 6
         */
        public WebServicesAttributes build() {
            return new WebServicesAttributes(this);
        }
    }
} 