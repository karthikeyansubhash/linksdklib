package com.hp.workpath.common.json.webservices;

import com.hp.workpath.api.webservices.HttpResponse;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class HttpResponseTest {
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
                StringBuilder sb = new StringBuilder(); String line; while((line = br.readLine())!=null) sb.append(line).append('\n'); return sb.toString();
            }
        }
    }

    @Test
    public void normal_response_round_trip() throws Exception {
        String json = load("/json/webservices/HttpResponse_normal.json");
        HttpResponse resp = parser.fromJson(json, HttpResponse.class);
        assertNotNull(resp);
        assertEquals(200, resp.getStatusCode());
        assertEquals("OK", resp.getStatusDescription());
        assertEquals("GET", resp.getMethod());
        assertNotNull("Header map should not be null after access", resp.getHeader());
        Map out = parser.fromJson(parser.toJson(resp), Map.class);
        assertEquals(200, ((Number)out.get("statusCode")).intValue());
        assertEquals("OK", out.get("statusDescription"));
        assertEquals("GET", out.get("method"));
        assertTrue(((Map)out.get("header")).containsKey("Content-Type"));
    }

    @Test
    public void minimal_response_defaults() throws Exception {
        HttpResponse resp = parser.fromJson(load("/json/webservices/HttpResponse_minimal.json"), HttpResponse.class);
        assertNotNull(resp);
        assertEquals(204, resp.getStatusCode());
        assertNull(resp.getStatusDescription());
        assertNull(resp.getMethod());
        // header lazily initializes to empty map when getHeader called
        assertNotNull(resp.getHeader());
        assertTrue(resp.getHeader().isEmpty());
    }

    @Test
    public void invalid_method_lenient_acceptance() throws Exception {
        HttpResponse resp = parser.fromJson(load("/json/webservices/HttpResponse_invalid_method.json"), HttpResponse.class);
        assertNotNull(resp);
        // Class does no validation; value should be kept as-is
        assertEquals("PATCH", resp.getMethod());
        assertEquals(200, resp.getStatusCode());
    }

    @Test
    public void unusual_status_code_passthrough() throws Exception {
        HttpResponse resp = parser.fromJson(load("/json/webservices/HttpResponse_unusual_status.json"), HttpResponse.class);
        assertNotNull(resp);
        assertEquals(599, resp.getStatusCode());
        assertEquals("Network Connect Timeout Error", resp.getStatusDescription());
        assertEquals("POST", resp.getMethod());
    }

    @Test
    public void body_as_array_supported() throws Exception {
        HttpResponse resp = parser.fromJson(load("/json/webservices/HttpResponse_body_array.json"), HttpResponse.class);
        assertNotNull(resp);
        assertEquals(207, resp.getStatusCode());
        assertEquals("Multi-Status", resp.getStatusDescription());
        assertTrue("Body should be a list/array like structure", resp.getBody() instanceof java.util.List);
    }

    @Test
    public void header_map_never_null_after_get() throws Exception {
        HttpResponse resp = new HttpResponse();
        assertNotNull(resp.getHeader());
        assertTrue(resp.getHeader().isEmpty());
    }
}
