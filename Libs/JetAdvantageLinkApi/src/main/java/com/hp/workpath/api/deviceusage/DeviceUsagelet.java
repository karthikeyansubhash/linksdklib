// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.deviceusage;

import android.net.Uri;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Holds constants for communication with the DeviceUsagelet module.
 *
 * @hide for internal use
 * @since API 5
 */
@DeviceApi
public class DeviceUsagelet {

    private DeviceUsagelet() {
    }

    /**
     * @hide
     */
    public static final String TAG = "DeviceUsagelet";

    /**
     * @hide
     */
    public static final String IS_SUPPORTED_EXTRA = "com.hp.jetadvantage.link.api.deviceusage.extra.IS_SUPPORTED";

    /**
     * @hide
     */
    public static final class Keys {
        public static final String KEY_CLIENT_VERSION = "clientVersion";
        public static final String PACKAGE_NAME = "pkgname";
    }

    /**
     * @hide
     */
    public static final class Method {
        public static final String IS_SUPPORTED = "is_supported";
        public static final String GET_DEVICEUSAGE = "get_deviceusage";
    }

    /**
     * The parameters used for transporting data. These are used to provide data to the client.
     *
     * @hide The client should not need to know about the content provider parameters
     */
    public static final class Param {

        private Param() {
        }

        /**
         * Param to pass package name to DeviceUsagelet
         *
         * @hide for internal communication
         * @since API 5
         */
        public static final String PACKAGE_NAME = "pkgname";

        /**
         * @hide
         */
        public static final String KEY_CLIENT_VERSION = "clientVersion";
    }

    /**
     * DeviceUsagelet providers authority
     *
     * @hide for internal communication
     * @since API 5
     */
    public static final String AUTHORITY = "com.hp.jetadvantage.link.authority.deviceusageletcp";

    /**
     * Path for general DeviceUsagelet content provider calls
     *
     * @hide for internal communication
     * @since API 5
     */
    public static final String DIR_PATH_SEGMENT = "DeviceUsagelet";

    /**
     * DeviceUsagelet providers scheme
     *
     * @hide for internal communication
     * @since API 5
     */
    public static final String CONTENT_SCHEME = "content";

    /**
     * DeviceUsagelet providers content uri
     *
     * @hide for internal communication
     * @since API 5
     */
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(CONTENT_SCHEME).authority(DeviceUsagelet.AUTHORITY)
            .appendPath(DIR_PATH_SEGMENT).build();
}
