package com.hp.workpath.common.json.authorization;

import com.hp.workpath.api.authorization.AuthenticationType;
import com.hp.workpath.api.authorization.UserAuthorizationData;
import com.hp.workpath.api.authorization.UserOverrides;
import com.hp.workpath.common.utils.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class UserAuthorizationDataTest {
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
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void userAuth_normal_roundTrip() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_normal.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        assertNotNull(d);
        assertNotNull(d.getAuthenticatedUserInfo());
        // JSON contains "CORP\\jdoe" which becomes a single backslash in the Java String
        assertEquals("CORP\\jdoe", d.getAuthenticatedUserInfo().getFullyQualifiedUserName());
        assertEquals(AuthenticationType.WINDOWS, d.getAuthenticatedUserInfo().getAuthenticationType());
        assertEquals("jdoe", d.getAuthenticatedUserInfo().getUserName());
        assertEquals("agent-42", d.getAuthenticationAgentId());
        assertEquals("SmartCardReader-A", d.getAuthenticationAgentName());
        assertNotNull(d.getAuthenticatedUserOverrides());
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.mock.signature", d.getOpaqueAuthenticationToken());
        assertNotNull(d.getTimestamp());
        assertEquals("2025-09-02T12:34:56.789Z", d.getTimestamp().getTime());

        // Round trip basic stable fields
        String out = parser.toJson(d);
        Map inMap = parser.fromJson(json, Map.class);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.get("authenticationAgentId"), outMap.get("authenticationAgentId"));
        assertEquals(inMap.get("opaqueAuthenticationToken"), outMap.get("opaqueAuthenticationToken"));
    }

    @Test
    public void userAuth_invalid_enum_lenient() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_invalid_enum.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        // lenientEnum=true -> unknown enum becomes null
        assertNotNull(d);
        assertNotNull(d.getAuthenticatedUserInfo());
        assertNull(d.getAuthenticatedUserInfo().getAuthenticationType());
    }

    @Test
    public void userAuth_invalid_enum_strict_failure() throws Exception {
        parser.setLenientEnum(false);
        parser.setSwallowFieldErrors(false); // ensure field error propagates
        String json = load("/json/authorization/UserAuthorizationData_invalid_enum.json");
        try {
            parser.fromJson(json, UserAuthorizationData.class);
            fail("Expected enum failure in strict mode");
        } catch (RuntimeException expected) { /* expected */ } finally {
            parser.setLenientEnum(true);
            parser.setSwallowFieldErrors(true);
        }
    }

    @Test
    public void userAuth_missing_blocks() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_missing_required_blocks.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        // Missing nested objects -> remain null
        assertNotNull(d);
        assertNull(d.getAuthenticatedUserInfo());
        assertNull(d.getTimestamp());
        assertEquals("agent-only", d.getAuthenticationAgentId());
        assertEquals("tok123", d.getOpaqueAuthenticationToken());
    }

    @Test
    public void userAuth_nullables_and_empty_lists() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_nullables_and_empty_lists.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        assertNotNull(d);
        assertNotNull(d.getAuthenticatedUserInfo());
        assertNull(d.getAuthenticatedUserInfo().getFullyQualifiedUserName());
        assertEquals("", d.getAuthenticatedUserInfo().getUserName());
        assertNull(d.getAuthenticationAgentId());
        assertEquals("", d.getAuthenticationAgentName());
        assertNotNull(d.getAuthenticatedUserOverrides());
        UserOverrides uo = d.getAuthenticatedUserOverrides();
        assertNotNull(uo.getTo());
        assertEquals(0, uo.getTo().size());
        assertEquals(0, uo.getCc().size());
        assertEquals(0, uo.getBcc().size());
        assertNull(uo.getFrom());
        assertNull(uo.getSubject());
        assertEquals("", uo.getMessage());
        assertNull(uo.getFaxCompanyName());
        assertEquals("", uo.getFaxBillingCode());
        assertNotNull(d.getTimestamp());
        assertNull(d.getTimestamp().getTime());
    }

    @Test
    public void userAuth_bad_timestamp_lenient() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_bad_timestamp.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        // lenientDate=true -> date parsing failure yields null time in Timestamp object OR entire timestamp if object provided? Here timestamp exists with bad time -> field remains raw string because Timestamp.time is String (no date parsing) -> we only assert structure
        assertNotNull(d);
        assertNotNull(d.getTimestamp());
        assertEquals("bad-date-format", d.getTimestamp().getTime()); // Timestamp.time is plain String (no validation in parser because it's not Date type)
    }

    @Test
    public void userAuth_deep_overrides() throws Exception {
        String json = load("/json/authorization/UserAuthorizationData_deep_overrides.json");
        UserAuthorizationData d = parser.fromJson(json, UserAuthorizationData.class);
        assertNotNull(d);
        assertNotNull(d.getAuthenticatedUserOverrides());
        UserOverrides uo = d.getAuthenticatedUserOverrides();
        assertEquals(1, uo.getTo().size());
        assertEquals("a@example.com", uo.getTo().get(0).getAddress());
        assertEquals("C1", uo.getCc().get(0).getName());
        assertEquals("b1@example.com", uo.getBcc().get(0).getAddress());
        assertEquals("from@example.com", uo.getFrom().getAddress());
        assertEquals("Subj", uo.getSubject());
        assertEquals("Msg", uo.getMessage());
        assertEquals("Co", uo.getFaxCompanyName());
        assertNull(uo.getFaxBillingCode());
    }

    @Test
    public void userAuth_invalid_enum_and_bad_timestamp_strict_combo() throws Exception {
        // Combine strict enum + timestamp unaffected (timestamp uses String field, not Date)
        parser.setLenientEnum(false);
        parser.setSwallowFieldErrors(false);
        String json = load("/json/authorization/UserAuthorizationData_invalid_enum.json");
        try {
            parser.fromJson(json, UserAuthorizationData.class);
            fail("Expected enum failure");
        } catch (RuntimeException expected) { /* expected */ } finally {
            parser.setLenientEnum(true);
            parser.setSwallowFieldErrors(true);
        }
    }
}
