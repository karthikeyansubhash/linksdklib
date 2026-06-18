// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.scanner;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.PaperSize;
import com.hp.workpath.common.Sdk;

/**
 * The sets of attributes for requesting a scan.<br/>
 * An instance of this class is created using one of the builders.
 *
 * @since API 1
 * @hide
 */
@Deprecated
public final class ScanAttributes implements Parcelable {
    /**
     * A collection of ColorMode.
     *
     * @since API 1
     */
    public enum ColorMode {
        /**
         * Color mode default from the device.
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * If the job consists of a mixture of Black and Color images (side of a sheet), the device will detect the content of each image and generate appropriate output.
         *
         * @since API 1
         */
        AUTO,

        /**
         * Color
         *
         * @since API 1
         */
        COLOR,

        /**
         * Gray
         *
         * @since API 1
         */
        GRAY,

        /**
         * Black and White.
         *
         * @since API 1
         */
        MONO
    }

    /**
     * A collection of duplex mode
     *
     * @since API 1
     */
    public enum Duplex {
        /**
         * Default duplex settings configured on the device.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * No duplex
         *
         * @since API 1
         */
        NONE,
        /**
         * Duplex along the long edge of the paper.
         *
         * @since API 1
         */
        BOOK,
        /**
         * Duplex along the short edge of the paper.
         *
         * @since API 1
         */
        FLIP
    }

    /**
     * A collection of original Orientation options.
     *
     * @since API 1
     */
    public enum Orientation {
        /**
         * Default original orientation settings configured on the device.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Portrait orientation of a page
         *
         * @since API 1
         */
        PORTRAIT,
        /**
         * Landscape orientation of a page
         *
         * @since API 1
         */
        LANDSCAPE,
        /**
         * AutoDetect
         *
         * @since API 5
         */
        AUTO_DETECT
    }

    /**
     * A collection of destinations for scanning.
     *
     * @since API 1
     */
    public enum Destination {
        /**
         * Me : Scanned document is stored in local storage.
         *
         * @since API 1
         */
        ME,

        /**
         * Http : Scanned document is sent to server through http.
         *
         * @since API 1
         */
        HTTP,

        /**
         * Ftp : Scanned document is sent to server through ftp.
         *
         * @since API 1
         */
        FTP,

        /**
         * Network folder : Scanned document is stored in network folder.
         *
         * @since API 1
         */
        NETWORK_FOLDER,

        /**
         * Email : Scanned document is sent by email as attachment.
         *
         * @deviceOnly
         * @since API 1
         */
        EMAIL,

        /**
         * USB : Scanned document is stored in USB
         *
         * @deviceOnly
         * @since API 2
         */
        USB
    }

    /**
     * A collection of document formats for scanning.
     *
     * @since API 1
     */
    public enum DocumentFormat {
        /**
         * Document format default from the device.
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * JPEG file type
         *
         * @since API 1
         */
        JPEG,

        /**
         * PDF file type
         *
         * @since API 1
         */
        PDF,

        /**
         * TIFF
         * Each image (side of a sheet) is provided as a separate TIFF file
         *
         * @since API 1
         */
        TIFF,

        /**
         * MTIFF
         * Multiple TIFF images in a single TIFF file
         *
         * @since API 1
         */
        MTIFF,

        /**
         * PDF with OCR text
         *
         * @since API 1
         */
        OCR_PDF_TEXT_UNDER_IMAGE,

        /**
         * PDF/A with OCR text
         *
         * @since API 1
         */
        OCR_PDF_A_TEXT_UNDER_IMAGE,

        /**
         * XPS with OCR text
         *
         * @since API 1
         */
        OCR_XPS_TEXT_UNDER_IMAGE,

        /**
         * CSV with OCR
         *
         * @since API 1
         */
        OCR_CSV,

        /**
         * HTML with OCR
         *
         * @since API 1
         */
        OCR_HTML,

        /**
         * RTF with OCR
         *
         * @since API 1
         */
        OCR_RTF,

        /**
         * Text with OCR
         *
         * @since API 1
         */
        OCR_TEXT,

        /**
         * Unicode text with OCR
         *
         * @since API 1
         */
        OCR_UNICODE_TEXT,

        /**
         * XML with OCR
         *
         * @since API 1
         */
        OCR_XML,

        /**
         * PDF_A
         * ISO-standardized version of the Portable Document Format (PDF)
         *
         * @since API 1
         */
        PDF_A,

        /**
         * XPS
         * Open xml paper specification
         *
         * @since API 1
         */
        XPS
    }

    /**
     * A collection of resolution types for scanning.
     *
     * @since API 1
     */
    public enum Resolution {
        /**
         * Default value from the device.
         *
         * @since API 1
         */
        DEFAULT,

        DPI_75,
        DPI_100,
        DPI_150,
        DPI_200,
        DPI_240,
        DPI_300,
        DPI_400,
        DPI_500,
        DPI_600;

        public static Resolution valueOf(int dpi) {
            if (dpi == 75) {
                return DPI_75;
            } else if (dpi == 100) {
                return DPI_100;
            } else if (dpi == 150) {
                return DPI_150;
            } else if (dpi == 200) {
                return DPI_200;
            } else if (dpi == 240) {
                return DPI_240;
            } else if (dpi == 300) {
                return DPI_300;
            } else if (dpi == 400) {
                return DPI_400;
            } else if (dpi == 500) {
                return DPI_500;
            } else if (dpi == 600) {
                return DPI_600;
            }
            return DEFAULT;
        }
    }

    /**
     * The scan size (i.e., size of the original) for scanning.<br>
     * <p>
     * This attribute is relative to the orientation parameter of scan.
     *
     * @since API 1
     */
    public enum ScanSize {
        /**
         * Default scanning size from printer.
         * If the printer supports auto-detection, it will be used.
         *
         * @since API 1
         */
        DEFAULT("Default"),

        /**
         * ISO A3 (297mm x 420mm)
         *
         * @since API 1
         */
        A3("A3"),

        /**
         * ISO A4 Rotated (297mm x 210mm)
         *
         * @since API 1
         */
        A4_ROTATE("A4R"),

        /**
         * ISO A4 (210mm x 297mm)
         *
         * @since API 1
         */
        A4("A4"),

        /**
         * ISO A5 Rotated (210mm x 148mm)
         *
         * @since API 1
         */
        A5_ROTATE("A5R"),

        /**
         * ISO/JIS A5 (148mm x 210mm)
         *
         * @since API 1
         */
        A5("A5"),

        /**
         * ISO A6 Rotated (148mm x 105mm)
         *
         * @since API 1
         */
        A6_ROTATE("A6R"),

        /**
         * ISO A6 (105mm x 148mm)
         *
         * @since API 1
         */
        A6("A6"),

        /**
         * ISO B4 (250mm x 353mm)
         *
         * @since API 1
         */
        B4("B4"),

        /**
         * ISO B5 Rotated (250mm x 176mm)
         *
         * @since API 1
         */
        B5_ROTATE("B5R"),

        /**
         * ISO B5 (176mm x 250mm)
         *
         * @since API 1
         */
        B5("B5"),

        /**
         * ISO B6 (125mm x 176mm)
         *
         * @since API 1
         */
        B6("B6"),

        /**
         * JBusiness Card (55mm x 91mm)
         *
         * @since API 1
         */
        BUSINESS_CARD("BusinessCard"),

        /**
         * Letter Rotated (11inch x 8.5inch)
         *
         * @since API 1
         */
        LETTER_ROTATE("LetterR"),

        /**
         * Letter (8.5inch x 11inch)
         *
         * @since API 1
         */
        LETTER("Letter"),

        /**
         * JIS B4 (257mm x 364mm)
         *
         * @since API 1
         */
        JB4("JB4"),

        /**
         * JIS B5 Rotated (257mm x 182mm)
         *
         * @since API 1
         */
        JB5_ROTATE("JB5R"),

        /**
         * JIS B5 (182mm x 257mm)
         *
         * @since API 1
         */
        JB5("JB5"),

        /**
         * JIS B6 (128mm x 182mm)
         *
         * @since API 1
         */
        JB6("JB6"),

        /**
         * Legal (8.5inch x 14inch)
         *
         * @since API 1
         */
        LEGAL("Legal"),

        /**
         * Ledger (11inch x 17inch)
         *
         * @since API 1
         */
        LEDGER("Ledger"),

        /**
         * Executive (7.25inch x 10.5inch)
         *
         * @since API 1
         */
        EXECUTIVE("Executive"),

        /**
         * Statement (5.5inch x 8.5inch)
         *
         * @since API 1
         */
        STATEMENT("Statement"),

        /**
         * Statement Rotated (8.5inch x 5.5inch)
         *
         * @since API 1
         */
        STATEMENT_ROTATE("StatementR"),

        /**
         * Custom scan size
         *
         * @since API 1
         */
        CUSTOM("Custom"),

        /**
         * K8 (270mm x 390mm)
         *
         * @since API 1
         */
        K8("K8"),

        /**
         * K16 (195mm x 270mm)
         *
         * @since API 1
         */
        K16("K16"),

        /**
         * PRC 8K (273mm x 394mm)
         *
         * @since API 1
         */
        PK8("PK8"),

        /**
         * PRC 16K (197mm x 273mm)
         *
         * @since API 1
         */
        PK16("PK16"),

        /**
         * INCH8POINT5X13 (8.5inch x 13inch)
         *
         * @since API 1
         */
        INCH8POINT5X13("Inch8Point5x13"),

        /**
         * INCH12X18 (12inch x 18inch)
         *
         * @since API 1
         */
        INCH12X18("Inch12x18"),

        /**
         * Mixed Letter and Legal
         *
         * @since API 1
         */
        MIXEDLETTERLEGAL("MixedLetterLegal"),

        /**
         * Mixed A3 and A4
         *
         * @since API 1
         */
        MIXEDA3A4("MixedA3A4"),

        /**
         * Auto detected size for scan (Any)
         * A meta-type used to indicate auto-select
         *
         * @since API 1
         */
        AUTO("Any"),

        /**
         * Envelope B5 (176mm x 250mm)
         *
         * @since API 5
         */
        ENVELOPE_B5("EnvelopeB5"),

        /**
         * Envelope 9 (3.875inch x 8.875inch)
         *
         * @since API 5
         */
        ENVELOPE_9("Envelope9"),

        /**
         * Envelope Comm10 (4.125inch x 9.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM10("EnvelopeComm10"),

        /**
         * Envelope Monarch (3.875inch x 7.5inch)
         *
         * @since API 5
         */
        ENVELOPE_MONARCH("EnvelopeMonarch"),

        /**
         * ISO C5 (162mm x 229mm)
         *
         * @since API 5
         */
        C5("C5"),

        /**
         * ISO C6 (114mm x 162mm)
         *
         * @since API 5
         */
        C6("C6"),

        /**
         * Envelope DL (110mm x 220mm)
         *
         * @since API 5
         */
        ENVELOPE_DL("EnvelopeDL"),

        /**
         * JIS Chou3 (120mm x 235mm)
         *
         * @since API 5
         */
        JCHOU3("JChou3"),

        /**
         * JIS Chou4 (90mm x 205mm)
         *
         * @since API 5
         */
        JCHOU4("JChou4"),

        /**
         * Unknown Envelope
         *
         * @since API 5
         */
        UNKNOWN_ENVELOP("UnknownEnvelope"),

        /**
         * Japanese Double Postcard (148mm x 200mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD("JDoublePostcard"),

        /**
         * Japanese Postcard (100mm x 148mm)
         *
         * @since API 5
         */
        JPOSTCARD("JPostcard"),

        /**
         * K8_260X368mm (260mm x 368mm)
         *
         * @since API 5
         */
        K8_260X368mm("K8_260x368mm"),

        /**
         * K16_184X260mm (184mm x 260 mm)
         *
         * @since API 5
         */
        K16_184X260mm("K16_184x260mm"),

        /**
         * Mixed Letter and Ledger
         *
         * @since API 5
         */
        MIXEDLETTERLEDGER("MixedLetterLedger"),

        /**
         * Mixed Original
         *
         * @since API 5
         */
        MIXEDORIGINAL("MixedOriginal"),

        /**
         * Oficio (216mm x 340mm)
         *
         * @since API 5
         */
        OFICIO("Oficio"),

        /**
         * RA3 (305mm x 430mm)
         *
         * @since API 5
         */
        RA3("RA3"),

        /**
         * RA4 (215mm x 305mm)
         *
         * @since API 5
         */
        RA4("RA4"),

        /**
         * GENERAL_10X15cm (10cm x 15cm)
         *
         * @since API 5
         */
        GENERAL_10X15cm("General10x15cm"),

        /**
         * GENERAL_12X18in (12inch x 18inch)
         *
         * @since API 5
         */
        GENERAL_12X18in("General12x18in"),

        /**
         * GENERAL_3X5in (3inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3X5in("General3x5in"),

        /**
         * GENERAL_4X6in (4inch x 6inch)
         *
         * @since API 5
         */
        GENERAL_4X6in("General4x6in"),

        /**
         * GENERAL_5X7in (5inch x 7inch)
         *
         * @since API 5
         */
        GENERAL_5X7in("General5x7in"),

        /**
         * GENERAL_5X8in (5inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_5X8in("General5x8in"),

        /**
         * GENERAL_8POINT5X13in (8.5inch x 13inch)
         *
         * @since API 5
         */
        GENERAL_8POINT5X13in("General8Point5x13in"),

        /**
         * GENERAL_8POINT5X34in (8.5inch x 34inch)
         *
         * @since API 5
         */
        GENERAL_8POINT5X34in("General8point5x34in"),

        /**
         * GENERAL L (9cm x 13cm)
         *
         * @since API 5
         */
        GENERAL_L_9X13cm("GeneralL9x13cm"),

        /**
         * SRA3 (320mm x 450mm)
         *
         * @since API 5
         */
        SRA3("Sra3"),

        /**
         * SRA4 (225mm x 320mm)
         *
         * @since API 5
         */
        SRA4("Sra4"),

        /**
         * Unknown
         *
         * @since API 5
         */
        UNKNOWN("Unknown");

        private final String scanSize;

        ScanSize(final String scanSize) {
            this.scanSize = scanSize;
        }

        /**
         * @hide trivial
         */
        @Override
        public String toString() {
            return scanSize;
        }

        /**
         * @hide This is for advanced
         */
        public static ScanSize getScanSize(final String jsonStr) {
            if (jsonStr != null) {
                for (ScanSize ss : ScanSize.values()) {
                    if (jsonStr.toLowerCase().equalsIgnoreCase(ss.scanSize.toLowerCase())) {
                        return ss;
                    }
                }
            }

            return ScanSize.DEFAULT;
        }

        /**
         * Retrieves the height of paper
         *
         * @return <p>supported height from {@link ScanSize}.
         * <ul>
         * <li>Return the supported height</li>
         * <li>Return can be 0.0 if PaperSize height is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getHeight() {
            for (PaperSize ps : PaperSize.values()) {
                if (getScanSize(this.scanSize).name().equals(ps.name())) {
                    return ps.getHeight();
                }
            }
            return 0;
        }

        /**
         * Retrieves the width of paper
         *
         * @return <p>supported width from {@link ScanSize}.
         * <ul>
         * <li>Return the supported width</li>
         * <li>Return can be 0.0 if PaperSize width is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getWidth() {
            for (PaperSize ps : PaperSize.values()) {
                if (getScanSize(this.scanSize).name().equals(ps.name())) {
                    return ps.getWidth();
                }
            }
            return 0;
        }

        /**
         * Retrieves the unit of paper
         *
         * @return <p>supported unit from {@link ScanSize}.
         * <ul>
         * <li>Return the supported unit (mm or inch or cm) according to paper size.</li>
         * <li>Return can be null if PaperSize unit is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public String getUnit() {
            for (PaperSize ps : PaperSize.values()) {
                if (getScanSize(this.scanSize).name().equals(ps.name())) {
                    return ps.getUnit();
                }
            }
            return null;
        }
    }

    /**
     * <p>Scan Preview controls for showing a preview of scanned images and requesting confirmation before completing the scan.</p>
     *
     * @deviceOnly
     * @since API 1
     */
    public enum ScanPreview {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Enable Scan Preview.
         * After starting a scan, the device will show the pre-scanned document to display preview
         * to confirm the document from user.
         *
         * @since API 1
         */
        TRUE,
        /**
         * Disable Scan Preview.
         * No preview will be while scanning
         *
         * @since API 1
         */
        FALSE
    }

    /**
     * <p>Background Cleanup controls the level of removing background on a scanned image.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum BackgroundCleanup {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * No cleanup.
         *
         * @since API 1
         */
        LEVEL_0,

        /**
         * Cleanup level 1 of 8
         *
         * @since API 1
         */
        LEVEL_1,

        /**
         * Cleanup level 2 of 8
         *
         * @since API 1
         */
        LEVEL_2,

        /**
         * Cleanup level 3 of 8
         *
         * @since API 1
         */
        LEVEL_3,

        /**
         * Cleanup level 4 of 8
         *
         * @since API 1
         */
        LEVEL_4,

        /**
         * Cleanup level 5 of 8
         *
         * @since API 1
         */
        LEVEL_5,

        /**
         * Cleanup level 6 of 8
         *
         * @since API 1
         */
        LEVEL_6,

        /**
         * Cleanup level 7 of 8
         *
         * @since API 1
         */
        LEVEL_7,

        /**
         * Most aggressive cleanup.
         *
         * @since API 1
         */
        LEVEL_8
    }

    /**
     * <p>Contrast Adjustment controls the level of contrast correction on a scanned image.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum ContrastAdjustment {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Least contrast.
         *
         * @since API 1
         */
        LEVEL_0,

        /**
         * Contrast level 1 of 8
         *
         * @since API 1
         */
        LEVEL_1,

        /**
         * Contrast level 2 of 8
         *
         * @since API 1
         */
        LEVEL_2,

        /**
         * Contrast level 3 of 8
         *
         * @since API 1
         */
        LEVEL_3,

        /**
         * Contrast level 4 of 8
         *
         * @since API 1
         */
        LEVEL_4,

        /**
         * Contrast level 5 of 8
         *
         * @since API 1
         */
        LEVEL_5,

        /**
         * Contrast level 6 of 8
         *
         * @since API 1
         */
        LEVEL_6,

        /**
         * Contrast level 7 of 8
         *
         * @since API 1
         */
        LEVEL_7,

        /**
         * Most contrast.
         *
         * @since API 1
         */
        LEVEL_8
    }

    /**
     * <p>Darkness Adjustment controls the level of darkness correction on a scanned image.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum DarknessAdjustment {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Darkest.
         *
         * @since API 1
         */
        LEVEL_0,

        /**
         * Darkness level 1 of 8
         *
         * @since API 1
         */
        LEVEL_1,

        /**
         * Darkness level 2 of 8
         *
         * @since API 1
         */
        LEVEL_2,

        /**
         * Darkness level 3 of 8
         *
         * @since API 1
         */
        LEVEL_3,

        /**
         * Darkness level 4 of 8
         *
         * @since API 1
         */
        LEVEL_4,

        /**
         * Darkness level 5 of 8
         *
         * @since API 1
         */
        LEVEL_5,

        /**
         * Darkness level 6 of 8
         *
         * @since API 1
         */
        LEVEL_6,

        /**
         * Darkness level 7 of 8
         *
         * @since API 1
         */
        LEVEL_7,

        /**
         * Lightest.
         *
         * @since API 1
         */
        LEVEL_8
    }

    /**
     * <p>Blank Image Removal Mode controls the whether to skip blank pages or not.</p>
     *
     * @since API 1
     */
    public enum BlankImageRemovalMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Do not remove blank images.
         *
         * @since API 1
         */
        OFF,

        /**
         * Remove blank images.<br/>
         * If all images in the job are removed, the job will fail.<br/>
         * Some older devices will always include the first image in the job, even if it is blank.
         *
         * @since API 1
         */
        ON
    }

    /**
     * <p>Color dropout mode for scanning</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum ColorDropoutMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * No dropout.
         *
         * @since API 1
         */
        OFF,

        /**
         * Remove the red color plane.
         *
         * @since API 1
         */
        REMOVE_RED,

        /**
         * Remove the green color plane.
         *
         * @since API 1
         */
        REMOVE_GREEN,

        /**
         * Remove the blue color plane.
         *
         * @since API 1
         */
        REMOVE_BLUE
    }

    /**
     * <p>Crop Mode controls whether the scanned image be cropped to the contents or not .</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum CropMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Do not crop
         *
         * @since API 1
         */
        OFF,

        /**
         * Same as "CROP_TO_CONTENT". Remove white space from top, left, right, and bottom of scanned images.
         * Note that the resulting image will likely not conform to a standard media size.
         * Blank images will not be cropped.
         *
         * @since API 1
         */
        ON,

        /**
         * Crop the image/paper to media size.
         * Note that the resulting image will likely not conform to a standard media size.
         * Blank images will not be cropped.
         *
         * @since API 5
         */
        CROP_TO_PAPER,

        /**
         * Remove white space from top, left, right, and bottom of scanned images.
         * Note that the resulting image will likely not conform to a standard media size.
         * Blank images will not be cropped.
         *
         * @since API 5
         */
        CROP_TO_CONTENT
    }

    /**
     * <p>Progress Dialog Mode controls whether to show device built-in progress dialog or not.</p>
     *
     * @deviceOnly
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum ProgressDialogMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * <p>The device will not display built-in scan progress dialogs.<br/>
         * The embedded browser will not be obscured while the device is scanning, allowing the web application to update and display browser content to the walk-up user.</p>
         *
         * @since API 1
         */
        OFF,

        /**
         * <p>The device will display built-in scan progress dialogs.<br/>
         * The embedded browser will be obscured by a built-in scan progress dialog while the device is scanning.</p>
         *
         * @since API 1
         */
        ON
    }

    /**
     * <p>Output Quality controls the result quality of a scanned image.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum OutputQuality {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Lowest quality (most loss due to file compression, smallest file size).
         *
         * @since API 1
         */
        LOW,

        /**
         * Medium quality (medium loss due to file compression, medium file size).
         *
         * @since API 1
         */
        MEDIUM,

        /**
         * Highest quality (least loss due to file compression, largest file size).
         *
         * @since API 1
         */
        HIGH
    }

    /**
     * <p>Transmission Mode controls whether a image file will be sent after each page scanned
     * or all files sent when job completed.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum TransmissionMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Transmit images as they become available (may only be used with HTTP and HTTPS schemes).
         *
         * @since API 1
         */
        IMAGE,

        /**
         * Transmit all images after the job has completed processing.
         *
         * @since API 1
         */
        JOB
    }

    /**
     * <p>Job Assembly Mode controls whether to allow multiple scan segments assembled
     * into a single job when completed.</p>
     *
     * @deviceOnly
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum JobAssemblyMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Scan only one segment per job
         *
         * @since API 1
         */
        OFF,

        /**
         * <p>Scan multiple segments, prompting the walk-up user for another segment at the end of each segment,
         * and assembling all segments into a single job. Scan settings cannot be adjusted between segments.
         * This mode is intended for jobs where the number of sheets exceeds the capacity of the ADF</p>
         *
         * @since API 1
         */
        ON
    }

    /**
     * <p>Sharpness Adjustment controls the level of sharpness correction on a scanned image.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum SharpnessAdjustment {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Least sharp.
         *
         * @since API 1
         */
        LEVEL_0,

        /**
         * Sharpness level 1 of 4
         *
         * @since API 1
         */
        LEVEL_1,

        /**
         * Sharpness level 2 of 4
         *
         * @since API 1
         */
        LEVEL_2,

        /**
         * Sharpness level 3 of 4
         *
         * @since API 1
         */
        LEVEL_3,

        /**
         * Sharpest.
         *
         * @since API 1
         */
        LEVEL_4
    }

    /**
     * <p>Enumeration of media weight adjustments.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum MediaWeightAdjustment {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Adjust the scanner feed mechanism for typical (normal) paper weights.
         *
         * @since API 1
         */
        NORMAL,

        /**
         * Adjust the scanner feed mechanism for paper weighing more than 100 grams per square meter.
         *
         * @since API 1
         */
        HEAVY
    }

    /**
     * <p>Enumeration of text versus photo optimization settings</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum TextPhotoOptimization {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Optimize for all text.
         *
         * @since API 1
         */
        TEXT,

        /**
         * Optimize for mostly text with less graphics.
         *
         * @since API 1
         */
        MIXED_0,

        /**
         * Optimize for mostly text with more graphics.
         *
         * @since API 1
         */
        MIXED_1,

        /**
         * Optimize for an equal mix of text and graphics.
         *
         * @since API 1
         */
        MIXED_2,

        /**
         * Optimize for mostly graphics with more text.
         *
         * @since API 1
         */
        MIXED_3,

        /**
         * Optimize for mostly graphics with some text.
         *
         * @since API 1
         */
        MIXED_4,

        /**
         * Optimize for all graphics/pictures.
         *
         * @since API 1
         */
        GRAPHIC
    }

    /**
     * <p>Media Source controls whether device to scan from the ADF or flatbed.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum MediaSource {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * Directs the device to scan from the automatic document feeder
         *
         * @since API 1
         */
        ADF,

        /**
         * Directs the device to scan from the flatbed (glass)
         *
         * @since API 1
         */
        FLATBED,

        /**
         * The device will automatically select the media source
         *
         * @since API 1
         */
        AUTO
    }

    /**
     * <p>Misfeed Detection Mode controls whether to detect mis feed or not.</p>
     *
     * @since API 1
     */
    @SuppressWarnings("unused")
    public enum MisfeedDetectionMode {
        /**
         * Default value
         *
         * @since API 1
         */
        DEFAULT,

        /**
         * No misfeed detection
         *
         * @since API 1
         */
        OFF,

        /**
         * Detect misfeeds
         *
         * @since API 1
         */
        ON
    }

    /**
     * <p>Enable this feature to scan pages into separate files based on a specified page limit.
     * A page is one side of an original document. JPEG and TIFF have a limit of one page per file.</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum SplitAttachmentByPage {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Disable split attachment by pages
         *
         * @since API 5
         */
        DISABLED,

        /**
         * Enable split attachment by pages
         *
         * @since API 5
         */
        ENABLED
    }

    /**
     * <p>Margin unit used for erase edge.</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum EraseMarginUnit {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Feature should use inches
         *
         * @since API 5
         */
        INCHES,

        /**
         * Feature should use millimeters
         *
         * @since API 5
         */
        MILLIMETERS
    }

    /**
     * <p>Scan capture Mode.</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum CaptureMode {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Standard
         *
         * @since API 5
         */
        STANDARD,

        /**
         * Standard add pages
         *
         * @since API 5
         */
        STANDARD_ADD_PAGES,

        /**
         * Book capture (A4, Letter Only)
         *
         * @since API 5
         */
        BOOK_CAPTURE,

        /**
         * ID capture prompt both sides (A4, Letter Only)
         *
         * @since API 5
         */
        ID_CAPTURE_PROMPT_BOTH_SIDES,

        /**
         * ID capture prompt back side only (A4, Letter Only)
         *
         * @since API 5
         */
        ID_CAPTURE_PROMPT_BACK_SIDE_ONLY
    }

    /**
     * <p>Automatic tone mode.</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum AutomaticToneMode {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Enable automatic tone mode
         *
         * @since API 5
         */
        ENABLE,

        /**
         * Disable automatic tone mode
         *
         * @since API 5
         */
        DISABLE
    }

    /**
     * <p>To straighten content that is skewed relative to the page dimensions in the source document.
     * It works in automatic document feeder (ADF) mode.</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum AutomaticStraightenMode {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Enable automatic straighten mode
         *
         * @since API 5
         */
        ENABLE,

        /**
         * Disable automatic straighten mode
         *
         * @since API 5
         */
        DISABLE
    }

    final int mVersion;

    final ColorMode mColorMode;

    final Duplex mPlex;

    final Destination mDestination;

    final DocumentFormat mDocFormat;

    final Resolution mResolutionType;

    final ScanSize mScanSize;

    final ScanPreview mScanPreview;

    final Orientation mOrientation;

    final String mFileName;

    final String mFolderName;

    final String mCredentialsUsername;

    final String mCredentialsPassword;

    final Uri mUri;

    final int mConnectTimeout;

    final int mReadTimeout;

    final int mMaxRetries;

    final int mRetryInterval;

    final NetworkCredentialsAttributes mNetworkCredentialsAttributes;

    final EmailAttributes mEmailAttributes;

    final SmtpAttributes mSmtpAttributes;

    final BackgroundCleanup mBackgroundCleanup;

    final ContrastAdjustment mContrastAdjustment;

    final DarknessAdjustment mDarknessAdjustment;

    final BlankImageRemovalMode mBlankImageRemovalMode;

    final ColorDropoutMode mColorDropoutMode;

    final CropMode mCropMode;

    final ProgressDialogMode mProgressDialogMode;

    final OutputQuality mOutputQuality;

    final TransmissionMode mTransmissionMode;

    final FileOptionsAttributes mFileOptionsAttributes;

    final JobAssemblyMode mJobAssemblyMode;

    final SharpnessAdjustment mSharpnessAdjustment;

    final MediaWeightAdjustment mMediaWeightAdjustment;

    final TextPhotoOptimization mTextPhotoOptimization;

    final MediaSource mMediaSource;

    final MisfeedDetectionMode mMisfeedDetectionMode;

    final Float mCustomLength;

    final Float mCustomWidth;

    final String mUsbLocation;

    final SplitAttachmentByPage mSplitAttachmentByPage;

    final Integer mMaxPagesPerAttachment;

    final EraseMarginUnit mEraseMarginUnit;

    final Float mEraseBackBottom;
    final Float mEraseBackLeft;
    final Float mEraseBackRight;
    final Float mEraseBackTop;
    final Float mEraseFrontBottom;
    final Float mEraseFrontLeft;
    final Float mEraseFrontRight;
    final Float mEraseFrontTop;

    final CaptureMode mCaptureMode;

    final AutomaticToneMode mAutomaticToneMode;

    final AutomaticStraightenMode mAutomaticStraightenMode;

    /**
     * @hide The client should not need to know about the scan parcelable
     * methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide The client should not need to know about the scan parcelable
     * methods
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>Attributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(mVersion);
        out.writeSerializable(mColorMode);
        out.writeSerializable(mDestination);
        out.writeSerializable(mDocFormat);
        out.writeSerializable(mResolutionType);
        out.writeSerializable(mScanSize);
        out.writeSerializable(mPlex);
        out.writeSerializable(mOrientation);
        out.writeSerializable(mScanPreview);
        out.writeString(mFileName);
        out.writeString(mFolderName);
        out.writeString(mCredentialsUsername);
        out.writeString(mCredentialsPassword);

        out.writeParcelable(mUri, 0);
        out.writeInt(mConnectTimeout);
        out.writeInt(mReadTimeout);
        out.writeInt(mMaxRetries);
        out.writeInt(mRetryInterval);

        out.writeParcelable(mNetworkCredentialsAttributes, 0);
        out.writeParcelable(mEmailAttributes, 0);
        out.writeParcelable(mSmtpAttributes, 0);

        out.writeSerializable(mBackgroundCleanup);
        out.writeSerializable(mContrastAdjustment);
        out.writeSerializable(mDarknessAdjustment);
        out.writeSerializable(mBlankImageRemovalMode);
        out.writeSerializable(mColorDropoutMode);
        out.writeSerializable(mCropMode);
        out.writeSerializable(mProgressDialogMode);
        out.writeSerializable(mOutputQuality);
        out.writeSerializable(mTransmissionMode);
        out.writeParcelable(mFileOptionsAttributes, 0);
        out.writeSerializable(mJobAssemblyMode);
        out.writeSerializable(mSharpnessAdjustment);

        out.writeSerializable(mMediaWeightAdjustment);
        out.writeSerializable(mTextPhotoOptimization);
        out.writeSerializable(mMediaSource);
        out.writeSerializable(mMisfeedDetectionMode);
        out.writeSerializable(mCustomLength);
        out.writeSerializable(mCustomWidth);

        out.writeString(mUsbLocation);

        out.writeSerializable(mSplitAttachmentByPage);
        out.writeInt(mMaxPagesPerAttachment);
        out.writeSerializable(mEraseMarginUnit);

        out.writeFloat(mEraseBackBottom);
        out.writeFloat(mEraseBackLeft);
        out.writeFloat(mEraseBackRight);
        out.writeFloat(mEraseBackTop);
        out.writeFloat(mEraseFrontBottom);
        out.writeFloat(mEraseFrontLeft);
        out.writeFloat(mEraseFrontRight);
        out.writeFloat(mEraseFrontTop);

        out.writeSerializable(mCaptureMode);
        out.writeSerializable(mAutomaticToneMode);
        out.writeSerializable(mAutomaticStraightenMode);
        // Add new values here
    }

    /**
     * @hide The client should not need to know about the scan parcelable
     * methods
     */
    public static final Parcelable.Creator<ScanAttributes> CREATOR = new Parcelable.Creator<ScanAttributes>() {
        public ScanAttributes createFromParcel(final Parcel in) {
            return new ScanAttributes(in);
        }

        public ScanAttributes[] newArray(final int size) { return new ScanAttributes[size]; }
    };

    @SuppressLint("RestrictedApi")
    private ScanAttributes(final Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mColorMode = ColorMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ColorMode)in.readSerializable()).name());
        mDestination = Destination.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.Destination)in.readSerializable()).name());
        mDocFormat = DocumentFormat.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.DocumentFormat)in.readSerializable()).name());
        mResolutionType = Resolution.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.Resolution)in.readSerializable()).name());
        mScanSize = ScanSize.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ScanSize)in.readSerializable()).name());
        mPlex = Duplex.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.Duplex)in.readSerializable()).name());
        mOrientation = Orientation.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.Orientation)in.readSerializable()).name());
        mScanPreview = ScanPreview.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ScanPreview)in.readSerializable()).name());
        mFileName = in.readString();
        mFolderName = in.readString();
        mCredentialsUsername = in.readString();
        mCredentialsPassword = in.readString();
        mUri = in.readParcelable(Uri.class.getClassLoader());
        mConnectTimeout = in.readInt();
        mReadTimeout = in.readInt();
        mMaxRetries = in.readInt();
        mRetryInterval = in.readInt();

        Parcel parcelForNetworkAttr = Parcel.obtain();
        com.hp.workpath.api.scanner.NetworkCredentialsAttributes networkCredentialsAttributes = in.readParcelable(com.hp.workpath.api.scanner.NetworkCredentialsAttributes.class.getClassLoader());
        if(networkCredentialsAttributes != null) {
            networkCredentialsAttributes.writeToParcel(parcelForNetworkAttr, 0);
            parcelForNetworkAttr.setDataPosition(0);
            mNetworkCredentialsAttributes = NetworkCredentialsAttributes.CREATOR.createFromParcel(parcelForNetworkAttr);
        } else {
            mNetworkCredentialsAttributes = null;
        }

        Parcel parcelForEmailAttr = Parcel.obtain();
        com.hp.workpath.api.scanner.EmailAttributes emailAttributes = in.readParcelable(com.hp.workpath.api.scanner.EmailAttributes.class.getClassLoader());
        if(emailAttributes != null) {
            emailAttributes.writeToParcel(parcelForEmailAttr, 0);
            parcelForEmailAttr.setDataPosition(0);
            mEmailAttributes = EmailAttributes.CREATOR.createFromParcel(parcelForEmailAttr);
        } else {
            mEmailAttributes = null;
        }

        Parcel parcelForSmtpAttr = Parcel.obtain();
        com.hp.workpath.api.scanner.SmtpAttributes smtpAttributes = in.readParcelable(com.hp.workpath.api.scanner.SmtpAttributes.class.getClassLoader());
        if(smtpAttributes != null) {
            smtpAttributes.writeToParcel(parcelForSmtpAttr, 0);
            parcelForSmtpAttr.setDataPosition(0);
            mSmtpAttributes = SmtpAttributes.CREATOR.createFromParcel(parcelForSmtpAttr);
        } else {
            mSmtpAttributes = null;
        }

        in.setDataPosition(in.dataPosition());
        mBackgroundCleanup = BackgroundCleanup.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.BackgroundCleanup)in.readSerializable()).name());
        mContrastAdjustment = ContrastAdjustment.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ContrastAdjustment)in.readSerializable()).name());
        mDarknessAdjustment = DarknessAdjustment.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.DarknessAdjustment)in.readSerializable()).name());
        mBlankImageRemovalMode = BlankImageRemovalMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.BlankImageRemovalMode)in.readSerializable()).name());
        mColorDropoutMode = ColorDropoutMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ColorDropoutMode)in.readSerializable()).name());
        mCropMode = CropMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.CropMode)in.readSerializable()).name());
        mProgressDialogMode = ProgressDialogMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.ProgressDialogMode)in.readSerializable()).name());
        mOutputQuality = OutputQuality.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.OutputQuality)in.readSerializable()).name());
        mTransmissionMode = TransmissionMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.TransmissionMode)in.readSerializable()).name());

        Parcel parcelForFileAttr = Parcel.obtain();
        com.hp.workpath.api.scanner.FileOptionsAttributes fileOptionsAttributes = in.readParcelable(com.hp.workpath.api.scanner.FileOptionsAttributes.class.getClassLoader());
        if(fileOptionsAttributes != null) {
            fileOptionsAttributes.writeToParcel(parcelForFileAttr, 0);
            parcelForFileAttr.setDataPosition(0);
            mFileOptionsAttributes = FileOptionsAttributes.CREATOR.createFromParcel(parcelForFileAttr);
        } else {
            mFileOptionsAttributes = null;
        }

        mJobAssemblyMode = JobAssemblyMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.JobAssemblyMode)in.readSerializable()).name());
        mSharpnessAdjustment = SharpnessAdjustment.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.SharpnessAdjustment)in.readSerializable()).name());
        mMediaWeightAdjustment = MediaWeightAdjustment.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.MediaWeightAdjustment)in.readSerializable()).name());
        mTextPhotoOptimization = TextPhotoOptimization.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.TextPhotoOptimization)in.readSerializable()).name());
        mMediaSource = MediaSource.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.MediaSource)in.readSerializable()).name());
        mMisfeedDetectionMode = MisfeedDetectionMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.MisfeedDetectionMode)in.readSerializable()).name());

        mCustomLength = (Float) in.readSerializable();
        mCustomWidth = (Float) in.readSerializable();

        if (mVersion >= Sdk.VERSION_LEVEL.TWO) {
            mUsbLocation = in.readString();
        } else {
            mUsbLocation = null;
        }

        if (mVersion >= Sdk.VERSION_LEVEL.SIX) {
            mSplitAttachmentByPage = SplitAttachmentByPage.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.SplitAttachmentByPage)in.readSerializable()).name());
            mMaxPagesPerAttachment = in.readInt();
            mEraseMarginUnit = EraseMarginUnit.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.EraseMarginUnit)in.readSerializable()).name());

            mEraseBackBottom = in.readFloat();
            mEraseBackLeft = in.readFloat();
            mEraseBackRight = in.readFloat();
            mEraseBackTop = in.readFloat();
            mEraseFrontBottom = in.readFloat();
            mEraseFrontLeft = in.readFloat();
            mEraseFrontRight = in.readFloat();
            mEraseFrontTop = in.readFloat();

            mCaptureMode = CaptureMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.CaptureMode)in.readSerializable()).name());
            mAutomaticToneMode = AutomaticToneMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.AutomaticToneMode)in.readSerializable()).name());
            mAutomaticStraightenMode = AutomaticStraightenMode.valueOf(((com.hp.workpath.api.scanner.ScanAttributes.AutomaticStraightenMode)in.readSerializable()).name());
        } else {
            mSplitAttachmentByPage = SplitAttachmentByPage.DEFAULT;
            mMaxPagesPerAttachment = 1;
            mEraseMarginUnit = EraseMarginUnit.DEFAULT;

            mEraseBackBottom = 0.0f;
            mEraseBackLeft = 0.0f;
            mEraseBackRight = 0.0f;
            mEraseBackTop = 0.0f;
            mEraseFrontBottom = 0.0f;
            mEraseFrontLeft = 0.0f;
            mEraseFrontRight = 0.0f;
            mEraseFrontTop = 0.0f;

            mCaptureMode = CaptureMode.DEFAULT;
            mAutomaticToneMode = AutomaticToneMode.DEFAULT;
            mAutomaticStraightenMode = AutomaticStraightenMode.DEFAULT;
        }
    }
}
