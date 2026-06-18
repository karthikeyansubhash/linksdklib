// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.jetadvantage.link.common.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hp.jetadvantage.link.svcmgt.services.ISvcmgtServicesToLibraryInterface;
import com.hp.workpath.common.utils.SLog;

public class SvcmgtServicesToLibraryService extends Service {
    private static String TAG = SvcmgtServicesToLibraryService.class.getCanonicalName();

    public static Callback callback;

    public interface Callback {
        String get(String request, String requestBody);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        SLog.d(TAG, "onBind called");
        return binder;
    }

    private final ISvcmgtServicesToLibraryInterface.Stub binder = new ISvcmgtServicesToLibraryInterface.Stub() {
        @Override
        public String callService(String request, String requestHeaders, String requestBody) {
            SLog.d(TAG, "Requested to get data");
            if (callback != null) {
                try {
                    return callback.get(request, requestBody);
                } catch (Throwable throwable) {
                    SLog.e(TAG, "Failed to get data" + throwable.getMessage());
                }
            }
            return null;
        }
    };

} 