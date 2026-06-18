// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.access;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Provides the sets of sign-in actions and failure message to complete authentication process.
 *
 * @hide
 * @since API 2
 */
@Deprecated
public class SignInAction implements Parcelable {

    private Action action;
    private String failureMessage;

    private SignInAction(Parcel in) {
        action = Action.valueOf(((com.hp.workpath.api.access.SignInAction.Action)in.readSerializable()).name());
        failureMessage = in.readString();
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(action);
        dest.writeString(failureMessage);
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
    public static final Parcelable.Creator<SignInAction> CREATOR = new Parcelable.Creator<SignInAction>() {
        @Override
        public SignInAction createFromParcel(final Parcel in) {
            return new SignInAction(in);
        }

        @Override
        public SignInAction[] newArray(final int size) {
            return new SignInAction[size];
        }
    };

    public enum Action {
        SUCCESS,
        CONTINUE,
        FAIL,
        CANCEL,
        HOME,
        BACK
    }
}
