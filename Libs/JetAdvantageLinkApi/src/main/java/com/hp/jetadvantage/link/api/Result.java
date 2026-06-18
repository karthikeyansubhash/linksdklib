// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api;

/**
 * <p>Sets of result details of SDK API on various operations. SDK strongly recommends to check result after requesting operation.</p>
 * An application can use this class of these cases :
 * <ul>
 *     <li>To know operation is completed successfully or not</li>
 *     <li>To know the reason and error detail for failure</li>
 * </ul>
 *
 * @hide
 * @since API 1
 */
@SuppressWarnings({"WeakerAccess"})
@Deprecated
public class Result {
    /**
     * @hide
     */
    public static final String KEY_RESULT = "result";

    /**
     * @hide
     */
    public static final String KEY_CODE = "resultCode";

    /**
     * @hide
     */
    public static final String KEY_ERROR_CODE = "resultErrorCode";

    /**
     * @hide
     */
    public static final String KEY_CAUSE = "resultCause";

    private int mCode;

    private String mCause;

    private ErrorCode mErrorCode;

    /**
     * @hide
     */
    @Deprecated
    public enum SpsCauseKind {
        /**
         * Job creation kind of cause
         *
         * @since API 1
         */
        CREATION,
        /**
         * Job processing kind of cause
         *
         * @since API 1
         */
        PROCESSING,
        /**
         * Job operation (like cancel etc.) kind of cause
         *
         * @since API 1
         */
        OPERATION
    }

    @Deprecated
    public static final int RESULT_OK = -1;

    @Deprecated
    public static final int RESULT_FAIL = 0;

    @Deprecated
    public enum ErrorCode {
        /**
         * <p>Occurs error when parameters in request include data of invalid format or unsupported attribute.</p>
         *
         * @since API 1
         */
        INVALID_PARAM,

        /**
         * <p>Occurs error when SDK can not query because of connection error.</p>
         *
         * @since API 1
         */
        CONNECTION_ERROR,

        /**
         * <p>Occurs error when SDK can not complete the operation because of internal error.</p>
         *
         * @since API 1
         */
        SERVICE_ERROR,

        /**
         * <p>Occurs error when job is reported as fail by a printer.</p>
         *
         * @since API 1
         */
        JOB_FAILURE,

        /**
         * <p>Occurs error when operation is not permitted because of insufficient authentication.</p>
         *
         * @since API 1
         */
        AUTHENTICATION_ERROR,

        /**
         * <p>Occurs error because an application doesn't have the right permission to call SDK.</p>
         *
         * @since API 1
         */
        UNAUTHORIZED,

        /**
         * <p>Occurs error with unexpected reason.</p>
         *
         * @since API 1
         */
        UNKNOWN,

        /**
         * <p>Occurs error when operation is not supported by SDK.</p>
         *
         * @since API 1
         */
        NOT_SUPPORTED,

        /**
         * <p>Occurs error when device is in system issue while operating.</p>
         *
         * @since API 1
         */
        SYSTEM_ERROR,

        /**
         * <p>Occurs error when operation is not available by SDK.</p>
         *
         * @since API 9
         */
        UNAVAILABLE
    }
}
