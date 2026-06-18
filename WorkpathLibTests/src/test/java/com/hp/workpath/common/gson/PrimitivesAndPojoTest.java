package com.hp.workpath.common.gson;

import com.hp.workpath.common.utils.JsonParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Phase 1: primitives + simple POJO round-trip tests (Gson-inspired scope; only supported features).
 */
public class PrimitivesAndPojoTest {
    private JsonParser parser;

    @Before
    public void setup() {
        parser = JsonParser.getInstance();
        reset();
    }

    @After
    public void tearDown() {
        reset();
    }

    private void reset() {
        parser.setDetectCycles(true).setFailOnUnknown(false).setLenientNumbers(true)
                .setLenientEnum(true).setLenientDate(true).setSwallowFieldErrors(true)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setDateTimeZone(TimeZone.getTimeZone("UTC"));
    }

    static class Basic {
        int i;
        String s;
        boolean b;
    }

    @Test
    public void primitiveFieldDeserialization() {
        Basic v = parser.fromJson("{\"i\":5,\"s\":\"x\",\"b\":true}", Basic.class);
        assertEquals(5, v.i);
        assertEquals("x", v.s);
        assertTrue(v.b);
    }

    @Test
    public void primitiveRoundTrip() {
        Basic v = new Basic();
        v.i = 9;
        v.s = "hello";
        v.b = false;
        String json = parser.toJson(v);
        assertTrue(json.contains("\"i\":9"));
        assertTrue(json.contains("\"s\":\"hello\""));
        assertTrue(json.contains("\"b\":false"));
        Basic back = parser.fromJson(json, Basic.class);
        assertEquals(9, back.i);
        assertEquals("hello", back.s);
        assertFalse(back.b);
    }

    @Test
    public void serializeNullOmittedByDefault() {
        Basic v = new Basic();
        v.i = 1;
        v.s = null;
        v.b = true;
        String json = parser.toJson(v);
        assertFalse(json.contains("\"s\""));
    }

    @Test
    public void serializeNullIncludedWhenEnabled() {
        parser.setSerializeNulls(true);
        Basic v = new Basic();
        v.i = 2;
        v.s = null;
        String json = parser.toJson(v);
        assertTrue(json.contains("\"s\":null"));
    }
}
