// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies.supplyinfo;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides FixedPointNumber information
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
@DeviceApi
public class FixedPointNumber {
    private int mVersion;
    private String Significand;
    private byte Exponent;

    private FixedPointNumber() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * Retrieves Significand value
     *
     * @return Significand
     * <ul>
     * <li>Return can be null if the value for Significand is null</li>
     * <li>Return can be empty if the value for Significand is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getSignificand() {
        return Significand;
    }

    /**
     * Retrieves Exponent value
     *
     * @return Exponent
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public byte getExponent() {
        return Exponent;
    }
}
