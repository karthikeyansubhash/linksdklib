// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;

/**
 * <p>The sets of attributes to determine behavior to use a built-in UI of discovery.
 * An instance of this class is created using {@link Builder}
 * <br/>
 * This class can be used to decide Discovery UI page option when discovery process is started.
 * In default, Link SDK Service will provide a built-in UI which includes the menu for showing discovered devices.
 * If a client wants to use its own UI, it needs to set the showDiscoveryUi option as false.</p>
 *
 * @since API 1
 */
@SuppressWarnings({"WeakerAccess"})
public class DiscoveryletAttributes implements Parcelable {
    @MobileApi
    final int mVersion;
    @MobileApi
    final boolean mShowDiscoveryUi;

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the parcelable methods.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>letAttributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(Sdk.VERSION.LEVEL);
        out.writeByte((byte) (mShowDiscoveryUi ? 1 : 0));
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<DiscoveryletAttributes> CREATOR = new Creator<DiscoveryletAttributes>() {
        public DiscoveryletAttributes createFromParcel(final Parcel in) {
            return new DiscoveryletAttributes(in);
        }

        public DiscoveryletAttributes[] newArray(final int size) {
            return new DiscoveryletAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private DiscoveryletAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mShowDiscoveryUi = in.readByte() != 0;
    }

    private DiscoveryletAttributes(final Builder builder) {
        mVersion = Sdk.VERSION.LEVEL;
        mShowDiscoveryUi = builder.mShowDiscoveryUi;
    }

    /**
     * @return True, if Discovery UI will be shown. Otherwise, false.
     * @hide not open API
     * Indicates whether a discovery UI should appear prior to sending to the printer.
     */
    @SuppressWarnings({"unused"})
    public boolean getShowDiscoveryUI() {
        return mShowDiscoveryUi;
    }

    /**
     * @return of the Xlet attributes
     * @hide not open API
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * <p>Builder for creating an instance of {@link DiscoveryletAttributes}.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static class Builder {
        boolean mShowDiscoveryUi = true;

        /**
         * <p>Default constructor to create a new Builder with default attributes.<br/>
         *     Show Discovery UI = true <br/>
         * </p>
         *
         * @since API 1
         */
        public Builder() {
            // Default constructor
        }

        /**
         * <p>Sets the option to use built-in page for starting discovery.</p>
         *
         * @param visibility If TRUE, built-in discovery dialog shows.
         * @return Builder builder with showDiscoveryUi option
         *
         * @since API 1
         */
        public Builder setShowDiscoveryUi(final boolean visibility) {
            this.mShowDiscoveryUi = visibility;
            return this;
        }

        /**
         * <p>Builds DiscoveryletAttributes. All of the attributes combine into a {@link DiscoveryletAttributes} object.</p>
         *
         * @return DiscoveryletAttributes object containing all the attributes.
         * @since API 1
         */
        public DiscoveryletAttributes build() {
            return new DiscoveryletAttributes(this);
        }
    }
}
