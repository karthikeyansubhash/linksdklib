// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import java.util.ArrayList;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Copy attributes capabilities provided from the printer.
 *
 * @since API 3
 */
public class CopyAttributesCaps {
    final CopyAttributesCapsCreator mCapsCreator;

    /**
     * Constructs this object from the CopyAttributesCapsCreator object.
     *
     * @param creator object containing the copy capabilities.
     * @hide The creator is hidden
     * @since API 3
     */
    public CopyAttributesCaps(final CopyAttributesCapsCreator creator) {
        mCapsCreator = creator;
    }

    /**
     * Indicates the groups of color sets which the scanned output will reside within. For example, a monospace set indicates that the scanned output
     * only includes black, white, and various shades of gray.
     *
     * @return  <p>the color modes supported {@link CopyAttributes.ColorMode} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the color modes from {@link CopyAttributes.ColorMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.ColorMode> getColorModeList() {
        return Collections.unmodifiableList(mCapsCreator.mColorModeList);
    }

    /**
     * Retrieves the supported original orientation options by the current printer.
     *
     * @return <p>supported Orientation options {@link CopyAttributes.Orientation} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.Orientation}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.Orientation> getOrientationList() {
        return Collections.unmodifiableList(mCapsCreator.mOrientationList);
    }

    /**
     * Retrieves scan duplex options supported by the current printer.
     *
     * @return <p>supported duplex options {@link CopyAttributes.Duplex} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.Duplex}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.Duplex> getScanDuplexList() {
        return Collections.unmodifiableList(mCapsCreator.mScanDuplexList);
    }

    /**
     * A list of scan sizes supported by the current printer.
     *
     * @return <p>supported scan sizes {@link CopyAttributes.ScanSize} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the sizes from {@link CopyAttributes.ScanSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.ScanSize> getScanSizeList() {
        return Collections.unmodifiableList(mCapsCreator.mScanSizeList);
    }

    /**
     * A range for scan size custom length.
     *
     * @return <p>Retrieves the supported range for custom length by the device.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public FloatRange getScanCustomLengthRange() {
        return mCapsCreator.mScanCustomLengthRange;
    }

    /**
     * A range for scan size custom width.
     *
     * @return <p>Retrieves the supported range for custom width by the device.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public FloatRange getScanCustomWidthRange() {
        return mCapsCreator.mScanCustomWidthRange;
    }

    /**
     * A list of scan sources supported by the current printer.
     *
     * @return <p>supported scan sources {@link CopyAttributes.ScanSource} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the source type from {@link CopyAttributes.ScanSource}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.ScanSource> getScanSourceList() {
        return Collections.unmodifiableList(mCapsCreator.mScanSourceList);
    }

    /**
     * Retrieves the supported copy preview options by the current printer.
     *
     * @return <p>supported copy preview options {@link CopyAttributes.CopyPreview} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the copy preview options (true/false).</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.CopyPreview> getCopyPreviewList() {
        return Collections.unmodifiableList(mCapsCreator.mCopyPreviewList);
    }

    /**
     * Retrieves the supported scan background cleanup options by the current printer.
     *
     * @return <p>supported levels of BackgroundCleanup settings {@link CopyAttributes.BackgroundCleanup} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the BackgroundCleanup levels {@link CopyAttributes.BackgroundCleanup}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.BackgroundCleanup> getBackgroundCleanupList() {
        return Collections.unmodifiableList(mCapsCreator.mBackgroundCleanupList);
    }

    /**
     * Retrieves the supported scan contrast adjustment options by the current printer.
     *
     * @return  <p>supported levels of ContrastAdjustment settings {@link CopyAttributes.ContrastAdjustment} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the ContrastAdjustment levels {@link CopyAttributes.ContrastAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.ContrastAdjustment> getContrastAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mContrastAdjustmentList);
    }

    /**
     * Retrieves the supported scan darkness adjustment options by the current printer.
     *
     * @return <p>supported levels of DarknessAdjustment settings {@link CopyAttributes.DarknessAdjustment} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the DarknessAdjustment levels {@link CopyAttributes.DarknessAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.DarknessAdjustment> getDarknessAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mDarknessAdjustmentList);
    }

    /**
     * Retrieves the supported scan sharpness adjustment options by the current printer.
     *
     * @return <p>supported levels of SharpnessAdjustment settings {@link CopyAttributes.SharpnessAdjustment} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the SharpnessAdjustment levels {@link CopyAttributes.SharpnessAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.SharpnessAdjustment> getSharpnessAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mSharpnessAdjustmentList);
    }

    /**
     * Retrieves the print duplex options supported by the current printer.
     *
     * @return <p>supported duplex options {@link CopyAttributes.Duplex} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the Duplex options {@link CopyAttributes.Duplex}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.Duplex> getPrintDuplexList() {
        return Collections.unmodifiableList(mCapsCreator.mPrintDuplexList);
    }

    /**
     * A list of print sizes supported by the current printer.
     *
     * @return <p>supported PaperSize {@link CopyAttributes.PaperSize} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the print sizes {@link CopyAttributes.PaperSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.PaperSize> getPrintSizeList() {
        return Collections.unmodifiableList(mCapsCreator.mPrintSizeList);
    }

    /**
     * A range for print size custom length.
     *
     * @return <p>supported range for print custom length.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [5.5, 18.0].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public FloatRange getPrintCustomLengthRange() {
        return mCapsCreator.mPrintCustomLengthRange;
    }

    /**
     * A range for print size custom width.
     *
     * @return <p>supported range for custom width.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [3.86, 12.6].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public FloatRange getPrintCustomWidthRange() {
        return mCapsCreator.mPrintCustomWidthRange;
    }

    /**
     * Retrieves the copies range supported by the current printer.
     *
     * @return <p>supported range for copies.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [1, 9999].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public Range getCopiesRange() {
        return mCapsCreator.mCopiesRange;
    }

    /**
     * Retrieves the collate options supported on the current printer.
     *
     * @return supported Collate options {@link CopyAttributes.CollateMode} list
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.CollateMode> getCollateModeList() {
        return Collections.unmodifiableList(mCapsCreator.mCollateModeList);
    }

    /**
     * Retrieves the paper sources supported on the current printer.
     *
     * @return supported paper sources {@link CopyAttributes.PaperSource} list
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.PaperSource> getPaperSourceList() {
        return Collections.unmodifiableList(mCapsCreator.mPaperSourceList);
    }

    /**
     * Retrieves the paper types supported on the current device.
     *
     * @return supported paper types {@link CopyAttributes.PaperType} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.PaperType> getPaperTypeList() {
        return Collections.unmodifiableList(mCapsCreator.mPaperTypeList);
    }

    /**
     * Retrieves scale modes supported on the current printer.
     *
     * @return supported scale modes {@link CopyAttributes.ScaleMode} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.ScaleMode> getScaleModeList() {
        return Collections.unmodifiableList(mCapsCreator.mScaleModeList);
    }

    /**
     * Retrieves scale percent range supported by the current printer.
     *
     * @return <p>supported scan sources with scale percent range as Map<key, value> pair.
     * <ul>
     * <li>Return can't be empty and should be either any of the ScanSource from {@link CopyAttributes.ScanSource} with the valid scale range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public Map<CopyAttributes.ScanSource, Range> getScalePercentRangeByScanSource() {
        return mCapsCreator.mScalePercentRangeMap;
    }

    /**
     * Gets the supported text/photo optimization options by the device.
     *
     * @return <p>supported text/graphics optimization options {@link CopyAttributes.TextGraphicsOptimization} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.TextGraphicsOptimization}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public List<CopyAttributes.TextGraphicsOptimization> getTextGraphicsOptimizationList() {
        return Collections.unmodifiableList(mCapsCreator.mTextGraphicsOptimizationList);
    }

    /**
     * Retrieves job assembly modes supported on the current printer.
     *
     * @return supported job assembly modes {@link CopyAttributes.JobAssemblyMode} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.JobAssemblyMode> getJobAssemblyModeList() {
        return Collections.unmodifiableList(mCapsCreator.mJobAssemblyModeList);
    }

    /**
     * Retrieves job execution modes supported on the current printer.
     *
     * @return supported job execution modes {@link CopyAttributes.JobExecutionMode} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.JobExecutionMode> getJobExecutionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mJobExecutionModeList);
    }

    /**
     * Retrieves number up modes supported on the current printer.
     *
     * @return supported number up modes {@link CopyAttributes.NumberUpMode} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<CopyAttributes.NumberUpMode> getNumberUpModeList() {
        return Collections.unmodifiableList(mCapsCreator.mNumberUpModeList);
    }

    /**
     * Retrieves number up directions supported on the current printer.
     *
     * @return supported of number up direction {@link CopyAttributes.NumberUpDirection} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public Map<CopyAttributes.NumberUpMode, List<CopyAttributes.NumberUpDirection>> getNumberUpDirectionByNumberUpCount() {
        return Collections.unmodifiableMap(mCapsCreator.mNumberUpDirectionMap);
    }


    /**
     * Retrieves password types supported for protecting stored job on the current printer.
     *
     * @return supported password types {@link JobCredentialsAttributes.PasswordType} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 3
     */
    public List<JobCredentialsAttributes.PasswordType> getPasswordTypeList() {
        return Collections.unmodifiableList(mCapsCreator.mPasswordTypeList);
    }

    /**
     * Retrieves output bins supported on the current printer.
     *
     * @return supported output bin types {@link CopyAttributes.OutputBin} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 5
     */
    public List<CopyAttributes.OutputBin> getOutputBinList() {
        return Collections.unmodifiableList(mCapsCreator.mOutputBinList);
    }

    /**
     * Retrieves progress dialog modes supported on the current printer.
     *
     * @return supported progress dialog mode types {@link CopyAttributes.ProgressDialogMode} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 5
     */
    public List<CopyAttributes.ProgressDialogMode> getProgressDialogModeList() {
        return Collections.unmodifiableList(mCapsCreator.mProgressDialogModeList);
    }

    /**
     * <p>Retrieves the supported EraseMarginUnits by the device. Device support following erase margin units
     * <ul>
     * <li>INCHES</li>
     * <li>MILLIMETERS</li>
     * </ul>
     * </p>
     *
     * @return <p>supported {@link CopyAttributes.EraseMarginUnit} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the units from {@link CopyAttributes.EraseMarginUnit}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.EraseMarginUnit> getEraseMarginUnitList() {
        if (mCapsCreator.mEraseMarginUnitList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mEraseMarginUnitList);
    }

    /**
     * <p>Retrieves the supported range of Erase BackBottom by the device.</p>
     *
     * @return <p>Supported range for EraseBackBottom.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseBackBottomRange() {
        return mCapsCreator.mEraseBackBottomRange;
    }

    /**
     * <p>Retrieves the supported range of Erase BackLeft by the device.</p>
     *
     * @return <p>Supported range for EraseBackLeft.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseBackLeftRange() {
        return mCapsCreator.mEraseBackLeftRange;
    }

    /**
     * <p>Retrieves the supported range of Erase BackRight by the device.</p>
     *
     * @return <p>Supported range for EraseBackRight.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseBackRightRange() {
        return mCapsCreator.mEraseBackRightRange;
    }

    /**
     * <p>Retrieves the supported range of Erase BackTop by the device.</p>
     *
     * @return <p>Supported range for EraseBackTop.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseBackTopRange() {
        return mCapsCreator.mEraseBackTopRange;
    }

    /**
     * <p>Retrieves the supported range of Erase FrontBottom by the device.</p>
     *
     * @return <p>Supported range for EraseFrontBottom.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseFrontBottomRange() {
        return mCapsCreator.mEraseFrontBottomRange;
    }

    /**
     * <p>Retrieves the supported range of Erase FrontLeft by the device.</p>
     *
     * @return <p>Supported range for EraseFrontLeft.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseFrontLeftRange() {
        return mCapsCreator.mEraseFrontLeftRange;
    }

    /**
     * <p>Retrieves the supported range of Erase FrontRight by the device.</p>
     *
     * @return <p>Supported range for EraseFrontRight.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseFrontRightRange() {
        return mCapsCreator.mEraseFrontRightRange;
    }

    /**
     * <p>Retrieves the supported range of Erase FrontTop by the device.</p>
     *
     * @return <p>Supported range for EraseFrontTop.
     * <ul>
     * <li>Return value ranges from [0 - 999] for Millimeters.</li>
     * <li>Return value ranges from [0 - 39] for Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getEraseFrontTopRange() {
        return mCapsCreator.mEraseFrontTopRange;
    }

    /**
     * Retrieves ImageShift Reduce To Fit options supported on the current printer.
     *
     * @return <p>supported ImageShiftReduceToFit options {@link CopyAttributes.ImageShiftReduceToFit} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from (true/false).</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<CopyAttributes.ImageShiftReduceToFit> getImageShiftReduceToFitList() {
        return Collections.unmodifiableList(mCapsCreator.mImageShiftReduceToFitList);
    }

    /**
     * Retrieves Image Shift Units supported on the current printer.
     *
     * @return <p>supported Image Shift Units {@link CopyAttributes.ImageShiftUnits} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.ImageShiftUnits}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.ImageShiftUnits> getImageShiftUnitsList() {
        return Collections.unmodifiableList(mCapsCreator.mImageShiftUnitsList);
    }

    /**
     * Retrieves the range of ImageShiftXFrontRange.
     *
     * @return <p>supported range for ImageShiftXFrontRange.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getImageShiftXFrontRange() { return mCapsCreator.mImageShiftXFrontRange; }

    /**
     * Retrieves the range of ImageShiftYFrontRange.
     *
     * @return <p>supported range for ImageShiftYFrontRange.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getImageShiftYFrontRange() { return mCapsCreator.mImageShiftYFrontRange; }

    /**
     * Retrieves the range of ImageShiftXBackRange.
     *
     * @return <p>supported range for ImageShiftXBackRange.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getImageShiftXBackRange() { return mCapsCreator.mImageShiftXBackRange; }

    /**
     * Retrieves the range of ImageShiftYBackRange.
     *
     * @return <p>supported range for ImageShiftYBackRange.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public FloatRange getImageShiftYBackRange() { return mCapsCreator.mImageShiftYBackRange; }

    /**
     * Retrieves Booklet Borders Each Page options supported on the current printer.
     *
     * @return <p>supported Booklet Borders Each Page options {@link CopyAttributes.BookletBordersEachPage} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from (true/false).</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.BookletBordersEachPage> getBookletBordersEachPageList() {
        return Collections.unmodifiableList(mCapsCreator.mBookletBordersEachPageList);
    }

    /**
     * Retrieves Booklet Finishing Option supported on the current printer.
     *
     * @return supported Booklet Finishing Option {@link CopyAttributes.BookletFinishingOption} list.
     * An empty list indicates that the option is not supported by the printer.
     * @since API 6
     */
    public List<CopyAttributes.BookletFinishingOption> getBookletFinishingOptionList() {
        return Collections.unmodifiableList(mCapsCreator.mBookletFinishingOptionList);
    }

    /**
     * Retrieves Booklet Format options supported on the current printer.
     *
     * @return <p>supported Booklet Format options {@link CopyAttributes.BookletFormat} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.BookletFormat}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.BookletFormat> getBookletFormatList() {
        return Collections.unmodifiableList(mCapsCreator.mBookletFormatList);
    }

    /**
     * <p>Retrieves the supported capture mode options by the current printer.</p>
     *
     * @return <p>supported {@link CopyAttributes.CaptureMode} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.CaptureMode} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.CaptureMode> getCaptureModeList() {
        return Collections.unmodifiableList(mCapsCreator.mCaptureModeList);
    }

    /**
     * Retrieves staple modes supported on the current printer.
     *
     * @return supported staple mode options {@link CopyAttributes.StapleOption} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<CopyAttributes.StapleOption> getStapleOptionList() {
        return Collections.unmodifiableList(mCapsCreator.mStapleOption);
    }

    /**
     * Retrieves punch modes supported on the current printer.
     *
     * @return supported punch mode options {@link CopyAttributes.PunchMode} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<CopyAttributes.PunchMode> getPunchModeList() {
        return Collections.unmodifiableList(mCapsCreator.mPunchMode);
    }

    /**
     * Retrieves fold modes supported on the current printer.
     *
     * @return supported fold mode options {@link CopyAttributes.FoldMode} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<CopyAttributes.FoldMode> getFoldModeList() {
        return Collections.unmodifiableList(mCapsCreator.mFoldMode);
    }

    /**
     * Retrieves stampPosition supported on the current printer.
     *
     * @return supported stampPosition options {@link CopyAttributes.StampPosition} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<CopyAttributes.StampPosition> getStampPositionList() {
        return new ArrayList<>(mCapsCreator.mStampOptionMap.keySet());
    }

    /**
     * Retrieves stampOption supported on the current printer.
     *
     * @return supported stampOption.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * </ul>
     * </p>
     * @exception NullPointerException when stampPosition is passed null.
     * @since API 6
     */
    public StampOption getStampOption(CopyAttributes.StampPosition stampPosition) {
        if (stampPosition == null) {
            throw new NullPointerException();
        }
        return mCapsCreator.mStampOptionMap.get(stampPosition);
    }

    /**
     * Retrieves StampType supported on the current printer.
     *
     * @return supported StampType options {@link StampType} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<StampType> getStampTypeList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampTypeList();
    }

    /**
     * Retrieves StampPolicyType supported on the current printer.
     *
     * @return supported StampPolicyType options {@link StampPolicyType} list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<StampPolicyType> getStampPolicyTypeList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampPolicyTypeList();
    }

    /**
     * Retrieves StampFormatFont supported on the current printer.
     *
     * @return supported StampFormatFont options list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<String> getStampFormatFontList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampFormatFontList();
    }

    /**
     * Retrieves StampFormatTextSize supported on the current printer.
     *
     * @return supported StampFormatTextSize options list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<Integer> getStampFormatTextSizeList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampFormatTextSizeList();
    }

    /**
     * Retrieves StampFormatTextColor supported on the current printer.
     *
     * @return supported StampFormatTextColor options list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<String> getStampFormatTextColorList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampFormatTextColorList();
    }

    /**
     * Retrieves StampFormatStartingPage supported on the current printer.
     *
     * @return supported StampFormatStartingPage options list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public Range getStampFormatStartingPage(CopyAttributes.StampPosition stampPosition) {
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampFormatTextStartingPage();
    }

    /**
     * Retrieves StampFormatWhiteBackground supported on the current printer.
     *
     * @return supported StampFormatWhiteBackground options list.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public List<Boolean> getStampFormatWhiteBackgroundList(CopyAttributes.StampPosition stampPosition){
        return mCapsCreator.mStampOptionMap.get(stampPosition).getStampFormatWhiteBackgroundList();
    }

    /**
     * <p>Retrieve the supported watermark transparency range by the current printer.</p>
     *
     * @return <p>List of supported ranges for watermark transparency.
     * <ul>
     * <li>Return value ranges from [-2 to 2]</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public Range getWatermarkTransparencyRange() {
        return mCapsCreator.mWatermarkTransparencyRange;
    }

    /**
     * <p>Retrieve the supported watermark OnlyFirstPage options by the current printer.</p>
     *
     * @return <p> {@link CopyAttributes.WatermarkOnlyFirstPage} list.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either any of the boolean values in {@link CopyAttributes.WatermarkOnlyFirstPage} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.WatermarkOnlyFirstPage> getWatermarkOnlyFirstPageList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkOnlyFirstPageList);
    }

    /**
     * <p>Retrieve the supported darkness range by the current printer.</p>
     *
     * @return <p>List of supported ranges for watermark darkness.
     * <ul>
     * <li>Return value ranges from [1 to 5].</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public Range getWatermarkDarknessRange() {
        return mCapsCreator.mWatermarkDarknessRange;
    }

    /**
     * <p>Retrieve the supported watermark rotation options by the current printer.</p>
     *
     * @return <p> {@link CopyAttributes.WatermarkRotate45} list.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either any of the boolean values in {@link CopyAttributes.WatermarkRotate45} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.WatermarkRotate45> getWatermarkRotate45List() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkRotate45List);
    }

    /**
     * <p>Retrieve the supported watermark type options by the current printer.</p>
     *
     * @return <p> {@link CopyAttributes.WatermarkType} list.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either any of the values in {@link CopyAttributes.WatermarkType} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.WatermarkType> getWatermarkTypeList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkTypeList);
    }

    /**
     * <p>Retrieve the supported watermark message type by the current printer.</p>
     *
     * @return <p> {@link CopyAttributes.WatermarkMessageType} list.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either any of the values in {@link CopyAttributes.WatermarkMessageType} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.WatermarkMessageType> getWatermarkMessageTypeList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkMessageTypeList);
    }

    /**
     * <p>Retrieve the supported watermark background patterns by the current printer.</p>
     *
     * @return <p> {@link CopyAttributes.WatermarkBackgroundPattern} list.
     * <ul>
     * <li>Return value shouldn't be empty list and should be either any of the values in {@link CopyAttributes.WatermarkBackgroundPattern} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<CopyAttributes.WatermarkBackgroundPattern> getWatermarkBackgroundPatternList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkBackgroundPatternList);
    }

    /**
     * <p>Retrieve the supported watermark background color by the current printer. Following colors are supported
     * <ul>
     * <li>Gray</li>
     * <li>Cyan</li>
     * <li>Magenta</li>
     * </ul>
     * </p>
     *
     * @return <p> List of supported watermark background colors.
     * <ul>
     * <li>Return value shouldn't be empty list and it should be a list containing colors like gray, cyan and magenta.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<String> getWatermarkBackgroundColorList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkBackgroundColorList);
    }

    /**
     * <p>Retrieve the supported watermark font options by the current printer. Following fonts are supported
     * <ul>
     * <li>LetterGothic</li>
     * <li>AntiqueOlive</li>
     * <li>CenturySchoolbook</li>
     * <li>Garamond</li>
     * </ul>
     * </p>
     *
     * @return <p> List of supported watermark fonts.
     * <ul>
     * <li>Return value shouldn't be empty list and it should be a list containing fonts like LetterGothic, AntiqueOlive, CenturySchoolbook and Garamond.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<String> getWatermarkFontList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkFontList);
    }

    /**
     * <p>Retrieve the supported watermark text color by the current printer. Following colors are supported
     * <ul>
     * <li>Black</li>
     * <li>Yellow</li>
     * <li>Green</li>
     * <li>Red</li>
     * <li>Blue</li>
     * <li>SkyBlue</li>
     * <li>Purple</li>
     * </ul>
     * </p>
     * @return <p> List of supported Watermark TextColors.
     * <ul>
     * <li>Return value shouldn't be empty list and it should be a list containing colors like Black, Yellow,
     * Green, Red, Blue, SkyBlue and Purple.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<String> getWatermarkTextColorList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkTextColorList);
    }

    /**
     * <p>Retrieve the supported watermark text size options by the current printer. Following text size are supported
     * <ul>
     * <li>30</li>
     * <li>40</li>
     * <li>60</li>
     * </ul>
     * </p>
     *
     * @return <p> List of supported Watermark TextSize.
     * <ul>
     * <li>Return value shouldn't be empty list and it should be a list containing fonts size like 30 point, 40 point, and 60 point.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public List<Integer> getWatermarkTextSizeList() {
        return Collections.unmodifiableList(mCapsCreator.mWatermarkTextSizeList);
    }
}
