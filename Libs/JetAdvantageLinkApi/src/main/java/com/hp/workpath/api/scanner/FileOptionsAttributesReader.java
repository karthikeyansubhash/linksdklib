// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

/**
 * Reads attributes from {@link FileOptionsAttributes}
 *
 * @since API 1
 */
@SuppressWarnings({"unused"})
public class FileOptionsAttributesReader {
    private final FileOptionsAttributes mAttrs;

    /**
     * Constructor to create new instance of FileOptionsAttributesReader
     *
     * @param fileOptionsAttrs The attributes of the file options
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public FileOptionsAttributesReader(final FileOptionsAttributes fileOptionsAttrs) {
        mAttrs = fileOptionsAttrs;
    }

    /**
     * Gets PDF encryption password
     *
     * @return String password
     * <p>
     * <ul>
     * <li>Return can be null if the PdfEncryptionPassword is null</li>
     * <li>Return can be null if the PdfEncryptionPassword is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public String getPdfEncryptionPassword() {
        return mAttrs.mPdfEncryptionPassword;
    }

    /**
     * Gets OCR language
     *
     * @return OcrLanguage language
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link FileOptionsAttributes.OcrLanguage}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public FileOptionsAttributes.OcrLanguage getOcrLanguage() {
        return mAttrs.mOcrLanguage;
    }

    /**
     * Gets PDF compression mode
     *
     * @return PdfCompressionMode mode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  FileOptionsAttributes.PdfCompressionMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public FileOptionsAttributes.PdfCompressionMode getPdfCompressionMode() {
        return mAttrs.mPdfCompressionMode;
    }

    /**
     * Gets TIFF compression mode
     *
     * @return TiffCompressionMode mode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link FileOptionsAttributes.TiffCompressionMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public FileOptionsAttributes.TiffCompressionMode getTiffCompressionMode() {
        return mAttrs.mTiffCompressionMode;
    }

    /**
     * Gets XPS compression mode
     *
     * @return XpsCompressionMode mode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link FileOptionsAttributes.XpsCompressionMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public FileOptionsAttributes.XpsCompressionMode getXpsCompressionMode() {
        return mAttrs.mXpsCompressionMode;
    }
}
