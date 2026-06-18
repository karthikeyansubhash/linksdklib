// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "prop")
public class Prop {

    @Attribute(name = "key")
    private String key;

    @Attribute(name = "type", required = false, empty = "STR")
    private PropType type;

    @Attribute(name = "value")
    private String value;

    @Attribute(name = "editable", required = false)
    private boolean editable;

    @Element(required = false)
    private Descr descr;

    @Element(name = "l10n_value", required = false)
    private L10nValue l10nValue;

    public String getKey() {
        return key;
    }

    public PropType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isEditable() {
        return editable;
    }

    public Descr getDescr() {
        return descr;
    }

    public L10nValue getL10nValue() {
        return l10nValue;
    }
}
