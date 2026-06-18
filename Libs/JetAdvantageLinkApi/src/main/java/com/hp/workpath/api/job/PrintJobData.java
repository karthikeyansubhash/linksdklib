// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Convertor;
import com.hp.workpath.api.printer.PrintAttributes;
import com.hp.workpath.common.Sdk;

/**
 * <p>Provides details of PRINT job.</p>
 *
 * @since API 1
 */
public class PrintJobData implements JobInfo.JobData, Convertor {
    @Keep
    private int mVersion;
    @Keep
    private int mSheetsPrinted;
    @Keep
    private int mImpressionsPrinted;
    @Keep
    private int mCopies;
    @Keep
    private PrintAttributes.Duplex mDuplex;
    @Keep
    private PrintJobState mJobState;
    @Keep
    private PrintAttributes.Source mSource;

    /**
     * @hide
     */
    public PrintJobData() {
        mVersion = Sdk.VERSION.LEVEL;
    }

    @SuppressLint("RestrictedApi")
    private PrintJobData(Parcel in) {
        mVersion = in.readInt();
        Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

        mSheetsPrinted = in.readInt();
        mImpressionsPrinted = in.readInt();
        mCopies = in.readInt();
        mDuplex = (PrintAttributes.Duplex) in.readSerializable();
        mJobState = in.readParcelable(PrintJobState.class.getClassLoader());
        mSource = (PrintAttributes.Source) in.readSerializable();
    }

    @SuppressLint("RestrictedApi")
    private PrintJobData(Object object) {
        if(object instanceof com.hp.jetadvantage.link.api.job.PrintJobData) {
            mVersion = ((com.hp.jetadvantage.link.api.job.PrintJobData) object).getVersion();
            Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);

            mSheetsPrinted = ((com.hp.jetadvantage.link.api.job.PrintJobData) object).getA();
            mImpressionsPrinted = ((com.hp.jetadvantage.link.api.job.PrintJobData) object).getB();
            mCopies = ((com.hp.jetadvantage.link.api.job.PrintJobData) object).getC();
            mDuplex = PrintAttributes.Duplex.valueOf(((com.hp.jetadvantage.link.api.job.PrintJobData) object).getD().name());

            mJobState = PrintJobState.CREATOR_OBJ.createFromObject(((com.hp.jetadvantage.link.api.job.PrintJobData) object).getE());
            mSource = PrintAttributes.Source.valueOf(((com.hp.jetadvantage.link.api.job.PrintJobData) object).getF().name());
        }
    }

    /**
     * @hide
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVersion);
        dest.writeInt(mSheetsPrinted);
        dest.writeInt(mImpressionsPrinted);
        dest.writeInt(mCopies);
        dest.writeSerializable(mDuplex);
        dest.writeParcelable(mJobState, 0);
        if (mVersion >= Sdk.VERSION_LEVEL.TWO ||
                (mSource != PrintAttributes.Source.USB && mSource != PrintAttributes.Source.STREAM)) {
            dest.writeSerializable(mSource);
        } else {
            dest.writeSerializable(null);
        }
    }

    /**
     * @hide
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide
     */
    public static final Creator<PrintJobData> CREATOR = new Creator<PrintJobData>() {
        @Override
        public PrintJobData createFromParcel(Parcel in) {
            return new PrintJobData(in);
        }

        @Override
        public PrintJobData[] newArray(int size) {
            return new PrintJobData[size];
        }
    };

    /**
     * @hide trivial
     */
    public static final Convertor.ConvertorCreator<PrintJobData> CREATOR_OBJ = new Convertor.ConvertorCreator<PrintJobData>() {
        @Override
        public PrintJobData createFromObject(Object object) {
            return new PrintJobData(object);
        }
    };

    /**
     * Returns total number of sheets (one or two sides)
     *
     * @return number of sheets
     * @since API 1
     */
    @Keep
    public int getSheetsPrinted() {
        return mSheetsPrinted;
    }

    /**
     * Sets the total number of sheets for the job
     *
     * @param sheetsPrinted number of sheets
     * @hide for internal use.
     * @since API 1
     */
    public void setSheetsPrinted(int sheetsPrinted) {
        this.mSheetsPrinted = sheetsPrinted;
    }

    /**
     * Returns total number of impressions (one side)
     *
     * @return number of impressions
     * @since API 1
     */
    @Keep
    public int getImpressionsPrinted() {
        return mImpressionsPrinted;
    }

    /**
     * Sets the total number of impressions for the job
     *
     * @param impressionsPrinted number of impressions
     * @hide for internal use.
     * @since API 1
     */
    public void setImpressionsPrinted(int impressionsPrinted) {
        this.mImpressionsPrinted = impressionsPrinted;
    }

    /**
     * Returns Number of copies of the job
     *
     * @return number of copies
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public int getCopies() {
        return mCopies;
    }

    /**
     * Set the number of copies
     *
     * @param copies number of copies
     * @hide for internal use
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public void setCopies(int copies) {
        this.mCopies = copies;
    }

    /**
     * Returns duplex mode of print job
     *
     * @return Duplex duplex mode
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public PrintAttributes.Duplex getDuplex() {
        return mDuplex;
    }

    /**
     * Set the duplex mode for the print
     *
     * @param duplex mode
     * @hide for internal use
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public void setDuplex(PrintAttributes.Duplex duplex) {
        this.mDuplex = duplex;
    }

    /**
     * Returns the state of print job. If job is printing, "PROCESSING" returns.
     *
     * @return PrintJobState the state of print job
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public PrintJobState getJobState() {
        return mJobState;
    }

    /**
     * Set the state of the print job
     *
     * @param state of print job
     * @hide for internal use
     * @since API 1
     */
    public void setJobState(PrintJobState state) {
        mJobState = state;
    }

    /**
     * Returns source of the file for print
     *
     * @return PrintAttributes.Source source of the file
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    public PrintAttributes.Source getSource() {
        return mSource;
    }

    /**
     * Set the source of the file for the print
     *
     * @param source of the file
     * @hide for internal use
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public void setSource(PrintAttributes.Source source) {
        this.mSource = source;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public int getVersion() {
        return mVersion;
    }

    /**
     * Internal parcelable version
     * @param version version for client
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    public void setVersion(int version) {
        mVersion = version;
    }

    /**
     * @hide This is hidden because it should be understood without documenting in the javadoc
     */
    @Override
    @Keep
    public String toString() {
        return getClass().getSimpleName() + "[" +
                "jobState=" + mJobState +
                ", sheetsPrinted=" + mSheetsPrinted +
                ", impressionsPrinted=" + mImpressionsPrinted +
                ", copies=" + mCopies +
                ", duplex=" + mDuplex +
                ", source=" + mSource +
                ']';
    }
}
