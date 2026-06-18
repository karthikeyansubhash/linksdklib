// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.api.copier.CopyAttributes;
import com.hp.workpath.common.Sdk;

/**
 * Copy Job details
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class CopyJobData implements JobInfo.JobData {
    @Keep
    private int mVersion;
    @Keep
    private int mImagesScanned;
    @Keep
    private int mSheetsPrinted;
    @Keep
    private CopyAttributes.Duplex mDuplex;
    @Keep
    private CopyAttributes.ScanSize mScanSize;
    @Keep
    private CopyJobState mJobState;
    @Keep
    private CopyAttributes.JobExecutionMode mJobExecutionMode;

    @SuppressLint("RestrictedApi")
    private CopyJobData(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mImagesScanned = in.readInt();
        mSheetsPrinted = in.readInt();
        mDuplex = (CopyAttributes.Duplex) in.readSerializable();
        mScanSize = (CopyAttributes.ScanSize) in.readSerializable();
        mJobState = in.readParcelable(PrintJobState.class.getClassLoader());
        mJobExecutionMode = (CopyAttributes.JobExecutionMode) in.readSerializable();
    }

    /**
     * @hide trivial
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeInt(mImagesScanned);
        dest.writeInt(mSheetsPrinted);
        dest.writeSerializable(mDuplex);
        dest.writeSerializable(mScanSize);
        dest.writeParcelable(mJobState, 0);
        dest.writeSerializable(mJobExecutionMode);
    }

    /**
     * @hide trivial
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide trivial
     */
    public static final Creator<CopyJobData> CREATOR = new Creator<CopyJobData>() {
        @Override
        public CopyJobData createFromParcel(Parcel in) {
            return new CopyJobData(in);
        }

        @Override
        public CopyJobData[] newArray(int size) {
            return new CopyJobData[size];
        }
    };

    @Keep
    @Deprecated
    public int getA() {
        return mImagesScanned;
    }

    @Keep
    @Deprecated
    public int getB() {
        return mSheetsPrinted;
    }

    @Keep
    @Deprecated
    public CopyAttributes.Duplex getC() {
        if(mDuplex == null) {
            mDuplex = CopyAttributes.Duplex.DEFAULT;
        }
        return mDuplex;
    }

    @Keep
    @Deprecated
    public CopyAttributes.ScanSize getD() {
        if(mScanSize == null) {
            mScanSize = CopyAttributes.ScanSize.DEFAULT;
        }
        return mScanSize;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public CopyJobState getE() {
        return mJobState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public CopyAttributes.JobExecutionMode getF() {
        if(mJobExecutionMode == null) {
            mJobExecutionMode = CopyAttributes.JobExecutionMode.NORMAL;
        }
        return mJobExecutionMode;
    }

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

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    @Keep
    public String toString() {
        return getClass().getSimpleName() + "[" +
                "jobState=" + mJobState +
                ", imagesScanned=" + mImagesScanned +
                ", sheetsPrinted=" + mSheetsPrinted +
                ", duplex=" + mDuplex +
                ", scanSize=" + mScanSize +
                ", jobExecutionMode=" + mJobExecutionMode +
                "]";
    }
}
