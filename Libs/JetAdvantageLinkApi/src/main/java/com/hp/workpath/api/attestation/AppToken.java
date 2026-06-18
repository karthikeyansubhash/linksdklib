// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.attestation;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * <p>AppToken object to return application token such as app_token and expires_in.
 * SDK provides this API to retrieve app token by calling attestation service.
 * </p>
 *
 * @since API 3
 */
@DeviceApi
public final class AppToken implements Parcelable {

    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private final int mVersion;

    /**
     * The token of link application to communicate with token proxy service or others.
     *
     * @since API 3
     */
    private String appToken;

    /**
     * The remaining expiry time of the application token in seconds. (for example 3600 for an hour)
     *
     * @since API 3
     */
    private Long expiresIn;

    /**
     * For Parcelable implementation
     */
    @SuppressLint("RestrictedApi")
    private AppToken(final Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        appToken = in.readString();
        expiresIn = in.readLong();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<AppToken> CREATOR = new Creator<AppToken>() {

        @Override
        public AppToken createFromParcel(final Parcel in) {
            return new AppToken(in);
        }

        @Override
        public AppToken[] newArray(final int size) {
            return new AppToken[size];
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
        parcel.writeString(appToken);
        parcel.writeLong(expiresIn);
    }

    /**
     * <p>Retrieve the application token</p>
     *
     * @return <p>Application token.
     * <ul>
     * <li>Return value can be empty or null and if its null, then a client can not get application token.</li>
     * </ul>
     * </p>
     * @throws NullPointerException Returns error if calling a method on a null reference(appToken) or
     *                              trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public String getAppToken() {
        return appToken;
    }

    /**
     * <p>The expiry time of token.</p>
     *
     * @return <p>The expiry time of token in seconds.
     * <ul>
     * <li>Return can't be empty and should be either any integer value.</li>
     * </ul>
     * </p>
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    public Long getExpiresIn() {
        return expiresIn;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * @return string representation
     * @hide
     */
    public String toString() {
        return "app token expires in: " + expiresIn;
    }
}
