// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.os.Parcel;
import android.support.annotation.Keep;

/**
 * Enumerations of the state for scan Job
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class ScanJobState implements JobInfo.JobState {
    /**
     * Returns the state of scan job
     * @since API 1
     */
    @Keep
    public enum State {
        /**
         * Job is still active (not yet complete).
         *
         * @since API 1
         */
        PENDING,
        /**
         * Job was scanned and delivered successfully.
         *
         * @since API 1
         */
        COMPLETED,
        /**
         * Job was canceled by user or the CancelJob method.
         *
         * @since API 1
         */
        CANCELED,
        /**
         * Job failed prior to completion.
         *
         * @since API 1
         */
        FAILED
    }

    /**
     * Returns the state of scan activity.
     * @since API 1
     */
    @Keep
    public enum ActivityState {
        /**
         * Activity is not started.
         *
         * @since API 1
         */
        NOT_STARTED,
        /**
         * Activity is started.
         *
         * @since API 1
         */
        STARTED,
        /**
         * Activity restarted.
         *
         * @since API 1
         */
        RESTARTED,
        /**
         * Activity is completed.
         *
         * @since API 1
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
    private ActivityState mTransmittingState;
    @Keep
    private ActivityState mCancelingState;

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getA() {
        if(mScanningState == null) {
            mScanningState = ActivityState.NOT_STARTED;
        }
        return mScanningState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getB() {
        if(mProcessingState == null) {
            mProcessingState = ActivityState.NOT_STARTED;
        }
        return mProcessingState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getC() {
        if(mTransmittingState == null) {
            mTransmittingState = ActivityState.NOT_STARTED;
        }
        return mTransmittingState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public ActivityState getD() {
        if(mCancelingState == null) {
            mCancelingState = ActivityState.NOT_STARTED;
        }
        return mCancelingState;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public State getE() {
        return mState;
    }

    private ScanJobState(Parcel in) {
        mState = (State) in.readSerializable();
        mScanningState = (ActivityState) in.readSerializable();
        mProcessingState = (ActivityState) in.readSerializable();
        mTransmittingState = (ActivityState) in.readSerializable();
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
        out.writeSerializable(mTransmittingState);
        out.writeSerializable(mCancelingState);
    }

    /**
     * @hide internal
     */
    public static final Creator<ScanJobState> CREATOR = new Creator<ScanJobState>() {
        @Override
        public ScanJobState createFromParcel(Parcel in) {
            return new ScanJobState(in);
        }

        @Override
        public ScanJobState[] newArray(int size) {
            return new ScanJobState[size];
        }
    };

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
                ", transmittingState=" + mTransmittingState +
                ", cancelingState=" + mCancelingState +
                ']';
    }
}
