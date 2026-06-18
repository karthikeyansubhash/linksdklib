// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.callback;

import android.annotation.SuppressLint;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>The sets of attributes for using callback service. An application can pass the result to HP Workpath SDK Service through callback service mechanism.
 * An instance of this class is created using {@link Builder}</p>
 *
 * @since API 1
 */
@MobileApi
public class CallbackAttributes {
    final Callback.SigningCallback mSigningCallback;

    CallbackAttributes(final Builder builder) {
        this.mSigningCallback = builder.mSigningCallback;
    }

    /**
     * <p>Builder for creating an instance of {@link CallbackAttributes}.</p>
     *
     * @since API 1
     */
    @MobileApi
    public static class Builder {
        Callback.SigningCallback mSigningCallback = null;

        /**
         * <p>Sets callback which is called by Workpath SDK Service for exchanging signed data based on application's certification.</p>
         *
         * @param signingCallback The name of callback function
         * @return this builder for method chaining
         * @exception NullPointerException if signingCallback is null.
         * @since API 1
         */
        @SuppressWarnings({"unused"})
        @SuppressLint("RestrictedApi")
        public Builder setSigningCallback(Callback.SigningCallback signingCallback) {
            mSigningCallback = Preconditions.checkNotNull(signingCallback);
            return this;
        }

        /**
         * <p>The builder for building CallbackAttributes</p>
         *
         * @return CallbackAttributes new CallbackAttributes
         * @since API 1
         */
        public CallbackAttributes build() {
            return new CallbackAttributes(this);
        }
    }
}
