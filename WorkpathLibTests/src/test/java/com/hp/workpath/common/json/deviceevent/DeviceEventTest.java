package com.hp.workpath.common.json.deviceevent;

import com.hp.workpath.api.device.events.DeviceEvent;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class DeviceEventTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
    // Configure similarly to other tests (enum/date leniency, allow unknowns, no swallowing field errors)
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
                String line; while((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void normal_event_parses() throws Exception {
        String json = load("/json/deviceevent/DeviceEvent_normal.json");
        DeviceEvent ev = parser.fromJson(json, DeviceEvent.class);
        assertNotNull(ev);
        assertEquals("evt-12345", ev.getInstanceId());
        assertEquals("Error", ev.getSeverity());
        assertEquals("Print", ev.getCategory());
        assertEquals("Add", ev.getStateChangeType());
        assertEquals("Paper Jam in Tray 2", ev.getTitle());
        assertEquals("PJ-002", ev.getEventCode());
        assertNotNull(ev.getTimestamp());
        assertEquals("2025-09-02T12:34:56Z", ev.getTimestamp().getTime());
        assertNotNull(ev.getDetails());
        assertEquals(2, ev.getDetails().length);
        assertEquals("https://support.example.com/device/events/PJ-002", ev.getSupportInformationLink());
    }

    @Test
    public void missing_optional_eventCode_and_link() throws Exception {
        String json = load("/json/deviceevent/DeviceEvent_missing_optional.json");
        DeviceEvent ev = parser.fromJson(json, DeviceEvent.class);
        assertNotNull(ev);
        assertEquals("evt-12346", ev.getInstanceId());
        assertEquals("Warning", ev.getSeverity());
        assertNull("eventCode should be null when omitted", ev.getEventCode());
        assertNull("supportInformationLink should be null when omitted", ev.getSupportInformationLink());
        assertNotNull(ev.getTimestamp());
        assertEquals(330, ev.getTimestamp().getOffset());
    }

    @Test
    public void empty_details_array() throws Exception {
        String json = load("/json/deviceevent/DeviceEvent_empty_details.json");
        DeviceEvent ev = parser.fromJson(json, DeviceEvent.class);
        assertNotNull(ev);
        assertNotNull(ev.getDetails());
        assertEquals(0, ev.getDetails().length);
    }

    @Test
    public void invalid_severity_string_retained() throws Exception {
        String json = load("/json/deviceevent/DeviceEvent_invalid_severity.json");
        DeviceEvent ev = parser.fromJson(json, DeviceEvent.class);
        // Since severity is plain String (no enum), parser keeps the raw invalid value
        assertEquals("Severe", ev.getSeverity());
    }

    @Test
    public void round_trip_subset() throws Exception {
        String json = load("/json/deviceevent/DeviceEvent_normal.json");
        DeviceEvent ev = parser.fromJson(json, DeviceEvent.class);
        String out = parser.toJson(ev);
        Map inMap = parser.fromJson(json, Map.class);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.get("instanceId"), outMap.get("instanceId"));
        assertEquals(inMap.get("severity"), outMap.get("severity"));
        assertEquals(inMap.get("category"), outMap.get("category"));
        assertEquals(inMap.get("stateChangeType"), outMap.get("stateChangeType"));
    }
}
