package com.hp.workpath.common.gson;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Phase 5: date parsing lenient vs strict.
 */
public class DateParsingTest extends BaseJsonParserTest {
    static class DH {
        Date when;
    }

    @Test
    public void lenientBadDateNull() {
        DH d = parser.fromJson("{\"when\":\"bad-date\"}", DH.class);
        assertNull(d.when);
    }

    @Test
    public void strictBadDateFails() {
        parser.setLenientDate(false);
        try {
            parser.fromJson("{\"when\":\"bad-date\"}", DH.class);
            fail();
        } catch (RuntimeException expected) {
        } finally {
            parser.setLenientDate(true);
        }
    }

    @Test
    public void strictGoodDateParses() {
        parser.setLenientDate(false);
        DH d = parser.fromJson("{\"when\":\"2025-09-01T10:20:30.123Z\"}", DH.class);
        assertNotNull(d.when);
        parser.setLenientDate(true);
    }
}
