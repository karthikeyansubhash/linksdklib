// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.access;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * <p>Container for an email address and optional display name</p>
 *
 * @since API 2
 */
@DeviceApi
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
     * <p>Gets email address</p>
     *
     * @return String email address
     * <ul>
     * <li>Return can be null if the value for mAddress is null</li>
     * <li>Return can be empty if the value for mAddress is empty</li>
     * </ul>
     *
     * @since API 1
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * <p>Gets email display name</p>
     *
     * @return String display name
     * <ul>
     * <li>Return can be null if the value for mName is null</li>
     * <li>Return can be empty if the value for mName is empty</li>
     * </ul>
     *
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
    public static final Parcelable.Creator<EmailAddressInfo> CREATOR = new Parcelable.Creator<EmailAddressInfo>() {
        @Override
        public EmailAddressInfo createFromParcel(final Parcel in) {
            return new EmailAddressInfo(in);
        }

        @Override
        public EmailAddressInfo[] newArray(final int size) {
            return new EmailAddressInfo[size];
        }
    };

}
