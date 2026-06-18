// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.helper.email;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The sets of attributes to access destination server over network for scan.
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public class NetworkCredentialsAttributes implements Parcelable {
    final String mUsername;
    final String mPassword;
    final String mDomain;

    private NetworkCredentialsAttributes(Parcel in) {
        mUsername = in.readString();
        mPassword = in.readString();
        mDomain = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUsername);
        dest.writeString(mPassword);
        dest.writeString(mDomain);
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
