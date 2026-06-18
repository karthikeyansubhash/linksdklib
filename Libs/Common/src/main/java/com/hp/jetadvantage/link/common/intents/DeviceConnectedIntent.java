// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.common.intents;

import android.content.Intent;
import android.os.Bundle;

import com.hp.jetadvantage.link.common.model.PrinterInfo;
import com.hp.workpath.common.utils.Preconditions;

public class DeviceConnectedIntent extends Intent implements ISpsIntentWrapper<DeviceConnectedIntent.IntentParams> {
    /**
     * Intent action tag
     */
    public static final String ACTION_DEVICE_CONNECTED = "com.hp.jetadvantage.link.action.DEVICE_CONNECTED";

    /**
     * Mandatory tags
     */
    private static final String TAG_DATA = "data";
    private static final String TAG_RESULT = "result";

    /**
     * Constructor
     */
    public DeviceConnectedIntent(){
        super(ACTION_DEVICE_CONNECTED);
    }

    /**
     * @param intent
     *        Constructor
     */
    public DeviceConnectedIntent(final Intent intent){
        super(intent);
    }

    /**
     * Parameters for a connected broadcast
     */
    public static class IntentParams {

        final PrinterInfo mPrinterInfo;
        final Bundle mResult;

        /**
         * Create intent parameters for the connected broadcast
         *
         * @param printerInfo connected device info
         */
        public IntentParams(final PrinterInfo printerInfo, final Bundle bundle){
            // Must for mandatory only
            Preconditions.checkNotNull(printerInfo);
            mPrinterInfo = printerInfo;
            mResult = bundle;

            // optional
        }

        public PrinterInfo getPrinterInfo() {
            return mPrinterInfo;
        }

        public Bundle getResult() {
            return mResult;
        }
    }

    /**
     * Adds the UP connected parameters provided to the intent
     *
     * @param params
     *        The UP connected parameters to add to the intent
     */
    @Override
    public Intent putIntentParams(final IntentParams params) {
        Preconditions.checkNotNull(params);
        putExtra(TAG_DATA, params.mPrinterInfo);
        putExtra(TAG_RESULT, params.mResult);

        return this;
    }

    /**
     *
     */
    @Override
    public IntentParams getIntentParams(){
        final PrinterInfo printerInfp = getParcelableExtra(TAG_DATA);
        final Bundle bundle = getParcelableExtra(TAG_RESULT);
        return new IntentParams(printerInfp, bundle);
    }
}
