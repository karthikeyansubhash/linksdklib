// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.copier;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.common.Sdk;

import java.util.Map;

/**
 * Job attributes for requesting a copy from the printer.
 *
 * @hide
 * @since API 3
 */
@Deprecated
public class CopyAttributes implements Parcelable {
    /**
     * The color modes for copying supported by the current printer.
     *
     * @since API 3
     */
    public enum ColorMode {
        /**
         * Color mode default from the printer.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Auto mode.
         *
         * @since API 3
         */
        AUTO,

        /**
         * Color
         *
         * @since API 3
         */
        COLOR,

        /**
         * Gray / Gray-scale
         *
         * @since API 3
         */
        GRAY,

        /**
         * Mono
         *
         * @since API 3
         */
        MONO
    }

    /**
     * Original Orientation options supported by the current printer.
     *
     * @since API 3
     */
    public enum Orientation {
        /**
         * Uses the default orientation settings configured on the printer.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Portrait orientation of a page
         *
         * @since API 3
         */
        PORTRAIT,

        /**
         * Landscape orientation of a page
         *
         * @since API 3
         */
        LANDSCAPE
    }

    /**
     * Duplex options supported by the current printer.
     *
     * @since API 3
     */
    public enum Duplex {
        /**
         * Use the duplex settings configured on the printer.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * No duplex
         *
         * @since API 3
         */
        NONE,

        /**
         * Duplex along the long edge of the paper.
         *
         * @since API 3
         */
        BOOK,

        /**
         * Duplex along the short edge of the paper.
         *
         * @since API 3
         */
        FLIP
    }

    /**
     * The scan size (i.e., size of the original) for scanning.<br>
     *
     * <p>This attribute is relative to the orientation parameter of scan.</p>
     *
     * @since API 3
     */
    public enum ScanSize {
        /**
         * Default scanning size from printer.
         * If the printer supports auto-detection it will be used.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * ISO A3 (297mm x 420mm)
         *
         * @since API 3
         */
        A3,

        /**
         * ISO A4 Rotated (297mm x 210mm)
         *
         * @since API 3
         */
        A4_ROTATE,

        /**
         * ISO A4 (210mm x 297mm)
         *
         * @since API 3
         */
        A4,

        /**
         * ISO A5 Rotated (210mm x 148mm)
         *
         * @since API 3
         */
        A5_ROTATE,

        /**
         * ISO A5 (148mm x 210mm)
         *
         * @since API 3
         */
        A5,

        /**
         * ISO A6 Rotated (148mm x 105mm)
         *
         * @since API 3
         */
        A6_ROTATE,

        /**
         * ISO A6 (105mm x 148mm)
         *
         * @since API 3
         */
        A6,

        /**
         * ISO B4 (250mm x 353mm)
         *
         * @since API 3
         */
        B4,

        /**
         * ISO B5 (176mm x 250mm)
         *
         * @since API 3
         */
        B5,

        /**
         * ISO B5 Rotated (250mm x 176mm)
         *
         * @since API 3
         */
        B5_ROTATE,

        /**
         * ISO B6 (125mm x 176mm)
         *
         * @since API 3
         */
        B6,

        /**
         * JBusinessCard (55mm x 91mm)
         *
         * @since API 3
         */
        BUSINESS_CARD,

        /**
         * Letter Rotated (11inch x 8.5inch)
         *
         * @since API 3
         */
        LETTER_ROTATE,

        /**
         * Letter (8.5inch x 11inch)
         *
         * @since API 3
         */
        LETTER,

        /**
         * JIS B4 (257mm x 364mm)
         *
         * @since API 3
         */
        JB4,

        /**
         * JIS B5 Rotated (257mm x 182mm)
         *
         * @since API 3
         */
        JB5_ROTATE,

        /**
         * JIS B5 (182mm x 257mm)
         *
         * @since API 3
         */
        JB5,

        /**
         * JIS B6 (128mm x 182mm)
         *
         * @since API 3
         */
        JB6,

        /**
         * Legal (8.5inch x 14inch)
         *
         * @since API 3
         */
        LEGAL,

        /**
         * Ledger (11inch x 17inch)
         *
         * @since API 3
         */
        LEDGER,

        /**
         * Executive (7.25inch x 10.5inch)
         *
         * @since API 3
         */
        EXECUTIVE,

        /**
         * Statement (5.5inch x 8.5inch)
         *
         * @since API 3
         */
        STATEMENT,

        /**
         * Statement Rotated (8.5inch x 5.5inch)
         *
         * @since API 3
         */
        STATEMENT_ROTATE,

        /**
         * K8 (270mm x 390mm)
         *
         * @since API 3
         */
        K8,

        /**
         * K16 (195mm x 270mm)
         *
         * @since API 3
         */
        K16,

        /**
         * PRC 8K (273mm x 394mm)
         *
         * @since API 3
         */
        PK8,

        /**
         * PRC 16K (197mm x 273mm)
         *
         * @since API 3
         */
        PK16,

        /**
         * INCH8POINT5X13 (8.5inch x 13inch)
         *
         * @since API 3
         */
        INCH8POINT5X13,

        /**
         * INCH12X18 (12inch x 18inch)
         *
         * @since API 3
         */
        INCH12X18,

        /**
         * Mixed Letter and Legal
         *
         * @since API 3
         */
        MIXED_LETTER_LEGAL,

        /**
         * Mixed Letter and Ledger
         *
         * @since API 3
         */
        MIXED_LETTER_LEDGER,

        /**
         * Mixed A3 and A4
         *
         * @since API 3
         */
        MIXED_A3_A4,

        /**
         * Match original media size
         *
         * @since API 3
         */
        MATCH_ORIGINAL,

        /**
         * Custom size
         *
         * @since API 3
         */
        CUSTOM,

        /**
         * Auto detected size for scan (Any)
         * A meta-type used to indicate auto-select
         *
         * @since API 3
         */
        AUTO,

        /**
         * ANSI C (17inch x 22inch)
         *
         * @since API 5
         */
        ANSI_C_17X22in,

        /**
         * ANSI D (22inch x 34inch)
         *
         * @since API 5
         */
        ANSI_D_22X34in,

        /**
         * ANSI E (34inch x 44inch)
         *
         * @since API 5
         */
        ANSI_E_34X44in,

        /**
         * ANSI F (28inch x 40inch)
         *
         * @since API 5
         */
        ANSI_F_28X40in,

        /**
         * Architectural A (9inch x 12inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_A_9X12in,

        /**
         * Architectural C (18inch x 24inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_C_18X24in,

        /**
         * Architectural D (24inch x 36inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_D_24X36in,

        /**
         * Architectural E (36inch x 48inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E_36X48in,

        /**
         * Architectural E1 (30inch x 42inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E1_30X42in,

        /**
         * Architectural E2 (26inch x 38inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E2_26X38in,

        /**
         * Architectural E3 (27inch x 39inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E3_27X39in,

        /**
         * DIN 2A0 (1189mm x 1682mm)
         *
         * @since API 5
         */
        DIN_2XA0_1189X1682mm,

        /**
         * DIN 4A0 (1682mm x 2378mm)
         *
         * @since API 5
         */
        DIN_4XA0_1682X2378mm,

        /**
         * DL (99mm x 210mm)
         *
         * @since API 5
         */
        DL_99X210mm,

        /**
         * Envelope A2 (4.375inch x 5.75inch)
         *
         * @since API 5
         */
        ENVELOPE_A2,

        /**
         * Envelope Catalog1 (6inch x 9inch)
         *
         * @since API 5
         */
        ENVELOPE_CATALOG,

        /**
         * Envelope Comm10 (4.125inch x 9.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM10,

        /**
         * Envelope Comm6.75 (3.625inch x 6.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM6,

        /**
         * Envelope DL (110mm x 220mm)
         *
         * @since API 5
         */
        ENVELOPE_DL,

        /**
         * Envelope Monarch (3.875inch x 7.5inch)
         *
         * @since API 5
         */
        ENVELOPE_MONARCH,

        /**
         * Envelope 9 (3.875inch x 8.875inch)
         *
         * @since API 5
         */
        ENVELOPE_9,

        /**
         * Executive Rotated (10.5inch x 7.25inch)
         *
         * @since API 5
         */
        EXECUTIVE_ROTATE,

        /**
         * GENERAL_10X11in (10inch x 11inch)
         *
         * @since API 5
         */
        GENERAL_10X11in,

        /**
         * GENERAL_10X13in (10inch x 13inch)
         *
         * @since API 5
         */
        GENERAL_10X13in,

        /**
         * GENERAL_10X14in (10inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_10X14in,

        /**
         * GENERAL_10X15in (10inch x 15inch)
         *
         * @since API 5
         */
        GENERAL_10X15in,

        /**
         * GENERAL_11X12in (11inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_11X12in,

        /**
         * GENERAL_11X14in (11inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_11X14in,

        /**
         * GENERAL_11X15in (11inch x 15inch)
         *
         * @since API 5
         */
        GENERAL_11X15in,

        /**
         * GENERAL_11X19in (11inch x 19inch)
         *
         * @since API 5
         */
        GENERAL_11X19in,

        /**
         * GENERAL_12X12in (12inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_12X12in,

        /**
         * GENERAL_12X14in (12inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_12X14in,

        /**
         * GENERAL_12X19in (12inch x 19inch)
         *
         * @since API 5
         */
        GENERAL_12X19in,

        /**
         * GENERAL_3POINT5X5in (3.5inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3POINT5X5in,

        /**
         * GENERAL_3X5in (3inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3X5in,

        /**
         * GENERAL_4X12in (4inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_4X12in,

        /**
         * GENERAL_4X6in (4inch x 6inch)
         *
         * @since API 5
         */
        GENERAL_4X6in,

        /**
         * GENERAL_4X8in (4inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_4X8in,

        /**
         * GENERAL_5X7in (5inch x 7inch)
         *
         * @since API 5
         */
        GENERAL_5X7in,

        /**
         * GENERAL_5X8in (5inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_5X8in,

        /**
         * GENERAL_6X8in (6inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_6X8in,

        /**
         * GENERAL_7X9in (7inch x 9inch)
         *
         * @since API 5
         */
        GENERAL_7X9in,

        /**
         * GENERAL_9X11in (9inch x 11inch)
         *
         * @since API 5
         */
        GENERAL_9X11in,

        /**
         * Government Legal (8inch x 13inch)
         *
         * @since API 5
         */
        GOVT_LEGAL,

        /**
         * Government Letter (8inch x 10inch)
         *
         * @since API 5
         */
        GOVT_LETTER,

        /**
         * ISO A0 (841mm x 1189mm)
         *
         * @since API 5
         */
        A0,

        /**
         * ISO A1 (594mm x 841mm)
         *
         * @since API 5
         */
        A1,

        /**
         * ISO A2 (420mm x 594mm)
         *
         * @since API 5
         */
        A2,

        /**
         * ISO A7 (74mm x 105mm)
         *
         * @since API 5
         */
        A7,

        /**
         * ISO A8 (52mm x 74mm)
         *
         * @since API 5
         */
        A8,

        /**
         * ISO A9 (37mm x 52mm)
         *
         * @since API 5
         */
        A9,

        /**
         * ISO A10 (26mm x 37mm)
         *
         * @since API 5
         */
        A10,

        /**
         * ISO B0 (1000mm x 1414mm)
         *
         * @since API 5
         */
        B0,

        /**
         * ISO B1 (707mm x 1000mm)
         *
         * @since API 5
         */
        B1,

        /**
         * ISO B2 (500mm x 707mm)
         *
         * @since API 5
         */
        B2,

        /**
         * ISO B3 (353mm x 500mm)
         *
         * @since API 5
         */
        B3,

        /**
         * ISO B7 (88mm x 125mm)
         *
         * @since API 5
         */
        B7,

        /**
         * ISO B8 (62mm x 88mm)
         *
         * @since API 5
         */
        B8,

        /**
         * ISO B9 (44mm x 62mm)
         *
         * @since API 5
         */
        B9,

        /**
         * ISO B10 (31mm x 44mm)
         *
         * @since API 5
         */
        B10,

        /**
         * ISO C0 (917mm x 1297mm)
         *
         * @since API 5
         */
        C0,

        /**
         * ISO C1 (648mm x 917mm)
         *
         * @since API 5
         */
        C1,

        /**
         * ISO C2 (458mm x 648mm)
         *
         * @since API 5
         */
        C2,

        /**
         * ISO C3 (324mm x 458mm)
         *
         * @since API 5
         */
        C3,

        /**
         * ISO C4 (229mm x 324mm)
         *
         * @since API 5
         */
        C4,

        /**
         * ISO C5 (162mm x 229mm)
         *
         * @since API 5
         */
        C5,

        /**
         * ISO C6 (114mm x 162mm)
         *
         * @since API 5
         */
        C6,

        /**
         * ISO C7 (81mm x 114mm)
         *
         * @since API 5
         */
        C7,

        /**
         * ISO C8 (57mm x 81mm)
         *
         * @since API 5
         */
        C8,

        /**
         * ISO C9 (40mm x 57mm)
         *
         * @since API 5
         */
        C9,

        /**
         * ISO C10 (28mm x 40mm)
         *
         * @since API 5
         */
        C10,

        /**
         * Japanese Double Postcard (148mm x 200mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD,

        /**
         * Japanese Double Postcard Rotated (200mm x 148mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD_ROTATE,

        /**
         * JIS B0 (1030mm x 1456mm)
         *
         * @since API 5
         */
        JB0,

        /**
         * JIS B1 (728mm x 1030mm)
         *
         * @since API 5
         */
        JB1,

        /**
         * JIS B2 (515mm x 728mm)
         *
         * @since API 5
         */
        JB2,

        /**
         * JIS B3 (364mm x 515mm)
         *
         * @since API 5
         */
        JB3,

        /**
         * JIS B7 (91mm x 128mm)
         *
         * @since API 5
         */
        JB7,

        /**
         * JIS B8 (64mm x 91mm)
         *
         * @since API 5
         */
        JB8,

        /**
         * JIS B9 (45mm x 64mm)
         *
         * @since API 5
         */
        JB9,

        /**
         * JIS B10 (32mm x 45mm)
         *
         * @since API 5
         */
        JB10,

        /**
         * JIS Chou3 (120mm x 235mm)
         *
         * @since API 5
         */
        JCHOU3,

        /**
         * JIS Chou4 (90mm x 205mm)
         *
         * @since API 5
         */
        JCHOU4,

        /**
         * JIS Exec (216mm x 330mm)
         *
         * @since API 5
         */
        JEXEC,

        /**
         * JIS Kaku2 (240mm x 332mm)
         *
         * @since API 5
         */
        JKAKU2,

        /**
         * Japanese Postcard (100mm x 148mm)
         *
         * @since API 5
         */
        JPOSTCARD,

        /**
         * K16_184X260mm (184mm x 260mm)
         *
         * @since API 5
         */
        K16_184X260mm,

        /**
         * K8_260X368mm (260mm x 368mm)
         *
         * @since API 5
         */
        K8_260X368mm,

        /**
         * Long Scan (8.5inch x 34inch)
         *
         * @since API 5
         */
        LONG_SCAN,

        /**
         * Mutsugiri (203mm x 254mm)
         *
         * @since API 5
         */
        MUTSUGIRI,

        /**
         * Oficio (216mm x 340mm)
         *
         * @since API 5
         */
        OFICIO,

        /**
         * RA0 (860mm x 1220mm)
         *
         * @since API 5
         */
        RA0,

        /**
         * RA1 (610mm x 860mm)
         *
         * @since API 5
         */
        RA1,

        /**
         * RA2 (430mm x 610mm)
         *
         * @since API 5
         */
        RA2,

        /**
         * RA3 (305mm x 430mm)
         *
         * @since API 5
         */
        RA3,

        /**
         * RA4 (215mm x 305mm)
         *
         * @since API 5
         */
        RA4,

        /**
         * SRA0 (900mm x 1280mm)
         *
         * @since API 5
         */
        SRA0,

        /**
         * SRA1 (640mm x 900mm)
         *
         * @since API 5
         */
        SRA1,

        /**
         * SRA2 (450mm x 640mm)
         *
         * @since API 5
         */
        SRA2,

        /**
         * SRA3 (320mm x 450mm)
         *
         * @since API 5
         */
        SRA3,

        /**
         * SRA4 (225mm x 320mm)
         *
         * @since API 5
         */
        SRA4,

        /**
         * Super B (13inch x 19inch)
         *
         * @since API 5
         */
        SUPER_B,

        /**
         * Index card (100mm x 150mm)
         *
         * @since API 5
         */
        INDEXCARD,

        /**
         * An indeterminable size due to lack of sensors
         *
         * @since API 5
         */
        UNKNOWN,

        /**
         * Any custom size
         *
         * @since API 5
         */
        ANY_CUSTOM,

        /**
         * An indeterminable envelope size due to lack of sensors
         *
         * @since API 5
         */
        UNKNOWN_ENVELOP;

        /**
         * Retrieves the height of paper
         *
         * @return <p>supported height from {@link com.hp.workpath.api.PaperSize}.
         * <ul>
         * <li>Return the supported height</li>
         * <li>Return can be 0.0 if PaperSize height is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getHeight() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getHeight();
                }
            }
            return 0;
        }

        /**
         * Retrieves the width of paper
         *
         * @return <p>supported width from {@link com.hp.workpath.api.PaperSize}.
         * <ul>
         * <li>Return the supported width</li>
         * <li>Return can be 0.0 if PaperSize width is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getWidth() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getWidth();
                }
            }
            return 0;
        }

        /**
         * Retrieves the unit of paper
         *
         * @return <p>supported unit from {@link com.hp.workpath.api.PaperSize}.
         * <ul>
         * <li>Return the supported unit (mm or inch or cm) according to paper size.</li>
         * <li>Return can be null if PaperSize unit is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public String getUnit() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getUnit();
                }
            }
            return null;
        }
    }

    /**
     * <p>Scan Source controls a feed source for scanning</p>
     *
     * @since API 3
     */
    public enum ScanSource {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Directs the device to scan from the automatic document feeder.
         *
         * @since API 3
         */
        ADF,

        /**
         * Directs the device to scan from the flatbed (Glass).
         *
         * @since API 3
         */
        FLATBED,

        /**
         * The device will automatically select the media source.
         *
         * @since API 3
         */
        AUTO
    }

    /**
     * <p>Copy Preview controls if device must show a preview of scanned images and request confirmation to copy final document.</p>
     *
     * @since API 3
     */
    public enum CopyPreview {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Enable Copy Preview,
         * After starting a job the device will pre-scan a document and displays preview to an user,
         * if user confirms the preview the device will do a final scan
         *
         * @since API 3
         */

        TRUE,
        /**
         * Disable Copy Preview
         * No preview will be shown during copying
         *
         * @since API 3
         */
        FALSE,
    }

    /**
     * <p>Background Cleanup controls the level of removing background on a scanned image.</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum BackgroundCleanup {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * @since API 3
         */
        LEVEL_0,

        /**
         * @since API 3
         */
        LEVEL_1,

        /**
         * @since API 3
         */
        LEVEL_2,

        /**
         * @since API 3
         */
        LEVEL_3,

        /**
         * @since API 3
         */
        LEVEL_4,

        /**
         * @since API 3
         */
        LEVEL_5,

        /**
         * @since API 3
         */
        LEVEL_6,

        /**
         * @since API 3
         */
        LEVEL_7,

        /**
         * @since API 3
         */
        LEVEL_8
    }

    /**
     * <p>Contrast Adjustment controls the level of contrast correction on a scanned image.</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum ContrastAdjustment {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * @since API 3
         */
        LEVEL_0,

        /**
         * @since API 3
         */
        LEVEL_1,

        /**
         * @since API 3
         */
        LEVEL_2,

        /**
         * @since API 3
         */
        LEVEL_3,

        /**
         * @since API 3
         */
        LEVEL_4,

        /**
         * @since API 3
         */
        LEVEL_5,

        /**
         * @since API 3
         */
        LEVEL_6,

        /**
         * @since API 3
         */
        LEVEL_7,

        /**
         * @since API 3
         */
        LEVEL_8
    }

    /**
     * <p>Darkness Adjustment controls the level of darkness correction on a scanned image.</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum DarknessAdjustment {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * @since API 3
         */
        LEVEL_0,

        /**
         * @since API 3
         */
        LEVEL_1,

        /**
         * @since API 3
         */
        LEVEL_2,

        /**
         * @since API 3
         */
        LEVEL_3,

        /**
         * @since API 3
         */
        LEVEL_4,

        /**
         * @since API 3
         */
        LEVEL_5,

        /**
         * @since API 3
         */
        LEVEL_6,

        /**
         * @since API 3
         */
        LEVEL_7,

        /**
         * @since API 3
         */
        LEVEL_8
    }

    /**
     * <p>Sharpness Adjustment controls the level of sharpness correction on a scanned image.</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum SharpnessAdjustment {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * @since API 3
         */
        LEVEL_0,

        /**
         * @since API 3
         */
        LEVEL_1,

        /**
         * @since API 3
         */
        LEVEL_2,

        /**
         * @since API 3
         */
        LEVEL_3,

        /**
         * @since API 3
         */
        LEVEL_4,
    }

    /**
     * Collate Mode controls sheet collation.
     *
     * @since API 3
     */
    public enum CollateMode {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Sheets will be collated
         *
         * @since API 3
         */
        COLLATED,

        /**
         * Sheets will be uncollated
         *
         * @since API 3
         */
        UNCOLLATED
    }

    /**
     * The paper size for printing.<br>
     *
     * <p>This attribute is relative to the orientation parameter of scan.</p>
     *
     * @since API 3
     */
    public enum PaperSize {
        /**
         * Default paper size.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * ISO A3 (297mm x 420mm)
         *
         * @since API 3
         */
        A3,

        /**
         * ISO A4 Rotated (297mm x 210mm)
         *
         * @since API 3
         */
        A4_ROTATE,

        /**
         * ISO A4 (210mm x 297mm)
         *
         * @since API 3
         */
        A4,

        /**
         * ISO A5 Rotated (210mm x 148mm)
         *
         * @since API 3
         */
        A5_ROTATE,

        /**
         * ISO A5 (148mm x 210mm)
         *
         * @since API 3
         */
        A5,

        /**
         * ISO A6 Rotated (148mm x 105mm)
         *
         * @since API 3
         */
        A6_ROTATE,

        /**
         * ISO A6 (105mm x 148mm)
         *
         * @since API 3
         */
        A6,

        /**
         * ISO B4 (250mm x 353mm)
         *
         * @since API 3
         */
        B4,

        /**
         * ISO B5 (176mm x 250mm)
         *
         * @since API 3
         */
        B5,

        /**
         * ISO B5 Rotated (250mm x 176mm)
         *
         * @since API 3
         */
        B5_ROTATE,

        /**
         * ISO B6 (125mm x 176mm)
         *
         * @since API 3
         */
        B6,

        /**
         * JBusinessCard (55mm x 91mm)
         *
         * @since API 3
         */
        BUSINESS_CARD,

        /**
         * Letter Rotated (11inch x 8.5inch)
         *
         * @since API 3
         */
        LETTER_ROTATE,

        /**
         * Letter (8.5inch x 11inch)
         *
         * @since API 3
         */
        LETTER,

        /**
         * JIS B4 (257mm x 364mm)
         *
         * @since API 3
         */
        JB4,

        /**
         * JIS B5 Rotated (257mm x 182mm)
         *
         * @since API 3
         */
        JB5_ROTATE,

        /**
         * JIS B5 (182mm x 257mm)
         *
         * @since API 3
         */
        JB5,

        /**
         * JIS B6 (128mm x 182mm)
         *
         * @since API 3
         */
        JB6,

        /**
         * Legal (8.5inch x 14inch)
         *
         * @since API 3
         */
        LEGAL,

        /**
         * Ledger (11inch x 17inch)
         *
         * @since API 3
         */
        LEDGER,

        /**
         * Executive (7.25inch x 10.5inch)
         *
         * @since API 3
         */
        EXECUTIVE,

        /**
         * Statement (5.5inch x 8.5inch)
         *
         * @since API 3
         */
        STATEMENT,

        /**
         * Statement Rotated (8.5inch x 5.5inch)
         *
         * @since API 3
         */
        STATEMENT_ROTATE,

        /**
         * K8 (270mm x 390mm)
         *
         * @since API 3
         */
        K8,

        /**
         * K16 (195mm x 270mm)
         *
         * @since API 3
         */
        K16,

        /**
         * PRC 8K (273mm x 394mm)
         *
         * @since API 3
         */
        PK8,

        /**
         * PRC 16K (197mm x 273mm)
         *
         * @since API 3
         */
        PK16,

        /**
         * INCH8POINT5X13 (8.5inch x 13inch)
         *
         * @since API 3
         */
        INCH8POINT5X13,

        /**
         * INCH12X18 (12inch x 18inch)
         *
         * @since API 3
         */
        INCH12X18,

        /**
         * Mixed Letter and Legal paper size
         *
         * @since API 3
         */
        MIXED_LETTER_LEGAL,

        /**
         * Mixed Letter and Ledger paper size
         *
         * @since API 3
         */
        MIXED_LETTER_LEDGER,

        /**
         * Mixed A3 and A4 paper size
         *
         * @since API 3
         */
        MIXED_A3_A4,

        /**
         * Match original scan size
         *
         * @since API 3
         */
        MATCH_ORIGINAL,

        /**
         * Custom size
         *
         * @since API 3
         */
        CUSTOM,

        /**
         * Auto detected size for print (Any)
         * A meta-type used to indicate auto-select
         *
         * @since API 3
         */
        AUTO,

        /**
         * ANSI C (17inch x 22inch)
         *
         * @since API 5
         */
        ANSI_C_17X22in,

        /**
         * ANSI D (22inch x 34inch)
         *
         * @since API 5
         */
        ANSI_D_22X34in,

        /**
         * ANSI E (34inch x 44inch)
         *
         * @since API 5
         */
        ANSI_E_34X44in,

        /**
         * ANSI F (28inch x 40inch)
         *
         * @since API 5
         */
        ANSI_F_28X40in,

        /**
         * Architectural A (9inch x 12inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_A_9X12in,

        /**
         * Architectural C (18inch x 24inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_C_18X24in,

        /**
         * Architectural D (24inch x 36inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_D_24X36in,

        /**
         * Architectural E (36inch x 48inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E_36X48in,

        /**
         * Architectural E1 (30inch x 42inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E1_30X42in,

        /**
         * Architectural E2 (26inch x 38inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E2_26X38in,

        /**
         * Architectural E3 (27inch x 39inch)
         *
         * @since API 5
         */
        ARCHITECTURAL_E3_27X39in,

        /**
         * DIN 2A0 (1189mm x 1682mm)
         *
         * @since API 5
         */
        DIN_2XA0_1189X1682mm,

        /**
         * DIN 4A0 (1682mm x 2378mm)
         *
         * @since API 5
         */
        DIN_4XA0_1682X2378mm,

        /**
         * DL (99mm x 210mm)
         *
         * @since API 5
         */
        DL_99X210mm,

        /**
         * Envelope A2 (4.375inch x 5.75inch)
         *
         * @since API 5
         */
        ENVELOPE_A2,

        /**
         * Envelope Catalog1 (6inch x 9inch)
         *
         * @since API 5
         */
        ENVELOPE_CATALOG,

        /**
         * Envelope Comm10 (4.125inch x 9.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM10,

        /**
         * Envelope Comm6.75 (3.625inch x 6.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM6,

        /**
         * Envelope DL (110mm x 220mm)
         *
         * @since API 5
         */
        ENVELOPE_DL,

        /**
         * Envelope Monarch (3.875inch x 7.5inch)
         *
         * @since API 5
         */
        ENVELOPE_MONARCH,

        /**
         * Envelope 9 (3.875inch x 8.875inch)
         *
         * @since API 5
         */
        ENVELOPE_9,

        /**
         * Executive Rotated (10.5inch x 7.25inch)
         *
         * @since API 5
         */
        EXECUTIVE_ROTATE,

        /**
         * GENERAL_10X11in (10inch x 11inch)
         *
         * @since API 5
         */
        GENERAL_10X11in,

        /**
         * GENERAL_10X13in (10inch x 13inch)
         *
         * @since API 5
         */
        GENERAL_10X13in,

        /**
         * GENERAL_10X14in (10inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_10X14in,

        /**
         * GENERAL_10X15in (10inch x 15inch)
         *
         * @since API 5
         */
        GENERAL_10X15in,

        /**
         * GENERAL_11X12in (11inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_11X12in,

        /**
         * GENERAL_11X14in (11inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_11X14in,

        /**
         * GENERAL_11X15in (11inch x 15inch)
         *
         * @since API 5
         */
        GENERAL_11X15in,

        /**
         * GENERAL_11X19in (11inch x 19inch)
         *
         * @since API 5
         */
        GENERAL_11X19in,

        /**
         * GENERAL_12X12in (12inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_12X12in,

        /**
         * GENERAL_12X14in (12inch x 14inch)
         *
         * @since API 5
         */
        GENERAL_12X14in,

        /**
         * GENERAL_12X19in (12inch x 19inch)
         *
         * @since API 5
         */
        GENERAL_12X19in,

        /**
         * GENERAL_3POINT5X5in (3.5inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3POINT5X5in,

        /**
         * GENERAL_3X5in (3inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3X5in,

        /**
         * GENERAL_4X12in (4inch x 12inch)
         *
         * @since API 5
         */
        GENERAL_4X12in,

        /**
         * GENERAL_4X6in (4inch x 6inch)
         *
         * @since API 5
         */
        GENERAL_4X6in,

        /**
         * GENERAL_4X8in (4inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_4X8in,

        /**
         * GENERAL_5X7in (5inch x 7inch)
         *
         * @since API 5
         */
        GENERAL_5X7in,

        /**
         * GENERAL_5X8in (5inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_5X8in,

        /**
         * GENERAL_6X8in (6inch x 8inch)
         *
         * @since API 5
         */
        GENERAL_6X8in,

        /**
         * GENERAL_7X9in (7inch x 9inch)
         *
         * @since API 5
         */
        GENERAL_7X9in,

        /**
         * GENERAL_9X11in (9inch x 11inch)
         *
         * @since API 5
         */
        GENERAL_9X11in,

        /**
         * Government Legal (8inch x 13inch)
         *
         * @since API 5
         */
        GOVT_LEGAL,

        /**
         * Government Letter (8inch x 10inch)
         *
         * @since API 5
         */
        GOVT_LETTER,

        /**
         * ISO A0 (841mm x 1189mm)
         *
         * @since API 5
         */
        A0,

        /**
         * ISO A1 (594mm x 841mm)
         *
         * @since API 5
         */
        A1,

        /**
         * ISO A2 (420mm x 594mm)
         *
         * @since API 5
         */
        A2,

        /**
         * ISO A7 (74mm x 105mm)
         *
         * @since API 5
         */
        A7,

        /**
         * ISO A8 (52mm x 74mm)
         *
         * @since API 5
         */
        A8,

        /**
         * ISO A9 (37mm x 52mm)
         *
         * @since API 5
         */
        A9,

        /**
         * ISO A10 (26mm x 37mm)
         *
         * @since API 5
         */
        A10,

        /**
         * ISO B0 (1000mm x 1414mm)
         *
         * @since API 5
         */
        B0,

        /**
         * ISO B1 (707mm x 1000mm)
         *
         * @since API 5
         */
        B1,

        /**
         * ISO B2 (500mm x 707mm)
         *
         * @since API 5
         */
        B2,

        /**
         * ISO B3 (353mm x 500mm)
         *
         * @since API 5
         */
        B3,

        /**
         * ISO B7 (88mm x 125mm)
         *
         * @since API 5
         */
        B7,

        /**
         * ISO B8 (62mm x 88mm)
         *
         * @since API 5
         */
        B8,

        /**
         * ISO B9 (44mm x 62mm)
         *
         * @since API 5
         */
        B9,

        /**
         * ISO B10 (31mm x 44mm)
         *
         * @since API 5
         */
        B10,

        /**
         * ISO C0 (917mm x 1297mm)
         *
         * @since API 5
         */
        C0,

        /**
         * ISO C1 (648mm x 917mm)
         *
         * @since API 5
         */
        C1,

        /**
         * ISO C2 (458mm x 648mm)
         *
         * @since API 5
         */
        C2,

        /**
         * ISO C3 (324mm x 458mm)
         *
         * @since API 5
         */
        C3,

        /**
         * ISO C4 (229mm x 324mm)
         *
         * @since API 5
         */
        C4,

        /**
         * ISO C5 (162mm x 229mm)
         *
         * @since API 5
         */
        C5,

        /**
         * ISO C6 (114mm x 162mm)
         *
         * @since API 5
         */
        C6,

        /**
         * ISO C7 (81mm x 114mm)
         *
         * @since API 5
         */
        C7,

        /**
         * ISO C8 (57mm x 81mm)
         *
         * @since API 5
         */
        C8,

        /**
         * ISO C9 (40mm x 57mm)
         *
         * @since API 5
         */
        C9,

        /**
         * ISO C10 (28mm x 40mm)
         *
         * @since API 5
         */
        C10,

        /**
         * Japanese Double Postcard (148mm x 200mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD,

        /**
         * Japanese Double Postcard Rotated (200mm x 148mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD_ROTATE,

        /**
         * JIS B0 (1030mm x 1456mm)
         *
         * @since API 5
         */
        JB0,

        /**
         * JIS B1 (728mm x 1030mm)
         *
         * @since API 5
         */
        JB1,

        /**
         * JIS B2 (515mm x 728mm)
         *
         * @since API 5
         */
        JB2,

        /**
         * JIS B3 (364mm x 515mm)
         *
         * @since API 5
         */
        JB3,

        /**
         * JIS B7 (91mm x 128mm)
         *
         * @since API 5
         */
        JB7,

        /**
         * JIS B8 (64mm x 91mm)
         *
         * @since API 5
         */
        JB8,

        /**
         * JIS B9 (45mm x 64mm)
         *
         * @since API 5
         */
        JB9,

        /**
         * JIS B10 (32mm x 45mm)
         *
         * @since API 5
         */
        JB10,

        /**
         * JIS Chou3 (120mm x 235mm)
         *
         * @since API 5
         */
        JCHOU3,

        /**
         * JIS Chou4 (90mm x 205mm)
         *
         * @since API 5
         */
        JCHOU4,

        /**
         * JIS Exec (216mm x 330mm)
         *
         * @since API 5
         */
        JEXEC,

        /**
         * JIS Kaku2 (240mm x 332mm)
         *
         * @since API 5
         */
        JKAKU2,

        /**
         * Japanese Postcard (100mm x 148mm)
         *
         * @since API 5
         */
        JPOSTCARD,

        /**
         * K16_184X260mm (184mm x 260mm)
         *
         * @since API 5
         */
        K16_184X260mm,

        /**
         * K8_260X368mm (260mm x 368mm)
         *
         * @since API 5
         */
        K8_260X368mm,

        /**
         * Long Scan (8.5inch x 34inch)
         *
         * @since API 5
         */
        LONG_SCAN,

        /**
         * Mutsugiri (203mm x 254mm)
         *
         * @since API 5
         */
        MUTSUGIRI,

        /**
         * Oficio (216mm x 340mm)
         *
         * @since API 5
         */
        OFICIO,

        /**
         * RA0 (860mm x 1220mm)
         *
         * @since API 5
         */
        RA0,

        /**
         * RA1 (610mm x 860mm)
         *
         * @since API 5
         */
        RA1,

        /**
         * RA2 (430mm x 610mm)
         *
         * @since API 5
         */
        RA2,

        /**
         * RA3 (305mm x 430mm)
         *
         * @since API 5
         */
        RA3,

        /**
         * RA4 (215mm x 305mm)
         *
         * @since API 5
         */
        RA4,

        /**
         * SRA0 (900mm x 1280mm)
         *
         * @since API 5
         */
        SRA0,

        /**
         * SRA1 (640mm x 900mm)
         *
         * @since API 5
         */
        SRA1,

        /**
         * SRA2 (450mm x 640mm)
         *
         * @since API 5
         */
        SRA2,

        /**
         * SRA3 (320mm x 450mm)
         *
         * @since API 5
         */
        SRA3,

        /**
         * SRA4 (225mm x 320mm)
         *
         * @since API 5
         */
        SRA4,

        /**
         * Super B (13inch x 19inch)
         *
         * @since API 5
         */
        SUPER_B,

        /**
         * Index card (100mm x 150mm)
         *
         * @since API 5
         */
        INDEXCARD,

        /**
         * An indeterminable size due to lack of sensors
         *
         * @since API 5
         */
        UNKNOWN,

        /**
         * Any custom size
         *
         * @since API 5
         */
        ANY_CUSTOM,

        /**
         * An indeterminable envelope size due to lack of sensors
         *
         * @since API 5
         */
        UNKNOWN_ENVELOP;

        /**
         * Retrieves the height of paper
         *
         * @return <p>supported height from {@link PaperSize}.
         * <ul>
         * <li>Return the supported height</li>
         * <li>Return can be 0.0 if PaperSize height is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getHeight() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getHeight();
                }
            }
            return 0;
        }

        /**
         * Retrieves the width of paper
         *
         * @return <p>supported width from {@link PaperSize}.
         * <ul>
         * <li>Return the supported width</li>
         * <li>Return can be 0.0 if PaperSize width is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public double getWidth() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getWidth();
                }
            }
            return 0;
        }

        /**
         * Retrieves the unit of paper
         *
         * @return <p>supported unit from {@link PaperSize}.
         * <ul>
         * <li>Return the supported unit (mm or inch or cm) according to paper size.</li>
         * <li>Return can be null if PaperSize unit is not supported</li>
         * </ul>
         * </p>
         *
         * @since API 8
         */
        public String getUnit() {
            for (com.hp.workpath.api.PaperSize ps : com.hp.workpath.api.PaperSize.values()) {
                if (this.name().equals(ps.name())) {
                    return ps.getUnit();
                }
            }
            return null;
        }
    }

    /**
     * Paper Source defines the tray that feeds the copy output.
     *
     * @since API 3
     */
    public enum PaperSource {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Use printer auto selection
         *
         * @since API 3
         */
        AUTO,

        /**
         * Use tray 1
         *
         * @since API 3
         */
        TRAY_1,

        /**
         * Use tray 2
         *
         * @since API 3
         */
        TRAY_2,

        /**
         * Use tray 3
         *
         * @since API 3
         */
        TRAY_3,

        /**
         * Use tray 4
         *
         * @since API 3
         */
        TRAY_4,

        /**
         * Use tray 5
         *
         * @since API 3
         */
        TRAY_5,

        /**
         * Use manual feeder
         *
         * @since API 3
         */
        MANUAL_FEED,

        /**
         * Automatic Document Feeder
         *
         * @since API 5
         */
        ADF,

        /**
         * Envelope Feed
         *
         * @since API 5
         */
        ENVELOPE_FEED,

        /**
         * Flatbed/Glass
         *
         * @since API 5
         */
        FLATBED,

        /**
         * Tray 6
         *
         * @since API 5
         */
        TRAY_6,

        /**
         * Tray 7
         *
         * @since API 5
         */
        TRAY_7,

        /**
         * Tray 8
         *
         * @since API 5
         */
        TRAY_8,

        /**
         * Tray 9
         *
         * @since API 5
         */
        TRAY_9,

        /**
         * Tray 10
         *
         * @since API 5
         */
        TRAY_10,

        /**
         * Tray 11
         *
         * @since API 5
         */
        TRAY_11,

        /**
         * Tray 12
         *
         * @since API 5
         */
        TRAY_12,

        /**
         * Tray 13
         *
         * @since API 5
         */
        TRAY_13,

        /**
         * Tray 14
         *
         * @since API 5
         */
        TRAY_14,

        /**
         * Tray 15
         *
         * @since API 5
         */
        TRAY_15,

        /**
         * Tray 16
         *
         * @since API 5
         */
        TRAY_16,

        /**
         * Duplexer
         *
         * @since API 5
         */
        DUPLEXER,

        /**
         * External
         *
         * @since API 5
         */
        EXTERNAL,

        /**
         * External tray 1
         *
         * @since API 5
         */
        EXTERNAL_TRAY_1,

        /**
         * External tray 2
         *
         * @since API 5
         */
        EXTERNAL_TRAY_2,

        /**
         * External tray 3
         *
         * @since API 5
         */
        EXTERNAL_TRAY_3,

        /**
         * External tray 4
         *
         * @since API 5
         */
        EXTERNAL_TRAY_4,

        /**
         * External tray 5
         *
         * @since API 5
         */
        EXTERNAL_TRAY_5,

        /**
         * External tray 6
         *
         * @since API 5
         */
        EXTERNAL_TRAY_6,

        /**
         * External tray 7
         *
         * @since API 5
         */
        EXTERNAL_TRAY_7,

        /**
         * External tray 8
         *
         * @since API 5
         */
        EXTERNAL_TRAY_8,

        /**
         * External tray 9
         *
         * @since API 5
         */
        EXTERNAL_TRAY_9,

        /**
         * External tray 10
         *
         * @since API 5
         */
        EXTERNAL_TRAY_10,

        /**
         * Multipurpose Tray
         *
         * @since API 5
         */
        MULTIPURPOSE_TRAY,

        /**
         * Photo tray
         *
         * @since API 5
         */
        PHOTO_TRAY,

        /**
         * Rear manual feed
         *
         * @since API 5
         */
        REAR_MANUAL_FEED,

        /**
         * Roll 1
         *
         * @since API 5
         */
        ROLL_1,

        /**
         * Roll 2
         *
         * @since API 5
         */
        ROLL_2,

        /**
         * Roll 3
         *
         * @since API 5
         */
        ROLL_3,

        /**
         * Roll 4
         *
         * @since API 5
         */
        ROLL_4,

        /**
         * Envelope feed job settings
         *
         * @since API 5
         */
        ENVELOPE_FEED_JOB_SETTINGS,

        /**
         * Tray 1 job settings
         *
         * @since API 5
         */
        TRAY_1_JOB_SETTINGS
    }

    /**
     * Scale Mode controls reduce-enlarge mode of a scanned image.
     *
     * @since API 3
     */
    public enum ScaleMode {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Automatic based on media size
         *
         * @since API 3
         */
        AUTO,

        /**
         * Include margins when automatic calculation of reduction/enlargement
         *
         * @since API 3
         */
        AUTO_MARGINS_INCLUDED,

        /**
         * Reduce/Enlarge manually adjustable
         *
         * @since API 3
         */
        MANUAL
    }

    /**
     * <p>Job Assembly Mode controls whether to allow multiple scan segments assembled
     * into a single job when completed.</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum JobAssemblyMode {
        /**
         * Default value
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Scan only one segment per job
         *
         * @since API 3
         */
        OFF,

        /**
         * Scan multiple segments, prompting the walk-up user for another segment at the end of each segment,
         * and assembling all segments into a single job. Scan settings cannot be adjusted between segments.
         * This mode is intended for jobs where the number of sheets exceeds the capacity of the ADF
         *
         * @since API 3
         */
        ON
    }

    /**
     * <p>Job Execution Mode controls whether to print the scanned images or store as a stored job</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum JobExecutionMode {
        /**
         * Scan and print
         *
         * @since API 3
         */
        NORMAL,

        /**
         * Scan and store in job storage to release later
         *
         * @since API 3
         */
        STORE
    }

    /**
     * Number Up Mode controls how many scanned images are placed per printer sheet.
     *
     * @since API 3
     */
    public enum NumberUpMode {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * One image per sheet
         *
         * @since API 3
         */
        ONE_UP,

        /**
         * Two images per sheet
         *
         * @since API 3
         */
        TWO_UP,

        /**
         * Four images per sheet
         *
         * @since API 3
         */
        FOUR_UP,

        /**
         * Eight images per sheet
         *
         * @since API 3
         */
        EIGHT_UP,

        /**
         * Sixteen images per sheet
         *
         * @since API 3
         */
        SIXTEEN_UP,

        /**
         * Thirty two images per sheet
         *
         * @since API 3
         */
        THIRTY_TWO
    }

    /**
     * Number Up Direction controls the order of images placed on a printed sheet.
     *
     * @since API 3
     */
    public enum NumberUpDirection {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * From top to bottom and from right to left
         *
         * @since API 3
         */
        TO_BOTTOM_TO_LEFT,

        /**
         * from top to bottom and from left to right
         *
         * @since API 3
         */
        TO_BOTTOM_TO_RIGHT,

        /**
         * From right to left and from top to bottom
         *
         * @since API 3
         */
        TO_LEFT_TO_BOTTOM,

        /**
         * From right to left and bottom to top
         *
         * @since API 3
         */
        TO_LEFT_TO_TOP,

        /**
         * From left to right and from top to bottom
         *
         * @since API 3
         */
        TO_RIGHT_TO_BOTTOM,

        /**
         * From left to right and from bottom to top
         *
         * @since API 3
         */
        TO_RIGHT_TO_TOP,

        /**
         * From bottom to top and from right to left
         *
         * @since API 3
         */
        TO_TOP_TO_LEFT,

        /**
         * From bottom to top and from left to right
         *
         * @since API 3
         */
        TO_TOP_TO_RIGHT
    }

    /**
     * Retention mode for stored copy job supported by current printer.
     *
     * @since API 3
     */
    public enum RetentionMode {
        /**
         * Use printer's settings.
         *
         * @since API 3
         */
        DEFAULT,

        /**
         * Don't delete
         *
         * @since API 3
         */
        KEEP,

        /**
         * Delete
         *
         * @since API 3
         */
        DELETE
    }

    /**
     * The paper type for printing.
     *
     * @since API 3
     */
    public enum PaperType {
        /**
         * Sets device's settings.
         *
         * @since API 3
         */
        DEFAULT,
        /**
         * Card Stock 176-220g
         *
         * @since API 3
         */
        CARD_STOCK,
        /**
         * Card Stock Glossy 176-220g
         *
         * @since API 3
         */
        CARD_STOCK_GLOSSY,
        /**
         * HP EcoFFICIENT
         *
         * @since API 3
         */
        HP_ECOFFICIENT,
        /**
         * HP Glossy 120g
         *
         * @since API 3
         */
        HP_GLOSSY_120G,
        /**
         * HP Glossy 150g
         *
         * @since API 3
         */
        HP_GLOSSY_150G,
        /**
         * HP Glossy 200g
         *
         * @since API 3
         */
        HP_GLOSSY_200G,
        /**
         * HP Matte 105g
         *
         * @since API 3
         */
        HP_MATTE_105G,
        /**
         * HP Matte 120g
         *
         * @since API 3
         */
        HP_MATTE_120G,
        /**
         * HP Matter 150g
         *
         * @since API 3
         */
        HP_MATTE_150G,
        /**
         * HP Matte 200g
         *
         * @since API 3
         */
        HP_MATTE_200G,
        /**
         * HP Matte 90g
         *
         * @since API 3
         */
        HP_MATTE_90G,
        /**
         * HP SoftGloss 120g
         *
         * @since API 3
         */
        HP_SOFT_GLOSS_120G,
        /**
         * Extra Heavy 131-175g
         *
         * @since API 3
         */
        EXTRA_HEAVY,
        /**
         * Extra Heavy Glossy 131-175g
         *
         * @since API 3
         */
        EXTRA_HEAVY_GLOSSY,
        /**
         * Heavy 111-130g
         *
         * @since API 3
         */
        HEAVY,
        /**
         * Mid-weight 96-110g
         *
         * @since API 3
         */
        MID_WEIGHT,
        /**
         * Intermediate 85-95g
         *
         * @since API 3
         */
        INTERMEDIATE,
        /**
         * Light 60-74g
         *
         * @since API 3
         */
        LIGHT,
        /**
         * Recycled
         *
         * @since API 3
         */
        RECYCLED,
        /**
         * Rough
         *
         * @since API 3
         */
        ROUGH,
        /**
         * Heavy Rough
         *
         * @since API 3
         */
        HEAVY_ROUGH,
        /**
         * Envelope
         *
         * @since API 3
         */
        ENVELOPE,
        /**
         * Heavy Envelope
         *
         * @since API 3
         */
        HEAVY_ENVELOPE,
        /**
         * Labels
         *
         * @since API 3
         */
        LABELS,
        /**
         * Plain
         *
         * @since API 3
         */
        PLAIN,
        /**
         * Bond
         *
         * @since API 3
         */
        BOND,
        /**
         * Colored
         *
         * @since API 3
         */
        COLORED,
        /**
         * Letterhead
         *
         * @since API 3
         */
        LETTERHEAD,
        /**
         * Preprinted
         *
         * @since API 3
         */
        PREPRINTED,
        /**
         * Prepunched
         *
         * @since API 3
         */
        PREPUNCHED,
        /**
         * Transparency
         *
         * @since API 3
         */
        TRANSPARENCY,
        /**
         * HP Advanced Photo
         *
         * @since API 3
         */
        HP_ADVANCED_PHOTO,
        /**
         * HP Brochure Glossy
         *
         * @since API 3
         */
        HP_BROCHURE_GLOSSY,
        /**
         * HP Brochure Matte 180g
         *
         * @since API 3
         */
        HP_BROCHURE_MATTE_180G,
        /**
         * HP InkJet Matte 120g (Premium Presentation)
         *
         * @since API 3
         */
        HP_INKJET_MATTE_120G,
        /**
         * Any type
         *
         * @since API 5
         */
        ANY,
        /**
         * Brochure Matte
         *
         * @since API 5
         */
        BROCHURE_MATTE,
        /**
         * Cover Matte
         *
         * @since API 5
         */
        COVER_MATTE,
        /**
         * Heavy Glossy (111 to 130g)
         *
         * @since API 5
         */
        HEAVY_GLOSSY,
        /**
         * Heavy paperboard
         *
         * @since API 5
         */
        HEAVY_PAPERBOARD,
        /**
         * HP Cover Matte (200g)
         *
         * @since API 5
         */
        HP_COVER_MATTE_200G,
        /**
         * HP Everyday Photo Matte
         *
         * @since API 5
         */
        HP_EVERYDAY_PHOTO_MATTE,
        /**
         * HP Glossy Edgeline 180G
         *
         * @since API 5
         */
        HP_GLOSSY_EDGELINE_180G,
        /**
         * HP Matte Brochure And Flyer (180g)
         *
         * @since API 5
         */
        HP_MATTE_BROCHURE_AND_FLYER_180G,
        /**
         * HP Photo
         *
         * @since API 5
         */
        HP_PHOTO,
        /**
         * HP Photo Plus
         *
         * @since API 5
         */
        HP_PHOTO_PLUS,
        /**
         * HP Premium Inkjet Transparency
         *
         * @since API 5
         */
        HP_PREMIUM_INKJET_TRANSPARENCY,
        /**
         * HP Premium Matte (120g)
         *
         * @since API 5
         */
        HP_PREMIUM_MATTE_120G,
        /**
         * HP Tough
         *
         * @since API 5
         */
        HP_TOUGH,
        /**
         * HP Trifold Glossy (160g)
         *
         * @since API 5
         */
        HP_TRIFOLD_GLOSSY_160G,
        /**
         * Light bond
         *
         * @since API 5
         */
        LIGHT_BOND,
        /**
         * Light paperboard
         *
         * @since API 5
         */
        LIGHT_PAPERBOARD,
        /**
         * Light rough
         *
         * @since API 5
         */
        LIGHT_ROUGH,
        /**
         * Matte
         *
         * @since API 5
         */
        MATTE,
        /**
         * Midweight Gloss (96 to 110g)
         *
         * @since API 5
         */
        MID_WEIGHT_GLOSSY,
        /**
         * Opaque Film
         *
         * @since API 5
         */
        OPAQUE_FILM,
        /**
         * Paperboard
         *
         * @since API 5
         */
        PAPERBOARD,
        /**
         * Photo
         *
         * @since API 5
         */
        PHOTO,
        /**
         * Shelf Edge Labels
         *
         * @since API 5
         */
        SHELF_EDGE_LABELS,
        /**
         * Tab
         *
         * @since API 5
         */
        TAB,
        /**
         * Thick Plain
         *
         * @since API 5
         */
        THICK_PLAIN,
        /**
         * Vellum
         *
         * @since API 5
         */
        VELLUM,
        /**
         * User defined type 1
         *
         * @since API 5
         */
        USER_DEFINED_1,
        /**
         * User defined type 2
         *
         * @since API 5
         */
        USER_DEFINED_2,
        /**
         * User defined type 3
         *
         * @since API 5
         */
        USER_DEFINED_3,
        /**
         * User defined type 4
         *
         * @since API 5
         */
        USER_DEFINED_4,
        /**
         * User defined type 5
         *
         * @since API 5
         */
        USER_DEFINED_5,
        /**
         * User defined type 6
         *
         * @since API 5
         */
        USER_DEFINED_6,
        /**
         * User defined type 7
         *
         * @since API 5
         */
        USER_DEFINED_7,
        /**
         * User defined type 8
         *
         * @since API 5
         */
        USER_DEFINED_8,
        /**
         * User defined type 9
         *
         * @since API 5
         */
        USER_DEFINED_9,
        /**
         * User defined type 10
         *
         * @since API 5
         */
        USER_DEFINED_10,
        /**
         * User defined type 11
         *
         * @since API 5
         */
        USER_DEFINED_11,
        /**
         * User defined type 12
         *
         * @since API 5
         */
        USER_DEFINED_12,
        /**
         * User defined type 13
         *
         * @since API 5
         */
        USER_DEFINED_13,
        /**
         * User defined type 14
         *
         * @since API 5
         */
        USER_DEFINED_14,
        /**
         * User defined type 15
         *
         * @since API 5
         */
        USER_DEFINED_15,
        /**
         * User defined type 16
         *
         * @since API 5
         */
        USER_DEFINED_16,
        /**
         * Auto
         *
         * @since API 5
         */
        AUTO,
        /**
         * Heavy bond
         *
         * @since API 5
         */
        HEAVY_BOND

    }

    /**
     * <p>Enumeration of text versus graphics optimization settings</p>
     *
     * @since API 3
     */
    @SuppressWarnings("unused")
    public enum TextGraphicsOptimization {
        /**
         * Default value
         *
         * @since API
         */
        DEFAULT,

        /**
         * Optimize for all text.
         *
         * @since API 3
         */
        TEXT,

        /**
         * Optimize for mixed text with graphics.
         *
         * @since API 3
         */
        MIXED,

        /**
         * Optimize for all graphics.
         *
         * @since API 3
         */
        GRAPHIC,

        /**
         * Optimize for photograph.
         *
         * @since API 3
         */
        PHOTOGRAPH,

        /**
         * Automatically detect.
         *
         * @since API 3
         */
        AUTODETECT
    }

    /**
     * <p>Output bin options suppprted by the current printer</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum OutputBin {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Accessory bin
         *
         * @since API 5
         */
        ACCESSORY,

        /**
         * ADF output bin
         *
         * @since API 5
         */
        ADF,

        /**
         * Printer selected output bin
         *
         * @since API 5
         */
        AUTO,

        /**
         * Booklet bin
         *
         * @since API 5
         */
        BOOKLET,

        /**
         * Document feeder output bin
         *
         * @since API 5
         */
        DOCUMENT_FEEDER,

        /**
         * External bin
         *
         * @since API 5
         */
        EXTERNAL,

        /**
         * Face down bin
         *
         * @since API 5
         */
        FACE_DOWN,

        /**
         * Face down bin correct order
         *
         * @since API 5
         */
        FACE_DOWN_CORRECT_ORDER,

        /**
         * Face up bin
         *
         * @since API 5
         */
        FACE_UP,

        /**
         * Face up bin with the straightest path
         *
         * @since API 5
         */
        FACE_UP_STRAIGHTEST_PATH,

        /**
         * Fax output bin
         *
         * @since API 5
         */
        FAX,

        /**
         * Folded output bin
         *
         * @since API 5
         */
        FOLDED,

        /**
         * Left bin
         *
         * @since API 5
         */
        LEFT,

        /**
         * Left bin with the straightest path
         *
         * @since API 5
         */
        LEFT_STRAIGHTEST_PATH,

        /**
         * Lower bin
         *
         * @since API 5
         */
        LOWER,

        /**
         * Lower booklet bin
         *
         * @since API 5
         */
        LOWER_BOOKLET,

        /**
         * Lower left bin
         *
         * @since API 5
         */
        LOWER_LEFT,

        /**
         * Lower left highest capacity bin
         *
         * @since API 5
         */
        LOWER_LEFT_HIGHEST_CAPACITY,

        /**
         * Lower stacker bin
         *
         * @since API 5
         */
        LOWER_STACKER,

        /**
         * Main copier bin
         *
         * @since API 5
         */
        MAIN_COPIER,

        /**
         * Middle bin
         *
         * @since API 5
         */
        MIDDLE,

        /**
         * Middle left bin
         *
         * @since API 5
         */
        MIDDLE_LEFT,

        /**
         * Output bin 1
         *
         * @since API 5
         */
        OUTPUT_BIN_1,

        /**
         * Output bin 2
         *
         * @since API 5
         */
        OUTPUT_BIN_2,

        /**
         * Output bin 3
         *
         * @since API 5
         */
        OUTPUT_BIN_3,

        /**
         * Output bin 4
         *
         * @since API 5
         */
        OUTPUT_BIN_4,

        /**
         * Output bin 5
         *
         * @since API 5
         */
        OUTPUT_BIN_5,

        /**
         * Output bin 6
         *
         * @since API 5
         */
        OUTPUT_BIN_6,

        /**
         * Output bin 7
         *
         * @since API 5
         */
        OUTPUT_BIN_7,

        /**
         * Output bin 8
         *
         * @since API 5
         */
        OUTPUT_BIN_8,

        /**
         * Output bin 9
         *
         * @since API 5
         */
        OUTPUT_BIN_9,

        /**
         * Output bin 10
         *
         * @since API 5
         */
        OUTPUT_BIN_10,

        /**
         * Output bin 11
         *
         * @since API 5
         */
        OUTPUT_BIN_11,

        /**
         * Output bin 12
         *
         * @since API 5
         */
        OUTPUT_BIN_12,

        /**
         * Output bin 13
         *
         * @since API 5
         */
        OUTPUT_BIN_13,

        /**
         * Output bin 14
         *
         * @since API 5
         */
        OUTPUT_BIN_14,

        /**
         * Output bin 15
         *
         * @since API 5
         */
        OUTPUT_BIN_15,

        /**
         * Output bin 16
         *
         * @since API 5
         */
        OUTPUT_BIN_16,

        /**
         * Rear bin
         *
         * @since API 5
         */
        REAR,

        /**
         * Rear face up bin
         *
         * @since API 5
         */
        REAR_FACE_UP,

        /**
         * Rear bin with the straightest path
         *
         * @since API 5
         */
        REAR_STRAIGHTEST_PATH,

        /**
         * Stacker
         *
         * @since API 5
         */
        STACKER,

        /**
         * Standard bin
         *
         * @since API 5
         */
        STANDARD,

        /**
         * Standard bin correct order
         *
         * @since API 5
         */
        STANDARD_CORRECT_ORDER,

        /**
         * Standard top bin
         *
         * @since API 5
         */
        STANDARD_TOP,

        /**
         * Top bin
         *
         * @since API 5
         */
        TOP,

        /**
         * Upper bin
         *
         * @since API 5
         */
        UPPER,

        /**
         * Upper face up bin
         *
         * @since API 5
         */
        UPPER_FACE_UP,

        /**
         * Upper left bin
         *
         * @since API 5
         */
        UPPER_LEFT,

        /**
         * Upper left bins
         *
         * @since API 5
         */
        UPPER_LEFT_BINS,

        /**
         * Upper left bin with the straightest path
         *
         * @since API 5
         */
        UPPER_LEFT_STRAIGHTEST_PATH,

        /**
         * Bins 1 to 3 (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_BINS_1TO3,

        /**
         * Bins 1 to 5 (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_BINS_1TO5,

        /**
         * Bins 1 to 8 (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_BINS_1TO8,

        /**
         * Bins 1 to 10 (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_BINS_1TO10,

        /**
         * Bins 2 to 8 (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_BINS_2TO8,

        /**
         * Finisher Bins (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_FINISHER_BINS,

        /**
         * Left Bins (virtual bin)
         *
         * @since API 5
         */
        VIRTUAL_LEFT_BINS,

        /**
         * An alternate output bin
         *
         * @since API 5
         */
        ALTERNATE,

        /**
         * The bottom output bin
         *
         * @since API 5
         */
        BOTTOM,

        /**
         * The center output bin
         *
         * @since API 5
         */
        CENTER,

        /**
         * A collator output bin
         *
         * @since API 5
         */
        COLLATOR,

        /**
         * A duplexer output bin
         *
         * @since API 5
         */
        DUPLEXER,

        /**
         * Engine optional bin 1
         *
         * @since API 5
         */
        ENGINE_OPTIONAL_BIN_1,

        /**
         * Large capacity bin
         *
         * @since API 5
         */
        LARGE_CAPACITY,

        /**
         * A custom mailbox bin
         *
         * @since API 5
         */
        MY_MAILBOX,

        /**
         * The righ output bin
         *
         * @since API 5
         */
        RIGHT,

        /**
         * The side output bin
         *
         * @since API 5
         */
        SIDE,

        /**
         * Stacker face down
         *
         * @since API 5
         */
        STACKER_FACEDOWN,

        /**
         * Stacker face up
         *
         * @since API 5
         */
        STACKER_FACE_UP,

        /**
         * Stacker staples
         *
         * @since API 5
         */
        STACKER_STAPLES,

        /**
         * Universal output bin
         *
         * @since API 5
         */
        UNIVERSAL_OUTPUT_BIN,

        /**
         * Stapler
         *
         * @since API 5
         */
        STAPLER
    }

    /**
     * <p>Progress dialog mode options supported by current printer</p>
     *
     * @since API 5
     */
    @SuppressWarnings("unused")
    public enum ProgressDialogMode {
        /**
         * Default value
         *
         * @since API 5
         */
        DEFAULT,

        /**
         * Off
         *
         * @since API 5
         */
        OFF,

        /**
         * On
         *
         * @since API 5
         */
        ON
    }

    /**
     * <p>Margin unit used for erase edge</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum EraseMarginUnit {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * Feature should use inches
         *
         * @since API 6
         */
        INCHES,

        /**
         * Feature should use millimeters
         *
         * @since API 6
         */
        MILLIMETERS
    }

    /**
     * <p>Capture Mode for Copy</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum CaptureMode {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,

        /** Standard
         *
         * @since API 6
         */
        STANDARD,

        /**
         * Standard add pages
         *
         * @since API 6
         */
        STANDARD_ADD_PAGES,

        /**
         * Book capture
         *
         * @since API 6
         */
        BOOK_CAPTURE,

        /**
         * ID capture prompt both sides
         *
         * @since API 6
         */
        ID_CAPTURE_PROMPT_BOTH_SIDES,

        /**
         * ID capture prompt back side only
         *
         * @since API 6
         */
        ID_CAPTURE_PROMPT_BACK_SIDE_ONLY,
    }

    /**
     * <p>ImageShiftReduceToFit controls the image enlargement and reduction enable/disable</p>
     *
     * @since API 6
     */
    public enum ImageShiftReduceToFit {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,
        /**
         * Enable ImageShiftReduceToFit if user confirms the reduce to fit option,
         * the device will enlarge or reduce depending up on the ScaleMode value during copying
         *
         * @since API 6
         */
        TRUE,
        /**
         * Disable ImageShiftReduceToFit, No changes will be shown on image size during copying
         *
         * @since API 6
         */
        FALSE,
    }

    /**
     * <p>Units used for Image Shifting supported by the current printer</p>
     *
     * @since API 6
     */
    public enum ImageShiftUnits {
        /**
         * Uses the default image shift units configured on the printer.
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * Inch unit used to shift image.
         *
         * @since API 6
         */
        INCHES,

        /**
         * Millimeter unit used to shift image.
         *
         * @since API 6
         */
        MILLIMETERS
    }

    /**
     * <p>Booklet Borders Each Page for copy supported by the current printer</p>
     *
     * @since API 6
     */
    public enum BookletBordersEachPage {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,
        /**
         * Enable BookletBordersEachPage if user confirms the boarder,
         * the device will add boarder in booklet during copying
         *
         * @since API 6
         */
        TRUE,
        /**
         * Disable BookletBordersEachPage, No boarders will be shown on booklet during copying
         *
         * @since API 6
         */
        FALSE
    }

    /**
     * <p>Booklet Finishing Option for copy supported by the current printer</p>
     *
     * @since API 6
     */
    public enum BookletFinishingOption {
        /**
         * Uses the default BookletFinishing Option configured on the printer.
         *
         * @since API 6
         */
        DEFAULT,
        /**
         * Bale
         *
         * @since API 6
         */
        BALE,
        /**
         * Bind
         *
         * @since API 6
         */
        BIND,
        /**
         * Bind bottom
         *
         * @since API 6
         */
        BINDBOTTOM,
        /**
         * Bind left
         *
         * @since API 6
         */
        BINDLEFT,
        /**
         * Bind right
         *
         * @since API 6
         */
        BINDRIGHT,
        /**
         * Bind top
         *
         * @since API 6
         */
        BINDTOP,
        /**
         * Booklet maker
         *
         * @since API 6
         */
        BOOKLETMAKER,
        /**
         * Cover
         *
         * @since API 6
         */
        COVER,
        /**
         * Edge stitch
         *
         * @since API 6
         */
        EDGESTITCH,
        /**
         * Edge stitch bottom
         *
         * @since API 6
         */
        EDGESTITCHBOTTOM,
        /**
         * Edge stitch left
         *
         * @since API 6
         */
        EDGESTITCHLEFT,
        /**
         * Edge stich right
         *
         * @since API 6
         */
        EDGESTICHRIGHT,
        /**
         * Edge stitch top
         *
         * @since API 6
         */
        EDGESTITCHTOP,
        /**
         * Fold
         *
         * @since API 6
         */
        FOLD,
        /**
         * Jog offset
         *
         * @since API 6
         */
        JOGOFFSET,
        /**
         * None
         *
         * @since API 6
         */
        NONE,
        /**
         * Saddle stitch
         *
         * @since API 6
         */
        SADDLESTITCH,
        /**
         * Trim
         *
         * @since API 6
         */
        TRIM,
        /**
         * Other
         *
         * @since API 6
         */
        OTHER
    }

    /**
     * <p>Booklet Format for copy supported by the current printer</p>
     *
     * @since API 6
     */
    public enum BookletFormat {
        /**
         * Uses the default BookletFormat type configured on the printer.
         *
         * @since API 6
         */
        DEFAULT,
        /**
         * Off
         *
         * @since API 6
         */
        OFF,
        /**
         * Left edge
         *
         * @since API 6
         */
        LEFTEDGE,
        /**
         * Right edge
         *
         * @since API 6
         */
        RIGHTEDGE,
        /**
         * Other
         *
         * @since API 6
         */
        OTHER
    }

    /**
     * <p>Staple mode for copy</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum StapleOption {
        /**
         * None
         *
         * @since API 6
         */
        NONE,

        /**
         * Default
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * TopAnyOnePointAny
         *
         * @since API 6
         */
        TOP_ANY_ONE_POINT_ANY,

        /**
         * TopAnyonePointAngled
         *
         * @since API 6
         */
        TOP_ANY_ONE_POINT_ANGLED,

        /**
         * TopLeftOnePointAny
         *
         * @since API 6
         */
        TOP_LEFT_ONE_POINT_ANY,

        /**
         * TopLeftOnePointAngled
         *
         * @since API 6
         */
        TOP_LEFT_ONE_POINT_ANGLED,

        /**
         * TopLeftOnePointHorizontal
         *
         * @since API 6
         */
        TOP_LEFT_ONE_POINT_HORIZONTAL,

        /**
         * TopLeftOnePointVertical
         *
         * @since API 6
         */
        TOP_LEFT_ONE_POINT_VERTICAL,

        /**
         * TopRightOnePointAny
         *
         * @since API 6
         */
        TOP_RIGHT_ONE_POINT_ANY,

        /**
         * TopRightOnePointAngled
         *
         * @since API 6
         */
        TOP_RIGHT_ONE_POINT_ANGLED,

        /**
         * TopRightOnePointHorizontal
         *
         * @since API 6
         */
        TOP_RIGHT_ONE_POINT_HORIZONTAL,

        /**
         * TopRightOnePointVertical
         *
         * @since API 6
         */
        TOP_RIGHT_ONE_POINT_VERTICAL,

        /**
         * BottomAnyOnePointAny
         *
         * @since API 6
         */
        BOTTOM_ANY_ONE_POINT_ANY,

        /**
         * BottomAnyonePointAngled
         *
         * @since API 6
         */
        BOTTOM_ANY_ONE_POINT_ANGLED,

        /**
         * BottomLeftOnePointAny
         *
         * @since API 6
         */
        BOTTOM_LEFT_ONE_POINT_ANY,

        /**
         * BottomLeftOnePointAngled
         *
         * @since API 6
         */
        BOTTOM_LEFT_ONE_POINT_ANGLED,

        /**
         * BottomLeftOnePointHorizontal
         *
         * @since API 6
         */
        BOTTOM_LEFT_ONE_POINT_HORIZONTAL,

        /**
         * BottomLeftOnePointVertical
         *
         * @since API 6
         */
        BOTTOM_LEFT_ONE_POINT_VERTICAL,

        /**
         * BottomRightOnePointAny
         *
         * @since API 6
         */
        BOTTOM_RIGHT_ONE_POINT_ANY,

        /**
         * BottomRightOnePointAngled
         *
         * @since API 6
         */
        BOTTOM_RIGHT_ONE_POINT_ANGLED,

        /**
         * BottomRightOnePointHorizontal
         *
         * @since API 6
         */
        BOTTOM_RIGHT_ONE_POINT_HORIZONTAL,

        /**
         * BottomRightOnePointVertical
         *
         * @since API 6
         */
        BOTTOM_RIGHT_ONE_POINT_VERTICAL,

        /**
         * CenterOnePoint
         *
         * @since API 6
         */
        CENTER_ONE_POINT,

        /**
         * LeftTwoPoints
         *
         * @since API 6
         */
        LEFT_TWO_POINTS,

        /**
         * LeftTwoPointsAny
         *
         * @since API 6
         */
        LEFT_TWO_POINTS_ANY,

        /**
         * RightTwoPoints
         *
         * @since API 6
         */
        RIGHT_TWO_POINTS,

        /**
         * TopTwoPoints
         *
         * @since API 6
         */
        TOP_TWO_POINTS,

        /**
         * BottomTwoPoints
         *
         * @since API 6
         */
        BOTTOM_TWO_POINTS,

        /**
         * CenterTwoPoints
         *
         * @since API 6
         */
        CENTER_TWO_POINTS,

        /**
         * LeftThreePoints
         *
         * @since API 6
         */
        LEFT_THREE_POINTS,

        /**
         * LeftThreePointsAny
         *
         * @since API 6
         */
        LEFT_THREE_POINTS_ANY,

        /**
         * RightThreePoints
         *
         * @since API 6
         */
        RIGHT_THREE_POINTS,

        /**
         * TopThreePoints
         *
         * @since API 6
         */
        TOP_THREE_POINTS,

        /**
         * BottomThreePoints
         *
         * @since API 6
         */
        BOTTOM_THREE_POINTS,

        /**
         * CenterThreePoints
         *
         * @since API 6
         */
        CENTER_THREE_POINTS,

        /**
         * LeftSixPoints
         *
         * @since API 6
         */
        LEFT_SIX_POINTS,

        /**
         * LeftSixPointsAny
         *
         * @since API 6
         */
        LEFT_SIX_POINTS_ANY,

        /**
         * RightSixPoints
         *
         * @since API 6
         */
        RIGHT_SIX_POINTS,

        /**
         * TopSixPoints
         *
         * @since API 6
         */
        TOP_SIX_POINTS,

        /**
         * BottomSixPoints
         *
         * @since API 6
         */
        BOTTOM_SIX_POINTS,

        /**
         * CenterSixPoints
         *
         * @since API 6
         */
        CENTER_SIX_POINTS,

        /**
         * CustomLegacy
         *
         * @since API 6
         */
        CUSTOM_LEGACY,

        /**
         * CustomPointsOption1
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_1,

        /**
         * CustomPointsOption2
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_2,

        /**
         * CustomPointsOption3
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_3,

        /**
         * CustomPointsOption4
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_4,

        /**
         * CustomPointsOption5
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_5,

        /**
         * CustomPointsOption6
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_6,

        /**
         * CustomPointsOption7
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_7,

        /**
         * CustomPointsOption8
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_8,

        /**
         * CustomPointsOption9
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_9,

        /**
         * CustomPointsOption10
         *
         * @since API 6
         */
        CUSTOM_POINTS_OPTION_10,

        /**
         * ExtraOption1
         *
         * @since API 6
         */
        EXTRA_OPTION_1,

        /**
         * ExtraOption2
         *
         * @since API 6
         */
        EXTRA_OPTION_2,

        /**
         * ExtraOption3
         *
         * @since API 6
         */
        EXTRA_OPTION_3,

        /**
         * ExtraOption4
         *
         * @since API 6
         */
        EXTRA_OPTION_4,

        /**
         * ExtraOption5
         *
         * @since API 6
         */
        EXTRA_OPTION_5,

        /**
         * ExtraOption6
         *
         * @since API 6
         */
        EXTRA_OPTION_6,

        /**
         * ExtraOption7
         *
         * @since API 6
         */
        EXTRA_OPTION_7,

        /**
         * ExtraOption8
         *
         * @since API 6
         */
        EXTRA_OPTION_8,

        /**
         * ExtraOption9
         *
         * @since API 6
         */
        EXTRA_OPTION_9,

        /**
         * ExtraOption10
         *
         * @since API 6
         */
        EXTRA_OPTION_10,

        /**
         * Other
         *
         * @since API 6
         */
        OTHER
    }

    /**
     * <p>Punch mode for copy</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum PunchMode {
        /**
         * None
         *
         * @since API 6
         */
        NONE,

        /**
         * Default
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * TwoPointAny
         *
         * @since API 6
         */
        TWO_POINT_ANY,

        /**
         * LeftTwoPointDin
         *
         * @since API 6
         */
        LEFT_TWO_POINT_DIN,

        /**
         * RightTwoPointDin
         *
         * @since API 6
         */
        RIGHT_TWO_POINT_DIN,

        /**
         * TopTwoPointDin
         *
         * @since API 6
         */
        TOP_TWO_POINT_DIN,

        /**
         * BottomTwoPointDin
         *
         * @since API 6
         */
        BOTTOM_TWO_POINT_DIN,

        /**
         * TwoPointDin
         *
         * @since API 6
         */
        TWO_POINT_DIN,

        /**
         * LeftTwoPointUs
         *
         * @since API 6
         */
        LEFT_TWO_POINT_US,

        /**
         * RightTwoPointUs
         *
         * @since API 6
         */
        RIGHT_TWO_POINT_US,

        /**
         * TopTwoPointUs
         *
         * @since API 6
         */
        TOP_TWO_POINT_US,

        /**
         * BottomTwoPointUs
         *
         * @since API 6
         */
        BOTTOM_TWO_POINT_US,

        /**
         * TwoPointUs
         *
         * @since API 6
         */
        TWO_POINT_US,

        /**
         * LeftThreePointUs
         *
         * @since API 6
         */
        LEFT_THREE_POINT_US,

        /**
         * RightThreePointUs
         *
         * @since API 6
         */
        RIGHT_THREE_POINT_US,

        /**
         * TopThreePointUs
         *
         * @since API 6
         */
        TOP_THREE_POINT_US,

        /**
         * BottomThreePointUs
         *
         * @since API 6
         */
        BOTTOM_THREE_POINT_US,

        /**
         * ThreePointUs
         *
         * @since API 6
         */
        THREE_POINT_US,

        /**
         * ThreePointAny
         *
         * @since API 6
         */
        THREE_POINT_ANY,

        /**
         * LeftFourPointDin
         *
         * @since API 6
         */
        LEFT_FOUR_POINT_DIN,

        /**
         * RightFourPointDin
         *
         * @since API 6
         */
        RIGHT_FOUR_POINT_DIN,

        /**
         * BottomFourPointDin
         *
         * @since API 6
         */
        BOTTOM_FOUR_POINT_DIN,

        /**
         * FourPointDin
         *
         * @since API 6
         */
        FOUR_POINT_DIN,

        /**
         * LeftFourPointSwd
         *
         * @since API 6
         */
        LEFT_FOUR_POINT_SWD,

        /**
         * RightFourPointSwd
         *
         * @since API 6
         */
        RIGHT_FOUR_POINT_SWD,

        /**
         * TopFourPointSwd
         *
         * @since API 6
         */
        TOP_FOUR_POINT_SWD,

        /**
         * BottomFourPointSwd
         *
         * @since API 6
         */
        BOTTOM_FOUR_POINT_SWD,

        /**
         * FourPointSwd
         *
         * @since API 6
         */
        FOUR_POINT_SWD,

        /**
         * FourPointAny
         *
         * @since API 6
         */
        FOUR_POINT_ANY,

        /**
         * LeftTwoPoint
         *
         * @since API 6
         */
        LEFT_TWO_POINT,

        /**
         * RightTwoPoint
         *
         * @since API 6
         */
        RIGHT_TWO_POINT,

        /**
         * TopTwoPoint
         *
         * @since API 6
         */
        TOP_TWO_POINT,

        /**
         * BottomTwoPoint
         *
         * @since API 6
         */
        BOTTOM_TWO_POINT,

        /**
         * Other
         *
         * @since API 6
         */
        OTHER
    }

    /**
     * <p>Fold mode for copy</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum FoldMode{
        /**
         * None
         *
         * @since API 6
         */
        NONE,

        /**
         * Default
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * CInwardTop
         *
         * @since API 6
         */
        C_INWARD_TOP,

        /**
         * CInwardBottom
         *
         * @since API 6
         */
        C_INWARD_BOTTOM,

        /**
         * COutwardTop
         *
         * @since API 6
         */
        C_OUTWARD_TOP,

        /**
         * COutwardBottom
         *
         * @since API 6
         */
        C_OUTWARD_BOTTOM,

        /**
         * VInwardTop
         *
         * @since API 6
         */
        V_INWARD_TOP,

        /**
         * VInwardBottom
         *
         * @since API 6
         */
        V_INWARD_BOTTOM,

        /**
         * VOutwardTop
         *
         * @since API 6
         */
        V_OUTWARD_TOP,

        /**
         * VOutwardBottom
         *
         * @since API 6
         */
        V_OUTWARD_BOTTOM,

        /**
         * Other
         *
         * @since API 6
         */
        OTHER
    }


    /**
     * <p>Stamp Position for copy</p>
     *
     * @since API 6
     */
    @SuppressWarnings("unused")
    public enum StampPosition {
        /**
         * Top Left
         *
         * @since API 6
         */
        TOP_LEFT,

        /**
         * Top Center
         *
         * @since API 6
         */
        TOP_CENTER,

        /**
         * Top Right
         *
         * @since API 6
         */
        TOP_RIGHT,

        /**
         * Bottom Left
         *
         * @since API 6
         */
        BOTTOM_LEFT,

        /**
         * Bottom Center
         *
         * @since API 6
         */
        BOTTOM_CENTER,

        /**
         * Bottom Right
         *
         * @since API 6
         */
        BOTTOM_RIGHT


    }

    /**
     * <p>Indicates if watermark should be rotated 45 degrees</p>
     *
     * @since API 6
     */
    public enum WatermarkRotate45 {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * Enable Watermark Rotation,
         *
         * @since API 6
         */

        TRUE,
        /**
         * Disable Watermark Rotation
         *
         * @since API 6
         */
        FALSE,
    }

    /**
     * <p>Indicates if watermark should be OnlyFirstPage</p>
     *
     * @since API 6
     */
    public enum WatermarkOnlyFirstPage {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * Enable Watermark OnlyFirstPage,
         *
         * @since API 6
         */

        TRUE,
        /**
         * Disable Watermark OnlyFirstPage
         *
         * @since API 6
         */
        FALSE,
    }

    /**
     * <p>Indicates the watermark Type.</p>
     *
     * @since API 6
     */
    public enum WatermarkType {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,
        /**
         * Default value
         *
         * @since API 6
         */
        NONE,

        /**
         * Enable Watermark TEXT,
         *
         * @since API 6
         */

        TEXT,
        /**
         * Enable Watermark SECURE
         *
         * @since API 6
         */
        SECURE,
    }

    /**
     * <p>Watermark Message Type for copy</p>
     *
     * @since API 6
     */
    public enum WatermarkMessageType{
        /**
         * Default value
         *
         * @since API 6
         */
        NONE,

        /**
         * Custom Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOM,

        /**
         * CONFIDENTIAL Watermark Message Type,
         *
         * @since API 6
         */
        CONFIDENTIAL,

        /**
         * CUSTOMSTRING1 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING1,

        /**
         * CUSTOMSTRING2 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING2,

        /**
         * CUSTOMSTRING3 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING3,

        /**
         * CUSTOMSTRING4 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING4,

        /**
         * CUSTOMSTRING5 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING5,

        /**
         * CUSTOMSTRING6 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING6,

        /**
         * CUSTOMSTRING7 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING7,

        /**
         * CUSTOMSTRING8 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING8,

        /**
         * CUSTOMSTRING9 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING9,

        /**
         * CUSTOMSTRING10 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING10,

        /**
         * CUSTOMSTRING11 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING11,

        /**
         * CUSTOMSTRING12 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING12,

        /**
         * CUSTOMSTRING13 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING13,

        /**
         * CUSTOMSTRING14 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING14,

        /**
         * CUSTOMSTRING15 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING15,

        /**
         * CUSTOMSTRING16 Watermark Message Type,
         *
         * @since API 6
         */
        CUSTOMSTRING16,

        /**
         * DATEANDTIME Watermark Message Type,
         *
         * @since API 6
         */
        DATEANDTIME,

        /**
         * DRAFT Watermark Message Type,
         *
         * @since API 6
         */
        DRAFT,

        /**
         * IPADDRESS Watermark Message Type,
         *
         * @since API 6
         */
        IPADDRESS,

        /**
         * PAGENUMBER Watermark Message Type,
         *
         * @since API 6
         */
        PAGENUMBER,

        /**
         * PRODUCTINFORMATION Watermark Message Type,
         *
         * @since API 6
         */
        PRODUCTINFORMATION,

        /**
         * SECRET Watermark Message Type,
         *
         * @since API 6
         */
        SECRET,

        /**
         * TOPSECRET Watermark Message Type,
         *
         * @since API 6
         */
        TOPSECRET,

        /**
         * URGENT Watermark Message Type,
         *
         * @since API 6
         */
        URGENT,

        /**
         * USERNAME Watermark Message Type,
         *
         * @since API 6
         */
        USERNAME,

        /**
         * OTHER Watermark Message Type,
         *
         * @since API 6
         */
        OTHER,
    }

    /**
     * <p>Watermark Background Pattern</p>
     *
     * @since API 6
     */
    public enum WatermarkBackgroundPattern {
        /**
         * Default value
         *
         * @since API 6
         */
        DEFAULT,

        /**
         * Scroll Background Pattern
         *
         * @since API 6
         */
        SCROLL,

        /**
         * LEAF Background Pattern
         *
         * @since API 6
         */
        LEAF,

        /**
         * FLAT Background Pattern
         *
         * @since API 6
         */
        FLAT,

        /**
         * OTHER Background Pattern
         *
         * @since API 6
         */
        OTHER,
    }

    final int mVersion;

    final ColorMode mColorMode;

    final Orientation mOrientation;

    final Duplex mScanDuplex;

    final ScanSize mScanSize;

    final Float mScanCustomLength;

    final Float mScanCustomWidth;

    final ScanSource mScanSource;

    final CopyPreview mCopyPreview;

    final BackgroundCleanup mBackgroundCleanup;

    final ContrastAdjustment mContrastAdjustment;

    final DarknessAdjustment mDarknessAdjustment;

    final SharpnessAdjustment mSharpnessAdjustment;

    final Duplex mPrintDuplex;

    final PaperSize mPrintSize;

    final Float mPrintCustomLength;

    final Float mPrintCustomWidth;

    final int mCopies;

    final CollateMode mCollateMode;

    final PaperSource mPaperSource;

    final PaperType mPaperType;

    final ScaleMode mScaleMode;

    final int mScalePercent;

    final TextGraphicsOptimization mTextGraphicsOptimization;

    final JobAssemblyMode mJobAssemblyMode;

    final JobExecutionMode mJobExecutionMode;

    final NumberUpMode mNumberUpMode;

    final NumberUpDirection mNumberUpDirection;

    final String mStoreJobFolderName;

    final String mStoreJobName;

    final RetentionMode mStoredJobRetentionModeOnPowerCycle;

    final RetentionMode mStoredJobRetentionModeOnRelease;

    final JobCredentialsAttributes mStoredJobCredentialsAttributes;

    final OutputBin mOutputBin;

    final ProgressDialogMode mProgressDialogMode;

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

    final ImageShiftReduceToFit mImageShiftReduceToFit;

    final ImageShiftUnits mImageShiftUnits;

    final Float mImageShiftXFront;
    final Float mImageShiftYFront;
    final Float mImageShiftXBack;
    final Float mImageShiftYBack;

    final BookletBordersEachPage mBookletBordersEachPage;

    final BookletFinishingOption mBookletFinishingOption;

    final BookletFormat mBookletFormat;

    final StapleOption mStapleOption;

    final PunchMode mPunchMode;

    final FoldMode mFoldMode;

    final Map<String, StampOption> mStampOptionMap;

    final int mWatermarkDarkness;
    final int mWatermarkTextSize;
    final int mWatermarkTransparency;

    final String mWatermarkText;
    final String mWatermarkBackgroundColor;
    final String mWatermarkFont;
    final String mWatermarkTextColor;

    final WatermarkRotate45 mWatermarkRotate45;
    final WatermarkType mWatermarkType;
    final WatermarkOnlyFirstPage mWatermarkOnlyFirstPage ;
    final WatermarkMessageType mWatermarkMessageType;
    final WatermarkBackgroundPattern mWatermarkBackgroundPattern;

    @SuppressLint("RestrictedApi")
    private CopyAttributes(Parcel in) {
        // The version is used to support compatibility. It must be the first in the parcel. If a new attribute is added, then logic needs to be added
        // to the end of this constructor. The constructor will compare the version passed with the version supported and handle the compatibility. This
        // means that if the version passed is less than the version of the reader, then the reader will read all values up to the version passed.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mColorMode = ColorMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ColorMode)in.readSerializable()).name());
        mOrientation = Orientation.valueOf(((com.hp.workpath.api.copier.CopyAttributes.Orientation)in.readSerializable()).name());
        mScanDuplex = Duplex.valueOf(((com.hp.workpath.api.copier.CopyAttributes.Duplex)in.readSerializable()).name());
        mScanSize = ScanSize.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ScanSize)in.readSerializable()).name());
        mScanCustomLength = (Float) in.readSerializable();
        mScanCustomWidth = (Float) in.readSerializable();
        mScanSource = ScanSource.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ScanSource)in.readSerializable()).name());
        mCopyPreview = CopyPreview.valueOf(((com.hp.workpath.api.copier.CopyAttributes.CopyPreview)in.readSerializable()).name());
        mBackgroundCleanup = BackgroundCleanup.valueOf(((com.hp.workpath.api.copier.CopyAttributes.BackgroundCleanup)in.readSerializable()).name());
        mContrastAdjustment = ContrastAdjustment.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ContrastAdjustment)in.readSerializable()).name());
        mDarknessAdjustment = DarknessAdjustment.valueOf(((com.hp.workpath.api.copier.CopyAttributes.DarknessAdjustment)in.readSerializable()).name());
        mSharpnessAdjustment = SharpnessAdjustment.valueOf(((com.hp.workpath.api.copier.CopyAttributes.SharpnessAdjustment)in.readSerializable()).name());
        mPrintDuplex = Duplex.valueOf(((com.hp.workpath.api.copier.CopyAttributes.Duplex)in.readSerializable()).name());
        mPrintSize = PaperSize.valueOf(((com.hp.workpath.api.copier.CopyAttributes.PaperSize)in.readSerializable()).name());
        mPrintCustomLength = (Float) in.readSerializable();
        mPrintCustomWidth = (Float) in.readSerializable();
        mCopies = in.readInt();
        mCollateMode = CollateMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.CollateMode)in.readSerializable()).name());
        mPaperSource = PaperSource.valueOf(((com.hp.workpath.api.copier.CopyAttributes.PaperSource)in.readSerializable()).name());
        mPaperType = PaperType.valueOf(((com.hp.workpath.api.copier.CopyAttributes.PaperType)in.readSerializable()).name());
        mScaleMode = ScaleMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ScaleMode)in.readSerializable()).name());
        mScalePercent = in.readInt();
        mTextGraphicsOptimization = TextGraphicsOptimization.valueOf(((com.hp.workpath.api.copier.CopyAttributes.TextGraphicsOptimization)in.readSerializable()).name());
        mJobAssemblyMode = JobAssemblyMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.JobAssemblyMode)in.readSerializable()).name());
        mJobExecutionMode = JobExecutionMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.JobExecutionMode)in.readSerializable()).name());
        mNumberUpMode = NumberUpMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.NumberUpMode)in.readSerializable()).name());
        mNumberUpDirection = NumberUpDirection.valueOf(((com.hp.workpath.api.copier.CopyAttributes.NumberUpDirection)in.readSerializable()).name());
        mStoreJobFolderName = in.readString();
        mStoreJobName = in.readString();
        com.hp.workpath.api.copier.CopyAttributes.RetentionMode retentionMode = ((com.hp.workpath.api.copier.CopyAttributes.RetentionMode)in.readSerializable());
        if(retentionMode == null) {
            retentionMode = com.hp.workpath.api.copier.CopyAttributes.RetentionMode.DEFAULT;
        }
        mStoredJobRetentionModeOnPowerCycle = RetentionMode.valueOf(retentionMode.name());

        com.hp.workpath.api.copier.CopyAttributes.RetentionMode retentionModeForRelease = (com.hp.workpath.api.copier.CopyAttributes.RetentionMode)in.readSerializable();
        if(retentionModeForRelease == null) {
            retentionModeForRelease = com.hp.workpath.api.copier.CopyAttributes.RetentionMode.DEFAULT;
        }
        mStoredJobRetentionModeOnRelease = RetentionMode.valueOf(retentionModeForRelease.name());

        Parcel parcelForNetworkAttr = Parcel.obtain();
        com.hp.workpath.api.copier.JobCredentialsAttributes storedJobCredentialsAttributes = in.readParcelable(com.hp.workpath.api.copier.JobCredentialsAttributes.class.getClassLoader());
        if(storedJobCredentialsAttributes != null) {
            storedJobCredentialsAttributes.writeToParcel(parcelForNetworkAttr, 0);
            parcelForNetworkAttr.setDataPosition(0);
            mStoredJobCredentialsAttributes = JobCredentialsAttributes.CREATOR.createFromParcel(parcelForNetworkAttr);
        } else {
            mStoredJobCredentialsAttributes = null;
        }

        com.hp.workpath.api.copier.CopyAttributes.OutputBin outputBin = ((com.hp.workpath.api.copier.CopyAttributes.OutputBin)in.readSerializable());
        if (outputBin == null) {
            outputBin = com.hp.workpath.api.copier.CopyAttributes.OutputBin.DEFAULT;
        }
        mOutputBin = OutputBin.valueOf(outputBin.name());

        com.hp.workpath.api.copier.CopyAttributes.ProgressDialogMode progressDialogMode = ((com.hp.workpath.api.copier.CopyAttributes.ProgressDialogMode)in.readSerializable());
        if (progressDialogMode == null) {
            progressDialogMode = com.hp.workpath.api.copier.CopyAttributes.ProgressDialogMode.DEFAULT;
        }
        mProgressDialogMode = ProgressDialogMode.valueOf(progressDialogMode.name());

        if (mVersion >= Sdk.VERSION_LEVEL.SEVEN) {
            mEraseMarginUnit = EraseMarginUnit.valueOf(((com.hp.workpath.api.copier.CopyAttributes.EraseMarginUnit) in.readSerializable()).name());

            mEraseBackBottom = in.readFloat();
            mEraseBackLeft = in.readFloat();
            mEraseBackRight = in.readFloat();
            mEraseBackTop = in.readFloat();
            mEraseFrontBottom = in.readFloat();
            mEraseFrontLeft = in.readFloat();
            mEraseFrontRight = in.readFloat();
            mEraseFrontTop = in.readFloat();

            mCaptureMode = CaptureMode.valueOf(((com.hp.workpath.api.copier.CopyAttributes.CaptureMode)in.readSerializable()).name());

            mImageShiftReduceToFit = ImageShiftReduceToFit.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ImageShiftReduceToFit)in.readSerializable()).name());

            mImageShiftUnits = ImageShiftUnits.valueOf(((com.hp.workpath.api.copier.CopyAttributes.ImageShiftUnits)in.readSerializable()).name());

            mImageShiftXFront = in.readFloat();
            mImageShiftYFront = in.readFloat();
            mImageShiftXBack = in.readFloat();
            mImageShiftYBack = in.readFloat();
            mBookletBordersEachPage = BookletBordersEachPage.valueOf(((com.hp.workpath.api.copier.CopyAttributes.BookletBordersEachPage)in.readSerializable()).name());
            mBookletFinishingOption = BookletFinishingOption.valueOf(((com.hp.workpath.api.copier.CopyAttributes.BookletFinishingOption)in.readSerializable()).name());
            mBookletFormat = BookletFormat.valueOf(((com.hp.workpath.api.copier.CopyAttributes.BookletFormat)in.readSerializable()).name());

        }else {
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

            mImageShiftReduceToFit = ImageShiftReduceToFit.DEFAULT;

            mImageShiftUnits = ImageShiftUnits.DEFAULT;

            mImageShiftXFront = 0.0f;
            mImageShiftYFront = 0.0f;
            mImageShiftXBack = 0.0f;
            mImageShiftYBack = 0.0f;

            mBookletBordersEachPage = BookletBordersEachPage.DEFAULT;

            mBookletFinishingOption = BookletFinishingOption.DEFAULT;

            mBookletFormat = BookletFormat.DEFAULT;
        }

        com.hp.workpath.api.copier.CopyAttributes.StapleOption stapleOption = ((com.hp.workpath.api.copier.CopyAttributes.StapleOption)in.readSerializable());
        if(stapleOption == null){
            stapleOption = com.hp.workpath.api.copier.CopyAttributes.StapleOption.DEFAULT;
        }
        mStapleOption = StapleOption.valueOf(stapleOption.name());

        com.hp.workpath.api.copier.CopyAttributes.PunchMode punchMode = ((com.hp.workpath.api.copier.CopyAttributes.PunchMode)in.readSerializable());
        if(punchMode == null){
            punchMode = com.hp.workpath.api.copier.CopyAttributes.PunchMode.DEFAULT;
        }
        mPunchMode = PunchMode.valueOf(punchMode.name());

        com.hp.workpath.api.copier.CopyAttributes.FoldMode foldMode = ((com.hp.workpath.api.copier.CopyAttributes.FoldMode)in.readSerializable());
        if(foldMode == null){
            foldMode = com.hp.workpath.api.copier.CopyAttributes.FoldMode.DEFAULT;
        }
        mFoldMode = FoldMode.valueOf(foldMode.name());
        mStampOptionMap = in.readHashMap(StampOption.class.getClassLoader());
        mWatermarkDarkness = in.readInt();
        mWatermarkText = in.readString();
        mWatermarkRotate45 = WatermarkRotate45.valueOf(((com.hp.workpath.api.copier.CopyAttributes.WatermarkRotate45)in.readSerializable()).name());
        com.hp.workpath.api.copier.CopyAttributes.WatermarkType watermarkType = ((com.hp.workpath.api.copier.CopyAttributes.WatermarkType)in.readSerializable());
        if (watermarkType == null){
            watermarkType = com.hp.workpath.api.copier.CopyAttributes.WatermarkType.DEFAULT;
        }
        mWatermarkType = WatermarkType.valueOf(watermarkType.name());
        mWatermarkTextSize = in.readInt();
        mWatermarkTransparency = in.readInt();
        mWatermarkBackgroundColor = in.readString();
        mWatermarkFont = in.readString();
        mWatermarkTextColor = in.readString();
        mWatermarkOnlyFirstPage = WatermarkOnlyFirstPage.valueOf(((com.hp.workpath.api.copier.CopyAttributes.WatermarkOnlyFirstPage)in.readSerializable()).name());
        mWatermarkMessageType = WatermarkMessageType.valueOf(((com.hp.workpath.api.copier.CopyAttributes.WatermarkMessageType)in.readSerializable()).name());
        mWatermarkBackgroundPattern = WatermarkBackgroundPattern.valueOf(((com.hp.workpath.api.copier.CopyAttributes.WatermarkBackgroundPattern)in.readSerializable()).name());

    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        // The Sdk version level is used to because changes to this would constitute API level changes. Additionally, this reduces management of
        // <xyz>Attributes versions.

        // Note: The order of these values is CRITICAL. When new values are needed, please add to the end.
        out.writeInt(mVersion);
        out.writeSerializable(mColorMode);
        out.writeSerializable(mOrientation);
        out.writeSerializable(mScanDuplex);
        out.writeSerializable(mScanSize);
        out.writeSerializable(mScanCustomLength);
        out.writeSerializable(mScanCustomWidth);
        out.writeSerializable(mScanSource);
        out.writeSerializable(mCopyPreview);
        out.writeSerializable(mBackgroundCleanup);
        out.writeSerializable(mContrastAdjustment);
        out.writeSerializable(mDarknessAdjustment);
        out.writeSerializable(mSharpnessAdjustment);
        out.writeSerializable(mPrintDuplex);
        out.writeSerializable(mPrintSize);
        out.writeSerializable(mPrintCustomLength);
        out.writeSerializable(mPrintCustomWidth);
        out.writeInt(mCopies);
        out.writeSerializable(mCollateMode);
        out.writeSerializable(mPaperSource);
        out.writeSerializable(mPaperType);
        out.writeSerializable(mScaleMode);
        out.writeInt(mScalePercent);
        out.writeSerializable(mTextGraphicsOptimization);
        out.writeSerializable(mJobAssemblyMode);
        out.writeSerializable(mJobExecutionMode);
        out.writeSerializable(mNumberUpMode);
        out.writeSerializable(mNumberUpDirection);
        out.writeString(mStoreJobFolderName);
        out.writeString(mStoreJobName);
        out.writeSerializable(mStoredJobRetentionModeOnPowerCycle);
        out.writeSerializable(mStoredJobRetentionModeOnRelease);
        out.writeParcelable(mStoredJobCredentialsAttributes, 0);
        out.writeSerializable(mOutputBin);
        out.writeSerializable(mProgressDialogMode);
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
        out.writeSerializable(mImageShiftReduceToFit);
        out.writeSerializable(mImageShiftUnits);
        out.writeFloat(mImageShiftXFront);
        out.writeFloat(mImageShiftYFront);
        out.writeFloat(mImageShiftXBack);
        out.writeFloat(mImageShiftYBack);
        out.writeSerializable(mBookletBordersEachPage);
        out.writeSerializable(mBookletFinishingOption);
        out.writeSerializable(mBookletFormat);
        out.writeSerializable(mStapleOption);
        out.writeSerializable(mPunchMode);
        out.writeSerializable(mFoldMode);
        out.writeMap(mStampOptionMap);

        out.writeInt(mWatermarkDarkness);
        out.writeString(mWatermarkText);
        out.writeSerializable(mWatermarkRotate45);
        out.writeSerializable(mWatermarkType);
        out.writeInt(mWatermarkTextSize);
        out.writeInt(mWatermarkTransparency);
        out.writeString(mWatermarkBackgroundColor);
        out.writeString(mWatermarkFont);
        out.writeString(mWatermarkTextColor);
        out.writeSerializable(mWatermarkOnlyFirstPage);
        out.writeSerializable(mWatermarkMessageType);
        out.writeSerializable(mWatermarkBackgroundPattern);
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
    public static final Creator<CopyAttributes> CREATOR = new Creator<CopyAttributes>() {
        @Override
        public CopyAttributes createFromParcel(Parcel in) {
            return new CopyAttributes(in);
        }

        @Override
        public CopyAttributes[] newArray(int size) {
            return new CopyAttributes[size];
        }
    };

    /**
     * Expresses the {@link CopyAttributes} in a string.
     *
     * @hide trivial
     */
    @Override
    public String toString() {
        return new StringBuilder().append("[").append("ColorMode=").append(((mColorMode != null)?mColorMode.name().toString():"null")).append("]").toString();
    }
}
