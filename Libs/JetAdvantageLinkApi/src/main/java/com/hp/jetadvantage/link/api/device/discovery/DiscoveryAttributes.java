// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.device.discovery;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @hide
 * @since API 1
 */
@Deprecated
public class DiscoveryAttributes implements Parcelable {
    @MobileApi
    private final int mVersion;
    @MobileApi
    private final List<DiscoveryType> mDiscoveryTypes;
    @MobileApi
    private final int mTimeout;

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
