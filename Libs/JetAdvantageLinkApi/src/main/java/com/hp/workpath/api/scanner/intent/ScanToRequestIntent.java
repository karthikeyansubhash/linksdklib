// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner.intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.jetadvantage.link.common.intents.ISpsIntentWrapper;
import com.hp.jetadvantage.link.common.model.ApiType;
import com.hp.jetadvantage.link.api.scanner.ScanAttributes;
import com.hp.jetadvantage.link.api.scanner.ScanletAttributes;

/**
 * @hide The client should not know how the internal communication is done
 */
@SuppressLint("ParcelCreator")
public class ScanToRequestIntent extends Intent implements ISpsIntentWrapper<ScanToRequestIntent.IntentParams> {
    /**
     * Action to launch the ScanTo Activity.
     */
    public static final String ACTION = "com.hp.jetadvantage.link.intent.action.SCANTO";
    private static final String EXTRA_SCAN_ATTRIBUTES = "scanAttrExtra";
    private static final String EXTRA_TASK_ATTRIBUTES = "taskAttrExtra";
    private static final String EXTRA_REQ_ID = "reqIDExtra";
    private static final String EXTRA_PACKAGE_NAME = "packageName";
    private static final String EXTRA_FORCED_API = "forcedApi";
    private static final String EXTRA_APP_ID = "applicationId";
    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_PASSWORD = "password";
    private static final String EXTRA_API_LEVEL = "apiLevel";

    /**
     * Constructor for ScanToRequestIntent.
     */
    public ScanToRequestIntent() {
        super(ACTION);
    }

    /**
     * Parameters for ScanToRequestIntent.
     */
    public static class IntentParams {
        private final ScanAttributes mScanAttributes;
        private final ScanletAttributes mTaskAttributes;
        private final String mReqId;
        private final String mPackageName;
        private final ApiType mForcedApi;
        private final String mApplicationId;
        private final String mUsername;
        private final String mPassword;
        private final Integer mApiLevel;

        /**
         * Creates IntentParams for ScanToRequestIntent.
         *
         * @param attributes     {@link ScanAttributes}
         * @param taskAttributes {@link ScanletAttributes}
         * @param reqId          of this job request
         * @param packageName    of the client
         * @param forcedApi      to enforce specific API-targeted XLet
         * @param username       device user name to access privileged functions
         * @param password       device user password to access privileged functions
         * @param apiLevel       API level value to override value in attributes
         */
        public IntentParams(final com.hp.workpath.api.scanner.ScanAttributes attributes, final com.hp.workpath.api.scanner.ScanletAttributes taskAttributes,
                            final String reqId, final String packageName, final ApiType forcedApi, final String applicationId,
                            String username, String password, Integer apiLevel) {
            Parcel parcelForScan = Parcel.obtain();
            if (attributes != null) {
                attributes.writeToParcel(parcelForScan, 0);
                parcelForScan.setDataPosition(0);
                mScanAttributes = ScanAttributes.CREATOR.createFromParcel(parcelForScan);
            } else {
                mScanAttributes = null;
            }

            if (taskAttributes == null) {
                mTaskAttributes = new ScanletAttributes.Builder().z();
            } else {
                Parcel parcelForTask = Parcel.obtain();
                taskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                mTaskAttributes = ScanletAttributes.CREATOR.createFromParcel(parcelForTask);
            }
            mReqId = reqId;
            mPackageName = packageName;
            mForcedApi = forcedApi;
            mApplicationId = applicationId;
            mUsername = username;
            mPassword = password;
            mApiLevel = apiLevel;
        }

        public com.hp.workpath.api.scanner.ScanAttributes getScanAttributes() {
            Parcel parcelForScan = Parcel.obtain();
            if (mScanAttributes != null) {
                mScanAttributes.writeToParcel(parcelForScan, 0);
                parcelForScan.setDataPosition(0);
                return com.hp.workpath.api.scanner.ScanAttributes.CREATOR.createFromParcel(parcelForScan);
            } else {
                return null;
            }
        }

        public com.hp.workpath.api.scanner.ScanletAttributes getTaskAttributes() {
            Parcel parcelForTask = Parcel.obtain();
            if (mTaskAttributes != null) {
                mTaskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                return com.hp.workpath.api.scanner.ScanletAttributes.CREATOR.createFromParcel(parcelForTask);
            } else {
                return null;
            }
        }

        public String getReqId() {
            return mReqId;
        }

        public String getPackageName() {
            return mPackageName;
        }

        public ApiType getForcedApi() {
            return mForcedApi;
        }

        public String getApplicationId() {
            return mApplicationId;
        }

        public String getUsername() {
            return mUsername;
        }

        public String getPassword() {
            return mPassword;
        }

        public Integer getApiLevel() {
            return mApiLevel;
        }
    }

    /**
     * Retrieves the IntentParams of the ScanToRequestIntent.
     */
    @Override
    public IntentParams getIntentParams() {
        return getIntentParams(this);
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

        com.hp.workpath.api.scanner.ScanAttributes sa = null;
        com.hp.workpath.api.scanner.ScanletAttributes ta = null;
        String rid = null;
        String packageName = null;
        ApiType forcedApi = null;
        String applicationId = null;
        String username = null;
        String password = null;
        Integer apiLevel = Sdk.VERSION_LEVEL.ONE;

        bundle.setClassLoader(com.hp.workpath.api.scanner.ScanAttributes.class.getClassLoader());

        if (bundle.containsKey(EXTRA_SCAN_ATTRIBUTES)) {
            sa = bundle.getParcelable(EXTRA_SCAN_ATTRIBUTES);
        }

        if (bundle.containsKey(EXTRA_TASK_ATTRIBUTES)) {
            bundle.setClassLoader(ScanletAttributes.class.getClassLoader());
            ta = bundle.getParcelable(EXTRA_TASK_ATTRIBUTES);
        }

        if (bundle.containsKey(EXTRA_REQ_ID)) {
            rid = bundle.getString(EXTRA_REQ_ID);
        }

        if (bundle.containsKey(EXTRA_PACKAGE_NAME)) {
            packageName = bundle.getString(EXTRA_PACKAGE_NAME);
        }

        if (bundle.containsKey(EXTRA_FORCED_API)) {
            forcedApi = ApiType.valueOf(bundle.getString(EXTRA_FORCED_API));
        }

        if (bundle.containsKey(EXTRA_APP_ID)) {
            applicationId = bundle.getString(EXTRA_APP_ID);
        }

        if (bundle.containsKey(EXTRA_USERNAME)) {
            username = bundle.getString(EXTRA_USERNAME);
        }

        if (bundle.containsKey(EXTRA_PASSWORD)) {
            password = bundle.getString(EXTRA_PASSWORD);
        }

        if (bundle.containsKey(EXTRA_API_LEVEL)) {
            apiLevel = bundle.getInt(EXTRA_API_LEVEL);
        }

        return new IntentParams(sa, ta, rid, packageName, forcedApi, applicationId, username, password, apiLevel);
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
        putExtra(EXTRA_SCAN_ATTRIBUTES, params.mScanAttributes);
        putExtra(EXTRA_TASK_ATTRIBUTES, params.mTaskAttributes);
        putExtra(EXTRA_REQ_ID, params.mReqId);
        putExtra(EXTRA_PACKAGE_NAME, params.mPackageName);
        if (params.mForcedApi != null) {
            putExtra(EXTRA_FORCED_API, params.mForcedApi.name());
        }
        putExtra(EXTRA_APP_ID, params.mApplicationId);
        putExtra(EXTRA_USERNAME, params.mUsername);
        putExtra(EXTRA_PASSWORD, params.mPassword);
        if (params.getApiLevel() != null) {
            putExtra(EXTRA_API_LEVEL, params.mApiLevel);
        }
        return this;
    }
}
