// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * Copy Job state details
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class CopyJobState implements JobInfo.JobState {
    /**
     * Copy specific job states
     * @since API 3
     */
    @Keep
    public enum State {
        /**
         * Job is processing.
         *
         * @since API 3
         */
        ACTIVE,
        /**
         * Job was copied successfully.
         *
         * @since API 3
         */
        COMPLETED,
        /**
         * Job was canceled by user or the CancelJob method.
         *
         * @since API 3
         */
        CANCELED,
        /**
         * Job failed prior to completion.
         *
         * @since API 3
         */
        FAILED
    }

    /**
     * Copy specific job sub-states
     * @since API 3
     */
    @Keep
    public enum ActivityState {
        /**
         * Activity is not started.
         *
         * @since API 3
         */
        NOT_STARTED,
        /**
         * Activity is started.
         *
         * @since API 3
         */
        STARTED,
        /**
         * Activity is completed.
         *
         * @since API 3
         */
        COMPLETED
    }

    @Keep
    private State mState;
    @Keep
    private ActivityState mScanningState;
    @Keep
    private ActivityState mProcessingState;
    @Keep
    private ActivityState mPrintingState;
    @Keep
    private ActivityState mCancelingState;

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getB() {
        if(mScanningState == null) {
            mScanningState = ActivityState.NOT_STARTED;
        }
        return mScanningState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getC() {
        if(mProcessingState == null) {
            mProcessingState = ActivityState.NOT_STARTED;
        }
        return mProcessingState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getD() {
        if(mPrintingState == null) {
            mPrintingState = ActivityState.NOT_STARTED;
        }
        return mPrintingState;
    }

    /**
     * State of canceling phase of copy job
     * @return canceling state
     * @since API 3
     */
    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getE() {
        if(mCancelingState == null) {
            mCancelingState = ActivityState.NOT_STARTED;
        }
        return mCancelingState;
    }

    private CopyJobState(Parcel in) {
        mState = (State) in.readSerializable();
        mScanningState = (ActivityState) in.readSerializable();
        mProcessingState = (ActivityState) in.readSerializable();
        mPrintingState = (ActivityState) in.readSerializable();
        mCancelingState = (ActivityState) in.readSerializable();
    }

    /**
     * @hide internal
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide internal
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(mState);
        out.writeSerializable(mScanningState);
        out.writeSerializable(mProcessingState);
        out.writeSerializable(mPrintingState);
        out.writeSerializable(mCancelingState);
    }

    /**
     * @hide internal
     */
    public static final Parcelable.Creator<CopyJobState> CREATOR = new Parcelable.Creator<CopyJobState>() {
        @Override
        public CopyJobState createFromParcel(Parcel in) {
            return new CopyJobState(in);
        }

        @Override
        public CopyJobState[] newArray(int size) {
            return new CopyJobState[size];
        }
    };

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public State getA() {
        return mState;
    }

    /**
     * @hide internal
     */
    @Override
    @Keep
    public String toString() {
        return getClass().getSimpleName() + "[" +
                "state=" + mState +
                ", scanningState=" + mScanningState +
                ", processingState=" + mProcessingState +
                ", printingState=" + mPrintingState +
                ", cancelingState=" + mCancelingState +
                ']';
    }
}