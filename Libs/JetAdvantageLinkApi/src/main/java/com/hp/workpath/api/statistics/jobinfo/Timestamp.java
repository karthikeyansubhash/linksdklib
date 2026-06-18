// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Timestamp information
 *
 * @since API 5
 */
@DeviceApi
public class Timestamp {
    private int offset;
    private String time;

    /**
     * Returns offset of Timestamp
     *
     * @return offset
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Returns time of Timestamp
     *
     * @return time
     * <p>
     * <ul>
     * <li>Return can be null if the time is null</li>
     * <li>Return can be null if the time is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getTime() {
        return time;
    }
}
