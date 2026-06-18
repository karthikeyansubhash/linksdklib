// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.faxinfo;

import android.support.annotation.Keep;

import com.hp.workpath.common.utils.json.SerializedName;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides FaxAttributes information
 *
 * @since API 5
 */
@DeviceApi
public class FaxAttributes {

    /**
     * Enums of Result
     *
     * @since API 5
     */
    @Keep
    public enum Result {
        /**
         * Fax was completed successfully.
         *
         * @since API 5
         */
        @SerializedName("Successful")
        SUCCESSFUL,

        /**
         * Fax was partially completed.
         *
         * @since API 5
         */
        @SerializedName("Partial")
        PARTIAL,

        /**
         * Fax was Busy.
         *
         * @since API 5
         */
        @SerializedName("Busy")
        BUSY,

        /**
         * Fax failed prior to completion.
         *
         * @since API 5
         */
        @SerializedName("Failed")
        FAILED,

        /**
         * Fax was canceled.
         *
         * @since API 5
         */
        @SerializedName("Canceled")
        CANCELED
    }

    /**
     * Enums of IpTransport
     *
     * @since API 5
     */
    @Keep
    public enum IpTransport {
        /**
         * TCP
         *
         * @since API 5
         */
        @SerializedName("TCP")
        TCP,

        /**
         * UDP
         *
         * @since API 5
         */
        @SerializedName("UDP")
        UDP,

        /**
         * TLS
         *
         * @since API 5
         */
        @SerializedName("TLS")
        TLS
    }

    /**
     * Enums of T38Transport
     *
     * @since API 5
     */
    @Keep
    public enum T38Transport {
        /**
         * t38tTCP
         *
         * @since API 5
         */
        @SerializedName("t38tTCP")
        t38tTCP,

        /**
         * t38tUDP
         *
         * @since API 5
         */
        @SerializedName("t38tUDP ")
        t38tUDP
    }

    /**
     * Returns Call for FaxAttributes
     *
     * @since API 5
     */
    public class Call {
        private String billingCode;
        private int duration;
        private String faxNumber;
        private FaxAttributes.Result faxResult;

        /**
         * Returns billingCode for Call
         *
         * @return billingCode
         * <p>
         * <ul>
         * <li>Return can be null if the billingCode is null</li>
         * <li>Return can be null if the billingCode is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getBillingCode() {
            return billingCode;
        }

        /**
         * Returns billingCode for Call
         *
         * @return duration
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Returns faxNumber for Call
         *
         * @return faxNumber
         * <p>
         * <ul>
         * <li>Return can be null if the faxNumber is null</li>
         * <li>Return can be null if the faxNumber is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getFaxNumber() {
            return faxNumber;
        }

        /**
         * Returns faxResult for call
         *
         * @return faxResult
         * <p>
         * <ul>
         * <li>Return can be null if the faxResult is null</li>
         * <li>Return can be null if the faxResult is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public FaxAttributes.Result getFaxResult() {
            return faxResult;
        }
    }

    /**
     * Returns DigitalFaxCall for Faxinfo
     *
     * @since API 5
     */
    public class DigitalFaxCall {
        private String billingCode;
        private String faxNumber;

        /**
         * Returns billingCode for DigitalFaxCall
         *
         * @return billingCode
         * <p>
         * <ul>
         * <li>Return can be null if the billingCode is null</li>
         * <li>Return can be null if the billingCode is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getBillingCode() {
            return billingCode;
        }

        /**
         * Returns faxNumber for DigitalFaxCall
         *
         * @return faxNumber
         * <p>
         * <ul>
         * <li>Return can be null if the faxNumber is null</li>
         * <li>Return can be null if the faxNumber is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getFaxNumber() {
            return faxNumber;
        }
    }

    /**
     * Returns FaxConfiguration for Faxinfo
     *
     * @since API 5
     */
    public class FaxConfiguration {
        private IpServer sipServer;
        private IpTransport sipTransport;
        private T38Transport faxNumber;

        /**
         * Returns sipServer for FaxConfiguration
         *
         * @return sipServer
         * <p>
         * <ul>
         * <li>Return can be null if the sipServer is null</li>
         * <li>Return can be null if the sipServer is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public IpServer getSipServer() {
            return sipServer;
        }

        /**
         * Returns sipTransport for FaxConfiguration
         *
         * @return sipTransport
         * <p>
         * <ul>
         * <li>Return can be null if the sipTransport is null</li>
         * <li>Return can be null if the sipTransport is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public IpTransport getSipTransport() {
            return sipTransport;
        }

        /**
         * Returns faxNumber for FaxConfiguration
         *
         * @return faxNumber
         * <p>
         * <ul>
         * <li>Return can be null if the faxNumber is null</li>
         * <li>Return can be null if the faxNumber is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public T38Transport getFaxNumber() {
            return faxNumber;
        }
    }

    /**
     * Returns IpServer for Faxinfo
     *
     * @since API 5
     */
    public class IpServer {
        private int portNumber;
        private int proxyPortNumber;
        private String proxyServer;
        private String server;

        /**
         * Returns portNumber for IpServer
         *
         * @return portNumber
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getPortNumber() {
            return portNumber;
        }

        /**
         * Returns proxyPortNumber for IpServer
         * @return proxyPortNumber
         * <p>
         * <ul>
         * <li>Ensures that the return numeric value is non-negative.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getProxyPortNumber() {
            return proxyPortNumber;
        }

        /**
         * Returns proxyServer for IpServer
         *
         * @return proxyServer
         * <p>
         * <ul>
         * <li>Return can be null if the proxyServer is null</li>
         * <li>Return can be null if the proxyServer is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getProxyServer() {
            return proxyServer;
        }

        /**
         * Returns server for IpServer
         *
         * @return server
         * <p>
         * <ul>
         * <li>Return can be null if the server is null</li>
         * <li>Return can be null if the server is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getServer() {
            return server;
        }
    }
}
