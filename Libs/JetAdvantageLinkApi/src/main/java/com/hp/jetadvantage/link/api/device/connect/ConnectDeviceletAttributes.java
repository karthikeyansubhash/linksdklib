// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.device.connect;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>The sets of attributes for using built-in ui such as progress dialog provided by SDK Service.
 * An instance of this class is created using {@link Builder}
 * <br/>
 * If application sets showUI as TRUE, UI progress dialog will show while establishing connection by Link SDK service.</p>
 *
 * @since API 1
 * @hide
 */
@SuppressWarnings({"WeakerAccess"})
@Deprecated
public class ConnectDeviceletAttributes implements Parcelable {
    @MobileApi
    final int mVersion;
    @MobileApi
    final boolean mShowUi;

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>letAttributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(Sdk.VERSION.LEVEL);
        out.writeByte((byte) (mShowUi ? 1 : 0));
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<ConnectDeviceletAttributes> CREATOR = new Creator<ConnectDeviceletAttributes>() {
        public ConnectDeviceletAttributes createFromParcel(final Parcel in) {
            return new ConnectDeviceletAttributes(in);
        }

        public ConnectDeviceletAttributes[] newArray(final int size) {
            return new ConnectDeviceletAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private ConnectDeviceletAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mShowUi = in.readByte() != 0;
    }

    private ConnectDeviceletAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mShowUi = builder.mShowUi;
    }

    /**
     * <p>Builder for creating an instance of {@link ConnectDeviceletAttributes}.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static class Builder {
        boolean mShowUi = false;

        /**
         * <p>Default constructor to create a new Builder with default ConnectDeviceletAttributes.<br/>
         * Default value of UI progress dialog is FALSE.<br/>
         * </p>
         *
         * @since API 1
         */
        @Deprecated
        public Builder() {
            // Default constructor
        }

        /**
         * <p>Builds ConnectDeviceletAttributes. All of the attributes combine into a {@link ConnectDeviceletAttributes} object.</p>
         *
         * @return ConnectDeviceletAttributes object containing all the attributes.
         * @since API 1
         */
        @Deprecated
        public ConnectDeviceletAttributes z() {
            return new ConnectDeviceletAttributes(this);
        }
    }
}
