package com.hp.workpath.common.gson;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Phase 7: Error & edge handling.
 */
public class ErrorAndEdgeTest extends BaseJsonParserTest {
    static class Num {
        int i;
    }
    static class DateHolder { java.util.Date when; }
    enum Simple { A }
    static class EnumHolder { Simple e; }

    @Test
    public void malformed_trailingData() {
        try {
            parser.fromJson("{}{}", Object.class);
            fail();
        } catch (RuntimeException expected) {
        }
    }

    @Test
    public void malformed_badEscape() {
        try {
            parser.fromJson("{\"a\":\"\\x\"}", Object.class);
            fail();
        } catch (RuntimeException expected) {
        }
    }

    @Test
    public void malformed_unterminatedString() {
        try {
            parser.fromJson("{\"a\":\"value", Object.class);
            fail();
        } catch (RuntimeException expected) {
        }
    }

    @Test
    public void malformed_unterminatedArray() {
        try {
            parser.fromJson("[1,2", Object.class);
            fail();
        } catch (RuntimeException expected) {
        }
    }

    @Test
    public void failOnUnknown_nested() {
        parser.setFailOnUnknown(true);
        try {
            parser.fromJson("{\"inner\":{\"x\":1,\"unknown\":2}}", Num.class);
            fail();
        } catch (RuntimeException expected) {
        } finally {
            parser.setFailOnUnknown(false);
        }
    }

    @Test
    public void swallowFieldErrorsFalse_number() {
        parser.setSwallowFieldErrors(false);
        try {
            parser.fromJson("{\"i\":\"zz\"}", Num.class);
            fail();
        } catch (RuntimeException expected) {
        } finally {
            parser.setSwallowFieldErrors(true);
        }
    }

    // (4) swallowFieldErrors=false + bad date should throw immediately
    @Test
    public void swallowFieldErrorsFalse_badDate() {
        parser.setSwallowFieldErrors(false);
        parser.setLenientDate(false); // strict date so bad format triggers exception (not null)
        try {
            parser.fromJson("{\"when\":\"bad-date\"}", DateHolder.class);
            fail();
        } catch (RuntimeException expected) {
        } finally {
            parser.setSwallowFieldErrors(true);
            parser.setLenientDate(true);
        }
    }

    // (5) lenientEnum=false unknown enum token throws
    @Test
    public void strictEnumUnknownToken() {
        parser.setLenientEnum(false);
        parser.setSwallowFieldErrors(false); // ensure enum error not swallowed
        try {
            parser.fromJson("{\"e\":\"Z\"}", EnumHolder.class);
            fail();
        } catch (RuntimeException expected) {
        } finally {
            parser.setLenientEnum(true);
            parser.setSwallowFieldErrors(true);
        }
    }
}
