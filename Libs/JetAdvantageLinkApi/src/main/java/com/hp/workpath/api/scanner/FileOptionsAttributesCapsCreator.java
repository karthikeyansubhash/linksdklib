// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the set of file options attributes supported by the printer
 *
 * @hide Clients should not be writing capabilities.
 */
public class FileOptionsAttributesCapsCreator {
    boolean isPdfEncryptionPasswordSupported;
    final List<FileOptionsAttributes.PdfCompressionMode> mPdfCompressionModeList;
    final List<FileOptionsAttributes.OcrLanguage> mOcrLanguageList;
    final List<FileOptionsAttributes.TiffCompressionMode> mTiffCompressionModeList;
    final List<FileOptionsAttributes.XpsCompressionMode> mXpsCompressionModeList;

    private FileOptionsAttributesCapsCreator(final Builder builder) {
        isPdfEncryptionPasswordSupported = builder.isPdfEncryptionPasswordSupported;
        mPdfCompressionModeList = builder.mPdfCompressionModeList;
        mOcrLanguageList = builder.mOcrLanguageList;
        mTiffCompressionModeList = builder.mTiffCompressionModeList;
        mXpsCompressionModeList = builder.mXpsCompressionModeList;
    }

    /**
     * Builder for determining the attributes supported for requesting images to be sent back to the client.
     */
    @SuppressWarnings("unused")
    public static class Builder {
        final List<FileOptionsAttributes.PdfCompressionMode> mPdfCompressionModeList = new ArrayList<>();
        final List<FileOptionsAttributes.OcrLanguage> mOcrLanguageList = new ArrayList<>();
        final List<FileOptionsAttributes.TiffCompressionMode> mTiffCompressionModeList = new ArrayList<>();
        final List<FileOptionsAttributes.XpsCompressionMode> mXpsCompressionModeList = new ArrayList<>();
        boolean isPdfEncryptionPasswordSupported = false;

        /**
         * Constructs a new builder used for determining the attributes supported for scanned images back to the client.
         */
        @SuppressWarnings("unused")
        public Builder() {
            mPdfCompressionModeList.add(FileOptionsAttributes.PdfCompressionMode.DEFAULT);
            mOcrLanguageList.add(FileOptionsAttributes.OcrLanguage.DEFAULT);
            mTiffCompressionModeList.add(FileOptionsAttributes.TiffCompressionMode.DEFAULT);
            mXpsCompressionModeList.add(FileOptionsAttributes.XpsCompressionMode.DEFAULT);
        }

        /**
         * Adds a OCR language
         *
         * @param ocrLanguage language
         * @return this builder for method chaining.
         */
        @SuppressWarnings("unused")
        public Builder addOcrLanguage(final FileOptionsAttributes.OcrLanguage ocrLanguage) {
            mOcrLanguageList.add(ocrLanguage);
            return this;
        }

        /**
         * Adds a PDF compression mode
         *
         * @param pdfCompressionMode mode
         * @return this builder for method chaining.
         */
        @SuppressWarnings("unused")
        public Builder addPdfCompressionMode(final FileOptionsAttributes.PdfCompressionMode pdfCompressionMode) {
            mPdfCompressionModeList.add(pdfCompressionMode);
            return this;
        }

        /**
         * Adds a TIFF compression mode
         *
         * @param tiffCompressionMode mode
         * @return this builder for method chaining.
         */
        @SuppressWarnings("unused")
        public Builder addTiffCompressionMode(final FileOptionsAttributes.TiffCompressionMode tiffCompressionMode) {
            mTiffCompressionModeList.add(tiffCompressionMode);
            return this;
        }

        /**
         * Adds a XPS compression mode
         *
         * @param xpsCompressionMode mode
         * @return this builder for method chaining.
         */
        @SuppressWarnings("unused")
        public Builder addXpsCompressionMode(final FileOptionsAttributes.XpsCompressionMode xpsCompressionMode) {
            mXpsCompressionModeList.add(xpsCompressionMode);
            return this;
        }

        /**
         * Sets whether PDF encryption password supported
         *
         * @param pdfEncryptionPasswordSupported value
         * @return this builder for method chaining.
         */
        @SuppressWarnings("unused")
        public Builder setPdfEncryptionPasswordSupported(boolean pdfEncryptionPasswordSupported) {
            isPdfEncryptionPasswordSupported = pdfEncryptionPasswordSupported;
            return this;
        }

        /**
         * Combine all of the capabilities in this into a FileOptionsAttributesCapsCreator object.
         *
         * @return a FileOptionsAttributesCapsCreator object containing all of the attributes.
         */
        @SuppressWarnings("unused")
        public FileOptionsAttributesCapsCreator build() {
            return new FileOptionsAttributesCapsCreator(this);
        }
    }
}
