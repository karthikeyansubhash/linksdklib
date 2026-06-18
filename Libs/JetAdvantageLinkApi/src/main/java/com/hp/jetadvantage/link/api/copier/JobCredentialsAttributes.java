// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

/**
 * Job credentials attributes for accessing a stored job from the printer.
 * An instance of this class is created using {@link //Builder}.
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class JobCredentialsAttributes implements Parcelable {
    public enum PasswordType {
        NONE,
        NUMERIC,
        ALPHA_NUMERIC
    }

    @Keep
    final int mVersion;

    @Keep
    final PasswordType mStoreJobPasswordType;

    @Keep
    final String mStoreJobPassword;

    @SuppressLint("RestrictedApi")
    private JobCredentialsAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mStoreJobPasswordType = PasswordType.valueOf(((com.hp.workpath.api.copier.JobCredentialsAttributes.PasswordType)in.readSerializable()).name());
        mStoreJobPassword = in.readString();
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeSerializable(mStoreJobPasswordType);
        dest.writeString(mStoreJobPassword);
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
    public static final Creator<JobCredentialsAttributes> CREATOR = new Creator<JobCredentialsAttributes>() {
        @Override
        public JobCredentialsAttributes createFromParcel(Parcel in) {
            return new JobCredentialsAttributes(in);
        }

        @Override
        public JobCredentialsAttributes[] newArray(int size) {
            return new JobCredentialsAttributes[size];
        }
    };
}
