// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.scanner;

import android.net.Uri;
import android.support.annotation.Keep;

/**
 * Reads attributes from requested scan job
 *
 * @since API 1
 */
@SuppressWarnings({"unused"})
public class ScanAttributesReader {
    @Keep
    private final ScanAttributes mAttrs;

    /**
     * Constructor to create new instance of ScanAttributesReader
     *
     * @param scanAttrs The scan attributes used to construct this.
     * <p>
     * <ul>
     * <li>Ensures that an object reference passed as a parameter to the calling method is not null.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributesReader(final ScanAttributes scanAttrs) {
        mAttrs = scanAttrs;
    }

    /**
     * The version in which the attributes were created.
     *
     * @return The version in which the attributes were created.
     * @hide for internal use
     * @since API 1
     */
    public int getVersion() {
        return mAttrs.mVersion;
    }

    /**
     * Gets the color mode of the scan job.
     *
     * @return color mode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ColorMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ColorMode getColorMode() {
        return mAttrs.mColorMode;
    }

    /**
     * Gets the duplex mode of the scan job.
     *
     * @return duplex mode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.Duplex}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.Duplex getPlex() {
        return mAttrs.mPlex;
    }

    /**
     * Gets the destination of the scan job.
     *
     * @return destination for scanned files
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.Destination}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.Destination getDestination() {
        return mAttrs.mDestination;
    }

    /**
     * Gets the original orientation of the scan job.
     *
     * @return original orientation
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.Orientation}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.Orientation getOrientation() {
        return mAttrs.mOrientation;
    }

    /**
     * Gets the size of the scanned region.
     *
     * @return scan size type.
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ScanSize}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ScanSize getScanSize() {
        return mAttrs.mScanSize;
    }

    /**
     * Gets the custom length of the scanned region
     *
     * @return custom length in inches.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [2.9, 34.0].</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public Float getCustomLength() {
        return mAttrs.mCustomLength;
    }

    /**
     * Gets the custom width of the scanned region
     *
     * @return custom width in inches.
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [2.16, 8.5].</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public Float getCustomWidth() {
        return mAttrs.mCustomWidth;
    }

    /**
     * Gets the output format of the scanned image(s).
     *
     * @return the output format of the scanned image(s), as a string.
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.DocumentFormat}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.DocumentFormat getDocumentFormat() {
        return mAttrs.mDocFormat;
    }

    /**
     * Gets the resolution to be used for the scanned image(s).
     *
     * @return the resolution to be used for the scanned image(s).
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.Resolution}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.Resolution getResolution() {
        return mAttrs.mResolutionType;
    }

    /**
     * Gets the preview mode of system dialog
     *
     * @return selected scan preview option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ScanPreview}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ScanPreview getScanPreview() {
        return mAttrs.mScanPreview;
    }

    /**
     * Gets folder name to be stored scanned image(s).
     *
     * @return folder path
     * <p>
     * <ul>
     * <li>Return can be null if the scan preview is null</li>
     * <li>Return can be null if the scan preview is empty</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public String getFolderName() {
        return mAttrs.mFolderName;
    }

    /**
     * Gets the option of background cleanup
     *
     * @return selected background cleanup option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.BackgroundCleanup}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.BackgroundCleanup getBackgroundCleanup() {
        return mAttrs.mBackgroundCleanup;
    }

    /**
     * Gets contrast adjustment option of the scan job
     *
     * @return selected contrast adjustment option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ContrastAdjustment}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ContrastAdjustment getContrastAdjustment() {
        return mAttrs.mContrastAdjustment;
    }

    /**
     * Gets darkness adjustment option of the scan job
     *
     * @return selected darkness adjustment option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.DarknessAdjustment}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.DarknessAdjustment getDarknessAdjustment() {
        return mAttrs.mDarknessAdjustment;
    }

    /**
     * Gets the value which blank image is removed or not
     *
     * @return selected blank image removal mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.BlankImageRemovalMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.BlankImageRemovalMode getBlankImageRemovalMode() {
        return mAttrs.mBlankImageRemovalMode;
    }

    /**
     * Gets color dropout option of the scan job
     *
     * @return selected color dropout mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ColorDropoutMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ColorDropoutMode getColorDropoutMode() {
        return mAttrs.mColorDropoutMode;
    }

    /**
     * Gets the crop mode of the scan job
     *
     * @return selected crop mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.CropMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.CropMode getCropMode() {
        return mAttrs.mCropMode;
    }

    /**
     * Gets the progress dialog option
     *
     * @return selected progress dialog mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.ProgressDialogMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.ProgressDialogMode getProgressDialogMode() {
        return mAttrs.mProgressDialogMode;
    }

    /**
     * Gets the output quality option of the scan job
     *
     * @return selected output quality option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.OutputQuality}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.OutputQuality getOutputQuality() {
        return mAttrs.mOutputQuality;
    }

    /**
     * Gets the transmission option of the scan job
     *
     * @return selected transmission mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.TransmissionMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.TransmissionMode getTransmissionMode() {
        return mAttrs.mTransmissionMode;
    }

    /**
     * Gets the assembly mode of the scan job
     *
     * @return selected job assembly mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.JobAssemblyMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.JobAssemblyMode getJobAssemblyMode() {
        return mAttrs.mJobAssemblyMode;
    }

    /**
     * Gets the sharpness adjustment option of the scan job
     *
     * @return selected sharpness adjustment option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.SharpnessAdjustment}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.SharpnessAdjustment getSharpnessAdjustment() {
        return mAttrs.mSharpnessAdjustment;
    }

    /**
     * Gets the media weight adjustment option of the scan job
     *
     * @return selected media weight adjustment option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.MediaWeightAdjustment}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.MediaWeightAdjustment getMediaWeightAdjustment() {
        return mAttrs.mMediaWeightAdjustment;
    }

    /**
     * Gets the text/photo optimization option of the scan job
     *
     * @return selected text/photo optimization option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.TextPhotoOptimization}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.TextPhotoOptimization getTextPhotoOptimization() {
        return mAttrs.mTextPhotoOptimization;
    }

    /**
     * Gets the media source of the scan job
     *
     * @return selected media source option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.MediaSource}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.MediaSource getMediaSource() {
        return mAttrs.mMediaSource;
    }

    /**
     * Gets the misfeed detection mode of the scan job
     *
     * @return selected misfeed detection mode option
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.MisfeedDetectionMode}.</li>
     * </ul>
     * </p>
     * @since API 1
     */
    @Keep
    public ScanAttributes.MisfeedDetectionMode getMisfeedDetectionMode() {
        return mAttrs.mMisfeedDetectionMode;
    }

    /**
     * SplitAttachmentByPage
     * @return SplitAttachmentByPage
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.SplitAttachmentByPage}.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public ScanAttributes.SplitAttachmentByPage getSplitAttachmentByPage() { return mAttrs.mSplitAttachmentByPage; }

    /**
     * MaxPagesPerAttachment
     * @return MaxPagesPerAttachment
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [1.0, 9999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Integer getMaxPagesPerAttachment() { return mAttrs.mMaxPagesPerAttachment; }

    /**
     * EraseMarginUnit
     * @return EraseMarginUnit
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.EraseMarginUnit}.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public ScanAttributes.EraseMarginUnit getEraseMarginUnit() { return mAttrs.mEraseMarginUnit; }

    /**
     * EraseBackBottomMargin
     * @return EraseBackBottomMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseBackBottomMargin() { return mAttrs.mEraseBackBottom; }

    /**
     * EraseBackLeftMargin
     * @return EraseBackLeftMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseBackLeftMargin() { return mAttrs.mEraseBackLeft; }

    /**
     * EraseBackRightMargin
     * @return EraseBackRightMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseBackRightMargin() { return mAttrs.mEraseBackRight; }

    /**
     * EraseBackTopMargin
     * @return EraseBackTopMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseBackTopMargin() { return mAttrs.mEraseBackTop; }

    /**
     * EraseFrontBottomMargin
     * @return EraseFrontBottomMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseFrontBottomMargin() { return mAttrs.mEraseFrontBottom; }

    /**
     * EraseFrontLeftMargin
     * @return EraseFrontLeftMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseFrontLeftMargin() { return mAttrs.mEraseFrontLeft; }

    /**
     * EraseFrontRightMargin
     * @return EraseFrontRightMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseFrontRightMargin() { return mAttrs.mEraseFrontRight; }

    /**
     * EraseFrontTopMargin
     * @return EraseFrontTopMargin
     * <p>
     * <ul>
     * <li>Return can't be empty and should be either any of the values with in the range [0.0, 999.0].</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public Float getEraseFrontTopMargin() { return mAttrs.mEraseFrontTop; }

    /**
     * CaptureMode
     * @return CaptureMode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.CaptureMode}.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public ScanAttributes.CaptureMode getCaptureMode() { return mAttrs.mCaptureMode; }

    /**
     * AutomaticToneMode
     * @return AutomaticToneMode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.AutomaticToneMode}.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public ScanAttributes.AutomaticToneMode getAutomaticToneMode() { return mAttrs.mAutomaticToneMode; }

    /**
     * AutomaticStraightenMode
     * @return AutomaticStraightenMode
     * <p>
     * <ul>
     * <li>Return can't be empty or null, should be either any of the values from {@link  ScanAttributes.AutomaticStraightenMode}.</li>
     * </ul>
     * </p>
     * @since API 5
     */
    @Keep
    public ScanAttributes.AutomaticStraightenMode getAutomaticStraightenMode() { return mAttrs.mAutomaticStraightenMode; }

    /**
     * @return get file name about scanned file
     * @hide for internal use
     * @since API 1
     */
    public String getFileName() {
        return mAttrs.mFileName;
    }

    /**
     * @return selected file options attributes
     * @hide for internal use
     * @since API 1
     */
    public FileOptionsAttributes getFileOptionsAttributes() {
        return mAttrs.mFileOptionsAttributes;
    }

    /**
     * @return get destination Uri for scanned images
     * @hide for internal use
     * @since API 1
     */
    public Uri getUri() {
        return mAttrs.mUri;
    }

    /**
     * Network credentials for authentication when accessing Uri
     *
     * @return uri authentication credentials
     * @hide for internal use
     * @since API 1
     */
    public NetworkCredentialsAttributes getNetworkCredentialsAttributes() {
        return mAttrs.mNetworkCredentialsAttributes;
    }

    /**
     * Connect timeout for accessing Uri
     *
     * @return timeout in seconds
     * @hide for internal use
     * @since API 1
     */
    public int getConnectTimeout() {
        return mAttrs.mConnectTimeout;
    }

    /**
     * Read timeout for accessing Uri
     *
     * @return timeout in seconds
     * @hide for internal use
     * @since API 1
     */
    public int getReadTimeout() {
        return mAttrs.mReadTimeout;
    }

    /**
     * Maximum retry count for accessing Uri
     *
     * @return maximum retry count
     * @hide for internal use
     * @since API 1
     */
    public int getMaxConsecutiveRetries() {
        return mAttrs.mMaxRetries;
    }

    /**
     * Interval between retries
     *
     * @return interval in seconds
     * @hide for internal use
     * @since API 1
     */
    public int getRetryInterval() {
        return mAttrs.mRetryInterval;
    }

    /**
     * Email attributes for email destination
     *
     * @return email attributes
     * @hide for internal use
     * @since API 1
     */
    public EmailAttributes getEmailAttributes() {
        return mAttrs.mEmailAttributes;
    }

    /**
     * SMTP attributes for SMTP server connection
     *
     * @return smtp attributes
     * @hide for internal use
     * @since API 1
     */
    public SmtpAttributes getSmtpAttributes() {
        return mAttrs.mSmtpAttributes;
    }

    /**
     * USB location
     *
     * @return usb location
     * @hide for internal use
     * @since API 2
     */
    public String getUsbLocation() {
        return mAttrs.mUsbLocation;
    }
}
