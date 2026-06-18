// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.supplies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hp.workpath.api.Result;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.supplies.supplyinfo.Supply;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.SLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>SuppliesService provides interfaces to retrieve Supplies from a printer.</p>
 *
 * @since API 5
 */
@DeviceApi
public final class SuppliesService {
    private static final String TAG = Supplieslet.TAG;

    private SuppliesService() {
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, SuppliesService operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true.
     * @exception NullPointerException Returns error if context is null.
     * @since API 5
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Supplieslet.Keys.PACKAGE_NAME, packageName);
        extras.putInt(Supplieslet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(Supplieslet.CONTENT_URI,
                        Supplieslet.Method.IS_SUPPORTED,
                        null,
                        extras);

        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(Supplieslet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Supplieslet.IS_SUPPORTED_EXTRA);
    }

    /**
     * <p>Returns Supplies information</p>
     *
     * @param context The Context in which the application is running. If it's null, configuration will not be retrieved.
     * @param result  (optional) Indicates any errors which occurred while
     *                retrieving configuration.
     * @return List Supplies information
     * @exception NullPointerException if context is null.
     * @since API 5
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    @SuppressLint("RestrictedApi")
    @Nullable
    public static synchronized List<Supply> getSuppliesInfo(@NonNull final Context context,
                                                            @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Supplieslet.Param.PACKAGE_NAME, packageName);
        extras.putInt(Supplieslet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }

        try {
            final Bundle bundle =
                    context.getContentResolver().call(Supplieslet.CONTENT_URI, Supplieslet.Method.GET_SUPPLIES, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(bundle, result);

                if (bundle.containsKey(Result.KEY_RESULT)) {
                    final String suppliesInfoOrg = bundle.getString(Result.KEY_RESULT);
                    List<Supply> supplyList = new ArrayList<Supply>();
                    if (!TextUtils.isEmpty(suppliesInfoOrg)) {
                        try {
                            replaceCountersForArray(suppliesInfoOrg);
                            SLog.d(TAG, "Replaced STR: " + ORG_STR.length());
                            JSONObject jsonObject = new JSONObject((ORG_STR.length() > 0) ? ORG_STR : suppliesInfoOrg);
                            JSONObject agentListObject = jsonObject.getJSONObject("Supplies").getJSONObject("AgentList");
                            JSONArray jsonArray;
                            if (agentListObject.has("Agent")) { //In the case of the legacy mono model, agent is not an array.
                                JSONObject dataObject = agentListObject.optJSONObject("Agent");
                                if (dataObject != null) {
                                    jsonArray = new JSONArray();
                                    jsonArray.put(dataObject);
                                } else {
                                    jsonArray = agentListObject.optJSONArray("Agent");
                                }
                            } else {
                                jsonArray = new JSONArray(); // Do nothing
                            }							
                            SLog.d(TAG, "Supplies is not empty, " + jsonArray.length());
                            Type listType = JsonParser.type(List.class, Supply.class);
                            supplyList = JsonParser.getInstance().fromJson(jsonArray.toString(), listType);

                        } catch (Throwable throwable) {
                            SLog.d(TAG, "Supplies info is invalid");
                            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SYSTEM_ERROR, "Supplies info is invalid:" + throwable.getMessage());
                        }

                        return supplyList;
                    } else {
                        SLog.d(TAG, "SupplyInfo is null");
                        Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, "SupplyInfo is null");
                    }
                }
            }
        } catch (Throwable e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, e.getMessage());
        }

        return null;
    }

    private static String ORG_STR = "";

    private static void replaceCountersForArray(final String orgStr) {
        final int len = 11; //"Counter":{"
        int preStrIdx = orgStr.indexOf("\"Counter\":{\"");
        if (preStrIdx < 0) {
            return;
        }
        String leftStr = orgStr.substring(preStrIdx + len);
        int midStrIdx = leftStr.indexOf("}},");

        String tmpStr = orgStr.substring(0, preStrIdx + len - 2) + ":[{" + leftStr.substring(0, midStrIdx) + "}}]," + leftStr.substring(midStrIdx + "}},".length());
        ORG_STR = tmpStr;
        replaceCountersForArray(tmpStr);
    }
}
