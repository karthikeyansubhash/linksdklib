// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery;

import com.hp.workpath.common.annotation.MobileApi;

/**
 * Holds constants for communication with the Discoverylet module.
 *
 * @hide
 */
@MobileApi
public class Discoverylet {
    /**
     * @hide
     */
    public static final String TAG = "Discoverylet";

    /**
     * @hide
     */
    public static final String DISCOVERY_STARTED_ACTION = "com.hp.jetadvantage.link.api.discovery.DISCOVERY_STARTED_ACTION";

    /**
     * @hide
     */
    public static final String DISCOVERY_STOPPED_ACTION = "com.hp.jetadvantage.link.api.discovery.DISCOVERY_STOPPED_ACTION";

    /**
     * @hide
     */
    public static final String DISCOVERY_FAILED_ACTION = "com.hp.jetadvantage.link.api.discovery.DISCOVERY_FAILED_ACTION";

    /**
     * @hide
     */
    public static final String DEVICE_DISCOVERED_ACTION = "com.hp.jetadvantage.link.api.discovery.DEVICE_DISCOVERED_ACTION";

    /**
     * @hide
     */
    public static final int MINIMUM_TIMEOUT = 10;

    /**
     * @hide
     */
    public static final int MAXIMUM_TIMEOUT = 120;

    /**
     * Keys used for transporting data. These are used to provide data to the client.
     * @hide
     */
    public static final class Keys {
        private Keys() {
            // Utility class
        }

        public static final String KEY_RESULT = "resultInfo";
        public static final String KEY_PRINTER_INFO = "printerInfo";
    }
}
