// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.helper.email;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hp.workpath.api.CapabilitiesExceededException;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * The sets of attributes to access destination server over network for scan.
 * An instance of this class is created using {@link Builder}.
 *
 * @since API 1
 */
@SuppressWarnings("WeakerAccess")
public class NetworkCredentialsAttributes implements Parcelable {
    @DeviceApi
    final String mUsername;
    @DeviceApi
    final String mPassword;
    @DeviceApi
    final String mDomain;

    private NetworkCredentialsAttributes(final Builder builder) {
        mUsername = builder.mUsername;
        mPassword = builder.mPassword;
        mDomain = builder.mDomain;
    }

    private NetworkCredentialsAttributes(Parcel in) {
        mUsername = in.readString();
        mPassword = in.readString();
        mDomain = in.readString();
    }

    /**
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public String getUsername() {
        return mUsername;
    }

    /**
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public String getPassword() {
        return mPassword;
    }

    /**
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public String getDomain() {
        return mDomain;
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUsername);
        dest.writeString(mPassword);
        dest.writeString(mDomain);
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
    public static final Creator<NetworkCredentialsAttributes> CREATOR = new Creator<NetworkCredentialsAttributes>() {
        @Override
        public NetworkCredentialsAttributes createFromParcel(Parcel in) {
            return new NetworkCredentialsAttributes(in);
        }

        @Override
        public NetworkCredentialsAttributes[] newArray(int size) {
            return new NetworkCredentialsAttributes[size];
        }
    };

    /**
     * Builder for creating {@link NetworkCredentialsAttributes} containing credentials.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @DeviceApi
    public static class Builder {
        private static final int MAXIMUM_USERNAME = 128;
        private static final int MAXIMUM_PASSWORD = 128;
        private static final int MAXIMUM_DOMAIN = 256;

        String mUsername;
        String mPassword;
        String mDomain;

        /**
         * Default constructor to create a new Builder with default attributes.
         *
         * @since API 1
         */
        public Builder() {
        }

        /**
         * Sets username for URI authentication.<br>
         *
         * @param <p>Username for URI authentication.
         * <ul>
         * <li>username value can not be empty or null and must not exceed 128 characters length.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining.
         * @exception NullPointerException Returns error if calling a method on a null reference(username) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings({"unused", "unchecked"})
        @NonNull
        public Builder setUserName(@Nullable final String username) {
            mUsername = username;
            return this;
        }

        /**
         * Sets password for URI authentication.<br>
         * @param <p>Password for URI authentication.
         * <ul>
         * <li>password value can not be empty or null and must not exceed 128 characters length.</li>
         * <li>username value must be set as well.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining.
         * @exception NullPointerException Returns error if calling a method on a null reference(password) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings({"unused", "unchecked"})
        @NonNull
        public Builder setPassword(@Nullable final String password) {
            mPassword = password;
            return this;
        }

        /**
         * Sets domain for URI authentication.<br>
         * @param <p>Domain for URI authentication.
         * <ul>
         * <li>domain value can not be empty or null and must not exceed 256 characters length.</li>
         * <li>username value must be set as well.</li>
         * </ul>
         * </p>
         * @return this builder for method chaining.
         * @exception NullPointerException Returns error if calling a method on a null reference(domain) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         * @since API 1
         */
        @SuppressWarnings({"unused", "unchecked"})
        @NonNull
        public Builder setDomain(@Nullable final String domain) {
            mDomain = domain;
            return this;
        }

        /**
         * <p>Builds ConnectDeviceAttributes. All of the attributes combine into a {@link NetworkCredentialsAttributes} object.</p>
         *
         * @return a {@link NetworkCredentialsAttributes} object containing all of the attributes.
         * @exception CapabilitiesExceededException if attributes are out of supported range.
         * @since API 1
         */
        @SuppressWarnings({"unused"})
        @NonNull
        public NetworkCredentialsAttributes build() throws CapabilitiesExceededException {
            if (mUsername != null) {
                if (mUsername.isEmpty()) {
                    throw new CapabilitiesExceededException("Username value must be a non-empty string");
                } else if (mUsername.length() > MAXIMUM_USERNAME) {
                    throw new CapabilitiesExceededException(
                            "Username value length must not exceed " + MAXIMUM_USERNAME + " characters");
                }
            }

            if (mPassword != null) {
                if (mPassword.length() > MAXIMUM_PASSWORD) {
                    throw new CapabilitiesExceededException(
                            "Password value length must not exceed " + MAXIMUM_PASSWORD + " characters");
                } else if (mUsername == null || mUsername.isEmpty()) {
                    throw new CapabilitiesExceededException("If Password value is set, Username value must be also set");
                }
            }

            if (mDomain != null) {
                if (mDomain.length() > MAXIMUM_DOMAIN) {
                    throw new CapabilitiesExceededException(
                            "Domain value length must not exceed " + MAXIMUM_DOMAIN + " characters");
                } else if (mUsername == null || mUsername.isEmpty()) {
                    throw new CapabilitiesExceededException("If Domain value is set, Username value must be also set");
                }
            }

            return new NetworkCredentialsAttributes(this);
        }
    }
}
