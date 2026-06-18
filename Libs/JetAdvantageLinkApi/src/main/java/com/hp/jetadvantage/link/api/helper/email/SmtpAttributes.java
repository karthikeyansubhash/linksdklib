// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.helper.email;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * The sets of attributes to update SMTP information for sending email.
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public class SmtpAttributes implements Parcelable {
    /**
     * Enumeration of the configuration to connect to SMTP server
     *
     * @since API 1
     */
    public enum TransportMode {
        /**
         * Plain text connection
         *
         * @since API 1
         */
        PLAIN,

        /**
         * Encrypted connection using SSL/TLS protocol over separate port (usually 465)
         *
         * @since API 1
         */
        SSL_TLS,

        /**
         * A plain text connection to an encrypted (SMTP server must enable STARTTLS option)
         *
         * @since API 1
         */
        START_TLS
    }

    final int mVersion;
    final TransportMode mTransportMode;
    final String mHost;
    final int mPort;
    final int mConnectTimeout;
    final int mReadTimeout;
    final NetworkCredentialsAttributes mNetworkCredentialsAttributes;

    @SuppressLint("RestrictedApi")
    private SmtpAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mTransportMode = TransportMode.valueOf(((com.hp.workpath.api.helper.email.SmtpAttributes.TransportMode) in.readSerializable()).name());
        mHost = in.readString();
        mPort = in.readInt();
        mConnectTimeout = in.readInt();
        mReadTimeout = in.readInt();

        Parcel parcelForNetwork = Parcel.obtain();
        com.hp.workpath.api.helper.email.NetworkCredentialsAttributes networkCredentialsAttributes = in.readParcelable(com.hp.workpath.api.helper.email.NetworkCredentialsAttributes.class.getClassLoader());
        if(networkCredentialsAttributes != null) {
            networkCredentialsAttributes.writeToParcel(parcelForNetwork, 0);
            parcelForNetwork.setDataPosition(0);
            mNetworkCredentialsAttributes = NetworkCredentialsAttributes.CREATOR.createFromParcel(parcelForNetwork);
        } else {
            mNetworkCredentialsAttributes = null;
        }
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeSerializable(mTransportMode);
        dest.writeString(mHost);
        dest.writeInt(mPort);
        dest.writeInt(mConnectTimeout);
        dest.writeInt(mReadTimeout);
        dest.writeParcelable(mNetworkCredentialsAttributes, flags);
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
    public static final Creator<SmtpAttributes> CREATOR = new Creator<SmtpAttributes>() {
        @Override
        public SmtpAttributes createFromParcel(Parcel in) {
            return new SmtpAttributes(in);
        }

        @Override
        public SmtpAttributes[] newArray(int size) {
            return new SmtpAttributes[size];
        }
    };
}
