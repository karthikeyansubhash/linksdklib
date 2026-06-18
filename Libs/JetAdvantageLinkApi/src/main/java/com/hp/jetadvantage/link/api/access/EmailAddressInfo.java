// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Provides the Email address information including address and name.
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class EmailAddressInfo implements Parcelable {
    private int mVersion;
    private String mAddress;
    private String mName;

    private EmailAddressInfo(Parcel in) {
        mVersion = in.readInt();
        mAddress = in.readString();
        mName = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeString(mAddress);
        dest.writeString(mName);
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
    public static final Creator<EmailAddressInfo> CREATOR = new Creator<EmailAddressInfo>() {
        @Override
        public EmailAddressInfo createFromParcel(Parcel in) {
            return new EmailAddressInfo(in);
        }

        @Override
        public EmailAddressInfo[] newArray(int size) {
            return new EmailAddressInfo[size];
        }
    };

}
