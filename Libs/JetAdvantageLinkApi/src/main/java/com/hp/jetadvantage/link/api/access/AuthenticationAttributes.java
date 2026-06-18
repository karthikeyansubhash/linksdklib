// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

import java.util.Map;

/**
 * Provides the authentication attributes to be filled with user details for requesting sign in.
 *
 * @hide
 * @since API 2
 */
@Deprecated
public final class AuthenticationAttributes implements Parcelable {

    @DeviceApi
    public enum AuthenticationType {
        WINDOWS,
        WINDOWS_SMART_CARD,
        NOVELL,
        LDAP,
        PIN,
        OTHER
    }

    private final int mVersion;
    private final AuthenticationType mAuthenticationType;
    private final String mFullyQualifiedName;
    private final String mUserName;
    private final String mSidString;
    private final String mDisplayName;
    private final String mLdapBindUser;
    private final String mUserPrincipalName;
    private final String mSAMAccountName;
    private final String mHomeFolderPath;
    private final String mUserDomain;
    private final String mNdsContext;
    private final String mNdsTreeName;
    private final String mUserEmail;
    private final String mExchangeMailboxUri;
    private final String mPassword;
    private final Map<String, String> mUserProperties;
    private final UserPreferencesAttributes mUserPreferencesAttributes;
    private final UserOverridesAttributes mUserOverridesAttributes;

    @SuppressLint("RestrictedApi")
    private AuthenticationAttributes(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        com.hp.workpath.api.access.AuthenticationAttributes.AuthenticationType authenticationType = (com.hp.workpath.api.access.AuthenticationAttributes.AuthenticationType) in.readSerializable();
        if(authenticationType == null) {
            authenticationType = com.hp.workpath.api.access.AuthenticationAttributes.AuthenticationType.WINDOWS; //default
        }
        mAuthenticationType = AuthenticationType.valueOf((authenticationType).name());

        mFullyQualifiedName = in.readString();
        mUserName = in.readString();
        mSidString = in.readString();
        mDisplayName = in.readString();
        mLdapBindUser = in.readString();
        mUserPrincipalName = in.readString();
        mSAMAccountName = in.readString();
        mHomeFolderPath = in.readString();
        mUserDomain = in.readString();
        mNdsContext = in.readString();
        mNdsTreeName = in.readString();
        mUserEmail = in.readString();
        mExchangeMailboxUri = in.readString();
        mPassword = in.readString();
        mUserProperties = in.readHashMap(String.class.getClassLoader());

        Parcel parcelForUserPrefAttr = Parcel.obtain();
        com.hp.workpath.api.access.UserPreferencesAttributes userPreferencesAttributes = in.readParcelable(com.hp.workpath.api.access.UserPreferencesAttributes.class.getClassLoader());
        if (userPreferencesAttributes != null) {
            userPreferencesAttributes.writeToParcel(parcelForUserPrefAttr, 0);
            parcelForUserPrefAttr.setDataPosition(0);
            mUserPreferencesAttributes = UserPreferencesAttributes.CREATOR.createFromParcel(parcelForUserPrefAttr);
        } else {
            mUserPreferencesAttributes = null;
        }

        Parcel parcelForUserOverrAttr = Parcel.obtain();
        com.hp.workpath.api.access.UserOverridesAttributes userOverridesAttributes = in.readParcelable(com.hp.workpath.api.access.UserOverridesAttributes.class.getClassLoader());
        if (userOverridesAttributes != null) {
            userOverridesAttributes.writeToParcel(parcelForUserOverrAttr, 0);
            parcelForUserOverrAttr.setDataPosition(0);
            mUserOverridesAttributes = UserOverridesAttributes.CREATOR.createFromParcel(parcelForUserOverrAttr);
        } else {
            mUserOverridesAttributes = null;
        }
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeSerializable(mAuthenticationType);
        dest.writeString(mFullyQualifiedName);
        dest.writeString(mUserName);
        dest.writeString(mSidString);
        dest.writeString(mDisplayName);
        dest.writeString(mLdapBindUser);
        dest.writeString(mUserPrincipalName);
        dest.writeString(mSAMAccountName);
        dest.writeString(mHomeFolderPath);
        dest.writeString(mUserDomain);
        dest.writeString(mNdsContext);
        dest.writeString(mNdsTreeName);
        dest.writeString(mUserEmail);
        dest.writeString(mExchangeMailboxUri);
        dest.writeString(mPassword);
        dest.writeMap(mUserProperties);
        dest.writeParcelable(mUserPreferencesAttributes, 0);
        dest.writeParcelable(mUserOverridesAttributes, 0);
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
    public static final Parcelable.Creator<AuthenticationAttributes> CREATOR = new Parcelable.Creator<AuthenticationAttributes>() {
        @Override
        public AuthenticationAttributes createFromParcel(final Parcel in) {
            return new AuthenticationAttributes(in);
        }

        @Override
        public AuthenticationAttributes[] newArray(final int size) {
            return new AuthenticationAttributes[size];
        }
    };
}
