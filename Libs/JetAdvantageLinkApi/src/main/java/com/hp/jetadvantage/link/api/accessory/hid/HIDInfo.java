// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.accessory.hid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * HID information
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class HIDInfo implements Parcelable {
    private int mFeatureReportLength;
    private int mInputReportLength;
    private int mOutputReportLength;
    private boolean mIsReading;

    /**
     * Creates new report
     *
     * @since API 3
     *
     * @hide
     */
    @Deprecated
    public HIDInfo(int mFeatureReportLength, int mInputReportLength, int mOutputReportLength, boolean mIsReading) {
        this.mFeatureReportLength = mFeatureReportLength;
        this.mInputReportLength = mInputReportLength;
        this.mOutputReportLength = mOutputReportLength;
        this.mIsReading = mIsReading;
    }

    /**
     * @hide parcelable implementation
     */
    private HIDInfo(Parcel in) {
        mFeatureReportLength = in.readInt();
        mInputReportLength = in.readInt();
        mOutputReportLength = in.readInt();
        mIsReading = in.readInt() != 0;
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mFeatureReportLength);
        dest.writeInt(mInputReportLength);
        dest.writeInt(mOutputReportLength);
        dest.writeInt(mIsReading ? 1 : 0);
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
    public static final Creator<HIDInfo> CREATOR = new Creator<HIDInfo>() {
        @Override
        public HIDInfo createFromParcel(Parcel in) {
            return new HIDInfo(in);
        }

        @Override
        public HIDInfo[] newArray(int size) {
            return new HIDInfo[size];
        }
    };


    @Deprecated
    public int getA() {
        return mFeatureReportLength;
    }

    @Deprecated
    public int getB() {
        return mInputReportLength;
    }

    @Deprecated
    public int getC() {
        return mOutputReportLength;
    }

    @Deprecated
    public boolean d() {
        return mIsReading;
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public String toString() {
        return "HIDReport{" +
                "featureReportLength=" + mFeatureReportLength +
                ", inputReportLength=" + mInputReportLength +
                ", outputReportLength=" + mOutputReportLength +
                ", isReading=" + mIsReading + '}';
    }
}
