// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class Data {

    @Attribute(name = "key")
    private String key;

    @Attribute(name = "multiValued", required = false)
    private boolean multiValued;

    @ElementList(name = "item", required = false, inline = true)
    private List<Item> items;

    public String getKey() {
        return key;
    }

    public boolean isMultiValued() {
        return multiValued;
    }

    public List<Item> getItems() {
        return items;
    }
}
