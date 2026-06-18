// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "item")
public class Item {

    @Attribute(name = "key")
    private String key;

    @ElementList(name = "prop", required = false, inline = true)
    private List<Prop> props;

    public String getKey() {
        return key;
    }

    public List<Prop> getProps() {
        return props;
    }
}
