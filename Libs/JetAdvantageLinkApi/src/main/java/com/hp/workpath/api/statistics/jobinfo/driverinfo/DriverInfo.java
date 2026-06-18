// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.statistics.jobinfo.driverinfo;

import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides Driver information
 *
 * @since API 5
 */
@DeviceApi
public class DriverInfo {

    private String applicationName;
    private String clientHostName;
    private String fileName;
    private String jobAcct13;
    private String jobAcct14;
    private String jobAcct15;
    private String jobAcct16;
    private String jobId;
    private String userDomain;
    private String userName;

    /**
     * Returns applicationName
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
     * Returns clientHostName Driver information
     *
     * @return clientHostName
     * <p>
     * <ul>
     * <li>Return can be null if the clientHostName is null</li>
     * <li>Return can be null if the clientHostName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getClientHostName() {
        return clientHostName;
    }

    /**
     * Returns fileName Driver information
     *
     * @return fileName
     * <p>
     * <ul>
     * <li>Return can be null if the fileName is null</li>
     * <li>Return can be null if the fileName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns jobAcct13
     * Reserved for use by 3rd party solutions. For IOPrint jobs, this
     * information may be included by a 3rd party driver extension in a PJL attribute at the
     * beginning of the job data (e.g. @PJL SET JOBATTR="JobAcct13=Some String").
     *
     * @return jobAcct13
     * <p>
     * <ul>
     * <li>Return can be null if the jobAcct13 is null</li>
     * <li>Return can be null if the jobAcct13 is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getJobAcct13() {
        return jobAcct13;
    }

    /**
     * Returns jobAcct14
     * Reserved for use by 3rd party solutions. For IOPrint jobs, this
     * information may be included by a 3rd party driver extension in a PJL attribute at the
     * beginning of the job data (e.g. @PJL SET JOBATTR="JobAcct14=Some String").
     *
     * @return jobAcct14
     * <p>
     * <ul>
     * <li>Return can be null if the jobAcct14 is null</li>
     * <li>Return can be null if the jobAcct14 is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getJobAcct14() {
        return jobAcct14;
    }

    /**
     * Returns jobAcct15
     * Reserved for use by 3rd party solutions. For IOPrint jobs, this
     * information may be included by a 3rd party driver extension in a PJL attribute at the
     * beginning of the job data (e.g. @PJL SET JOBATTR="JobAcct15=Some String").
     *
     * @return jobAcct15
     * <p>
     * <ul>
     * <li>Return can be null if the jobAcct15 is null</li>
     * <li>Return can be null if the jobAcct15 is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getJobAcct15() {
        return jobAcct15;
    }

    /**
     * Returns jobAcct16
     * Reserved for use by 3rd party solutions. For IOPrint jobs, this
     * information may be included by a 3rd party driver extension in a PJL attribute at the
     * beginning of the job data (e.g. @PJL SET JOBATTR="JobAcct16=Some String").
     *
     * @return jobAcct16
     * <p>
     * <ul>
     * <li>Return can be null if the jobAcct16 is null</li>
     * <li>Return can be null if the jobAcct16 is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getJobAcct16() {
        return jobAcct16;
    }

    /**
     * Returns jobId for Driver information
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
     * Returns userDomain for Driver information
     *
     * @return userDomain
     * <p>
     * <ul>
     * <li>Return can be null if the userDomain is null</li>
     * <li>Return can be null if the userDomain is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getUserDomain() {
        return userDomain;
    }

    /**
     * Returns userName for Driver information
     *
     * @return userName
     * <p>
     * <ul>
     * <li>Return can be null if the userName is null</li>
     * <li>Return can be null if the userName is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public String getUserName() {
        return userName;
    }
}
