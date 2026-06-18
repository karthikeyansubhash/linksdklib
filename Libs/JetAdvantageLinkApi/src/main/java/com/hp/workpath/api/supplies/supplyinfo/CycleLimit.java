// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies.supplyinfo;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides CycleLimit information
 *
 * @since API 5
 * @deprecated API 8 "Deprecated due to change in scope of supply data. Supply data are currently only managed by the 9 selected APIs:
 * <ul>
 * <li>{@link Supply#getConsumableTypeEnum()}</li>
 * <li>{@link Supply#getMakeAndModel()}</li>
 * <li>{@link Supply#getProductNumber()}</li>
 * <li>{@link Supply#getSerialNumber()}</li>
 * <li>{@link Supply#getDescription()}</li>
 * <li>{@link Supply#getMarkerColor()}</li>
 * <li>{@link Supply#getApproxPercentRemaining()}</li>
 * <li>{@link com.hp.workpath.api.supplies.supplyinfo.Supply.Capacity#getMaxCapacity()}</li>
 * <li>{@link com.hp.workpath.api.supplies.supplyinfo.Supply.Capacity#getUnit()}</li>
 * </ul>"
 */
@Deprecated
@DeviceApi
public class CycleLimit {
    private int Version;
    private int Capacity;
    private String Unit;

    private CycleLimit() {
        Version = Sdk.VERSION.LEVEL;
    }

    /**
     * Retrieves Capacity
     *
     * @return Capacity
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public int getCapacity() {
        return Capacity;
    }

    /**
     * Retrieves Unit
     *
     * @return Unit
     * <ul>
     * <li>Return can be null if the value for Unit is null</li>
     * <li>Return can be empty if the value for Unit is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getUnit() {
        return Unit;
    }
}
