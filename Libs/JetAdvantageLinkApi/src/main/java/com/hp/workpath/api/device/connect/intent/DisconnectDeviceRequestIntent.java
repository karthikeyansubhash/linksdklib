// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect.intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.annotation.MobileApi;
import com.hp.jetadvantage.link.common.intents.ISpsIntentWrapper;

/**
 * @hide The client should not know how the internal communication is done
 */
@SuppressLint("ParcelCreator")
@MobileApi
public class DisconnectDeviceRequestIntent extends Intent implements ISpsIntentWrapper<DisconnectDeviceRequestIntent.IntentParams> {
    /**
     * Action to launch the discovery.
     */
    public static final String ACTION = "com.hp.jetadvantage.link.intent.action.DISCONNECT";
    private static final String EXTRA_PACKAGE_NAME = "packageName";

    /**
     * Constructor for ScanToRequestIntent.
     */
    public DisconnectDeviceRequestIntent() {
        super(ACTION);
    }

    /**
     * Parameters for ScanToRequestIntent.
     */
    @MobileApi
    public static class IntentParams {
        private final String mPackageName;

        /**
         * Creates IntentParams for ScanToRequestIntent.
         *
         * @param packageName    of the client
         */
        public IntentParams(final String packageName) {
            mPackageName = packageName;
        }

        public String getPackageName() {
            return mPackageName;
        }
    }

    /**
     * Retrieves the IntentParams of the ScanToRequestIntent.
     *
     * @param intent source intents
     * @return IntentParams
     */
    public static IntentParams getIntentParams(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return getIntentParams(intent.getExtras());
    }

    public static IntentParams getIntentParams(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String packageName = null;

        if (bundle.containsKey(EXTRA_PACKAGE_NAME)) {
            packageName = bundle.getString(EXTRA_PACKAGE_NAME);
        }

        return new IntentParams(packageName);
    }

    /**
     * Retrieves the IntentParams of the ScanToRequestIntent.
     */
    @Override
    public IntentParams getIntentParams() {
        return getIntentParams(this);
    }

    /**
     * Puts the IntentParams to this ScanToRequestIntent.
     *
     * @param params intent params to store
     * @return Intent
     */
    @SuppressLint("RestrictedApi")
    @Override
    public Intent putIntentParams(final IntentParams params) {
        Preconditions.checkNotNull(params);
        putExtra(EXTRA_PACKAGE_NAME, params.mPackageName);
        return this;
    }
}
