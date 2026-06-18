// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.api.Convertor;
import com.hp.workpath.api.scanner.ScanAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Provides details of SCAN job.</p>
 *
 * @since API 1
 */
public class ScanJobData implements JobInfo.JobData, Convertor {
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

    /**
     * @hide trivial
     */
    public ScanJobData() {
        mVersion = Sdk.VERSION.LEVEL;
    }

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

    @SuppressLint("RestrictedApi")
    private ScanJobData(Object object) {
        if(object instanceof com.hp.jetadvantage.link.api.job.ScanJobData) {
            mVersion = ((com.hp.jetadvantage.link.api.job.ScanJobData) object).getVersion();
            Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

            mImagesScanned = ((com.hp.jetadvantage.link.api.job.ScanJobData) object).getA();
            mImagesProcessed = ((com.hp.jetadvantage.link.api.job.ScanJobData) object).getB();
            mImagesTransmitted = ((com.hp.jetadvantage.link.api.job.ScanJobData) object).getC();
            mDuplex = ScanAttributes.Duplex.valueOf(((com.hp.jetadvantage.link.api.job.ScanJobData) object).getD().name());
            mScanSize = ScanAttributes.ScanSize.valueOf(((com.hp.jetadvantage.link.api.job.ScanJobData) object).getE().name());
            mFileNames = ((com.hp.jetadvantage.link.api.job.ScanJobData) object).getG();

            mJobState =  ScanJobState.CREATOR_OBJ.createFromObject(((com.hp.jetadvantage.link.api.job.ScanJobData) object).getH());
            mDestination = ScanAttributes.Destination.valueOf(((com.hp.jetadvantage.link.api.job.ScanJobData) object).getF().name());
        }
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

    /**
     * @hide trivial
     */
    public static final ConvertorCreator<ScanJobData> CREATOR_OBJ = new ConvertorCreator<ScanJobData>() {
        @Override
        public ScanJobData createFromObject(Object object) {
            return new ScanJobData(object);
        }
    };

    /**
     * Returns counter of scanned images
     *
     * @return number of scanned images
     * @since API 1
     */
    @Keep
    public int getImagesScanned() {
        return mImagesScanned;
    }

    /**
     * Sets the number of of scanned images
     *
     * @param imagesScanned number of images
     * @hide for internal use.
     * @since API 1
     */
    public void setImagesScanned(int imagesScanned) {
        this.mImagesScanned = imagesScanned;
    }

    /**
     * Returns number of processed images
     *
     * @return number of images
     * @since API 1
     */
    @Keep
    public int getImagesProcessed() {
        return mImagesProcessed;
    }

    /**
     * Sets the number of of processed images
     *
     * @param imagesProcessed number of images
     * @hide for internal use.
     * @since API 1
     */
    public void setImagesProcessed(int imagesProcessed) {
        this.mImagesProcessed = imagesProcessed;
    }

    /**
     * Returns number of transmitted images
     *
     * @return number of images
     * @since API 1
     */
    @Keep
    public int getImagesTransmitted() {
        return mImagesTransmitted;
    }

    /**
     * Set the number of of transmitted images
     *
     * @param imagesTransmitted number of images
     * @hide for internal use.
     * @since API 1
     */
    public void setImagesTransmitted(int imagesTransmitted) {
        this.mImagesTransmitted = imagesTransmitted;
    }

    /**
     * Returns duplex mode of scan job
     *
     * @return Duplex duplex mode
     * @since API 1
     */
    @Keep
    public ScanAttributes.Duplex getDuplex() {
        return mDuplex;
    }

    /**
     * Sets duplex mode for the job
     *
     * @param duplex duplex mode
     * @hide for internal use.
     * @since API 1
     */
    public void setDuplex(ScanAttributes.Duplex duplex) {
        this.mDuplex = duplex;
    }

    /**
     * Returns media size of scanned document
     *
     * @return ScanSize
     * @since API 1
     */
    @Keep
    public ScanAttributes.ScanSize getScanSize() {
        return mScanSize;
    }

    /**
     * Sets media size for the job
     *
     * @param scanSize media size of scanned document
     * @hide for internal use.
     * @since API 1
     */
    public void setScanSize(ScanAttributes.ScanSize scanSize) {
        this.mScanSize = scanSize;
    }

    /**
     * Returns destination to be sent with scanned document
     *
     * @return Destination of scanned file
     * @since API 1
     */
    @Keep
    public ScanAttributes.Destination getDestination() {
        return mDestination;
    }

    /**
     * Sets destination for the job
     *
     * @param destination of scanned file
     * @hide for internal use.
     * @since API 1
     */
    public void setDestination(ScanAttributes.Destination destination) {
        this.mDestination = destination;
    }

    /**
     * Returns the list of the scanned document name with absolute path
     *
     * @return List of filename of the scanned document for ScanToME
     * @since API 1
     */
    @Keep
    public List<String> getFileNames() {
        return mFileNames;
    }

    /**
     * Sets the list of file paths for the job
     *
     * @param fileName list of file path
     * @hide for internal use.
     * @since API 1
     */
    public void setFileNames(List<String> fileName) {
        this.mFileNames = fileName;
    }

    /**
     * Returns the state of scan job.
     *
     * @return ScanJobState the state of scan job
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public ScanJobState getJobState() {
        return mJobState;
    }

    /**
     * Set the state for the scan job
     *
     * @param state of scan job
     * @hide for internal use
     * @since API 1
     */
    public void setJobState(ScanJobState state) {
        mJobState = state;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * Internal parcelable version
     * @param version version for client
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public void setVersion(int version) {
        mVersion = version;
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
