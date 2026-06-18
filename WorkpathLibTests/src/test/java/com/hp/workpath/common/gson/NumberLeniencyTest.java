package com.hp.workpath.common.gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Phase 4: lenientNumbers true/false behavior.
 */
public class NumberLeniencyTest extends BaseJsonParserTest {
    static class Num {
        int i;
        double d;
    }

    @Test
    public void lenientNumbersAcceptQuoted() {
        Num n = parser.fromJson("{\"i\":\"10\",\"d\":\"2.5\"}", Num.class);
        assertEquals(10, n.i);
        assertEquals(2.5, n.d, 0.0001);
    }

    @Test
    public void strictNumbersRejectQuoted() {
        parser.setLenientNumbers(false);
        parser.setSwallowFieldErrors(false); // ensure number format error surfaces
        try {
            parser.fromJson("{\"i\":\"7\"}", Num.class);
            fail(); // we expect a runtime error because quoted number not allowed now
        } catch (RuntimeException expected) {
            // expected path
        } finally {
            parser.setLenientNumbers(true);
            parser.setSwallowFieldErrors(true);
        }
    }

    @Test
    public void strictNumbersQuotedSwallowedWhenAllowed() {
        // Show legacy behavior: with swallowFieldErrors=true the invalid quoted number is ignored silently.
        parser.setLenientNumbers(false);
        parser.setSwallowFieldErrors(true);
        Num n = parser.fromJson("{\"i\":\"7\"}", Num.class); // error swallowed, default 0 remains
        assertEquals(0, n.i);
        parser.setLenientNumbers(true);
    }
}
