// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies.supplyinfo;

import android.support.annotation.Keep;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.json.EmptyStringAsNull;
import com.hp.workpath.common.utils.json.SerializedName;

/**
 * Provides Supply information
 *
 * @since API 5
 */
@DeviceApi
public class Supply {
    private int mVersion;
    private Counter[] Counter;
    private String ConsumableTypeEnum;
    private String Description;
    private String ProductNumber;
    private String SerialNumber;
    private String MakeAndModel;
    private com.hp.workpath.api.supplies.supplyinfo.Manufacturer Manufacturer;
    private String ApproxPercentRemainingOnInstall;
    private String ApproxPercentRemaining;
    private ConsumableStateAction AtVeryLowAction;
    private String ConsumableLowThreshold;
    private String ConsumableState;
    private String EstimatedPagesRemaining;
    private String SelectabilityNumber;
    private com.hp.workpath.api.supplies.supplyinfo.ApproximatePagesRemaining ApproximatePagesRemaining;
    private String CartridgeModelInformation;
    private String CartridgeModelLife;
    private String CartridgeModelCapacity;
    private String SupportedPartNumbers;
    private com.hp.workpath.api.supplies.supplyinfo.CycleCount CycleCount;
    private com.hp.workpath.api.supplies.supplyinfo.CycleLimit CycleLimit;
    private int CycleCountV2;
    private int CycleLimitV2;
    private String MarkerColor;
    private String AgentID;
    private String ConsumableContentType;
    private Capacity Capacity;
    private Installation Installation;
    private String LastUse;
    private RawElabelData RawElabelData;
    private int WebServiceAccessData;
    //    private PreviousCartridges PreviousCartridges; //Data is not existed
    private String ConsumableManufacturingSignature;
    private ConsumableStateAction AtLowAction;
    @EmptyStringAsNull
    private CartridgeInfo CartridgeInfo;
    private String ReserveState;
    private String Region;

    private Supply() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * Enums of ConsumableStateAction
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
     * <li>{@link Capacity#getMaxCapacity()}</li>
     * <li>{@link Capacity#getUnit()}</li>
     * </ul>"
     */
    @Keep
    @Deprecated
    public enum ConsumableStateAction {
        /**
         * The device's action will be to acknowledge the pen state e.g.
         * newGenuineHP. This state is exclusive with continue or stop actions and cannot exist in
         * the list of supported actions together for a pen state.
         *
         * @since API 5
         * @deprecated API 8 "No longer use ACKNOWLEDGE."
         */
        @Deprecated
        @SerializedName("acknowledge")
        ACKNOWLEDGE,

        /**
         * No specific action is required in this consumable
         * state.
         *
         * @since API 5
         * @deprecated API 8 "No longer use NONE."
         */
        @Deprecated
        @SerializedName("none")
        NONE,

        /**
         * The device's action will be stop when in the specific consumable
         * state.
         *
         * @since API 5
         * @deprecated API 8 "No longer use STOP."
         */
        @Deprecated
        @SerializedName("stop")
        STOP,

        /**
         * The device's action will be to continue when in the specific consumable
         * state.
         *
         * @deprecated API 8 "No longer use CONTINUE."
         */
        @Deprecated
        @SerializedName("continue")
        CONTINUE,

        /**
         * The device's action will be to continue with the counterfeit cartridge
         * that was sold to user as new genuine HP cartridge.
         *
         * @deprecated API 8 "No longer use CONTINUECOUNTERFEIT."
         */

        @Deprecated
        @SerializedName("continuecounterfeit")
        CONTINUECOUNTERFEIT,

        /**
         * The device's action will be to continue printing in black only when in
         * the specific consumable state.
         *
         * @deprecated API 8 "No longer use CONTINUEINBLACK."
         */

        @Deprecated
        @SerializedName("continueinblack")
        CONTINUEINBLACK
    }

    /**
     * Retrieves Capacity
     *
     * @since API 5
     */
    public class Capacity {
        private String Unit;
        private int MaxCapacity;

        /**
         * Retrieves Unit
         *
         * @return Unit
         * <ul>
         * <li>Return can be null if the value for Unit is null</li>
         * <li>Return can be empty if the value for Unit is empty</li>
         * </ul>
         * @since API 5
         */
        public String getUnit() {
            return Unit;
        }

        /**
         * Retrieves Maximum Capacity
         *
         * @return MaxCapacity
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getMaxCapacity() {
            return MaxCapacity;
        }
    }

    /**
     * Retrieves Installation information
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
     * <li>{@link Capacity#getMaxCapacity()}</li>
     * <li>{@link Capacity#getUnit()}</li>
     * </ul>"
     */
    @Deprecated
    public class Installation {
        private String Date;

        /**
         * Retrieves Date
         *
         * @return Date
         * <ul>
         * <li>Return can be null if the value for Date is null</li>
         * <li>Return can be empty if the value for Date is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getDate() {
            return Date;
        }
    }

    /**
     * Retrieves RawElabelData information
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
     * <li>{@link Capacity#getMaxCapacity()}</li>
     * <li>{@link Capacity#getUnit()}</li>
     * </ul>"
     */
    @Deprecated
    public class RawElabelData {
        private String CartridgeMemoryTagData1; //hexBinary
        private String ElabelMask; //hexBinary

        /**
         * Retrieves Cartridge Memory Tag Data1
         *
         * @return CartridgeMemoryTagData1
         * <ul>
         * <li>Return can be null if the value for CartridgeMemoryTagData1 is null</li>
         * <li>Return can be empty if the value for CartridgeMemoryTagData1 is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getCartridgeMemoryTagData1() {
            return CartridgeMemoryTagData1;
        }

        /**
         * Retrieves Elabel Mask
         *
         * @return ElabelMask
         * <ul>
         * <li>Return can be null if the value for ElabelMask is null</li>
         * <li>Return can be empty if the value for ElabelMask is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getElabelMask() {
            return ElabelMask;
        }
    }

    /**
     * Retrieves PreviousCartridges information
     *
     * @since API 5
     */
    public class PreviousCartridges {
        private PreviousCartridge[] PreviousCartridge;

        /**
         * Retrieves {@link PreviousCartridge} array
         *
         * @return PreviousCartridge
         * @since API 5
         */
        public PreviousCartridge[] getPreviousCartridge() {
            return PreviousCartridge;
        }
    }

    /**
     * Retrieves PreviousCartridge information
     *
     * @since API 5
     */
    public class PreviousCartridge {
        private int CartridgeData;

        /**
         * Retrieves Cartridge Data
         *
         * @return CartridgeData
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getCartridgeData() {
            return CartridgeData;
        }
    }

    /**
     * Retrieves {@link Counter} array
     *
     * @return Counter
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public Counter[] getCounter() {
        return Counter;
    }

    /**
     * Retrieves ConsumableTypeEnum
     *
     * @return ConsumableTypeEnum
     * <ul>
     * <li>Return can be null if the value for ConsumableTypeEnum is null</li>
     * <li>Return can be empty if the value for ConsumableTypeEnum is empty</li>
     * </ul>
     * @since API 5
     */
    public String getConsumableTypeEnum() {
        return ConsumableTypeEnum;
    }

    /**
     * Retrieves Description
     *
     * @return Description
     * <ul>
     * <li>Return can be null if the value for Description is null</li>
     * <li>Return can be empty if the value for Description is empty</li>
     * </ul>
     * @since API 5
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Retrieves Product Number
     *
     * @return ProductNumber
     * <ul>
     * <li>Return can be null if the value for ProductNumber is null</li>
     * <li>Return can be empty if the value for ProductNumber is empty</li>
     * </ul>
     * @since API 5
     */
    public String getProductNumber() {
        return ProductNumber;
    }

    /**
     * Retrieves Serial Number
     *
     * @return SerialNumber
     * <ul>
     * <li>Return can be null if the value for SerialNumber is null</li>
     * <li>Return can be empty if the value for SerialNumber is empty</li>
     * </ul>
     * @since API 5
     */
    public String getSerialNumber() {
        return SerialNumber;
    }

    /**
     * Retrieves Make And Model
     *
     * @return MakeAndModel
     * <ul>
     * <li>Return can be null if the value for MakeAndModel is null</li>
     * <li>Return can be empty if the value for MakeAndModel is empty</li>
     * </ul>
     * @since API 5
     */
    public String getMakeAndModel() {
        return MakeAndModel;
    }

    /**
     * Retrieves Marker Color
     *
     * @return MarkerColor
     * <ul>
     * <li>Return can be null if the value for MarkerColor is null</li>
     * <li>Return can be empty if the value for MarkerColor is empty</li>
     * </ul>
     * @since API 5
     */
    public String getMarkerColor() {
        return MarkerColor;
    }

    /**
     * Retrieves {@link Manufacturer}
     *
     * @return Manufacturer
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public Manufacturer getManufacturer() {
        return Manufacturer;
    }

    /**
     * Retrieves Approx Percent Remaining On Install
     *
     * @return ApproxPercentRemainingOnInstall
     * <ul>
     * <li>Return can be null if the value for ApproxPercentRemainingOnInstall is null</li>
     * <li>Return can be empty if the value for ApproxPercentRemainingOnInstall is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getApproxPercentRemainingOnInstall() {
        return ApproxPercentRemainingOnInstall;
    }

    /**
     * Retrieves Approx Percent Remaining
     *
     * @return ApproxPercentRemaining
     * <ul>
     * <li>Return can be null if the value for ApproxPercentRemaining is null</li>
     * <li>Return can be empty if the value for ApproxPercentRemaining is empty</li>
     * </ul>
     * @since API 5
     */
    public String getApproxPercentRemaining() {
        return ApproxPercentRemaining;
    }

    /**
     * Retrieves {@link ConsumableStateAction}
     *
     * @return AtVeryLowAction
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public ConsumableStateAction getAtVeryLowAction() {
        return AtVeryLowAction;
    }

    /**
     * Retrieves Consumable Low Threshold
     *
     * @return ConsumableLowThreshold
     * <ul>
     * <li>Return can be null if the value for ConsumableLowThreshold is null</li>
     * <li>Return can be empty if the value for ConsumableLowThreshold is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getConsumableLowThreshold() {
        return ConsumableLowThreshold;
    }

    /**
     * Retrieves Consumable State
     *
     * @return ConsumableState
     * <ul>
     * <li>Return can be null if the value for ConsumableState is null</li>
     * <li>Return can be empty if the value for ConsumableState is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getConsumableState() {
        return ConsumableState;
    }

    /**
     * Retrieves Estimated Pages Remaining
     *
     * @return EstimatedPagesRemaining
     * <ul>
     * <li>Return can be null if the value for EstimatedPagesRemaining is null</li>
     * <li>Return can be empty if the value for EstimatedPagesRemaining is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getEstimatedPagesRemaining() {
        return EstimatedPagesRemaining;
    }

    /**
     * Retrieves Selectability Number
     *
     * @return SelectabilityNumber
     * <ul>
     * <li>Return can be null if the value for SelectabilityNumber is null</li>
     * <li>Return can be empty if the value for SelectabilityNumber is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getSelectabilityNumber() {
        return SelectabilityNumber;
    }

    /**
     * Retrieves {@link ApproximatePagesRemaining}
     *
     * @return ApproximatePagesRemaining
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public ApproximatePagesRemaining getApproximatePagesRemaining() {
        return ApproximatePagesRemaining;
    }

    /**
     * Retrieves Cartridge Model Information
     *
     * @return CartridgeModelInformation
     * <ul>
     * <li>Return can be null if the value for CartridgeModelInformation is null</li>
     * <li>Return can be empty if the value for CartridgeModelInformation is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getCartridgeModelInformation() {
        return CartridgeModelInformation;
    }

    /**
     * Retrieves Cartridge Model Life
     *
     * @return CartridgeModelLife
     * <ul>
     * <li>Return can be null if the value for CartridgeModelLife is null</li>
     * <li>Return can be empty if the value for CartridgeModelLife is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getCartridgeModelLife() {
        return CartridgeModelLife;
    }

    /**
     * Retrieves Cartridge Model Capacity
     *
     * @return CartridgeModelCapacity
     * <ul>
     * <li>Return can be null if the value for CartridgeModelCapacity is null</li>
     * <li>Return can be empty if the value for CartridgeModelCapacity is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getCartridgeModelCapacity() {
        return CartridgeModelCapacity;
    }

    /**
     * Retrieves Supported Part Numbers
     *
     * @return SupportedPartNumbers
     * <ul>
     * <li>Return can be null if the value for SupportedPartNumbers is null</li>
     * <li>Return can be empty if the value for SupportedPartNumbers is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getSupportedPartNumbers() {
        return SupportedPartNumbers;
    }

    /**
     * Retrieves {@link CycleCount}
     *
     * @return CycleCount
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public CycleCount getCycleCount() {
        return CycleCount;
    }

    /**
     * Retrieves {@link CycleLimit}
     *
     * @return CycleLimit
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public CycleLimit getCycleLimit() {
        return CycleLimit;
    }

    /**
     * Retrieves Cycle Count V2
     *
     * @return CycleCountV2
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public int getCycleCountV2() {
        return CycleCountV2;
    }

    /**
     * Retrieves Cycle Limit V2
     *
     * @return CycleLimitV2
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public int getCycleLimitV2() {
        return CycleLimitV2;
    }

    /**
     * Retrieves Agent ID
     *
     * @return AgentID
     * <ul>
     * <li>Return can be null if the value for AgentID is null</li>
     * <li>Return can be empty if the value for AgentID is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getAgentID() {
        return AgentID;
    }

    /**
     * Retrieves Consumable Content Type
     *
     * @return ConsumableContentType
     * <ul>
     * <li>Return can be null if the value for ConsumableContentType is null</li>
     * <li>Return can be empty if the value for ConsumableContentType is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getConsumableContentType() {
        return ConsumableContentType;
    }

    /**
     * Retrieves Capacity
     *
     * @since API 5
     */
    public Supply.Capacity getCapacity() {
        return Capacity;
    }

    /**
     * Retrieves {@link Supply.Installation}
     *
     * @return Installation
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public Supply.Installation getInstallation() {
        return Installation;
    }

    /**
     * Retrieves Last Use
     *
     * @return LastUse
     * <ul>
     * <li>Return can be null if the value for LastUse is null</li>
     * <li>Return can be empty if the value for LastUse is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getLastUse() {
        return LastUse;
    }

    /**
     * Retrieves {@link RawElabelData}
     *
     * @return RawElabelData
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public Supply.RawElabelData getRawElabelData() {
        return RawElabelData;
    }

    /**
     * Retrieves Web Service Access Data
     *
     * @return WebServiceAccessData
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return 0."
     */
    @Deprecated
    public int getWebServiceAccessData() {
        return WebServiceAccessData;
    }

    /**
     * Retrieves Consumable Manufacturing Signature
     *
     * @return ConsumableManufacturingSignature
     * <ul>
     * <li>Return can be null if the value for ConsumableManufacturingSignature is null</li>
     * <li>Return can be empty if the value for ConsumableManufacturingSignature is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getConsumableManufacturingSignature() {
        return ConsumableManufacturingSignature;
    }

    /**
     * Retrieves {@link ConsumableStateAction}
     *
     * @return AtLowAction
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public ConsumableStateAction getAtLowAction() {
        return AtLowAction;
    }

    /**
     * Retrieves {@link com.hp.workpath.api.supplies.supplyinfo.CartridgeInfo}
     *
     * @return CartridgeInfo
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public com.hp.workpath.api.supplies.supplyinfo.CartridgeInfo getCartridgeInfo() {
        return CartridgeInfo;
    }

    /**
     * Retrieves Reserve State
     *
     * @return ReserveState
     * <ul>
     * <li>Return can be null if the value for ReserveState is null</li>
     * <li>Return can be empty if the value for ReserveState is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getReserveState() {
        return ReserveState;
    }

    /**
     * Retrieves Region
     *
     * @return Region
     * <ul>
     * <li>Return can be null if the value for Region is null</li>
     * <li>Return can be empty if the value for Region is empty</li>
     * </ul>
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public String getRegion() {
        return Region;
    }
}
