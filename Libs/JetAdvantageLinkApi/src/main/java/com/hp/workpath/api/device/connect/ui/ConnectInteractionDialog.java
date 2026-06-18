// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.device.connect.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.hp.workpath.api.device.connect.ConnectDevicelet;
import com.hp.workpath.api.device.connect.ConnectDeviceService;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.MobileApi;
import com.hp.workpath.common.utils.SLog;

import java.lang.ref.WeakReference;

/**
 * Progress dialog for device connection
 *
 * @hide
 */
@MobileApi
public class ConnectInteractionDialog extends Fragment {
    private static final String TAG = ConnectDevicelet.TAG;

    private static WeakReference<Activity> sParentActivity;

    @MobileApi
    private enum BindState {
        DISCONNECTED, BINDING, CONNECTED
    }

    private BindState mBound = BindState.DISCONNECTED;
    private Messenger mFromServer;
    private Messenger mToServer;
    private Bundle mParamBundle;

    private final ServerMsgHandler mServerHandler = new ServerMsgHandler();

    private static final String FG_TRACKER_SVC_COMP_NAME = "com.hp.jetadvantage.link.services.connectlet.service.ConnectProgressTracker";

    /**
     * Extra from the service for progress text
     *
     * @hide internal use
     */
    public static final String PROGRESS_TEXT_EXTRA = "progressTextExtra";

    private static final String STRING_TYPE_RES = "string";

    /**
     * @hide internal usage
     */
    public Messenger getFromServer() {
        return mFromServer;
    }

    /**
     * @hide internal usage
     */
    public Messenger getToServer() {
        return mToServer;
    }

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            mToServer = new Messenger(service);
            mBound = BindState.CONNECTED;

            if (mParamBundle != null) {
                Bundle paramBundle = new Bundle(mParamBundle);
                final Message message = Message.obtain(null, ConnectDevicelet.Message.MSG_IN_FG, 0, 0, paramBundle);
                message.replyTo = mFromServer;

                Intent intent = paramBundle.getParcelable(ConnectDevicelet.Keys.KEY_REQUEST);
                paramBundle.remove(ConnectDevicelet.Keys.KEY_REQUEST);
                try {
                    mToServer.send(message);

                    SLog.d(TAG, "onServiceConnected: " + sParentActivity.get() + ", intent=" + intent);
                    sParentActivity.get().sendOrderedBroadcast(intent, null);

                    SLog.d(ConnectDevicelet.TAG, "Sent MSG_IN_FG message to server and sent connect request");
                } catch (final RemoteException re) {
                    SLog.e(TAG, "Failed to send message to FG Tracking service: ", re);
                }

                mParamBundle = null;
            }

            SLog.d(TAG, "Service Connected");
        }

        /**
         * @hide internal usage
         */
        @Override
        public void onServiceDisconnected(final ComponentName name) {
            mBound = BindState.DISCONNECTED;
            mToServer = null;
            SLog.d(TAG, "Service Disconnected");
        }
    };

    /**
     * @hide internal usage
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SLog.d(TAG, "Fragment onCreate");
        setRetainInstance(true);
        mFromServer = new Messenger(mServerHandler);
    }

    /**
     * @hide internal usage
     */
    @Override
    public void onPause() {
        super.onPause();
        SLog.d(TAG, "Fragment onPause");
        if (mBound != BindState.DISCONNECTED) {
            getActivity().unbindService(mConnection);
            mBound = BindState.DISCONNECTED;
            SLog.d(TAG, "Unbound with the service");
        }

        // Cancel the dialog
        Message message = Message.obtain(null, ConnectDevicelet.Message.MSG_CANCEL_ALL, 0, 0);
        try {
            mFromServer.send(message);
            SLog.d(TAG, "Sent Cancel All message");
        } catch (final RemoteException re) {
            SLog.d(TAG, "RemoteException sending cancel message: " , re);
        }
    }

    /**
     * @hide internal usage
     */
    @Override
    public void onResume() {
        super.onResume();

        SLog.d(TAG, "Fragment onResume");

        bindService();
        SLog.d(TAG, "Bound to Services package and service");
    }

    /**
     * @hide internal usage
     */
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SLog.d(TAG, "onActivityCreated");
        sParentActivity = new WeakReference<>(getActivity());
    }

    /**
     * @hide internal usage
     */
    @Override
    public void onDetach() {
        super.onDetach();
        SLog.d(TAG, "onDetach");
        ConnectDeviceService.sConnectInteractionDialog = null;
        // There's no more attached activity, so cancel dialog which holds activity references
        mServerHandler.cancelAll();
    }

    /**
     * Sets parent activity
     *
     * @param activity to be set as parent
     * @hide internal use
     */
    public void setParentActivity(final Activity activity) {
        sParentActivity = new WeakReference<>(activity);
    }

    /**
     * Sets param bundle
     *
     * @param paramBundle to be set as parent
     * @hide internal use
     */
    public void setParamBundle(Bundle paramBundle) {
        mParamBundle = paramBundle;
    }

    /**
     * Check if connection is alivve and re-bind if needed
     */
    public void bindService() {
        if (getActivity() != null && mBound == BindState.DISCONNECTED) {
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName(Sdk.SERVICES_PACKAGE, FG_TRACKER_SVC_COMP_NAME));
            getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            mBound = BindState.BINDING;
        }
    }

    /**
     * @hide internal usage
     */
    @MobileApi
    private static class ServerMsgHandler extends Handler {

        private ProgressDialog mCPD = null;
        private boolean mShowCPD = false;

        // Cache the last SHOW CP params
        private String mCPText = "";
        private String mDeviceName;

        void initCPD() {
            SLog.d(TAG, "initCPD: mDeviceName = " + mDeviceName);

            mCPD = new ProgressDialog(sParentActivity.get());
            mCPD.setCancelable(true);
            mCPD.setCanceledOnTouchOutside(false);
            mCPD.setIndeterminate(true);
            mCPD.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(final DialogInterface dialog) {
                    mCPD = null;
                }
            });

            final Resources r = sParentActivity.get().getResources();
            final String pkg = sParentActivity.get().getPackageName();
            final int titleTextStrId = r.getIdentifier("connection_dialog_title", STRING_TYPE_RES, pkg);

            mCPD.setTitle(titleTextStrId);
            mCPD.setMessage(mCPText);
            mCPD.show();
        }

        /**
         * @hide internal usage
         */
        @Override
        public synchronized void handleMessage(final Message msg) {
            SLog.d(TAG, "Msg: " + msg.what + "\n"
                    + "Msg.arg1: " + msg.arg1 + "\n"
                    + "Msg.arg2: " + msg.arg2 + "\n"
                    + "mShowCPD: " + mShowCPD + "\n"
                    + "mDeviceName: " + mDeviceName + "\n"
                    + "mCPD: " + mCPD + "\n");

            switch (msg.what) {
                case ConnectDevicelet.Message.MSG_SHOW_CPD:
                    // Ignore showing Job Progress if the NPCD is showing. Joblet is anyhow showing the consolidated JobProgress in the notification

                    Bundle deviceNameBundle = (Bundle) msg.obj;

                    String msgDeviceName = deviceNameBundle.getString(ConnectDevicelet.Keys.KEY_NAME);
                    if (mDeviceName == null || !mDeviceName.equals(msgDeviceName)) {
                        SLog.d(TAG, "SHOW_CPD: mDeviceName " + mDeviceName + " is null or its not same as in deviceNameBundle " + msgDeviceName);
                        SLog.d(TAG, "Setting mShowCPD = true");
                        mShowCPD = true;
                    }

                    mDeviceName = deviceNameBundle.getString(ConnectDevicelet.Keys.KEY_NAME);
                    SLog.d(TAG, "SHOW_CPD: handleMessage: mDeviceName = " + mDeviceName);

                    if (msg.getData().containsKey(PROGRESS_TEXT_EXTRA)) {
                        mCPText = msg.getData().getString(PROGRESS_TEXT_EXTRA);
                    } else {
                        final Resources r = sParentActivity.get().getResources();
                        mCPText = r.getString(r.getIdentifier("connection_dialog_message",
                                STRING_TYPE_RES, sParentActivity.get().getPackageName()), mDeviceName);
                    }
                    if (mShowCPD) {
                        if (mCPD == null) {
                            SLog.d(TAG, "SHOW_CPD: CPD is null. Calling initCPD to show CPD.");
                            initCPD();
                        } else {
                            SLog.d(TAG, "SHOW_CPD: CPD is not null. Calling setMessage to show mCPText.");
                            mCPD.setMessage(mCPText);
                            if (!mCPD.isShowing()) {
                                SLog.d(TAG, "SHOW_CPD: CPD show.");
                                mCPD.show();
                            }
                        }
                    } else {
                        SLog.d(TAG, "mShowCPD is " + mShowCPD);
                    }
                    break;

                case ConnectDevicelet.Message.MSG_CANCEL_ALL:
                    cancelAll();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }

        /**
         * Cancels all current dialogs
         */
        private void cancelAll() {
            if (mCPD != null && mCPD.isShowing()) {
                mCPD.cancel();
            }
            mCPD = null;
        }
    }

}
