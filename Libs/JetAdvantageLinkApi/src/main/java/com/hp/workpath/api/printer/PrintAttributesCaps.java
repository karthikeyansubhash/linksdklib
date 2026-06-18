// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.printer;

import java.util.Collections;
import java.util.List;

/**
 * Sets of attributes provided by the device when requesting a print.
 *
 * @since API 1
 */
public class PrintAttributesCaps {
    private final PrintAttributesCapsCreator mCapsCreator;

    /**
     * Constructor to craete object from the PrintAttributesCapsCreator object
     *
     * @param creator object containing the print capabilities
     * @hide The creator is hidden
     * @since API 1
     */
    public PrintAttributesCaps(final PrintAttributesCapsCreator creator) {
        mCapsCreator = creator;
    }

    /**
     * Retrieves the groups of color sets.
     *
     * @return the color modes supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ColorMode from {@link PrintAttributes.ColorMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.ColorMode> getColorModeList() {
        return Collections.unmodifiableList(mCapsCreator.mColorModeList);
    }

    /**
     * Retrieves duplex options supported by the device
     *
     * @return List of duplex options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Duplex from {@link PrintAttributes.Duplex}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.Duplex> getDuplexList() {
        return Collections.unmodifiableList(mCapsCreator.mPlexList);
    }

    /**
     * Retrieves max number of copies a user can provide
     *
     * @return maximum number of copies.
     * <p>
     * <ul>
     * <li>maximum value of copies should be 32000 and it shouldn't accept value above 32000</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public int getMaxCopies() {
        return mCapsCreator.mMaxCopies;
    }

    /**
     * Retrieves if AutoFit option is supported on the device.
     *
     * @return List of AutoFit options for printer.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the AutoFit from {@link PrintAttributes.AutoFit}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.AutoFit> getAutoFitList() {
        return Collections.unmodifiableList(mCapsCreator.mAutoFitList);
    }

    /**
     * Retrieves staple options supported on the device.
     *
     * @return List of Staple options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the StapleMode from {@link PrintAttributes.StapleMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.StapleMode> getStapleModeList() {
        return Collections.unmodifiableList(mCapsCreator.mStapleModeList);
    }

    /**
     * Retrieves paper sources supported on the device.
     *
     * @return <p>List of paper sources from {@link PrintAttributes.PaperSource}.
     * <ul>
     * <li>Return can't be empty and should be either any of the PaperSource from {@link PrintAttributes.PaperSource}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.PaperSource> getPaperSourceList() {
        Collections.sort(mCapsCreator.mPaperSourceList);
        mCapsCreator.mPaperSourceList.remove(PrintAttributes.PaperSource.DEFAULT);
        mCapsCreator.mPaperSourceList.add(0, PrintAttributes.PaperSource.DEFAULT);

        return Collections.unmodifiableList(mCapsCreator.mPaperSourceList);
    }

    /**
     * Retrieves paper sizes supported on the device.
     *
     * @return List of paper sizes.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the PaperSize from {@link PrintAttributes.PaperSize}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.PaperSize> getPaperSizeList() {
        Collections.sort(mCapsCreator.mPaperSizeList);
        mCapsCreator.mPaperSizeList.remove(PrintAttributes.PaperSize.DEFAULT);
        mCapsCreator.mPaperSizeList.add(0, PrintAttributes.PaperSize.DEFAULT);

        return Collections.unmodifiableList(mCapsCreator.mPaperSizeList);
    }

    /**
     * Retrieves paper types supported on the device.
     *
     * @return List of paper types.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the PaperType from {@link PrintAttributes.PaperType}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.PaperType> getPaperTypeList() {
        return Collections.unmodifiableList(mCapsCreator.mPaperTypeList);
    }

    /**
     * Retrieves document formats supported on the device.
     *
     * @return List of document formats.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the DocumentFormat from {@link PrintAttributes.DocumentFormat}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.DocumentFormat> getDocumentFormatList() {
        return Collections.unmodifiableList(mCapsCreator.mDocumentFormatList);
    }

    /**
     * Retrieves collate options supported on the device.
     *
     * @return List of Collate options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the CollateMode from {@link PrintAttributes.CollateMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.CollateMode> getCollateModeList() {
        return Collections.unmodifiableList(mCapsCreator.mCollateModeList);
    }

    /**
     * Retrieves orientation options supported on the device.
     *
     * @return List of Orientation options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Orientation from {@link PrintAttributes.Orientation}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<PrintAttributes.Orientation> getOrientationList() {
        return Collections.unmodifiableList(mCapsCreator.mOrientationList);
    }

    /**
     * Retrieves print-quality options supported on the device.
     *
     * @return List of Print Quality options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the PrintQuality from {@link PrintAttributes.PrintQuality}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<PrintAttributes.PrintQuality> getPrintQualityList() {
        return Collections.unmodifiableList(mCapsCreator.mPrintQualityList);
    }

    /**
     * Retrieves output-bin options supported on the device.
     *
     * @return List of Output-bin options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the OutputBin from {@link PrintAttributes.OutputBin}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<PrintAttributes.OutputBin> getOutputBinList() {
        return Collections.unmodifiableList(mCapsCreator.mOutputBinList);
    }

    /**
     * Retrieves finishings options supported on the device.
     *
     * @return List of Finishings options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Finishings from {@link PrintAttributes.Finishings}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<PrintAttributes.Finishings> getFinishingsList() {
        return Collections.unmodifiableList(mCapsCreator.mFinishingsList);
    }

    /**
     * Retrieves file sources supported on the device.
     *
     * @return List of supported sources.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Source from {@link PrintAttributes.Source}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<PrintAttributes.Source> getSourceList() {
        return Collections.unmodifiableList(mCapsCreator.mSourceList);
    }

    /**
     * @hide trivial
     */
    @Override
    public String toString() {
        return "Print Caps: " +
                ",Plex:" + mCapsCreator.mPlexList +
                ",CM:" + mCapsCreator.mColorModeList +
                ",Copies:" + mCapsCreator.mMaxCopies +
                ",AutoFit:" + mCapsCreator.mAutoFitList +
                ",Staple:" + mCapsCreator.mStapleModeList +
                ",Collate:" + mCapsCreator.mCollateModeList +
                ",PaperSources:" + mCapsCreator.mPaperSourceList +
                ",PaperSizes:" + mCapsCreator.mPaperSizeList +
                ",DocumentFormats:" + mCapsCreator.mDocumentFormatList +
                ",Orientation:" + mCapsCreator.mOrientationList +
                ",PrintQuality:" + mCapsCreator.mPrintQualityList +
                ",OutputBin:" + mCapsCreator.mOutputBinList +
                ",Finishings:" + mCapsCreator.mFinishingsList;
    }
}
