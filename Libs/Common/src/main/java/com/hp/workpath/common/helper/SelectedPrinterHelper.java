// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.helper;

import android.content.ContentResolver;
import android.os.Bundle;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.contract.SelectedPrinterContract;
import com.hp.jetadvantage.link.common.model.PrinterInfo;
import com.hp.jetadvantage.link.common.model.PrinterState;
import com.hp.workpath.common.utils.Preconditions;
import com.hp.workpath.common.utils.SLog;

/**
 * Assistance in accessing the selected printer information without having to know about
 * all of the storage internals
 * 
 * @author APS
 * 
 */
@SuppressWarnings({"unused"})
public class SelectedPrinterHelper {
    private static final String LOG_TAG = SelectedPrinterHelper.class.getSimpleName();

    private SelectedPrinterHelper() {
        // Non-instantiable class (utility / helper)
    }

    /**
     * Retrieve the printer information for the currently selected printer.
     * Otherwise, null.
     * 
     * @param cr
     *        The Android content resolver
     * @return The printer information for the currently selected printer
     */
    public static PrinterInfo get(final ContentResolver cr) {
        return getPrinter(cr, false);
    }

    /**
     * Retrieve the printer information for the currently selected printer.
     * Otherwise, null.
     *
     * @param cr
     *        The Android content resolver
     * @return The printer information for the currently selected printer
     */
    public static PrinterInfo getUpdated(final ContentResolver cr) {
        return getPrinter(cr, true);
    }

    /**
     * Retrieve the printer information for the currently selected printer.
     * Otherwise, null.
     *
     * @param cr
     *        The Android content resolver
     * @return The printer information for the currently selected printer
     */
    public static PrinterInfo set(final ContentResolver cr, final PrinterInfo printer) {
        return setPrinter(cr, printer);
    }

    /**
     * Clear the printer information (disconnect)
     */
    public static void clear(final ContentResolver cr) {
        cr.call(SelectedPrinterContract.CONTENT_URI, SelectedPrinterContract.Method.CLEAR, null, null);
    }

    /**
     * Clear the printer information (disconnect)
     */
    public static void offline(final ContentResolver cr) {
        Bundle extras = new Bundle();
        extras.putString(SelectedPrinterContract.KEY_PRINTER_STATE, PrinterState.DISCONNECTED.name());
        cr.call(SelectedPrinterContract.CONTENT_URI, SelectedPrinterContract.Method.CHANGE_PRINTER_STATE, null, extras);
    }

    /**
     * Clear the printer information (disconnect)
     */
    public static void online(final ContentResolver cr) {
        Bundle extras = new Bundle();
        extras.putString(SelectedPrinterContract.KEY_PRINTER_STATE, PrinterState.CONNECTED.name());
        cr.call(SelectedPrinterContract.CONTENT_URI, SelectedPrinterContract.Method.CHANGE_PRINTER_STATE, null, extras);
    }

    /**
     * Generic getter for printer info.
     *
     * {@link PrinterInfo} and not provide it from LSM, because it might not be defined in the client library and cause crash.
     *
     * @param cr {@link ContentResolver} to get printers data
     * @param update true if need to update printer details (i.e state)
     *
     * @return requested infoClass or null if no printer info or error happed
     */
    private static PrinterInfo getPrinter(final ContentResolver cr, final boolean update) {
        SLog.d(LOG_TAG, "Getting selected printer bundle");
        Preconditions.checkNotNull(cr, "Content resolver must be provided to get the selected printer");
        String requestMethod = SelectedPrinterContract.Method.GET;
        PrinterInfo printerInfo = null;

        Bundle params = new Bundle();
        params.putBoolean(SelectedPrinterContract.KEY_UPDATE, update);
        params.putInt(SelectedPrinterContract.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle bundle = cr.call(SelectedPrinterContract.CONTENT_URI, requestMethod, null, params);

        if (bundle != null) {
            bundle.setClassLoader(PrinterInfo.class.getClassLoader());
            printerInfo = bundle.getParcelable(SelectedPrinterContract.KEY_PRINTER_INFO);
        }

        return printerInfo;
    }

    private static PrinterInfo setPrinter(final ContentResolver cr, final PrinterInfo printer) {
        String requestMethod = SelectedPrinterContract.Method.ADD;

        Bundle extras = new Bundle();
        extras.putParcelable(SelectedPrinterContract.KEY_PRINTER_INFO, printer);
        cr.call(SelectedPrinterContract.CONTENT_URI, requestMethod, null, extras);

        return get(cr);
    }
}
