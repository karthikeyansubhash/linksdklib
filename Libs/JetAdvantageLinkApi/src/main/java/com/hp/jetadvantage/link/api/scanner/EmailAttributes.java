// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.scanner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * The sets of attributes for an email to be sent.
 * An instance of this class is created using {@link /*Builder}.
 *
 * @since API 1
 * @hide
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public final class EmailAttributes implements Parcelable {
    @Keep
    final int mVersion;
    @Keep
    final List<EmailAddressInfo> mToList;
    @Keep
    final List<EmailAddressInfo> mCcList;
    @Keep
    final List<EmailAddressInfo> mBccList;
    @Keep
    EmailAddressInfo mFrom;
    @Keep
    final String mSubject;
    @Keep
    final String mMessage;

    @SuppressLint("RestrictedApi")
    private EmailAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mToList = new ArrayList<>();
        in.readTypedList(mToList, EmailAddressInfo.CREATOR);
        mCcList = new ArrayList<>();
        in.readTypedList(mCcList, EmailAddressInfo.CREATOR);
        mBccList = new ArrayList<>();
        in.readTypedList(mBccList, EmailAddressInfo.CREATOR);

        Parcel parcelForFrom = Parcel.obtain();
        com.hp.workpath.api.scanner.EmailAddressInfo emailAddressInfo = null;
        int tmpPosition = 0;
        try {
            tmpPosition = in.dataPosition();
            emailAddressInfo = in.readParcelable(com.hp.workpath.api.scanner.EmailAddressInfo.class.getClassLoader());
            if (emailAddressInfo != null) {
                emailAddressInfo.writeToParcel(parcelForFrom, 0);
                parcelForFrom.setDataPosition(0);
                mFrom = EmailAddressInfo.CREATOR.createFromParcel(parcelForFrom);
            } else {
                mFrom = null;
            }
        } catch (ClassCastException castException) {
            in.setDataPosition(tmpPosition);
            mFrom = in.readParcelable(EmailAddressInfo.class.getClassLoader());
        }

        mSubject = in.readString();
        mMessage = in.readString();
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
    public static final Creator<EmailAttributes> CREATOR = new Creator<EmailAttributes>() {
        @Override
        public EmailAttributes createFromParcel(Parcel in) {
            return new EmailAttributes(in);
        }

        @Override
        public EmailAttributes[] newArray(int size) {
            return new EmailAttributes[size];
        }
    };
}
