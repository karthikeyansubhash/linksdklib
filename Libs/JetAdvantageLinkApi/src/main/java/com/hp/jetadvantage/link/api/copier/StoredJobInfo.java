// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * Stored job info from the printer.
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class StoredJobInfo implements Parcelable {
    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private int mVersion;

    @Keep
    private final String mStoredJobId;

    @Keep
    private final String mStoredJobFolderName;

    @Keep
    private final String mStoredJobName;

    @Keep
    private final String mStoredJobUserName;

    @Keep
    private final JobCredentialsAttributes.PasswordType mStoredJobPasswordType;

    @Keep
    private final String mStoreJobTimestamp;

    @Keep
    private final int mCopies;

    @Keep
    private final CopyAttributes.ColorMode mColorMode;

    @Keep
    private final CopyAttributes.ScanSize mOriginalMediaSize;

    @Keep
    private final CopyAttributes.Duplex mOutputSides;

    @Keep
    private final int mTotalPages;

    @SuppressLint("RestrictedApi")
    private StoredJobInfo(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mTotalPages = in.readInt();
        mCopies = in.readInt();
        mStoredJobId = in.readString();
        mStoredJobFolderName = in.readString();
        mStoredJobName = in.readString();
        mStoredJobUserName = in.readString();
        mStoreJobTimestamp = in.readString();
        mStoredJobPasswordType = (JobCredentialsAttributes.PasswordType) in.readSerializable();
        mColorMode = (CopyAttributes.ColorMode) in.readSerializable();
        mOriginalMediaSize = (CopyAttributes.ScanSize) in.readSerializable();
        mOutputSides = (CopyAttributes.Duplex) in.readSerializable();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mVersion);
        out.writeInt(mTotalPages);
        out.writeInt(mCopies);
        out.writeString(mStoredJobId);
        out.writeString(mStoredJobFolderName);
        out.writeString(mStoredJobName);
        out.writeString(mStoredJobUserName);
        out.writeString(mStoreJobTimestamp);
        out.writeSerializable(mStoredJobPasswordType);
        out.writeSerializable(mColorMode);
        out.writeSerializable(mOriginalMediaSize);
        out.writeSerializable(mOutputSides);
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
    public static final Creator<StoredJobInfo> CREATOR = new Creator<StoredJobInfo>() {
        @Override
        public StoredJobInfo createFromParcel(Parcel in) {
            return new StoredJobInfo(in);
        }

        @Override
        public StoredJobInfo[] newArray(int size) {
            return new StoredJobInfo[size];
        }
    };


    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @Deprecated
    public int getVersion() {
        return mVersion;
    }

    @Deprecated
    public String getA() {
        return mStoredJobId;
    }

    @Deprecated
    public String getB() {
        return mStoredJobFolderName;
    }

    @Deprecated
    public String getC() {
        return mStoredJobName;
    }

    @Deprecated
    public String getD() {
        return mStoredJobUserName;
    }

    @Deprecated
    public JobCredentialsAttributes.PasswordType getE() {
        return mStoredJobPasswordType;
    }

    @Deprecated
    public String getF() {
        return mStoreJobTimestamp;
    }

    @Deprecated
    public int getG() {
        return mCopies;
    }

    @Deprecated
    public CopyAttributes.ColorMode getH() {
        return mColorMode;
    }

    @Deprecated
    public CopyAttributes.ScanSize getI() {
        return mOriginalMediaSize;
    }

    @Deprecated
    public CopyAttributes.Duplex getJ() {
        return mOutputSides;
    }

    @Deprecated
    public int getK() {
        return mTotalPages;
    }

    /**
     * @hide internal
     */
    @Override
    public String toString() {
        return "StoredJobInfo{" +
                "StoredJobId=" + mStoredJobId +
                ", StoreJobTimestamp=" + mStoreJobTimestamp +
                ", Copies=" + mCopies+
                ", ColorMode=" + mColorMode +
                ", OriginalMediaSize=" + mOriginalMediaSize +
                ", OutputSides=" + mOutputSides +
                ", TotalPages=" + mTotalPages +
                '}';
    }
}
