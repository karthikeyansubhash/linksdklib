// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect.intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.api.device.connect.ConnectDeviceAttributes;
import com.hp.jetadvantage.link.api.device.connect.ConnectDeviceletAttributes;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.jetadvantage.link.common.intents.ISpsIntentWrapper;

/**
 * @hide The client should not know how the internal communication is done
 */
@SuppressLint("ParcelCreator")
@MobileApi
public class ConnectDeviceRequestIntent extends Intent implements ISpsIntentWrapper<ConnectDeviceRequestIntent.IntentParams> {
    /**
     * Action to launch the discovery.
     */
    public static final String ACTION = "com.hp.jetadvantage.link.intent.action.CONNECT";
    private static final String EXTRA_ATTRIBUTES = "connectAttrExtra";
    private static final String EXTRA_TASK_ATTRIBUTES = "taskAttrExtra";
    private static final String EXTRA_PACKAGE_NAME = "packageName";

    /**
     * Constructor for DiscoveryStartRequestIntent.
     */
    public ConnectDeviceRequestIntent() {
        super(ACTION);
    }

    /**
     * Parameters for DiscoveryStartRequestIntent.
     */
    @MobileApi
    public static class IntentParams {
        private final ConnectDeviceAttributes mDiscoveryAttributes;
        private final ConnectDeviceletAttributes mTaskAttributes;
        private final String mPackageName;

        /**
         * Creates IntentParams for DiscoveryStartRequestIntent.
         *
         * @param attributes     {@link ConnectDeviceAttributes}
         * @param taskAttributes {@link ConnectDeviceletAttributes}
         * @param packageName    of the client
         */
        public IntentParams(final com.hp.workpath.api.device.connect.ConnectDeviceAttributes attributes, final com.hp.workpath.api.device.connect.ConnectDeviceletAttributes taskAttributes,
                            final String packageName) {
            Parcel parcelForConnect = Parcel.obtain();
            if (attributes != null) {
                attributes.writeToParcel(parcelForConnect, 0);
                parcelForConnect.setDataPosition(0);
                mDiscoveryAttributes = ConnectDeviceAttributes.CREATOR.createFromParcel(parcelForConnect);
            } else {
                mDiscoveryAttributes = null;
            }

            if (taskAttributes == null) {
                mTaskAttributes = new ConnectDeviceletAttributes.Builder().z();
            } else {
                Parcel parcelForTask = Parcel.obtain();
                taskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                mTaskAttributes = ConnectDeviceletAttributes.CREATOR.createFromParcel(parcelForTask);
            }

            mPackageName = packageName;
        }

        public com.hp.workpath.api.device.connect.ConnectDeviceAttributes getDiscoveryAttributes() {
            Parcel parcelForConnect = Parcel.obtain();
            if (mDiscoveryAttributes != null) {
                mDiscoveryAttributes.writeToParcel(parcelForConnect, 0);
                parcelForConnect.setDataPosition(0);
                return com.hp.workpath.api.device.connect.ConnectDeviceAttributes.CREATOR.createFromParcel(parcelForConnect);
            } else {
                return null;
            }
        }

        public com.hp.workpath.api.device.connect.ConnectDeviceletAttributes getTaskAttributes() {
            Parcel parcelForTask = Parcel.obtain();
            if (mTaskAttributes != null) {
                mTaskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                return com.hp.workpath.api.device.connect.ConnectDeviceletAttributes.CREATOR.createFromParcel(parcelForTask);
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

        com.hp.workpath.api.device.connect.ConnectDeviceAttributes sa = null;
        com.hp.workpath.api.device.connect.ConnectDeviceletAttributes ta = null;
        String packageName = null;

        if (bundle.containsKey(EXTRA_ATTRIBUTES)) {
            bundle.setClassLoader(ConnectDeviceAttributes.class.getClassLoader());
            sa = bundle.getParcelable(EXTRA_ATTRIBUTES);
        }

        if (bundle.containsKey(EXTRA_TASK_ATTRIBUTES)) {
            bundle.setClassLoader(ConnectDeviceletAttributes.class.getClassLoader());
            ta = bundle.getParcelable(EXTRA_TASK_ATTRIBUTES);
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
        putExtra(EXTRA_ATTRIBUTES, params.mDiscoveryAttributes);
        putExtra(EXTRA_TASK_ATTRIBUTES, params.mTaskAttributes);
        putExtra(EXTRA_PACKAGE_NAME, params.mPackageName);
        return this;
    }
}
