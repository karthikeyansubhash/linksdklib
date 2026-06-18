// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect;

import com.hp.workpath.common.annotation.MobileApi;

import java.util.regex.Pattern;

/**
 * Holds constants for communication with the ConnectDevicelet module.
 *
 * @hide
 */
@MobileApi
public class ConnectDevicelet {
    /**
     * @hide
     */
    public static final String TAG = "ConnectDevicelet";

    /**
     * @hide
     */
    public static final Pattern MAC_ADDRESS = Pattern.compile("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");

    /**
     * @hide
     */
    public static final int MINIMUM_TIMEOUT = 10;

    /**
     * @hide
     */
    public static final int MAXIMUM_TIMEOUT = 120;

    /**
     * Keys used for transporting data.
     * @hide
     */
    public interface Keys {
        /**
         * Key to retrieve the device name.
         * @hide
         */
        String KEY_NAME = "name";

        /**
         * Key to retrieve the device name.
         * @hide
         */
        String KEY_REQUEST = "request";
    }

    /**
     * @hide The client does not need to know about the server messages
     */
    public static final class Message {
        public static final int MSG_SHOW_CPD = 1;
        public static final int MSG_CANCEL_ALL = 2;
        // Foreground activity tracker messages
        public static final int MSG_IN_FG = 10;
    }
}
