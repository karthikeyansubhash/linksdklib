package com.hp.workpath.common.json.access;

import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.api.access.DeviceToken;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class DeviceTokenTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setDetectCycles(true)
                .setFailOnUnknown(false)
                .setLenientNumbers(true)
                .setLenientEnum(true)
                .setLenientDate(true);
    }

    @Test public void parse_normal() throws Exception {
        Map obj = parse("/json/access/DeviceToken_normal.json");
        assertEquals("test-device-token_1q2w3e4r5t6y7u8i9o0", obj.get("deviceToken"));
        assertNotNull(obj.get("deviceToken"));
    }

    @Test public void parse_nulls() throws Exception {
        Map obj = parse("/json/access/DeviceToken_null.json");
        assertTrue(obj.containsKey("deviceToken"));
    }

    @Test public void parse_empty() throws Exception {
        Map obj = parse("/json/access/DeviceToken_empty.json");
        assertEquals("", obj.get("deviceToken"));
    }

    @Test public void parse_object_roundTrip() throws Exception {
        String json = load("/json/access/DeviceToken_normal.json");
        DeviceToken dt = parser.fromJson(json, DeviceToken.class);
        assertNotNull(dt);
        assertEquals("test-device-token_1q2w3e4r5t6y7u8i9o0", dt.getDeviceToken());
        String out = parser.toJson(dt);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals("test-device-token_1q2w3e4r5t6y7u8i9o0", outMap.get("code"));
    }

    @Test public void parse_alternate_code_key() throws Exception {
        String json = load("/json/access/DeviceToken_code_legacy.json");
        DeviceToken dt = parser.fromJson(json, DeviceToken.class);
        assertNotNull(dt);
        assertEquals("legacy-code-token_123", dt.getDeviceToken());
    }

    private Map parse(String res) throws Exception {
        String json = load(res);
        return parser.fromJson(json, Map.class);
    }

    private String load(String path) throws Exception {
        InputStream in = getClass().getResourceAsStream(path);
        assertNotNull("Missing resource: " + path, in);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder(); String line; while ((line = br.readLine()) != null) sb.append(line).append('\n'); return sb.toString();
        }
    }

    @Test public void deviceToken_roundTrip_object() throws Exception {
        String json = load("/json/access/DeviceToken_normal.json");
        DeviceToken tok = parser.fromJson(json, DeviceToken.class);
        assertNotNull(tok);
        assertEquals("test-device-token_1q2w3e4r5t6y7u8i9o0", tok.getDeviceToken());
        String out = parser.toJson(tok);
        assertNotNull(out);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals("test-device-token_1q2w3e4r5t6y7u8i9o0", outMap.get("code"));
    }
}
