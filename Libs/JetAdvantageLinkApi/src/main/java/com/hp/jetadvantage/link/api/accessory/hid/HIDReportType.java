// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.accessory.hid;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides the accessory report types.
 *
 * @hide
 * @since API 3
 */
@DeviceApi
@Deprecated
public enum HIDReportType {
    FEATURE,
    INPUT,
    OUTPUT
}
