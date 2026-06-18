// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.job;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

import com.hp.workpath.api.Convertor;

/**
 * Enumerations of the state for print Job
 *
 * @since API 1
 */
public class PrintJobState implements JobInfo.JobState, Convertor {
    /**
     * The states of print job
     * @since API 1
     */
    @Keep
    public enum State {
        /**
         * Job is processing.
         *
         * @since API 1
         */
        PROCESSING,
        /**
         * Job was printed successfully.
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
         * Job is aborted.
         *
         * @since API 1
         */
        ABORTED,
        /**
         * Job processing is stopped.
         *
         * @since API 1
         */
        STOPPED,
        /**
         * Job is still active (not yet complete).
         *
         * @since API 1
         */
        PENDING,
        /**
         * Job is created, then it's held. It will not go to processing without release.
         *
         * @since API 1
         */
        HELD
    }

    @Keep
    private State mState;

    /**
     * Constructor
     * @param state of job
     * @hide client should not create this class, only read received data
     */
    public PrintJobState(final State state) {
        this.mState = state;
    }

    /**
     * Returns The state of print job
     * @return The current state of print job
     * @since API 1
     */
    @Keep
    public State getState() {
        return mState;
    }

    private PrintJobState(Parcel in) {
        mState = (State) in.readSerializable();
    }

    private PrintJobState(Object object) {
        if(object instanceof com.hp.jetadvantage.link.api.job.PrintJobState) {
            mState = State.valueOf(((com.hp.jetadvantage.link.api.job.PrintJobState) object).getA().name());
        }
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
    }

    /**
     * @hide internal
     */
    public static final Parcelable.Creator<PrintJobState> CREATOR = new Parcelable.Creator<PrintJobState>() {
        @Override
        public PrintJobState createFromParcel(Parcel in) {
            return new PrintJobState(in);
        }

        @Override
        public PrintJobState[] newArray(int size) {
            return new PrintJobState[size];
        }
    };

    /**
     * @hide trivial
     */
    public static final ConvertorCreator<PrintJobState> CREATOR_OBJ = new ConvertorCreator<PrintJobState>() {
        @Override
        public PrintJobState createFromObject(Object object) {
            return new PrintJobState(object);
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
                ']';
    }
}
