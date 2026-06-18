// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;
import android.text.TextUtils;

import com.hp.workpath.api.CapabilitiesExceededException;

import com.hp.workpath.common.Sdk;

/**
 * Job credentials attributes for accessing a stored job from the printer.
 * An instance of this class is created using {@link Builder}.
 *
 * @since API 3
 */
public class JobCredentialsAttributes implements Parcelable {
    /**
     * Password type for stored copy job.
     *
     * @since API 3
     */
    public enum PasswordType {
        /**
         * No password for stored copy job.
         *
         * @since API 3
         */
        NONE,

        /**
         * Pin (numeric only)
         *
         * @since API 3
         */
        NUMERIC,

        /**
         * Password (alphanumeric)
         *
         * @since API 3
         */
        ALPHA_NUMERIC
    }

    @Keep
    final int mVersion;

    @Keep
    final PasswordType mStoreJobPasswordType;

    @Keep
    final String mStoreJobPassword;

    private JobCredentialsAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mStoreJobPasswordType = builder.mStoreJobPasswordType;
        mStoreJobPassword = builder.mStoreJobPassword;
    }

    /**
     * @hide for internal use
     */
    public int getVersion() {
        return mVersion;
    }

    /**
     * @hide for internal use
     */
    public PasswordType getStoreJobPasswordType() {
        return mStoreJobPasswordType;
    }

    /**
     * @hide for internal use
     */
    public String getStoreJobPassword() {
        return mStoreJobPassword;
    }

    @SuppressLint("RestrictedApi")
    private JobCredentialsAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mStoreJobPasswordType = (PasswordType) in.readSerializable();
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

    /**
     * Builder for creating {@link StoredJobAttributes} containing stored job attributes.
     *
     * @since API 3
     */
    @Keep
    public static class Builder {
        PasswordType mStoreJobPasswordType = PasswordType.NONE;
        String mStoreJobPassword;

        /**
         * Default constructor to create a new Builder with default attributes.
         *
         * @since API 3
         */
        public Builder() {
        }

        /**
         * <p>Set stored job password type.</p>
         *
         * @param passwordType can be any of value in {@link JobCredentialsAttributes.PasswordType}.
         * <p>
         * <ul>
         * <li>passwordType shouldn't be empty and if empty passwordType is passed, the builder can't be created.</li>
         * <li>passwordType shouldn't be null and if null is passed, exception will be thrown.</li>
         * </ul>
         * </p>
         * @return <p>this builder for method chaining.</p>
         *
         * @exception NullPointerException if passwordType is null.
         *
         * @since API 3
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder setPasswordType(@NonNull final PasswordType passwordType) {
            mStoreJobPasswordType = Preconditions.checkNotNull(passwordType);
            return this;
        }

        /**
         * <p>Set stored job password.</p>
         *
         * @param password can be any password set for stored job.
         * <p>
         * <ul>
         * <li>password shouldn't be empty and if empty password is passed, the builder can't be created.</li>
         * <li>password shouldn't be null and if null is passed, exception will be thrown.</li>
         * </ul>
         * </p>
         * @return <p>this builder for method chaining.</p>
         *
         * @exception NullPointerException if password is null.
         *
         * @since API 3
         */
        @SuppressWarnings("unused")
        @SuppressLint("RestrictedApi")
        @NonNull
        public Builder setPassword(@NonNull final String password) {
            mStoreJobPassword = Preconditions.checkNotNull(password);
            return this;
        }

        /**
         * <p>Combine all of the attributes in this into a {@link JobCredentialsAttributes} object.</p>
         *
         * @return a JobCredentialsAttributes object containing all of the attributes.
         *
         * @exception CapabilitiesExceededException <p>
         * <ul>
         * <li>if mStoreJobPasswordType is PasswordType.NONE and mStoreJobPassword is EMPTY.</li>
         * <li>if mStoreJobPasswordType is PasswordType.NUMERIC and mStoreJobPassword is not digit.</li>
         * </ul>
         * </p>
         * @since API 3
         */
        @SuppressWarnings("unused")
        @NonNull
        public JobCredentialsAttributes build() throws CapabilitiesExceededException {
            if (mStoreJobPasswordType != PasswordType.NONE && TextUtils.isEmpty(mStoreJobPassword)) {
                throw new CapabilitiesExceededException("For password type " + mStoreJobPasswordType + " a password must be provided");
            }

            if (mStoreJobPasswordType == PasswordType.NUMERIC && !TextUtils.isDigitsOnly(mStoreJobPassword)) {
                throw new CapabilitiesExceededException("For password type " + mStoreJobPasswordType + " only digits are allowed in a password");
            }

            return new JobCredentialsAttributes(this);
        }
    }
}
