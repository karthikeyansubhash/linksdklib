// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery.intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.annotation.MobileApi;
import com.hp.jetadvantage.link.api.device.discovery.DiscoveryAttributes;
import com.hp.jetadvantage.link.api.device.discovery.DiscoveryletAttributes;
import com.hp.jetadvantage.link.common.intents.ISpsIntentWrapper;

/**
 * @hide The client should not know how the internal communication is done
 */
@SuppressLint("ParcelCreator")
@MobileApi
public class DiscoveryStartRequestIntent extends Intent implements ISpsIntentWrapper<DiscoveryStartRequestIntent.IntentParams> {
    /**
     * Action to launch the discovery.
     */
    public static final String ACTION = "com.hp.jetadvantage.link.intent.action.START_DISCOVERY";
    private static final String EXTRA_DISCOVERY_ATTRIBUTES = "discoveryAttrExtra";
    private static final String EXTRA_LET_ATTRIBUTES = "letAttrExtra";
    private static final String EXTRA_PACKAGE_NAME = "packageName";

    /**
     * Constructor for DiscoveryStartRequestIntent.
     */
    public DiscoveryStartRequestIntent() {
        super(ACTION);
    }

    /**
     * Parameters for DiscoveryStartRequestIntent.
     */
    @MobileApi
    public static class IntentParams {
        private final DiscoveryAttributes mDiscoveryAttributes;
        private final DiscoveryletAttributes mUiAttributes;
        private final String mPackageName;

        /**
         * Creates IntentParams for DiscoveryStartRequestIntent.
         *
         * @param attributes     {@link DiscoveryAttributes}
         * @param uiAttributes {@link DiscoveryletAttributes}
         * @param packageName    of the client
         */
        public IntentParams(final com.hp.workpath.api.device.discovery.DiscoveryAttributes attributes, final com.hp.workpath.api.device.discovery.DiscoveryletAttributes uiAttributes,
                            final String packageName) {
            Parcel parcelForDiscovery = Parcel.obtain();
            if (attributes != null) {
                attributes.writeToParcel(parcelForDiscovery, 0);
                parcelForDiscovery.setDataPosition(0);
                mDiscoveryAttributes = DiscoveryAttributes.CREATOR.createFromParcel(parcelForDiscovery);
            } else {
                mDiscoveryAttributes = null;
            }

            Parcel parcelForDiscoveryLet = Parcel.obtain();
            if (uiAttributes == null) {
                mUiAttributes = new DiscoveryletAttributes.Builder().z();
            } else {
                uiAttributes.writeToParcel(parcelForDiscoveryLet, 0);
                parcelForDiscoveryLet.setDataPosition(0);
                mUiAttributes = DiscoveryletAttributes.CREATOR.createFromParcel(parcelForDiscoveryLet);
            }

            mPackageName = packageName;
        }

        public com.hp.workpath.api.device.discovery.DiscoveryAttributes getDiscoveryAttributes() {
            Parcel parcelForDiscovery = Parcel.obtain();
            if (mDiscoveryAttributes != null) {
                mDiscoveryAttributes.writeToParcel(parcelForDiscovery, 0);
                parcelForDiscovery.setDataPosition(0);
                return com.hp.workpath.api.device.discovery.DiscoveryAttributes.CREATOR.createFromParcel(parcelForDiscovery);
            } else {
                return null;
            }
        }

        public com.hp.workpath.api.device.discovery.DiscoveryletAttributes getUiAttributes() {
            Parcel parcelForDiscoveryLet = Parcel.obtain();
            if (mUiAttributes != null) {
                mUiAttributes.writeToParcel(parcelForDiscoveryLet, 0);
                parcelForDiscoveryLet.setDataPosition(0);
                return com.hp.workpath.api.device.discovery.DiscoveryletAttributes.CREATOR.createFromParcel(parcelForDiscoveryLet);
            } else {
                return null;
            }
        }

        public String getPackageName() {
            return mPackageName;
        }
    }

    /**
     * Retrieves the IntentParams of the DiscoveryStartRequestIntent.
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

        com.hp.workpath.api.device.discovery.DiscoveryAttributes sa = null;
        com.hp.workpath.api.device.discovery.DiscoveryletAttributes ta = null;
        String packageName = null;

        bundle.setClassLoader(DiscoveryAttributes.class.getClassLoader());
        if (bundle.containsKey(EXTRA_DISCOVERY_ATTRIBUTES)) {
            sa = bundle.getParcelable(EXTRA_DISCOVERY_ATTRIBUTES);
        }

        bundle.setClassLoader(DiscoveryletAttributes.class.getClassLoader());
        if (bundle.containsKey(EXTRA_LET_ATTRIBUTES)) {
            ta = bundle.getParcelable(EXTRA_LET_ATTRIBUTES);
        }

        if (bundle.containsKey(EXTRA_PACKAGE_NAME)) {
            packageName = bundle.getString(EXTRA_PACKAGE_NAME);
        }

        return new IntentParams(sa, ta, packageName);
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
        putExtra(EXTRA_DISCOVERY_ATTRIBUTES, params.mDiscoveryAttributes);
        putExtra(EXTRA_LET_ATTRIBUTES, params.mUiAttributes);
        putExtra(EXTRA_PACKAGE_NAME, params.mPackageName);
        return this;
    }
}
