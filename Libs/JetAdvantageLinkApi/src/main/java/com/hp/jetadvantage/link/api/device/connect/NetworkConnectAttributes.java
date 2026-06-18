// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.device.connect;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * @since API 1
 * @hide
 */
@Deprecated
public class NetworkConnectAttributes implements Parcelable {
    @MobileApi
    private final int mVersion;
    @MobileApi
    private final int mConnectTimeout;
    @MobileApi
    private final int mReadTimeout;

    @SuppressLint("RestrictedApi")
    private NetworkConnectAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mConnectTimeout = in.readInt();
        mReadTimeout = in.readInt();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeInt(mConnectTimeout);
        dest.writeInt(mReadTimeout);
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
    public static final Creator<NetworkConnectAttributes> CREATOR = new Creator<NetworkConnectAttributes>() {
        @Override
        public NetworkConnectAttributes createFromParcel(Parcel in) {
            return new NetworkConnectAttributes(in);
        }

        @Override
        public NetworkConnectAttributes[] newArray(int size) {
            return new NetworkConnectAttributes[size];
        }
    };
}
