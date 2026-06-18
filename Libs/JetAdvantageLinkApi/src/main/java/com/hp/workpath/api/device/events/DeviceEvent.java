// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.events;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides detail of device event when the event occurs on a printer.
 *
 * @since API 5
 */
public class DeviceEvent {
    private String instanceId;
    private String severity;
    private String category;
    private String stateChangeType;
    private String title;
    private String eventCode;
    private Timestamp timestamp;
    private String[] details;
    private String supportInformationLink;

    /**
     * <p>Retrieve device event id.</p>
     *
     * @return <p>String device event id.
     * <ul>
     * <li>Return value shouldn't be empty or null since it is required to identify device event.</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * <p>Retrieve device event severity. Following are the device event severity messages
     * <ul>
     * <li>Error</li>
     * <li>CriticalError</li>
     * <li>Warning</li>
     * </ul>
     * </p>
     *
     * @return <p>String device event severity.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should be any of the following values like Error, CriticalError and Warning.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getSeverity() {
        return severity;
    }

    /**
     * <p>Retrieve device event category. Device event category are category of job doing in device this can be print, copy or scan job.</p>
     *
     * @return <p>String device event category.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should be any of the job category.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getCategory() {
        return category;
    }

    /**
     * <p>Retrieve device event changed type. Following are the device event changed type
     * <ul>
     * <li>Add</li>
     * <li>Remove</li>
     * </ul>
     * </p>
     *
     * @return <p>String device event changed type.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should be any of the device event changed type.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getStateChangeType() {
        return stateChangeType;
    }

    /**
     * <p>Retrieve device event title.</p>
     *
     * @return <p>String device event title.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should be any of the Error or warning name.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getTitle() {
        return title;
    }

    /**
     * <p>Retrieve device event code.</p>
     *
     * @return <p>String device event code.
     * <ul>
     * <li>Return value can be empty or null and if value is set empty or null, then getEventCode will return empty string.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getEventCode() {
        return eventCode;
    }

    /**
     * <p>Retrieve device event timestamp (time and offset).</p>
     *
     * @return <p>Timestamp device timestamp.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should be any combination of time and offset.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * <p>Retrieve device event details.</p>
     *
     * @return <p>Array device details.
     * <ul>
     * <li>Return value shouldn't be empty and it should contain any details of corresponding device events.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String[] getDetails() {
        return details;
    }

    /**
     * <p>Retrieve device event information link.</p>
     *
     * @return <p>String device information link.
     * <ul>
     * <li>Return value shouldn't be empty and it should contain link which provide current device information.</li>
     * </p>
     *
     * @since API 5
     */
    @DeviceApi
    public String getSupportInformationLink() {
        return supportInformationLink;
    }
}
