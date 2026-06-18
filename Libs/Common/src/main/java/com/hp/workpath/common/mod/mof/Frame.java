// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import com.hp.workpath.common.mod.mof.Fidget;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "frame")
public class Frame {

    @Attribute(name = "id")
    private String id;

    @ElementList(name = "fidget", inline = true)
    private List<Fidget> fidgets;

    public String getId() {
        return id;
    }

    public List<Fidget> getFidgets() {
        return fidgets;
    }
}
