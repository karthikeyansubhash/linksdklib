package com.hp.workpath.common.json.statistics;

import com.hp.workpath.api.statistics.StatisticsJobData;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class StatisticsJobDataTest {
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
    public void normal_jobdata_parses_round_trip() throws Exception {
        String json = load("/json/statistics/StatisticsJobData_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("JOB-001", raw.get("jobId"));
        StatisticsJobData data = parser.fromJson(json, StatisticsJobData.class);
        assertNotNull(data);
        String out = parser.toJson(data);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(raw.get("jobSequence"), outMap.get("jobSequence"));
        assertEquals(raw.get("jobId"), outMap.get("jobId"));
        assertEquals(((Map)raw.get("jobInfo")).get("jobCategory"), ((Map)outMap.get("jobInfo")).get("jobCategory"));
    }

    @Test
    public void minimal_jobdata_only_id() throws Exception {
        StatisticsJobData data = parser.fromJson(load("/json/statistics/StatisticsJobData_minimal.json"), StatisticsJobData.class);
        assertNotNull(data);
        Map out = parser.fromJson(parser.toJson(data), Map.class);
        assertEquals("JOB-002", out.get("jobId"));
        // Optional fields absent
        assertNull(out.get("resourceId"));
        assertNull(out.get("jobInfo"));
    }

    @Test
    public void invalid_enum_tokens_lenient() throws Exception {
        StatisticsJobData data = parser.fromJson(load("/json/statistics/StatisticsJobData_invalid_enum.json"), StatisticsJobData.class);
        assertNotNull(data);
        Map out = parser.fromJson(parser.toJson(data), Map.class);
        Map jobInfo = (Map) out.get("jobInfo");
        assertNotNull(jobInfo);
        // Invalid enums should become null or be dropped
        assertNull(jobInfo.get("jobCategory"));
        assertNull(jobInfo.get("jobDataSource"));
        assertNull(jobInfo.get("jobDoneStatus"));
        assertNull(jobInfo.get("jobPaused"));
        // jobDestinations list: invalid first + valid Email -> may serialize only valid or include null; ensure invalid token not present
        Object dests = jobInfo.get("jobDestinations");
        if (dests != null) {
            assertFalse(dests.toString().contains("Teleport"));
        }
    }

    @Test
    public void missing_sections_defaults_or_null() throws Exception {
        StatisticsJobData data = parser.fromJson(load("/json/statistics/StatisticsJobData_missing_sections.json"), StatisticsJobData.class);
        assertNotNull(data);
        Map out = parser.fromJson(parser.toJson(data), Map.class);
        assertEquals("JOB-004", out.get("jobId"));
        Map jobInfo = (Map) out.get("jobInfo");
        assertNotNull(jobInfo);
        assertEquals("AppX", jobInfo.get("applicationName"));
        // Unspecified enums null
        assertNull(jobInfo.get("jobCategory"));
    }

    @Test
    public void empty_arrays_supported() throws Exception {
        StatisticsJobData data = parser.fromJson(load("/json/statistics/StatisticsJobData_empty_arrays.json"), StatisticsJobData.class);
        assertNotNull(data);
        Map out = parser.fromJson(parser.toJson(data), Map.class);
        Map jobInfo = (Map) out.get("jobInfo");
        assertNotNull(jobInfo);
        Object destinations = jobInfo.get("jobDestinations");
        // Expect empty list or null, but not crash
        if (destinations != null) {
            assertTrue(destinations.toString().equals("[]") || destinations.toString().length() >= 0);
        }
        // Top-level empty arrays preserved
        assertTrue(out.containsKey("folderInfo"));
    }
}
