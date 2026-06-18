// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.api.massstorage;

import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
public class CustomerDataFile implements Parcelable {
    MassStorageInfo mStorageInfo;
    String mPath;
    String mPackageName;

    protected CustomerDataFile(Parcel in) {
        mPath = in.readString();
        mStorageInfo = (MassStorageInfo) in.readParcelable(this.getClass().getClassLoader());
        mPackageName = in.readString();
    }

    public CustomerDataFile(com.hp.workpath.api.massstorage.CustomerDataFile customerDataFile) {
        this.mPath = customerDataFile.getPath();
        this.mStorageInfo = MassStorageInfo.CREATOR_OBJ.createFromObject(customerDataFile.getStorageInfo());
        this.mPackageName = customerDataFile.getPackageName();
    }

    public static final Creator<CustomerDataFile> CREATOR = new Creator<CustomerDataFile>() {
        @Override
        public CustomerDataFile createFromParcel(Parcel in) {
            return new CustomerDataFile(in);
        }

        @Override
        public CustomerDataFile[] newArray(int size) {
            return new CustomerDataFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
        dest.writeParcelable(mStorageInfo, flags);
        dest.writeString(mPackageName);
    }
}
