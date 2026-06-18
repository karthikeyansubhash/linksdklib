// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.massstorage;

import android.os.Parcel;
import android.os.Parcelable;

import com.hp.workpath.api.Convertor;
import com.hp.workpath.common.Sdk;

/**
 * Provides attached mass storage details.
 *
 * @hide
 * @since API 2
 */
@Deprecated
public class MassStorageInfo implements Parcelable {
    /**
     * Supported type of mass storage
     *
     * @return enum supported type
     * @since API 2
     */
    public enum StorageType {
        USB,
        HDD
    }

    /**
     * Supported Protocol for accessing to mass storage
     *
     * @return enum supported protocol
     * @since API 2
     */
    public enum Protocol {
        LOCAL
    }
    /**
     * Version of info. Important to maintain to avoid Parcel breakage
     */
    private int mVersion;
    private StorageType mType;
    private String mName;
    private String mVolumeName;
    private long mTotalSpace;
    private long mFreeSpace;
    private boolean mIsMounted;
    private String mExternalFileDirectory;
    private Protocol mProtocol;

    private MassStorageInfo(Parcel in) {
        //mVersion = in.readInt();
        //Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);
        mType = (StorageType) in.readSerializable();
        mName = in.readString();
        if (Sdk.VERSION.LEVEL >= Sdk.VERSION_LEVEL.THREE) {
            if (mName != null && mName.indexOf("(") > 0) {
                mVolumeName = mName.substring(mName.indexOf("(")+1, mName.length()-1);
                mName = mName.substring(0, mName.indexOf("("));
            }
        }
        mTotalSpace = in.readLong();
        mFreeSpace = in.readLong();
        mIsMounted = in.readByte() != 0;
        mExternalFileDirectory = in.readString();
        mProtocol = (Protocol) in.readSerializable();
    }
    /**
     * @hide
     */
    private MassStorageInfo(Object object) {
        if (object instanceof com.hp.workpath.api.massstorage.MassStorageInfo) {
            //mVersion = in.readInt();
            //Preconditions.checkArgument(mVersion >= Sdk.VERSION_LEVEL.ONE);
            mType = MassStorageInfo.StorageType.valueOf(((com.hp.workpath.api.massstorage.MassStorageInfo) object).getType().name());
            mName = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).getName();
            if (Sdk.VERSION.LEVEL >= Sdk.VERSION_LEVEL.THREE) {
                if (mName != null && mName.indexOf("(") > 0) {
                    mVolumeName = mName.substring(mName.indexOf("(") + 1, mName.length() - 1);
                    mName = mName.substring(0, mName.indexOf("("));
                } else {
                    mVolumeName = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).getVolumeName(); //TODO
                }
            }
            mTotalSpace = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).getTotalSpace();
            mFreeSpace = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).getFreeSpace();
            mIsMounted = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).isMounted();
            mExternalFileDirectory = ((com.hp.workpath.api.massstorage.MassStorageInfo) object).getExternalFileDirectory();
            mProtocol = Protocol.valueOf(((com.hp.workpath.api.massstorage.MassStorageInfo) object).getProtocol().name());
        }
    }
    /**
     * @hide parcelable implementation
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeInt(Sdk.VERSION.LEVEL);
        dest.writeSerializable(mType);
        dest.writeString(mName);
        dest.writeLong(mTotalSpace);
        dest.writeLong(mFreeSpace);
        dest.writeByte((byte) (mIsMounted ? 1 : 0));
        dest.writeString(mExternalFileDirectory);
        dest.writeSerializable(mProtocol);
        //API LEVEL FIVE
        //dest.writeString(mVolumeName);
    }

    /**
     * @hide parcelable implementation
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @hide parcelable implementation
     */
    public static final Creator<MassStorageInfo> CREATOR = new Creator<MassStorageInfo>() {
        @Override
        public MassStorageInfo createFromParcel(Parcel in) {
            return new MassStorageInfo(in);
        }

        @Override
        public MassStorageInfo[] newArray(int size) {
            return new MassStorageInfo[size];
        }
    };
    /**
     * @hide trivial
     */
    public static final Convertor.ConvertorCreator<MassStorageInfo> CREATOR_OBJ = new Convertor.ConvertorCreator<MassStorageInfo>() {
        @Override
        public MassStorageInfo createFromObject(Object object) {
            return new MassStorageInfo(object);
        }
    };
    @Deprecated
    public StorageType getA() { return mType; }

    @Deprecated
    public long getB() {
        return mFreeSpace;
    }

    @Deprecated
    public boolean isC() { return mIsMounted; }

    @Deprecated
    public String getD() {
        return mExternalFileDirectory;
    }

    @Deprecated
    public String getE() {
        return mName;
    }

    @Deprecated
    public long getF() {
        return mTotalSpace;
    }

    @Deprecated
    public Protocol getG() {
        return mProtocol;
    }

    @Deprecated
    public String getH() {
        return (mVolumeName==null)?"":mVolumeName;
    }

    /**
     * @hide internal
     */
    @Override
    public String toString() {
        return "MassStorageInfo{" +
                "type=" + mType +
                ", name=" + mName +
                ", volumeName=" + mVolumeName +
                ", totalSpace=" + mTotalSpace +
                ", freeSpace=" + mFreeSpace +
                ", isMounted=" + mIsMounted +
                ", externalFileDirectory=" + mExternalFileDirectory +
                ", protocol=" + mProtocol +
                '}';
    }
}
