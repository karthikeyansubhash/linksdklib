// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.copier;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * Stored job attributes for requesting a stored job from the printer.
 * An instance of this class is created using {@link /*StoredJobBuilder}.
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class StoredJobAttributes implements Parcelable {
    @Keep
    private final int mVersion;

    @Keep
    private final int mCopies;

    @Keep
    private final String mStoredJobId;

    @Keep
    private final JobCredentialsAttributes mJobCredentialsAttributes;

    private StoredJobAttributes(Parcel in) {
        mVersion = in.readInt();
        mCopies = in.readInt();
        mStoredJobId = in.readString();

        Parcel parcelForJobCredentials = Parcel.obtain();
        com.hp.workpath.api.copier.JobCredentialsAttributes jobCredentialsAttributes = in.readParcelable(com.hp.workpath.api.copier.JobCredentialsAttributes.class.getClassLoader());
        if(jobCredentialsAttributes != null) {
            jobCredentialsAttributes.writeToParcel(parcelForJobCredentials, 0);
            parcelForJobCredentials.setDataPosition(0);
            mJobCredentialsAttributes = JobCredentialsAttributes.CREATOR.createFromParcel(parcelForJobCredentials);
        } else {
            mJobCredentialsAttributes = null;
        }
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeInt(mCopies);
        dest.writeString(mStoredJobId);
        dest.writeParcelable(mJobCredentialsAttributes, 0);
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
    public static final Creator<StoredJobAttributes> CREATOR = new Creator<StoredJobAttributes>() {
        @Override
        public StoredJobAttributes createFromParcel(Parcel in) {
            return new StoredJobAttributes(in);
        }

        @Override
        public StoredJobAttributes[] newArray(int size) {
            return new StoredJobAttributes[size];
        }
    };
}
