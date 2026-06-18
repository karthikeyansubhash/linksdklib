// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.mod.mof;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.Map;

@Root(name = "descr")
public class Descr {

    @ElementMap(entry="locale", value="key", attribute=true, inline=true)
    private Map<String, String> value;

    public Map<String, String> getValue() {
        return value;
    }
}
