// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.scanner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/*
 * @hide
 */
@SuppressWarnings({"WeakerAccess"})
@Deprecated
public class ScanletAttributes implements Parcelable {
    @Keep
    final int mVersion;
    @Keep
    final boolean mShowSettingsUi;
    @Keep
    final boolean mShowCredentialsUi;
    @Keep
    final boolean mIgnoreScanCompletion;

    /**
     * @hide The client should not need to know about the scanlet parcelable methods.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the scanlet parcelable methods.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>letAttributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(Sdk.VERSION.LEVEL);
        out.writeByte((byte) (mShowSettingsUi ? 1 : 0));
        out.writeByte((byte) (mShowCredentialsUi ? 1 : 0));
        out.writeByte((byte) (mIgnoreScanCompletion ? 1 : 0));
    }

    /**
     * @hide The client should not need to know about the scanlet parcelable methods
     */
    public static final Creator<ScanletAttributes> CREATOR = new Creator<ScanletAttributes>() {
        public ScanletAttributes createFromParcel(final Parcel in) {
            return new ScanletAttributes(in);
        }

        public ScanletAttributes[] newArray(final int size) {
            return new ScanletAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private ScanletAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mShowSettingsUi = in.readByte() != 0;
        mShowCredentialsUi = in.readByte() != 0;
        mIgnoreScanCompletion = in.readByte() != 0;
    }

    private ScanletAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mShowSettingsUi = builder.mShowSettingsUi;
        mShowCredentialsUi = builder.mShowCredentialsUi;
        mIgnoreScanCompletion = builder.mIgnoreScanCompletion;
    }

    /**
     * Builder for creating an instance of {@link ScanletAttributes}.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public static class Builder {
        boolean mShowSettingsUi = false;
        boolean mShowCredentialsUi = true;
        boolean mIgnoreScanCompletion = false;

        /**
         * Construct a new Builder with default attributes.<br>
         * <br>
         * Show Settings UI = false <br>
         * <br>
         *
         * @since API 1
         */
        public Builder() {
        }

        /**
         * Combine all of the attributes into a ScanletAttributes object.
         *
         * @return ScanletAttributes object containing all the attributes.
         * @since API 1
         */
        @Deprecated
        public ScanletAttributes z() {
            return new ScanletAttributes(this);
        }
    }
}
