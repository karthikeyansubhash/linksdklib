package com.hp.workpath.common.json.scanner;

import com.hp.workpath.api.scanner.StatusInfo;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
                StringBuilder sb = new StringBuilder(); String line; while((line = br.readLine())!=null) sb.append(line).append('\n'); return sb.toString();
            }
        }
    }

    @Test
    public void normal_status_parses_round_trip() throws Exception {
        String json = load("/json/scanner/StatusInfo_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals(true, raw.get("mIsOnline"));
        StatusInfo info = parser.fromJson(json, StatusInfo.class);
        assertNotNull(info);
        String out = parser.toJson(info);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(raw.get("mIsOnline"), outMap.get("mIsOnline"));
        assertEquals(raw.get("mIsBusy"), outMap.get("mIsBusy"));
        assertEquals(raw.get("mIsAdfOutputBinFull"), outMap.get("mIsAdfOutputBinFull"));
        assertEquals(raw.get("mIsPaperInAdf"), outMap.get("mIsPaperInAdf"));
        assertEquals(raw.get("mIsPaperInFlatbed"), outMap.get("mIsPaperInFlatbed"));
    }

    @Test
    public void all_true_status() throws Exception {
        StatusInfo info = parser.fromJson(load("/json/scanner/StatusInfo_all_true.json"), StatusInfo.class);
        assertNotNull(info);
        Map out = parser.fromJson(parser.toJson(info), Map.class);
        assertEquals("TRUE", out.get("mIsAdfOutputBinFull"));
        assertEquals("TRUE", out.get("mIsPaperInAdf"));
        assertEquals("TRUE", out.get("mIsPaperInFlatbed"));
    }

    @Test
    public void all_false_status() throws Exception {
        StatusInfo info = parser.fromJson(load("/json/scanner/StatusInfo_all_false.json"), StatusInfo.class);
        assertNotNull(info);
        Map out = parser.fromJson(parser.toJson(info), Map.class);
        assertEquals("FALSE", out.get("mIsAdfOutputBinFull"));
        assertEquals("FALSE", out.get("mIsPaperInAdf"));
        assertEquals("FALSE", out.get("mIsPaperInFlatbed"));
    }

    @Test
    public void invalid_enum_tokens_lenient() throws Exception {
        StatusInfo info = parser.fromJson(load("/json/scanner/StatusInfo_invalid_enum.json"), StatusInfo.class);
        assertNotNull(info);
        Map out = parser.fromJson(parser.toJson(info), Map.class);
        // Invalid enum tokens should serialize as null or be omitted
        assertNull(out.get("mIsAdfOutputBinFull"));
        assertNull(out.get("mIsPaperInAdf"));
        assertNull(out.get("mIsPaperInFlatbed"));
    }

    @Test
    public void missing_fields_defaults_or_nulls() throws Exception {
        StatusInfo info = parser.fromJson(load("/json/scanner/StatusInfo_missing_fields.json"), StatusInfo.class);
        assertNotNull(info);
        Map out = parser.fromJson(parser.toJson(info), Map.class);
        // Provided only online flag -> others may default false/null
        assertEquals(true, out.get("mIsOnline"));
        // Busy default expected false
        assertTrue(out.get("mIsBusy") == null || Boolean.FALSE.equals(out.get("mIsBusy")));
    }
}
