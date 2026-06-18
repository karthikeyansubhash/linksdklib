// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.printer;

import android.net.Uri;
import android.support.annotation.Keep;

import java.util.List;

/**
 * Reads attributes which are requesting of a print from the device
 *
 * @since API 1
 */
public class PrintAttributesReader {
    @Keep
    private final PrintAttributes mAttrs;

    /**
     * Constructor
     *
     * @param attrs The print attributes used to construct this.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributesReader(final PrintAttributes attrs) {
        mAttrs = attrs;
    }

    /**
     * Retrieves the version of the created attributes.
     *
     * @return The version of the created attributes.
     * @hide for internal use
     * @since API 1
     */
    public int getVersion() {
        return mAttrs.mVersion;
    }

    /**
     * Retrieves the color mode for the print.
     *
     * @return color mode.
     * @since API 1
     */
    @Keep
    public PrintAttributes.ColorMode getColorMode() {
        return mAttrs.mColorMode;
    }

    /**
     * Retrieves the duplex mode for the print.
     *
     * @return duplex mode
     * <p>
     * <ul>
     * <li>Return can be null if the duplex mode is null</li>
     * <li>Return can be null if the duplex mode is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.Duplex getPlex() {
        return mAttrs.mPlex;
    }

    /**
     * Retrieves the auto fit mode (enabled or disabled) for the print.
     *
     * @return auto fit mode
     * <p>
     * <ul>
     * <li>Return can be null if the auto fit mode is null</li>
     * <li>Return can be null if the auto fit mode is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.AutoFit getAutoFit() {
        return mAttrs.mAutoFit;
    }

    /**
     * Retrieves the staple mode for the print.
     *
     * @return staple mode
     * <p>
     * <ul>
     * <li>Return can be null if the staple mode is null</li>
     * <li>Return can be null if the staple mode is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.StapleMode getStapleMode() { return mAttrs.mStapleMode; }

    /**
     * Retrieves the number of copies to print
     *
     * @return number of copies
     * <p>
     * <ul>
     * <li>Return value ranges from [1 to 32000]</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public int getCopies() {
        return mAttrs.mCopies;
    }

    /**
     * Retrieves Media tray to be used for print
     *
     * @return paper source
     * <p>
     * <ul>
     * <li>Return can be null if the paper source is null</li>
     * <li>Return can be null if the paper source is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.PaperSource getPaperSource() {
        return mAttrs.mPaperSource;
    }

    /**
     * Retrieves Media size to be used for print
     *
     * @return paper size
     * <p>
     * <ul>
     * <li>Return can be null if the paper size is null</li>
     * <li>Return can be null if the paper size is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.PaperSize getPaperSize() {
        return mAttrs.mPaperSize;
    }

    /**
     * Retrieves Media type to be used for print
     *
     * @return paper type
     * <p>
     * <ul>
     * <li>Return can be null if the paper type is null</li>
     * <li>Return can be null if the paper type is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.PaperType getPaperType() {
        return mAttrs.mPaperType;
    }

    /**
     * Retrieves Document format of the file to print
     *
     * @return file format
     * <p>
     * <ul>
     * <li>Return can be null if the file format is null</li>
     * <li>Return can be null if the file format is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.DocumentFormat getDocumentFormat() {
        return mAttrs.mDocumentFormat;
    }

    /**
     * Retrieves the collate mode to be used for print
     *
     * @return collate mode
     * <p>
     * <ul>
     * <li>Return can be null if the collate mode is null</li>
     * <li>Return can be null if the collate mode is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public PrintAttributes.CollateMode getCollateMode() {
        return mAttrs.mCollateMode;
    }

    /**
     * Retrieves the job name to be used for print
     *
     * @return String jobname
     * <p>
     * <ul>
     * <li>Return can be null if the jobname is null</li>
     * <li>Return can be null if the jobname is empty</li>
     * </ul>
     * </p>
     * @since API 3
     */
    @Keep
    public String getJobName() {
        return mAttrs.mJobName;
    }

    /**
     * Retrieves the orientation to be used for print
     *
     * @return orientation
     * <p>
     * <ul>
     * <li>Return can be null if the orientation is null</li>
     * <li>Return can be null if the orientation is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public PrintAttributes.Orientation getOrientation() {
        return mAttrs.mOrientation;
    }

    /**
     * Retrieves the print quality to be used for print
     *
     * @return printQuality
     * <p>
     * <ul>
     * <li>Return can be null if the printQuality is null</li>
     * <li>Return can be null if the printQuality is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public PrintAttributes.PrintQuality getPrintQuality() {
        return mAttrs.mPrintQuality;
    }

    /**
     * Retrieves the Outputbin to be used for print
     *
     * @return outputBin
     * <p>
     * <ul>
     * <li>Return can be null if the outputBin is null</li>
     * <li>Return can be null if the outputBin is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public PrintAttributes.OutputBin getOutputBin() {
        return mAttrs.mOutputBin;
    }
    /**
     * Retrieves the start page of page ranges to print
     *
     * @return start page of page ranges
     * <p>
     * <ul>
     * <li>Return can be null if the start page ranges is null</li>
     * <li>Return can be null if the start page ranges is empty</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public int getStartPageRanges() {
        return mAttrs.mStartPageRanges;
    }
    /**
     * Retrieves the end page of page ranges to print
     *
     * @return end page of page ranges
     * <p>
     * <ul>
     * <li>Ensures that the return numeric value is non-negative.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public int getEndPageRanges() {
        return mAttrs.mEndPageRanges;
    }
    /**
     * Retrieves the finishings for the print.
     *
     * @return finishings
     *
     * @since API 5
     */
    @Keep
    public List<PrintAttributes.Finishings> getFinishingsList() { return mAttrs.mFinishings; }
    /**
     * Retrieves Location of the file to print
     *
     * @return uri of file
     * @hide for internal use
     * @since API 1
     */

    public Uri getUri() {
        return mAttrs.mFileUri;
    }

    /**
     * Retrieves source of file to print
     *
     * @return paper source
     * @hide for internal use
     * @since API 1
     */
    public PrintAttributes.Source getSource() {
        return mAttrs.mSource;
    }

    /**
     * Retrieves Network credentials for authentication when accessing Uri
     *
     * @return uri authentication credentials
     * @hide for internal use
     * @since API 1
     */
    public NetworkCredentialsAttributes getNetworkCredentials() {
        return mAttrs.mNetworkCredentialsAttributes;
    }
}
