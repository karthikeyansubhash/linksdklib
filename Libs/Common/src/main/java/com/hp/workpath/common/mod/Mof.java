// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod;

import android.content.Context;

import com.hp.workpath.common.mod.mof.MofObject;
import com.hp.workpath.common.mod.parser.MofParser;

public class Mof {
    private static Mof instance;
    private MofObject mofObject;

    private Mof() {

    }

    public static Mof getInstance() {
        if (instance == null) {
            synchronized (Mof.class) {
                if (instance == null) {
                    instance = new Mof();
                }
            }
        }
        return instance;
    }

    public void initialize(Context context) throws Exception {
        if (mofObject == null) {
            MofParser mofParser = new MofParser(context);
            mofObject = mofParser.getMofObject();
        }
    }

    public MofObject getMofObject() {
        return mofObject;
    }
}
