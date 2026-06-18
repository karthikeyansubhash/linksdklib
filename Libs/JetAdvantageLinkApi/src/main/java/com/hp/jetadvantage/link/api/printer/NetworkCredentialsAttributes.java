// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.printer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * The sets of credentials attributes related with authentication parameters to get a file for printing by device over network.
 * An instance of this class is created using {@link //Builder}.
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public class NetworkCredentialsAttributes implements Parcelable {
    @Keep
    final String mUsername;
    @Keep
    final String mPassword;
    @Keep
    final String mCookie;

    private NetworkCredentialsAttributes(Parcel in) {
        mUsername = in.readString();
        mPassword = in.readString();
        mCookie = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUsername);
        dest.writeString(mPassword);
        dest.writeString(mCookie);
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
    public static final Creator<NetworkCredentialsAttributes> CREATOR = new Creator<NetworkCredentialsAttributes>() {
        @Override
        public NetworkCredentialsAttributes createFromParcel(Parcel in) {
            return new NetworkCredentialsAttributes(in);
        }

        @Override
        public NetworkCredentialsAttributes[] newArray(int size) {
            return new NetworkCredentialsAttributes[size];
        }
    };
}
