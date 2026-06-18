// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Counter group information
 *
 * @since API 5
 */
@DeviceApi
public class CounterGroup {
    private int mVersion;
    private String MediaInputID;
    private String MediaOutputID;
    private String MediaSizeID;
    private String MediaTypeID;
    private String Finishings;
    private String PrintModeID;
    private String PrintQuality;
    private String AgentCount;
    private int TimePeriod;
    private String MarkerColor;

    private CounterGroup() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * Returns the value of media inputs
     *
     * @return MediaInputID
     * <ul>
     * <li>Return can be null if the value for MediaInputID is null</li>
     * <li>Return can be empty if the value for MediaInputID is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getMediaInputID() {
        return MediaInputID;
    }

    /**
     * Returns the value of media outputs
     *
     * @return MediaOutputID
     * <ul>
     * <li>Return can be null if the value for MediaOutputID is null</li>
     * <li>Return can be empty if the value for MediaOutputID is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getMediaOutputID() {
        return MediaOutputID;
    }

    /**
     * Returns the value of media size
     *
     * @return MediaSizeID
     * <ul>
     * <li>Return can be null if the value for MediaSizeID is null</li>
     * <li>Return can be empty if the value for MediaSizeID is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getMediaSizeID() {
        return MediaSizeID;
    }

    /**
     * Returns the value of media type
     *
     * @return  media type identifier
     * <ul>
     * <li>Return can be null if the value for MediaTypeID is null</li>
     * <li>Return can be empty if the value for MediaTypeID is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getMediaTypeID() {
        return MediaTypeID;
    }

    /**
     * Returns Finishings information
     *
     * @return Finishings
     * <ul>
     * <li>Return can be null if the value for Finishings is null</li>
     * <li>Return can be empty if the value for Finishings is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getFinishings() {
        return Finishings;
    }

    /**
     * Returns the value of the possible print modes
     *
     * @return print mode identifier
     * <ul>
     * <li>Return can be null if the value for PrintModeID is null</li>
     * <li>Return can be empty if the value for PrintModeID is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getPrintModeID() {
        return PrintModeID;
    }

    /**
     * Returns PrintQuality information
     *
     * @return print quality
     * <ul>
     * <li>Return can be null if the value for PrintQuality is null</li>
     * <li>Return can be empty if the value for PrintQuality is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getPrintQuality() {
        return PrintQuality;
    }

    /**
     * Returns total count of color original HP cartridges used in the device.
     *
     * @return agent count
     * <ul>
     * <li>Return can be null if the value for AgentCount is null</li>
     * <li>Return can be empty if the value for AgentCount is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getAgentCount() {
        return AgentCount;
    }

    /**
     * Returns TimePeriod
     *
     * @return time period
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * @since API 5
     */
    public int getTimePeriod() {
        return TimePeriod;
    }

    /**
     * Returns MarkerColor
     *
     * @return marker color
     * <ul>
     * <li>Return can be null if the value for MarkerColor is null</li>
     * <li>Return can be empty if the value for MarkerColor is empty</li>
     * </ul>
     *
     * @since API 5
     */
    public String getMarkerColor() {
        return MarkerColor;
    }
}
