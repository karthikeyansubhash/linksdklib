// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.api.scanner.ScanAttributes;
import com.hp.workpath.common.Sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Provides details of SCAN job.</p>
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class ScanJobData implements JobInfo.JobData {
    @Keep
    private int mVersion;
    @Keep
    private int mImagesScanned;
    @Keep
    private int mImagesProcessed;
    @Keep
    private int mImagesTransmitted;
    @Keep
    private ScanAttributes.Duplex mDuplex;
    @Keep
    private ScanAttributes.ScanSize mScanSize;
    @Keep
    private ScanAttributes.Destination mDestination;
    @Keep
    private List<String> mFileNames = new ArrayList<>();
    @Keep
    private ScanJobState mJobState;

    @SuppressLint("RestrictedApi")
    private ScanJobData(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mImagesScanned = in.readInt();
        mImagesProcessed = in.readInt();
        mImagesTransmitted = in.readInt();
        mDuplex = (ScanAttributes.Duplex) in.readSerializable();
        mScanSize = (ScanAttributes.ScanSize) in.readSerializable();
        in.readStringList(mFileNames);
        mJobState = in.readParcelable(ScanJobState.class.getClassLoader());
        mDestination = (ScanAttributes.Destination) in.readSerializable();
    }

    /**
     * @hide trivial
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeInt(mImagesScanned);
        dest.writeInt(mImagesProcessed);
        dest.writeInt(mImagesTransmitted);
        dest.writeSerializable(mDuplex);
        dest.writeSerializable(mScanSize);
        dest.writeStringList(mFileNames);
        dest.writeParcelable(mJobState, 0);
        if (mVersion >= Sdk.VERSION_LEVEL.TWO || mDestination != ScanAttributes.Destination.USB) {
            dest.writeSerializable(mDestination);
        } else {
            dest.writeSerializable(null);
        }
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
    public static final Creator<ScanJobData> CREATOR = new Creator<ScanJobData>() {
        @Override
        public ScanJobData createFromParcel(Parcel in) {
            return new ScanJobData(in);
        }

        @Override
        public ScanJobData[] newArray(int size) {
            return new ScanJobData[size];
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
        return mImagesProcessed;
    }

    @Keep
    @Deprecated
    public int getC() {
        return mImagesTransmitted;
    }

    @Keep
    @Deprecated
    public ScanAttributes.Duplex getD() {
        return mDuplex;
    }

    @Keep
    @Deprecated
    public ScanAttributes.ScanSize getE() {
        return mScanSize;
    }

    @Keep
    @Deprecated
    public ScanAttributes.Destination getF() {
        return mDestination;
    }

    @Keep
    @Deprecated
    public List<String> getG() {
        return mFileNames;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ScanJobState getH() {
        return mJobState;
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
                ", imagesProcessed=" + mImagesProcessed +
                ", imagesTransmitted=" + mImagesTransmitted +
                ", duplex=" + mDuplex +
                ", fileNames=" + mFileNames +
                ", scanSize=" + mScanSize +
                ", destination=" + mDestination +
                "]";
    }
}
