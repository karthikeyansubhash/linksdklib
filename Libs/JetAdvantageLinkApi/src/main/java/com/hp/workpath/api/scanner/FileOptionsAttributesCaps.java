// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import java.util.Collections;
import java.util.List;

/**
 * File options which are available from device.
 *
 * @since API 1
 */
public final class FileOptionsAttributesCaps {
    final FileOptionsAttributesCapsCreator mCapsCreator;

    /**
     * Constructor to build FileOptionsAttributesCapsCreator object
     *
     * @param creator object containing the file options capabilities
     * @hide The creator is hidden
     */
    public FileOptionsAttributesCaps(final FileOptionsAttributesCapsCreator creator) {
        mCapsCreator = creator;
    }

    /**
     * Returns supported option of PDF encryption
     *
     * @return true if PDF encryption supported
     * <p>
     * <ul>
     * <li>If encryption is supported, method returns true.</li>
     * <li>If encryption is not supported, method returns false.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public boolean isPdfEncryptionPasswordSupported() {
        return mCapsCreator.isPdfEncryptionPasswordSupported;
    }

    /**
     * Gets supported PDF compression modes
     *
     * @return the PDF compression modes supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the PdfCompressionMode from {@link com.hp.workpath.api.scanner.FileOptionsAttributes.PdfCompressionMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<FileOptionsAttributes.PdfCompressionMode> getPdfCompressionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mPdfCompressionModeList);
    }

    /**
     * Gets supported PDF OCR languages
     *
     * @return the PDF OCR languages supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the OcrLanguage from {@link com.hp.workpath.api.scanner.FileOptionsAttributes.OcrLanguage}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<FileOptionsAttributes.OcrLanguage> getOcrLanguageList() {
        return Collections.unmodifiableList(mCapsCreator.mOcrLanguageList);
    }

    /**
     * Gets supported TIFF compression modes
     *
     * @return the TIFF compression modes supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the TiffCompressionMode from {@link com.hp.workpath.api.scanner.FileOptionsAttributes.TiffCompressionMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<FileOptionsAttributes.TiffCompressionMode> getTiffCompressionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mTiffCompressionModeList);
    }

    /**
     * Gets supported XPS compression modes
     *
     * @return the XPS compression modes supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the XpsCompressionMode from {@link com.hp.workpath.api.scanner.FileOptionsAttributes.XpsCompressionMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressWarnings("unused")
    public List<FileOptionsAttributes.XpsCompressionMode> getXpsCompressionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mXpsCompressionModeList);
    }
}
