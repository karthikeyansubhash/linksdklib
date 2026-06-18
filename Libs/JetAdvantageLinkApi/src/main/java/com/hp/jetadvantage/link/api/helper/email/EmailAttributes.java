// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.helper.email;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The sets of attributes for sending email.
 * An instance of this class is created using {@link /*Builder}.</p>
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
public final class EmailAttributes implements Parcelable {
    final int mVersion;
    final List<EmailAddressInfo> mToList;
    final List<EmailAddressInfo> mCcList;
    final List<EmailAddressInfo> mBccList;
    EmailAddressInfo mFrom;
    final String mSubject;
    final String mMessage;
    final List<File> mAttachmentList;

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
        com.hp.workpath.api.helper.email.EmailAddressInfo emailAddressInfo = null;

        int tmpPosition = 0;
        try {
            tmpPosition = in.dataPosition();
            emailAddressInfo = in.readParcelable(com.hp.workpath.api.helper.email.EmailAddressInfo.class.getClassLoader());
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
        mAttachmentList = new ArrayList<>();
        in.readList(mAttachmentList, File.class.getClassLoader());
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
        dest.writeList(mAttachmentList);
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


    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    @Deprecated
    public int getVersion() {
        return mVersion;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public List<EmailAddressInfo> getA() {
        return mToList;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public List<EmailAddressInfo> getB() {
        return mCcList;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public List<EmailAddressInfo> getC() {
        return mBccList;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public EmailAddressInfo getD() {
        return mFrom;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public String getE() {
        return mSubject;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public String getF() {
        return mMessage;
    }

    @SuppressWarnings("unused")
    @Deprecated
    public List<File> getG() {
        return mAttachmentList;
    }
}
