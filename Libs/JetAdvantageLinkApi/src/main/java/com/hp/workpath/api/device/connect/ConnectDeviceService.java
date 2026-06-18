// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.Result;
import com.hp.workpath.api.device.DeviceInfo;
import com.hp.workpath.api.device.connect.intent.ConnectDeviceRequestIntent;
import com.hp.workpath.api.device.connect.intent.DisconnectDeviceRequestIntent;
import com.hp.workpath.api.device.connect.ui.ConnectInteractionDialog;
import com.hp.workpath.common.Platform;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.workpath.common.helper.SelectedPrinterHelper;
import com.hp.jetadvantage.link.common.intents.DeviceConnectedIntent;
import com.hp.jetadvantage.link.common.intents.DeviceDisconnectedIntent;
import com.hp.jetadvantage.link.common.model.PrinterInfo;
import com.hp.workpath.common.utils.SLog;

/**
 * <p>ConnectDeviceService provides interfaces for handling connection with a device.</p>
 *
 * @since API 1
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
@MobileApi
public final class ConnectDeviceService {
    private static final String CID_FRAGMENT_TAG = "CID";

    /**
     * @hide only for internal usage
     */
    public static ConnectInteractionDialog sConnectInteractionDialog;

    /**
     * <p>Requests to connect to the specified device.</p>
     *
     * @param context     The Context in which the application is running. If it's null, client will not be connected with a device.
     * @param attributes  Attributes for connecting to a device
     * @param letAttributes Attributes for using a built-in UI
     * @param result      Returns result of requesting connection
     * @exception NullPointerException If context or attributes are null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static synchronized void connect(@NonNull final Context context, @NonNull final ConnectDeviceAttributes attributes,
                                            @Nullable final ConnectDeviceletAttributes letAttributes, @Nullable Result result) {
        Preconditions.checkNotNull(context, "Context must be provided");
        Preconditions.checkNotNull(attributes, "ConnectDeviceAttributes must be provided");

        if (result == null) {
            result = new Result();
        }

        final ConnectDeviceRequestIntent intent = new ConnectDeviceRequestIntent();
        final ConnectDeviceRequestIntent.IntentParams params =
                new ConnectDeviceRequestIntent.IntentParams(attributes, letAttributes, context.getPackageName());
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        if (letAttributes != null && letAttributes.mShowUi) {
            Preconditions.checkArgument(context instanceof Activity);
            startProgressUI((Activity) context, attributes, intent);
        } else {
            context.sendOrderedBroadcast(intent, null);
        }

        Result.pack(result, Result.RESULT_OK);
    }

    /**
     * <p>Requests to disconnect from the connected device.</p>
     *
     * @param context The Context in which the application is running. If it's null, connection will be kept.
     * @param result Returns result of requesting disconnection
     * @exception NullPointerException If context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static synchronized void disconnect(@NonNull final Context context, @Nullable Result result) {
        Preconditions.checkNotNull(context, "Context must be provided");

        if (result == null) {
            result = new Result();
        }

        final DisconnectDeviceRequestIntent intent = new DisconnectDeviceRequestIntent();
        final DisconnectDeviceRequestIntent.IntentParams params =
                new DisconnectDeviceRequestIntent.IntentParams(context.getPackageName());
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        context.sendOrderedBroadcast(intent, null);

        Result.pack(result, Result.RESULT_OK);
    }

    /**
     * <p>Returns the status whether an application connects to a device or not.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns TRUE, if the device is connected. Otherwise, FALSE.
     * @exception NullPointerException If context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static boolean isConnected(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        try {
            final PrinterInfo pi = SelectedPrinterHelper.get(context.getContentResolver());

            return !PrinterInfo.isEmpty(pi);
        } catch (Exception e) {
            SLog.e(ConnectDevicelet.TAG, "Failed to get printer info", e);
        }
        return false;
    }

    /**
     * <p>Returns details from the connected device.</p>
     *
     * @param context The Context in which the application is running.
     * @param result Returns result of requesting device information
     * @return DeviceInfo Returns connected device information
     * @exception NullPointerException If context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static DeviceInfo getConnectedDeviceInfo(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        if (result == null) {
            result = new Result();
        }

        try {
            Result.pack(result, Result.RESULT_OK);

            PrinterInfo printerInfo = SelectedPrinterHelper.getUpdated(context.getContentResolver());
            return !PrinterInfo.isEmpty(printerInfo) ? new DeviceInfo(printerInfo) : null;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, ConnectDeviceService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true.
     * @exception NullPointerException Returns error if context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        return Platform.isMobile();
    }

    private static void startProgressUI(final Activity activity,
            final ConnectDeviceAttributes attributes,
            final ConnectDeviceRequestIntent intent) {
        String deviceName;
        if (attributes.mDeviceInfo != null) {
            deviceName = attributes.mDeviceInfo.getName();
        } else {
            deviceName = attributes.mUri.getAuthority();
        }

        Bundle deviceNameBundle = new Bundle();
        deviceNameBundle.putString(ConnectDevicelet.Keys.KEY_NAME, deviceName);

        final FragmentManager fm = activity.getFragmentManager();

        if (sConnectInteractionDialog == null) {
            SLog.d(ConnectDevicelet.TAG, "sConnectInteractionDialog is null. Creating it");

            // delay sending connect request till Progress UI service connected
            deviceNameBundle.putParcelable(ConnectDevicelet.Keys.KEY_REQUEST, intent);

            sConnectInteractionDialog = new ConnectInteractionDialog();
            sConnectInteractionDialog.setParamBundle(deviceNameBundle);
            sConnectInteractionDialog.setParentActivity(activity);

            fm.beginTransaction().add(sConnectInteractionDialog, CID_FRAGMENT_TAG).commit();
        } else {
            sConnectInteractionDialog.setParentActivity(activity);

            Message message = Message.obtain(null, ConnectDevicelet.Message.MSG_IN_FG, 0, 0, deviceNameBundle);
            message.replyTo = sConnectInteractionDialog.getFromServer();

            try {
                Messenger toServer = sConnectInteractionDialog.getToServer();

                if (toServer != null) {
                    toServer.send(message);

                    // send connect request
                    activity.sendOrderedBroadcast(intent, null);

                    SLog.d(ConnectDevicelet.TAG, "Sent MSG_IN_FG message to server and sent connect request");
                } else {
                    // service is probably disconnected, try to reconnect and resend connect request
                    SLog.w(ConnectDevicelet.TAG, "Did not send MSG_IN_FG message to server as toServer is null");

                    deviceNameBundle.putParcelable(ConnectDevicelet.Keys.KEY_REQUEST, intent);

                    sConnectInteractionDialog.setParamBundle(deviceNameBundle);
                    sConnectInteractionDialog.bindService();
                }
            } catch (final RemoteException re) {
                SLog.e(ConnectDevicelet.TAG, "Failed to send MSG_IN_FG: ", re);
            }
        }
    }

    /**
     * <p>Provides a mechanism to monitor the state of the
     * {@link ConnectDeviceService} operations. If application extends AbstractConnectDeviceObserver, it can receive
     * events when there have been changes the state of connection of the device.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static abstract class AbstractConnectDeviceObserver extends BroadcastReceiver {
        private final Handler mHandler;

        /**
         * <p>Constructor</p>
         * @param handler The handler to run callbacks on, or null if none.
         * @since API 1
         */
        public AbstractConnectDeviceObserver(final Handler handler) {
            super();
            mHandler = handler;
        }

        /**
         * <p>Requests to register the observer to monitor events of the change of the status associated with connection.</p>
         *
         * @param context The Context in which the application is running. If it's null, event will not be triggered.
         * @exception NullPointerException If context is null.
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public final void register(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(ConnectDevicelet.TAG, "Registering device connection event");

            final IntentFilter filter = new IntentFilter();
            filter.addAction(DeviceConnectedIntent.ACTION_DEVICE_CONNECTED);
            filter.addAction(DeviceDisconnectedIntent.ACTION_DEVICE_DISCONNECTED);
            context.registerReceiver(this, filter);
        }

        /**
         * <p>Requests to unregister the observer to stop monitoring events of the change of the status associated with connection.</p>
         *
         * @param context The Context in which the application is running.
         * @exception NullPointerException If context is null.
         * @exception IllegalArgumentException if this receiver hasn't been registered.
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public final void unregister(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");

            SLog.d(ConnectDevicelet.TAG, "Un-registering device connection event");
            context.unregisterReceiver(this);
        }

        /**
         * @hide internal
         */
        @Override
        public final void onReceive(Context context, Intent intent) {
            SLog.d(ConnectDevicelet.TAG, "Received intent for " + intent.getAction());
            final String action = intent.getAction();

            if (action != null) {
                final DeviceInfo printerInfo;

                switch (action) {
                    case DeviceConnectedIntent.ACTION_DEVICE_CONNECTED:
                        DeviceConnectedIntent deviceConnectedIntent = new DeviceConnectedIntent(intent);
                        DeviceConnectedIntent.IntentParams connectParams = deviceConnectedIntent.getIntentParams();
                        printerInfo = new DeviceInfo(connectParams.getPrinterInfo());
                        final Result connectResult = Result.parse(connectParams.getResult(), new Result());
                        if (connectResult.getCode() == Result.RESULT_OK) {
                            if (mHandler == null) {
                                onConnect(printerInfo);
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onConnect(printerInfo);
                                    }
                                });
                            }
                        } else {
                            if (mHandler == null) {
                                onFail(printerInfo, connectResult);
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onFail(printerInfo, connectResult);
                                    }
                                });
                            }
                        }
                        break;

                    case DeviceDisconnectedIntent.ACTION_DEVICE_DISCONNECTED:
                        DeviceDisconnectedIntent deviceDisconnectedIntent = new DeviceDisconnectedIntent(intent);
                        DeviceDisconnectedIntent.IntentParams disconnectParams = deviceDisconnectedIntent.getIntentParams();
                        printerInfo = new DeviceInfo(disconnectParams.getPrinterInfo());
                        final Result disconnectResult = Result.parse(disconnectParams.getResult(), new Result());
                        if (disconnectResult.getCode() == Result.RESULT_OK) {
                            if (mHandler == null) {
                                onDisconnect(printerInfo);
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onDisconnect(printerInfo);
                                    }
                                });
                            }
                        } else {
                            if (mHandler == null) {
                                onFail(printerInfo, disconnectResult);
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onFail(printerInfo, disconnectResult);
                                    }
                                });
                            }
                        }
                        break;

                    default:
                        SLog.e(ConnectDevicelet.TAG, "Received invalid intent");
                }
            }
        }

        /**
         * <p>Called to notify the client when connection is established with connected device information.</p>
         *
         * @param printerInfo Returns deviceInfo of connected device
         * @since API 1
         */
        public void onConnect(final DeviceInfo printerInfo) {

        }

        /**
         * <p>Called to notify the client when connection is closed completely by request.</p>
         *
         * @param printerInfo Returns deviceInfo of disconnected device
         * @since API 1
         */
        public void onDisconnect(final DeviceInfo printerInfo) {

        }

        /**
         * <p>Called to notify the client when requests of connection/disconnection are failed.</p>
         *
         * @param printerInfo Returns deviceInfo of requested device
         * @param result Returns result of the operation
         * @since API 1
         */
        public void onFail(final DeviceInfo printerInfo, Result result) {

        }
    }
}