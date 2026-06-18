// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import com.hp.workpath.common.mod.mof.Frame;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "mof")
@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance")
public class MofObject {

    @Attribute(name = "noNamespaceSchemaLocation")
    private String noNamespaceSchemaLocation;

    @Attribute(name = "schemaVersion")
    private String schemaVersion;

    @ElementList(inline = true)
    private List<Frame> frame;

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public List<Frame> getFrame() {
        return frame;
    }
}
