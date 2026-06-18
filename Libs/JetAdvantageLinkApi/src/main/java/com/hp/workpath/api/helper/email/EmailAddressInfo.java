// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.helper.email;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.api.Convertor;

/**
 * <p>Provides the email information with address. </p>
 *
 * @since API 1
 */
@DeviceApi
public class EmailAddressInfo implements Parcelable, Convertor {
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

    private EmailAddressInfo(Object object) {
        if (object instanceof com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo) {
            mVersion = ((com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo) object).getVersion();
            mAddress = ((com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo) object).getB();
            mName = ((com.hp.jetadvantage.link.api.helper.email.EmailAddressInfo) object).getC();
        }
    }

    /**
     * <p>Gets email recipient address</p>
     *
     * @return <p> Email recipient address.
     * <ul>
     * <li>Return value can be empty , if no recipient address for email.</li>
     * <li>Returns the address, if recipient address is given for email.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(mAddress) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * <p>Gets email recipient name</p>
     *
     * @return <p> Email recipient name.
     * <ul>
     * <li>Return value can be empty , if no recipient name is given for email.</li>
     * <li>Returns the name, if recipient name is given for email.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(mName) or
     * trying to access a field of a null reference will trigger a NullPointerException.
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

    /**
     * @hide trivial
     */
    public static final ConvertorCreator<EmailAddressInfo> CREATOR_OBJ = new ConvertorCreator<EmailAddressInfo>() {
        @Override
        public EmailAddressInfo createFromObject(Object object) {
            return new EmailAddressInfo(object);
        }
    };

}
