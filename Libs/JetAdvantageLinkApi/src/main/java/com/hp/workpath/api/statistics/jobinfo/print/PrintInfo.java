// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.print;

import android.support.annotation.Keep;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Print job information
 *
 * @since API 5
 */
@DeviceApi
public class PrintInfo {
    @Keep
    private int a4EquivalentBlankDeciSides;
    @Keep
    private int a4EquivalentColorDeciImpressions;
    @Keep
    private int a4EquivalentDuplexDeciSheets;
    @Keep
    private int a4EquivalentMonoChromeDeciImpressions;
    @Keep
    private int a4EquivalentSimplexDeciSheets;
    @Keep
    private int a4EquivalentTotalDeciImpressions;
    @Keep
    private int a4EquivalentTotalDeciSheets;
    @Keep
    private int blankSides;
    @Keep
    private int colorImpressions;
    @Keep
    private int duplexSheets;
    @Keep
    private int monochromeImpressions;
    @Keep
    private int simplexSheets;
    @Keep
    private int totalImpressions;
    @Keep
    private int totalSheets;
    @Keep
    private PrintAgentInfo[] agents;
    @Keep
    private PrintSettings printSettings;
    @Keep
    private PrintedSheetInfo printedSheetInfo;

    /**
     * The total of all impressions in the job where all marking agent units used is 0, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentBlankDeciSides
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentBlankDeciSides() {
        return a4EquivalentBlankDeciSides;
    }


    /**
     * The total of all impressions in the job where at least one color marking agent units used is > 0, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentColorDeciImpressions
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentColorDeciImpressions() {
        return a4EquivalentColorDeciImpressions;
    }


    /**
     * The total of all sheets in the job where plex result is Duplex, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentDuplexDeciSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentDuplexDeciSheets() {
        return a4EquivalentDuplexDeciSheets;
    }


    /**
     * The total of all impressions in the job where only the black marking agent units used is > 0, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentMonoChromeDeciImpressions
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentMonoChromeDeciImpressions() {
        return a4EquivalentMonoChromeDeciImpressions;
    }

    /**
     * The total of all sheets in the job where plex result is Simplex, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentSimplexDeciSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentSimplexDeciSheets() {
        return a4EquivalentSimplexDeciSheets;
    }

    /**
     * Retrieves a4 Equivalent Total Deci Impressions
     *
     * @return <p>a4EquivalentTotalDeciImpressions
     * <ul>
     * <li>The total of all impressions in the job where at least one marking agent units used is > 0</li>
     * <li>If device has never printed before, return value will be 0</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    public int getA4EquivalentTotalDeciImpressions() {
        return a4EquivalentTotalDeciImpressions;
    }

    /**
     * The total of all sheets used in the job, normalized by weighting each impression by its A4 equivalent media size multiplier.
     *
     * @return a4EquivalentTotalDeciSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentTotalDeciSheets() {
        return a4EquivalentTotalDeciSheets;
    }

    /**
     * The total of all potentially impressed sides in the job, regardless of size. A Blank side is a potential impression requiring zero colorants (i.e., no marks are impressed on the media sheet side by the print device).
     *
     * @return blankSides
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getBlankSides() {
        return blankSides;
    }

    /**
     * The total of all impressions in the job where at least one color marking agent units used is > 0, regardless of size.
     *
     * @return colorImpressions
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getColorImpressions() {
        return colorImpressions;
    }

    /**
     * The total of all sheets in the job where plex result is Duplex, regardless of size.
     *
     * @return duplexSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getDuplexSheets() {
        return duplexSheets;
    }

    /**
     * The total of all impressions in the job where only the black marking agent units used is > 0, regardless of size.
     *
     * @return monochromeImpressions
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getMonochromeImpressions() {
        return monochromeImpressions;
    }

    /**
     * The total of all sheets in the job where plex result is Simplex, regardless of size.
     *
     * @return simplexSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getSimplexSheets() {
        return simplexSheets;
    }

    /**
     * The total number of impressions used in the job where at least one color marking agent units used is > 0
     *
     * @return totalImpressions
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getTotalImpressions() {
        return totalImpressions;
    }

    /**
     * The total of all sheets in the job regardless of plex result or size. A sheet is a physical piece of paper.
     *
     * @return totalSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getTotalSheets() {
        return totalSheets;
    }

    /**
     * Returns list of agents
     *
     * @return agent list
     * <p>
     * <ul>
     * <li>if {@link com.hp.workpath.api.statistics.jobinfo.print.PrintAgentInfo} field not added to PrintAgentInfo array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public PrintAgentInfo[] getAgents() {
        return agents;
    }

    /**
     * Returns print settings
     *
     * @return printSettings
     * <p>
     * <ul>
     * <li>Return can be null if the PrintSettings is null</li>
     * <li>Return can be null if the PrintSettings is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public PrintSettings getPrintSettings() {
        return printSettings;
    }

    /**
     * Returns printed sheet information
     *
     * @return printedSheetInfo
     * <p>
     * <ul>
     * <li>Return can be null if the PrintedSheetInfo is null</li>
     * <li>Return can be null if the PrintedSheetInfo is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public PrintedSheetInfo getPrintedSheetInfo() {
        return printedSheetInfo;
    }
}
