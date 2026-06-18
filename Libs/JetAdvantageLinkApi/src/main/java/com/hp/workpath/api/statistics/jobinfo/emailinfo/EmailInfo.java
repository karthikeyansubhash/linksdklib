// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.emailinfo;

import android.support.annotation.Keep;

import com.hp.workpath.common.utils.json.SerializedName;
import com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes;
import com.hp.workpath.api.statistics.jobinfo.userinfo.ExtendedUserInfo;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Email information
 *
 * @since API 5
 */
@DeviceApi
public class EmailInfo {

    private String[] bccAddresses;
    private String[] ccAddresses;
    private DigitalFaxCall[] digitalFaxCalls;
    private StatisticsAttributes.DigitalSendInfo digitalSendInfo;
    private String emailSubject;
    private String[] failedRecipientsList;
    private String fromAddress;
    private String hostName;
    private String ipAddress;
    private int port;
    private String[] toAddresses;

    /**
     * Returns DigitalFaxCall
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
     * Enums of Result
     *
     * @since API 5
     */
    @Keep
    public enum Result {
        /**
         * EmailInfo was completed successfully.
         *
         * @since API 5
         */
        @SerializedName("Successful")
        SUCCESSFUL,

        /**
         * EmailInfo was partially completed.
         *
         * @since API 5
         */
        @SerializedName("Partial")
        PARTIAL,

        /**
         * EmailInfo failed prior to completion.
         *
         * @since API 5
         */
        @SerializedName("Failed")
        FAILED,

        /**
         * EmailInfo was canceled.
         *
         * @since API 5
         */
        @SerializedName("Canceled")
        CANCELED
    }

    /**
     * Returns bccAddresses for Email
     *
     * @return bccAddresses
     * <p>
     * <ul>
     * <li>if bcc address field is not added to String array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String[] getBccAddresses() {
        return bccAddresses;
    }

    /**
     * Returns ccAddresses for Email
     *
     * @return ccAddresses
     * <p>
     * <ul>
     * <li>if cc address field is not added to String array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String[] getCcAddresses() {
        return ccAddresses;
    }

    /**
     * Returns digitalFaxCalls for Email
     *
     * @return digitalFaxCalls
     * <p>
     * <ul>
     * <li>if DigitalFaxCall field is not added to DigitalFaxCall array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public DigitalFaxCall[] getDigitalFaxCalls() {
        return digitalFaxCalls;
    }

    /**
     * Returns digitalSendInfo for Email
     *
     * @return digitalSendInfo
     *
     * @since API 5
     */
    public StatisticsAttributes.DigitalSendInfo getDigitalSendInfo() {
        return digitalSendInfo;
    }

    /**
     * Returns emailSubject for Email
     *
     * @return The email subject
     * <p>
     * <ul>
     * <li>Return can be null if the emailSubject is null</li>
     * <li>Return can be null if the emailSubject is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * The list of email addresses that the smtp server failed to deliver to. This list will exists only if digitalSendInfo.result is 'Partial'
     *
     * @return failedRecipientsList
     * <p>
     * <ul>
     * <li>if failedRecipient field is not added to String array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String[] getFailedRecipientsList() {
        return failedRecipientsList;
    }

    /**
     * Returns fromAddress for Email
     *
     * @return fromAddress
     * <p>
     * <ul>
     * <li>Return can be null if the fromAddress is null</li>
     * <li>Return can be null if the fromAddress is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * The email server's hostname for Email
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
     * An IP address in the form of a string. May contain an IPv4 or an IPv6 address, but is never a host name.
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
     * Returns email server port for Email
     *
     * @return email server port
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
     * Returns The list of recipient (To) addresses for Email
     *
     * @return The list of recipient (To) addresses
     * <p>
     * <ul>
     * <li>if toAddress field is not added to String array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String[] getToAddresses() {
        return toAddresses;
    }
}
