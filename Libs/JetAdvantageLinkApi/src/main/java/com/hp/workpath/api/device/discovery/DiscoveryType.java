// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery;

import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>Enumeration of discovery types.</p>
 *
 * @since API 1
 */
@SuppressWarnings({"unused"})
@MobileApi
public enum DiscoveryType {
    /**
     * <p>The option to discover devices connected by Wi-Fi Direct</p>
     *
     * @since API 1
     */
    WIFI_DIRECT,

    /**
     * <p>The option to discover devices connected by network (Wi-Fi)</p>
     *
     * @since API 1
     */
    LOCAL
}
