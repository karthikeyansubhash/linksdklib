// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.massstorage;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.Result;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.SLog;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * <p>CustomerDataFile is user interface to representation of files and directories from a printer storage such as USB and HDD.</p>
 *
 * @since API 5
 */
@DeviceApi
public class CustomerDataFile implements Parcelable {
    private static final String TAG = "[MassSVC/CDF]";

    WeakReference<Context> mContext;
    MassStorageInfo mStorageInfo;
    String mPath;
    String mPackageName;

    /**
     * <p>Creates a new CustomerDataFile instance from pathname</p>
     *
     * @param context     The Context object for your activity or application
     * @param storageInfo Storage information
     * @param pathname    A pathname string
     * @exception NullPointerException If context, storageInfo and pathname arguments are null.
     * @since API 5
     */
    @DeviceApi
    public CustomerDataFile(@NonNull final Context context, MassStorageInfo storageInfo, String pathname) {
        if (context == null || storageInfo == null || pathname == null) {
            throw new NullPointerException();
        }
        this.mContext = new WeakReference<>(context);
        this.mStorageInfo = storageInfo;
        this.mPackageName = context.getPackageName();
        File file = new File("/", pathname);
        try {
            this.mPath = file.getCanonicalPath();
        } catch (Throwable throwable) {
        }
    }

    /*public CustomerDataFile(@NonNull final Context context, CustomerDataFile customerDataFile, String pathname) {
        if (context == null || customerDataFile == null || pathname == null) {
            throw new NullPointerException();
        }
        this.mContext = new WeakReference<>(context);
        this.mStorageInfo = customerDataFile.getStorageInfo();
        this.mPackageName = context.getPackageName();
        File file = new File(customerDataFile.getPath(), pathname);
        try {
            this.mPath = file.getCanonicalPath();
        } catch (Exception e) {
        }
    }*/

    /**
     * @hide
     */
    protected CustomerDataFile(Parcel in) {
        mPath = in.readString();
        mStorageInfo = (MassStorageInfo) in.readParcelable(this.getClass().getClassLoader());
        mPackageName = in.readString();
    }

    /**
     * @hide
     */
    protected void setContext(@NonNull final Context context) {
        this.mContext = new WeakReference<>(context);
        this.mPackageName = context.getPackageName();
    }

    /**
     * @hide parcelable implementation
     */
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

    /**
     * <p>Returns the Storage information of the file or directory denoted by pathname.</p>
     *
     * @return storage info
     * @since API 5
     */
    @DeviceApi
    public MassStorageInfo getStorageInfo() {
        return mStorageInfo;
    }

    /**
     * <p>Returns the pathname string of the file or directory.</p>
     *
     * @return path
     * @since API 5
     */
    @DeviceApi
    public String getPath() {
        return mPath;
    }

    /**
     * <p>Returns the package path for accessing the storage.</p>
     *
     * @return package name
     * @since API 5
     */
    @DeviceApi
    public String getPackageName() {
        return mPackageName;
    }

    /**
     * <p>Atomically creates a new, empty file named by this pathname.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean createNewFile() throws IOException {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.CREATE_NEW_FILE, null, extras);

            if (null == bundle) {
                throw new IOException("Failed to create file. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (bundle.containsKey(Result.KEY_RESULT)) {
                    if (result.getCode() == Result.RESULT_OK) {
                        return bundle.getBoolean(Result.KEY_RESULT);
                    } else {
                        throw new IOException(result.getCause() == null ? "Failed to create file: unknown issue" : result.getCause());
                    }
                } else {
                    throw new IOException("Failed to create file (result error)");
                }
            }
        } catch (Throwable t) {
            throw new IOException("Failed to create file:" + t.getMessage());
        }
    }

    /**
     * <p>Creates the directory.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean mkdir() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.CREATE_NEW_DIRECTORY, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to create directory. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to create directory:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Deletes the file or directory.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean delete() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.DELETE_FILE, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to delete file/directory. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to delete file/directory:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns an array of CustomerDataFile.</p>
     *
     * @return array of CustomerDataFile
     * @since API 5
     */
    @DeviceApi
    public CustomerDataFile[] listFiles() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.LIST_FILES, null, extras);

            if (null == bundle) {
                SLog.e(TAG, "Failed to listfiles: return empty");
                throw new IOException("Failed to listfiles: return empty");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    ArrayList<CustomerDataFile> resultArrayList = new ArrayList<CustomerDataFile>();
                    ArrayList<String> tmplistFiles = bundle.getStringArrayList(Result.KEY_RESULT);
                    for (String fileName : tmplistFiles) {
                        resultArrayList.add(new CustomerDataFile(mContext.get(), mStorageInfo, fileName));
                    }
                    SLog.d(TAG, "Success to listfiles:" + resultArrayList.size());
                    return resultArrayList.toArray(new CustomerDataFile[resultArrayList.size()]);
                }
                SLog.e(TAG, "Failed to listfiles");
            }
        } catch (Throwable throwable) {
            SLog.e(TAG, "Failed to listfiles, error is " + throwable.getMessage());
        }

        return null;
    }

    /**
     * <p>Returns true if the file by pathname is a file.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean isFile() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.IS_FILE, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to check isFile. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check isFile:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns true if the file by pathname is a directory.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean isDirectory() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.IS_DIRECTORY, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to check isDirectory. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check isDirectory:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns true if path is home folder</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean isRoot() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            if (customerDataFile != null && this.getPath() != null && "/".equalsIgnoreCase(this.getPath())) return true;
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check isRoot:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns true if the file or the directory by pathname exists.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean exists() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.FILE_EXISTS, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to check exists. (Service is not ready)");
            } else {
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check exists:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns the time that the file was last modified.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public long lastModified() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.FILE_LAST_MODIFIED, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to get lastModified. (Service is not ready)");
            } else {
                bundle.setClassLoader(MassStorageInfo.class.getClassLoader());
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getLong(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check lastModified:" + t.getMessage());
        }

        return 0L;
    }

    /**
     * <p>Returns the length of the file.</p>
     *
     * @since API 5
     */
    @DeviceApi
    public long length() {
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.FILE_LENGTH, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to get length. (Service is not ready)");
            } else {
                bundle.setClassLoader(MassStorageInfo.class.getClassLoader());
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getLong(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to check length:" + t.getMessage());
        }

        return 0L;
    }

    /**
     * <p>Renames the file to the destination. Returns the result of rename</p>
     *
     * @since API 5
     */
    @DeviceApi
    public boolean renameTo(@NonNull CustomerDataFile dest) {
        if (dest == null) throw new NullPointerException();
        Workpath.getInstance().checkPreconditions(mContext.get());

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(this);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile destCustomerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(dest);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        extras.putParcelable(MassStoragelet.Keys.KEY_DEST_FILE_TYPE, destCustomerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.FILE_RENAME, null, extras);

            if (null == bundle) {
                throw new Exception("Failed to rename. (Service is not ready)");
            } else {
                bundle.setClassLoader(MassStorageInfo.class.getClassLoader());
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    return bundle.getBoolean(Result.KEY_RESULT);
                }
            }
        } catch (Throwable t) {
            SLog.w(TAG, "Failed to rename:" + t.getMessage());
        }

        return false;
    }

    /**
     * <p>Returns the name of the file or directory.</p>
     *
     * @return file/directory name
     * @since API 5
     */
    @DeviceApi
    public String getName() {
        File tmpFile = new File(mPath);
        return tmpFile.getName();
    }

    /**
     * <p>Returns the CustomerDataFile of this pathname's parent.</p>
     *
     * @return CustomerDataFile of pathname's parent
     * @since API 5
     */
    @DeviceApi
    public CustomerDataFile getParentFile() {
        File tmpFile = new File(mPath);
        if (tmpFile.getParentFile() != null) {
            return new CustomerDataFile(mContext.get(), mStorageInfo, tmpFile.getParentFile().getAbsolutePath());
        }
        return null;
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
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
        dest.writeParcelable(mStorageInfo, flags);
        dest.writeString(mPackageName);
    }
}
