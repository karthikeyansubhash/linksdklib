// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.accessory.hid;

import android.os.Parcel;

import com.hp.jetadvantage.link.api.accessory.AccessoryInfo;
import com.hp.jetadvantage.link.api.accessory.RegistrationType;

/**
 * Accessory details
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class HIDAccessoryInfo extends AccessoryInfo {
    private int mVendorId;
    private int mProductId;
    private String mSerialNumber;
    private String mDescription;
    private String mManufacturer;
    private RegistrationType mRegistrationType;

    /**
     * @hide
     */
    @Deprecated
    public HIDAccessoryInfo(int vendorId, int productId, String serialNumber, String description, String manufacturer, RegistrationType registrationType) {
        this.mVendorId = vendorId;
        this.mProductId = productId;
        this.mSerialNumber = serialNumber;
        this.mDescription = description;
        this.mManufacturer = manufacturer;
        this.mRegistrationType = registrationType;
    }

    private HIDAccessoryInfo(Parcel in) {
        mVendorId = in.readInt();
        mProductId = in.readInt();
        mSerialNumber = in.readString();
        mDescription = in.readString();
        mManufacturer = in.readString();
        int tmpPosition = 0;
        try {
            tmpPosition = in.dataPosition();
            com.hp.workpath.api.accessory.RegistrationType registrationType = (com.hp.workpath.api.accessory.RegistrationType) in.readSerializable();
            if(registrationType != null) {
                mRegistrationType = RegistrationType.valueOf(registrationType.name());
            } else {
                mRegistrationType = null;
            }
        } catch (ClassCastException castException) {
            in.setDataPosition(tmpPosition);
            mRegistrationType = (RegistrationType)in.readSerializable();
        }
    }

    @Override
    public AccessoryClass getAccessoryClass() {
        return AccessoryClass.HID;
    }

    @Override
    public RegistrationType getRegistrationType() {
        return mRegistrationType;
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVendorId);
        dest.writeInt(mProductId);
        dest.writeString(mSerialNumber);
        dest.writeString(mDescription);
        dest.writeString(mManufacturer);
        dest.writeSerializable(mRegistrationType);
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
    public static final Creator<HIDAccessoryInfo> CREATOR = new Creator<HIDAccessoryInfo>() {
        @Override
        public HIDAccessoryInfo createFromParcel(Parcel in) {
            return new HIDAccessoryInfo(in);
        }

        @Override
        public HIDAccessoryInfo[] newArray(int size) {
            return new HIDAccessoryInfo[size];
        }
    };

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HIDAccessoryInfo)) return false;

        HIDAccessoryInfo that = (HIDAccessoryInfo) o;

        return that.mVendorId == mVendorId
                && that.mProductId == mProductId
                && mSerialNumber != null ? mSerialNumber.equals(that.mSerialNumber) : that.mSerialNumber == null;
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public int hashCode() {
        int result = mVendorId;
        result = 31 * result + mProductId;
        result = 31 * result + (mSerialNumber != null ? mSerialNumber.hashCode() : 0);
        return result;
    }


    @Deprecated
    public int getA() {
        return mVendorId;
    }

    @Deprecated
    public int getB() {
        return mProductId;
    }

    @Deprecated
    public String getC() {
        return mSerialNumber;
    }

    @Deprecated
    public String getD() {
        return mDescription;
    }

    @Deprecated
    public String getE() {
        return mManufacturer;
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public String toString() {
        return "HIDAccessoryInfo{" +
                "VID=" + mVendorId +
                '}';
    }
}
