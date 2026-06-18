package com.hp.workpath.common.json.printer;

import com.hp.workpath.api.printer.TrayInfo;
import com.hp.workpath.api.printer.PrintAttributes;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TrayInfoTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setLenientEnum(true)
                .setFailOnUnknown(false)
                .setSwallowFieldErrors(false)
                .setLenientDate(true);
    }

    private String load(String path) throws Exception {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            assertNotNull("Resource not found: " + path, in);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void normal_trayinfo_parses_round_trip() throws Exception {
        String json = load("/json/printer/TrayInfo_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        TrayInfo info = parser.fromJson(json, TrayInfo.class);
        assertNotNull(info);
        assertEquals("TRAY_1", info.getPaperSource().name());
        assertEquals("A4", info.getPaperSize().name());
        assertEquals("PLAIN", info.getPaperType().name());
        assertEquals("AVAILABLE", info.getStatus().name());
        assertEquals(75, info.getLevel());
        assertEquals(500, info.getCapacity());
        String out = parser.toJson(info);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(raw.get("mPaperSource"), outMap.get("mPaperSource"));
        assertEquals(raw.get("mPaperSize"), outMap.get("mPaperSize"));
        assertEquals(raw.get("mPaperType"), outMap.get("mPaperType"));
        assertEquals(raw.get("mStatus"), outMap.get("mStatus"));
        assertEquals(raw.get("mLevel"), outMap.get("mLevel"));
        assertEquals(raw.get("mCapacity"), outMap.get("mCapacity"));
    }

    @Test
    public void minimal_trayinfo_defaults() throws Exception {
        String json = load("/json/printer/TrayInfo_minimal.json");
        TrayInfo info = parser.fromJson(json, TrayInfo.class);
        assertNotNull(info);
        assertEquals(PrintAttributes.PaperSource.TRAY_2, info.getPaperSource());
        // Missing size/type/status -> expect nulls
        assertNull(info.getPaperSize());
        assertNull(info.getPaperType());
        assertEquals(0, info.getLevel());
        assertEquals(0, info.getCapacity());
    }

    @Test
    public void invalid_enum_tokens_lenient() throws Exception {
        String json = load("/json/printer/TrayInfo_invalid_enum.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("TRAY_Z9", raw.get("mPaperSource"));
        assertEquals("A47", raw.get("mPaperSize"));
        assertEquals("SUPER_GLOSS", raw.get("mPaperType"));
        assertEquals("BROKEN", raw.get("mStatus"));
        TrayInfo info = parser.fromJson(json, TrayInfo.class);
        assertNotNull(info);
        assertNull(info.getPaperSource());
        assertNull(info.getPaperSize());
        assertNull(info.getPaperType());
        assertNull(info.getStatus());
        // Numeric fields still parse
        assertEquals(60, info.getLevel());
        assertEquals(250, info.getCapacity());
        String out = parser.toJson(info);
        Map outMap = parser.fromJson(out, Map.class);
        // Ensure invalid enum fields not serialized or set to null
        if (outMap.containsKey("mPaperSource")) assertNull(outMap.get("mPaperSource"));
        if (outMap.containsKey("mPaperSize")) assertNull(outMap.get("mPaperSize"));
        if (outMap.containsKey("mPaperType")) assertNull(outMap.get("mPaperType"));
        if (outMap.containsKey("mStatus")) assertNull(outMap.get("mStatus"));
    }

    @Test
    public void out_of_range_negative_numbers() throws Exception {
        String json = load("/json/printer/TrayInfo_out_of_range.json");
        TrayInfo info = parser.fromJson(json, TrayInfo.class);
        assertNotNull(info);
        // Negative numbers accepted as-is by JSON -> ensure not positive unexpectedly
        assertTrue(info.getLevel() <= 0);
        assertTrue(info.getCapacity() <= 0);
    }

    @Test
    public void partial_missing_fields() throws Exception {
        String json = load("/json/printer/TrayInfo_partial_missing.json");
        TrayInfo info = parser.fromJson(json, TrayInfo.class);
        assertNotNull(info);
        assertEquals(PrintAttributes.PaperSource.TRAY_4, info.getPaperSource());
        assertNull(info.getPaperSize());
        assertNull(info.getPaperType());
        assertNull(info.getStatus());
        assertEquals(40, info.getLevel());
        assertEquals(0, info.getCapacity());
    }
}
