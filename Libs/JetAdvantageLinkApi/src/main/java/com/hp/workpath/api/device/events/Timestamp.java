// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.events;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * <p>Provides timestamp information.</p>
 *
 * @since API 5
 */
public class Timestamp {
    private int offset;
    private String time;

    /**
     * <p>Retrieve offset when device event occur.</p>
     *
     * @return int offset
     * @since API 5
     */
    @DeviceApi
    public int getOffset() {
        return offset;
    }

    /**
     * <p>Retrieve time when device event occur.</p>
     *
     * @return String time
     * @since API 5
     */
    @DeviceApi
    public String getTime() {
        return time;
    }
}
