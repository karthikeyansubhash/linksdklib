// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.scan;

import android.support.annotation.Keep;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Scan information
 *
 * @since API 5
 */
@DeviceApi
public class ScanInfo {
    @Keep
    private int a4EquivalentAdfDeciSheets;
    @Keep
    private int a4EquivalentDuplexDeciSheets;
    @Keep
    private int a4EquivalentFlatbedlDeciSheets;
    @Keep
    private int a4EquivalentSimplexDeciSheets;
    @Keep
    private int a4EquivalentTotalDeciSheets;
    @Keep
    private int adfSheets;
    @Keep
    private int duplexSheets;
    @Keep
    private int flatbedSheets;
    @Keep
    private int simplexSheets;
    @Keep
    private int totalSheets;
    @Keep
    private ScannedSheetInfo scannedSheetInfo;


    /**
     * Total number of sheets scanned from the ADF in the job (normalized by weighting each sheet by its A4 equivalent media size multiplier), regardless of output location or plex mode.
     * A count of the number of 1/10th of a sheets. For example, a value of 7329 means 732.9 A4-equivalent sheets. This is used for .1 precision when measuring A4 equivalent sheets.
     *
     * @return a4EquivalentAdfDeciSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentAdfDeciSheets() {
        return a4EquivalentAdfDeciSheets;
    }

    /**
     * Total number of sheets scanned on both sides side in the job (normalized by weighting each sheet by its A4 equivalent media size multiplier), regardless of input location or output location.
     * A count of the number of 1/10th of a sheets. For example, a value of 7329 means 732.9 A4-equivalent sheets. This is used for .1 precision when measuring A4 equivalent sheets.
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
     * Total number of sheets scanned from the faltbed in the job (normalized by weighting each sheet by its A4 equivalent media size multiplier), regardless of output location or plex mode.
     *
     * @return a4EquivalentFlatbedlDeciSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getA4EquivalentFlatbedlDeciSheets() {
        return a4EquivalentFlatbedlDeciSheets;
    }

    /**
     * Total number of sheets scanned on only one side in the job (normalized by weighting each sheet by its A4 equivalent media size multiplier), regardless of input location or output location.
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
     * Total number of sheets scanned in the job (normalized by weighting each sheet by its A4 equivalent media size multiplier), regardless of plex mode, input location or output location.
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
     * Total number of sheets scanned from the ADF in the job, regardless of output location, plex mode, or size.
     *
     * @return adfSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getAdfSheets() {
        return adfSheets;
    }

    /**
     * Total number of sheets scanned on both sides side in the job, regardless of input location, output location, or size.
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
     * Total number of sheets scanned from the flatbed in the job, regardless of output location, plex mode, or size.
     *
     * @return flatbedSheets
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getFlatbedSheets() {
        return flatbedSheets;
    }

    /**
     * Total number of sheets scanned on only one side in the job, regardless of regardless of input location, output location, or size.
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
     * Total number of sheets scanned in the job, regardless of input location, output location, plex mode, or size.
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
     * Returns Scanned sheet information
     *
     * @return scannedSheetInfo
     * <p>
     * <ul>
     * <li>Return can be null if the ScannedSheetInfo is null</li>
     * <li>Return can be null if the ScannedSheetInfo is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public ScannedSheetInfo getScannedSheetInfo() {
        return scannedSheetInfo;
    }
}
