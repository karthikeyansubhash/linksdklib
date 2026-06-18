// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.massstorage;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.Result;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.SLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides interfaces to access mass storage.
 *
 * @since API 2
 */
@SuppressWarnings("unused")
@DeviceApi
public final class MassStorageService {
    private static final String TAG = "[MassSVC]";

    private MassStorageService() {
    }

    /**
     * Returns list of supported mass storage
     *
     * @param context The Context in which the application is running.
     * @param result  (optional) Indicates any errors which occurred while operation
     * @return List list of {@link MassStorageInfo MassStorageInfo} which is retrieved from a device
     * @exception NullPointerException if context is null
     * @since API 2
     */
    @SuppressWarnings("unused")
    public static List<MassStorageInfo> getStorageList(@NonNull final Context context,
                                                       @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(MassStoragelet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }
        try {
            final Bundle bundle =
                    context.getContentResolver().call(MassStoragelet.CONTENT_URI, MassStoragelet.Method.GET_STORAGE_LIST, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                bundle.setClassLoader(MassStorageInfo.class.getClassLoader());
                Result.parse(bundle, result);

                if (result.getCode() == Result.RESULT_OK && bundle.containsKey(Result.KEY_RESULT)) {
                    ArrayList<com.hp.jetadvantage.link.api.massstorage.MassStorageInfo> tmpMassStorageInfos = bundle.getParcelableArrayList(Result.KEY_RESULT);
                    List<MassStorageInfo> massStorageInfos = new ArrayList<MassStorageInfo>();
                    for (com.hp.jetadvantage.link.api.massstorage.MassStorageInfo massStorageInfo : tmpMassStorageInfos) {
                        massStorageInfos.add(MassStorageInfo.CREATOR_OBJ.createFromObject(massStorageInfo));
                    }

                    return massStorageInfos;
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, operations will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true.
     * @exception NullPointerException Returns error if context is null.
     * @since API 2
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        Bundle extras = new Bundle();
        extras.putInt(MassStoragelet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(MassStoragelet.CONTENT_URI,
                        MassStoragelet.Method.IS_SUPPORTED,
                        null,
                        extras);
        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(MassStoragelet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(MassStoragelet.IS_SUPPORTED_EXTRA);
    }

    /**
     * <p>Provides a mechanism to monitor the state of the
     * {@link MassStorageService MassStorageService} events. If application extends AbstractMassStorageChangeObserver, it can receive
     * events when massstorage is attached or detached.</p>
     *
     * @since API 3
     */
    @DeviceApi
    public static abstract class AbstractMassStorageChangeObserver extends BroadcastReceiver {
        private final Handler mHandler;

        /**
         * <p>Instantiates AbstractMassStorageChangeObserver with the handler passed as argument</p>
         *
         * @param handler The handler to run callbacks on, or null if none.
         * @since API 3
         */
        public AbstractMassStorageChangeObserver(final Handler handler) {
            super();
            mHandler = handler;
        }

        /**
         * <p>Requests to register the observer to monitor events of the change of massstorage status.</p>
         *
         * @param context The Context in which the application is running. If it's null, event will not be triggered.
         * @exception NullPointerException If context is null.
         * @since API 3
         */
        @SuppressWarnings({"unused"})
        @SuppressLint("RestrictedApi")
        public void register(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(TAG, "Registering massstorage changed event");

            final IntentFilter filter = new IntentFilter(MassStoragelet.MASSSTORAGE_CHANGE_ACTION);
            context.registerReceiver(this, filter);
        }

        /**
         * <p>Requests to unregister the observer to stop monitoring events of the change of massstorage status.</p>
         *
         * @param context The Context in which the application is running.
         * @exception NullPointerException     If context is null.
         * @exception IllegalArgumentException if this receiver hasn't been registered.
         * @since API 3
         */
        @SuppressWarnings({"unused"})
        @SuppressLint("RestrictedApi")
        public void unregister(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(TAG, "Un-registering massstorage changed event");
            context.unregisterReceiver(this);
        }

        /**
         * @hide final
         */
        @Override
        public final void onReceive(final Context context, final Intent intent) {
            SLog.d(TAG, "Received intent for " + intent.getAction());
            final String action = intent.getAction();
            if (!MassStoragelet.MASSSTORAGE_CHANGE_ACTION.equals(action)) {
                SLog.e(TAG, "Received invalid intent");
                return;
            }
            if (mHandler == null) {
                onRecv();
            } else {
                mHandler.post(new MassStorageStateRunnable());
            }
        }

        private void onRecv() {
            SLog.d(TAG, "Call onChange()");
            onChange();
        }

        private final class MassStorageStateRunnable implements Runnable {
            MassStorageStateRunnable() {
            }

            @Override
            public void run() {
                AbstractMassStorageChangeObserver.this.onRecv();
            }
        }

        /**
         * <p>Called to notify the client that massstorage state is changed.</p>
         *
         * @since API 3
         */
        public abstract void onChange();
    }
}
