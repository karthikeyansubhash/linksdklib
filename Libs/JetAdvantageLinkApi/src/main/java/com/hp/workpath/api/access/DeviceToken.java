// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.access;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.json.SerializedName;


/**
 * <p>DeviceToken object to return certain token from a device.</p>
 *
 * @since API 3
 */
@DeviceApi
public final class DeviceToken implements Parcelable {

    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private final int mVersion;

    /**
     * The token from device to communicate with SIO services or others.
     *
     * @since API 3
     */
    @SerializedName("code")
    private String deviceToken;

    /**
     * For Parcelable implementation
     */
    @SuppressLint("RestrictedApi")
    private DeviceToken(final Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        deviceToken = in.readString();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<DeviceToken> CREATOR = new Creator<DeviceToken>() {

        @Override
        public DeviceToken createFromParcel(final Parcel in) {
            return new DeviceToken(in);
        }

        @Override
        public DeviceToken[] newArray(final int size) {
            return new DeviceToken[size];
        }
    };

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public int describeContents() {
        return hashCode();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeInt(Sdk.VERSION.LEVEL);
        parcel.writeString(deviceToken);
    }

    /**
     * <p>Returns device token</p>
     *
     * @return String device token.
     * <ul>
     * <li>Return can be null if the value for device token is null</li>
     * <li>Return can be empty if the value for device token is empty</li>
     * </ul>
     *
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public String getDeviceToken() {
        return deviceToken;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * @return string representation
     * <ul>
     * <li>Return can be null if the value for representation is null</li>
     * <li>Return can be empty if the value for representation is empty</li>
     * </ul>
     *
     * @hide
     */
    public String toString() {
        return "device token";
    }
}
