// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.massstorage;

import android.content.Context;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.SLog;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

import static android.os.ParcelFileDescriptor.*;

/**
 * <p>The utility provides to read or write data from storage using CustomerDataFile object.</p>
 *
 * @since API 5
 */
@DeviceApi
public class CustomerDataFileUtils {
    private static final String TAG = "[MassSVC/CDFUtil]";

    private static String model = null;
    private static final String TNT = "nanotesla";
    private static final String TRON = "tron";

    private static String getModel() {
        if (model != null) {
            return model;
        }
        Process process = null;
        String cmd[] = {"/system/bin/sh", "-c", "getprop | grep ro.arch"};
        BufferedReader reader = null;

        try {
            process = Runtime.getRuntime().exec(cmd);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String readline = reader.readLine();
            Log.e(TAG, "model = " + readline);
            if (readline != null) {
                if (readline.contains(TRON)) {
                    model = TRON;
                } else if (readline.contains(TNT)) {
                    model = TNT;
                }
            } else {
                model = TNT;
            }

            reader.close();
            reader = null;
        } catch (Exception e) {
            Log.e(TAG, "failed while reading a model name.");
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException ioe) {}
            }
        }
        return model;
    }


    /**
     * <p>Returns an InputStream representing the data and throws the appropriate exception if it can not do so.</p>
     *
     * @param context The Context object for your activity or application
     * @param file    CustomerDataFile for reading the data
     * @return InputStream An InputStream
     * @exception IOException
     * @since API 5
     */
    @DeviceApi
    public static InputStream openInputStream(Context context, CustomerDataFile file) throws IOException {
        WeakReference<Context> mContext = new WeakReference<>(context);
        AutoCloseInputStream fis = null;
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(file);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {

            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.GET_FILE_URI, null, extras);

            if (null == bundle) {
                throw new Exception("Return empty");
            } else {
                Result.parse(bundle, result);
                if (result.getCode() == Result.RESULT_OK) {
                    String fileUri = bundle.getString(MassStoragelet.Keys.KEY_FILE_URI);
                    ParcelFileDescriptor fd = mContext.get().getContentResolver().openFileDescriptor(Uri.parse(fileUri), "r");
                    fis = new AutoCloseInputStream(fd);
                }
            }
        } catch (Throwable t) {
            SLog.e(TAG, "Error openInputStream: " + t.getMessage());
            throw new IOException(t.getMessage());
        }

        return fis;
    }

    /**
     * <p>Returns an OutputStream where the data can be written and throws the appropriate exception if it can not do so.</p>
     *
     * @param context The Context object for your activity or application
     * @param file    CustomerDataFile for writing the data
     * @return OutputStream An OutputStream
     * @exception IOException
     * @since API 5
     */
    @DeviceApi
    public static OutputStream openOutputStream(Context context, CustomerDataFile file) throws IOException {
        WeakReference<Context> mContext = new WeakReference<>(context);
        AutoCloseOutputStream fos = null;
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = mContext.get().getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
        com.hp.jetadvantage.link.api.massstorage.CustomerDataFile customerDataFile = new com.hp.jetadvantage.link.api.massstorage.CustomerDataFile(file);
        extras.putParcelable(MassStoragelet.Keys.KEY_FILE_TYPE, customerDataFile);
        Result result = new Result();

        try {
            final Bundle bundle =
                    mContext.get().getContentResolver().call(MassStoragelet.CONTENT_URI,
                            TRON.equals(getModel())? MassStoragelet.Method.GET_FILE_SOCKET : MassStoragelet.Method.GET_FILE_URI, null, extras);

            if (null == bundle) {
                throw new Exception("Return empty");
            } else {
                Result.parse(bundle, result);
                if (result.getCode() == Result.RESULT_OK) {
                    String fileUri;
                    if (bundle.containsKey(MassStoragelet.Keys.KEY_FILE_URI_PATH)) {
                        fileUri = bundle.getString(MassStoragelet.Keys.KEY_FILE_URI_PATH);
                        SLog.d(TAG, "getting data from file uri path");
                    } else {
                        fileUri = bundle.getString(MassStoragelet.Keys.KEY_FILE_URI);
                        SLog.d(TAG, "getting data from uri path");
                    }
                    long availableSize = bundle.getLong(MassStoragelet.Keys.KEY_AVAILABLE_SIZE);
                    if (bundle.containsKey(MassStoragelet.Keys.KEY_FILE_ADDR)) {
                        String addr = bundle.getString(MassStoragelet.Keys.KEY_FILE_ADDR);
                        int port = 0;
                        if (bundle.containsKey(MassStoragelet.Keys.KEY_FILE_PORT)) {
                            port = bundle.getInt(MassStoragelet.Keys.KEY_FILE_PORT);
                        }
                        Socket socket = new Socket(addr, port);
                        SLog.d(TAG, "getting data from a socket");
                        return new LimitedOutputStream(socket.getOutputStream(), availableSize);
                    }
                    ParcelFileDescriptor fd = mContext.get().getContentResolver().openFileDescriptor(Uri.parse(fileUri), "rw");
                    fos = new AutoCloseOutputStream(mContext.get(), fileUri, fd);
                    return new LimitedOutputStream(fos, availableSize);
                }
            }
        } catch (Throwable t) {
            SLog.e(TAG, "Error openOutputStream: " + t.getMessage());
            throw new IOException(t.getMessage());
        }

        return fos;
    }

    private static class AutoCloseOutputStream extends FileOutputStream {
        private final ParcelFileDescriptor mPfd;
        private final Context mContext;
        private String mFileUri;

        public AutoCloseOutputStream(Context context, String fileUri, ParcelFileDescriptor pfd) {
            super(pfd.getFileDescriptor());
            mContext = context;
            mFileUri = fileUri;
            mPfd = pfd;
        }

        @Override
        public void close() throws IOException {
            try {
                mPfd.close();
                if (mFileUri != null) {
                    Bundle extras = new Bundle();
                    String packageName = mContext.getPackageName();
                    extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
                    extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);
                    extras.putString(MassStoragelet.Keys.KEY_STORAGE_TYPE, mFileUri);
                    try {
                        mContext.getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.CLOSE_FILE_URI, null, extras);
                    } catch (Throwable throwable) {
                        SLog.e(TAG, "Ignored for compatibility");
                    }
                } else {
                }
            } finally {
                super.close();
            }
        }
    }

    private static final class LimitedOutputStream extends FilterOutputStream {
        private final long maxBytes;
        private long bytesWritten;

        public LimitedOutputStream(OutputStream out, long maxBytes) {
            super(out);
            this.maxBytes = maxBytes;
        }

        @Override
        public void write(int b) throws IOException {
            write(new byte[]{(byte) b}, 0, 1);
        }

        @Override
        public void write(@NonNull byte[] b) throws IOException {
            write(b, 0, b.length);
        }

        @Override
        public void write(@NonNull byte[] b, int off, int len) throws IOException {
            this.bytesWritten += len;
            if (this.bytesWritten > this.maxBytes) {
                throw new IOException("File size exceeded: " + this.maxBytes);
            }
            out.write(b, off, len);
        }
    }
}
