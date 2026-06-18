// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.print;

import com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides PrintedSheet information
 *
 * @since API 5
 */
@DeviceApi
public class PrintedSheetInfo {
    private String description;
    /**
     * Initialized to zero at the start of the job, this is an 'overflow' counter for all scanned sheets that do not match any of the existing 15 scanned sheet sets.
     */
    private int otherPrintedSheets;
    private PrintedSheetSets[] printedSheetSets;

    /**
     * A collection of sheet sets.
     *
     * @since API 5
     */
    public class PrintedSheetSets {
        private StatisticsAttributes.ImpressionClassification backImpressionClassification;
        private int count;
        private StatisticsAttributes.ImpressionClassification frontImpressionClassification;
        private StatisticsAttributes.OutputBin logicalMediaOutputId;
        private StatisticsAttributes.PaperSource mediaInputId;
        private StatisticsAttributes.ScanSize mediaSizeId;
        private StatisticsAttributes.OutputMediaType mediaTypeId;
        private StatisticsAttributes.OutputBin physicalMediaOutputId;
        private StatisticsAttributes.Plex plex;

        /**
         * The back impression classification of all sheets in the set.
         *
         * @return backImpressionClassification
         * <p>
         * <ul>
         * <li>Return can be null if the backImpressionClassification is null</li>
         * <li>Return can be null if the backImpressionClassification is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.ImpressionClassification getBackImpressionClassification() {
            return backImpressionClassification;
        }

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
         * The front impression classification of all sheets in the set.
         *
         * @return frontImpressionClassification
         * <p>
         * <ul>
         * <li>Return can be null if the frontImpressionClassification is null</li>
         * <li>Return can be null if the frontImpressionClassification is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.ImpressionClassification getFrontImpressionClassification() {
            return frontImpressionClassification;
        }

        /**
         * The input location of all sheets in the set.
         *
         * @return mediaInputId
         * <p>
         * <ul>
         * <li>Return can be null if the mediaInputId is null</li>
         * <li>Return can be null if the mediaInputId is empty</li>
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
         * <li>Return can be null if the mediaSizeId is null</li>
         * <li>Return can be null if the mediaSizeId is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.ScanSize getMediaSizeId() {
            return mediaSizeId;
        }

        /**
         * The media type of all sheets in the set.
         *
         * @return mediaTypeId
         * <p>
         * <ul>
         * <li>Return can be null if the mediaTypeId is null</li>
         * <li>Return can be null if the mediaTypeId is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.OutputMediaType getMediaTypeId() {
            return mediaTypeId;
        }

        /**
         * Returns logical media output id
         *
         * @return logicalMediaOutputId
         * <p>
         * <ul>
         * <li>Return can be null if the logicalMediaOutputId is null</li>
         * <li>Return can be null if the logicalMediaOutputId is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.OutputBin getLogicalMediaOutputId() {
            return logicalMediaOutputId;
        }

        /**
         * Returns physical media output id
         *
         * @return physicalMediaOutputId
         * <p>
         * <ul>
         * <li>Return can be null if the physicalMediaOutputId is null</li>
         * <li>Return can be null if the physicalMediaOutputId is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.OutputBin getPhysicalMediaOutputId() {
            return physicalMediaOutputId;
        }

        /**
         * Returns plex
         *
         * @return plex
         * <p>
         * <ul>
         * <li>Return can be null if the plex is null</li>
         * <li>Return can be null if the plex is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public StatisticsAttributes.Plex getPlex() {
            return plex;
        }
    }

    /**
     * Returns description
     *
     * @return description
     * <p>
     * <ul>
     * <li>Return can be null if the description is null</li>
     * <li>Return can be null if the description is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getDescription() {
        return description;
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
     * Returns printed sheet sets
     *
     * @return printedSheetSets
     * <p>
     * <ul>
     * <li>if {@link PrintedSheetSets} field is not added to PrintedSheetSets array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public PrintedSheetSets[] getPrintedSheetSets() {
        return printedSheetSets;
    }
}
