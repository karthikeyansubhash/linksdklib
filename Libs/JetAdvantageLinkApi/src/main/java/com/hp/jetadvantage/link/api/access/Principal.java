// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Principal object to return authenticated user details such as user name, domain, password and others.</p>
 *
 * @hide
 * @since API 1
 */
@Deprecated
public final class Principal implements Parcelable {
    public enum SimpleAuthority {
        LOCAL_UI_ACCESS,
        JOB_CONTROL,
        PRINT,
        SCAN,
        COPY,
        ADMIN
    }

    private final int mVersion;
    private String provider;
    private String domain;
    private String principalId;
    private String fullyQualifiedName;
    private String username;
    private String password;
    private String userEmail;
    private boolean authenticated = false;
    private boolean admin = false;
    private List<SimpleAuthority> simpleAuthorities;
    private Map<String, String> userProperties;
    private boolean isHPCloudUser;
    private boolean isSmartCardUser;
    private boolean isServiceUser;
    private boolean isGuestUser;
    private boolean isDeviceUser;
    private String providerUUID;
    private boolean authNAgentTrusted;

    /**
     * For Parcelable implementation
     */
    @SuppressLint("RestrictedApi")
    private Principal(final Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        provider = in.readString();
        domain = in.readString();
        principalId = in.readString();
        fullyQualifiedName = in.readString();
        username = in.readString();
        password = in.readString();
        userEmail = in.readString();
        authenticated = (1 == in.readInt());
        admin = (1 == in.readInt());
        simpleAuthorities = new ArrayList<>();
        in.readList(simpleAuthorities, this.getClass().getClassLoader());
        userProperties = in.readHashMap(String.class.getClassLoader());

        if (mVersion >= Sdk.VERSION_LEVEL.THREE) {
            isHPCloudUser = (1 == in.readInt());
            isSmartCardUser = (1 == in.readInt());
            isServiceUser = (1 == in.readInt());
            isGuestUser = (1 == in.readInt());
            isDeviceUser = (1 == in.readInt());
            providerUUID = in.readString();
        } else {
            isHPCloudUser = false;
            isSmartCardUser = false;
            isServiceUser = (1 == in.readInt());
            isGuestUser = (1 == in.readInt());
            isDeviceUser = (1 == in.readInt());
            providerUUID = null;
        }

        if (mVersion >= Sdk.VERSION_LEVEL.FOUR) {
            authNAgentTrusted = (1 == in.readInt());
        } else {
            authNAgentTrusted = false;
        }
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<Principal> CREATOR = new Creator<Principal>() {

        @Override
        public Principal createFromParcel(final Parcel in) {
            return new Principal(in);
        }

        @Override
        public Principal[] newArray(final int size) {
            return new Principal[size];
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
        parcel.writeString(provider);
        parcel.writeString(domain);
        parcel.writeString(principalId);
        parcel.writeString(fullyQualifiedName);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(userEmail);
        parcel.writeInt(authenticated ? 1 : 0);
        parcel.writeInt(admin ? 1 : 0);
        parcel.writeList(simpleAuthorities);
        parcel.writeMap(userProperties);

        //API LEVEL FOUR
        parcel.writeInt(isHPCloudUser ? 1 : 0);

        //API LEVEL FIVE
        parcel.writeInt(isSmartCardUser ? 1 : 0);
        parcel.writeInt(isServiceUser ? 1 : 0);
        parcel.writeInt(isGuestUser ? 1 : 0);
        parcel.writeInt(isDeviceUser ? 1 : 0);

        parcel.writeString(providerUUID);

        parcel.writeInt(authNAgentTrusted ? 1 : 0);
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getA() {
        return provider;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getB() {
        return domain;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getC() {
        return fullyQualifiedName;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getD() {
        return principalId;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getE() {
        return username;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getF() {
        return password;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getG() {
        return userEmail;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public boolean h() {
        return authenticated;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public boolean i() {
        return admin;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public List<SimpleAuthority> getJ() {
        return simpleAuthorities;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String getU(final String propertyName) {
        return userProperties.get(propertyName);
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public Map<String, String> getK() {
        return userProperties;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    @Deprecated
    public int getVersion() {
        return mVersion;
    }

    @Deprecated
    public boolean d() {
        return isHPCloudUser;
    }

    @Deprecated
    public boolean f() {
        return isSmartCardUser;
    }

    @Deprecated
    public boolean g() {
        return isServiceUser;
    }

    @Deprecated
    public boolean k() {
        return isGuestUser;
    }

    @Deprecated
    public boolean l() {
        return isDeviceUser;
    }

    @SuppressWarnings({"unused"})
    @Deprecated
    public String m() {
        return providerUUID;
    }

    @Deprecated
    public boolean n() { return authNAgentTrusted; }
}
