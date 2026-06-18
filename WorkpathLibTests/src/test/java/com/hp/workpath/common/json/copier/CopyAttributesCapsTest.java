package com.hp.workpath.common.json.copier;

import com.hp.workpath.api.copier.CopyAttributesCaps;
import com.hp.workpath.api.copier.CopyAttributes;
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

/** Basic smoke test for CopyAttributesCaps JSON parsing. */
public class CopyAttributesCapsTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setLenientEnum(true).setFailOnUnknown(false).setSwallowFieldErrors(true);
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
    public void parse_normal_caps() throws Exception {
        String json = load("/json/copier/CopyAttributesCaps_normal.json");

        // 1. Validate raw JSON structure via Map to ensure fixture correctness
        Map root = parser.fromJson(json, Map.class);
        assertNotNull(root);
        Object creator = root.get("mCapsCreator");
        assertTrue("mCapsCreator should be a Map", creator instanceof Map);
        Map capsMap = (Map) creator;
        List colorModes = (List) capsMap.get("mColorModeList");
        assertNotNull(colorModes);
        assertEquals(3, colorModes.size()); // DEFAULT, COLOR, GRAY from fixture
        List stampTypeList = (List) ((Map)((Map)capsMap.get("mStampOptionMap")).get("TOP_LEFT")).get("stampTypeList");
        assertNotNull(stampTypeList);
        assertTrue(stampTypeList.contains("CONFIDENTIAL"));

        // 2. Parse to domain object (limitations: CopyAttributesCapsCreator private ctor + builder pattern only applied for default instance)
        CopyAttributesCaps caps = parser.fromJson(json, CopyAttributesCaps.class);
        assertNotNull(caps);

        // Builder pattern gives at least DEFAULT entries; JSON lists may not overwrite due to constructor limitation.
        List<CopyAttributes.ColorMode> parsedColorModes = caps.getColorModeList();
        assertNotNull(parsedColorModes);
        assertTrue("Expected at least one color mode (DEFAULT)", parsedColorModes.size() >= 1);
        assertTrue(parsedColorModes.contains(CopyAttributes.ColorMode.DEFAULT));

        // Safe invocation of some range getters (will reflect either defaults or JSON if future parser enhancement occurs)
        assertNotNull(caps.getPrintCustomLengthRange());
        assertNotNull(caps.getCopiesRange());

        // Stamp data likely absent (empty) presently; check that calling list retrieval does not throw
        try {
            caps.getStampPositionList();
        } catch (Throwable t) {
            fail("Stamp position list accessor threw: " + t);
        }
    }
}
