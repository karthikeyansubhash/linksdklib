// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Holder for the user's overrides</p>
 *
 * @hide
 * @since API 2
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public final class UserOverridesAttributes implements Parcelable {
    final int mVersion;
    final List<EmailAddressInfo> mToList;
    final List<EmailAddressInfo> mCcList;
    final List<EmailAddressInfo> mBccList;
    final EmailAddressInfo mFrom;
    final String mSubject;
    final String mMessage;

    final String mFaxCompanyName;
    final String mFaxBillingCode;

    @SuppressLint("RestrictedApi")
    private UserOverridesAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mToList = new ArrayList<>();
        in.readTypedList(mToList, EmailAddressInfo.CREATOR);

        mCcList = new ArrayList<>();
        in.readTypedList(mCcList, EmailAddressInfo.CREATOR);

        mBccList = new ArrayList<>();
        in.readTypedList(mBccList, EmailAddressInfo.CREATOR);

        Parcel parcelForFrom = Parcel.obtain();
        com.hp.workpath.api.access.EmailAddressInfo emailAddressInfo = in.readParcelable(com.hp.workpath.api.access.EmailAddressInfo.class.getClassLoader());
        if(emailAddressInfo != null) {
            emailAddressInfo.writeToParcel(parcelForFrom, 0);
            parcelForFrom.setDataPosition(0);
            mFrom = EmailAddressInfo.CREATOR.createFromParcel(parcelForFrom);
        } else {
            mFrom = null;
        }

        mSubject = in.readString();
        mMessage = in.readString();

        mFaxCompanyName = in.readString();
        mFaxBillingCode = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeTypedList(mToList);
        dest.writeTypedList(mCcList);
        dest.writeTypedList(mBccList);
        dest.writeParcelable(mFrom, 0);
        dest.writeString(mSubject);
        dest.writeString(mMessage);
        dest.writeString(mFaxCompanyName);
        dest.writeString(mFaxBillingCode);
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
    public static final Creator<UserOverridesAttributes> CREATOR = new Creator<UserOverridesAttributes>() {
        @Override
        public UserOverridesAttributes createFromParcel(final Parcel in) {
            return new UserOverridesAttributes(in);
        }

        @Override
        public UserOverridesAttributes[] newArray(final int size) {
            return new UserOverridesAttributes[size];
        }
    };
}
