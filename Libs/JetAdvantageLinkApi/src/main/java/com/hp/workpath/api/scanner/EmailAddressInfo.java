// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hp.workpath.common.Sdk;

/**
 * Provides the Email address information including address and name.
 *
 * @since API 1
 */
public class EmailAddressInfo implements Parcelable {
    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private int mVersion;

    /**
     * Recipient email address
     */
    private String mAddress;

    /**
     * Recipient name
     */
    private String mName;

    private EmailAddressInfo() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    EmailAddressInfo(@NonNull final String address, @Nullable final String name) {
        this();
        mAddress = address;
        mName = name;
    }

    private EmailAddressInfo(Parcel in) {
        mVersion = in.readInt();
        mAddress = in.readString();
        mName = in.readString();
    }

    /**
     * Returns recipient email address
     *
     * @return recipient address
     * <p>
     * <ul>
     * <li>Return can be null if the mAddress is null</li>
     * <li>Return can be null if the mAddress is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * Returns recipient name
     *
     * @return recipient name
     * <p>
     * <ul>
     * <li>Return can be null if the mName is null</li>
     * <li>Return can be null if the mName is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public String getName() {
        return mName;
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
