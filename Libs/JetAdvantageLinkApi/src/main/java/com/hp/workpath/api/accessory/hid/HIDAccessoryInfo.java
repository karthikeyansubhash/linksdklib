// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.accessory.hid;

import android.os.Parcel;

import com.hp.workpath.api.accessory.AccessoryInfo;
import com.hp.workpath.api.accessory.RegistrationType;
import com.hp.workpath.api.Convertor;

/**
 * <p>HIDAccessoryInfo class provide an interface to get accessory details.</p>
 *
 * @since API 3
 */
public class HIDAccessoryInfo extends AccessoryInfo implements Convertor {
    private int mVendorId;
    private int mProductId;
    private String mSerialNumber;
    private String mDescription;
    private String mManufacturer;
    private RegistrationType mRegistrationType;

    /**
     * @hide
     */
    public HIDAccessoryInfo(int vendorId, int productId, String serialNumber, String description, String manufacturer, RegistrationType registrationType) {
        this.mVendorId = vendorId;
        this.mProductId = productId;
        this.mSerialNumber = serialNumber;
        this.mDescription = description;
        this.mManufacturer = manufacturer;
        this.mRegistrationType = registrationType;
    }

    /**
     * <p>Creates HIDAccessory information.</p>
     *
     * @param vendorId     Accessory vendor id.
     * @param productId    Accessory product id.
     * @param serialNumber Accessory serial number.
     * @return an object of HIDAccessoryInfo, description and manufacturer will be initialized as null
     *
     * @since API 3
     */
    public HIDAccessoryInfo(int vendorId, int productId, String serialNumber) {
        this(vendorId, productId, serialNumber, null, null, null);
    }

    private HIDAccessoryInfo(Parcel in) {
        mVendorId = in.readInt();
        mProductId = in.readInt();
        mSerialNumber = in.readString();
        mDescription = in.readString();
        mManufacturer = in.readString();
        mRegistrationType = (RegistrationType) in.readSerializable();
    }

    private HIDAccessoryInfo(Object object) {
        if (object instanceof com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) {
            mVendorId = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getA();
            mProductId = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getB();
            mSerialNumber = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getC();
            mDescription = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getD();
            mManufacturer = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getE();

            com.hp.jetadvantage.link.api.accessory.RegistrationType registrationType = ((com.hp.jetadvantage.link.api.accessory.hid.HIDAccessoryInfo) object).getRegistrationType();
            if (registrationType != null) {
                mRegistrationType = RegistrationType.valueOf(registrationType.name());
            } else {
                mRegistrationType = RegistrationType.OWNED; //TODO Default is OWNED
            }
        }
    }

    /**
     * <p>Retrieve the supported accessory class.</p>
     *
     * @return <p>accessory class.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of the values in {@link AccessoryInfo.AccessoryClass}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Override
    public AccessoryClass getAccessoryClass() {
        return AccessoryClass.HID;
    }

    /**
     * <p>Retrieve the registration type of accessory.</p>
     *
     * @return <p>registration type of accessory.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of the values in {@link RegistrationType}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Override
    public RegistrationType getRegistrationType() {
        return mRegistrationType;
    }

    /**
     * <p>Retrieve the Vendor ID of accessory.</p>
     *
     * @return <p>vendor id of accessory.
     * <ul>
     * <li>Return value can't be empty or null and should be a set of integer value assigned by vendor.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getVendorId() {
        return mVendorId;
    }

    /**
     * <p>Retrieve the Product ID of accessory.</p>
     *
     * @return <p>product id of accessory.
     * <ul>
     * <li>Return value can't be empty or null and should be a set of integer value assigned by vendor.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public int getProductId() {
        return mProductId;
    }

    /**
     * <p>Retrieve the serial number of accessory.</p>
     *
     * @return <p>serial number of accessory.
     * <ul>
     * <li>Return value can't be empty or null and should be a set of value assigned by vendor.</li>
     * </ul>
     * </p>
     * @since API 3
     */
    public String getSerialNumber() {
        return mSerialNumber;
    }

    /**
     * <p>Retrieve the accessory description for accessory.</p>
     *
     * @return <p>description for the accessory connected.</p>
     *
     * @since API 3
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * <p>Retrieve the accessory manufacturer information.</p>
     *
     * @return <p>manufacturer details for accessory connected.</p>
     *
     * @since API 3
     */
    public String getManufacturer() {
        return mManufacturer;
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
     * @hide trivial
     */
    public static final ConvertorCreator<HIDAccessoryInfo> CREATOR_OBJ = new ConvertorCreator<HIDAccessoryInfo>() {
        @Override
        public HIDAccessoryInfo createFromObject(Object object) {
            return new HIDAccessoryInfo(object);
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
