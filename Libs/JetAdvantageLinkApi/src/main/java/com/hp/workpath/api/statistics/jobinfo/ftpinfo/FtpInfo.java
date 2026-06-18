// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.ftpinfo;

import com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Ftp information
 *
 * @since API 5
 */
@DeviceApi
public class FtpInfo {
    private StatisticsAttributes.DigitalSendInfo digitalSendInfo;
    private String directoryPath;
    private String hostName;
    private String ipAddress;
    private int port;
    private String userName;

    /**
     * Returns digitalSendInfo for FTP infomation
     *
     * @return digitalSendInfo
     * <p>
     * <ul>
     * <li>Return can be null if the digitalSendInfo is null</li>
     * <li>Return can be null if the digitalSendInfo is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.DigitalSendInfo getDigitalSendInfo() {
        return digitalSendInfo;
    }

    /**
     * Returns directoryPath for FTP infomation
     *
     * @return directoryPath
     * <p>
     * <ul>
     * <li>Return can be null if the directoryPath is null</li>
     * <li>Return can be null if the directoryPath is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getDirectoryPath() {
        return directoryPath;
    }

    /**
     * Returns hostName for FTP infomation
     *
     * @return hostName
     * <p>
     * <ul>
     * <li>Return can be null if the hostName is null</li>
     * <li>Return can be null if the hostName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Returns ipAddress for FTP infomation
     *
     * @return ipAddress
     * <p>
     * <ul>
     * <li>Return can be null if the ipAddress is null</li>
     * <li>Return can be null if the ipAddress is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Returns port for FTP infomation
     *
     * @return port
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns userName for FTP infomation
     *
     * @return userName
     * <p>
     * <ul>
     * <li>Return can be null if the userName is null</li>
     * <li>Return can be null if the userName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getUserName() {
        return userName;
    }
}
