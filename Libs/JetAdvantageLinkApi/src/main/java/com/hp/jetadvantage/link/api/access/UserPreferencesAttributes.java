// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * <p>Holder for the user's preferences</p>
 *
 * @hide
 * @since API 2
 */
@Deprecated
public class UserPreferencesAttributes implements Parcelable {
    private final int mVersion;
    private final String mAutoLaunchAppAccessPointId;
    private final String mLanguageCode;

    @SuppressLint("RestrictedApi")
    private UserPreferencesAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mAutoLaunchAppAccessPointId = in.readString();
        mLanguageCode = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeString(mAutoLaunchAppAccessPointId);
        dest.writeString(mLanguageCode);
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
    public static final Creator<UserPreferencesAttributes> CREATOR = new Creator<UserPreferencesAttributes>() {
        @Override
        public UserPreferencesAttributes createFromParcel(final Parcel in) {
            return new UserPreferencesAttributes(in);
        }

        @Override
        public UserPreferencesAttributes[] newArray(final int size) {
            return new UserPreferencesAttributes[size];
        }
    };
}
