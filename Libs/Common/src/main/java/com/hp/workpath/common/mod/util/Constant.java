// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.util;

import android.net.Uri;

public class Constant {
    public static final String TAG = "AMF";

    public static final String SELECTED = "selected";
    public static final String TEXT = "text";
    public static final String HINT = "hint";
    public static final String TEXT_COLOR = "textColor";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BACKGROUND_IMAGE = "backgroundImage";
    public static final String IMAGE = "image";
    public static final String VISIBILITY = "visibility";

//    <Custom mode>
//    public static final String ONCLICK = "onClick";
//    public static final String SOUND = "sound";
//    public static final String MSG = "msg";

//    <With Linkit>
//    public static final String ITEM_COLOR = "itemColor";
//    public static final String ITEM_DRAWABLE = "itemDrawable";

    public static final String RED = "red";

    public static final Uri PACMAN_FILE_CONTENT_URI = new Uri.Builder()
            .scheme("content").authority("com.hp.modmanager").appendPath("mods").build();

    public static final Uri PACMAN_MOD_PERMISSION_CONTENT_URI = new Uri.Builder()
            .scheme("content").authority("com.hp.modmanager.provider.permission").build();

}
