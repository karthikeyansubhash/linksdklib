// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the set of attributes provided from the printer when requesting a scan
 *
 * @hide The creation of capabilities is meant for the scanner service to expose a read-only list of capabilities. Clients should not be writing
 * capabilities.
 */
public class ScanAttributesCapsCreator {

    final List<ScanAttributes.ColorMode> mColorModeList;

    final List<ScanAttributes.Duplex> mPlexList;

    final List<ScanAttributes.Destination> mDestinationList;

    final List<ScanAttributes.DocumentFormat> mMeDocFormatList;

    final List<ScanAttributes.Orientation> mOrientationList;

    final List<ScanAttributes.ScanPreview> mScanPreviewList;

    final List<ScanAttributes.Resolution> mResolutionList;

    final List<ScanAttributes.ScanSize> mScanSizeList;

    final List<ScanAttributes.BackgroundCleanup> mBackgroundCleanupList;

    final List<ScanAttributes.ContrastAdjustment> mContrastAdjustmentList;

    final List<ScanAttributes.DarknessAdjustment> mDarknessAdjustmentList;

    final List<ScanAttributes.BlankImageRemovalMode> mBlankImageRemovalModeList;

    final List<ScanAttributes.ColorDropoutMode> mColorDropoutModeList;

    final List<ScanAttributes.CropMode> mCropModeList;

    final List<ScanAttributes.OutputQuality> mOutputQualityList;

    final List<ScanAttributes.ProgressDialogMode> mProgressDialogModeList;

    final List<ScanAttributes.TransmissionMode> mTransmissionModeList;

    final Map<ScanAttributes.ColorMode, List<ScanAttributes.DocumentFormat>> mDocumentFormatsByColorMode;

    final List<ScanAttributes.JobAssemblyMode> mJobAssemblyModeList;

    final List<ScanAttributes.SharpnessAdjustment> mSharpnessAdjustmentList;

    final List<ScanAttributes.MediaWeightAdjustment> mMediaWeightAdjustmentList;

    final List<ScanAttributes.TextPhotoOptimization> mTextPhotoOptimizationList;

    final List<ScanAttributes.MediaSource> mMediaSourceList;

    final List<ScanAttributes.MisfeedDetectionMode> mMisfeedDetectionModeList;

    final Range mCustomLengthRange;

    final Range mCustomWidthRange;

    final List<ScanAttributes.SplitAttachmentByPage> mSplitAttachmentByPageList;

    final Range mMaxPagesPerAttachmentRange;

    final List<ScanAttributes.EraseMarginUnit> mEraseMarginUnitList;

    final Range mEraseBackBottomRange;

    final Range mEraseBackLeftRange;

    final Range mEraseBackRightRange;

    final Range mEraseBackTopRange;

    final Range mEraseFrontBottomRange;

    final Range mEraseFrontLeftRange;

    final Range mEraseFrontRightRange;

    final Range mEraseFrontTopRange;

    final List<ScanAttributes.CaptureMode> mCaptureModeList;

    final List<ScanAttributes.AutomaticToneMode> mAutomaticToneModeList;

    final List<ScanAttributes.AutomaticStraightenMode> mAutomaticStraightenModeList;

    private ScanAttributesCapsCreator(final Builder builder) {
        mColorModeList = builder.mColorModeList;
        mPlexList = builder.mPlexList;
        mDestinationList = builder.mDestinationList;
        mMeDocFormatList = builder.mMeDocFormatList;
        mOrientationList = builder.mOrientationList;
        mScanPreviewList = builder.mScanPreviewList;
        mResolutionList = builder.mResolutionList;
        mScanSizeList = builder.mScanSizeList;
        mBackgroundCleanupList = builder.mBackgroundCleanupList;
        mContrastAdjustmentList = builder.mContrastAdjustmentList;
        mDarknessAdjustmentList = builder.mDarknessAdjustmentList;
        mBlankImageRemovalModeList = builder.mBlankImageRemovalModeList;
        mColorDropoutModeList = builder.mColorDropoutModeList;
        mCropModeList = builder.mCropModeList;
        mOutputQualityList = builder.mOutputQualityList;
        mProgressDialogModeList = builder.mProgressDialogModeList;
        mTransmissionModeList = builder.mTransmissionModeList;
        mDocumentFormatsByColorMode = builder.mDocumentFormatsByColorMode;
        mJobAssemblyModeList = builder.mJobAssemblyModeList;
        mSharpnessAdjustmentList = builder.mSharpnessAdjustmentList;
        mMediaWeightAdjustmentList = builder.mMediaWeightAdjustmentList;
        mTextPhotoOptimizationList = builder.mTextPhotoOptimizationList;
        mMediaSourceList = builder.mMediaSourceList;
        mMisfeedDetectionModeList = builder.mMisfeedDetectionModeList;
        mCustomLengthRange = builder.mCustomLengthRange;
        mCustomWidthRange = builder.mCustomWidthRange;
        // ScanTicket3 Support
        mSplitAttachmentByPageList = builder.mSplitAttachmentByPageList;
        mMaxPagesPerAttachmentRange = builder.mMaxPagesPerAttachmentRange;
        mEraseMarginUnitList = builder.mEraseMarginUnitList;
        mEraseBackBottomRange = builder.mEraseBackBottomRange;
        mEraseBackLeftRange = builder.mEraseBackLeftRange;
        mEraseBackRightRange = builder.mEraseBackRightRange;
        mEraseBackTopRange = builder.mEraseBackTopRange;
        mEraseFrontBottomRange = builder.mEraseFrontBottomRange;
        mEraseFrontLeftRange = builder.mEraseFrontLeftRange;
        mEraseFrontRightRange = builder.mEraseFrontRightRange;
        mEraseFrontTopRange = builder.mEraseFrontTopRange;
        mCaptureModeList = builder.mCaptureModeList;
        mAutomaticToneModeList = builder.mAutomaticToneModeList;
        mAutomaticStraightenModeList = builder.mAutomaticStraightenModeList;
    }

    /**
     * Builder for determining the attributes supported for requesting images to be sent back to the client.
     */
    public static class Builder {
        final List<ScanAttributes.ColorMode> mColorModeList = new ArrayList<>();

        final List<ScanAttributes.Duplex> mPlexList = new ArrayList<>();

        final List<ScanAttributes.Destination> mDestinationList = new ArrayList<>();

        final List<ScanAttributes.DocumentFormat> mMeDocFormatList = new ArrayList<>();

        final List<ScanAttributes.Orientation> mOrientationList = new ArrayList<>();

        final List<ScanAttributes.ScanPreview> mScanPreviewList = new ArrayList<>();

        final List<ScanAttributes.Resolution> mResolutionList = new ArrayList<>();

        final List<ScanAttributes.ScanSize> mScanSizeList = new ArrayList<>();

        final List<ScanAttributes.BackgroundCleanup> mBackgroundCleanupList = new ArrayList<>();

        final List<ScanAttributes.ContrastAdjustment> mContrastAdjustmentList = new ArrayList<>();

        final List<ScanAttributes.DarknessAdjustment> mDarknessAdjustmentList = new ArrayList<>();

        final List<ScanAttributes.BlankImageRemovalMode> mBlankImageRemovalModeList = new ArrayList<>();

        final List<ScanAttributes.ColorDropoutMode> mColorDropoutModeList = new ArrayList<>();

        final List<ScanAttributes.CropMode> mCropModeList = new ArrayList<>();

        final List<ScanAttributes.OutputQuality> mOutputQualityList = new ArrayList<>();

        final List<ScanAttributes.ProgressDialogMode> mProgressDialogModeList = new ArrayList<>();

        final List<ScanAttributes.TransmissionMode> mTransmissionModeList = new ArrayList<>();

        final Map<ScanAttributes.ColorMode, List<ScanAttributes.DocumentFormat>> mDocumentFormatsByColorMode = new HashMap<>();

        final List<ScanAttributes.JobAssemblyMode> mJobAssemblyModeList = new ArrayList<>();

        final List<ScanAttributes.SharpnessAdjustment> mSharpnessAdjustmentList = new ArrayList<>();

        final List<ScanAttributes.MediaWeightAdjustment> mMediaWeightAdjustmentList = new ArrayList<>();

        final List<ScanAttributes.TextPhotoOptimization> mTextPhotoOptimizationList = new ArrayList<>();

        final List<ScanAttributes.MediaSource> mMediaSourceList = new ArrayList<>();

        final List<ScanAttributes.MisfeedDetectionMode> mMisfeedDetectionModeList = new ArrayList<>();

        final Range mCustomLengthRange = new Range(0, 0);

        final Range mCustomWidthRange = new Range(0, 0);

        final List<ScanAttributes.SplitAttachmentByPage> mSplitAttachmentByPageList = new ArrayList<>();

        final Range mMaxPagesPerAttachmentRange = new Range(0, 0);

        final List<ScanAttributes.EraseMarginUnit> mEraseMarginUnitList = new ArrayList<>();

        final Range mEraseBackBottomRange = new Range(0, 0);
        final Range mEraseBackLeftRange = new Range(0, 0);
        final Range mEraseBackRightRange = new Range(0, 0);
        final Range mEraseBackTopRange = new Range(0, 0);
        final Range mEraseFrontBottomRange = new Range(0, 0);
        final Range mEraseFrontLeftRange = new Range(0, 0);
        final Range mEraseFrontRightRange = new Range(0, 0);
        final Range mEraseFrontTopRange = new Range(0, 0);

        final List<ScanAttributes.CaptureMode> mCaptureModeList = new ArrayList<>();

        final List<ScanAttributes.AutomaticToneMode> mAutomaticToneModeList = new ArrayList<>();

        final List<ScanAttributes.AutomaticStraightenMode> mAutomaticStraightenModeList = new ArrayList<>();

        /**
         * Constructs a new builder used for determining the attributes supported for scanned images back to the client.
         */
        public Builder() {
            // Empty constructor
            mColorModeList.add(ScanAttributes.ColorMode.DEFAULT);
            mPlexList.add(ScanAttributes.Duplex.DEFAULT);
            mMeDocFormatList.add(ScanAttributes.DocumentFormat.DEFAULT);
            mOrientationList.add(ScanAttributes.Orientation.DEFAULT);
            mResolutionList.add(ScanAttributes.Resolution.DEFAULT);
            mScanPreviewList.add(ScanAttributes.ScanPreview.DEFAULT);
            mScanSizeList.add(ScanAttributes.ScanSize.DEFAULT);
            mBackgroundCleanupList.add(ScanAttributes.BackgroundCleanup.DEFAULT);
            mContrastAdjustmentList.add(ScanAttributes.ContrastAdjustment.DEFAULT);
            mDarknessAdjustmentList.add(ScanAttributes.DarknessAdjustment.DEFAULT);
            mBlankImageRemovalModeList.add(ScanAttributes.BlankImageRemovalMode.DEFAULT);
            mColorDropoutModeList.add(ScanAttributes.ColorDropoutMode.DEFAULT);
            mCropModeList.add(ScanAttributes.CropMode.DEFAULT);
            mOutputQualityList.add(ScanAttributes.OutputQuality.DEFAULT);
            mProgressDialogModeList.add(ScanAttributes.ProgressDialogMode.DEFAULT);
            mTransmissionModeList.add(ScanAttributes.TransmissionMode.DEFAULT);
            mDocumentFormatsByColorMode.put(ScanAttributes.ColorMode.DEFAULT, Collections.singletonList(ScanAttributes.DocumentFormat.DEFAULT));
            mJobAssemblyModeList.add(ScanAttributes.JobAssemblyMode.DEFAULT);
            mSharpnessAdjustmentList.add(ScanAttributes.SharpnessAdjustment.DEFAULT);
            mMediaWeightAdjustmentList.add(ScanAttributes.MediaWeightAdjustment.DEFAULT);
            mTextPhotoOptimizationList.add(ScanAttributes.TextPhotoOptimization.DEFAULT);
            mMediaSourceList.add(ScanAttributes.MediaSource.DEFAULT);
            mMisfeedDetectionModeList.add(ScanAttributes.MisfeedDetectionMode.DEFAULT);

            mSplitAttachmentByPageList.add(ScanAttributes.SplitAttachmentByPage.DEFAULT);
            mEraseMarginUnitList.add(ScanAttributes.EraseMarginUnit.DEFAULT);
            mCaptureModeList.add(ScanAttributes.CaptureMode.DEFAULT);
            mAutomaticToneModeList.add(ScanAttributes.AutomaticToneMode.DEFAULT);
            mAutomaticStraightenModeList.add(ScanAttributes.AutomaticStraightenMode.DEFAULT);
        }

        /**
         * Adds a color set in which the scanned image(s) will be supplied.
         *
         * @param colorMode The color set.
         * @return this builder for method chaining.
         */
        public Builder addColorMode(final ScanAttributes.ColorMode colorMode) {
            mColorModeList.add(colorMode);
            return this;
        }

        /**
         * Adds a duplex set in which the scanned image(s) will be supplied.
         *
         * @param plex The Duplex set.
         * @return this builder for method chaining.
         */
        public Builder addPlex(final ScanAttributes.Duplex plex) {
            mPlexList.add(plex);
            return this;
        }

        /**
         * Adds a original orientation set in which the scanned image(s) will be supplied.
         *
         * @param orientation The original orientation set.
         * @return this builder for method chaining.
         */
        public Builder addOrientation(final ScanAttributes.Orientation orientation) {
            mOrientationList.add(orientation);
            return this;
        }

        /**
         * Adds a destination that the printer can scan to.
         *
         * @param destination The destination that the printer can scan to.
         * @return this builder for method chaining.
         */
        public Builder addDestination(final ScanAttributes.Destination destination) {
            mDestinationList.add(destination);
            return this;
        }

        /**
         * Adds an output format for me destinations in which the scanned image(s) can be supplied.
         *
         * @param documentFormat The output format
         * @return this builder for method chaining.
         */
        public Builder addMeDocumentFormat(final ScanAttributes.DocumentFormat documentFormat) {
            mMeDocFormatList.add(documentFormat);
            return this;
        }

        /**
         * Adds scanPreview
         *
         * @param scanPreview The scan preview value
         * @return this builder for method chaining.
         */
        public Builder addScanPreview(final ScanAttributes.ScanPreview scanPreview) {
            mScanPreviewList.add(scanPreview);
            return this;
        }

        /**
         * Adds Resolution
         *
         * @param resolution Resolution value
         * @return this builder for method chaining.
         */
        public Builder addResolution(final ScanAttributes.Resolution resolution) {
            mResolutionList.add(resolution);
            return this;
        }

        /**
         * Adds ScanSize
         *
         * @param scanSize Resolution value
         * @return this builder for method chaining.
         */
        public Builder addScanSize(final ScanAttributes.ScanSize scanSize) {
            mScanSizeList.add(scanSize);
            return this;
        }

        /**
         * Sets scan size custom length range
         *
         * @param lowerBound minimum value
         * @param upperBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setCustomLengthRange(final float lowerBound, final float upperBound) {
            mCustomLengthRange.mLowerBound = lowerBound;
            mCustomLengthRange.mUpperBound = upperBound;
            return this;
        }

        /**
         * Sets scan size custom width range
         *
         * @param lowerBound minimum value
         * @param upperBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setCustomWidthRange(final float lowerBound, final float upperBound) {
            mCustomWidthRange.mLowerBound = lowerBound;
            mCustomWidthRange.mUpperBound = upperBound;
            return this;
        }

        /**
         * Adds background cleanup
         *
         * @param backgroundCleanup BackgroundCleanup value
         * @return this builder for method chaining.
         */
        public Builder addBackgroundCleanup(final ScanAttributes.BackgroundCleanup backgroundCleanup) {
            mBackgroundCleanupList.add(backgroundCleanup);
            return this;
        }

        /**
         * Adds contrast adjustment
         *
         * @param contrastAdjustment BackgroundCleanup value
         * @return this builder for method chaining.
         */
        public Builder addContrastAdjustment(final ScanAttributes.ContrastAdjustment contrastAdjustment) {
            mContrastAdjustmentList.add(contrastAdjustment);
            return this;
        }

        /**
         * Adds darkness adjustment
         *
         * @param darknessAdjustment BackgroundCleanup value
         * @return this builder for method chaining.
         */
        public Builder addDarknessAdjustment(final ScanAttributes.DarknessAdjustment darknessAdjustment) {
            mDarknessAdjustmentList.add(darknessAdjustment);
            return this;
        }

        /**
         * Adds blank image removal mode
         *
         * @param blankImageRemovalMode value
         * @return this builder for method chaining.
         */
        public Builder addBlankImageRemovalMode(final ScanAttributes.BlankImageRemovalMode blankImageRemovalMode) {
            mBlankImageRemovalModeList.add(blankImageRemovalMode);
            return this;
        }

        /**
         * Adds color dropout mode
         *
         * @param colorDropoutMode value
         * @return this builder for method chaining.
         */
        public Builder addColorDropoutMode(final ScanAttributes.ColorDropoutMode colorDropoutMode) {
            mColorDropoutModeList.add(colorDropoutMode);
            return this;
        }

        /**
         * Adds crop mode
         *
         * @param cropMode value
         * @return this builder for method chaining.
         */
        public Builder addCropMode(final ScanAttributes.CropMode cropMode) {
            mCropModeList.add(cropMode);
            return this;
        }

        /**
         * Adds output quality
         *
         * @param outputQuality value
         * @return this builder for method chaining.
         */
        public Builder addOutputQuality(final ScanAttributes.OutputQuality outputQuality) {
            mOutputQualityList.add(outputQuality);
            return this;
        }

        /**
         * Adds progress dialog mode
         *
         * @param progressDialogMode value
         * @return this builder for method chaining.
         */
        public Builder addProgressDialogMode(final ScanAttributes.ProgressDialogMode progressDialogMode) {
            mProgressDialogModeList.add(progressDialogMode);
            return this;
        }

        /**
         * Adds transmission mode
         *
         * @param transmissionMode value
         * @return this builder for method chaining.
         */
        public Builder addTransmissionMode(final ScanAttributes.TransmissionMode transmissionMode) {
            mTransmissionModeList.add(transmissionMode);
            return this;
        }

        /**
         * Adds document formats list for color mode
         *
         * @param colorMode       color mode
         * @param documentFormats list of supported document format for that color mode
         * @return this builder for method chaining.
         */
        public Builder addDocumentFormatsByColorMode(final ScanAttributes.ColorMode colorMode,
                                                     final List<ScanAttributes.DocumentFormat> documentFormats) {
            mDocumentFormatsByColorMode.put(colorMode, documentFormats);
            return this;
        }

        /**
         * Adds job assembly mode
         *
         * @param jobAssemblyMode value
         * @return this builder for method chaining.
         */
        public Builder addJobAssemblyMode(final ScanAttributes.JobAssemblyMode jobAssemblyMode) {
            mJobAssemblyModeList.add(jobAssemblyMode);
            return this;
        }

        /**
         * Adds sharpness adjustment
         *
         * @param sharpnessAdjustment value
         * @return this builder for method chaining.
         */
        public Builder addSharpnessAdjustment(final ScanAttributes.SharpnessAdjustment sharpnessAdjustment) {
            mSharpnessAdjustmentList.add(sharpnessAdjustment);
            return this;
        }

        /**
         * Adds media weight adjustment
         *
         * @param mediaWeightAdjustment value
         * @return this builder for method chaining.
         */
        public Builder addMediaWeightAdjustment(final ScanAttributes.MediaWeightAdjustment mediaWeightAdjustment) {
            mMediaWeightAdjustmentList.add(mediaWeightAdjustment);
            return this;
        }

        /**
         * Adds text photo optimization
         *
         * @param textPhotoOptimization value
         * @return this builder for method chaining.
         */
        public Builder addTextPhotoOptimization(final ScanAttributes.TextPhotoOptimization textPhotoOptimization) {
            mTextPhotoOptimizationList.add(textPhotoOptimization);
            return this;
        }

        /**
         * Adds media source
         *
         * @param mediaSource value
         * @return this builder for method chaining.
         */
        public Builder addMediaSource(final ScanAttributes.MediaSource mediaSource) {
            mMediaSourceList.add(mediaSource);
            return this;
        }

        /**
         * Adds misfeed detection mode
         *
         * @param misfeedDetectionMode value
         * @return this builder for method chaining.
         */
        public Builder addMisfeedDetectionMode(final ScanAttributes.MisfeedDetectionMode misfeedDetectionMode) {
            mMisfeedDetectionModeList.add(misfeedDetectionMode);
            return this;
        }

        /**
         * SplitAttachmentByPage mode
         *
         * @param splitAttachmentByPage value
         * @return this builder for method chaining.
         */
        public Builder addSplitAttachmentByPage (final ScanAttributes.SplitAttachmentByPage splitAttachmentByPage) {
            mSplitAttachmentByPageList.add(splitAttachmentByPage);
            return this;
        }

        /**
         * MaxPagesPerAttachmentRange range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setMaxPagesPerAttachmentRange(final int lowerBound, final int highBound) {
            mMaxPagesPerAttachmentRange.mLowerBound = lowerBound;
            mMaxPagesPerAttachmentRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseMarginUnit mode
         *
         * @param eraseMarginUnit value
         * @return this builder for method chaining.
         */
        public Builder addEraseMarginUnits (final ScanAttributes.EraseMarginUnit eraseMarginUnit) {
            mEraseMarginUnitList.add(eraseMarginUnit);
            return this;
        }

        /**
         * EraseBackBottom range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseBackBottomRange(final float lowerBound, final float highBound) {
            mEraseBackBottomRange.mLowerBound = lowerBound;
            mEraseBackBottomRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseBackLeft range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseBackLeftRange(final float lowerBound, final float highBound) {
            mEraseBackLeftRange.mLowerBound = lowerBound;
            mEraseBackLeftRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseBackRight range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseBackRightRange(final float lowerBound, final float highBound) {
            mEraseBackRightRange.mLowerBound = lowerBound;
            mEraseBackRightRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseBackTop range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseBackTopRange(final float lowerBound, final float highBound) {
            mEraseBackTopRange.mLowerBound = lowerBound;
            mEraseBackTopRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseFrontBottom range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseFrontBottomRange(final float lowerBound, final float highBound) {
            mEraseFrontBottomRange.mLowerBound = lowerBound;
            mEraseFrontBottomRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseFrontLeft range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseFrontLeftRange(final float lowerBound, final float highBound) {
            mEraseFrontLeftRange.mLowerBound = lowerBound;
            mEraseFrontLeftRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseFrontRight range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseFrontRightRange(final float lowerBound, final float highBound) {
            mEraseFrontRightRange.mLowerBound = lowerBound;
            mEraseFrontRightRange.mUpperBound = highBound;
            return this;
        }

        /**
         * EraseFrontTop range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public Builder setEraseFrontTopRange(final float lowerBound, final float highBound) {
            mEraseFrontTopRange.mLowerBound = lowerBound;
            mEraseFrontTopRange.mUpperBound = highBound;
            return this;
        }

        /**
         * CaptureMode mode
         *
         * @param captureMode value
         * @return this builder for method chaining.
         */
        public Builder addCaptureMode (final ScanAttributes.CaptureMode captureMode) {
            mCaptureModeList.add(captureMode);
            return this;
        }

        /**
         * AutomaticToneMode mode
         *
         * @param automaticToneMode value
         * @return this builder for method chaining.
         */
        public Builder addAutomaticToneMode (final ScanAttributes.AutomaticToneMode automaticToneMode) {
            mAutomaticToneModeList.add(automaticToneMode);
            return this;
        }

        /**
         * AutomaticStraightenMode mode
         *
         * @param automaticStraightenMode value
         * @return this builder for method chaining.
         */
        public Builder addAutomaticStraightenMode (final ScanAttributes.AutomaticStraightenMode automaticStraightenMode) {
            mAutomaticStraightenModeList.add(automaticStraightenMode);
            return this;
        }

        /**
         * Combine all of the capabilities in this into a ScanAttributesCapsCreator object.
         *
         * @return a ScanAttributesCapsCreator object containing all of the attributes.
         */
        public ScanAttributesCapsCreator build() {
            return new ScanAttributesCapsCreator(this);
        }
    }
}
