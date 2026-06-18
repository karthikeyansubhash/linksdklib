package com.hp.workpath.common.gson;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Phase 6: custom serializer (deserializer skipped due to internal JsonValue visibility).
 */
public class CustomAdapterTest extends BaseJsonParserTest {
    static class Point {
        int x;
        int y;
    }

    @Test
    public void customSerializer() {
        parser.registerSerializer(Point.class, (p, out, ctx) -> out.append('{').append("\"p\":\"" + p.x + "," + p.y + "\"}"));
        Point pt = new Point();
        pt.x = 3;
        pt.y = 4;
        String json = parser.toJson(pt);
        assertTrue(json.contains("\"p\":\"3,4\""));
    }
}
