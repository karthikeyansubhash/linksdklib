// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies.supplyinfo;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Cartridge information
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
public class CartridgeInfo {
    private CartridgeAnalytic Current;
    private CartridgeAnalytic Previous;

    /**
     * Retrieves the current {@link CartridgeAnalytic}
     *
     * @return Current CartridgeAnalytic
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    @Deprecated
    public CartridgeAnalytic getCurrent() {
        return Current;
    }

    /**
     * Retrieves the previous CartridgeAnalytic
     *
     * @return Previous CartridgeAnalytic
     * @since API 5
     * @deprecated API 8 "No longer supported. It will be return null."
     */
    public CartridgeAnalytic getPrevious() {
        return Previous;
    }

    /**
     * Retrieves CartridgeAnalytic
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
    public class CartridgeAnalytic {
        private String CartridgeModelInformation;
        private String CartridgeModelLife;
        private Manufacturer Manufacturer;
        private String SerialNumber;
        private Supply.Installation Installation;
        private String LastUse;
        private String ConsumableState;
        private boolean CanonMade;
        private boolean NewCartridge;
        private boolean TonerLow;
        private boolean TonerOut;
        private boolean DeveloperLifeLow;
        private boolean DeveloperLifeOut;
        private boolean OPCLifeLow;
        private boolean OPCLifeOut;
        private boolean DefaultLowMessageReached;
        private int A3Ledger;
        private int LGLB4;
        private int A4Letter;
        private int ExecutiveB5A5A6B616k;
        private int Envelope;
        private int Others;
        private String LetterAreaConverted;
        private String LetterAreaConvertedMono;
        private int TotalRegardlessOfSize;
        private int SheetDuplex;
        private int MonoColorRegardlessOfSize;
        private int MonoColor2RegardlessOfSize;
        private int FormatterColorPageCount;
        private int CopyPageCountMono;
        private int CopyPageCountColor;
        private int FaxPageCount;
        private int FormatterPagesWithTonerSavings;
        private int FormatterPagesAtOut;
        private int EngineJobCount;
        private int EngineCyclesCountAtInstall;
        private int CPRCalibrationsCount;
        private int DhalfCalibrationsCount;
        private int CleaningCyclesCount;
        private int PowerCyclesCount;
        private int DeviceModelCode;
        private int EngineTonerRemaining;
        private int FormatterDesignatedRemainingToner;
        private int DrumLife;
        private int DeveloperLife;
        private int DeveloperWearLife;
        private int WasteTonerContainerRemaining;
        private int EngineTonerRemainingLevelAtVeryLow;
        private int FormatterDesignatedRemainingTonerAtVeryLow;
        private int DrumLifeAtVeryLow;
        private int DeveloperLifeAtVeryLow;
        private int DeveloperWearLifeAtVeryLow;
        private String CartridgeLastUsedDateAtVeryLow;
        private int PageCountLetterAreaConvertedAtVeryLow;
        private int PageCountMonoColor2RegardlessSizeAtVeryLow;
        private String ConsumableManufacturingSignature;
        private String ProductNumber;
        private String ApproxPercentRemaining;
        private Supply.Capacity Capacity;
        private int WebServiceAccessData;
        private String ReplacementDate;
        private String RecordedDateTime;
        private com.hp.workpath.api.supplies.supplyinfo.CycleCount CycleCount;
        private com.hp.workpath.api.supplies.supplyinfo.CycleLimit CycleLimit;
        private int CycleCountV2;
        private int CycleLimitV2;
        private boolean InstalledWhileMarkerCartridgeProtectionWarningOverrideActive;

        /**
         * Retrieves the Cartridge Model Information
         *
         * @return CartridgeModelInformation
         * @since API 5
         * precated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getCartridgeModelInformation() {
            return CartridgeModelInformation;
        }

        /**
         * Retrieves the Cartridge Model Life
         *
         * @return CartridgeModelLife
         * <p>
         * <p>
         * ** @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getCartridgeModelLife() {
            return CartridgeModelLife;
        }

        /**
         * Retrieves the {@link Manufacturer}
         *
         * @return Manufacturer detail
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public com.hp.workpath.api.supplies.supplyinfo.Manufacturer getManufacturer() {
            return Manufacturer;
        }

        /**
         * Retrieves the SerialNumber
         *
         * @return SerialNumber - value for the Serial Number
         * <ul>
         * <li>Return can be null if the value for serial number is null</li>
         * <li>Return can be empty if the value for serial number is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getSerialNumber() {
            return SerialNumber;
        }

        /**
         * Retrieves the {@link Supply.Installation} information
         *
         * @return Installation information
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public Supply.Installation getInstallation() {
            return Installation;
        }

        /**
         * Retrieves LastUse information
         *
         * @return Last Use
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
         * Retrieves ConsumableState information
         *
         * @return - ConsumableState information
         * <ul>
         * <li>Return can be null if the value for serial number is null</li>
         * <li>Return can be empty if the value for serial number is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getConsumableState() {
            return ConsumableState;
        }

        /**
         * Retrieves CanonMade or not
         *
         * @retun CanonMade
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isCanonMade() {
            return CanonMade;
        }

        /**
         * Retrieves NewCartridge or not
         *
         * @return NewCartridge
         * @since API 5
         * @deprecated API 8 "No longer supported.  It will be return false."
         */
        @Deprecated
        public boolean isNewCartridge() {
            return NewCartridge;
        }

        /**
         * Retrieves TonerLow or not
         *
         * @return TonerLow
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isTonerLow() {
            return TonerLow;
        }

        /**
         * Retrieves TonerOut or not
         *
         * @return TonerOut
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isTonerOut() {
            return TonerOut;
        }

        /**
         * Retrieves DeveloperLifeLow or not
         *
         * @return DeveloperLifeLow
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isDeveloperLifeLow() {
            return DeveloperLifeLow;
        }

        /**
         * Retrieves DeveloperLifeOut or not
         *
         * @return DeveloperLifeOut
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isDeveloperLifeOut() {
            return DeveloperLifeOut;
        }

        /**
         * Retrieves OPCLifeLow or not
         *
         * @return OPCLifeLow
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isOPCLifeLow() {
            return OPCLifeLow;
        }

        /**
         * Retrieves OPCLifeOut or not
         *
         * @return OPCLifeOut
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isOPCLifeOut() {
            return OPCLifeOut;
        }

        /**
         * Retrieves DefaultLowMessageReached or not
         *
         * @return DefaultLowMessageReached
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return false."
         */
        @Deprecated
        public boolean isDefaultLowMessageReached() {
            return DefaultLowMessageReached;
        }

        /**
         * Retrieves A3Ledger
         *
         * @return A3Ledger count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getA3Ledger() {
            return A3Ledger;
        }

        /**
         * Retrieves LGLB4
         *
         * @return LGLB4 count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getLGLB4() {
            return LGLB4;
        }

        /**
         * Retrieves A4Letter
         *
         * @return A4Letter count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getA4Letter() {
            return A4Letter;
        }

        /**
         * Retrieves ExecutiveB5A5A6B616k
         *
         * @return ExecutiveB5A5A6B616k count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getExecutiveB5A5A6B616k() {
            return ExecutiveB5A5A6B616k;
        }

        /**
         * Retrieves Envelope
         *
         * @return Envelope count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getEnvelope() {
            return Envelope;
        }

        /**
         * Retrieves Others
         *
         * @return Others count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getOthers() {
            return Others;
        }

        /**
         * Retrieves LetterAreaConverted
         *
         * @return LetterAreaConverted value
         * <ul>
         * <li>Return can be null if the value for LetterAreaConverted is null</li>
         * <li>Return can be empty if the value for LetterAreaConverted is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getLetterAreaConverted() {
            return LetterAreaConverted;
        }

        /**
         * Retrieves LetterAreaConvertedMono
         *
         * @return LetterAreaConvertedMono
         * <ul>
         * <li>Return can be null if the value for LetterAreaConvertedMono is null</li>
         * <li>Return can be empty if the value for LetterAreaConvertedMono is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getLetterAreaConvertedMono() {
            return LetterAreaConvertedMono;
        }

        /**
         * Retrieves value of Total regardless of size
         *
         * @return TotalRegardlessOfSize
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getTotalRegardlessOfSize() {
            return TotalRegardlessOfSize;
        }

        /**
         * Retrieves SheetDuplex
         *
         * @return SheetDuplex
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getSheetDuplex() {
            return SheetDuplex;
        }

        /**
         * Retrieves MonoColorRegardlessOfSize
         *
         * @return MonoColorRegardlessOfSize
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getMonoColorRegardlessOfSize() {
            return MonoColorRegardlessOfSize;
        }

        /**
         * Retrieves MonoColor2RegardlessOfSize
         *
         * @return MonoColor2RegardlessOfSize
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getMonoColor2RegardlessOfSize() {
            return MonoColor2RegardlessOfSize;
        }

        /**
         * Retrieves FormatterColorPageCount
         *
         * @return FormatterColorPageCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFormatterColorPageCount() {
            return FormatterColorPageCount;
        }

        /**
         * Retrieves CopyPageCountMono
         *
         * @return CopyPageCountMono
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getCopyPageCountMono() {
            return CopyPageCountMono;
        }

        /**
         * Retrieves CopyPageCountColor
         *
         * @return CopyPageCountColor
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getCopyPageCountColor() {
            return CopyPageCountColor;
        }

        /**
         * Retrieves FaxPageCount
         *
         * @return FaxPageCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFaxPageCount() {
            return FaxPageCount;
        }

        /**
         * Retrieves FormatterPagesWithTonerSavings
         *
         * @return FormatterPagesWithTonerSavings
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFormatterPagesWithTonerSavings() {
            return FormatterPagesWithTonerSavings;
        }

        /**
         * Retrieves FormatterPagesAtOut
         *
         * @return FormatterPagesAtOut
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFormatterPagesAtOut() {
            return FormatterPagesAtOut;
        }

        /**
         * Retrieves EngineJobCount
         *
         * @return EngineJobCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getEngineJobCount() {
            return EngineJobCount;
        }

        /**
         * Retrieves EngineCyclesCountAtInstall
         *
         * @return EngineCyclesCountAtInstall
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getEngineCyclesCountAtInstall() {
            return EngineCyclesCountAtInstall;
        }

        /**
         * Retrieves CPRCalibrationsCount
         *
         * @return CPRCalibrationsCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getCPRCalibrationsCount() {
            return CPRCalibrationsCount;
        }

        /**
         * Retrieves DhalfCalibrationsCount
         *
         * @return DhalfCalibrationsCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDhalfCalibrationsCount() {
            return DhalfCalibrationsCount;
        }

        /**
         * Retrieves CleaningCyclesCount
         *
         * @return CleaningCyclesCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getCleaningCyclesCount() {
            return CleaningCyclesCount;
        }

        /**
         * Retrieves PowerCyclesCount
         *
         * @return PowerCyclesCount
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getPowerCyclesCount() {
            return PowerCyclesCount;
        }

        /**
         * Retrieves DeviceModelCode
         *
         * @return DeviceModelCode
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDeviceModelCode() {
            return DeviceModelCode;
        }

        /**
         * Retrieves EngineTonerRemaining
         *
         * @return EngineTonerRemaining
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getEngineTonerRemaining() {
            return EngineTonerRemaining;
        }

        /**
         * Retrieves FormatterDesignatedRemainingToner
         *
         * @return FormatterDesignatedRemainingToner
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFormatterDesignatedRemainingToner() {
            return FormatterDesignatedRemainingToner;
        }

        /**
         * Retrieves DrumLife
         *
         * @return DrumLife
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDrumLife() {
            return DrumLife;
        }

        /**
         * Retrieves DeveloperLife
         *
         * @return DeveloperLife
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDeveloperLife() {
            return DeveloperLife;
        }

        /**
         * Retrieves DeveloperWearLife
         *
         * @return DeveloperWearLife
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDeveloperWearLife() {
            return DeveloperWearLife;
        }

        /**
         * Retrieves WasteTonerContainerRemaining
         *
         * @return WasteTonerContainerRemaining
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0.
         */
        @Deprecated
        public int getWasteTonerContainerRemaining() {
            return WasteTonerContainerRemaining;
        }

        /**
         * Retrieves EngineTonerRemainingLevelAtVeryLow
         *
         * @return EngineTonerRemainingLevelAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getEngineTonerRemainingLevelAtVeryLow() {
            return EngineTonerRemainingLevelAtVeryLow;
        }

        /**
         * Retrieves FormatterDesignatedRemainingTonerAtVeryLow
         *
         * @return FormatterDesignatedRemainingTonerAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getFormatterDesignatedRemainingTonerAtVeryLow() {
            return FormatterDesignatedRemainingTonerAtVeryLow;
        }

        /**
         * Retrieves DrumLifeAtVeryLow
         *
         * @return DrumLifeAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDrumLifeAtVeryLow() {
            return DrumLifeAtVeryLow;
        }

        /**
         * Retrieves DeveloperLifeAtVeryLow
         *
         * @return DeveloperLifeAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDeveloperLifeAtVeryLow() {
            return DeveloperLifeAtVeryLow;
        }

        /**
         * Retrieves DeveloperWearLifeAtVeryLow
         *
         * @return DeveloperWearLifeAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getDeveloperWearLifeAtVeryLow() {
            return DeveloperWearLifeAtVeryLow;
        }

        /**
         * Retrieves CartridgeLastUsedDateAtVeryLow
         *
         * @return CartridgeLastUsedDateAtVeryLow
         * <ul>
         * <li>Return can be null if the value for CartridgeLastUsedDateAtVeryLow is null</li>
         * <li>Return can be empty if the value for CartridgeLastUsedDateAtVeryLow is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getCartridgeLastUsedDateAtVeryLow() {
            return CartridgeLastUsedDateAtVeryLow;
        }

        /**
         * Retrieves PageCountLetterAreaConvertedAtVeryLow
         *
         * @return PageCountLetterAreaConvertedAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getPageCountLetterAreaConvertedAtVeryLow() {
            return PageCountLetterAreaConvertedAtVeryLow;
        }

        /**
         * Retrieves PageCountMonoColor2RegardlessSizeAtVeryLow
         *
         * @return PageCountMonoColor2RegardlessSizeAtVeryLow
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return 0."
         */
        @Deprecated
        public int getPageCountMonoColor2RegardlessSizeAtVeryLow() {
            return PageCountMonoColor2RegardlessSizeAtVeryLow;
        }

        /**
         * Retrieves ConsumableManufacturingSignature
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
         * Retrieves ProductNumber
         *
         * @return ProductNumber
         * <ul>
         * <li>Return can be null if the value for ProductNumber is null</li>
         * <li>Return can be empty if the value for ProductNumber is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getProductNumber() {
            return ProductNumber;
        }

        /**
         * Retrieves ApproxPercentRemaining
         *
         * @return ApproxPercentRemaining
         * <ul>
         * <li>Return can be null if the value for ApproxPercentRemaining is null</li>
         * <li>Return can be empty if the value for ApproxPercentRemaining is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getApproxPercentRemaining() {
            return ApproxPercentRemaining;
        }

        /**
         * Retrieves {@link Supply.Capacity}
         *
         * @return Capacity
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public Supply.Capacity getCapacity() {
            return Capacity;
        }

        /**
         * Retrieves WebServiceAccessData
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
         * Retrieves ReplacementDate
         *
         * @return ReplacementDate
         * <ul>
         * <li>Return can be null if the value for ReplacementDate is null</li>
         * <li>Return can be empty if the value for ReplacementDate is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getReplacementDate() {
            return ReplacementDate;
        }

        /**
         * Retrieves RecordedDateTime
         *
         * @return RecordedDateTime
         * <ul>
         * <li>Return can be null if the value for RecordedDateTime is null</li>
         * <li>Return can be empty if the value for RecordedDateTime is empty</li>
         * </ul>
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public String getRecordedDateTime() {
            return RecordedDateTime;
        }

        /**
         * Retrieves {@link com.hp.workpath.api.supplies.supplyinfo.CycleCount}
         *
         * @return CycleCount
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public com.hp.workpath.api.supplies.supplyinfo.CycleCount getCycleCount() {
            return CycleCount;
        }

        /**
         * Retrieves {@link com.hp.workpath.api.supplies.supplyinfo.CycleLimit}
         *
         * @return CycleLimit
         * @since API 5
         * @deprecated API 8 "No longer supported. It will be return null."
         */
        @Deprecated
        public com.hp.workpath.api.supplies.supplyinfo.CycleLimit getCycleLimit() {
            return CycleLimit;
        }

        /**
         * Retrieves CycleCountV2
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
         * Retrieves CycleLimitV2
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
         * Retrieves InstalledWhileMarkerCartridgeProtectionWarningOverrideActive
         *
         * @return InstalledWhileMarkerCartridgeProtectionWarningOverrideActive
         * @since API 5
         * @deprecated API 8 "No longer supported."
         */
        @Deprecated
        public boolean isInstalledWhileMarkerCartridgeProtectionWarningOverrideActive() {
            return InstalledWhileMarkerCartridgeProtectionWarningOverrideActive;
        }
    }
}
