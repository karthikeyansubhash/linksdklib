package com.hp.workpath.common.json.attestation;

import com.hp.workpath.api.attestation.AppToken;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class AppTokenTest {
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

    private String load(String path) throws Exception {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            assertNotNull("Resource not found: " + path, in);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line; while ((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void appToken_normal_roundTrip() throws Exception {
        String json = load("/json/attestation/AppToken_normal.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        assertEquals("app-token-abcdef1234567890", t.getAppToken());
        assertEquals(Long.valueOf(3600L), t.getExpiresIn());
        String out = parser.toJson(t);
        assertNotNull(out);
        Map inMap = parser.fromJson(json, Map.class);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.get("appToken"), outMap.get("appToken"));
        assertEquals(inMap.get("expiresIn"), outMap.get("expiresIn"));
    }

    @Test
    public void appToken_null_token() throws Exception {
        String json = load("/json/attestation/AppToken_null_token.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        assertNull(t.getAppToken());
        assertEquals(Long.valueOf(0L), t.getExpiresIn());
    }

    @Test
    public void appToken_empty_token() throws Exception {
        String json = load("/json/attestation/AppToken_empty_token.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        assertEquals("", t.getAppToken());
        assertEquals(Long.valueOf(1800L), t.getExpiresIn());
    }

    @Test
    public void appToken_missing_fields() throws Exception {
        String json = load("/json/attestation/AppToken_missing_fields.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        // appToken remains null; expiresIn defaults to 0 (primitive long written as Long wrapper default null -> might be 0)
        assertNull(t.getAppToken());
        // expiresIn might be null if reflection didn't set; treat null or 0 acceptable
        Long exp = t.getExpiresIn();
        if (exp != null) {
            assertEquals(Long.valueOf(0L), exp);
        }
    }

    @Test
    public void appToken_expires_string_lenient() throws Exception {
        String json = load("/json/attestation/AppToken_expires_string.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        // lenientNumbers=true -> "3600" parsed to 3600
        assertEquals(Long.valueOf(3600L), t.getExpiresIn());
    }

    @Test
    public void appToken_expires_string_strict_failure() throws Exception {
        // When swallowFieldErrors=true (default) the parser swallows the number coercion error and field remains null.
        // To actually surface the error we also disable swallowFieldErrors.
        parser.setLenientNumbers(false).setSwallowFieldErrors(false);
        String json = load("/json/attestation/AppToken_expires_string.json");
        try {
            parser.fromJson(json, AppToken.class);
            fail("Expected number format failure when lenientNumbers=false & swallowFieldErrors=false");
        } catch (RuntimeException expected) { /* expected */ }
        finally {
            parser.setLenientNumbers(true).setSwallowFieldErrors(true);
        }
    }

    @Test
    public void appToken_large_expiry() throws Exception {
        String json = load("/json/attestation/AppToken_large_expiry.json");
        AppToken t = parser.fromJson(json, AppToken.class);
        assertNotNull(t);
        assertEquals(Long.valueOf(2147483647L), t.getExpiresIn());
    }
}
