// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.device.connect;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.device.DeviceInfo;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * @since API 1
 * @hide
 */
@Deprecated
public class ConnectDeviceAttributes implements Parcelable {
    @MobileApi
    final int mVersion;
    @MobileApi
    final Uri mUri;
    @MobileApi
    final DeviceInfo mDeviceInfo;
    @MobileApi
    final NetworkConnectAttributes mNetworkConnectAttributes;

    /**
     * @return internal version
     * @hide not open API
     */
    @SuppressWarnings({"unused"})
    @Deprecated
    public int getVersion() {
        return mVersion;
    }

    @SuppressLint("RestrictedApi")
    private ConnectDeviceAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mUri = in.readParcelable(Uri.class.getClassLoader());
        mDeviceInfo = in.readParcelable(DeviceInfo.class.getClassLoader());

        Parcel parcelForNetworkAttr = Parcel.obtain();
        com.hp.workpath.api.device.connect.NetworkConnectAttributes networkConnectAttributes = in.readParcelable(com.hp.workpath.api.device.connect.NetworkConnectAttributes.class.getClassLoader());
        if(networkConnectAttributes != null) {
            networkConnectAttributes.writeToParcel(parcelForNetworkAttr, 0);
            parcelForNetworkAttr.setDataPosition(0);
            mNetworkConnectAttributes = NetworkConnectAttributes.CREATOR.createFromParcel(parcelForNetworkAttr);
        } else {
            mNetworkConnectAttributes = null;
        }
    }

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeParcelable(mUri, 0);
        dest.writeParcelable(mDeviceInfo, 0);
        dest.writeParcelable(mNetworkConnectAttributes, 0);
    }

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<ConnectDeviceAttributes> CREATOR = new Creator<ConnectDeviceAttributes>() {
        @Override
        public ConnectDeviceAttributes createFromParcel(Parcel in) {
            return new ConnectDeviceAttributes(in);
        }

        @Override
        public ConnectDeviceAttributes[] newArray(int size) {
            return new ConnectDeviceAttributes[size];
        }
    };

    /**
     * Expresses the {@link ConnectDeviceAttributes} in a string.
     *
     * @hide trivial
     */
    @Override
    @MobileApi
    public String toString() {
        return new StringBuilder().append("[").append("ConnectDeviceAttributes").append("]").toString();
    }

}
