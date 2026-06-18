// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.device.DeviceInfo;
import com.hp.workpath.api.device.discovery.DiscoveryService;
import com.hp.workpath.api.device.discovery.DiscoveryType;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.workpath.common.utils.CommonUtility;

/**
 * <p>The sets of attributes for establishing connection with selected device.
 * An instance of this class is created using {@link Builder}</p>
 *
 * @since API 1
 */
public class ConnectDeviceAttributes implements Parcelable {
    @MobileApi
    final int mVersion;
    @MobileApi
    final Uri mUri;
    @MobileApi
    final DeviceInfo mDeviceInfo;
    @MobileApi
    final NetworkConnectAttributes mNetworkConnectAttributes;

    private ConnectDeviceAttributes(Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mUri = builder.mUri;
        mDeviceInfo = builder.mDeviceInfo;
        mNetworkConnectAttributes = builder.mNetworkConnectAttributes;
    }

    /**
     * <p>Returns the uri from connected device.</p>
     * @return Uri Uri from connected device
     * @hide not open API
     */
    @SuppressWarnings({"unused"})
    public Uri getUri() {
        return mUri;
    }

    /**
     * @return DeviceInfo of device for connect to
     * @hide not open API
     */
    @SuppressWarnings({"unused"})
    public DeviceInfo getDeviceInfo() {
        return mDeviceInfo;
    }

    /**
     * @return network parameters for establishing connection
     * @hide not open API
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public NetworkConnectAttributes getNetworkConnectAttributes() {
        return mNetworkConnectAttributes;
    }

    /**
     * @return internal version
     * @hide not open API
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * <p>Builder for creating an instance of {@link ConnectDeviceAttributes}.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static class Builder {
        Uri mUri = null;
        DeviceInfo mDeviceInfo = null;
        NetworkConnectAttributes mNetworkConnectAttributes = null;

        /**
         * <p>Constructor to create a new Builder for establishing connection with provided Uri.<br>
         * Provided Uri must be a valid HTTP(S).</p>
         *
         * @param uri Valid uri for creating a new Builder
         * @exception NullPointerException if uri is null
         * @exception IllegalArgumentException if uri is not a valid HTTP(S) URL
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public Builder(final Uri uri) {
            Preconditions.checkNotNull(uri);
            if (!CommonUtility.isValidURL(uri)) {
                throw new IllegalArgumentException("Supplied Uri is not valid");
            }

            mUri = uri;
        }

        /**
         * <p>Constructor to create a new Builder for establishing connection with {@link DeviceInfo}.
         * DeviceInfo is returned while discoverying through {@link DiscoveryService.AbstractDiscoveryObserver#onDiscover(DeviceInfo)}</p>
         *
         * @param deviceInfo Valid deviceInfo for creating a new Builder
         * @exception NullPointerException if deviceInfo is null, or required parameter such as Uri and discoveryType is null
         * @exception IllegalArgumentException if deviceInfo.getUri() is not valid
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public Builder(final DeviceInfo deviceInfo) {
            Preconditions.checkNotNull(deviceInfo);
            Preconditions.checkNotNull(deviceInfo.getUri());
            Preconditions.checkNotNull(deviceInfo.getDiscoveryType());

            if (deviceInfo.getDiscoveryType() == DiscoveryType.LOCAL && !CommonUtility.isValidURL(deviceInfo.getUri()) ||
                deviceInfo.getDiscoveryType() == DiscoveryType.WIFI_DIRECT && !ConnectDevicelet.MAC_ADDRESS.matcher(deviceInfo.getUri().getAuthority()).matches()) {
                throw new IllegalArgumentException("Supplied deviceInfo.getUri() is not valid");
            }

            mDeviceInfo = Preconditions.checkNotNull(deviceInfo);
        }

        /**
         * <p>Sets network parameters for establishing connection</p>
         *
         * @param networkConnectAttributes network parameters for establishing connection in {@link NetworkConnectAttributes}
         * @return this builder for method chaining
         * @exception NullPointerException if networkConnectAttributes is null.
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public Builder setNetworkConnectAttributes(final NetworkConnectAttributes networkConnectAttributes) {
            mNetworkConnectAttributes = Preconditions.checkNotNull(networkConnectAttributes);
            return this;
        }

        /**
         * <p>Builds ConnectDeviceAttributes. All of the attributes combine into a {@link ConnectDeviceAttributes} object.</p>
         *
         * @return ConnectDeviceAttributes object containing all of the attributes.
         * @since API 1
         */
        public ConnectDeviceAttributes build() {
            return new ConnectDeviceAttributes(this);
        }
    }

    @SuppressLint("RestrictedApi")
    private ConnectDeviceAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mUri = in.readParcelable(Uri.class.getClassLoader());
        mDeviceInfo = in.readParcelable(DeviceInfo.class.getClassLoader());
        mNetworkConnectAttributes = in.readParcelable(NetworkConnectAttributes.class.getClassLoader());
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
