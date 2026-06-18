// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.job;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.support.annotation.Keep;
import android.support.v4.util.Preconditions;

import com.hp.jetadvantage.link.api.printer.PrintAttributes;
import com.hp.workpath.common.Sdk;

/**
 * <p>Provides details of PRINT job.</p>
 *
 * @hide
 * @since API 1
 */
@Deprecated
public class PrintJobData implements JobInfo.JobData {
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

    @Keep
    @Deprecated
    public int getA() {
        return mSheetsPrinted;
    }

    @Keep
    @Deprecated
    public int getB() {
        return mImpressionsPrinted;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public int getC() {
        return mCopies;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public PrintAttributes.Duplex getD() {
        return mDuplex;
    }

    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public PrintJobState getE() {
        return mJobState;
    }

    /**
     * Returns source of the file for print
     *
     * @return PrintAttributes.Source source of the file
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @Keep
    @Deprecated
    public PrintAttributes.Source getF() {
        return mSource;
    }

    /**
     * Internal parcelable version
     *
     * @return internal version
     * @hide for internal use
     */
    @SuppressWarnings({"unused"})
    @Deprecated
    public int getVersion() {
        return mVersion;
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
