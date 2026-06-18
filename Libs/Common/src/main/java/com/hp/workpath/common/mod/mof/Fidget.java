// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "fidget")
public class Fidget {

    @Attribute(name = "id")
    private String id;

    @Attribute(name = "type")
    private FidgetType type;

    @Attribute(name = "include", required = false)
    private String include;

    @ElementList(name = "prop", required = false, inline = true)
    private List<Prop> props;

    @Element(required = false)
    private Descr descr;

    @Element(required = false)
    private Data data;

    public String getId() {
        return id;
    }

    public FidgetType getType() {
        return type;
    }

    public String getInclude() {
        return include;
    }

    public List<Prop> getProps() {
        return props;
    }

    public Descr getDescr() {
        return descr;
    }

    public Data getData() {
        return data;
    }
}
