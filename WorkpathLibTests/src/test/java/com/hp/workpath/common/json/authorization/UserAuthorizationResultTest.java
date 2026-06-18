package com.hp.workpath.common.json.authorization;

import com.hp.workpath.api.authorization.UserAuthorizationResult;
import com.hp.workpath.api.authorization.UserOverrides;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UserAuthorizationResultTest {
    private JsonParser parser;
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

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
    public void result_normal_roundTrip() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_normal.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        List<String> perms = r.getUserPermissionSet();
        assertNotNull(perms);
        assertEquals(4, perms.size());
        perms.forEach(p -> assertTrue("Expected UUID format: " + p, UUID_PATTERN.matcher(p).matches()));
        assertNotNull(r.getAuthorizedUserOverrides());
        UserOverrides uo = r.getAuthorizedUserOverrides();
        assertEquals(2, uo.getTo().size());
        assertEquals("Authorized Access Notice", uo.getSubject());

        // Round trip stable fields
        String out = parser.toJson(r);
        Map inMap = parser.fromJson(json, Map.class);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.get("userPermissionSet"), outMap.get("userPermissionSet"));
    }

    @Test
    public void result_empty_permissions() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_empty_permissions.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        assertNotNull(r.getUserPermissionSet());
        assertEquals(0, r.getUserPermissionSet().size());
        assertNotNull(r.getAuthorizedUserOverrides());
        assertEquals("No Permissions", r.getAuthorizedUserOverrides().getSubject());
    }

    @Test
    public void result_null_overrides() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_null_overrides.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        assertNotNull(r.getUserPermissionSet());
        assertEquals(2, r.getUserPermissionSet().size());
        r.getUserPermissionSet().forEach(p -> assertTrue(UUID_PATTERN.matcher(p).matches()));
        assertNull(r.getAuthorizedUserOverrides());
    }

    @Test
    public void result_missing_fields() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_missing_fields.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        assertEquals(1, r.getUserPermissionSet().size());
        assertNull(r.getAuthorizedUserOverrides());
    }

    @Test
    public void result_invalid_uuid_detection() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_invalid_uuid.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        List<String> perms = r.getUserPermissionSet();
        assertEquals(3, perms.size());
        long validCount = perms.stream().filter(p -> UUID_PATTERN.matcher(p).matches()).count();
        assertEquals(0, validCount); // all invalid in this file
        assertNotNull(r.getAuthorizedUserOverrides());
        assertEquals("Invalid UUID samples", r.getAuthorizedUserOverrides().getMessage());
    }

    @Test
    public void result_duplicate_permissions_present() throws Exception {
        String json = load("/json/authorization/UserAuthorizationResult_duplicate_permissions.json");
        UserAuthorizationResult r = parser.fromJson(json, UserAuthorizationResult.class);
        assertNotNull(r);
        List<String> perms = r.getUserPermissionSet();
        assertEquals(3, perms.size()); // duplicates retained (parser does not de-duplicate)
        Map<String, Integer> freq = new LinkedHashMap<>();
        for (String p : perms) freq.put(p, freq.getOrDefault(p, 0) + 1);
        boolean hasDup = freq.values().stream().anyMatch(v -> v > 1);
        assertTrue(hasDup);
        assertNotNull(r.getAuthorizedUserOverrides());
        assertEquals("Duplicate Permissions", r.getAuthorizedUserOverrides().getSubject());
    }
}
