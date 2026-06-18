// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.accessory.hid;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Report data object to hold accessory data of HID type
 *
 * @since API 3
 */
@Deprecated
public class HIDReport implements Parcelable {
    private HIDReportType mHidReportType;
    private byte[] mData;

    @Deprecated
    public HIDReport(@NonNull final HIDReportType hidReportType, @NonNull final byte[] data) {
        this.mHidReportType = hidReportType;
        this.mData = data;
    }

    /**
     * @hide parcelable implementation
     */
    private HIDReport(Parcel in) {
        int position = in.dataPosition();
        try {
            mHidReportType = HIDReportType.valueOf(((com.hp.workpath.api.accessory.hid.HIDReportType) in.readSerializable()).name());
        } catch (ClassCastException castException) {
            in.setDataPosition(position);
            mHidReportType = (HIDReportType) in.readSerializable();
        }
        mData = in.createByteArray();
    }

    @Deprecated
    public HIDReportType getA() {
        return mHidReportType;
    }

    @Deprecated
    public byte[] getB() {
        return mData;
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(mHidReportType);
        dest.writeByteArray(mData);
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
    public static final Creator<HIDReport> CREATOR = new Creator<HIDReport>() {
        @Override
        public HIDReport createFromParcel(Parcel in) {
            return new HIDReport(in);
        }

        @Override
        public HIDReport[] newArray(int size) {
            return new HIDReport[size];
        }
    };

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public String toString() {
        return "HIDReport{" +
                "hidReportType=" + mHidReportType +
                '}';
    }
}
