// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import android.annotation.SuppressLint;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.scanner.ScanAttributes.BackgroundCleanup;
import com.hp.workpath.api.scanner.ScanAttributes.BlankImageRemovalMode;
import com.hp.workpath.api.scanner.ScanAttributes.ColorDropoutMode;
import com.hp.workpath.api.scanner.ScanAttributes.ColorMode;
import com.hp.workpath.api.scanner.ScanAttributes.ContrastAdjustment;
import com.hp.workpath.api.scanner.ScanAttributes.CropMode;
import com.hp.workpath.api.scanner.ScanAttributes.DarknessAdjustment;
import com.hp.workpath.api.scanner.ScanAttributes.Destination;
import com.hp.workpath.api.scanner.ScanAttributes.DocumentFormat;
import com.hp.workpath.api.scanner.ScanAttributes.Duplex;
import com.hp.workpath.api.scanner.ScanAttributes.JobAssemblyMode;
import com.hp.workpath.api.scanner.ScanAttributes.Orientation;
import com.hp.workpath.api.scanner.ScanAttributes.OutputQuality;
import com.hp.workpath.api.scanner.ScanAttributes.ProgressDialogMode;
import com.hp.workpath.api.scanner.ScanAttributes.Resolution;
import com.hp.workpath.api.scanner.ScanAttributes.ScanPreview;
import com.hp.workpath.api.scanner.ScanAttributes.ScanSize;
import com.hp.workpath.api.scanner.ScanAttributes.SharpnessAdjustment;
import com.hp.workpath.api.scanner.ScanAttributes.TransmissionMode;
import com.hp.workpath.api.scanner.ScanAttributes.MediaWeightAdjustment;
import com.hp.workpath.api.scanner.ScanAttributes.TextPhotoOptimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Scan attributes capabilities provided available values from the device.
 *
 * @since API 1
 */
public final class ScanAttributesCaps {
    final ScanAttributesCapsCreator mCapsCreator;

    /**
     * Constructor to create default object
     *
     * @param creator object containing the scan capabilities
     * @hide The creator is hidden
     * @since API 1
     */
    public ScanAttributesCaps(final ScanAttributesCapsCreator creator) {
        mCapsCreator = creator;
    }

    /**
     * Gets the groups of color sets supported by the device.
     *
     * @return the color modes supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ColorMode from {@link com.hp.workpath.api.scanner.ScanAttributes.ColorMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ColorMode> getColorModeList() {
        return Collections.unmodifiableList(mCapsCreator.mColorModeList);
    }

    /**
     * Gets duplex options supported by the device
     *
     * @return List of duplex options.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Duplex from {@link com.hp.workpath.api.scanner.ScanAttributes.Duplex}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<Duplex> getDuplexList() {
        return Collections.unmodifiableList(mCapsCreator.mPlexList);
    }

    /**
     * The destinations to which the scanned images can be sent.
     *
     * @return the supported destinations.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Destination from {@link com.hp.workpath.api.scanner.ScanAttributes.Destination}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<Destination> getDestinationList() {
        return Collections.unmodifiableList(mCapsCreator.mDestinationList);
    }

    /**
     * The output formats in which the scanned images can be created.
     * The supported format are destination-specific.
     *
     * @param destination type of destination.
     * @return the output formats supported.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the DocumentFormat from {@link com.hp.workpath.api.scanner.ScanAttributes.DocumentFormat}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @SuppressLint("RestrictedApi")
    public List<DocumentFormat> getDocumentFormatList(final Destination destination) {
        Preconditions.checkNotNull(destination, "Destination must be provided");

        switch (destination) {
            /*
             * For the extension like scan to email.
             */
            case ME:
            default:
                Collections.sort(mCapsCreator.mMeDocFormatList);
                mCapsCreator.mMeDocFormatList.remove(DocumentFormat.DEFAULT);
                mCapsCreator.mMeDocFormatList.add(0, DocumentFormat.DEFAULT);

                return Collections.unmodifiableList(mCapsCreator.mMeDocFormatList);
        }
    }

    /**
     * A list of supported scan sizes.
     *
     * @return scan sizes list.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ScanSize from {@link com.hp.workpath.api.scanner.ScanAttributes.ScanSize}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ScanSize> getScanSizeList() {
        Collections.sort(mCapsCreator.mScanSizeList);

        mCapsCreator.mScanSizeList.remove(ScanSize.DEFAULT);
        mCapsCreator.mScanSizeList.add(0, ScanSize.DEFAULT);

        mCapsCreator.mScanSizeList.remove(ScanSize.CUSTOM);
        mCapsCreator.mScanSizeList.add(ScanSize.CUSTOM);

        return Collections.unmodifiableList(mCapsCreator.mScanSizeList);
    }

    /**
     * Gets a range of custom length for scan size.
     *
     * @return range for custom length.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [2.9, 34.0].</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public Range getCustomLengthRange() {
        return mCapsCreator.mCustomLengthRange;
    }

    /**
     * Gets a range of custom width for scan size.
     *
     * @return range for custom width.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [2.16, 8.5].</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public Range getCustomWidthRange() {
        return mCapsCreator.mCustomWidthRange;
    }

    /**
     * Gets the supported original orientation options by the device
     *
     * @return supported Orientation list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Orientation from {@link com.hp.workpath.api.scanner.ScanAttributes.Orientation}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<Orientation> getOrientationList() {
        return Collections.unmodifiableList(mCapsCreator.mOrientationList);
    }

    /**
     * Gets the supported options for scan preview by the device
     *
     * @return supported ScanPreview list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ScanPreview from {@link com.hp.workpath.api.scanner.ScanAttributes.ScanPreview}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ScanPreview> getScanPreviewList() {
        return Collections.unmodifiableList(mCapsCreator.mScanPreviewList);
    }

    /**
     * Gets the supported scan resolution options by the device
     *
     * @return supported Resolution list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the Resolution from {@link com.hp.workpath.api.scanner.ScanAttributes.Resolution}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<Resolution> getResolutionList() {
        return Collections.unmodifiableList(mCapsCreator.mResolutionList);
    }

    /**
     * Gets the supported scan background cleanup options by the device
     *
     * @return supported BackgroundCleanup list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the BackgroundCleanup from {@link com.hp.workpath.api.scanner.ScanAttributes.BackgroundCleanup}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<BackgroundCleanup> getBackgroundCleanupList() {
        return Collections.unmodifiableList(mCapsCreator.mBackgroundCleanupList);
    }

    /**
     * Gets the supported scan contrast adjustment options by the device
     *
     * @return supported ContrastAdjustment list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ContrastAdjustment from {@link com.hp.workpath.api.scanner.ScanAttributes.ContrastAdjustment}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ContrastAdjustment> getContrastAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mContrastAdjustmentList);
    }

    /**
     * Gets the supported scan darkness adjustment options by the device
     *
     * @return supported DarknessAdjustment list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the DarknessAdjustment from {@link com.hp.workpath.api.scanner.ScanAttributes.DarknessAdjustment}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<DarknessAdjustment> getDarknessAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mDarknessAdjustmentList);
    }

    /**
     * Gets the supported scan blank image removal modes by the device
     *
     * @return supported BlankImageRemovalMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the BlankImageRemovalMode from {@link com.hp.workpath.api.scanner.ScanAttributes.BlankImageRemovalMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<BlankImageRemovalMode> getBlankImageRemovalModeList() {
        return Collections.unmodifiableList(mCapsCreator.mBlankImageRemovalModeList);
    }

    /**
     * Gets the supported scan color dropout modes by the device
     *
     * @return supported ColorDropoutMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ColorDropoutMode from {@link com.hp.workpath.api.scanner.ScanAttributes.ColorDropoutMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ColorDropoutMode> getColorDropoutModeList() {
        return Collections.unmodifiableList(mCapsCreator.mColorDropoutModeList);
    }

    /**
     * Gets the supported scan crop modes by the device
     *
     * @return supported CropMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the CropMode from {@link com.hp.workpath.api.scanner.ScanAttributes.CropMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<CropMode> getCropModeList() {
        return Collections.unmodifiableList(mCapsCreator.mCropModeList);
    }

    /**
     * Gets the supported modes for using scan progress dialog by the device
     *
     * @return supported ProgressDialogMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the ProgressDialogMode from {@link com.hp.workpath.api.scanner.ScanAttributes.ProgressDialogMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ProgressDialogMode> getProgressDialogModeList() {
        return Collections.unmodifiableList(mCapsCreator.mProgressDialogModeList);
    }

    /**
     * Gets the supported scan output qualities by the device
     *
     * @return supported OutputQuality list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the OutputQuality from {@link com.hp.workpath.api.scanner.ScanAttributes.OutputQuality}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<OutputQuality> getOutputQualityList() {
        return Collections.unmodifiableList(mCapsCreator.mOutputQualityList);
    }

    /**
     * Gets the supported scan transmission modes by the device
     *
     * @return supported TransmissionMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the TransmissionMode from {@link com.hp.workpath.api.scanner.ScanAttributes.TransmissionMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<TransmissionMode> getTransmissionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mTransmissionModeList);
    }

    /**
     * Gets the list of supported document formats for each color mode
     *
     * @return supported {@link ScanAttributes.DocumentFormat} list
     *
     * @since API 1
     */
    public Map<ColorMode, List<DocumentFormat>> getDocumentFormatsByColorMode() {
        return Collections.unmodifiableMap(mCapsCreator.mDocumentFormatsByColorMode);
    }

    /**
     * Gets the supported job assembly modes by the device
     *
     * @return supported JobAssemblyMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the JobAssemblyMode from {@link com.hp.workpath.api.scanner.ScanAttributes.JobAssemblyMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<JobAssemblyMode> getJobAssemblyModeList() {
        return Collections.unmodifiableList(mCapsCreator.mJobAssemblyModeList);
    }

    /**
     * Gets the supported sharpness adjustment options by the device
     *
     * @return supported SharpnessAdjustment list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the SharpnessAdjustment from {@link com.hp.workpath.api.scanner.ScanAttributes.SharpnessAdjustment}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<SharpnessAdjustment> getSharpnessAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mSharpnessAdjustmentList);
    }

    /**
     * Gets the supported media weight adjustment options by the device
     *
     * @return supported MediaWeightAdjustment list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the MediaWeightAdjustment from {@link com.hp.workpath.api.scanner.ScanAttributes.MediaWeightAdjustment}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<MediaWeightAdjustment> getMediaWeightAdjustmentList() {
        return Collections.unmodifiableList(mCapsCreator.mMediaWeightAdjustmentList);
    }

    /**
     * Gets the supported text/photo optimization options by the device
     *
     * @return supported TextPhotoOptimization list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the TextPhotoOptimization from {@link com.hp.workpath.api.scanner.ScanAttributes.TextPhotoOptimization}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<TextPhotoOptimization> getTextPhotoOptimizationList() {
        return Collections.unmodifiableList(mCapsCreator.mTextPhotoOptimizationList);
    }

    /**
     * Gets the supported media source options by the device
     *
     * @return supported MediaSource list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the MediaSource from {@link com.hp.workpath.api.scanner.ScanAttributes.MediaSource}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ScanAttributes.MediaSource> getMediaSourceList() {
        return Collections.unmodifiableList(mCapsCreator.mMediaSourceList);
    }

    /**
     * Gets the supported misfeed detection modes by the device
     *
     * @return supported MisfeedDetectionMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the MisfeedDetectionMode from {@link com.hp.workpath.api.scanner.ScanAttributes.MisfeedDetectionMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    public List<ScanAttributes.MisfeedDetectionMode> getMisfeedDetectionModeList() {
        return Collections.unmodifiableList(mCapsCreator.mMisfeedDetectionModeList);
    }

    /**
     * Gets the supported scan Split Attachment By Pages by the device
     *
     * @return supported SplitAttachmentByPage list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the SplitAttachmentByPage from {@link com.hp.workpath.api.scanner.ScanAttributes.SplitAttachmentByPage}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<ScanAttributes.SplitAttachmentByPage> getSplitAttachmentByPageList() {
        if (mCapsCreator.mSplitAttachmentByPageList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mSplitAttachmentByPageList);
    }

    /**
     * Gets a range of max pages per attachment range.
     *
     * @return range for max pages per attachment range
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [1.0, 9999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getMaxPagesPerAttachmentRange() {
        return mCapsCreator.mMaxPagesPerAttachmentRange;
    }

    /**
     * Gets the supported scan EraseMarginUnits by the device
     *
     * @return supported EraseMarginUnit list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the EraseMarginUnit from {@link com.hp.workpath.api.scanner.ScanAttributes.EraseMarginUnit}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<ScanAttributes.EraseMarginUnit> getEraseMarginUnitList() {
        if (mCapsCreator.mEraseMarginUnitList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mEraseMarginUnitList);
    }

    /**
     * Gets a range of EraseBackBottomRange.
     *
     * @return range for EraseBackBottomRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseBackBottomRange() {
        return mCapsCreator.mEraseBackBottomRange;
    }

    /**
     * Gets a range of EraseBackLeftRange.
     *
     * @return range for EraseBackLeftRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseBackLeftRange() {
        return mCapsCreator.mEraseBackLeftRange;
    }

    /**
     * Gets a range of EraseBackRightRange
     *
     * @return range for EraseBackRightRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseBackRightRange() {
        return mCapsCreator.mEraseBackRightRange;
    }

    /**
     * Gets a range of EraseBackTopRange
     *
     * @return range for EraseBackTopRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseBackTopRange() {
        return mCapsCreator.mEraseBackTopRange;
    }

    /**
     * Gets a range of EraseFrontBottomRange
     *
     * @return range for EraseFrontBottomRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseFrontBottomRange() {
        return mCapsCreator.mEraseFrontBottomRange;
    }

    /**
     * Gets a range of EraseFrontLeftRange
     *
     * @return range for EraseFrontLeftRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseFrontLeftRange() {
        return mCapsCreator.mEraseFrontLeftRange;
    }

    /**
     * Gets a range of EraseFrontRightRange
     *
     * @return range for EraseFrontRightRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseFrontRightRange() {
        return mCapsCreator.mEraseFrontRightRange;
    }

    /**
     * Gets a range of EraseFrontTopRange
     *
     * @return range for EraseFrontTopRange
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public Range getEraseFrontTopRange() {
        return mCapsCreator.mEraseFrontTopRange;
    }

    /**
     * Gets the supported scan CaptureMode by the device
     *
     * @return supported CaptureMode list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the CaptureMode from {@link com.hp.workpath.api.scanner.ScanAttributes.CaptureMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<ScanAttributes.CaptureMode> getCaptureModeList() {
        if (mCapsCreator.mCaptureModeList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mCaptureModeList);
    }

    /**
     * Gets the supported scan AutomaticToneMode by the device
     *
     * @return supported AutomaticToneMode list'
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the AutomaticToneMode from {@link com.hp.workpath.api.scanner.ScanAttributes.AutomaticToneMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<ScanAttributes.AutomaticToneMode> getAutomaticToneModeList() {
        if (mCapsCreator.mAutomaticToneModeList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mAutomaticToneModeList);
    }

    /**
     * Gets the supported scan AutomaticStraightenMode by the device
     *
     * @return supported AutomaticStraightenMode} list
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the AutomaticStraightenMode from {@link com.hp.workpath.api.scanner.ScanAttributes.AutomaticStraightenMode}</li>
     * <li>An empty list indicates that the option is not supported by the printer.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    public List<ScanAttributes.AutomaticStraightenMode> getAutomaticStraightenModeList() {
        if (mCapsCreator.mAutomaticStraightenModeList == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(mCapsCreator.mAutomaticStraightenModeList);
    }
}
