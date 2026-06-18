// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.CapabilitiesExceededException;
import com.hp.workpath.common.Sdk;

/**
 * Stored job attributes for requesting a stored job from the printer.
 * An instance of this class is created using {@link StoredJobBuilder}.
 *
 * @since API 3
 */
public class StoredJobAttributes implements Parcelable {
    @Keep
    private final int mVersion;

    @Keep
    private final int mCopies;

    @Keep
    private final String mStoredJobId;

    @Keep
    private final JobCredentialsAttributes mJobCredentialsAttributes;

    private StoredJobAttributes(final StoredJobBuilder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mCopies = builder.mCopies;
        mStoredJobId = builder.mStoredJobId;
        mJobCredentialsAttributes = builder.mJobCredentialsAttributes;
    }

    /**
     * @hide for internal use
     */
    @SuppressWarnings("unused")
    public int getVersion() {
        return mVersion;
    }

    /**
     * @hide for internal use
     */
    public int getCopies() {
        return mCopies;
    }

    /**
     * @hide for internal use
     */
    public String getStoredJobId() {
        return mStoredJobId;
    }

    /**
     * @hide for internal use
     */
    public JobCredentialsAttributes getJobCredentialsAttributes() {
        return mJobCredentialsAttributes;
    }

    /**
     * Builder for creating {@link StoredJobAttributes} containing stored job attributes.
     *
     * @since API 3
     */
    @Keep
    public static class StoredJobBuilder {
        int mCopies = 1;
        String mStoredJobId;
        JobCredentialsAttributes mJobCredentialsAttributes;

        /**
         * Construct a new StoredJobBuilder for releasing a stored job.<br>
         *
         * @param storedJobId ID of stored copy job.
         * <p>
         * <ul>
         * <li>storedJobId shouldn't be empty and if empty storedJobId is passed, the builder can't be created.</li>
         * <li>storedJobId shouldn't be null and if null is passed, exception will be thrown.</li>
         * @exception NullPointerException if storedJobId is null
         * </p>
         *
         * @since API 3
         */
        @SuppressLint("RestrictedApi")
        public StoredJobBuilder(final String storedJobId) {
            this.mStoredJobId = Preconditions.checkNotNull(storedJobId);
        }

        /**
         * <p>Sets number of copies.</p>
         *
         * @param copies number of copies.
         * <p>
         * <ul>
         * <li>Minimum value of copies should be 1 and it shouldn't accept value below 1.</li>
         * <li>Maximum value of copies should be 9999 and it shouldn't accept value above 9999.</li>
         * </p>
         *
         * @return <p>this builder for method chaining.
         * <ul>
         * <li>Return value ranges from [1 to 9999].</li>
         * </ul>
         * </p>
         *
         * @since API 3
         */
        @SuppressWarnings("unused")
        @NonNull
        public StoredJobBuilder setCopies(final int copies) {
            mCopies = copies;
            return this;
        }

        /**
         * <p>Set stored job credentials attributes to access job.</p>
         *
         * @param jobCredentialsAttributes contains job credentials for accessing job. Following are attributes in job credential
         * <p>
         * <ul>
         * <li>Version</li>
         * <li>StoreJob PasswordType</li>
         * <li>StoreJob Password</li>
         * </p>
         *
         * @return <p>This builder for method chaining.</p>
         *
         * @exception NullPointerException if jobCredentialsAttributes is null.
         *
         * @since API 3
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public StoredJobBuilder setJobCredentials(@NonNull final JobCredentialsAttributes jobCredentialsAttributes) {
            mJobCredentialsAttributes = Preconditions.checkNotNull(jobCredentialsAttributes);
            return this;
        }

        /**
         * <p>Combine all of the attributes in this into a {@link StoredJobAttributes} object.</p>
         *
         * @param caps Contains copy attributes capabilities.
         * <p>
         * <ul>
         * <li>capabilities shouldn't be empty or null and if null CapabilitiesExceededException will be thrown.</li>
         * </p>
         * @return a StoredJobAttributes object containing all of the attributes.
         *
         * @exception CapabilitiesExceededException if caps does not contain the set attribute value.
         *
         * @since API 3
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public StoredJobAttributes build(@NonNull final CopyAttributesCaps caps) throws CapabilitiesExceededException {
            if (caps == null) {
                throw new CapabilitiesExceededException("CopyAttributesCapabilities is required");
            }
            Preconditions.checkNotNull(caps.mCapsCreator);

            if (!caps.getCopiesRange().validate(mCopies)) {
                throw new CapabilitiesExceededException("Supplied copies value is not in range " + caps.getCopiesRange());
            }

            if (mJobCredentialsAttributes != null) {
                if (!caps.getPasswordTypeList().contains(mJobCredentialsAttributes.mStoreJobPasswordType)) {
                    throw new CapabilitiesExceededException("Supplied stored job password type not supported");
                }
            }

            return new StoredJobAttributes(this);
        }
    }

    private StoredJobAttributes(Parcel in) {
        mVersion = in.readInt();
        mCopies = in.readInt();
        mStoredJobId = in.readString();
        mJobCredentialsAttributes = in.readParcelable(JobCredentialsAttributes.class.getClassLoader());
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
