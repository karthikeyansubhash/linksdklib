package com.hp.workpath.common.gson;

import com.hp.workpath.common.utils.json.EmptyStringAsNull;
import com.hp.workpath.common.utils.json.SerializedName;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Phase 3: enum + @SerializedName + @EmptyStringAsNull behavior.
 */
public class EnumAndAnnotationTest extends BaseJsonParserTest {
    enum Mode {@SerializedName(value = "ON", alternate = {"on", "1"}) ON, @SerializedName(value = "OFF", alternate = {"off", "0"}) OFF}

    static class Holder {
        Mode mode;
        @SerializedName(value = "main_code", alternate = {"code", "deviceToken"})
        String code;
        @EmptyStringAsNull
        String note;
    }

    @Test
    public void enumPrimary() {
        Holder h = parser.fromJson("{\"mode\":\"ON\"}", Holder.class);
        assertEquals(Mode.ON, h.mode);
    }

    @Test
    public void enumAlternate() {
        Holder h = parser.fromJson("{\"mode\":\"off\"}", Holder.class);
        assertEquals(Mode.OFF, h.mode);
    }

    @Test
    public void fieldSerializedNamePrimary() {
        Holder h = parser.fromJson("{\"main_code\":\"abc\"}", Holder.class);
        assertEquals("abc", h.code);
    }

    @Test
    public void fieldSerializedNameAlternate() {
        Holder h = parser.fromJson("{\"deviceToken\":\"xyz\"}", Holder.class);
        assertEquals("xyz", h.code);
    }

    @Test
    public void emptyStringAsNull() {
        Holder h = parser.fromJson("{\"note\":\"\"}", Holder.class);
        assertNull(h.note);
    }

    @Test
    public void serializeUsesPrimaryName() {
        Holder h = new Holder();
        h.code = "v1";
        String json = parser.toJson(h);
        assertTrue(json.contains("\"main_code\":\"v1\""));
    }

    @Test
    public void enumUnknownLenientNull() {
        Holder h = parser.fromJson("{\"mode\":\"UNKNOWN\"}", Holder.class);
        // lenientEnum 기본 true -> null
        assertNull(h.mode);
    }

    @Test
    public void enumUnknownStrictThrows() {
    parser.setLenientEnum(false);
    parser.setSwallowFieldErrors(false); // ensure enum error surfaces
        try {
            parser.fromJson("{\"mode\":\"UNKNOWN\"}", Holder.class);
            org.junit.Assert.fail();
    } catch (RuntimeException expected) { } finally { parser.setLenientEnum(true); parser.setSwallowFieldErrors(true); }
    }
}
