// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.CapabilitiesExceededException;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>The sets of attributes for starting discovery of nearby devices.
 * An instance of this class is created using {@link Builder}</p>
 *
 * @since API 1
 */
public class DiscoveryAttributes implements Parcelable {
    @MobileApi
    private final int mVersion;
    @MobileApi
    private final List<DiscoveryType> mDiscoveryTypes;
    @MobileApi
    private final int mTimeout;

    private DiscoveryAttributes(Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mDiscoveryTypes = builder.mDiscoveryTypes;
        mTimeout = builder.mTimeout;
    }

    /**
     * @return list of discovery types
     * @hide not open API
     */
    public List<DiscoveryType> getDiscoveryTypes() {
        return mDiscoveryTypes;
    }

    /**
     * @return timeout when discovery will finish
     * @hide not open API
     */
    public int getTimeout() {
        return mTimeout;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * <p>Builder for creating an instance of {@link DiscoveryAttributes}.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static class Builder {
        private List<DiscoveryType> mDiscoveryTypes = Arrays.asList(DiscoveryType.values());
        private int mTimeout = 60;

        /**
         * <p>Default constructor to create a new Builder.<br/>
         * Default values will be configured : <br/>
         * Discovery types = [LOCAL, WIFI_DIRECT] <br/>
         * Timeout = 60 seconds</p>
         *
         * @since API 1
         */
        public Builder() {
        }

        /**
         * <p>Sets discovery methods for searching devices.</p>
         *
         * @param discoveryType discovery methods for searching devices
         * @return this builder for method chaining
         * @exception NullPointerException if discoveryType is null.
         *
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public Builder setDiscoveryType(final DiscoveryType... discoveryType) {
            mDiscoveryTypes = Arrays.asList(Preconditions.checkNotNull(discoveryType));
            return this;
        }

        /**
         * <p>Sets timeout for discovery. After timeout, discovery will be automatically stopped.<br>
         * Default value is 60 seconds.
         * Range: 10 ~ 120 seconds.</p>
         *
         * @param timeout value in seconds
         * @return this builder for method chaining
         *
         * @since API 1
         */
        public Builder setTimeout(final int timeout) {
            mTimeout = timeout;
            return this;
        }

        /**
         * <p>Builds ConnectDeviceAttributes. All of the attributes combine into a {@link DiscoveryAttributes} object.</p>
         *
         * @return DiscoveryAttributes object containing all of the attributes.
         * @exception CapabilitiesExceededException if attributes are out of supported range.
         *
         * @since API 1
         */
        public DiscoveryAttributes build() throws CapabilitiesExceededException {
            if (mTimeout < Discoverylet.MINIMUM_TIMEOUT || mTimeout > Discoverylet.MAXIMUM_TIMEOUT) {
                throw new CapabilitiesExceededException("Timeout value must be in range " + Discoverylet.MINIMUM_TIMEOUT + "-" + Discoverylet.MAXIMUM_TIMEOUT + " seconds");
            }
            return new DiscoveryAttributes(this);
        }
    }

    @SuppressLint("RestrictedApi")
    private DiscoveryAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        ArrayList<DiscoveryType> discoveryTypes = new ArrayList<>();
        for (int length = in.readInt(); length > 0; length--) {
            discoveryTypes.add(DiscoveryType.values()[in.readInt()]);
        }
        mDiscoveryTypes = Collections.unmodifiableList(discoveryTypes);

        mTimeout = in.readInt();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeInt(mDiscoveryTypes.size());
        for (Enum enumValue : mDiscoveryTypes) {
            dest.writeInt(enumValue.ordinal());
        }
        dest.writeInt(mTimeout);
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<DiscoveryAttributes> CREATOR = new Creator<DiscoveryAttributes>() {
        @Override
        public DiscoveryAttributes createFromParcel(Parcel in) {
            return new DiscoveryAttributes(in);
        }

        @Override
        public DiscoveryAttributes[] newArray(int size) {
            return new DiscoveryAttributes[size];
        }
    };

    /**
     * Expresses the {@link DiscoveryAttributes} in a string.
     *
     * @hide trivial
     */
    @Override
    public String toString() {
        return new StringBuilder().append("[").append("DiscoveryTypes=").append((mDiscoveryTypes != null)?mDiscoveryTypes.size():"0").append("]").toString();
    }

}
