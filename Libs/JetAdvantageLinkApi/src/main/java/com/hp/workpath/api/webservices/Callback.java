// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides interface for callback
 * @since API 6
 */
@DeviceApi
public interface Callback {
    /**
     * Provides interface for http callback
     * @since API 6
     */
    interface HttpCallback {
        /**
         * Requested to get http callback data
         * @param request String request
         * @param requestBody String request body
         *  <p>
         *  <ul>
         *  <li>There is no minimum & maximum length</li>
         *  <li>Empty string is allowed</li>
         *  <li>NULL string is allowed </li>
         *  </ul>
         *  </p>
         * @return Returns String callback result
         *
         */
        String get(String request, String requestBody);
    }
} 