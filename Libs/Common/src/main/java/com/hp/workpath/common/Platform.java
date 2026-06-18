// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common;

import android.os.Build;

/**
 * Platform
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Platform {

    private static final String PRINTER_PANEL = "printer";

    private static final String GENERIC_SDK = "generic";

    /**
     * Method determines the context this code is executing in: printer panel or mobile
     *
     * @return Returns true if the context is printer panel
     */
    public static boolean isPanel() {
        return Build.MODEL.toLowerCase().contains(PRINTER_PANEL) || isEmulator();
    }

    /**
     * @return Returns true if the context is mobile
     */
    public static boolean isMobile() {
        return !isPanel();
    }

    /**
     * @return Returns true if the context is Emulator
     */
    public static boolean isEmulator() {
        return Build.BRAND.startsWith(GENERIC_SDK) || Build.FINGERPRINT.contains(GENERIC_SDK);
    }
}