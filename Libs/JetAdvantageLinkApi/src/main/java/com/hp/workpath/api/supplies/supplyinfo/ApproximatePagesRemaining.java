// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies.supplyinfo;

import android.support.annotation.Keep;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.json.SerializedName;

/**
 * Provides ApproximatePagesRemaining information
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
@Deprecated
public class ApproximatePagesRemaining {
    private int mVersion;
    private ApproximatePagesRemainingSymbol ApproximatePagesRemainingSymbol;
    private int ApproximatePagesRemainingValue;

    private ApproximatePagesRemaining() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * Enums of ApproximatePagesRemainingSymbol
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
    @Keep
    @Deprecated
    public enum ApproximatePagesRemainingSymbol {
        /**
         * DASHDASH
         *
         * @since API 5
         * @deprecated API 8 "No longer use DASHDASH."
         */
        @Deprecated
        @SerializedName("dashDash")
        DASHDASH,
        /**
         * GREATERTHAN
         *
         * @since API 5
         * @deprecated API 8 "No longer use GREATERTHAN."
         */
        @Deprecated
        @SerializedName("greaterThan")
        GREATERTHAN,
        /**
         * LESSTHAN
         *
         * @since API 5
         * @deprecated API 8 "No longer use LESSTHAN."
         */
        @Deprecated
        @SerializedName("lessThan")
        LESSTHAN,
        /**
         * QUESTIONMARK
         *
         * @since API 5
         * @deprecated API 8 "No longer use QUESTIONMARK."
         */
        @Deprecated
        @SerializedName("questionMark")
        QUESTIONMARK
    }

    /**
     * Returns the value of approximate pages remaining symbol
     *
     * @return ApproximatePagesRemainingSymbol
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public ApproximatePagesRemainingSymbol getApproximatePagesRemainingSymbol() {
        return ApproximatePagesRemainingSymbol;
    }

    /**
     * Returns the value of approximate pages remaining
     *
     * @return ApproximatePagesRemainingValue
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public int getApproximatePagesRemainingValue() {
        return ApproximatePagesRemainingValue;
    }
}
