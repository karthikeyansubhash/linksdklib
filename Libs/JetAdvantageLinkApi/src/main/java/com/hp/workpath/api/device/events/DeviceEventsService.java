// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.events;

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

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.copier.CopyAttributes;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.SLog;

import java.util.List;

/**
 * <p>DeviceEventsService provides interfaces to get detail of device event which occurs on a printer.</p>
 *
 * @since API 5
 */
@DeviceApi
public final class DeviceEventsService {
    private static final String TAG = DeviceEventslet.TAG;

    private DeviceEventsService() {
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, DeviceEventsService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return <p>boolean Returns result of supported.
     * <ul>
     * <li>If service is supported, method returns true.</li>
     * <li>If service is not supported, method returns false.</li>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     *
     * @since API 5
     */
    @SuppressWarnings({"unused"})
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        extras.putInt(DeviceEventslet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(DeviceEventslet.CONTENT_URI,
                        DeviceEventslet.Method.IS_SUPPORTED,
                        null,
                        extras);
        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(DeviceEventslet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(DeviceEventslet.IS_SUPPORTED_EXTRA);
    }

    /**
     * <p>Retrieve list of events which occurs on a printer.</p>
     *
     * @param context The Context in which the application is running.
     * @param result  (optional) Indicates any errors occurred while getting events.
     *
     * @return <p> {@link DeviceEvent} list.
     * <ul>
     * <li>Return value can be empty list, if no device events occurred.</li>
     * <li>Return {@link DeviceEvent} list, if device events occurred.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     *
     * @since API 5
     */
    @SuppressWarnings({"unused"})
    @DeviceApi
    public static List<DeviceEvent> getDeviceEvents(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            Bundle extras = new Bundle();
            String packageName = context.getPackageName();
            extras.putString(DeviceEventslet.Keys.PACKAGE_NAME, packageName);
            extras.putInt(DeviceEventslet.Keys.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

            final Bundle returnBundle = context.getContentResolver()
                    .call(DeviceEventslet.CONTENT_URI,
                            DeviceEventslet.Method.GET_DEVICE_EVENTS,
                            null,
                            extras);

            if (null == returnBundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(returnBundle, result);

                if (result.getCode() == Result.RESULT_OK && returnBundle.containsKey(Result.KEY_RESULT)) {
            return JsonParser.getInstance().fromJson(returnBundle.getString(Result.KEY_RESULT),
                JsonParser.type(List.class, DeviceEvent.class));
                } else {
                    Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, "Failed to get device events: " + result.getCause());
                }
            }
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * <p>
     * AbstractDeviceEventsChangeObserver provides a mechanism to monitor the state of the
     * {@link DeviceEventsService} operations. The AbstractDeviceEventsChangeObserver receives
     * callbacks when there have been changes to the state of the event from a printer.
     * </p>
     *
     * @since API 5
     */
    @SuppressWarnings({"unused"})
    @DeviceApi
    public static abstract class AbstractDeviceEventsChangeObserver extends BroadcastReceiver {
        private final Handler mHandler;

        /**
         * <p>Default AbstractDeviceEventsChangeObserver Constructor</p>
         *
         * @param handler The handler to run callbacks on, or null if none.
         * @since API 5
         */
        public AbstractDeviceEventsChangeObserver(final Handler handler) {
            super();
            mHandler = handler;
        }

        /**
         * <p>
         * Register the observer to monitor state when device event changes.
         * </p>
         *
         * @param context The Context in which the application is running. If it's null, event will not be triggered.
         * @exception NullPointerException Calling a method on a null reference(context) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         *
         * @since API 5
         */
        @SuppressWarnings({"unused"})
        @SuppressLint("RestrictedApi")
        public void register(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(TAG, "Registering device event changed ev");

            final IntentFilter filter = new IntentFilter(DeviceEventslet.DEVICE_EVENTS_CHANGED_ACTION);
            context.registerReceiver(this, filter, Workpath.permissions.SDK_ACCESS_DEVICE_EVENTS_PERMISSION, null);
        }

        /**
         * <p>
         * Unregister the observer and stop monitoring state when device event changes.
         * </p>
         *
         * @param context The Context in which the application is running.
         * @exception NullPointerException Calling a method on a null reference(context) or
         * trying to access a field of a null reference will trigger a NullPointerException.
         *
         * @since API 5
         */
        @SuppressWarnings({"unused"})
        @SuppressLint("RestrictedApi")
        public void unregister(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(TAG, "Un-registering device event changed ev");
            context.unregisterReceiver(this);
        }

        /**
         * @hide final
         */
        @Override
        public final void onReceive(final Context context, final Intent intent) {
            if (isOrderedBroadcast()) {
                abortBroadcast();
            }

            SLog.d(TAG, "Received intent for " + intent.getAction());
            final String action = intent.getAction();
            if (!DeviceEventslet.DEVICE_EVENTS_CHANGED_ACTION.equals(action)) {
                SLog.e(TAG, "Received invalid intent");
                return;
            }
            if (mHandler == null) {
                onRecv(context, intent);
            } else {
                mHandler.post(new DeviceEventsService.AbstractDeviceEventsChangeObserver.DeviceEventsStateRunnable(context, intent));
            }
        }

        private void onRecv(final Context context, final Intent intent) {
            SLog.d(TAG, "Call onChange()");

            final String action = intent.getAction();
            final String deviceEventsInfo = (intent.hasExtra(DeviceEventslet.Keys.KEY_DEVICE_EVENTS)) ? intent.getStringExtra(DeviceEventslet.Keys.KEY_DEVICE_EVENTS) : null;

            if (action != null && deviceEventsInfo != null) {
                SLog.d(TAG, "Received intent for " + action + ", deviceEventsInfo is " + deviceEventsInfo);
                DeviceEvent deviceEvent = JsonParser.getInstance().fromJson(deviceEventsInfo, DeviceEvent.class);
                onChange(deviceEvent);
            }
        }

        private final class DeviceEventsStateRunnable implements Runnable {
            private final Intent mIntent;
            private final Context mContext;

            DeviceEventsStateRunnable(final Context context, final Intent intent) {
                mIntent = intent;
                mContext = context;
            }

            @Override
            public void run() {
                onRecv(mContext, mIntent);
            }
        }

        /**
         * <p>Called to notify the client that a printer has event.</p>
         *
         * @param deviceEventInfo The detail of device event which occurs on a printer
         *
         * @since API 5
         */
        public abstract void onChange(DeviceEvent deviceEventInfo);
    }
}
