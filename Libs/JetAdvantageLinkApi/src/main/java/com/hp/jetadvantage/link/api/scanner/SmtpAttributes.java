// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.scanner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * The sets of attributes for establishing a connection to SMTP server.
 * An instance of this class is created using {@link /*Builder}.
 *
 * @since API 1
 * @hide
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public class SmtpAttributes implements Parcelable {
    /**
     * Transport mode used to connect to SMTP server
     *
     * @since API 1
     */
    @Keep
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
         * Upgrade a plain text connection to an encrypted (SMTP server must enable STARTTLS option)
         *
         * @since API 1
         */
        START_TLS
    }

    @Keep
    final int mVersion;
    @Keep
    TransportMode mTransportMode;
    @Keep
    String mHost;
    @Keep
    int mPort;
    @Keep
    int mConnectTimeout;
    @Keep
    int mReadTimeout;
    @Keep
    NetworkCredentialsAttributes mNetworkCredentialsAttributes;

    @SuppressLint("RestrictedApi")
    private SmtpAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        int tmpPosition = 0;
        try {
            tmpPosition = in.dataPosition();
            mTransportMode = TransportMode.valueOf(((com.hp.workpath.api.scanner.SmtpAttributes.TransportMode) in.readSerializable()).name());
            mHost = in.readString();
            mPort = in.readInt();
            mConnectTimeout = in.readInt();
            mReadTimeout = in.readInt();

            Parcel parcelForNetwork = Parcel.obtain();
            com.hp.workpath.api.scanner.NetworkCredentialsAttributes networkCredentialsAttributes = in.readParcelable(com.hp.workpath.api.scanner.NetworkCredentialsAttributes.class.getClassLoader());
            if(networkCredentialsAttributes != null) {
                networkCredentialsAttributes.writeToParcel(parcelForNetwork, 0);
                parcelForNetwork.setDataPosition(0);
                mNetworkCredentialsAttributes = NetworkCredentialsAttributes.CREATOR.createFromParcel(parcelForNetwork);
            } else {
                mNetworkCredentialsAttributes = null;
            }
        } catch (ClassCastException castException) {
            in.setDataPosition(tmpPosition);
            mTransportMode = (TransportMode) in.readSerializable();
            mHost = in.readString();
            mPort = in.readInt();
            mConnectTimeout = in.readInt();
            mReadTimeout = in.readInt();
            mNetworkCredentialsAttributes = in.readParcelable(NetworkCredentialsAttributes.class.getClassLoader());
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
