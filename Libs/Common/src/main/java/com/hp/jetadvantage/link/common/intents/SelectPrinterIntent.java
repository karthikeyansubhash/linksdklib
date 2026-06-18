// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.common.intents;

import android.content.Intent;

import com.hp.workpath.common.utils.SLog;

/**
 * Intent structure used to start printer selection
 * 
 * @author APS
 * 
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SelectPrinterIntent extends Intent implements
        ISpsIntentWrapper<SelectPrinterIntent.IntentParams> {
    private static final String TAG = SelectPrinterIntent.class.getSimpleName();

    /**
     * Action for the launch of the printer selection activity 
     */
    private static final String ACTION_SELECT_PRINTER = "com.hp.jetadvantage.link.action.SELECT_PRINTER";

    /**
     * Constructor for SelectPrinterIntent
     */
    public SelectPrinterIntent() {
        super(ACTION_SELECT_PRINTER);
    }

    /**
     * Parameters for a SelectPrinterIntent
     */
    public static class IntentParams {
    }

    /**
     * Retrieves the printer found intent parameters from the provided intent
     * 
     * @param intent The intent which contains the whether anonymous is requested by the user
     * @return The printer found intent parameters
     */
    @SuppressWarnings("SameReturnValue")
    public static IntentParams getIntentParams(final Intent intent) {
        SLog.d(TAG, "Intent does not specify any parameters");
        return null;
    }

    /**
     * Adds the printer found parameters provided to the intent
     * 
     * @param params The printer found parameters to add to the intent
     * @return Intent
     */
    @Override
    public Intent putIntentParams(final IntentParams params) {
        // No params to put
        return this;
    }

    /**
     * Returns the IntentParams for this intent
     */
    @Override
    public IntentParams getIntentParams() {
        return getIntentParams(this);
    }
}
