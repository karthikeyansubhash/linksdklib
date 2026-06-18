// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.deviceusage.printer;

import android.support.annotation.Keep;

import com.hp.workpath.api.deviceusage.Plex;
import com.hp.workpath.api.deviceusage.SubUnitInfo;
import com.hp.workpath.api.deviceusage.scanner.ScannerInfo;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;

/**
 * Provides methods to retrieve print job information
 *
 * @since API 5
 */
@DeviceApi
public class PrinterInfo {
    private int mVersion;

    /**
     * Job information by category
     *
     * @since API 5
     */
    @DeviceApi
    public class ByJobCategory {
        @Keep
        private String jobCategory;
        @Keep
        private int a4EquivalentDeciImpressions;

        /**
         * Retrieves Category string of job
         *
         * @return <p>Job Category
         * <ul>
         * <li>Return value will be the category of the jobs e.g: Copy, Fax, Send etc</li>
         * <li>Return value will not be null</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public String getJobCategory() {
            return jobCategory;
        }

        /**
         * Retrieves Category Type of job
         *
         * @return <p>Job category type
         * <ul>
         * <li>Return value will be the category type of the jobs e.g: COPY, FAX, OTHER etc</li>
         * <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.JobCategory}</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public SubUnitInfo.JobCategory getJobCategoryType() {
            for (SubUnitInfo.JobCategory enumValue : SubUnitInfo.JobCategory.values()) {
                if (enumValue.getValue().equals(jobCategory)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.JobCategory.OTHER;
        }

        /**
         * Retrieves a4 Equivalent Deci Impressions
         *
         * @return <p>A4 Equivalent Deci Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getA4EquivalentDeciImpressions() {
            return a4EquivalentDeciImpressions;
        }
    }

    /**
     * Job information by category and mediaSize
     *
     * @since API 5
     */
    @DeviceApi
    public class ByJobCategoryAndMediaSize {
        @Keep
        private String jobCategory;
        @Keep
        private String mediaSize;
        @Keep
        private int impressions;

        /**
         * Retrieves Category string of job
         *
         * @return <p>Job Category
         * <ul>
         * <li>Return value will be the category of the jobs e.g: Copy, Fax, Send etc</li>
         * <li>Return value will not be null</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public String getJobCategory() {
            return jobCategory;
        }

        /**
         * Retrieves Category Type of job
         *
         * @return <p>Job category type
         * <ul>
         * <li>Return value will be the category type of the jobs e.g: COPY, FAX, OTHER etc</li>
         * <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.JobCategory}</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public SubUnitInfo.JobCategory getJobCategoryType() {
            for (SubUnitInfo.JobCategory enumValue : SubUnitInfo.JobCategory.values()) {
                if (enumValue.getValue().equals(jobCategory)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.JobCategory.OTHER;
        }

        /**
         * Retrieves MediaSize string of job
         *
         * @return <p>Media Size
         * <ul>
         * <li>Returns the Media size string for the printer e.g: ANSI_A_8point5x11in, Legal_8point5x14in, Executive_7point25x10point5in etc.</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public String getMediaSize() {
            return mediaSize;
        }

        /**
         * Retrieves MediaSize Type of job
         *
         * @return <p>media size type of the job
         *  <ul>
         *  <li>Return value will be the media size of SubUnitInfo e.g: LETTER, LETTER_ROTATE, LEDGER etc.</li>
         *  <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.MediaSize}.</li>
         *  </ul>
         *  </p>
         * @since API 5
         */
        public SubUnitInfo.MediaSize getMediaSizeType() {
            for (SubUnitInfo.MediaSize enumValue : SubUnitInfo.MediaSize.values()) {
                if (enumValue.getValue().equals(mediaSize)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.MediaSize.OTHER;
        }

        /**
         * Retrieves count of impressions
         *
         * @return <p>Count of impressions
         * <ul>
         * <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getImpressions() {
            return impressions;
        }
    }

    /**
     * Job information by colorMode
     *
     * @since API 5
     */
    @DeviceApi
    public class ByColorMode {
        @Keep
        private String jobCategory;
        @Keep
        private String colorMode;
        @Keep
        private int impressions;

        /**
         * Retrieves Category string of job
         *
         * @return <p>Job Category
         * <ul>
         * <li>Return value will be the category of the jobs e.g: Copy, Fax, Send etc</li>
         * <li>Return value will not be null</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public String getJobCategory() {
            return jobCategory;
        }


        /**
         * Retrieves Color mode string of job
         *
         * @return <p>Color mode
         * <ul>
         * <li>Return value will be the color mode of the jobs e.g: Color or Mono</li>
         * <li>Return value will not be null</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public String getColorMode() {
            return colorMode;
        }

        /**
         * Retrieves Color mode type of job
         *
         * @return <p>Color mode type
         * <ul>
         * <li>Return value will be the {@link com.hp.workpath.api.deviceusage.SubUnitInfo.ColorMode}: MONO or COLOR</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public SubUnitInfo.ColorMode getColorModeType() {
            for (SubUnitInfo.ColorMode enumValue : SubUnitInfo.ColorMode.values()) {
                if (enumValue.getValue().equals(colorMode)) {
                    return enumValue;
                }
            }
            return null;
        }

        /**
         * Retrieves count of impressions
         *
         * @return <p>Count of impressions
         * <ul>
         * <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getImpressions() {
            return impressions;
        }
    }

    /**
     * A4Equivalent impressions of Job information by category
     *
     * @since API 7
     */
    @DeviceApi
    public class A4EquivalentByJobCategory {
        @Keep
        private String jobCategory;
        @Keep
        private int colorDeciImpressions;
        @Keep
        private int monoDeciImpressions;
        @Keep
        private int totalDeciImpressions;

        /**
         * Retrieves Category string of job
         *
         * @return <p>Job Category
         * <ul>
         * <li>Return value will be the category of the jobs e.g: Copy, Fax, Send etc</li>
         * <li>Return value will not be null</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public String getJobCategory() {
            return jobCategory;
        }

        /**
         * Retrieves Category Type of job
         *
         * @return <p>Job category type
         * <ul>
         * <li>Return value will be the category type of the jobs e.g: COPY, FAX, OTHER etc</li>
         * <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.JobCategory}</li>
         * </ul>
         * </p>
         *
         * @since API 5
         */
        public SubUnitInfo.JobCategory getJobCategoryType() {
            for (SubUnitInfo.JobCategory enumValue : SubUnitInfo.JobCategory.values()) {
                if (enumValue.getValue().equals(jobCategory)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.JobCategory.OTHER;
        }

        /**
         * Retrieves Color Deci Impressions
         *
         * @return <p>Color Deci Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getColorDeciImpressions() {
            return colorDeciImpressions;
        }

        /**
         * Retrieves Mono Deci Impressions
         *
         * @return <p>Mono Deci Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getMonoDeciImpressions() {
            return monoDeciImpressions;
        }

        /**
         * Retrieves Total Deci Impressions
         *
         * @return <p>Total Deci Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 5
         */
        public int getTotalDeciImpressions() {
            return totalDeciImpressions;
        }
    }

    /**
     * Print impressions of Job information by mediaSize
     *
     * @since API 7
     */
    @DeviceApi
    public class PrintByMediaSize {
        @Keep
        private String mediaSize;
        @Keep
        private int colorImpressions;
        @Keep
        private int monoImpressions;
        @Keep
        private int totalImpressions;

        /**
         * Retrieves MediaSize string of job
         *
         * @return <p>Media Size
         * <ul>
         * <li>Returns the Media size string for the printer e.g: ANSI_A_8point5x11in, Legal_8point5x14in, Executive_7point25x10point5in etc.</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public String getMediaSize() {
            return mediaSize;
        }

        /**
         * Retrieves MediaSize Type of job
         *
         * @return <p>media size type of the job
         *  <ul>
         *  <li>Return value will be the media size of SubUnitInfo e.g: LETTER, LETTER_ROTATE, LEDGER etc.</li>
         *  <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.MediaSize}.</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public SubUnitInfo.MediaSize getMediaSizeType() {
            for (SubUnitInfo.MediaSize enumValue : SubUnitInfo.MediaSize.values()) {
                if (enumValue.getValue().equals(mediaSize)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.MediaSize.OTHER;
        }


        /**
         * Retrieves Color Impressions
         *
         * @return <p>Color Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getColorImpressions() {
            return colorImpressions;
        }

        /**
         * Retrieves Mono Impressions
         *
         * @return <p>Mono Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getMonoImpressions() {
            return monoImpressions;
        }

        /**
         * Retrieves Total Impressions Count
         *
         * @return <p>Total Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getTotalImpressions() {
            return totalImpressions;
        }
    }

    /**
     * Copy impressions of Job information by mediaSize
     *
     * @since API 7
     */
    @DeviceApi
    public class CopyByMediaSize {
        @Keep
        private String mediaSize;
        @Keep
        private int colorImpressions;
        @Keep
        private int monoImpressions;
        @Keep
        private int totalImpressions;

        /**
         * Retrieves MediaSize string of job
         *
         * @return <p>Media Size
         * <ul>
         * <li>Returns the Media size string for the printer e.g: ANSI_A_8point5x11in, Legal_8point5x14in, Executive_7point25x10point5in etc.</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public String getMediaSize() {
            return mediaSize;
        }

        /**
         * Retrieves MediaSize Type of job
         *
         * @return <p>media size type of the job
         *  <ul>
         *  <li>Return value will be the media size of SubUnitInfo e.g: LETTER, LETTER_ROTATE, LEDGER etc.</li>
         *  <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.MediaSize}.</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public SubUnitInfo.MediaSize getMediaSizeType() {
            for (SubUnitInfo.MediaSize enumValue : SubUnitInfo.MediaSize.values()) {
                if (enumValue.getValue().equals(mediaSize)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.MediaSize.OTHER;
        }

        /**
         * Retrieves Color Impressions
         *
         * @return <p>Color Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getColorImpressions() {
            return colorImpressions;
        }

        /**
         * Retrieves Mono Impressions
         *
         * @return <p>Mono Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getMonoImpressions() {
            return monoImpressions;
        }

        /**
         * Retrieves Total Impressions Count
         *
         * @return <p>Total Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getTotalImpressions() {
            return totalImpressions;
        }
    }

    /**
     * Fax impressions of Job information by mediaSize
     *
     * @since API 7
     */
    @DeviceApi
    public class FaxByMediaSize {
        @Keep
        private String mediaSize;
        @Keep
        private int colorImpressions;
        @Keep
        private int monoImpressions;
        @Keep
        private int totalImpressions;

        /**
         * Retrieves MediaSize string of job
         *
         * @return <p>Media Size
         * <ul>
         * <li>Returns the Media size string for the printer e.g: ANSI_A_8point5x11in, Legal_8point5x14in, Executive_7point25x10point5in etc.</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public String getMediaSize() {
            return mediaSize;
        }

        /**
         * Retrieves MediaSize Type of job
         *
         * @return <p>media size type of the job
         *  <ul>
         *  <li>Return value will be the media size of SubUnitInfo e.g: LETTER, LETTER_ROTATE, LEDGER etc.</li>
         *  <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.MediaSize}.</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public SubUnitInfo.MediaSize getMediaSizeType() {
            for (SubUnitInfo.MediaSize enumValue : SubUnitInfo.MediaSize.values()) {
                if (enumValue.getValue().equals(mediaSize)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.MediaSize.OTHER;
        }

        /**
         * Retrieves Color Impressions
         *
         * @return <p>Color Impressions
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getColorImpressions() {
            return colorImpressions;
        }

        /**
         * Retrieves Mono Impressions
         *
         * @return <p>Mono Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getMonoImpressions() {
            return monoImpressions;
        }

        /**
         * Retrieves Total Impressions Count
         *
         * @return <p>Total Impressions Count
         * <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public int getTotalImpressions() {
            return totalImpressions;
        }
    }

    /**
     * Plex sheets of Job information by mediaSize
     *
     * @since API 7
     */
    @DeviceApi
    public class PlexByMediaSize {
        @Keep
        private String mediaSize;
        @Keep
        private int simplexSheets;
        @Keep
        private int duplexSheets;
        @Keep
        private int totalSheets;

        /**
         * Retrieves MediaSize string of job
         *
         * @return <p>Media Size
         * <ul>
         * <li>Returns the Media size string for the printer e.g: ANSI_A_8point5x11in, Legal_8point5x14in, Executive_7point25x10point5in etc.</li>
         * </ul>
         * </p>
         * @since API 7
         */
        public String getMediaSize() {
            return mediaSize;
        }

        /**
         * Retrieves MediaSize Type of job
         *
         * @return <p>media size type of the job
         *  <ul>
         *  <li>Return value will be the media size of SubUnitInfo e.g: LETTER, LETTER_ROTATE, LEDGER etc.</li>
         *  <li>Return value will be of the type {@link com.hp.workpath.api.deviceusage.SubUnitInfo.MediaSize}.</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public SubUnitInfo.MediaSize getMediaSizeType() {
            for (SubUnitInfo.MediaSize enumValue : SubUnitInfo.MediaSize.values()) {
                if (enumValue.getValue().equals(mediaSize)) {
                    return enumValue;
                }
            }
            return SubUnitInfo.MediaSize.OTHER;
        }


        /**
         * Retrieves simplex sheets of impression
         *
         * @return <p>Simplex sheets impression of the job
         *  <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public int getSimplexSheets() {
            return simplexSheets;
        }

        /**
         * Retrieves duplex sheets of impression
         *
         * @return <p>Duplex sheets impression of the job
         *  <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public int getDuplexSheets() {
            return duplexSheets;
        }

        /**
         * Retrieves total sheets of impression
         *
         * @return <p>Total sheets impression of the job
         *  <ul>
         *  <li>Return value will be greater than or equal to zero</li>
         *  </ul>
         *  </p>
         * @since API 7
         */
        public int getTotalSheets() {
            return totalSheets;
        }
    }

    private int sheets;
    private int engineCycles;
    private Plex[] byPrintPlex;
    private ByJobCategory[] byJobCategory;
    private ByJobCategoryAndMediaSize[] byJobCategoryAndMediaSize;
    private ByColorMode[] byColorMode;
    private A4EquivalentByJobCategory[] a4EquivalentByJobCategory;
    private PrintByMediaSize[] printByMediaSize;
    private CopyByMediaSize[] copyByMediaSize;
    private FaxByMediaSize[] faxByMediaSize;
    private PlexByMediaSize[] plexByMediaSize;

    private PrinterInfo() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    /**
     * Retrieves total count of sheets
     *
     * @return <p>Number of sheets
     * <ul>
     *  <li>Return value will be greater than or equal to zero</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getSheets() {
        return sheets;
    }

    /**
     * Retrieves count of Engine Cycle
     *
     * @return <p>engineCycles
     * <ul>
     *  <li>Return value will be greater than or equal to zero</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public int getEngineCycles() {
        return engineCycles;
    }

    /**
     * Retrieves the plex mode of the print job.
     *
     * @return <p>byPrintPlex array
     * <ul>
     * <li>Return value will be a array of {@link Plex} which contains information on plex and sheets</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Plex[] getByPrintPlex() {
        return byPrintPlex;
    }

    /**
     * Retrieves the information by category.
     *
     * @return <p>byJobCategory array
     * <ul>
     * <li>Return value will be a array of {@link ByJobCategory} which contains information on job category and a4EquivalentDeciImpressions</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public ByJobCategory[] getByJobCategory() {
        return byJobCategory;
    }

    /**
     * Retrieves the information by category and mediaSize.
     *
     * @return <p>ByJobCategoryAndMediaSize array
     * <ul>
     * <li>Return value will be a array of {@link ByJobCategoryAndMediaSize} which contains information on job category, media size and impressions</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public ByJobCategoryAndMediaSize[] getByJobCategoryAndMediaSize() {
        return byJobCategoryAndMediaSize;
    }

    /**
     *  Retrieves the information by color mode.
     *
     * @return <p>ByColorMode array
     * <ul>
     * <li>Return value will be a array of {@link ByColorMode} which contains information on job category, color mode and impressions</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public ByColorMode[] getByColorMode() {
        return byColorMode;
    }


    /**
     *  Retrieves the A4Equivalent information by category.
     *
     * @return <p>ByColorMode array
     * <ul>
     * <li>Return value will be a array of {@link ByColorMode} which contains information on job category, color mode and impressions</li>
     * </ul>
     * </p>
     * @since API 7
     */
    public A4EquivalentByJobCategory[] getA4EquivalentByJobCategory() {
        return a4EquivalentByJobCategory;
    }

    /**
     *  Retrieves the print information by mediaSize.
     *
     * @return <p>printByMediaSize array
     * <ul>
     * <li>Return value will be a array of {@link PrintByMediaSize} which contains information on mediaSize, monoImpressions, colorImpressions and totalImpressions</li>
     * </ul>
     * </p>
     * @since API 7
     */
    public PrintByMediaSize[] getPrintByMediaSize() {
        return printByMediaSize;
    }

    /**
     *  Retrieves the copy information by mediaSize.
     *
     * @return <p>CopyByMediaSize array
     * <ul>
     * <li>Return value will be a array of {@link CopyByMediaSize} which contains information on mediaSize, monoImpressions, colorImpressions and totalImpressions</li>
     * </ul>
     * </p>
     * @since API 7
     */
    public CopyByMediaSize[] getCopyByMediaSize() {
        return copyByMediaSize;
    }

    /**
     *  Retrieves the copy information by mediaSize.
     *
     * @return <p>FaxByMediaSize array
     * <ul>
     * <li>Return value will be a array of {@link FaxByMediaSize} which contains information on mediaSize, monoImpressions, colorImpressions and totalImpressions</li>
     * </ul>
     * </p>
     * @since API 7
     */
    public FaxByMediaSize[] getFaxByMediaSize() {
        return faxByMediaSize;
    }

    /**
     *  Retrieves the plex information by mediaSize.
     *
     * @return <p>PlexByMediaSize array
     * <ul>
     * <li>Return value will be a array of {@link PlexByMediaSize} which contains information on mediaSize, monoImpressions, colorImpressions and totalImpressions</li>
     * </ul>
     * </p>
     * @since API 7
     */
    public PlexByMediaSize[] getPlexByMediaSize() {
        return plexByMediaSize;
    }
}
