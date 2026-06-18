// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * The sets of attributes to determine to use a built-in dialog during job.
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class JobletAttributes implements Parcelable {
    @Keep
    final int mVersion;
    @Keep
    final boolean mShowUi;
    @Keep
    Bundle mExtras;

    /**
     * @hide The client should not need to know about the joblet parcelable methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the joblet parcelable methods
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>letAttributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(Sdk.VERSION.LEVEL);
        out.writeByte((byte) (mShowUi ? 1 : 0));
        out.writeBundle(mExtras);
    }

    /**
     * @hide The client should not need to know about the joblet parcelable methods
     */
    public static final Creator<JobletAttributes> CREATOR = new Creator<JobletAttributes>() {
        public JobletAttributes createFromParcel(final Parcel in) {
            return new JobletAttributes(in);
        }

        public JobletAttributes[] newArray(final int size) {
            return new JobletAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private JobletAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);
        mShowUi = in.readByte() != 0;

        mExtras = in.readBundle(JobletAttributes.class.getClassLoader());
    }
}
