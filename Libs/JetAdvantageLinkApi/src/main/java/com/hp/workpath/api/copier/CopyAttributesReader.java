// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import android.support.annotation.Keep;

import java.util.HashMap;
import java.util.Map;

/**
 * Reads attributes which are for requesting a copy from the printer.
 *
 * @since API 3
 */
@SuppressWarnings({"unused"})
public class CopyAttributesReader {
    @Keep
    private final CopyAttributes mAttrs;

    /**
     * Constructs this object from the copy attributes provided.
     *
     * @param copyAttrs The copy attributes object used to construct this object.
     * @since API 3
     */
    @Keep
    public CopyAttributesReader(final CopyAttributes copyAttrs) {
        mAttrs = copyAttrs;
    }

    /**
     * The version in which the attributes were created.
     *
     * @return The version in which the attributes were created.
     * @hide for internal use
     * @since API 3
     */
    @Keep
    public int getVersion() {
        return mAttrs.mVersion;
    }

    /**
     * The color mode for the copy supported by the current printer.
     *
     * @return <p>the current color mode for copy job from {@link CopyAttributes.Colormode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the color modes from {@link CopyAttributes.Colormode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.ColorMode getColorMode() {
        return mAttrs.mColorMode;
    }

    /**
     * The original orientation for the copy supported by the current printer.
     *
     * @return <p>the current original orientation for copy job from {@link CopyAttributes.Orientation}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the original orientation from {@link CopyAttributes.Orientation}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.Orientation getOrientation() {
        return mAttrs.mOrientation;
    }

    /**
     * The duplex mode for the copy supported by the current printer.
     *
     * @return <p>the current duplex mode for copy job from {@link CopyAttributes.Duplex}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the duplex mode from {@link CopyAttributes.Duplex}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.Duplex getScanDuplex() {
        return mAttrs.mScanDuplex;
    }

    /**
     * The size of the scanned region for the copy supported by the current printer.
     *
     * @return <p>the current scan size type for copy job from {@link CopyAttributes.ScanSize}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the scan size type from {@link CopyAttributes.ScanSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.ScanSize getScanSize() {
        return mAttrs.mScanSize;
    }

    /**
     * The custom length of the paper to scan for the copy supported by the current printer.
     *
     * @return <p>the current scan size custom length for copy job.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public Float getScanCustomLength() {
        return mAttrs.mScanCustomLength;
    }

    /**
     * The custom width of the paper to scan for the copy supported by the current printer.
     *
     * @return <p>the current scan size type custom width for copy job.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public Float getScanCustomWidth() {
        return mAttrs.mScanCustomWidth;
    }

    /**
     * The source for the scanning supported by the current printer.
     *
     * @return <p>the current scan source for copy job {@link CopyAttributes.ScanSize}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.ScanSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.ScanSource getScanSource() {
        return mAttrs.mScanSource;
    }

    /**
     * Show a preview of the copied images supported by the current printer.
     *
     * @return <p>the current selected copy preview option for copy job from {@link CopyAttributes.CopyPreview}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {true/false}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.CopyPreview getCopyPreview() {
        return mAttrs.mCopyPreview;
    }

    /**
     * The background clean up for the copy supported by the current printer.
     *
     * @return <p>the current selected background cleanup option for copy job from {@link CopyAttributes.BackgroundCleanup}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.BackgroundCleanup}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.BackgroundCleanup getBackgroundCleanup() {
        return mAttrs.mBackgroundCleanup;
    }

    /**
     * The contrast adjustment for the copy supported by the current printer.
     *
     * @return <p>the current selected background cleanup option for copy job from {@link CopyAttributes.ContrastAdjustment}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.ContrastAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.ContrastAdjustment getContrastAdjustment() {
        return mAttrs.mContrastAdjustment;
    }

    /**
     * The darkness adjustment for the copy supported by the current printer.
     *
     * @return <p>the current selected darkness adjustment option for copy job from {@link CopyAttributes.DarknessAdjustment}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.DarknessAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.DarknessAdjustment getDarknessAdjustment() {
        return mAttrs.mDarknessAdjustment;
    }

    /**
     * The sharpness adjustment for the copy supported by the current printer.
     *
     * @return <p>the current selected sharpness adjustment option for copy job from {@link CopyAttributes.SharpnessAdjustment}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.SharpnessAdjustment}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.SharpnessAdjustment getSharpnessAdjustment() {
        return mAttrs.mSharpnessAdjustment;
    }

    /**
     * The duplex mode for the print supported by the current printer.
     *
     * @return <p>the current selected duplex mode option for copy job from {@link CopyAttributes.Duplex}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.Duplex}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.Duplex getPrintDuplex() {
        return mAttrs.mPrintDuplex;
    }

    /**
     * The size of the paper to print supported by the current printer.
     *
     * @return <p>the current selected print size type for copy job from {@link CopyAttributes.PaperSize}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.PaperSize}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.PaperSize getPrintSize() {
        return mAttrs.mPrintSize;
    }


    /**
     * The custom length of the paper to print supported by the current printer.
     *
     * @return <p>the current selected print size custom length for copy job.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [5.5, 18.0].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public Float getPrintCustomLength() {
        return mAttrs.mPrintCustomLength;
    }

    /**
     * The custom width of the paper to print supported by the current printer.
     *
     * @return <p>the current selected print size custom width for copy job.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [3.86, 12.6].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public Float getPrintCustomWidth() {
        return mAttrs.mPrintCustomWidth;
    }

    /**
     * The number of copies to copy supported by the current printer.
     *
     * @return <p>the current selected copies values for copy job.
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [1, 9999].</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public int getCopies() {
        return mAttrs.mCopies;
    }

    /**
     * The collate mode for the copy supported by the current printer.
     *
     * @return <p>the current selected collate mode for copy job from {@link CopyAttributes.CollateMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.CollateMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.CollateMode getCollateMode() {
        return mAttrs.mCollateMode;
    }

    /**
     * The source of paper (tray) for the copy supported by the current printer.
     *
     * @return <p>the current selected papaer source for copy job from {@link CopyAttributes.PaperSource}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.PaperSource}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.PaperSource getPaperSource() {
        return mAttrs.mPaperSource;
    }

    /**
     * The paper type for the copy supported by the current printer.
     *
     * @return <p>the current selected paper type for copy job from {@link CopyAttributes.PaperType}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.PaperType}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.PaperType getPaperType() {
        return mAttrs.mPaperType;
    }

    /**
     * Reduce or enlarge the image during copying supported by the current printer.
     *
     * @return <p>the current selected scale mode for copy job from {@link CopyAttributes.ScaleMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.ScaleMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.ScaleMode getScaleMode() {
        return mAttrs.mScaleMode;
    }

    /**
     * Scale percent (for manual scale mode) for the copy supported by the current printer.
     *
     * @return <p>the current selected scale percent for copy job.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public int getScalePercent() {
        return mAttrs.mScalePercent;
    }

    /**
     * Text/graphics optimization option for the copy supported by the current printer.
     *
     * @return <p>the current selected text/graphics optimization option for copy job from {@link CopyAttributes.TextGraphicsOptimization}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.TextGraphicsOptimization}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.TextGraphicsOptimization getTextGraphicsOptimization() {
        return mAttrs.mTextGraphicsOptimization;
    }

    /**
     * The job assembly mode for the copy supported by the current printer.
     *
     * @return <p>the current selected job assembly mode option for copy job from {@link CopyAttributes.JobAssemblyMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.JobAssemblyMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.JobAssemblyMode getJobAssemblyMode() {
        return mAttrs.mJobAssemblyMode;
    }

    /**
     * The job execution mode for the copy supported by the current printer.
     *
     * @return <p>the current selected job execution mode option for copy job from {@link CopyAttributes.JobExecutionMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.JobExecutionMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.JobExecutionMode getJobExecutionMode() {
        return mAttrs.mJobExecutionMode;
    }

    /**
     * Number up mode for the copy supported by the current printer.
     *
     * @return <p>the current selected number up mode for copy job from {@link CopyAttributes.NumberUpMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.NumberUpMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.NumberUpMode getNumberUpMode() {
        return mAttrs.mNumberUpMode;
    }

    /**
     * Number up direction for the copy supported by the current printer.
     *
     * @return <p>the current selected number up direction for copy job from {@link CopyAttributes.NumberUpDirection}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.NumberUpDirection}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    @Keep
    public CopyAttributes.NumberUpDirection getNumberUpDirection() {
        return mAttrs.mNumberUpDirection;
    }

    /**
     * The folder name for the storing copy job supported by the current printer.
     *
     * @return <p>the current selected store job folder name for copy job.
     * <ul>
     * <li>Return can be null if the folder name is null.</li>
     * <li>Return can be empty if the folder name is empty.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoreJobFolderName() {
        return mAttrs.mStoreJobFolderName;
    }

    /**
     * The name for the storing copy job supported by the current printer.
     *
     * @return <p>the current selected store job name for copy.
     * <ul>
     * <li>Return can be null if the job name is null.</li>
     * <li>Return can be empty if the job name is empty.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public String getStoreJobName() {
        return mAttrs.mStoreJobName;
    }

    /**
     * The retention mode for power cycle supported by the current printer.
     *
     * @return <p>the current selected store job retention mode on power cycle from {@link CopyAttributes.RetentionMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.RetentionMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public CopyAttributes.RetentionMode getStoredJobRetentionModeOnPowerCycle() {
        return mAttrs.mStoredJobRetentionModeOnPowerCycle;
    }

    /**
     * The retention mode for release job supported by the current printer.
     *
     * @return <p>the current selected store job retention mode on release job from {@link CopyAttributes.RetentionMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.RetentionMode}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public CopyAttributes.RetentionMode getStoredJobRetentionModeOnRelease() {
        return mAttrs.mStoredJobRetentionModeOnRelease;
    }

    /**
     * The password type for stored job supported by the current printer.
     *
     * @return <p>the current selected store job credentials type.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link JobCredentialsAttributes}.</li>
     * </ul>
     * </p>
     *
     * @since API 3
     */
    public JobCredentialsAttributes getStoredJobCredentialsAttributes() {
        return mAttrs.mStoredJobCredentialsAttributes;
    }

    /**
     * The output bin for copy supported by the current printer.
     *
     * @return <p>the current selected output bin options from {@link CopyAttributes.OutputBin}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link CopyAttributes.OutputBin}.</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    public CopyAttributes.OutputBin getOutputBin() {
        return mAttrs.mOutputBin;
    }

    /**
     * The progress dialog mode for copy supported by the current printer.
     *
     * @return <p>the current selected progress dialog mode from {@link CopyAttributes.ProgressDialogMode}.
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {true/false}.</li>
     * </ul>
     * </p>
     *
     * @since API 5
     */
    public CopyAttributes.ProgressDialogMode getProgressDialogMode() {
        return mAttrs.mProgressDialogMode;
    }

    /**
     * <p>The Erase Margin Units for copy.</p>
     *
     * @return <p>Current erase margin unit of the device.
     * <ul>
     * <li>Return can't be empty and should be either any of the units from {@link CopyAttributes.EraseMarginUnit}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.EraseMarginUnit getEraseMarginUnit() { return mAttrs.mEraseMarginUnit; }

    /**
     * <p>The Erase BackBottom Margin for copy.</p>
     *
     * @return <p>Current Erase BackBottom Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseBackBottomMargin() { return mAttrs.mEraseBackBottom; }

    /**
     * <p>The Erase BackLeft Margin for copy.</p>
     *
     * @return <p>Current Erase BackLeft Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseBackLeftMargin() { return mAttrs.mEraseBackLeft; }

    /**
     * <p>The Erase BackRight Margin for copy.</p>
     *
     * @return <p>Current Erase BackRight Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseBackRightMargin() { return mAttrs.mEraseBackRight; }

    /**
     * <p>The Erase BackTop Margin for copy.</p>
     *
     * @return <p>Current Erase BackTop Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseBackTopMargin() { return mAttrs.mEraseBackTop; }

    /**
     * <p>The Erase FrontBottom Margin for copy.</p>
     *
     * @return <p>Current Erase FrontBottom Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseFrontBottomMargin() { return mAttrs.mEraseFrontBottom; }

    /**
     * <p>The Erase FrontLeft Margin for copy.</p>
     *
     * @return <p>Current Erase FrontLeft Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseFrontLeftMargin() { return mAttrs.mEraseFrontLeft; }

    /**
     * <p>The Erase FrontRight Margin for copy.</p>
     *
     * @return <p>Current Erase FrontRight Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseFrontRightMargin() { return mAttrs.mEraseFrontRight; }

    /**
     * <p>The Erase FrontTop Margin for copy.</p>
     *
     * @return <p>Current Erase FrontTop Margin of the device.
     * <ul>
     * <li>Return value ranges from [0 - 999] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Millimeters.</li>
     * <li>Return value ranges from [0 - 39] if {@link CopyAttributes.EraseMarginUnit getEraseMarginUnit} is Inches.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getEraseFrontTopMargin() { return mAttrs.mEraseFrontTop; }

    /**
     * <p>The capture mode for the copy.</p>
     *
     * @return <p>Selected capture mode option available in {@link CopyAttributes.CaptureMode} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.CaptureMode} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.CaptureMode getCaptureMode() {
        return mAttrs.mCaptureMode;
    }

    /**
     * The Image Shift Reduce To Fit for copy supported by the current printer.
     *
     * @return <p>The current selected Image Shift Reduce To Fit option available in {@link CopyAttributes.ImageShiftReduceToFit} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {true/false}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public CopyAttributes.ImageShiftReduceToFit getImageShiftReduceToFit() {
        return mAttrs.mImageShiftReduceToFit;
    }

    /**
     * The Image Shift Units for copy supported by the current printer.
     *
     * @return <p>The current selected Image Shift Units from {@link CopyAttributes.ImageShiftUnits}.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from {@link CopyAttributes.ImageShiftUnits}.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public CopyAttributes.ImageShiftUnits getImageShiftUnits() {
        return mAttrs.mImageShiftUnits;
    }

    /**
     * The Image Shift X Front value for copy supported by the current printer.
     *
     * @return <p>The current selected ImageShiftXFront value.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getImageShiftXFront() { return mAttrs.mImageShiftXFront; }

    /**
     * The Image Shift Y Front value for copy supported by the current printer.
     *
     * @return <p>The current selected ImageShiftYFront value.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getImageShiftYFront() { return mAttrs.mImageShiftYFront; }

    /**
     * The Image Shift X Back value for copy supported by the current printer.
     *
     * @return <p>The current selected ImageShiftXBack value.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getImageShiftXBack() { return mAttrs.mImageShiftXBack; }

    /**
     * The Image Shift Y Back value for copy supported by the current printer.
     *
     * @return <p>The current selected ImageShiftYBack value.
     * <ul>
     * <li>Return value ranges from [-26.0, 26.0] for Millimeters.</li>
     * <li>Return value ranges from [-1, 1] for Inches.</li>
     * <li>Return can't be empty and should be either any of the values with in the valid range.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public Float getImageShiftYBack() { return mAttrs.mImageShiftYBack; }

    /**
     * The Booklet Borders Each Page for copy supported by the current printer.
     *
     * @return <p> The current selected Booklet Borders Each Page option from {@link CopyAttributes.BookletBordersEachPage}.
     * <ul>
     * <li>Return can't be empty and should be either any of the options from (true/false).</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public CopyAttributes.BookletBordersEachPage getBookletBordersEachPage() {
        return mAttrs.mBookletBordersEachPage;
    }

    /**
     * The Booklet Finishing Option for copy supported by the current printer.
     *
     * @return <p>The current selected Booklet Finishing Option from {@link CopyAttributes.BookletFinishingOption}.
     * <ul>
     * <li>An empty value indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public CopyAttributes.BookletFinishingOption getBookletFinishingOption() {
        return mAttrs.mBookletFinishingOption;
    }

    /**
     * The Booklet Format for copy supported by the current printer.
     *
     * @return <p>The current selected Booklet Format Option from {@link CopyAttributes.BookletFormat}.
     * <ul>
     * <li>An empty value indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    public CopyAttributes.BookletFormat getBookletFormat() {
        return mAttrs.mBookletFormat;
    }

    /**
     * The staple mode for copy supported by the current printer.
     *
     * @return <p>Selected staple mode option available in {@link CopyAttributes.StapleOption} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.StapleOption} list.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public CopyAttributes.StapleOption getStapleOption() {
        return mAttrs.mStapleOption;
    }

    /**
     * The punch mode for copy supported by the current printer.
     *
     * @return <p>Selected punch mode option available in {@link CopyAttributes.PunchMode} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.PunchMode} list.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public CopyAttributes.PunchMode getPunchMode() {
        return mAttrs.mPunchMode;
    }

    /**
     * The fold mode for copy supported by the current printer.
     *
     * @return <p>Selected fold mode option available in {@link CopyAttributes.FoldMode} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.FoldMode} list.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public CopyAttributes.FoldMode getFoldMode() {
        return mAttrs.mFoldMode;
    }

    /**
     * The StampOption for copy supported by the current printer.
     *
     * @return <p>Selected StampOption option available in {@link CopyAttributes.StampPosition} list.
     * <ul>
     * <li>Return can't be empty and should be either any of the values from {@link CopyAttributes.StampPosition} list.</li>
     * </ul>
     * </p>
     * @since API 6
     */
    public Map<CopyAttributes.StampPosition, StampOption> getStampOption() {
        Map<CopyAttributes.StampPosition, StampOption> stampOptionMap = new HashMap<>();
        for(String stampPositionName : mAttrs.mStampOptionMap.keySet()){
            stampOptionMap.put(CopyAttributes.StampPosition.valueOf(stampPositionName), mAttrs.mStampOptionMap.get(stampPositionName));
        }
        return stampOptionMap;
    }

    /**
     * <p>The Watermark Transparency value for copy.</p>
     *
     * @return <p>Current watermark transparency value from the device.
     * <ul>
     * <li>Return value ranges from [-2 to 2].</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public int getWatermarkTransparency() {
        return mAttrs.mWatermarkTransparency;
    }

    /**
     * <p>The Watermark TextSize value for copy.</p>
     *
     * @return <p>Current watermark textSize value from the device.
     * <ul>
     * <li>Return value should be either any of the following predefined values like 30 point, 40 point, and 60 point.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public int getWatermarkTextSize() {
        return mAttrs.mWatermarkTextSize;
    }

    /**
     * <p>The Watermark BackgroundColor value for copy.</p>
     *
     * @return <p>Current watermark background color value from the device.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of the following colors like gray, cyan and magenta.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public String getWatermarkBackgroundColor() {
        return mAttrs.mWatermarkBackgroundColor;
    }

    /**
     * <p>The Watermark Font value for copy.</p>
     *
     * @return <p>Current watermark font value from the device.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of following fonts values like LetterGothic, AntiqueOlive, CenturySchoolbook and Garamond.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public String getWatermarkFont() {
        return mAttrs.mWatermarkFont;
    }

    /**
     * <p>The Watermark TextColor value for copy.</p>
     *
     * @return <p>Current watermark text color values from the device.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of following color values like Black, Yellow,
     * Green, Red, Blue, SkyBlue and Purple.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public String getWatermarkTextColor() {
        return mAttrs.mWatermarkTextColor;
    }

    /**
     * <p>The Watermark OnlyFirstPage value for copy.</p>
     *
     * @return <p>Selected watermark OnlyFirstPage option.
     * <ul>
     * <li>Return true if watermark is needed only on first page and false if watermark is required in every page.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.WatermarkOnlyFirstPage getWatermarkOnlyFirstPage() {
        return mAttrs.mWatermarkOnlyFirstPage;
    }

    /**
     * <p>The Watermark Darkness value for copy.</p>
     *
     * @return <p>Current watermark darkness value of the device.
     * <ul>
     * <li>Return value ranges from [1 to 5].</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public int getWatermarkDarkness() {
        return mAttrs.mWatermarkDarkness;
    }

    /**
     * <p>The Watermark Text value for copy.</p>
     *
     * @return <p>Current watermark text values from the device.
     * <ul>
     * <li>Return value shouldn't be empty or null and it should have atleast a character or a string.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public String getWatermarkText() {
        return mAttrs.mWatermarkText;
    }

    /**
     * <p>The Watermark Rotation value for copy.</p>
     *
     * @return <p>Selected watermark rotation option.
     * <ul>
     * <li>Return true if watermark rotate45 is required and false if watermark rotate45 is not required.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.WatermarkRotate45 getWatermarkRotation45() {
        return mAttrs.mWatermarkRotate45;
    }

    /**
     * <p>The Watermark type value for copy.</p>
     *
     * @return <p>Selected watermark type option.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of values in {@link CopyAttributes.WatermarkType} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.WatermarkType getWatermarkType() {
        return mAttrs.mWatermarkType;
    }

    /**
     * <p>The Watermark message type value for copy.</p>
     *
     * @return <p>Selected watermark message type option.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of values in {@link CopyAttributes.WatermarkMessageType} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.WatermarkMessageType getWatermarkMessageType() {
        return mAttrs.mWatermarkMessageType;
    }

    /**
     * <p>The Watermark background pattern value for copy.</p>
     *
     * @return <p>Selected watermark background pattern option.
     * <ul>
     * <li>Return value shouldn't be empty or null and should be either any of values in {@link CopyAttributes.WatermarkBackgroundPattern} list.</li>
     * </ul>
     * </p>
     *
     * @since API 6
     */
    @Keep
    public CopyAttributes.WatermarkBackgroundPattern getWatermarkBackgroundPattern() {
        return mAttrs.mWatermarkBackgroundPattern;
    }
}
