// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.helper.email;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * The sets of attributes to update proxy settings.
 * An instance of this class is created using {@link /*Builder}.
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public class ProxyAttributes implements Parcelable {
    /**
     * Enumeration for setting configuration mode of proxy
     *
     * @since API 1
     */
    public enum ProxyConfigurationMode {
        /**
         * No proxy
         *
         * @since API 1
         */
        NONE,

        /**
         * Use system proxy settings defined on a device
         *
         * @since API 1
         */
        SYSTEM,

        /**
         * Use provided proxy settings
         *
         * @since API 1
         */
        CUSTOM
    }

    final int mVersion;
    final ProxyConfigurationMode mProxyConfigurationMode;
    final String mHost;
    final int mPort;

    @SuppressLint("RestrictedApi")
    private ProxyAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mProxyConfigurationMode = ProxyConfigurationMode.valueOf(((com.hp.workpath.api.helper.email.ProxyAttributes.ProxyConfigurationMode) in.readSerializable()).name());
        mHost = in.readString();
        mPort = in.readInt();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeSerializable(mProxyConfigurationMode);
        dest.writeString(mHost);
        dest.writeInt(mPort);
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide parcelable implementation
     */
    public static final Creator<ProxyAttributes> CREATOR = new Creator<ProxyAttributes>() {
        @Override
        public ProxyAttributes createFromParcel(Parcel in) {
            return new ProxyAttributes(in);
        }

        @Override
        public ProxyAttributes[] newArray(int size) {
            return new ProxyAttributes[size];
        }
    };
}
