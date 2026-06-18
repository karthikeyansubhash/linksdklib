// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.api.Convertor;

/**
 * <p>
 * StoredJobInfo class provide path to get information regarding jobs stored
 * in a printer. This class can retrieve information like:-
 * <ul>
 * <li><a href="#C2N">Stored Job Id </a></li>
 * <li><a href="#C2S">Stored Job Name </a></li>
 * <li><a href="#C2S">Stored Job FolderName </a></li>
 * <li><a href="#C2S">Stored Job UserName </a></li>
 * <li><a href="#C2S">Store Job Timestamp </a></li>
 * <li><a href="#C2S">Stored Job PasswordType </a></li>
 * <li><a href="#C2S">Color Mode </a></li>
 * <li><a href="#C2S">Original MediaSize </a></li>
 * <li><a href="#C2S">Output Sides </a></li>
 * <li><a href="#C2S">Total Pages </a></li>
 * </ul>
 * </p>
 *
 * @since API 3
 */
public class StoredJobInfo implements Parcelable, Convertor {
    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private int mVersion;

    @Keep
    private String mStoredJobId;

    @Keep
    private String mStoredJobFolderName;

    @Keep
    private String mStoredJobName;

    @Keep
    private String mStoredJobUserName;

    @Keep
    private JobCredentialsAttributes.PasswordType mStoredJobPasswordType;

    @Keep
    private String mStoreJobTimestamp;

    @Keep
    private int mCopies;

    @Keep
    private CopyAttributes.ColorMode mColorMode;

    @Keep
    private CopyAttributes.ScanSize mOriginalMediaSize;

    @Keep
    private CopyAttributes.Duplex mOutputSides;

    @Keep
    private int mTotalPages;

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
     * @hide trivial
     */
    public static final ConvertorCreator<StoredJobInfo> CREATOR_OBJ = new ConvertorCreator<StoredJobInfo>() {
        @Override
        public StoredJobInfo createFromObject(Object object) {
            return new StoredJobInfo(object);
        }
    };

    /**
     * <p>Retrieve stored job id from the printer.</p>
     *
     * @return <p>Stored job id.
     * <ul>
     * <li>Return value shouldn't be empty or null since it is required to identify job.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoredJobId() {
        return mStoredJobId;
    }

    /**
     * <p>Retrieve stored job folder name from the printer.</p>
     *
     * @return <p>Stored job folder name.
     * <ul>
     * <li>Return value can be empty and if no value is assigned, the function will return empty string.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoredJobFolderName() {
        return mStoredJobFolderName;
    }

    /**
     * <p>Retrieve stored job name from the printer.</p>
     *
     * @return <p>Stored job name.
     * <ul>
     * <li>Return value shouldn't be empty or null and if no name is set for the job, then job is assigned with a default name.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoredJobName() {
        return mStoredJobName;
    }

    /**
     * <p>Retrieve username which is used while creating a stored job.</p>
     *
     * @return <p>Username of stored job.
     * <ul>
     * <li>Return value can be empty or null and if value is set empty, then getStoredJobUserName will return empty string.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoredJobUserName() {
        return mStoredJobUserName;
    }

    /**
     * <p>Retrieve password type of stored job from the printer.</p>
     *
     * @return <p>Password type of stored job.
     * <ul>
     * <li>Return value can't be empty or null and it should be either any of the values from {@link JobCredentialsAttributes.PasswordType}</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public JobCredentialsAttributes.PasswordType getStoredJobPasswordType() {
        return mStoredJobPasswordType;
    }

    /**
     * <p>Retrieve timestamp of stored job from the printer.</p>
     *
     * @return <p>timestamp of stored job.
     * <ul>
     * <li>Return value can't be empty or null and the timestamp value is auto assigned when the job is stored.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoreJobTimestamp() {
        return mStoreJobTimestamp;
    }

    /**
     * <p>Retrieve number of copies required for each stored job.</p>
     *
     * @return <p>Number of copies required.
     * <ul>
     * <li>Return value ranges from [1 to 9999].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getCopies() {
        return mCopies;
    }

    /**
     * <p>Retrieve color mode of stored job from the printer.</p>
     *
     * @return <p>ColorMode of the stored job.
     * <ul>
     * <li>Return value can't be empty or null and it should be either any of the values from {@link CopyAttributes.ColorMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public CopyAttributes.ColorMode getColorMode() {
        return mColorMode;
    }

    /**
     * <p>Retrieve original media size of stored job from the printer.</p>
     *
     * @return <p>Original media size of the stored job.
     * <ul>
     * <li>Return value can't be empty or null and it should be either any of the values from {@link CopyAttributes.ScanSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public CopyAttributes.ScanSize getOriginalMediaSize() {
        return mOriginalMediaSize;
    }

    /**
     * <p>Retrieve output sides of stored job from the printer.</p>
     *
     * @return <p>Output sides of the stored job.
     * <ul>
     * <li>Return value can't be empty or null and it should be either any of the values from {@link CopyAttributes.Duplex}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public CopyAttributes.Duplex getOutputSides() {
        return mOutputSides;
    }

    /**
     * <p>Retrieve total number of pages of stored job.</p>
     *
     * @return <p>Number of total pages.
     * <ul>
     * <li>Return value should not be less than 1.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getTotalPages() {
        return mTotalPages;
    }


    /**
     * @hide
     */
    public StoredJobInfo(String mStoredJobId, String storedJobFolderName, String storedJobName, String storedJobUserName,
                         JobCredentialsAttributes.PasswordType storedJobPasswordType, String storeJobTimestamp, int copies,
                         CopyAttributes.ColorMode colorMode, CopyAttributes.ScanSize originalMediaSize,
                         CopyAttributes.Duplex outputSides, int totalPages) {
        this.mVersion = Sdk.VERSION.LEVEL;
        this.mStoredJobId = mStoredJobId;
        this.mStoredJobFolderName = storedJobFolderName;
        this.mStoredJobName = storedJobName;
        this.mStoredJobUserName = storedJobUserName;
        this.mStoredJobPasswordType = storedJobPasswordType;
        this.mStoreJobTimestamp = storeJobTimestamp;
        this.mCopies = copies;
        this.mColorMode = colorMode;
        this.mOriginalMediaSize = originalMediaSize;
        this.mOutputSides = outputSides;
        this.mTotalPages = totalPages;
    }

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

    @SuppressLint("RestrictedApi")
    private StoredJobInfo(Object object) {
        if (object instanceof com.hp.jetadvantage.link.api.copier.StoredJobInfo) {
            mVersion = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getVersion();
            Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

            mTotalPages = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getK();
            mCopies = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getG();
            mStoredJobId = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getA();
            mStoredJobFolderName = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getB();
            mStoredJobName = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getC();
            mStoredJobUserName = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getD();
            mStoreJobTimestamp = ((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getF();
            mStoredJobPasswordType = JobCredentialsAttributes.PasswordType.valueOf(((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getE().name());
            mColorMode = CopyAttributes.ColorMode.valueOf(((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getH().name());
            mOriginalMediaSize = CopyAttributes.ScanSize.valueOf(((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getI().name());
            mOutputSides = CopyAttributes.Duplex.valueOf(((com.hp.jetadvantage.link.api.copier.StoredJobInfo) object).getJ().name());
        }
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
     * @hide internal
     */
    @Override
    public String toString() {
        return "StoredJobInfo{" +
                "StoredJobId=" + mStoredJobId +
                ", StoreJobTimestamp=" + mStoreJobTimestamp +
                ", Copies=" + mCopies +
                ", ColorMode=" + mColorMode +
                ", OriginalMediaSize=" + mOriginalMediaSize +
                ", OutputSides=" + mOutputSides +
                ", TotalPages=" + mTotalPages +
                '}';
    }
}
