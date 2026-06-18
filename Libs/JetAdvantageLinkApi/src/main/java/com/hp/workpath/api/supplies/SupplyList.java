// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies;

import com.hp.workpath.api.supplies.supplyinfo.Supply;
import com.hp.workpath.common.annotation.DeviceApi;

import java.util.List;

/**
 * Class to manage Supply List
 *
 * @since API 5
 */
@DeviceApi
public final class SupplyList {
    final List<Supply> mSupply;

    /**
     *  Instantiates SupplyList with attributes provided
     *
     * @param supplies List of {@link Supply} information.
     *
     * @since API 5
     */
    public SupplyList(final List<Supply> supplies) {
        mSupply = supplies;
    }

    /**
     * Returns the supply information
     *
     * @return supply
     * @since API 5
     */
    public List<Supply> getSupply() {
        return mSupply;
    }
}
