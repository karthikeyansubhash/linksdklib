// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.parser;

import android.content.Context;

import com.hp.workpath.common.mod.mof.MofObject;
import com.hp.workpath.common.mod.util.Utils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.File;
import java.io.IOException;

public class MofParser {
    private static final Serializer SERIALIZER = new Persister(new AnnotationStrategy(),
            new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" standalone=\"yes\"?>"));
    private static final String XML_FILENAME = "app.xml";
    private static final String FILE_NAME = "mof/" + XML_FILENAME;

    private MofObject mofObject;

    public MofParser(Context context) throws Exception {
        File mofFile = Utils.getFile(context, FILE_NAME);
        open(mofFile);
    }

    public MofObject getMofObject() {
        return mofObject;
    }

    private void open(File mofFile) throws IOException, SecurityException {
        if (mofFile.isFile() && mofFile.exists()) {
            try {
                mofObject = SERIALIZER.read(MofObject.class, mofFile);
            } catch (Exception e) {
                throw new IOException("Failed to parse " + XML_FILENAME, e);
            }
        } else {
            throw new IOException("File " + mofFile.getAbsolutePath() + " is missing");
        }
    }
}
