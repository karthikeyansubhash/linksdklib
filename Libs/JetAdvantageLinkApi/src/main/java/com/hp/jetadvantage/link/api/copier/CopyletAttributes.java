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
 * Attributes to determine behavior after submitting copy job.
 * An instance of this class is created using {@link //Builder}
 * <br/>
 * It can be used to configure displaying Settings UI page after submitting a job.
 * If {@link //CopierService#submit(Context, CopyAttributes, CopyletAttributes)} is called with
 * {@link CopyletAttributes} instance created by the following code, built-in Settings UI page will
 * be shown. This page lets user select each job parameter from the list of supported values.
 * <br/>
 * <script src=
 * "https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"
 * ></script>
 * <pre class="prettyprint" style="word-wrap: break-word; white-space: pre-wrap; white-space:-moz-pre-wrap; white-space:-pre-wrap; white-space:-o-pre-wrap; word-break:break-all;">
 * CopyletAttributes jobAttributes = new CopyletAttributes.Builder().setShowSettingsUi(true).build();
 * </pre>
 *
 * @hide
 * @since API 3
 */
@SuppressWarnings({"WeakerAccess"})
@Deprecated
public class CopyletAttributes implements Parcelable {
    @Keep
    final int mVersion;
    @Keep
    final boolean mShowSettingsUi;
    @Keep
    final boolean mShowCredentialsUi;

    /**
     * @hide The client should not need to know about the scanlet parcelable methods.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the scanlet parcelable methods.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>letAttributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(Sdk.VERSION.LEVEL);
        out.writeByte((byte) (mShowSettingsUi ? 1 : 0));
        out.writeByte((byte) (mShowCredentialsUi ? 1 : 0));
    }

    /**
     * @hide The client should not need to know about the scanlet parcelable methods
     */
    public static final Creator<CopyletAttributes> CREATOR = new Creator<CopyletAttributes>() {
        public CopyletAttributes createFromParcel(final Parcel in) {
            return new CopyletAttributes(in);
        }

        public CopyletAttributes[] newArray(final int size) {
            return new CopyletAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private CopyletAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mShowSettingsUi = in.readByte() != 0;
        mShowCredentialsUi = in.readByte() != 0;
    }

    private CopyletAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mShowSettingsUi = builder.mShowSettingsUi;
        mShowCredentialsUi = builder.mShowCredentialsUi;
    }

    /**
     * Builder for creating an instance of {@link CopyletAttributes}.
     *
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    @Keep
    public static class Builder {
        boolean mShowSettingsUi = false;
        boolean mShowCredentialsUi = true;

        /**
         * Construct a new Builder with default attributes.<br>
         * <br>
         * Show Settings UI = false <br>
         * <br>
         *
         * @since API 3
         */
        public Builder() {
        }

        @Deprecated
        public CopyletAttributes Z() {
            return new CopyletAttributes(this);
        }
    }
}
