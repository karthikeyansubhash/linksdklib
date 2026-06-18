// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;

import com.hp.workpath.common.mod.util.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class Utils {

    public static File getFile(Context context, String fileName) throws Exception {
        InputStream inputStream = getFileStream(context, fileName);
        return createFileFromInputStream(context, inputStream, fileName);
    }

    public static InputStream getFileStream(Context context, String fileName) throws Exception {
        AssetManager am = context.getAssets();
        return am.open(fileName);
    }

    public static File createFileFromInputStream(Context context, InputStream inputStream, String fileName) throws Exception {
        File f = new File(context.getCacheDir() + "/" + fileName);
        File parent = f.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        OutputStream outputStream = new FileOutputStream(f);
        byte buffer[] = new byte[1024];
        int length = 0;

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
        inputStream.close();
        return f;
    }

    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void requestPermission(Context context, String modRid) {
        Bundle bundle = new Bundle();
        bundle.putString("modRid", modRid);
        bundle.putString("packageName", context.getPackageName());
        context.getContentResolver().call(Constant.PACMAN_MOD_PERMISSION_CONTENT_URI, "request", null, bundle);
    }

    public static StateListDrawable convertColorIntoBitmap(Resources res, String color) {
//        Bitmap normalDrawable = BitmapFactory.decodeResource(res, R.drawable.co_list_btn_check_off_nor);
        Bitmap selectDrawable = null;
        Bitmap pressedDrawable = null;
//        if (color.equals(Constant.RED)) {
//            selectDrawable = BitmapFactory.decodeResource(res, R.drawable.co_list_btn_check_on_nor_red);
//            pressedDrawable = BitmapFactory.decodeResource(res, R.drawable.co_list_btn_check_on_pre_red);
//        }
//
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new BitmapDrawable(selectDrawable));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(pressedDrawable));
//
        return stateListDrawable;
    }
}
