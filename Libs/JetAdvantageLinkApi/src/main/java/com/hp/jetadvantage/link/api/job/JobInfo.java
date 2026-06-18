// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;
import com.hp.workpath.api.job.CopyJobData;
import com.hp.workpath.api.job.PrintJobData;

/**
 * @hide
 */
@Deprecated
public final class JobInfo implements Parcelable {
    /**
     * Defines job types of submitted jobs
     *
     * @since API 1
     */
    @Keep
    public enum JobType {
        /**
         * Print job
         *
         * @since API 1
         */
        PRINT,
        /**
         * Scan job
         *
         * @since API 1
         */
        SCAN,
        /**
         * Copy job
         *
         * @since API 3
         */
        COPY
    }

    /**
     * Provides interface for specific job details
     *
     * @since API 1
     */
    @Keep
    public interface JobData extends Parcelable {

    }

    /**
     * Provides interface for specific job state
     *
     * @since API 1
     */
    @Keep
    public interface JobState extends Parcelable {

    }

    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    @Keep
    private int mVersion;

    /**
     * The job Id for which the Job Info is being provided
     *
     * @since API 1
     */
    @Keep
    private String mJobId;

    /**
     * The type of Job. </br>
     *
     * @since API 1
     */
    @Keep
    private JobType mJobType;

    /**
     * Jobs name, can be null
     *
     * @since API 1
     */
    @Keep
    private String mJobName;

    /**
     * Owner, who has submitted the job
     *
     * @since API 1
     */
    @Keep
    private String mOwner;

    /**
     * UTC start timestamp
     *
     * @since API 1
     */
    @Keep
    private long mStartTime;

    /**
     * UTC complete timestamp. Can hold invalid value before job completed really.
     *
     * @since API 1
     */
    @Keep
    private long mCompleteTime;

    /**
     * Job details specific for job type
     *
     * @since API 1
     */
    @Keep
    private JobData mJobData;

    @Keep
    @Deprecated
    public String getA() {
        return mJobId;
    }

    @Keep
    @Deprecated
    public JobType getB() {
        return mJobType;
    }

    @Keep
    @Deprecated
    public String getC() {
        return mJobName;
    }

    @Keep
    @Deprecated
    public String getD() {
        return mOwner;
    }

    @Keep
    @Deprecated
    public long getE() {
        return mStartTime;
    }

    @Keep
    @Deprecated
    public long getF() {
        return mCompleteTime;
    }

    @Keep
    @Deprecated
    public <T extends JobData> T getG() {
        return (T) mJobData;
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
        parcel.writeInt(mVersion);
        parcel.writeString(mJobId);
        parcel.writeSerializable(mJobType);
        parcel.writeString(mJobName);
        parcel.writeString(mOwner);
        parcel.writeLong(mStartTime);
        parcel.writeLong(mCompleteTime);
        parcel.writeParcelable(mJobData, 0);
    }

    /**
     * Parcelable constructor
     *
     * @param in Parcel to create data from
     */
    @SuppressLint("RestrictedApi")
    private JobInfo(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mJobId = in.readString();
        mJobType = (JobType) in.readSerializable();
        mJobName = in.readString();
        mOwner = in.readString();
        mStartTime = in.readLong();
        mCompleteTime = in.readLong();

        mJobData = in.readParcelable(JobData.class.getClassLoader());
    }

    /**
     * @hide The client should not need to know about the parcelable methods
     */
    public static final Creator<JobInfo> CREATOR = new Creator<JobInfo>() {
        @Override
        public JobInfo createFromParcel(Parcel in) {
            return new JobInfo(in);
        }

        @Override
        public JobInfo[] newArray(int size) {
            return new JobInfo[size];
        }
    };

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    @Keep
    public String toString() {
        return getClass().getSimpleName() + "["
                + "jobId=" + mJobId + ", "
                + "jobName=" + mJobName + ", "
                + "jobType=" + mJobType + ", "
                + "startTime=" + mStartTime + ", "
                + "completeTime=" + mCompleteTime
                + "]";
    }
}
