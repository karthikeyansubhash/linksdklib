// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo;

import android.support.annotation.Keep;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides StatisticsJob information
 *
 * @since API 5
 */
@DeviceApi
public final class StatisticsJobInfo {
    @Keep
    private String jobId;
    @Keep
    private String applicationName;
    @Keep
    private String deviceJobName;
    @Keep
    private StatisticsAttributes.JobCategory jobCategory;
    @Keep
    private StatisticsAttributes.JobDataSource jobDataSource;
    @Keep
    private StatisticsAttributes.JobDestination[] jobDestinations;
    @Keep
    private StatisticsAttributes.JobState jobDoneStatus;
    @Keep
    private Timestamp jobDoneTimestamp;
    @Keep
    private StatisticsAttributes.JobPausedState jobPaused;
    @Keep
    private Timestamp jobStartedTimestamp;
    @Keep
    private String parentJobId;
    @Keep
    private String processedBy;

    /**
     * Returns job id
     *
     * @return jobId
     * <p>
     * <ul>
     * <li>Return can be null if the jobId is null</li>
     * <li>Return can be null if the jobId is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Returns application name
     *
     * @return applicationName
     * <p>
     * <ul>
     * <li>Return can be null if the applicationName is null</li>
     * <li>Return can be null if the applicationName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Returns device job name
     *
     * @return deviceJobName
     * <p>
     * <ul>
     * <li>Return can be null if the deviceJobName is null</li>
     * <li>Return can be null if the deviceJobName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getDeviceJobName() {
        return deviceJobName;
    }

    /**
     * Returns job category
     *
     * @return jobCategory
     * <p>
     * <ul>
     * <li>Return can be null if the jobCategory is null</li>
     * <li>Return can be null if the jobCategory is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.JobCategory getJobCategory() {
        return jobCategory;
    }

    /**
     * Returns job data source
     *
     * @return jobDataSource
     * <p>
     * <ul>
     * <li>Return can be null if the jobDataSource is null</li>
     * <li>Return can be null if the jobDataSource is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.JobDataSource getJobDataSource() {
        return jobDataSource;
    }

    /**
     * Retrieves job destinations
     *
     * @return jobDestinations
     * <p>
     * <ul>
     * <li>if {@link com.hp.workpath.api.statistics.jobinfo.StatisticsAttributes.JobDestination} field is not added to JobDestination array list, the list should be empty.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.JobDestination[] getJobDestinations() {
        return jobDestinations;
    }

    /**
     * Returns job done status
     *
     * @return jobDoneStatus
     * <p>
     * <ul>
     * <li>Return can be null if the jobDoneStatus is null</li>
     * <li>Return can be null if the jobDoneStatus is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.JobState getJobDoneStatus() {
        return jobDoneStatus;
    }

    /**
     * Returns job done timestamp
     *
     * @return jobDoneTimestamp
     * <p>
     * <ul>
     * <li>Return can be null if the jobDoneTimestamp is null</li>
     * <li>Return can be null if the jobDoneTimestamp is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Timestamp getJobDoneTimestamp() {
        return jobDoneTimestamp;
    }

    /**
     * Returns job paused
     *
     * @return jobPaused
     * <p>
     * <ul>
     * <li>Return can be null if the jobPaused is null</li>
     * <li>Return can be null if the jobPaused is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public StatisticsAttributes.JobPausedState getJobPaused() {
        return jobPaused;
    }

    /**
     * Returns job started timestamp
     *
     * @return jobStartedTimestamp
     * <p>
     * <ul>
     * <li>Return can be null if the jobStartedTimestamp is null</li>
     * <li>Return can be null if the jobStartedTimestamp is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Timestamp getJobStartedTimestamp() {
        return jobStartedTimestamp;
    }

    /**
     * Returns parent job id
     *
     * @return parentJobId
     * <p>
     * <ul>
     * <li>Return can be null if the parentJobId is null</li>
     * <li>Return can be null if the parentJobId is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getParentJobId() {
        return parentJobId;
    }

    /**
     * Returns processedBy
     *
     * @return processedBy
     * <p>
     * <ul>
     * <li>Return can be null if the processedBy is null</li>
     * <li>Return can be null if the processedBy is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getProcessedBy() {
        return processedBy;
    }
}
