package com.hp.workpath.common.gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Phase 8: instantiation fallback behaviors.
 */
public class InstantiationFallbackTest extends BaseJsonParserTest {
    static class NoDefaultCtor {
        final int v;

        NoDefaultCtor(int v) {
            this.v = v;
        }

        String name;
    }

    static class SingleParcelCtor {
        final Object dummy;

        SingleParcelCtor(android.os.Parcel p) {
            this.dummy = null;
        }

        int id;
    }

    @Test
    public void noDefaultCtor_allocationFallback() {
        NoDefaultCtor n = parser.fromJson("{\"name\":\"ok\"}", NoDefaultCtor.class);
        assertEquals("ok", n.name);
    }

    // Parcel class may not exist on host JVM; test defensively
    @Test
    public void singleParcelCtor_skipIfAndroidAbsent() {
        try {
            Class.forName("android.os.Parcel");
        } catch (Throwable ignore) {
            return;
        }
        SingleParcelCtor s = parser.fromJson("{\"id\":5}", SingleParcelCtor.class);
        assertEquals(5, s.id);
    }
}
