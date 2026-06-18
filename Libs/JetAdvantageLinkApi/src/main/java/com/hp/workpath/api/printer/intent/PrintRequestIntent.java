// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.printer.intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.jetadvantage.link.common.intents.ISpsIntentWrapper;
import com.hp.jetadvantage.link.common.model.ApiType;
import com.hp.jetadvantage.link.api.printer.PrintAttributes;
import com.hp.jetadvantage.link.api.printer.PrintletAttributes;

/**
 * @hide The client should not know how the internal communication is done
 */
@SuppressLint("ParcelCreator")
public class PrintRequestIntent extends Intent implements ISpsIntentWrapper<PrintRequestIntent.IntentParams> {
    /**
     * Action to launch the Print Activity.
     */
    public static final String ACTION = "com.hp.jetadvantage.link.intent.action.PRINT";
    private static final String EXTRA_PRINT_ATTRIBUTES = "printAttrExtra";
    private static final String EXTRA_TASK_ATTRIBUTES = "taskAttrExtra";

    private static final String EXTRA_REQ_ID = "reqIDExtra";
    private static final String EXTRA_PACKAGE_NAME = "packageName";
    private static final String EXTRA_FORCED_API = "forcedApi";
    private static final String EXTRA_APP_ID = "applicationId";
    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_PASSWORD = "password";
    private static final String EXTRA_API_LEVEL = "apiLevel";
    private static final String EXTRA_URI = "extraUri";

    /**
     * Constructor for PrintRequestIntent
     */
    public PrintRequestIntent() {
        super(ACTION);
    }

    /**
     * Parameters for PrintRequestIntent.
     */
    public static class IntentParams {
        private final PrintAttributes mPrintAttributes;
        private final PrintletAttributes mTaskAttributes;
        private final String mReqID;
        private final String mPackageName;
        private final ApiType mForcedApi;
        private final String mApplicationId;
        private final String mUsername;
        private final String mPassword;
        private final Integer mApiLevel;
        private final String mExtraUri;

        /**
         * Creates Params for PrintRequestIntent.
         *
         * @param printAttributes {@link PrintAttributes}
         * @param taskAttributes  {@link PrintletAttributes}
         * @param rid             Request ID
         * @param packageName     of the client
         * @param forcedApi       for this request
         */
        public IntentParams(final PrintAttributes printAttributes, final PrintletAttributes taskAttributes,
                            final String rid, final String packageName, final ApiType forcedApi, final Integer apiLevel) {
            mPrintAttributes = printAttributes;

            if (taskAttributes == null) {
                mTaskAttributes = new PrintletAttributes.Builder().z();
            } else {
                mTaskAttributes = taskAttributes;
            }
            mReqID = rid;
            mPackageName = packageName;
            mForcedApi = forcedApi;
            mApplicationId = null;
            mUsername = null;
            mPassword = null;
            mApiLevel = apiLevel;
            mExtraUri = null;
        }

        public IntentParams(final com.hp.workpath.api.printer.PrintAttributes printAttributes, final com.hp.workpath.api.printer.PrintletAttributes taskAttributes,
                            final String rid, final String packageName, final ApiType forcedApi, final String applicationId, String username, String password, final Integer apiLevel) {
            Parcel parcelForPrint = Parcel.obtain();
            if (printAttributes != null) {
                printAttributes.writeToParcel(parcelForPrint, 0);
                parcelForPrint.setDataPosition(0);
                mPrintAttributes = PrintAttributes.CREATOR.createFromParcel(parcelForPrint);
            } else {
                mPrintAttributes = null;
            }

            if (taskAttributes == null) {
                mTaskAttributes = new PrintletAttributes.Builder().z();
            } else {
                Parcel parcelForTask = Parcel.obtain();
                taskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                mTaskAttributes = PrintletAttributes.CREATOR.createFromParcel(parcelForTask);
            }
            mReqID = rid;
            mPackageName = packageName;
            mForcedApi = forcedApi;
            mApplicationId = applicationId;
            mUsername = username;
            mPassword = password;
            mApiLevel = apiLevel;
            mExtraUri = null;
        }

        public IntentParams(final com.hp.workpath.api.printer.PrintAttributes printAttributes, final com.hp.workpath.api.printer.PrintletAttributes taskAttributes,
                            final String rid, final String packageName, final ApiType forcedApi, final String applicationId, String username, String password, final Integer apiLevel, final String extraUri) {
            Parcel parcelForPrint = Parcel.obtain();
            if (printAttributes != null) {
                printAttributes.writeToParcel(parcelForPrint, 0);
                parcelForPrint.setDataPosition(0);
                mPrintAttributes = PrintAttributes.CREATOR.createFromParcel(parcelForPrint);
            } else {
                mPrintAttributes = null;
            }

            if (taskAttributes == null) {
                mTaskAttributes = new PrintletAttributes.Builder().z();
            } else {
                Parcel parcelForTask = Parcel.obtain();
                taskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                mTaskAttributes = PrintletAttributes.CREATOR.createFromParcel(parcelForTask);
            }
            mReqID = rid;
            mPackageName = packageName;
            mForcedApi = forcedApi;
            mApplicationId = applicationId;
            mUsername = username;
            mPassword = password;
            mApiLevel = apiLevel;

            if (extraUri == null) {
                mExtraUri = null;
            } else {
                mExtraUri = extraUri;
            }
        }

        public com.hp.workpath.api.printer.PrintAttributes getPrintAttributes() {
            Parcel parcelForPrint = Parcel.obtain();
            if (mPrintAttributes != null) {
                mPrintAttributes.writeToParcel(parcelForPrint, 0);
                parcelForPrint.setDataPosition(0);
                return com.hp.workpath.api.printer.PrintAttributes.CREATOR.createFromParcel(parcelForPrint);
            } else {
                return null;
            }
        }

        public com.hp.workpath.api.printer.PrintletAttributes getTaskAttributes() {
            Parcel parcelForTask = Parcel.obtain();
            if (mTaskAttributes != null) {
                mTaskAttributes.writeToParcel(parcelForTask, 0);
                parcelForTask.setDataPosition(0);
                return com.hp.workpath.api.printer.PrintletAttributes.CREATOR.createFromParcel(parcelForTask);
            } else {
                return null;
            }
        }

        public String getReqId() {
            return mReqID;
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

        public String getExtraUri() {
            return mExtraUri;
        }

    }

    /**
     * Retrieves the IntentParams of the PrintRequestIntent.
     */
    @Override
    public IntentParams getIntentParams() {
        return getIntentParams(this);
    }

    public static IntentParams getIntentParams(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return getIntentParams(intent.getExtras());
    }

    /**
     * Retrieves the IntentParams of the PrintRequestIntent.
     *
     * @param bundle with params data
     * @return IntentParams
     */
    public static IntentParams getIntentParams(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        com.hp.workpath.api.printer.PrintAttributes pa = null;
        com.hp.workpath.api.printer.PrintletAttributes ta = null;
        String rid = null;
        String packageName = null;
        ApiType forcedApi = null;
        String applicationId = null;
        String username = null;
        String password = null;
        Integer apiLevel = Sdk.VERSION_LEVEL.ONE;
        String extraUri = null;

        bundle.setClassLoader(PrintAttributes.class.getClassLoader());
        bundle.setClassLoader(PrintletAttributes.class.getClassLoader());

        if (bundle.containsKey(EXTRA_PRINT_ATTRIBUTES)) {
            pa = bundle.getParcelable(EXTRA_PRINT_ATTRIBUTES);
        }

        if (bundle.containsKey(EXTRA_TASK_ATTRIBUTES)) {
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

        if (bundle.containsKey(EXTRA_URI)) {
            extraUri = bundle.getString(EXTRA_URI);
        }

        return new IntentParams(pa, ta, rid, packageName, forcedApi, applicationId, username, password, apiLevel, extraUri);
    }

    /**
     * Puts the IntentParams to this PrintRequestIntent.
     *
     * @param params intent params to store
     * @return Intent
     */
    @SuppressLint("RestrictedApi")
    @Override
    public Intent putIntentParams(final IntentParams params) {
        Preconditions.checkNotNull(params);

        putExtra(EXTRA_PRINT_ATTRIBUTES, params.mPrintAttributes);
        putExtra(EXTRA_TASK_ATTRIBUTES, params.mTaskAttributes);
        putExtra(EXTRA_REQ_ID, params.mReqID);
        putExtra(EXTRA_PACKAGE_NAME, params.mPackageName);
        if (params.mForcedApi != null) {
            putExtra(EXTRA_FORCED_API, params.mForcedApi.name());
        }
        if (params.mApplicationId != null) {
            putExtra(EXTRA_APP_ID, params.getApplicationId());
        }
        putExtra(EXTRA_USERNAME, params.mUsername);
        putExtra(EXTRA_PASSWORD, params.mPassword);

        if (params.getApiLevel() != null) {
            putExtra(EXTRA_API_LEVEL, params.mApiLevel);
        }

        if (params.getExtraUri() != null) {
            putExtra(EXTRA_URI, params.mExtraUri);
        }
        return this;
    }
}