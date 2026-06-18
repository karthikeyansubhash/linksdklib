// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.printer;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.PaperSize;
import com.hp.workpath.common.Sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * The sets of attributes for requesting a print.</b>
 * An instance of this class is created using one of the builders.
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class PrintAttributes implements Parcelable {

    /**
     * A collection of the color mode for printing.
     *
     * @since API 1
     */
    public enum ColorMode {
        /**
         * Sets the color mode setting configured on the device.
         */
        DEFAULT,

        /**
         * Color
         *
         * @since API 1
         */
        COLOR,

        /**
         * Black and White.
         *
         * @since API 1
         */
        MONO,

        /**
         * If the job consists of a mixture of Black and Color images (side of a sheet), the device will detect the content of each image and generate appropriate output.
         *
         * @since API 1
         */
        AUTO
    }

    /**
     * A collection of duplex mode for printing.
     *
     * @since API 1
     */
    public enum Duplex {
        /**
         * Sets the duplex settings configured on the device.
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
     * A collection of the configuration of auto fit
     *
     * @since API 1
     */
    public enum AutoFit {
        /**
         * Sets device's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Enable Auto fit.
         *
         * @since API 1
         */
        TRUE,
        /**
         * Disable Auto Fit.
         *
         * @since API 1
         */
        FALSE
    }

    /**
     * A collection of Staple Mode, this option will be ignored if Finishings option is enabled
     *
     * @since API 1
     */
    public enum StapleMode {
        /**
         * Use printer's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * No staple.
         *
         * @since API 1
         */
        NONE,
        /**
         * One staple.
         *
         * @since API 1
         */
        STAPLE,
        /**
         * One staple in top left corner
         *
         * @since API 1
         */
        TOP_LEFT,
        /**
         * One staple in bottom left corner
         *
         * @since API 1
         */
        BOTTOM_LEFT,
        /**
         * One staple in top right corner
         *
         * @since API 1
         */
        TOP_RIGHT,
        /**
         * One staple in bottom right corner
         *
         * @since API 1
         */
        BOTTOM_RIGHT,
        /**
         * Two staples on left side
         *
         * @since API 1
         */
        DUAL_LEFT,
        /**
         * Two staples on right side
         *
         * @since API 1
         */
        DUAL_RIGHT,
        /**
         * Two staples on top side
         *
         * @since API 1
         */
        DUAL_TOP,
        /**
         * Two staples on bottom side
         *
         * @since API 1
         */
        DUAL_BOTTOM,
        /**
         * Hole.
         *
         * @since API 1
         */
        PUNCH,
        /**
         * Cover.
         *
         * @since API 1
         */
        COVER,
        /**
         * Binds the set.
         *
         * @since API 1
         */
        BIND,
        /**
         * Binds the set with one or more staples along the middle fold.
         *
         * @since API 1
         */
        SADDLE_STITCH,
        /**
         * Binds the set with one or more staples along one edge.
         *
         * @since API 1
         */
        EDGE_STITCH,
        /**
         * Binds the set with one or more staples along the left edge.
         *
         * @since API 1
         */
        EDGE_STITCH_LEFT,
        /**
         * Binds the set with one or more staples along the right edge.
         *
         * @since API 1
         */
        EDGE_STITCH_RIGHT,
        /**
         * Binds the set with one or more staples along the top edge.
         *
         * @since API 1
         */
        EDGE_STITCH_TOP,
        /**
         * Binds the set with one or more staples along the bottom edge.
         *
         * @since API 1
         */
        EDGE_STITCH_BOTTOM
    }

    /**
     * A collection of Paper Source
     *
     * @since API 1
     */
    public enum PaperSource {
        /**
         * Sets device's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Sets auto selected paper source
         *
         * @since API 1
         */
        AUTO,
        /**
         * Prints from tray 1
         *
         * @since API 1
         */
        TRAY_1,
        /**
         * Prints from tray 2
         *
         * @since API 1
         */
        TRAY_2,
        /**
         * Prints from tray 3
         *
         * @since API 1
         */
        TRAY_3,
        /**
         * Prints from tray 4
         *
         * @since API 1
         */
        TRAY_4,
        /**
         * Prints from tray 5
         *
         * @since API 1
         */
        TRAY_5,
        /**
         * Prints from tray 6
         *
         * @since API 1
         */
        TRAY_6,
        /**
         * Prints from tray 7
         *
         * @since API 1
         */
        TRAY_7,
        /**
         * Prints from tray 8
         *
         * @since API 1
         */
        TRAY_8,
        /**
         * Prints from tray 9
         *
         * @since API 1
         */
        TRAY_9,
        /**
         * Prints from tray 10
         *
         * @since API 1
         */
        TRAY_10,
        /**
         * Prints from manual feeder
         *
         * @since API 1
         */
        MANUAL_FEED
    }

    /**
     * A collection of Paper Size
     *
     * @since API 1
     */
    public enum PaperSize {
        /**
         * Sets device's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * ISO A3 (297mm x 420mm)
         *
         * @since API 1
         */
        A3,
        /**
         * ISO A4 (210mm x 297mm)
         *
         * @since API 1
         */
        A4,
        /**
         * ISO A5 (148mm x 210mm)
         *
         * @since API 1
         */
        A5,
        /**
         * ISO A6 (105mm x 148mm)
         *
         * @since API 1
         */
        A6,
        /**
         * ISO B4 (250mm x 353mm)
         *
         * @since API 1
         */
        B4,
        /**
         * ISO B5 (176mm x 250mm)
         *
         * @since API 1
         */
        B5,
        /**
         * ISO B6 (125mm x 176mm)
         *
         * @since API 1
         */
        B6,
        /**
         * Envelope 9 (3.875inch x 8.875inch)
         *
         * @since API 5
         */
        ENVELOPE_9,
        /**
         * Envelope Comm10 (4.125inch x 9.5inch)
         *
         * @since API 5
         */
        ENVELOPE_COMM10,
        /**
         * Envelope Monarch (3.875inch x 7.5inch)
         *
         * @since API 5
         */
        ENVELOPE_MONARCH,
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
         * Envelope DL (110mm x 220mm)
         *
         * @since API 5
         */
        ENVELOPE_DL,
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
         * Executive (7.25inch x 10.5inch)
         *
         * @since API 1
         */
        EXECUTIVE,
        /**
         * INCH8POINT5X13 (8.5inch x 13inch)
         *
         * @since API 1
         */
        INCH8POINT5X13,
        /**
         * INCH12X18 (12inch x 18inch)
         *
         * @since API 1
         */
        INCH12X18,
        /**
         * JIS B4 (257mm x 364mm)
         *
         * @since API 1
         */
        JB4,
        /**
         * JIS B5 (182mm x 257mm)
         *
         * @since API 1
         */
        JB5,
        /**
         * JIS B6 (128mm x 182mm)
         *
         * @since API 1
         */
        JB6,
        /**
         * Japanese Double Postcard (148mm x 200mm)
         *
         * @since API 5
         */
        JDOUBLE_POSTCARD,
        /**
         * Japanese Postcard (100mm x 148mm)
         *
         * @since API 5
         */
        JPOSTCARD,
        /**
         * K8 (270mm x 390mm)
         *
         * @since API 5
         */
        K8,
        /**
         * K8_260x368mm (260mm x 368mm)
         *
         * @since API 5
         */
        K8_260x368mm,
        /**
         * K16 (195mm x 270mm)
         *
         * @since API 5
         */
        K16,
        /**
         * K16_184x260mm (184mm x 260mm)
         *
         * @since API 5
         */
        K16_184x260mm,
        /**
         * Ledger (11inch x 17inch)
         *
         * @since API 1
         */
        LEDGER,
        /**
         * Legal (8.5inch x 14inch)
         *
         * @since API 1
         */
        LEGAL,
        /**
         * Letter (8.5inch x 11inch)
         *
         * @since API 1
         */
        LETTER,
        /**
         * Oficio (216mm x 340mm)
         *
         * @since API 5
         */
        OFICIO,
        /**
         * PRC 8K (273mm x 394mm)
         *
         * @since API 1
         */
        PK8,
        /**
         * PRC 16K (197mm x 273mm)
         *
         * @since API 1
         */
        PK16,
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
         * GENERAL_10X15cm (10cm x 15cm)
         *
         * @since API 5
         */
        GENERAL_10X15cm,
        /**
         * GENERAL_3X5in (3inch x 5inch)
         *
         * @since API 5
         */
        GENERAL_3X5in,
        /**
         * GENERAL_4X6in (4inch x 6inch)
         *
         * @since API 5
         */
        GENERAL_4X6in,
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
         * Statement (5.5inch x 8.5inch)
         *
         * @since API 1
         */
        STATEMENT;

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
     * A collection of Paper Type
     *
     * @since API 1
     */
    public enum PaperType {
        /**
         * Sets device's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Bond
         *
         * @since API 1
         */
        BOND,
        /**
         * Card Stock 176-220g
         *
         * @since API 1
         */
        CARD_STOCK,
        /**
         * Card Stock Glossy 176-220g
         *
         * @since API 1
         */
        CARD_STOCK_GLOSSY,
        /**
         * Colored
         *
         * @since API 1
         */
        COLORED,
        /**
         * Envelope
         *
         * @since API 1
         */
        ENVELOPE,
        /**
         * Extra Heavy 131-175g
         *
         * @since API 1
         */
        EXTRA_HEAVY,
        /**
         * Extra Heavy Glossy 131-175g
         *
         * @since API 1
         */
        EXTRA_HEAVY_GLOSSY,
        /**
         * Heavy 111-130g
         *
         * @since API 1
         */
        HEAVY,
        /**
         * Heavy Envelope
         *
         * @since API 1
         */
        HEAVY_ENVELOPE,
        /**
         * Hvy Glossy 111-130g
         *
         * @since API 5
         */
        HEAVY_GLOSSY,
        /**
         * Paperboard 301g+
         *
         * @since API 5
         */
        HEAVY_PAPERBOARD,
        /**
         * Heavy Rough
         *
         * @since API 1
         */
        HEAVY_ROUGH,
        /**
         * HP Advanced Photo
         *
         * @since API 1
         */
        HP_ADVANCED_PHOTO,
        /**
         * HP Brochure Glossy
         *
         * @since API 1
         */
        HP_BROCHURE_GLOSSY,
        /**
         * HP Brochure Matte 180g
         *
         * @since API 1
         */
        HP_BROCHURE_MATTE_180G,
        /**
         * HP EcoFFICIENT
         *
         * @since API 1
         */
        HP_ECOFFICIENT,
        /**
         * HP Glossy 120g
         *
         * @since API 1
         */
        HP_GLOSSY_120G,
        /**
         * HP Glossy 150g
         *
         * @since API 1
         */
        HP_GLOSSY_150G,
        /**
         * HP Glossy 200g
         *
         * @since API 1
         */
        HP_GLOSSY_200G,
        /**
         * HP InkJet Matte 120g (Premium Presentation)
         *
         * @since API 1
         */
        HP_INKJET_MATTE_120G,
        /**
         * HP Matte 90g
         *
         * @since API 1
         */
        HP_MATTE_90G,
        /**
         * HP Matte 105g
         *
         * @since API 1
         */
        HP_MATTE_105G,
        /**
         * HP Matte 120g
         *
         * @since API 1
         */
        HP_MATTE_120G,
        /**
         * HP Matter 150g
         *
         * @since API 1
         */
        HP_MATTE_150G,
        /**
         * HP Matte 200g
         *
         * @since API 1
         */
        HP_MATTE_200G,
        /**
         * HP SoftGloss 120g
         *
         * @since API 1
         */
        HP_SOFT_GLOSS_120G,
        /**
         * Intermediate 85-95g
         *
         * @since API 1
         */
        INTERMEDIATE,
        /**
         * Labels
         *
         * @since API 1
         */
        LABELS,
        /**
         * Letterhead
         *
         * @since API 1
         */
        LETTERHEAD,
        /**
         * Light 60-74g
         *
         * @since API 1
         */
        LIGHT,
        /**
         * Light Bond
         *
         * @since API 5
         */
        LIGHT_BOND,
        /**
         * Light Rough
         *
         * @since API 5
         */
        LIGHT_ROUGH,
        /**
         * Paperboard 221-255g
         *
         * @since API 5
         */
        LIGHT_PAPERBOARD,
        /**
         * Mid-Weight 96-110g
         *
         * @since API 1
         */
        MID_WEIGHT,
        /**
         * Mid-WtGlossy 96-110g
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
         * Paperboard 256-300g
         *
         * @since API 5
         */
        PAPERBOARD,
        /**
         * Plain
         *
         * @since API 1
         */
        PLAIN,
        /**
         * Preprinted
         *
         * @since API 1
         */
        PREPRINTED,
        /**
         * Prepunched
         *
         * @since API 1
         */
        PREPUNCHED,
        /**
         * Recycled
         *
         * @since API 1
         */
        RECYCLED,
        /**
         * Rough
         *
         * @since API 1
         */
        ROUGH,
        /**
         * Tab
         *
         * @since API 5
         */
        TAB,
        /**
         * Transparency
         *
         * @since API 1
         */
        TRANSPARENCY,
        /**
         * Vellum
         *
         * @since API 5
         */
        VELLUM
    }

    /**
     * A collection of Document Format
     *
     * @since API 1
     */
    public enum DocumentFormat {
        /**
         * Sets device's default.
         *
         * @since API 1
         */
        AUTO,
        /**
         * PDF Document.
         *
         * @since API 1
         */
        PDF,
        /**
         * JPEG Image.
         *
         * @since API 1
         */
        JPEG,
        /**
         * TIFF Image.
         *
         * @since API 1
         */
        TIFF,
        /**
         * PCL5 Document.
         *
         * @since API 1
         */
        PCL5,
        /**
         * PCL6 Document.
         *
         * @since API 1
         */
        PCL6,
        /**
         * PostScript Document.
         *
         * @since API 1
         */
        PS,
        /**
         * Text Document.
         *
         * @since API 1
         */
        TEXT
    }

    /**
     * The source for file to print.
     *
     * @since API 1
     */
    public enum Source {
        /**
         * Local storage
         *
         * @since API 1
         */
        STORAGE,

        /**
         * Remote storage from http path
         *
         * @since API 1
         */
        HTTP,

        /**
         * Attached USB
         *
         * @deviceOnly
         * @since API 2
         */
        USB,

        /**
         * Input stream
         *
         * @since API 2
         */
        STREAM
    }

    /**
     * A collection of Collate
     *
     * @since API 1
     */
    public enum CollateMode {
        /**
         * Use printer's settings.
         *
         * @since API 1
         */
        DEFAULT,
        /**
         * Collated
         *
         * @since API 1
         */
        COLLATED,
        /**
         * Uncollated
         *
         * @since API 1
         */
        UNCOLLATED
    }

    /**
     * A collection of Orientation
     *
     * @since API 5
     */
    public enum Orientation {
        /**
         * Use printer's settings.
         *
         * @since API 5
         */
        DEFAULT,
        /**
         * Portrait
         *
         * @since API 5
         */
        PORTRAIT,
        /**
         * Landscape
         *
         * @since API 5
         */
        LANDSCAPE,
        /**
         * Reverse Portrait
         *
         * @since API 5
         */
        REVERSE_PORTRAIT,
        /**
         * Reverse Landscape
         *
         * @since API 5
         */
        REVERSE_LANDSCAPE,
        /**
         * NONE
         *
         * @since API 5
         */
        NONE

    }

    /**
     * A collection of Print-quality (IPP values - draft: 3, Normal: 4, High: 5)
     *
     * @since API 5
     */
    public enum PrintQuality {
        /**
         * Use printer's settings.
         *
         * @since API 5
         */
        DEFAULT,
        /**
         * Draft
         *
         * @since API 5
         */
        DRAFT,
        /**
         * Normal
         *
         * @since API 5
         */
        NORMAL,
        /**
         * High
         *
         * @since API 5
         */
        HIGH

    }

    /**
     * A collection of OutputBin
     *
     * @since API 5
     */
    public enum OutputBin {
        /**
         * Use printer's settings.
         *
         * @since API 5
         */
        DEFAULT,
        /**
         * Auto
         *
         * @since API 5
         */
        AUTO,
        /**
         * Bottom
         *
         * @since API 5
         */
        BOTTOM,
        /**
         * Center
         *
         * @since API 5
         */
        CENTER,
        /**
         * FaceDown
         *
         * @since API 5
         */
        FACE_DOWN,
        /**
         * FaceUp
         *
         * @since API 5
         */
        FACE_UP,
        /**
         * LargeCapacity
         *
         * @since API 5
         */
        LARGE_CAPACITY,
        /**
         * Left
         *
         * @since API 5
         */
        LEFT,
        /**
         * OutputBin1 or Mailbox1
         *
         * @since API 5
         */
        OUTPUT_BIN_1,
        /**
         * OutputBin2 or Mailbox2
         *
         * @since API 5
         */
        OUTPUT_BIN_2,
        /**
         * OutputBin3 or Mailbox3
         *
         * @since API 5
         */
        OUTPUT_BIN_3,
        /**
         * OutputBin4 or Mailbox4
         *
         * @since API 5
         */
        OUTPUT_BIN_4,
        /**
         * OutputBin5 or Mailbox5
         *
         * @since API 5
         */
        OUTPUT_BIN_5,
        /**
         * OutputBin6 or Mailbox6
         *
         * @since API 5
         */
        OUTPUT_BIN_6,
        /**
         * OutputBin7 or Mailbox7
         *
         * @since API 5
         */
        OUTPUT_BIN_7,
        /**
         * OutputBin8 or Mailbox8
         *
         * @since API 5
         */
        OUTPUT_BIN_8,
        /**
         * OutputBin9 or Mailbox9
         *
         * @since API 5
         */
        OUTPUT_BIN_9,
        /**
         * OutputBin10 or Mailbox10
         *
         * @since API 5
         */
        OUTPUT_BIN_10,
        /**
         * Middle
         *
         * @since API 5
         */
        MIDDLE,
        /**
         * MyMailbox
         *
         * @since API 5
         */
        MY_MAILBOX,
        /**
         * Rear
         *
         * @since API 5
         */
        REAR,
        /**
         * Right
         *
         * @since API 5
         */
        RIGHT,
        /**
         * Side
         *
         * @since API 5
         */
        SIDE,
        /**
         * Stacker1
         *
         * @since API 5
         */
        STACKER_1,
        /**
         * Stacker2
         *
         * @since API 5
         */
        STACKER_2,
        /**
         * Stacker3
         *
         * @since API 5
         */
        STACKER_3,
        /**
         * Stacker4
         *
         * @since API 5
         */
        STACKER_4,
        /**
         * Stacker5
         *
         * @since API 5
         */
        STACKER_5,
        /**
         * Stacker6
         *
         * @since API 5
         */
        STACKER_6,
        /**
         * Stacker7
         *
         * @since API 5
         */
        STACKER_7,
        /**
         * Stacker8
         *
         * @since API 5
         */
        STACKER_8,
        /**
         * Stacker9
         *
         * @since API 5
         */
        STACKER_9,
        /**
         * Stacker10
         *
         * @since API 5
         */
        STACKER_10,
        /**
         * Top
         *
         * @since API 5
         */
        TOP,
        /**
         * Tray1
         *
         * @since API 5
         */
        TRAY_1,
        /**
         * Tray2
         *
         * @since API 5
         */
        TRAY_2,
        /**
         * Tray3
         *
         * @since API 5
         */
        TRAY_3,
        /**
         * Tray4
         *
         * @since API 5
         */
        TRAY_4,
        /**
         * Tray5
         *
         * @since API 5
         */
        TRAY_5,
        /**
         * Tray6
         *
         * @since API 5
         */
        TRAY_6,
        /**
         * Tray7
         *
         * @since API 5
         */
        TRAY_7,
        /**
         * Tray8
         *
         * @since API 5
         */
        TRAY_8,
        /**
         * Tray9
         *
         * @since API 5
         */
        TRAY_9,
        /**
         * Tray10
         *
         * @since API 5
         */
        TRAY_10
    }

    /**
     * A collection of finishings
     *
     * @since API 5
     */
    public enum Finishings {
        /**
         * Use printer's settings.
         *
         * @since API 5
         */
        DEFAULT,
        /**
         * No staple.
         *
         * @since API 5
         */
        NONE,
        /**
         * One staple.
         *
         * @since API 5
         */
        STAPLE,
        /**
         * Punch.
         *
         * @since API 5
         */
        PUNCH,
        /**
         * Cover.
         *
         * @since API 5
         */
        COVER,
        /**
         * Binds the set.
         *
         * @since API 5
         */
        BIND,
        /**
         * Binds the set with one or more staples along the middle fold.
         *
         * @since API 5
         */
        SADDLE_STITCH,
        /**
         * Binds the set with one or more staples along one edge.
         *
         * @since API 5
         */
        EDGE_STITCH,
        /**
         * Fold (any type)
         *
         * @since API 5
         */
        FOLD,
        /**
         * Trim (any type)
         *
         * @since API 5
         */
        TRIM,
        /**
         * Bale (any type)
         *
         * @since API 5
         */
        BALE,
        /**
         * Fold to make booklet
         *
         * @since API 5
         */
        BOOKLET_MAKER,
        /**
         * Offset for binding (any type)
         *
         * @since API 5
         */
        JOG_OFFSET,
        /**
         * Apply protective liquid or powder coating
         *
         * @since API 5
         */
        COAT,
        /**
         * Apply protective (solid) material
         *
         * @since API 5
         */
        LAMINATE,
        /**
         * One staple in top left corner
         *
         * @since API 5
         */
        STAPLE_TOP_LEFT,
        /**
         * One staple in bottom left corner
         *
         * @since API 5
         */
        STAPLE_BOTTOM_LEFT,
        /**
         * One staple in top right corner
         *
         * @since API 5
         */
        STAPLE_TOP_RIGHT,
        /**
         * One staple in bottom right corner
         *
         * @since API 5
         */
        STAPLE_BOTTOM_RIGHT,
        /**
         * Binds the set with one or more staples along the left edge.
         *
         * @since API 5
         */
        EDGE_STITCH_LEFT,
        /**
         * Binds the set with one or more staples along the top edge.
         *
         * @since API 5
         */
        EDGE_STITCH_TOP,
        /**
         * Binds the set with one or more staples along the right edge.
         *
         * @since API 5
         */
        EDGE_STITCH_RIGHT,
        /**
         * Binds the set with one or more staples along the bottom edge.
         *
         * @since API 5
         */
        EDGE_STITCH_BOTTOM,
        /**
         * Two staples on left side
         *
         * @since API 5
         */
        STAPLE_DUAL_LEFT,
        /**
         * Two staples on top side
         *
         * @since API 5
         */
        STAPLE_DUAL_TOP,
        /**
         * Two staples on right side
         *
         * @since API 5
         */
        STAPLE_DUAL_RIGHT,
        /**
         * Two staples on bottom side
         *
         * @since API 5
         */
        STAPLE_DUAL_BOTTOM,
        /**
         * Three staples on left
         *
         * @since API 5
         */
        STAPLE_TRIPLE_LEFT,
        /**
         * Three staples on top
         *
         * @since API 5
         */
        STAPLE_TRIPLE_TOP,
        /**
         * Three staples on right
         *
         * @since API 5
         */
        STAPLE_TRIPLE_RIGHT,
        /**
         * Three staples on bottom
         *
         * @since API 5
         */
        STAPLE_TRIPLE_BOTTOM,
        /**
         * Bind on left
         *
         * @since API 5
         */
        BIND_LEFT,
        /**
         * Bind on top
         *
         * @since API 5
         */
        BIND_TOP,
        /**
         * Bind on right
         *
         * @since API 5
         */
        BIND_RIGHT,
        /**
         * Bind on bottom
         *
         * @since API 5
         */
        BIND_BOTTOM,
        /**
         * Trim output after each copy
         *
         * @since API 5
         */
        TRIM_AFTER_COPIES,
        /**
         * Trim output after each document
         *
         * @since API 5
         */
        TRIM_AFTER_DOCUMENTS,
        /**
         * Trim output after job
         *
         * @since API 5
         */
        TRIM_AFTER_JOB,
        /**
         * Trim output after each page
         *
         * @since API 5
         */
        TRIM_AFTER_PAGES,
        /**
         * Punch 1 hole top left
         *
         * @since API 5
         */
        PUNCH_TOP_LEFT,
        /**
         * Punch 1 hole bottom left
         *
         * @since API 5
         */
        PUNCH_BOTTOM_LEFT,
        /**
         * Punch 1 hole top right
         *
         * @since API 5
         */
        PUNCH_TOP_RIGHT,
        /**
         * Punch 1 hole bottom right
         *
         * @since API 5
         */
        PUNCH_BOTTOM_RIGHT,
        /**
         * Punch 2 holes left side
         *
         * @since API 5
         */
        PUNCH_DUAL_LEFT,
        /**
         * Punch 2 holes top edge
         *
         * @since API 5
         */
        PUNCH_DUAL_TOP,
        /**
         * Punch 2 holes right side
         *
         * @since API 5
         */
        PUNCH_DUAL_RIGHT,
        /**
         * Punch 2 holes bottom edge
         *
         * @since API 5
         */
        PUNCH_DUAL_BOTTOM,
        /**
         * Punch 3 holes left side
         *
         * @since API 5
         */
        PUNCH_TRIPLE_LEFT,
        /**
         * Punch 3 holes top edge
         *
         * @since API 5
         */
        PUNCH_TRIPLE_TOP,
        /**
         * Punch 3 holes right side
         *
         * @since API 5
         */
        PUNCH_TRIPLE_RIGHT,
        /**
         * Punch 3 holes bottom edge
         *
         * @since API 5
         */
        PUNCH_TRIPLE_BOTTOM,
        /**
         * Punch 4 holes left side
         *
         * @since API 5
         */
        PUNCH_QUAD_LEFT,
        /**
         * Punch 4 holes top edge
         *
         * @since API 5
         */
        PUNCH_QUAD_TOP,
        /**
         * Punch 4 holes right side
         *
         * @since API 5
         */
        PUNCH_QUAD_RIGHT,
        /**
         * Punch 4 holes bottom edge
         *
         * @since API 5
         */
        PUNCH_QUAD_BOTTOM,
        /**
         * Punch multiple holes left side
         *
         * @since API 5
         */
        PUNCH_MULTIPLE_LEFT,
        /**
         * Punch multiple holes top edge
         *
         * @since API 5
         */
        PUNCH_MULTIPLE_TOP,
        /**
         * Punch multiple holes right side
         *
         * @since API 5
         */
        PUNCH_MULTIPLE_RIGHT,
        /**
         * Punch multiple holes bottom edge
         *
         * @since API 5
         */
        PUNCH_MULTIPLE_BOTTOM,
        /**
         * Accordion-fold the paper vertically into four sections
         *
         * @since API 5
         */
        FOLD_ACCORDION,
        /**
         * Fold the top and bottom quarters of the paper towards the midline, then fold in half vertically
         *
         * @since API 5
         */
        FOLD_DOUBLE_GATE,
        /**
         * Fold the top and bottom quarters of the paper towards the midline
         *
         * @since API 5
         */
        FOLD_GATE,
        /**
         * Fold the paper in half vertically
         *
         * @since API 5
         */
        FOLD_HALF,
        /**
         * Fold the paper in half horizontally, then Z-fold the paper vertically
         *
         * @since API 5
         */
        FOLD_HALF_Z,
        /**
         * Fold the top quarter of the paper towards the midline
         *
         * @since API 5
         */
        FOLD_LEFT_GATE,
        /**
         * Fold the paper into three sections vertically; sometimes also known as a C fold
         *
         * @since API 5
         */
        FOLD_LETTER,
        /**
         * Fold the paper in half vertically two times, yielding four sections
         *
         * @since API 5
         */
        FOLD_PARALLEL,
        /**
         * Fold the paper in half horizontally and vertically; sometimes also called a cross fold
         *
         * @since API 5
         */
        FOLD_POSTER,
        /**
         * Fold the bottom quarter of the paper towards the midline
         *
         * @since API 5
         */
        FOLD_RIGHT_GATE,
        /**
         * Fold the paper vertically into three sections, forming a Z
         *
         * @since API 5
         */
        FOLD_Z,
        /**
         * Fold the paper vertically into two small sections and one larger, forming an elongated Z
         *
         * @since API 5
         */
        FOLD_ENGINEERING_Z
    }


    final int mVersion;

    final ColorMode mColorMode;

    final Duplex mPlex;

    final AutoFit mAutoFit;

    final StapleMode mStapleMode;

    final PaperSource mPaperSource;

    final PaperSize mPaperSize;

    final PaperType mPaperType;

    final DocumentFormat mDocumentFormat;

    final CollateMode mCollateMode;

    final int mCopies;

    final transient Uri mFileUri;

    final Source mSource;

    final NetworkCredentialsAttributes mNetworkCredentialsAttributes;

    final String mJobName;

    final Orientation mOrientation;

    final PrintQuality mPrintQuality;

    final OutputBin mOutputBin;

    final Integer mStartPageRanges;
    final Integer mEndPageRanges;

    final List<Finishings> mFinishings;

    /**
     * @hide trivial
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide trivial
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeInt(mVersion);
        out.writeInt(mCopies);
        out.writeParcelable(mFileUri, 0);
        out.writeSerializable(mColorMode);
        out.writeSerializable(mPlex);
        out.writeSerializable(mAutoFit);
        out.writeSerializable(mStapleMode);
        out.writeSerializable(mPaperSource);
        out.writeSerializable(mPaperSize);
        out.writeSerializable(mPaperType);
        out.writeSerializable(mDocumentFormat);

        out.writeSerializable(mSource);

        out.writeParcelable(mNetworkCredentialsAttributes, 0);

        out.writeSerializable(mCollateMode);

        out.writeString(mJobName);

        out.writeSerializable(mOrientation);
        out.writeSerializable(mPrintQuality);
        out.writeSerializable(mOutputBin);
        out.writeInt(mStartPageRanges);
        out.writeInt(mEndPageRanges);

        ArrayList<String> finishingsList = new ArrayList<>();
        for(Finishings finishings: mFinishings) {
            finishingsList.add(finishings.name());
        }
        out.writeStringList(finishingsList);
    }

    /**
     * @hide trivial
     */
    public static final Parcelable.Creator<PrintAttributes> CREATOR = new Parcelable.Creator<PrintAttributes>() {
        public PrintAttributes createFromParcel(final Parcel in) {
            return new PrintAttributes(in);
        }

        public PrintAttributes[] newArray(final int size) {
            return new PrintAttributes[size];
        }
    };

    @SuppressLint("RestrictedApi")
    private PrintAttributes(final Parcel in) {
        mFinishings = new ArrayList<>();
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mCopies = in.readInt();
        mFileUri = in.readParcelable(Uri.class.getClassLoader());
        mColorMode = ColorMode.valueOf(((com.hp.workpath.api.printer.PrintAttributes.ColorMode)in.readSerializable()).name());
        mPlex = Duplex.valueOf(((com.hp.workpath.api.printer.PrintAttributes.Duplex)in.readSerializable()).name());
        mAutoFit = AutoFit.valueOf(((com.hp.workpath.api.printer.PrintAttributes.AutoFit)in.readSerializable()).name());
        mStapleMode = StapleMode.valueOf(((com.hp.workpath.api.printer.PrintAttributes.StapleMode)in.readSerializable()).name());
        mPaperSource = PaperSource.valueOf(((com.hp.workpath.api.printer.PrintAttributes.PaperSource)in.readSerializable()).name());
        mPaperSize = PaperSize.valueOf(((com.hp.workpath.api.printer.PrintAttributes.PaperSize)in.readSerializable()).name());
        mPaperType = PaperType.valueOf(((com.hp.workpath.api.printer.PrintAttributes.PaperType)in.readSerializable()).name());
        mDocumentFormat = DocumentFormat.valueOf(((com.hp.workpath.api.printer.PrintAttributes.DocumentFormat)in.readSerializable()).name());

        mSource = Source.valueOf(((com.hp.workpath.api.printer.PrintAttributes.Source)in.readSerializable()).name());

        Parcel parcelForNetworkAttr = Parcel.obtain();
        com.hp.workpath.api.printer.NetworkCredentialsAttributes networkCredentialsAttributes = in.readParcelable(com.hp.workpath.api.printer.NetworkCredentialsAttributes.class.getClassLoader());
        if(networkCredentialsAttributes != null) {
            networkCredentialsAttributes.writeToParcel(parcelForNetworkAttr, 0);
            parcelForNetworkAttr.setDataPosition(0);
            mNetworkCredentialsAttributes = NetworkCredentialsAttributes.CREATOR.createFromParcel(parcelForNetworkAttr);
        } else {
            mNetworkCredentialsAttributes = null;
        }

        mCollateMode = CollateMode.valueOf(((com.hp.workpath.api.printer.PrintAttributes.CollateMode)in.readSerializable()).name());

        if (mVersion >= Sdk.VERSION_LEVEL.THREE) {
            mJobName = in.readString();
        } else {
            mJobName = null;
        }

        if (mVersion >= Sdk.VERSION_LEVEL.SIX) {
            mOrientation = Orientation.valueOf(((com.hp.workpath.api.printer.PrintAttributes.Orientation)in.readSerializable()).name());
            mPrintQuality = PrintQuality.valueOf(((com.hp.workpath.api.printer.PrintAttributes.PrintQuality)in.readSerializable()).name());
            mOutputBin = OutputBin.valueOf(((com.hp.workpath.api.printer.PrintAttributes.OutputBin)in.readSerializable()).name());

            mStartPageRanges = in.readInt();
            mEndPageRanges = in.readInt();

            ArrayList<String> finishingsList = new ArrayList<>();
            in.readStringList(finishingsList);
            for (String finishingsStr: finishingsList) {
                mFinishings.add(Finishings.valueOf(finishingsStr));
            }


        } else {
            mOrientation = Orientation.DEFAULT;
            mPrintQuality = PrintQuality.DEFAULT;
            mOutputBin = OutputBin.DEFAULT;
            mStartPageRanges = 0;
            mEndPageRanges = 0;
            mFinishings.add(Finishings.DEFAULT);
        }
    }
}
