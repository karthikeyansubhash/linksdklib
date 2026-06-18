// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.faxinfo;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides IpFaxOut information
 *
 * @since API 5
 */
@DeviceApi
public class IpFaxOutInfo {
    private FaxCall[] ipFaxCalls;

    /**
     * Returns FaxCall
     *
     * @since API 5
     */
    public class FaxCall {
        private FaxAttributes.Call faxCall;
        private FaxAttributes.FaxConfiguration ipFaxConfiguration;

        /**
         * Returns faxCall of IpFaxOutInfo
         *
         * @return faxCall
         * <p>
         * <ul>
         * <li>Return can be null if the faxCall is null</li>
         * <li>Return can be null if the faxCall is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public FaxAttributes.Call getFaxCall() {
            return faxCall;
        }

        /**
         * Returns ipFaxConfiguration of IpFaxOutInfo
         *
         * @return ipFaxConfiguration
         * <p>
         * <ul>
         * <li>Return can be null if the ipFaxConfiguration is null</li>
         * <li>Return can be null if the ipFaxConfiguration is empty</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public FaxAttributes.FaxConfiguration getIpFaxConfiguration() {
            return ipFaxConfiguration;
        }
    }

    /**
     * Returns ipFaxCalls for IpFaxOutInfo
     *
     * @return ipFaxCalls
     * <p>
     * <ul>
     * <li>if ipFaxCalls field is not added to FaxCall array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public FaxCall[] getIpFaxCalls() {
        return ipFaxCalls;
    }
}
