// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.api.helper.email;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;

import com.hp.workpath.api.FileProvider;
import com.hp.workpath.api.Workpath;
import com.hp.workpath.api.Result;
import com.hp.workpath.common.Sdk;
import com.hp.workpath.common.annotation.DeviceApi;
import com.hp.workpath.common.utils.SLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Email provides interfaces for sending email using smtp server
 * which is configured on a device or provided by a client.</p>
 *
 * @since API 1
 */
@DeviceApi
public class Email {
    private static final String TAG = Emaillet.TAG;

    private Email() {
    }

    /**
     * <p>Request to send an email specified by {@link EmailAttributes} based on a printer's SMTP settings.
     * Device's SMTP settings is configured through EWS.</p>
     *
     * @param context         The Context in which the application is running. If it's null, sending will be failed.
     * @param emailAttributes Attributes for sending email such as the content and recipients
     * @param result          (optional) Indicates any errors which occurred while sending an email.
     * @exception NullPointerException Returns error if calling a method on a null reference(context/result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static void send(@NonNull final Context context,
                            @NonNull final EmailAttributes emailAttributes, @Nullable Result result) {
        send(context, emailAttributes, null, null, result);
    }

    /**
     * <p>Sends an email specified by {@link EmailAttributes} using device's SMTP settings or client's SMTP information.</p>
     *
     * @param context         The Context in which the application is running. If it's null, sending will be failed.
     * @param emailAttributes attributes defining the content and recipients of the email
     * @param smtpAttributes  (optional) settings custom SMTP settings,
     *                        if null the device settings will be used
     * @param result          (optional) Indicates any errors which occurred while sending an email.
     * @exception NullPointerException Returns error if calling a method on a null reference(context/result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static void send(@NonNull final Context context,
                            @NonNull final EmailAttributes emailAttributes,
                            @Nullable final SmtpAttributes smtpAttributes,
                            @Nullable Result result) {
        send(context, emailAttributes, smtpAttributes, null, result);
    }

    /**
     * <p>Sends an email specified by {@link EmailAttributes} using SMTP settings defined by
     * {@link SmtpAttributes}</p>
     *
     * @param context         The Context in which the application is running. If it's null, sending will be failed.
     * @param emailAttributes attributes defining the content and recipients of the email
     * @param smtpAttributes  (optional) settings custom SMTP setting,
     *                        if null the device settings will be used
     * @param proxyAttributes (optional) Proxy information for setting custom proxy,
     *                        if null the no proxy will be used
     * @param result          (optional) Indicates any errors which occurred while sending an email.
     * @exception NullPointerException Returns error if calling a method on a null reference(context/result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static void send(@NonNull final Context context,
                            @NonNull final EmailAttributes emailAttributes,
                            @Nullable final SmtpAttributes smtpAttributes,
                            @Nullable final ProxyAttributes proxyAttributes,
                            @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);
        Preconditions.checkNotNull(emailAttributes, "EmailAttributes must be provided");

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Emaillet.Param.PACKAGE_NAME, packageName);
        extras.putInt(Emaillet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        Parcel parcelForEmail = Parcel.obtain();
        emailAttributes.writeToParcel(parcelForEmail, 0);
        parcelForEmail.setDataPosition(0);
        extras.putParcelable(Emaillet.Param.EMAIL_ATTRIBUTES, com.hp.jetadvantage.link.api.helper.email.EmailAttributes.CREATOR.createFromParcel(parcelForEmail));

        Parcel parcelForSmtp = Parcel.obtain();
        if (smtpAttributes != null) {
            smtpAttributes.writeToParcel(parcelForSmtp, 0);
            parcelForSmtp.setDataPosition(0);
            extras.putParcelable(Emaillet.Param.SMTP_ATTRIBUTES, com.hp.jetadvantage.link.api.helper.email.SmtpAttributes.CREATOR.createFromParcel(parcelForSmtp));
        } else {
            extras.putParcelable(Emaillet.Param.SMTP_ATTRIBUTES, null);
        }

        Parcel parcelForProxy = Parcel.obtain();
        if (proxyAttributes != null) {
            proxyAttributes.writeToParcel(parcelForProxy, 0);
            parcelForProxy.setDataPosition(0);
            extras.putParcelable(Emaillet.Param.PROXY_ATTRIBUTES, com.hp.jetadvantage.link.api.helper.email.ProxyAttributes.CREATOR.createFromParcel(parcelForProxy));
        } else {
            extras.putParcelable(Emaillet.Param.PROXY_ATTRIBUTES, null);
        }

        if (result == null) {
            result = new Result();
        }

        List<String> fileNames = new ArrayList<String>();
        try {
            List<File> attachments = emailAttributes.getAttachments();
            final File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            for (int inx = 0; attachments != null && inx < attachments.size(); inx++) {
                if (attachments.get(inx).getAbsolutePath().startsWith(downloadFolder.getAbsolutePath())) { //For download directory
                } else {
                    File newFile = new File(attachments.get(inx).getAbsolutePath());
                    Uri fileUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".provider", newFile);

                    context.getApplicationContext().grantUriPermission(Sdk.SERVICES_PACKAGE, fileUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    fileNames.add(fileUri.toString());
                    SLog.d(TAG, "Permission is granted to " + fileUri + ", " + fileNames.size());
                }
            }
            if (emailAttributes.getAttachments() != null && fileNames.size() > 0 && fileNames.size() == emailAttributes.getAttachments().size()) {
                extras.putStringArrayList(Emaillet.Param.EMAIL_FILENAMES, (ArrayList<String>) fileNames);
            }

            final Bundle bundle =
                    context.getContentResolver().call(Emaillet.CONTENT_URI, Emaillet.Method.SEND, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Return empty");
            } else {
                Result.parse(bundle, result);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
        } finally {
            if (fileNames != null && fileNames.size() > 0) {
                for (String fileName : fileNames) {
                    try {
                        context.getApplicationContext().revokeUriPermission(Uri.parse(fileName), Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * <p>Retrieves {@link EmailAttributes} parameters configured on a printer as default through EWS.</p>
     *
     * @param context The Context in which the application is running.
     * @param result  (optional) Indicates any errors which occurred while sending an email.
     * @return  <p> Email settings which is configured on a device.
     * <ul>
     * <li>Return value can be empty list, if no attribute value is given for email.</li>
     * <li>Return {@link EmailAttributes} values, if attribute value is given for email.</li>
     * </ul>
     * </p>
     * @exception NullPointerException Returns error if calling a method on a null reference(context/result) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    public static EmailAttributes getDefaults(@NonNull final Context context, @Nullable Result result) {
        Workpath.getInstance().checkPreconditions(context);

        Bundle extras = new Bundle();
        String packageName = context.getPackageName();
        extras.putString(Emaillet.Param.PACKAGE_NAME, packageName);
        extras.putInt(Emaillet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        if (result == null) {
            result = new Result();
        }

        try {
            final Bundle bundle =
                    context.getContentResolver().call(Emaillet.CONTENT_URI, Emaillet.Method.GET_DEFAULTS, null, extras);

            if (null == bundle) {
                Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.NOT_SUPPORTED, "Response is empty");
            } else {
                bundle.setClassLoader(EmailAttributes.class.getClassLoader());

                Result.parse(bundle, result);

                com.hp.jetadvantage.link.api.helper.email.EmailAttributes emailAttributes = bundle.getParcelable(Emaillet.Param.EMAIL_ATTRIBUTES);
                return EmailAttributes.CREATOR_OBJ.createFromObject(emailAttributes);
            }
        } catch (SecurityException se) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.SERVICE_ERROR, se.getMessage());
            throw se;
        } catch (Exception e) {
            Result.pack(result, Result.RESULT_FAIL, Result.ErrorCode.UNKNOWN, e.getMessage());
        }

        return null;
    }

    /**
     * <p>This method is needed to determine if the service is supported or not.
     * If it's not supported, Email operation will be failed.</p>
     *
     * @param context The Context in which the application is running.
     * @return boolean Returns result of supported. If service is supported, method returns true. Otherwise method returns false.
     * @exception NullPointerException Returns error if calling a method on a null reference(context) or
     * trying to access a field of a null reference will trigger a NullPointerException.
     * @since API 1
     */
    @SuppressWarnings({"unused"})
    @SuppressLint("RestrictedApi")
    public static boolean isSupported(@NonNull final Context context) {
        Preconditions.checkNotNull(context, "Context must be provided");

        Bundle extras = new Bundle();
        extras.putInt(Emaillet.Param.KEY_CLIENT_VERSION, Sdk.VERSION.LEVEL);

        final Bundle returnBundle = context.getContentResolver()
                .call(Emaillet.CONTENT_URI,
                        Emaillet.Method.IS_SUPPORTED,
                        null,
                        extras);
        return returnBundle != null
                && Result.parse(returnBundle, new Result()).getCode() == Result.RESULT_OK
                && returnBundle.containsKey(Emaillet.IS_SUPPORTED_EXTRA) && returnBundle.getBoolean(Emaillet.IS_SUPPORTED_EXTRA);
    }
}