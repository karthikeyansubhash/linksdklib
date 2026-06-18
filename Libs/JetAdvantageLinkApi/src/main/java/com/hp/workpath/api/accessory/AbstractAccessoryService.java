// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.accessory;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hp.workpath.api.accessory.hid.Accessorylet;

import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.SLog;

import java.lang.ref.WeakReference;

/**
 * <p>Implementations of Abstract Accessory Service provide the method to handle accessories features on Workpath platform.</p>
 *
 * @since API 3
 */
@DeviceApi
public abstract class AbstractAccessoryService extends Service {
    private static final String TAG = "[AbstractACC]";
    /**
     * @hide for internal use
     */
    public static final class AccessoryMessage {
        public static final int START = 100;
    }

    private Messenger mMessenger;
    private final Object mLock = new Object();
    private boolean mOnCreateCalled = false;

    /**
     * @hide for internal use
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        SLog.d(TAG, "onBind");

        if (!mOnCreateCalled) {
            throw new RuntimeException("super.onCreate() not called");
        }
        if (TextUtils.equals(intent.getAction(), Accessorylet.PROVIDER_EVENT_ACTION)) {
            return mMessenger.getBinder();
        }

        throw new UnsupportedOperationException("Unknown action");
    }

    /**
     * @hide for internal use
     */
    @Override
    public boolean onUnbind(Intent intent) {
        SLog.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * @hide for internal use
     */
    @Override
    public void onCreate() {
        SLog.d(TAG, "onCreate");
        super.onCreate();
        mMessenger = new Messenger(new ServiceHandler(getMainLooper(), this));
        mOnCreateCalled = true;
    }

    /**
     * @hide for internal use
     */
    @Override
    public void onDestroy() {
        SLog.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private static class ServiceHandler extends Handler {
        private final WeakReference<AbstractAccessoryService> mRef;

        ServiceHandler(Looper looper, AbstractAccessoryService context) {
            super(looper);
            mRef = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            AbstractAccessoryService serviceBase = mRef.get();
            if (serviceBase == null) {
                //fail requests
                return;
            }

            Bundle data = msg.getData();
            switch (msg.what) {
                // START
                case AccessoryMessage.START:
                    synchronized (serviceBase.mLock) {
                        serviceBase.onStart();
                    }
                    break;

                default:
                    // return error
            }
        }
    }

    /**
     * <p>Called to notify the client that accessory service started successfully.</p>
     *
     * @since API 3
     */
    protected abstract void onStart();

}
