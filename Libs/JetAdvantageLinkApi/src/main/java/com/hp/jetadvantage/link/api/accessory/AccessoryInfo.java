// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.accessory;

import android.os.Parcelable;

/**
 * Container for accessory details.
 *
 * @hide
 * @since API 3
 */
@Deprecated
public abstract class AccessoryInfo implements Parcelable {
    public enum AccessoryClass {
        UNSUPPORTED,
        CCID,
        HID,
        MASS_STORAGE
    }

    public abstract AccessoryClass getAccessoryClass();

    @Deprecated
    public abstract RegistrationType getRegistrationType();

    public <T extends AccessoryInfo> T getDetails() {
        return (T) this;
    }
}
