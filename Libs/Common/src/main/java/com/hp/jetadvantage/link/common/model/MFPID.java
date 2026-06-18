// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.common.model;

import com.hp.workpath.common.Platform;

import java.text.MessageFormat;


/**
 * Define a unique MFPID
 * 
 * @hide 
 */
public class MFPID {
    private final String mID;
    private static final String PATTERN = "{0}/{1}";

    /**
     * Class Constructor determines the context: OPE or BYOD and sets an internal variable.
     *
     * @param host address of the printer, usually an IP.
     * @param bssid BSSID passed by the caller. This is used for verifying that a stored MFPID matches another
     */
    public MFPID(final String host, final String bssid) {
        if (Platform.isPanel()) {
            // Fixed: just the host address is enough
            mID = "panel";
        } else {
            mID = MessageFormat.format(PATTERN, bssid, host);
        }
    }

    public String toString() {
        return mID;
    }
}
