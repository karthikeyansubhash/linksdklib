// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.CapabilitiesExceededException;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>The sets of attributes of network parameters for making a connection to the specified device.
 * An instance of this class is created using {@link Builder}</p>
 *
 * @since API 1
 */
public class NetworkConnectAttributes implements Parcelable {
    @MobileApi
    private final int mVersion;
    @MobileApi
    private final int mConnectTimeout;
    @MobileApi
    private final int mReadTimeout;

    private NetworkConnectAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mConnectTimeout = builder.mConnectTimeout;
        mReadTimeout = builder.mReadTimeout;
    }

    /**
     * @return socket connect timeout - how much time to wait before socket will be opened
     * @hide not open API
     */
    public int getConnectTimeout() {
        return mConnectTimeout;
    }

    /**
     * @return socket read timeout - how much time to wait before data becomes available to read from socket
     * @hide not open API
     */
    public int getReadTimeout() {
        return mReadTimeout;
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
     * <p>Builder for creating an instance of {@link NetworkConnectAttributes}.</p>
     *
     * @since API 1
     */
    @MobileApi
    @SuppressWarnings({"unused"})
    public static class Builder {
        private int mConnectTimeout = 60;
        private int mReadTimeout = 60;

        /**
         * <p>Default constructor to create a new Builder.<br/>
         * Default values will be configured : <br/>
         * Connect timeout = 60 seconds<br/>
         * Read timeout = 60 seconds</p>
         *
         * @since API 1
         */
        public Builder() {
        }

        /**
         * <p>Sets the socket connect timeout to wait the connection establishment.<br/>
         * Default value is 60 seconds.<br/>
         * Range: 10 ~ 120 seconds.</p>
         *
         * @param connectTimeout value in seconds. If attributes are out of supported range, CapabilitiesExceededException will return in build().
         * @return this builder for method chaining
         *
         * @since API 1
         */
        public Builder setConnectTimeout(final int connectTimeout) {
            mConnectTimeout = connectTimeout;
            return this;
        }

        /**
         * <p>Sets the socket read timeout to wait the data for starting reading.<br/>
         * Default value is 60 seconds.<br/>
         * Range: 10 ~ 120 seconds.</p>
         * @param readTimeout value in seconds. If attributes are out of supported range, CapabilitiesExceededException will return in build().
         * @return this builder for method chaining
         *
         * @since API 1
         */
        public Builder setReadTimeout(final int readTimeout) {
            mReadTimeout = readTimeout;
            return this;
        }

        /**
         * <p>Builds ConnectDeviceAttributes. All of the attributes combine into a {@link NetworkConnectAttributes} object.</p>
         *
         * @return NetworkConnectAttributes object containing all the attributes.
         * @exception CapabilitiesExceededException if attributes are out of supported range.
         *
         * @since API 1
         */
        public NetworkConnectAttributes build() throws CapabilitiesExceededException {
            if (mConnectTimeout < ConnectDevicelet.MINIMUM_TIMEOUT || mConnectTimeout > ConnectDevicelet.MAXIMUM_TIMEOUT) {
                throw new CapabilitiesExceededException("Connect Timeout value must be in range "
                        + ConnectDevicelet.MINIMUM_TIMEOUT + "-" + ConnectDevicelet.MAXIMUM_TIMEOUT + " seconds");
            }

            if (mReadTimeout < ConnectDevicelet.MINIMUM_TIMEOUT || mReadTimeout > ConnectDevicelet.MAXIMUM_TIMEOUT) {
                throw new CapabilitiesExceededException("Read Timeout value must be in range "
                        + ConnectDevicelet.MINIMUM_TIMEOUT + "-" + ConnectDevicelet.MAXIMUM_TIMEOUT + " seconds");
            }

            return new NetworkConnectAttributes(this);
        }
    }

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
