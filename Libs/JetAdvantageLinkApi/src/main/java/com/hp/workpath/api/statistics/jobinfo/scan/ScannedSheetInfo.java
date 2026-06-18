// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.scan;

import com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Scanned sheet information
 *
 * @since API 5
 */
@DeviceApi
public class ScannedSheetInfo {
    private int otherPrintedSheets;
    private ScannedSheetSets[] scannedSheetSets;

    public class ScannedSheetSets {
        private int count;
        private StatisticsAttributes.PaperSource mediaInputId;
        private StatisticsAttributes.ScanSize mediaSizeId;
        private StatisticsAttributes.Plex plex;

        /**
         * The number of sheets matching this sheet set.
         *
         * @return count
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getCount() {
            return count;
        }


        /**
         * The input location of all sheets in the set.
         *
         * @return mediaInputId
         * <p>
         * <ul>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.PaperSource} is null</li>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.PaperSource} is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.PaperSource getMediaInputId() {
            return mediaInputId;
        }

        /**
         * The size of all sheets in the set.
         *
         * @return mediaSizeId
         * <p>
         * <ul>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.ScanSize} is null</li>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.ScanSize} is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.ScanSize getMediaSizeId() {
            return mediaSizeId;
        }

        /**
         * Returns plex
         *
         * @return plex
         * <p>
         * <ul>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.Plex} is null</li>
         * <li>Return can be null if the {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.Plex} is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.Plex getPlex() {
            return plex;
        }
    }

    /**
     * Returns printed sheets for others
     *
     * @return otherPrintedSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getOtherPrintedSheets() {
        return otherPrintedSheets;
    }

    /**
     * Returns scanned sheets sets
     *
     * @return scannedSheetSets
     * <p>
     * <ul>
     * <li>if {@link ScannedSheetSets} field is not added to ScannedSheetSets array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public ScannedSheetSets[] getScannedSheetSets() {
        return scannedSheetSets;
    }
}
