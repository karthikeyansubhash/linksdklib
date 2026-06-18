// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.accessory.hid;

import android.os.Parcel;
import android.os.Parcelable;

import com.hp.workpath.api.Convertor;

/**
 * <p>HIDInfo class provide an interface to get HID information. The class provide methods to get:-
 * <ul>
 * <li>Feature Report Length</li>
 * <li>Input Report Length</li>
 * <li>Output Report Length</li>
 * </ul>
 * </p>
 *
 * @since API 3
 */
public class HIDInfo implements Parcelable, Convertor {
    private int mFeatureReportLength;
    private int mInputReportLength;
    private int mOutputReportLength;
    private boolean mIsReading;

    /**
     * Creates new report
     *
     * @hide
     * @since API 3
     */
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

    private HIDInfo(Object object) {
        if (object instanceof com.hp.jetadvantage.link.api.accessory.hid.HIDInfo) {
            mFeatureReportLength = ((com.hp.jetadvantage.link.api.accessory.hid.HIDInfo) object).getA();
            mInputReportLength = ((com.hp.jetadvantage.link.api.accessory.hid.HIDInfo) object).getB();
            mOutputReportLength = ((com.hp.jetadvantage.link.api.accessory.hid.HIDInfo) object).getC();
            mIsReading = ((com.hp.jetadvantage.link.api.accessory.hid.HIDInfo) object).d();
        }
    }

    /**
     * <p>The length of the feature report for this accessory.</p>
     *
     * @return <p>length of the feature report for accessory in bytes.
     * <ul>
     * <li>Return can't be empty and should be either any integer value.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getFeatureReportLength() {
        return mFeatureReportLength;
    }

    /**
     * <p>The length of the input report for this accessory.</p>
     *
     * @return <p>length of the input report for accessory in bytes.
     * <ul>
     * <li>Return can't be empty and should be either any integer value.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getInputReportLength() {
        return mInputReportLength;
    }

    /**
     * <p>The length of the output report for this accessory.</p>
     *
     * @return <p>length of the output report for accessory in bytes.</p>
     *
     * @since API 3
     */
    public int getOutputReportLength() {
        return mOutputReportLength;
    }

    /**
     * <p>True if the accessory is currently reading, otherwise false.</p>
     *
     * @return <p>true if reading accessory. Otherwise return false if not reading accessory.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either true or false.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public boolean isReading() {
        return mIsReading;
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

    /**
     * @hide trivial
     */
    public static final ConvertorCreator<HIDInfo> CREATOR_OBJ = new ConvertorCreator<HIDInfo>() {
        @Override
        public HIDInfo createFromObject(Object object) {
            return new HIDInfo(object);
        }
    };

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
