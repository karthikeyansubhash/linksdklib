package com.hp.workpath.common.json.printer;

import com.hp.workpath.api.printer.StatusInfo;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StatusInfoTest {
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
    public void normal_statusinfo_parses_round_trip() throws Exception {
        String json = load("/json/printer/StatusInfo_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        assertEquals("IDLE", info.getStatus().name());
        assertEquals(1, info.getStatusReasons().size());
        assertEquals("NONE", info.getStatusReasons().get(0).name());
        String out = parser.toJson(info);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(raw.get("mStatus"), outMap.get("mStatus"));
        assertEquals(raw.get("mStatusReasons"), outMap.get("mStatusReasons"));
    }

    @Test
    public void processing_with_multiple_reasons() throws Exception {
        String json = load("/json/printer/StatusInfo_all_true.json");
        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        assertEquals(StatusInfo.Status.PROCESSING, info.getStatus());
        List<StatusInfo.StatusReason> reasons = info.getStatusReasons();
        assertNotNull(reasons);
        assertTrue(reasons.contains(StatusInfo.StatusReason.MEDIA_JAM));
        assertTrue(reasons.contains(StatusInfo.StatusReason.TONER_LOW));
        assertTrue(reasons.contains(StatusInfo.StatusReason.DOOR_OPEN));
    }

    @Test
    public void stopped_with_error_reasons() throws Exception {
        String json = load("/json/printer/StatusInfo_all_false.json");
        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        assertEquals(StatusInfo.Status.STOPPED, info.getStatus());
        List<StatusInfo.StatusReason> reasons = info.getStatusReasons();
        assertTrue(reasons.contains(StatusInfo.StatusReason.COVER_OPEN));
        assertTrue(reasons.contains(StatusInfo.StatusReason.MEDIA_EMPTY));
        assertTrue(reasons.contains(StatusInfo.StatusReason.TONER_EMPTY));
    }

    @Test
    public void invalid_enum_tokens_lenient() throws Exception {
        String json = load("/json/printer/StatusInfo_invalid_enum.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("WORKING", raw.get("mStatus"));
        List rawReasons = (List) raw.get("mStatusReasons");
        assertTrue(rawReasons.contains("PAPER_JAM"));
        assertTrue(rawReasons.contains("LOW_TONER"));
        assertTrue(rawReasons.contains("WIDGET_BROKEN"));

        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        // All tokens are invalid -> expect null status
        assertNull("Invalid status token should yield null", info.getStatus());
        List<StatusInfo.StatusReason> reasons = info.getStatusReasons();
        // Accept null list OR empty list OR list of nulls only
        if (reasons != null) {
            assertTrue("Reasons should be empty or only nulls after invalid tokens", reasons.isEmpty() || reasons.stream().allMatch(r -> r == null));
        }
        String out = parser.toJson(info);
        Map outMap = parser.fromJson(out, Map.class);
        // Ensure invalid tokens not serialized
        if (outMap.containsKey("mStatus")) {
            Object v = outMap.get("mStatus");
            assertTrue(v.equals("IDLE") || v.equals("PROCESSING") || v.equals("STOPPED"));
        }
    }

    @Test
    public void missing_fields_defaults() throws Exception {
        String json = load("/json/printer/StatusInfo_missing_fields.json");
        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        // Expect either null status or a valid default; reasons may be null or empty list
        if (info.getStatus() != null) {
            assertTrue(info.getStatus() == StatusInfo.Status.IDLE || info.getStatus() == StatusInfo.Status.PROCESSING || info.getStatus() == StatusInfo.Status.STOPPED);
        }
        // Defensive: if list present ensure only valid enums (null entries allowed in lenient mode)
        List<StatusInfo.StatusReason> reasons = info.getStatusReasons();
        if (reasons != null) {
            for (StatusInfo.StatusReason r : new ArrayList<>(reasons)) {
                if (r != null) {
                    assertTrue(r == StatusInfo.StatusReason.UNKNOWN || r == StatusInfo.StatusReason.OTHER || r == StatusInfo.StatusReason.NONE ||
                            r == StatusInfo.StatusReason.COVER_OPEN || r == StatusInfo.StatusReason.DOOR_OPEN || r == StatusInfo.StatusReason.TONER_EMPTY ||
                            r == StatusInfo.StatusReason.TONER_LOW || r == StatusInfo.StatusReason.MEDIA_EMPTY || r == StatusInfo.StatusReason.MEDIA_LOW ||
                            r == StatusInfo.StatusReason.MEDIA_NEEDED || r == StatusInfo.StatusReason.MEDIA_JAM);
                }
            }
        }
    }
}
