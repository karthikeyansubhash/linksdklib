// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.discovery;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.device.DeviceInfo;
import com.hp.workpath.api.device.discovery.intent.DiscoveryStartRequestIntent;
import com.hp.workpath.api.device.discovery.intent.DiscoveryStopRequestIntent;
import com.hp.workpath.common.Platform;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.workpath.common.utils.SLog;

/**
 * <p>DiscoveryService provides interfaces to start discovery nearby devices.</p>
 *
 * @since API 1
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
@MobileApi
public class DiscoveryService {
    /**
     * <p>Starts discovery of nearby devices</p>
     *
     * @param context The Context in which the application is running. If it's null, starting discovery will be failed.
     * @param attributes Attributes for starting discovery
     * @param letAttributes Attributes for using a built-in UI
     * @exception NullPointerException If context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static synchronized void start(@NonNull final Context context, @Nullable final DiscoveryAttributes attributes,
            @Nullable final DiscoveryletAttributes letAttributes) {
        Preconditions.checkNotNull(context, "Context must be provided");
        if (letAttributes == null || !letAttributes.mShowDiscoveryUi) {
            Preconditions.checkNotNull(attributes, "DiscoveryAttributes must be provided");
        }

        final DiscoveryStartRequestIntent intent = new DiscoveryStartRequestIntent();
        final DiscoveryStartRequestIntent.IntentParams params =
                new DiscoveryStartRequestIntent.IntentParams(attributes, letAttributes, context.getPackageName());
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        context.sendOrderedBroadcast(intent, null);
    }

    /**
     * <p>Stops discovery, and shows discovered devices.</p>
     *
     * @param context The Context in which the application is running. If it's null, discovery will be running.
     * @param result Returns result of requesting stop of the discovery
     * @exception NullPointerException If context is null.
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static synchronized void stop(@NonNull final Context context, @Nullable Result result) {
        Preconditions.checkNotNull(context, "Context must be provided");

        if (result == null) {
            result = new Result();
        }

        final DiscoveryStopRequestIntent intent = new DiscoveryStopRequestIntent();
        final DiscoveryStopRequestIntent.IntentParams params =
                new DiscoveryStopRequestIntent.IntentParams(context.getPackageName());
        intent.putIntentParams(params);
        intent.setPackage(Sdk.SERVICES_PACKAGE);

        context.sendOrderedBroadcast(intent, null);

        Result.pack(result, Result.RESULT_OK);
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, DiscoveryService operation will be failed.</p>
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

    /**
     * <p>Provides a mechanism to monitor the state of the
     * {@link DiscoveryService} operations. If application extends AbstractDiscoveryObserver, it can receive
     * events when there have been changes the state of discovery of the device.</p>
     *
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @MobileApi
    public static abstract class AbstractDiscoveryObserver extends BroadcastReceiver {
        private final Handler mHandler;

        /**
         * <p>Constructor</p>
         * @param handler The handler to run callbacks on, or null if none.
         * @since API 1
         */
        public AbstractDiscoveryObserver(final Handler handler) {
            super();
            mHandler = handler;
        }

        /**
         * <p>Requests to register the observer to monitor events of the change of the status while discovery.</p>
         *
         * @param context The Context in which the application is running.
         * @exception NullPointerException if context is null.
         *
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public final void register(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");
            SLog.d(Discoverylet.TAG, "Registering device discovered event");

            final IntentFilter filter = new IntentFilter();
            filter.addAction(Discoverylet.DEVICE_DISCOVERED_ACTION);
            filter.addAction(Discoverylet.DISCOVERY_STARTED_ACTION);
            filter.addAction(Discoverylet.DISCOVERY_STOPPED_ACTION);
            filter.addAction(Discoverylet.DISCOVERY_FAILED_ACTION);
            context.registerReceiver(this, filter);
        }

        /**
         * <p>Request to unregister the observer to stop monitoring events of the change of the status while discovery.</p>
         *
         * @param context The Context in which the application is running.
         * @exception NullPointerException if context is null.
         * @exception IllegalArgumentException if this receiver hasn't been registered.
         *
         * @since API 1
         */
        @SuppressLint("RestrictedApi")
        public final void unregister(@NonNull final Context context) {
            Preconditions.checkNotNull(context, "Context must be provided");

            SLog.d(Discoverylet.TAG, "Un-registering device discovered event");
            context.unregisterReceiver(this);
        }

        /**
         * @hide internal
         */
        @Override
        public final void onReceive(Context context, Intent intent) {
            SLog.d(Discoverylet.TAG, "Received intent for " + intent.getAction());
            final String action = intent.getAction();

            if (action != null) {
                final DeviceInfo printerInfo;
                switch (action) {
                    case Discoverylet.DISCOVERY_STARTED_ACTION:
                        if (mHandler == null) {
                            onStart();
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onStart();
                                }
                            });                        }
                        break;

                    case Discoverylet.DISCOVERY_STOPPED_ACTION:
                        if (mHandler == null) {
                            onStop();
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onStop();
                                }
                            });                        }
                        break;

                    case Discoverylet.DISCOVERY_FAILED_ACTION:
                        final Result failResult = Result.parse(intent.getBundleExtra(Discoverylet.Keys.KEY_RESULT), new Result());
                        if (mHandler == null) {
                            onFail(failResult);
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onFail(failResult);
                                }
                            });                        }
                        break;

                    case Discoverylet.DEVICE_DISCOVERED_ACTION:
                        final com.hp.jetadvantage.link.api.device.DeviceInfo orgPrinterInfo = intent.getParcelableExtra(Discoverylet.Keys.KEY_PRINTER_INFO);
                        printerInfo = DeviceInfo.CREATOR_OBJ.createFromObject(orgPrinterInfo);
                        if (mHandler == null) {
                            onDiscover(printerInfo);
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onDiscover(printerInfo);
                                }
                            });                        }
                        break;

                    default:
                        SLog.e(Discoverylet.TAG, "Received invalid intent");
                }
            }
        }

        /**
         * <p>Called to notify the client when discovery is started successfully.</p>
         * @since API 1
         */
        public void onStart() {

        }

        /**
         * <p>Called to notify the client when discovery is stopped successfully.</p>
         * @since API 1
         */
        public void onStop() {

        }

        /**
         * <p>Called to notify the client when DiscoveryService is not requested successfully.</p>
         * @since API 1
         */
        public void onFail(Result result) {

        }

        /**
         * <p>Called to notify the client when device is discovered as new.</p>
         * @param printerInfo new device to be discovered
         * @since API 1
         */
        public void onDiscover(final DeviceInfo printerInfo) {

        }
    }
}