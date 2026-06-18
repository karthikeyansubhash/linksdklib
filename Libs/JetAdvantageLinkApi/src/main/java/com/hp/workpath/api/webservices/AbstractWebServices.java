// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.webservices;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.hp.jetadvantage.link.svcmgt.services.ISvcmgtServicesToLibraryInterface;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.SLog;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <p>Implementations of this class provide webservices for Workpath platform.</p>
 *
 * AbstractWebServices provides methods GET/PUT/POST/DELETE to get HttpRequest which comes from a client as a parameter and return a HttpResponse as a result.
 *
 * @since API 6
 */
public abstract class AbstractWebServices extends Service {
    private static final String TAG = "[AbstractWS]";
    /**
     * @hide for internal use
     */
    public static final class WebServicesMessage {
        public static final int START  = 100;
        public static final int GET    = 200;
        public static final int POST   = 300;
        public static final int PUT    = 400;
        public static final int DELETE = 500;
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
        SLog.d(TAG, "onBind called");
        return binder;
    }
    /**
     * @hide for internal use
     */
    public Callback callback;

    /**
     * Callback for abstract web services methods
     *
     * @since API 6
     */
    public interface Callback {
        /**
         * Method to authenticate HttpRequest
         * @param request HttpRequest object with header, attribute, body, method name.
         * @return Authentication result - true if authenticated and false if not authenticated
         *
         * @since API 6
         */
        boolean authenticated(HttpRequest request);
        /**
         * Method to query a get request
         * @param request HttpRequest object with header, attribute, body, method name.
         * @return HttpResponse for the given request
         *
         * @since API 6
         */
        HttpResponse get(HttpRequest request);
        /**
         * Method to query a post request
         * @param request HttpRequest object with header, attribute, body, method name.
         * @return HttpResponse for the given request
         *
         * @since API 6
         */
        HttpResponse post(HttpRequest request);
        /**
         * Method to query a put request
         * @param request HttpRequest object with header, attribute, body, method name.
         * @return HttpResponse for the given request
         *
         * @since API 6
         */
        HttpResponse put(HttpRequest request);
        /**
         * Method to query a delete request
         * @param request HttpRequest object with header, attribute, body, method name.
         * @return HttpResponse for the given request
         *
         * @since API 6
         */
        HttpResponse delete(HttpRequest request);
    }
    private final ISvcmgtServicesToLibraryInterface.Stub binder = new ISvcmgtServicesToLibraryInterface.Stub() {
        @Override
        public String callService(String request, String requestHeaders, String requestBody) {
            SLog.d(TAG, "Start to call bindService");
            if (callback != null) {
                SLog.d(TAG, "Request datas(original)" + request);
                SLog.d(TAG, "Request requestHeaders(original)" + requestHeaders);
                SLog.d(TAG, "Request body(original)" + requestBody);
                try {
                    String parsedRequest = request.substring(request.indexOf("▤")+1);
                    String parsedMethod = request.substring(0, request.indexOf("▤"));
                    SLog.d(TAG, "Request datas " + parsedMethod + "," +parsedRequest);

                    String parsedRequestBody = requestBody.substring(requestBody.indexOf("▤")+1);
                    String parsedMediaType = requestBody.substring(0, requestBody.indexOf("▤"));
                    SLog.d(TAG, "Request body " + parsedRequestBody + "," + parsedMediaType);
                    HttpRequest httpRequest = new HttpRequest();
                    Map<String, Object> data = new HashMap<>();
                    if(parsedRequest != null && parsedRequest.length() > 0) {
                        // Use raw HashMap parse (values become LinkedHashMap/List/primitive tree); generic detail not needed
                        data = JsonParser.getInstance().fromJson(parsedRequest, HashMap.class);
                        httpRequest.setAttribute(data);
                    }

                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", parsedMediaType);

                    boolean chkAuth = false;
                    JSONArray jsonArray = new JSONArray(requestHeaders);
                    String value = null;
                    for(int inx=0; inx<jsonArray.length(); inx++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(inx);
                        if(jsonObject.has("name")) {
                            if("X-Auth".equalsIgnoreCase(jsonObject.get("name").toString())) {
                                SLog.d(TAG, "X-Auth");
                                chkAuth = true;
                            }

                            if(!"Content-Type".equalsIgnoreCase(jsonObject.get("name").toString())) {
                                if(!jsonObject.has("value")) { value = ""; }
                                else value = jsonObject.get("value").toString();
                                headers.put(jsonObject.get("name").toString(), value);
                            }
                        }
                    }

                    if (headers.containsKey("Authorization")) {
                        SLog.d(TAG, "Invalid access for auth");
                        HttpResponse httpResponse = new HttpResponse();
                        httpResponse.setStatusCode(401);
                        httpResponse.setStatusDescription("Unauthorized");
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤";
                    }

                    httpRequest.setHeader(headers);
                    httpRequest.setBody(parsedRequestBody);
                    httpRequest.setMethod(parsedMethod.toUpperCase());

                    String response = null;
                    boolean authResult = false;
                    if(chkAuth) {
                        SLog.d(TAG, "Start check auth");
                        authResult = callback.authenticated(httpRequest);
                        SLog.d(TAG, "Start check auth result : " + authResult);
                    }

                    if(chkAuth && !authResult) {
                        HttpResponse httpResponse = new HttpResponse();
                        httpResponse.setStatusCode(401);
                        httpResponse.setStatusDescription("Unauthorized");
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤";
                    }

                    if("post".equalsIgnoreCase(parsedMethod)) {
                        HttpResponse httpResponse = callback.post(httpRequest);
                        SLog.d(TAG, "Requested to post data ");
                        String body = httpResponse.getBody()==null?"▤":httpResponse.getBody().toString();
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤"+body+"▤"+JsonParser.getInstance().toJson(httpResponse.getHeader()).toString();
                    } else if("put".equalsIgnoreCase(parsedMethod)) {
                        HttpResponse httpResponse = callback.put(httpRequest);
                        SLog.d(TAG, "Requested to put data ");
                        String body = httpResponse.getBody()==null?"▤":httpResponse.getBody().toString();
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤"+body+"▤"+JsonParser.getInstance().toJson(httpResponse.getHeader()).toString();
                    } else if("delete".equalsIgnoreCase(parsedMethod)) {
                        HttpResponse httpResponse = callback.delete(httpRequest);
                        SLog.d(TAG, "Requested to delete data ");
                        String body = httpResponse.getBody()==null?"▤":httpResponse.getBody().toString();
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤"+body+"▤"+JsonParser.getInstance().toJson(httpResponse.getHeader()).toString();
                    } else { //get, else
                        HttpResponse httpResponse = callback.get(httpRequest);
                        SLog.d(TAG, "Requested to get data" );
                        String body = httpResponse.getBody()==null?"▤":httpResponse.getBody().toString();
                        return httpResponse.getStatusCode()+"▤"+httpResponse.getStatusDescription()+"▤"+body+"▤"+JsonParser.getInstance().toJson(httpResponse.getHeader()).toString();
                    }
                } catch (Throwable throwable) {
                    SLog.e(TAG, "Failed to get data" + throwable.getMessage());
                }
            }else{
                SLog.d(TAG, "Callback is null");
            }
            return null;
        }
    };

    /**
     * @hide for internal use
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * @hide for internal use
     */
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mMessenger = new Messenger(new ServiceHandler(getMainLooper(), this));
        mOnCreateCalled = true;
        onStart();
    }

    /**
     * @hide for internal use
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private static class ServiceHandler extends Handler {
        private final WeakReference<AbstractWebServices> mRef;

        ServiceHandler(Looper looper, AbstractWebServices context) {
            super(looper);
            mRef = new WeakReference<>(context);
            SLog.d(TAG, "Service is started");
        }

        @Override
        public void handleMessage(Message msg) {
            AbstractWebServices serviceBase = mRef.get();
            if (serviceBase == null) {
                //fail requests
                return;
            }
            Bundle data = msg.getData();
            switch (msg.what) {
                // START
                case WebServicesMessage.START:
                    synchronized (serviceBase.mLock) {
                        serviceBase.onStart();
                    }
                    break;

                case WebServicesMessage.GET:
                    synchronized (serviceBase.mLock) {
                        Log.d(TAG, "test");
                    }
                    break;
                default:
                    // return error
            }
        }
    }

    /**
     * <p>This method is called to notify the client that web service started successfully.</p>
     *
     * @since API 6
     */
    protected abstract void onStart();
} 