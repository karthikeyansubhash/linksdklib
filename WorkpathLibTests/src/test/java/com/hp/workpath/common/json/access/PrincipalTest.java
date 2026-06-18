package com.hp.workpath.common.json.access;

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

public class PrincipalTest {
    private JsonParser parser;
    @Before public void init() { parser = JsonParser.getInstance(); }

    @Test public void principal_normal() throws Exception {
        Map m = parse("/json/access/Principal_normal.json");
        assertEquals("Jane Doe", m.get("username"));
        assertTrue(((List) m.get("simpleAuthorities")).size() >= 2);
    }

    @Test public void principal_missingOptional() throws Exception {
        Map m = parse("/json/access/Principal_missing_optional.json");
        assertFalse(m.containsKey("displayName"));
    }

    @Test public void principal_emptyAuthorities() throws Exception {
        Map m = parse("/json/access/Principal_empty_authorities.json");
        assertTrue(((List) m.get("simpleAuthorities")).isEmpty());
    }

    @Test public void principal_unknownAuthority() throws Exception {
        Map m = parse("/json/access/Principal_unknown_authority.json");
        List list = (List) m.get("simpleAuthorities");
        assertFalse(list.contains("ADMIN"));
    }

    @Test public void principal_nullProperties() throws Exception {
        Map m = parse("/json/access/Principal_null_properties.json");
        assertNull(m.get("userProperties"));
    }

    @Test public void principal_minimal() throws Exception {
        Map m = parse("/json/access/Principal_minimal.json");
        assertEquals("userX", m.get("username"));
    }

    private Map parse(String res) throws Exception { return parser.fromJson(load(res), Map.class); }

    private String load(String path) throws Exception {
        InputStream in = getClass().getResourceAsStream(path); assertNotNull("Missing resource: " + path, in);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder(); String line; while ((line = br.readLine()) != null) sb.append(line).append('\n'); return sb.toString(); }
    }
}
