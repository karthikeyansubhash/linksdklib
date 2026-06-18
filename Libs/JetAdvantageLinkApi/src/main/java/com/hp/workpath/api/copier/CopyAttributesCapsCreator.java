// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.copier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the set of attributes provided from the printer when requesting a copy
 *
 * @hide The creation of capabilities is meant for the copier service to expose a read-only list of capabilities.
 * Clients should not be writing capabilities.
 */
public class CopyAttributesCapsCreator {

    final List<CopyAttributes.ColorMode> mColorModeList;

    final List<CopyAttributes.Orientation> mOrientationList;

    final List<CopyAttributes.Duplex> mScanDuplexList;

    final List<CopyAttributes.ScanSize> mScanSizeList;

    final FloatRange mScanCustomLengthRange;

    final FloatRange mScanCustomWidthRange;

    final List<CopyAttributes.ScanSource> mScanSourceList;

    final List<CopyAttributes.CopyPreview> mCopyPreviewList;

    final List<CopyAttributes.BackgroundCleanup> mBackgroundCleanupList;

    final List<CopyAttributes.ContrastAdjustment> mContrastAdjustmentList;

    final List<CopyAttributes.DarknessAdjustment> mDarknessAdjustmentList;

    final List<CopyAttributes.SharpnessAdjustment> mSharpnessAdjustmentList;

    final List<CopyAttributes.Duplex> mPrintDuplexList;

    final List<CopyAttributes.PaperSize> mPrintSizeList;

    final List<CopyAttributes.PaperType> mPaperTypeList;

    final FloatRange mPrintCustomLengthRange;

    final FloatRange mPrintCustomWidthRange;

    final Range mCopiesRange;

    final List<CopyAttributes.CollateMode> mCollateModeList;

    final List<CopyAttributes.PaperSource> mPaperSourceList;

    final List<CopyAttributes.ScaleMode> mScaleModeList;

    final Map<CopyAttributes.ScanSource, Range> mScalePercentRangeMap;

    final List<CopyAttributes.TextGraphicsOptimization> mTextGraphicsOptimizationList;

    final List<CopyAttributes.JobAssemblyMode> mJobAssemblyModeList;

    final List<CopyAttributes.JobExecutionMode> mJobExecutionModeList;

    final List<CopyAttributes.NumberUpMode> mNumberUpModeList;

    final Map<CopyAttributes.NumberUpMode, List<CopyAttributes.NumberUpDirection>> mNumberUpDirectionMap;

    final List<JobCredentialsAttributes.PasswordType> mPasswordTypeList;

    final List<CopyAttributes.OutputBin> mOutputBinList;

    final List<CopyAttributes.ProgressDialogMode> mProgressDialogModeList;

    final List<CopyAttributes.EraseMarginUnit> mEraseMarginUnitList;

    final FloatRange mEraseBackBottomRange;

    final FloatRange mEraseBackLeftRange;

    final FloatRange mEraseBackRightRange;

    final FloatRange mEraseBackTopRange;

    final FloatRange mEraseFrontBottomRange;

    final FloatRange mEraseFrontLeftRange;

    final FloatRange mEraseFrontRightRange;

    final FloatRange mEraseFrontTopRange;

    final List<CopyAttributes.CaptureMode> mCaptureModeList;

    final List<CopyAttributes.ImageShiftReduceToFit> mImageShiftReduceToFitList;

    final List<CopyAttributes.ImageShiftUnits> mImageShiftUnitsList;

    final FloatRange mImageShiftXFrontRange;

    final FloatRange mImageShiftYFrontRange;

    final FloatRange mImageShiftXBackRange;

    final FloatRange mImageShiftYBackRange;

    final List<CopyAttributes.BookletBordersEachPage> mBookletBordersEachPageList;

    final List<CopyAttributes.BookletFinishingOption> mBookletFinishingOptionList;

    final List<CopyAttributes.BookletFormat> mBookletFormatList;

    final List<CopyAttributes.StapleOption> mStapleOption;

    final List<CopyAttributes.PunchMode> mPunchMode;

    final List<CopyAttributes.FoldMode> mFoldMode;

    final Map<CopyAttributes.StampPosition, StampOption> mStampOptionMap;
    final Range mWatermarkDarknessRange;
    final Range mWatermarkTransparencyRange;
    final List<CopyAttributes.WatermarkRotate45> mWatermarkRotate45List;
    final List<CopyAttributes.WatermarkType> mWatermarkTypeList;
    final List<CopyAttributes.WatermarkOnlyFirstPage> mWatermarkOnlyFirstPageList;
    final List<CopyAttributes.WatermarkMessageType> mWatermarkMessageTypeList;
    final List<CopyAttributes.WatermarkBackgroundPattern> mWatermarkBackgroundPatternList;

    final List<Integer> mWatermarkTextSizeList;
    final List<String> mWatermarkFontList;
    final List<String> mWatermarkBackgroundColorList;
    final List<String> mWatermarkTextColorList;

    private CopyAttributesCapsCreator(final Builder builder) {
        mColorModeList = builder.mColorModeList;
        mOrientationList = builder.mOrientationList;
        mScanDuplexList = builder.mScanDuplexList;
        mScanSizeList = builder.mScanSizeList;
        mScanCustomLengthRange = builder.mScanCustomLengthRange;
        mScanCustomWidthRange = builder.mScanCustomWidthRange;
        mScanSourceList = builder.mScanSourceList;
        mCopyPreviewList = builder.mCopyPreviewList;
        mBackgroundCleanupList = builder.mBackgroundCleanupList;
        mContrastAdjustmentList = builder.mContrastAdjustmentList;
        mDarknessAdjustmentList = builder.mDarknessAdjustmentList;
        mSharpnessAdjustmentList = builder.mSharpnessAdjustmentList;
        mPrintDuplexList = builder.mPrintDuplexList;
        mPrintSizeList = builder.mPrintSizeList;
        mPrintCustomLengthRange = builder.mPrintCustomLengthRange;
        mPrintCustomWidthRange = builder.mPrintCustomWidthRange;
        mCopiesRange = builder.mCopiesRange;
        mCollateModeList = builder.mCollateModeList;
        mPaperSourceList = builder.mPaperSourceList;
        mPaperTypeList = builder.mPaperTypeList;
        mScaleModeList = builder.mScaleModeList;
        mScalePercentRangeMap = builder.mScalePercentRangeMap;
        mTextGraphicsOptimizationList = builder.mTextGraphicsOptimizationList;
        mJobAssemblyModeList = builder.mJobAssemblyModeList;
        mJobExecutionModeList = builder.mJobExecutionModeList;
        mNumberUpModeList = builder.mNumberUpModeList;
        mNumberUpDirectionMap = builder.mNumberUpDirectionMap;
        mPasswordTypeList = builder.mPasswordTypeList;
        mOutputBinList = builder.mOutputBinList;
        mProgressDialogModeList = builder.mProgressDialogModeList;

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

        mImageShiftReduceToFitList = builder.mImageShiftReduceToFitList;
        mImageShiftUnitsList = builder.mImageShiftUnitsList;
        mImageShiftXFrontRange = builder.mImageShiftXFrontRange;
        mImageShiftYFrontRange = builder.mImageShiftYFrontRange;
        mImageShiftXBackRange = builder.mImageShiftXBackRange;
        mImageShiftYBackRange = builder.mImageShiftYBackRange;

        mBookletBordersEachPageList = builder.mBookletBordersEachPageList;
        mBookletFinishingOptionList = builder.mBookletFinishingOptionList;
        mBookletFormatList = builder.mBookletFormatList;
        mStapleOption = builder.mStapleOption;
        mPunchMode = builder.mPunchMode;
        mFoldMode = builder.mFoldMode;
        mStampOptionMap = builder.mStampOptionMap;
        mWatermarkTransparencyRange = builder.mWatermarkTransparencyRange;
        mWatermarkOnlyFirstPageList = builder.mWatermarkOnlyFirstPageList;
        mWatermarkDarknessRange = builder.mWatermarkDarknessRange;
        mWatermarkRotate45List = builder.mWatermarkRotate45List;
        mWatermarkTypeList = builder.mWatermarkTypeList;
        mWatermarkMessageTypeList = builder.mWatermarkMessageTypeList;
        mWatermarkBackgroundPatternList = builder.mWatermarkBackgroundPatternList;
        mWatermarkBackgroundColorList = builder.mWatermarkBackgroundColorList;
        mWatermarkFontList = builder.mWatermarkFontList;
        mWatermarkTextColorList = builder.mWatermarkTextColorList;
        mWatermarkTextSizeList = builder.mWatermarkTextSizeList;
    }

    /**
     * Builder for determining the attributes supported for requesting a copy job.
     */
    public static class Builder {
        final List<CopyAttributes.ColorMode> mColorModeList = new ArrayList<>();

        final List<CopyAttributes.Orientation> mOrientationList = new ArrayList<>();

        final List<CopyAttributes.Duplex> mScanDuplexList = new ArrayList<>();

        final List<CopyAttributes.ScanSize> mScanSizeList = new ArrayList<>();

        final FloatRange mScanCustomLengthRange = new FloatRange(0f, 0f);

        final FloatRange mScanCustomWidthRange = new FloatRange(0f, 0f);

        final List<CopyAttributes.ScanSource> mScanSourceList = new ArrayList<>();

        final List<CopyAttributes.CopyPreview> mCopyPreviewList = new ArrayList<>();

        final List<CopyAttributes.BackgroundCleanup> mBackgroundCleanupList = new ArrayList<>();

        final List<CopyAttributes.ContrastAdjustment> mContrastAdjustmentList = new ArrayList<>();

        final List<CopyAttributes.DarknessAdjustment> mDarknessAdjustmentList = new ArrayList<>();

        final List<CopyAttributes.SharpnessAdjustment> mSharpnessAdjustmentList = new ArrayList<>();

        final List<CopyAttributes.Duplex> mPrintDuplexList = new ArrayList<>();

        final List<CopyAttributes.PaperSize> mPrintSizeList = new ArrayList<>();

        final FloatRange mPrintCustomLengthRange = new FloatRange(0f, 0f);

        final FloatRange mPrintCustomWidthRange = new FloatRange(0f, 0f);

        final Range mCopiesRange = new Range(1, 1);

        final List<CopyAttributes.CollateMode> mCollateModeList = new ArrayList<>();

        final List<CopyAttributes.PaperSource> mPaperSourceList = new ArrayList<>();

        final List<CopyAttributes.PaperType> mPaperTypeList = new ArrayList<>();

        final List<CopyAttributes.ScaleMode> mScaleModeList = new ArrayList<>();

        final Map<CopyAttributes.ScanSource, Range> mScalePercentRangeMap = new HashMap<>();

        final List<CopyAttributes.TextGraphicsOptimization> mTextGraphicsOptimizationList = new ArrayList<>();

        final List<CopyAttributes.JobAssemblyMode> mJobAssemblyModeList = new ArrayList<>();

        final List<CopyAttributes.JobExecutionMode> mJobExecutionModeList = new ArrayList<>();

        final List<CopyAttributes.NumberUpMode> mNumberUpModeList = new ArrayList<>();

        final Map<CopyAttributes.NumberUpMode, List<CopyAttributes.NumberUpDirection>> mNumberUpDirectionMap = new HashMap<>();

        final List<JobCredentialsAttributes.PasswordType> mPasswordTypeList = new ArrayList<>();

        final List<CopyAttributes.OutputBin> mOutputBinList = new ArrayList<>();

        final List<CopyAttributes.ProgressDialogMode> mProgressDialogModeList = new ArrayList<>();

        final List<CopyAttributes.EraseMarginUnit> mEraseMarginUnitList = new ArrayList<>();

        final FloatRange mEraseBackBottomRange = new FloatRange(0, 0);
        final FloatRange mEraseBackLeftRange = new FloatRange(0, 0);
        final FloatRange mEraseBackRightRange = new FloatRange(0, 0);
        final FloatRange mEraseBackTopRange = new FloatRange(0, 0);
        final FloatRange mEraseFrontBottomRange = new FloatRange(0, 0);
        final FloatRange mEraseFrontLeftRange = new FloatRange(0, 0);
        final FloatRange mEraseFrontRightRange = new FloatRange(0, 0);
        final FloatRange mEraseFrontTopRange = new FloatRange(0, 0);

        final List<CopyAttributes.CaptureMode> mCaptureModeList = new ArrayList<>();

        final List<CopyAttributes.ImageShiftReduceToFit> mImageShiftReduceToFitList = new ArrayList<>();

        final List<CopyAttributes.ImageShiftUnits> mImageShiftUnitsList = new ArrayList<>();

        final FloatRange mImageShiftXFrontRange = new FloatRange(0, 0);
        final FloatRange mImageShiftYFrontRange = new FloatRange(0, 0);
        final FloatRange mImageShiftXBackRange = new FloatRange(0, 0);
        final FloatRange mImageShiftYBackRange = new FloatRange(0, 0);

        final List<CopyAttributes.BookletBordersEachPage> mBookletBordersEachPageList = new ArrayList<>();

        final List<CopyAttributes.BookletFinishingOption> mBookletFinishingOptionList = new ArrayList<>();

        final List<CopyAttributes.BookletFormat> mBookletFormatList = new ArrayList<>();

        final List<CopyAttributes.StapleOption> mStapleOption = new ArrayList<>();

        final List<CopyAttributes.PunchMode> mPunchMode= new ArrayList<>();

        final List<CopyAttributes.FoldMode> mFoldMode = new ArrayList<>();

        final Map<CopyAttributes.StampPosition, StampOption> mStampOptionMap = new HashMap<>();
        final Range mWatermarkTransparencyRange = new Range(0, 2);
        final List<CopyAttributes.WatermarkOnlyFirstPage> mWatermarkOnlyFirstPageList = new ArrayList<>();
        final Range mWatermarkDarknessRange = new Range(1, 5);

        final List<CopyAttributes.WatermarkRotate45> mWatermarkRotate45List = new ArrayList<>();
        final List<CopyAttributes.WatermarkType> mWatermarkTypeList = new ArrayList<>();
        final List<CopyAttributes.WatermarkMessageType> mWatermarkMessageTypeList = new ArrayList<>();
        final List<CopyAttributes.WatermarkBackgroundPattern> mWatermarkBackgroundPatternList = new ArrayList<>();
        final List<Integer> mWatermarkTextSizeList = new ArrayList<>();
        final List<String> mWatermarkFontList = new ArrayList<>();
        final List<String> mWatermarkBackgroundColorList = new ArrayList<>();
        final List<String> mWatermarkTextColorList = new ArrayList<>();

        /**
         * Constructs a new builder used for determining the attributes supported for scanned images back to the client.
         */
        public Builder() {
            // Empty constructor
            mColorModeList.add(CopyAttributes.ColorMode.DEFAULT);
            mOrientationList.add(CopyAttributes.Orientation.DEFAULT);
            mScanDuplexList.add(CopyAttributes.Duplex.DEFAULT);
            mScanSizeList.add(CopyAttributes.ScanSize.DEFAULT);
            mScanSourceList.add(CopyAttributes.ScanSource.DEFAULT);
            mCopyPreviewList.add(CopyAttributes.CopyPreview.DEFAULT);
            mBackgroundCleanupList.add(CopyAttributes.BackgroundCleanup.DEFAULT);
            mContrastAdjustmentList.add(CopyAttributes.ContrastAdjustment.DEFAULT);
            mDarknessAdjustmentList.add(CopyAttributes.DarknessAdjustment.DEFAULT);
            mSharpnessAdjustmentList.add(CopyAttributes.SharpnessAdjustment.DEFAULT);
            mPrintDuplexList.add(CopyAttributes.Duplex.DEFAULT);
            mPrintSizeList.add(CopyAttributes.PaperSize.DEFAULT);
            mCollateModeList.add(CopyAttributes.CollateMode.DEFAULT);
            mPaperSourceList.add(CopyAttributes.PaperSource.DEFAULT);
            mPaperTypeList.add(CopyAttributes.PaperType.DEFAULT);
            mScaleModeList.add(CopyAttributes.ScaleMode.DEFAULT);
            mScalePercentRangeMap.put(CopyAttributes.ScanSource.DEFAULT, new Range(100, 100));
            mTextGraphicsOptimizationList.add(CopyAttributes.TextGraphicsOptimization.DEFAULT);
            mJobAssemblyModeList.add(CopyAttributes.JobAssemblyMode.DEFAULT);
            mNumberUpModeList.add(CopyAttributes.NumberUpMode.DEFAULT);
            mNumberUpDirectionMap.put(CopyAttributes.NumberUpMode.DEFAULT, Collections.singletonList(CopyAttributes.NumberUpDirection.DEFAULT));
            mOutputBinList.add(CopyAttributes.OutputBin.DEFAULT);
            mProgressDialogModeList.add(CopyAttributes.ProgressDialogMode.DEFAULT);
            mEraseMarginUnitList.add(CopyAttributes.EraseMarginUnit.DEFAULT);
            mCaptureModeList.add(CopyAttributes.CaptureMode.DEFAULT);
            mImageShiftReduceToFitList.add(CopyAttributes.ImageShiftReduceToFit.DEFAULT);
            mImageShiftUnitsList.add(CopyAttributes.ImageShiftUnits.DEFAULT);
            mBookletBordersEachPageList.add(CopyAttributes.BookletBordersEachPage.DEFAULT);
            mBookletFinishingOptionList.add(CopyAttributes.BookletFinishingOption.DEFAULT);
            mBookletFormatList.add(CopyAttributes.BookletFormat.DEFAULT);
            mStapleOption.add(CopyAttributes.StapleOption.DEFAULT);
            mPunchMode.add(CopyAttributes.PunchMode.DEFAULT);
            mFoldMode.add(CopyAttributes.FoldMode.DEFAULT);
            mWatermarkRotate45List.add(CopyAttributes.WatermarkRotate45.DEFAULT);
            mWatermarkTypeList.add(CopyAttributes.WatermarkType.DEFAULT);
            mWatermarkOnlyFirstPageList.add(CopyAttributes.WatermarkOnlyFirstPage.DEFAULT);
            mWatermarkMessageTypeList.add(CopyAttributes.WatermarkMessageType.NONE);
            mWatermarkBackgroundPatternList.add(CopyAttributes.WatermarkBackgroundPattern.DEFAULT);

        }

        /**
         * Adds a color set in which the scanned image(s) will be supplied.
         *
         * @param colorMode The color set.
         * @return this builder for method chaining.
         */
        public Builder addColorMode(final CopyAttributes.ColorMode colorMode) {
            mColorModeList.add(colorMode);
            return this;
        }

        /**
         * Adds a original orientation set in which the scanned image(s) will be supplied.
         *
         * @param orientation The original orientation set.
         * @return this builder for method chaining.
         */
        public Builder addOrientation(final CopyAttributes.Orientation orientation) {
            mOrientationList.add(orientation);
            return this;
        }

        /**
         * Adds a scan duplex set in which the scanned image(s) will be supplied.
         *
         * @param duplex The Duplex set.
         * @return this builder for method chaining.
         */
        public Builder addScanDuplex(final CopyAttributes.Duplex duplex) {
            mScanDuplexList.add(duplex);
            return this;
        }

        /**
         * Adds a scan size
         *
         * @param scanSize scan size
         * @return this builder for method chaining.
         */
        public Builder addScanSize(final CopyAttributes.ScanSize scanSize) {
            mScanSizeList.add(scanSize);
            return this;
        }

        /**
         * Sets scan size custom length range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setScanCustomLengthRange(final float lowerBound, final float highBound) {
            mScanCustomLengthRange.mLowerBound = lowerBound;
            mScanCustomLengthRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets scan size custom width range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setScanCustomWidthRange(final float lowerBound, final float highBound) {
            mScanCustomWidthRange.mLowerBound = lowerBound;
            mScanCustomWidthRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Adds a scan source
         *
         * @param scanSource source value
         * @return this builder for method chaining.
         */
        public Builder addScanSource(final CopyAttributes.ScanSource scanSource) {
            mScanSourceList.add(scanSource);
            return this;
        }

        /**
         * Adds a copy preview.
         *
         * @param copyPreview The copy preview value
         * @return this builder for method chaining.
         */
        public Builder addCopyPreview(final CopyAttributes.CopyPreview copyPreview) {
            mCopyPreviewList.add(copyPreview);
            return this;
        }

        /**
         * Adds background cleanup
         *
         * @param backgroundCleanup value
         * @return this builder for method chaining.
         */
        public Builder addBackgroundCleanup(final CopyAttributes.BackgroundCleanup backgroundCleanup) {
            mBackgroundCleanupList.add(backgroundCleanup);
            return this;
        }

        /**
         * Adds contrast adjustment
         *
         * @param contrastAdjustment value
         * @return this builder for method chaining.
         */
        public Builder addContrastAdjustment(final CopyAttributes.ContrastAdjustment contrastAdjustment) {
            mContrastAdjustmentList.add(contrastAdjustment);
            return this;
        }

        /**
         * Adds darkness adjustment
         *
         * @param darknessAdjustment value
         * @return this builder for method chaining.
         */
        public Builder addDarknessAdjustment(final CopyAttributes.DarknessAdjustment darknessAdjustment) {
            mDarknessAdjustmentList.add(darknessAdjustment);
            return this;
        }

        /**
         * Adds sharpness adjustment
         *
         * @param sharpnessAdjustment value
         * @return this builder for method chaining.
         */
        public Builder addSharpnessAdjustment(final CopyAttributes.SharpnessAdjustment sharpnessAdjustment) {
            mSharpnessAdjustmentList.add(sharpnessAdjustment);
            return this;
        }

        /**
         * Adds a print duplex set in which the scanned image(s) will be supplied.
         *
         * @param duplex The Duplex set.
         * @return this builder for method chaining.
         */
        public Builder addPrintDuplex(final CopyAttributes.Duplex duplex) {
            mPrintDuplexList.add(duplex);
            return this;
        }

        /**
         * Adds a print size
         *
         * @param printSize print size
         * @return this builder for method chaining.
         */
        public Builder addPrintSize(final CopyAttributes.PaperSize printSize) {
            mPrintSizeList.add(printSize);
            return this;
        }

        /**
         * Sets print size custom length range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setPrintCustomLengthRange(final float lowerBound, final float highBound) {
            mPrintCustomLengthRange.mLowerBound = lowerBound;
            mPrintCustomLengthRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets print size custom width range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setPrintCustomWidthRange(final float lowerBound, final float highBound) {
            mPrintCustomWidthRange.mLowerBound = lowerBound;
            mPrintCustomWidthRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets copies range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setCopiesRange(final int lowerBound, final int highBound) {
            mCopiesRange.mLowerBound = lowerBound;
            mCopiesRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Adds a collate mode
         *
         * @param collateMode value
         * @return this builder for method chaining.
         */
        public Builder addCollateMode(final CopyAttributes.CollateMode collateMode) {
            mCollateModeList.add(collateMode);
            return this;
        }

        /**
         * Adds a paper source
         *
         * @param paperSource value
         * @return this builder for method chaining.
         */
        public Builder addPaperSource(final CopyAttributes.PaperSource paperSource) {
            mPaperSourceList.add(paperSource);
            return this;
        }

        /**
         * Adds a paper type
         *
         * @param paperType value
         * @return this builder for method chaining.
         */
        public Builder addPaperType(final CopyAttributes.PaperType paperType) {
            mPaperTypeList.add(paperType);
            return this;
        }

        /**
         * Adds a scale mode
         *
         * @param scaleMode value
         * @return this builder for method chaining.
         */
        public Builder addScaleMode(final CopyAttributes.ScaleMode scaleMode) {
            mScaleModeList.add(scaleMode);
            return this;
        }

        /**
         * Sets scale percent range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setScalePercentRange(final CopyAttributes.ScanSource scanSource, final int lowerBound, final int highBound) {
            mScalePercentRangeMap.put(scanSource, new Range(lowerBound, highBound));
            return this;
        }


        /**
         * Adds text graphics optimization
         *
         * @param textGraphicsOptimization value
         * @return this builder for method chaining.
         */
        public Builder addTextGraphicsOptimization(final CopyAttributes.TextGraphicsOptimization textGraphicsOptimization) {
            mTextGraphicsOptimizationList.add(textGraphicsOptimization);
            return this;
        }

        /**
         * Adds a job assembly mode
         *
         * @param jobAssemblyMode value
         * @return this builder for method chaining.
         */
        public Builder addJobAssemblyMode(final CopyAttributes.JobAssemblyMode jobAssemblyMode) {
            mJobAssemblyModeList.add(jobAssemblyMode);
            return this;
        }

        /**
         * Adds a job execution mode
         *
         * @param jobExecutionMode value
         * @return this builder for method chaining.
         */
        public Builder addJobExecutionMode(final CopyAttributes.JobExecutionMode jobExecutionMode) {
            mJobExecutionModeList.add(jobExecutionMode);
            return this;
        }

        /**
         * Adds a number up mode
         *
         * @param numberUpMode value
         * @return this builder for method chaining.
         */
        public Builder addNumberUpMode(final CopyAttributes.NumberUpMode numberUpMode) {
            mNumberUpModeList.add(numberUpMode);
            return this;
        }

        /**
         * Adds a number up direction
         *
         * @param numberUpMode       number up mode
         * @param numberUpDirections value
         * @return this builder for method chaining.
         */
        public Builder addNumberUpDirection(final CopyAttributes.NumberUpMode numberUpMode, final List<CopyAttributes.NumberUpDirection> numberUpDirections) {
            mNumberUpDirectionMap.put(numberUpMode, numberUpDirections);
            return this;
        }

        /**
         * Adds a password type
         *
         * @param passwordType value
         * @return this builder for method chaining.
         */
        public Builder addPasswordType(final JobCredentialsAttributes.PasswordType passwordType) {
            mPasswordTypeList.add(passwordType);
            return this;
        }

        /**
         * Adds outputBin
         *
         * @param outputBin value
         * @return this builder for method chaining.
         */
        public Builder addOutputBin(final CopyAttributes.OutputBin outputBin) {
            mOutputBinList.add(outputBin);
            return this;
        }


        /**
         * Adds progressDialogMode value
         *
         * @param progressDialogMode value
         * @return this builder for method chaining.
         */
        public Builder addProgressDialogMode(final CopyAttributes.ProgressDialogMode progressDialogMode) {
            mProgressDialogModeList.add(progressDialogMode);
            return this;
        }

        /**
         * EraseMarginUnit mode
         *
         * @param eraseMarginUnit value
         * @return this builder for method chaining.
         */
        public Builder addEraseMarginUnits (final CopyAttributes.EraseMarginUnit eraseMarginUnit) {
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

        /** Adds capture mode options
         *
         * @param captureMode value
         * @return this builder for method chaining.
         */
        public Builder addCaptureMode(final CopyAttributes.CaptureMode captureMode) {
            mCaptureModeList.add(captureMode);
            return this;
        }

        /**
         * Adds imageShiftReduceToFit value
         *
         * @param imageShiftReduceToFit value
         * @return this builder for method chaining.
         */
        public Builder addImageShiftReduceToFit(final CopyAttributes.ImageShiftReduceToFit imageShiftReduceToFit) {
            mImageShiftReduceToFitList.add(imageShiftReduceToFit);
            return this;
        }

        /**
         * Adds imageShiftUnits value
         *
         * @param  imageShiftUnits value
         * @return this builder for method chaining.
         */
        public Builder addImageShiftUnits(final CopyAttributes.ImageShiftUnits imageShiftUnits) {
            mImageShiftUnitsList.add(imageShiftUnits);
            return this;
        }

        /**
         * Sets ImageShiftXFront range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public CopyAttributesCapsCreator.Builder setImageShiftXFrontRange(final float lowerBound, final float highBound) {
            mImageShiftXFrontRange.mLowerBound = lowerBound;
            mImageShiftXFrontRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets ImageShiftYFront range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public CopyAttributesCapsCreator.Builder setImageShiftYFrontRange(final float lowerBound, final float highBound) {
            mImageShiftYFrontRange.mLowerBound = lowerBound;
            mImageShiftYFrontRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets ImageShiftXBack range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public CopyAttributesCapsCreator.Builder setImageShiftXBackRange(final float lowerBound, final float highBound) {
            mImageShiftXBackRange.mLowerBound = lowerBound;
            mImageShiftXBackRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Sets ImageShiftYBack range
         *
         * @param lowerBound minimum value
         * @param highBound maximum value
         * @return this builder for method chaining.
         */
        public CopyAttributesCapsCreator.Builder setImageShiftYBackRange(final float lowerBound, final float highBound) {
            mImageShiftYBackRange.mLowerBound = lowerBound;
            mImageShiftYBackRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Adds bookletBordersEachPage value
         *
         * @param bookletBordersEachPage value
         * @return this builder for method chaining.
         */
        public Builder addBookletBordersEachPage(final CopyAttributes.BookletBordersEachPage bookletBordersEachPage) {
            mBookletBordersEachPageList.add(bookletBordersEachPage);
            return this;
        }

        /**
         * Adds bookletFinishing Option
         *
         * @param bookletFinishingOption value
         * @return this builder for method chaining.
         */
        public Builder addBookletFinishingOption(final CopyAttributes.BookletFinishingOption bookletFinishingOption) {
            mBookletFinishingOptionList.add(bookletFinishingOption);
            return this;
        }

        /**
         * Adds bookletFormat value
         *
         * @param bookletFormat value
         * @return this builder for method chaining.
         */
        public Builder addBookletFormat(final CopyAttributes.BookletFormat bookletFormat) {
            mBookletFormatList.add(bookletFormat);
            return this;
        }

        /**
         * Sets stapleOption
         *
         * @param stapleOption value
         * @return this builder for method chaining.
         */
        public Builder addStapleOption(final CopyAttributes.StapleOption stapleOption) {
            mStapleOption.add(stapleOption);
            return this;
        }

        /**
         * Sets punchMode
         *
         * @param punchMode value
         * @return this builder for method chaining.
         */
        public Builder addPunchMode(final CopyAttributes.PunchMode punchMode) {
            mPunchMode.add(punchMode);
            return this;
        }

        /**
         * Sets foldMode
         *
         * @param foldMode value
         * @return this builder for method chaining.
         */
        public Builder addFoldMode(final CopyAttributes.FoldMode foldMode) {
            mFoldMode.add(foldMode);
            return this;
        }

        /**
         * Sets stampPosition
         *
         * @param stampPosition value
         * @return this builder for method chaining.
         */
        public Builder addStampOption(final CopyAttributes.StampPosition stampPosition, final StampOption stampOption) {
            mStampOptionMap.put(stampPosition, stampOption);
            return this;
        }
        /*** Sets Transparency range
         *
                 * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
                */
        public Builder setWatermarkTransparencyRange(final int lowerBound, final int highBound) {
            mWatermarkTransparencyRange.mLowerBound = lowerBound;
            mWatermarkTransparencyRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Adds a Watermark OnlyFirstPageList.
         *
         * @param watermarkOnlyFirstPage The value to determine to show watermark only on first page
         * @return this builder for method chaining.
         */
        public Builder addWatermarkOnlyFirstPageList(final CopyAttributes.WatermarkOnlyFirstPage watermarkOnlyFirstPage) {
            mWatermarkOnlyFirstPageList.add(watermarkOnlyFirstPage);
            return this;
        }

        /**
         * Sets darkness range
         *
         * @param lowerBound minimum value
         * @param highBound  maximum value
         * @return this builder for method chaining.
         */
        public Builder setWatermarkDarknessRange(final int lowerBound, final int highBound) {
            mWatermarkDarknessRange.mLowerBound = lowerBound;
            mWatermarkDarknessRange.mUpperBound = highBound;
            return this;
        }

        /**
         * Adds a watermark rotation.
         *
         * @param watermarkRotate45 The watermark rotation value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkRotation45(final CopyAttributes.WatermarkRotate45 watermarkRotate45) {
            mWatermarkRotate45List.add(watermarkRotate45);
            return this;
        }

        /**
         * Adds a watermark Type.
         *
         * @param watermarkType The watermark type value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkType(final CopyAttributes.WatermarkType watermarkType) {
            mWatermarkTypeList.add(watermarkType);
            return this;
        }

        /**
         * Adds a watermark messageType.
         *
         * @param watermarkMessageType The watermark message type value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkMessageType(final CopyAttributes.WatermarkMessageType watermarkMessageType) {
            mWatermarkMessageTypeList.add(watermarkMessageType);
            return this;
        }

        /**
         * Adds a watermark background pattern.
         *
         * @param watermarkBackgroundPattern The watermark background pattern value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkBackgroundPattern(final CopyAttributes.WatermarkBackgroundPattern watermarkBackgroundPattern) {
            mWatermarkBackgroundPatternList.add(watermarkBackgroundPattern);
            return this;
        }

        /**
         * Adds a watermark background color.
         *
         * @param watermarkBackgroundColor The watermark background color value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkBackgroundColor(final String watermarkBackgroundColor) {
            mWatermarkBackgroundColorList.add(watermarkBackgroundColor);
            return this;
        }

        /**
         * Adds a watermark font.
         *
         * @param watermarkFont The watermark font value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkFont(final String watermarkFont) {
            mWatermarkFontList.add(watermarkFont);
            return this;
        }

        /**
         * Adds a watermark text color.
         *
         * @param watermarkTextColor The watermark text color value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkTextColor(final String watermarkTextColor) {
            mWatermarkTextColorList.add(watermarkTextColor);
            return this;
        }

        /**
         * Adds a watermark text size.
         *
         * @param watermarkTextSize The watermark text size value
         * @return this builder for method chaining.
         */
        public Builder addWatermarkTextSize(final int watermarkTextSize) {
            mWatermarkTextSizeList.add(watermarkTextSize);
            return this;
        }

        /**
         * Combines all of the capabilities in this into a ScanAttributesCapsCreator object.
         *
         * @return a ScanAttributesCapsCreator object containing all of the attributes.
         */
        public CopyAttributesCapsCreator build() {
            return new CopyAttributesCapsCreator(this);
        }
    }
}
