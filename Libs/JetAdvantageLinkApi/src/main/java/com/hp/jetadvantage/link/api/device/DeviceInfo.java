// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.device;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.api.device.discovery.DiscoveryType;
import com.hp.jetadvantage.link.common.model.PrinterInfo;
import com.hp.jetadvantage.link.common.model.PrinterState;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>Deviceinfo object to return device details such as macaddress, modelname and others.</p>
 * @hide
 * @since API 1
 */
@Deprecated
public class DeviceInfo implements Parcelable {
    @MobileApi
    private final int mVersion;
    @MobileApi
    private String mMacAddress;
    @MobileApi
    private String mModelName;
    @MobileApi
    private String mName;
    @MobileApi
    private String mIPAddress;
    @MobileApi
    private Status mStatus;

    @MobileApi
    private Uri mUri;
    @MobileApi
    private DiscoveryType mDiscoveryType;

    /**
     * <p>The device status.
     * It reflects whether the device is ready to work.</p>
     *
     * @since API 1
     */
    @MobileApi
    @Deprecated
    public enum Status {
        /**
         * <p>Device is in online and ready to work</p>
         *
         * @since API 1
         */
        ONLINE,

        /**
         * <p>Device is in offline state, not accessible at the moment</p>
         *
         * @since API 1
         */
        OFFLINE
    }

    /**
     * @hide application should not create this instance
     */
    @Deprecated
    public DeviceInfo() {
        mVersion = Sdk.VERSION.LEVEL;
        mStatus = Status.ONLINE; // default value TODO: need to implement to get status.
    }

    /**
     * @hide application should not create this instance
     */
    @Deprecated
    public DeviceInfo(PrinterInfo printerInfo) {
        this();
        if (printerInfo.getPrinterState() == PrinterState.CONNECTED) {
            mStatus = Status.ONLINE;
        } else {
            mStatus = Status.OFFLINE;
        }
        mIPAddress = printerInfo.getIP();
        mModelName = printerInfo.getModelName();
        mMacAddress = printerInfo.getMacAddress();
        mName = printerInfo.getDisplayName();

        mUri = printerInfo.getBaseUri();
        mDiscoveryType = DiscoveryType.valueOf(printerInfo.getDiscoveryType());
    }

    /**
     * <p>Returns macaddress from the device.</p>
     *
     * @return String MAC address of the device's interface used in discovery time
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public String geta() {
        return mMacAddress;
    }

    /**
     * <p>Returns device model name.</p>
     *
     * @return String model name
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public String getb() {
        return mModelName;
    }

    /**
     * <p>Returns device displayed name.</p>
     *
     * @return String displayed name
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public String getc() {
        return mName;
    }

    /**
     * <p>Returns device ipaddress.</p>
     *
     * @return String IPv4 address of the device's interface used in discovery time
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public String getd() {
        return mIPAddress;
    }

    /**
     * <p>Returns device status.</p>
     *
     * @return Status status of device : online or offline
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public Status gete() {
        return mStatus;
    }

    /**
     * <p>Returns device uri.</p>
     *
     * @return Uri Uri of device
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public Uri getf() {
        return mUri;
    }

    /**
     * <p>Returns discovery type.</p>
     *
     * @return DiscoveryType which was used to discover the device and obtain its details, Default discoveryType is LOCAL(Wi-Fi).
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public DiscoveryType getg() {
        return mDiscoveryType;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    @Deprecated
    public int getVersion() {
        return mVersion;
    }

    @SuppressLint("RestrictedApi")
    private DeviceInfo(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mMacAddress = in.readString();
        mModelName = in.readString();
        mName = in.readString();
        mIPAddress = in.readString();
        mStatus = (Status) in.readSerializable();

        mUri = in.readParcelable(Uri.class.getClassLoader());
        mDiscoveryType = (DiscoveryType) in.readSerializable();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeString(mMacAddress);
        dest.writeString(mModelName);
        dest.writeString(mName);
        dest.writeString(mIPAddress);
        dest.writeSerializable(mStatus);

        dest.writeParcelable(mUri, flags);
        dest.writeSerializable(mDiscoveryType);
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<DeviceInfo> CREATOR = new Creator<DeviceInfo>() {
        @Override
        public DeviceInfo createFromParcel(Parcel in) {
            return new DeviceInfo(in);
        }

        @Override
        public DeviceInfo[] newArray(int size) {
            return new DeviceInfo[size];
        }
    };

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    public String toString() {
        return "DeviceInfo{" +
                "mModelName='" + mModelName + '\'' +
                ", mName='" + mName + '\'' +
                ", mStatus='" + mStatus + '\'' +
                '}';
    }
}
