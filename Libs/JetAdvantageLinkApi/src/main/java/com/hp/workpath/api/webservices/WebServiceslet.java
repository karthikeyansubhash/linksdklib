// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import android.net.Uri;

/**
 * Holds constants for communication with the WebServiceslet module.
 *
 * @hide for internal use
 * @since API 6
 */
public class WebServiceslet {

    private WebServiceslet() {
    }

    /**
     * @hide
     */
    public static final String TAG = "WebServiceslet";

    /**
     * @hide
     */
    public static final String PROVIDER_EVENT_ACTION = "com.hp.workpath.api.action.WEBSERVICES";


    /**
     * @hide
     */
    public static final String REQUEST_EXTRA = "com.hp.jetadvantage.link.api.webservices.extra.REQUEST";

    /**
     * @hide
     */
    public static final class Keys {
        public static final String KEY_CLIENT_VERSION = "clientVersion";
        public static final String KEY_ACTION = "action";
        public static final String KEY_CLIENT_ID = "clientId";
        public static final String PACKAGE_NAME = "pkgname";
    }

    /**
     * WebServiceslet providers authority
     *
     * @hide for internal communication
     */
    public static final String AUTHORITY = "com.hp.workpath.authority.webservicesletcp";

    /**
     * Path for general WebServiceslet content provider calls
     *
     * @hide for internal communication
     */
    public static final String DIR_PATH_SEGMENT = "WebServiceslet";

    /**
     * WebServiceslet providers scheme
     *
     * @hide for internal communication
     */
    private static final String CONTENT_SCHEME = "content";

    /**
     * WebServiceslet providers content uri
     *
     * @hide for internal communication
     */
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(CONTENT_SCHEME).authority(WebServiceslet.AUTHORITY)
            .appendPath(DIR_PATH_SEGMENT).build();
} 