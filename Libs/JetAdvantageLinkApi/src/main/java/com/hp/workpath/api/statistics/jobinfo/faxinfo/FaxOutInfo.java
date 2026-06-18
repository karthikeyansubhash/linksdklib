// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.faxinfo;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides FaxOut information
 *
 * @since API 5
 */
@DeviceApi
public class FaxOutInfo {

    private FaxAttributes.Call[] faxCalls;

    /**
     * Returns faxCalls for FaxOutInfo
     *
     * @return faxCalls
     * <p>
     * <ul>
     * <li>if {@link FaxAttributes.Call} field is not added to Call array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public FaxAttributes.Call[] getFaxCalls() {
        return faxCalls;
    }
}
