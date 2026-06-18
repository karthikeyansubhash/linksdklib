// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.deviceusage;

import com.hp.workpath.api.deviceusage.printer.PrinterInfo;
import com.hp.workpath.api.deviceusage.scanner.ScannerInfo;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Class for accessing usage information from a printer.
 *
 * @since API 5
 */
@DeviceApi
public class DeviceUsageInfo {
    private int mVersion;

    private PrinterInfo printer;
    private ScannerInfo scanner;

    private DeviceUsageInfo() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * <p> Retrieves {@link PrinterInfo} object containing relevant job information about the printer</p>
     *
     * @return <p>{@link PrinterInfo} object containing printer job information
     * <ul>
     * <li>Return can't be empty and should be object of the type {@link PrinterInfo}</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    public PrinterInfo getPrinter() {
        return printer;
    }

    /**
     * <p> Retrieves {@link ScannerInfo} object containing relevant scanner information</p>
     *
     * @return <p>{@link ScannerInfo} object containing scanner jobinformation
     * <ul>
     * <li>Return can't be empty and should be object of the type {@link ScannerInfo}</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    public ScannerInfo getScanner() {
        return scanner;
    }
}
