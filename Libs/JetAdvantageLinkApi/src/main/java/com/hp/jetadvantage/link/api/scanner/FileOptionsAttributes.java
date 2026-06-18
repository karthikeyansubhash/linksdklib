// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.scanner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The file options that have a dependency on file type.
 *
 * @since API 1
 * @hide
 */
@Deprecated
public final class FileOptionsAttributes implements Parcelable {
    /**
     * Enumeration for optimizing the language of the OCR engine
     *
     * @since API 1
     */
    public enum OcrLanguage {
        /**
         * Optimize the OCR engine with device default
         * @since API 1
         */
        DEFAULT,

        /**
         * Optimize the OCR engine for Arabic
         * @since API 1
         */
        ARABIC,

        /**
         * Optimize the OCR engine for Catalan
         * @since API 1
         */
        CATALAN,

        /**
         * Optimize the OCR engine for Simplified Chinese
         * @since API 1
         */
        CHINESE_SIMPLIFIED,

        /**
         * Optimize the OCR engine for Traditional Chinese
         * @since API 1
         */
        CHINESE_TRADITIONAL,

        /**
         * Optimize the OCR engine for Croatian
         * @since API 1
         */
        CROATIAN,

        /**
         * Optimize the OCR engine for Czech
         * @since API 1
         */
        CZECH,

        /**
         * Optimize the OCR engine for Danish
         * @since API 1
         */
        DANISH,

        /**
         * Optimize the OCR engine for Dutch
         * @since API 1
         */
        DUTCH,

        /**
         * Optimize the OCR engine for English
         * @since API 1
         */
        ENGLISH,

        /**
         * Optimize the OCR engine for Finish
         * @since API 1
         */
        FINNISH,

        /**
         * Optimize the OCR engine for French
         * @since API 1
         */
        FRENCH,

        /**
         * Optimize the OCR engine for German
         * @since API 1
         */
        GERMAN,

        /**
         * Optimize the OCR engine for Greek
         * @since API 1
         */
        GREEK,

        /**
         * Optimize the OCR engine for Hebrew
         * @since API 1
         */
        HEBREW,

        /**
         * Optimize the OCR engine for Hungarian
         * @since API 1
         */
        HUNGARIAN,

        /**
         * Optimize the OCR engine for Indonesian
         * @since API 1
         */
        INDONESIAN,

        /**
         * Optimize the OCR engine for Italian
         * @since API 1
         */
        ITALIAN,

        /**
         * Optimize the OCR engine for Japanese
         * @since API 1
         */
        JAPANESE,

        /**
         * Optimize the OCR engine for Korean
         * @since API 1
         */
        KOREAN,

        /**
         * Optimize the OCR engine for Norwegian
         * @since API 1
         */
        NORWEGIAN,

        /**
         * Optimize the OCR engine for Polish
         * @since API 1
         */
        POLISH,

        /**
         * Optimize the OCR engine for Portuguese
         * @since API 1
         */
        PORTUGUESE,

        /**
         * Optimize the OCR engine for Romanian
         * @since API 1
         */
        ROMANIAN,

        /**
         * Optimize the OCR engine for Russian
         * @since API 1
         */
        RUSSIAN,

        /**
         * Optimize the OCR engine for Slovak
         * @since API 1
         */
        SLOVAK,

        /**
         * Optimize the OCR engine for Slovenian
         * @since API 1
         */
        SLOVENIAN,

        /**
         * Optimize the OCR engine for Spanish
         * @since API 1
         */
        SPANISH,

        /**
         * Optimize the OCR engine for Swedish
         * @since API 1
         */
        SWEDISH,

        /**
         * Optimize the OCR engine for Turkish
         * @since API 1
         */
        TURKISH
    }

    /**
     * Enumeration of compression mode
     *
     * @since API 1
     */
    public enum PdfCompressionMode {
        /**
         * Default compression
         * @since API 1
         */
        DEFAULT,

        /**
         * Normal compression
         * @since API 1
         */
        NORMAL,

        /**
         * The device will attempt to use adaptive/selective methods to produce the smallest possible output.
         * @since API 1
         */
        HIGH
    }

    /**
     * Enumeration of compression mode
     *
     * @since API 1
     */
    public enum TiffCompressionMode {
        /**
         * Default compression mode
         * @since API 1
         */
        DEFAULT,

        /**
         * CCITT Group 3 (may only be used with Black color mode).
         * @since API 1
         */
        G_3,

        /**
         * CCITT Group 3 (may only be used with Black color mode).
         * @since API 1
         */
        G_4,

        /**
         * JPEG TIFF Version 6.0 (may not be used with Black color mode).
         * @since API 1
         */
        JPEG_TIFF_6,

        /**
         * JPEG TIFF Technical Note 2 (may not be used with Black color mode).
         * @since API 1
         */
        JPEG_TTN_2,

        /**
         * Lempel Ziv Welch
         * @since API 1
         */
        LZW,

        /**
         * The device will use adaptive methods to produce the smallest possible output (may only be used with Black color mode).
         * @since API 1
         */
        HIGH
    }

    /**
     * Enumeration of compression mode
     *
     * @since API 1
     */
    public enum XpsCompressionMode {
        /**
         * Default compression mode
         * @since API 1
         */
        DEFAULT,

        /**
         * Normal compression
         * @since API 1
         */
        NORMAL,

        /**
         * The device will attempt to use adaptive/selective methods to produce the smallest possible output.
         * @since API 1
         */
        HIGH
    }

    final String mPdfEncryptionPassword;
    final OcrLanguage mOcrLanguage;
    final PdfCompressionMode mPdfCompressionMode;
    final TiffCompressionMode mTiffCompressionMode;
    final XpsCompressionMode mXpsCompressionMode;

    private FileOptionsAttributes(Parcel in) {
        in.setDataPosition(0);
        mPdfEncryptionPassword = in.readString();
        mOcrLanguage = OcrLanguage.valueOf(((com.hp.workpath.api.scanner.FileOptionsAttributes.OcrLanguage) in.readSerializable()).name());
        mPdfCompressionMode = PdfCompressionMode.valueOf(((com.hp.workpath.api.scanner.FileOptionsAttributes.PdfCompressionMode) in.readSerializable()).name());
        mTiffCompressionMode = TiffCompressionMode.valueOf(((com.hp.workpath.api.scanner.FileOptionsAttributes.TiffCompressionMode) in.readSerializable()).name());
        mXpsCompressionMode = XpsCompressionMode.valueOf(((com.hp.workpath.api.scanner.FileOptionsAttributes.XpsCompressionMode) in.readSerializable()).name());
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPdfEncryptionPassword);
        dest.writeSerializable(mOcrLanguage);
        dest.writeSerializable(mPdfCompressionMode);
        dest.writeSerializable(mTiffCompressionMode);
        dest.writeSerializable(mXpsCompressionMode);
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
    public static final Creator<FileOptionsAttributes> CREATOR = new Creator<FileOptionsAttributes>() {
        @Override
        public FileOptionsAttributes createFromParcel(Parcel in) {
            return new FileOptionsAttributes(in);
        }

        @Override
        public FileOptionsAttributes[] newArray(int size) {
            return new FileOptionsAttributes[size];
        }
    };
}
