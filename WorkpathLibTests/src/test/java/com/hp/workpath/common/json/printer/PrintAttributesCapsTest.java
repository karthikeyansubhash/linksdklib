package com.hp.workpath.common.json.printer;

import com.hp.workpath.api.printer.PrintAttributesCaps;
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

/** Tests for PrintAttributesCaps JSON parsing (without needing the creator in test API). */
public class PrintAttributesCapsTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setLenientEnum(true)
                .setFailOnUnknown(false)
                .setSwallowFieldErrors(false);
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
    public void normal_caps_parses() throws Exception {
        String json = load("/json/printer/PrintAttributesCaps_normal.json");
        // Raw map sanity
        Map root = parser.fromJson(json, Map.class); assertNotNull(root.get("mCapsCreator"));
        PrintAttributesCaps caps = parser.fromJson(json, PrintAttributesCaps.class);
        assertNotNull(caps);
    // Color modes
    List<?> colorModes = caps.getColorModeList();
    assertEquals(4, colorModes.size());
    assertEquals("DEFAULT", colorModes.get(0).toString());
    assertEquals("COLOR", colorModes.get(1).toString());
    assertEquals("MONO", colorModes.get(2).toString());
    assertEquals("AUTO", colorModes.get(3).toString());

    // Duplex (plex) list
    List<?> duplex = caps.getDuplexList();
    assertEquals(4, duplex.size());
    assertEquals("DEFAULT", duplex.get(0).toString());
    assertEquals("NONE", duplex.get(1).toString());
    assertEquals("BOOK", duplex.get(2).toString());
    assertEquals("FLIP", duplex.get(3).toString());

    // Max copies
    assertEquals(32000, caps.getMaxCopies());

    // AutoFit
    List<?> autoFit = caps.getAutoFitList();
    assertEquals(3, autoFit.size());
    assertEquals("DEFAULT", autoFit.get(0).toString());
    assertEquals("TRUE", autoFit.get(1).toString());
    assertEquals("FALSE", autoFit.get(2).toString());

    // Staple modes
    List<?> staple = caps.getStapleModeList();
    assertEquals(3, staple.size());
    assertEquals("DEFAULT", staple.get(0).toString());
    assertEquals("STAPLE", staple.get(1).toString());
    assertEquals("DUAL_LEFT", staple.get(2).toString());

    // Paper sources (sorted with DEFAULT first)
    List<?> paperSources = caps.getPaperSourceList();
    assertEquals(4, paperSources.size());
    assertEquals("DEFAULT", paperSources.get(0).toString());
    assertEquals("AUTO", paperSources.get(1).toString());
    assertEquals("TRAY_1", paperSources.get(2).toString());
    assertEquals("TRAY_2", paperSources.get(3).toString());

    // Paper sizes (sorted -> DEFAULT, LEGAL, LETTER given subset)
    List<?> paperSizes = caps.getPaperSizeList();
    assertEquals(3, paperSizes.size());
    assertEquals("DEFAULT", paperSizes.get(0).toString());
    assertEquals("LEGAL", paperSizes.get(1).toString());
    assertEquals("LETTER", paperSizes.get(2).toString());

    // Paper types (sorted -> DEFAULT, CARD_STOCK, PLAIN)
    List<?> paperTypes = caps.getPaperTypeList();
    assertEquals(3, paperTypes.size());
    assertEquals("DEFAULT", paperTypes.get(0).toString());
    assertEquals("PLAIN", paperTypes.get(1).toString());
    assertEquals("CARD_STOCK", paperTypes.get(2).toString());

    // Document formats
    List<?> docFormats = caps.getDocumentFormatList();
    assertEquals(3, docFormats.size());
    assertEquals("AUTO", docFormats.get(0).toString());
    assertEquals("PDF", docFormats.get(1).toString());
    assertEquals("JPEG", docFormats.get(2).toString());

    // Sources (no DEFAULT member in enum Source)
    List<?> sources = caps.getSourceList();
    assertEquals(4, sources.size());
    assertEquals("STORAGE", sources.get(0).toString());
    assertEquals("HTTP", sources.get(1).toString());
    assertEquals("USB", sources.get(2).toString());
    assertEquals("STREAM", sources.get(3).toString());

    // Collate modes
    List<?> collate = caps.getCollateModeList();
    assertEquals(3, collate.size());
    assertEquals("DEFAULT", collate.get(0).toString());
    assertEquals("COLLATED", collate.get(1).toString());
    assertEquals("UNCOLLATED", collate.get(2).toString());

    // Orientation (order as provided in JSON)
    List<?> orientation = caps.getOrientationList();
    assertEquals(3, orientation.size());
    assertEquals("DEFAULT", orientation.get(0).toString());
    assertEquals("LANDSCAPE", orientation.get(1).toString());
    assertEquals("PORTRAIT", orientation.get(2).toString());

    // Print quality
    List<?> pq = caps.getPrintQualityList();
    assertEquals(4, pq.size());
    assertEquals("DEFAULT", pq.get(0).toString());
    assertEquals("DRAFT", pq.get(1).toString());
    assertEquals("NORMAL", pq.get(2).toString());
    assertEquals("HIGH", pq.get(3).toString());

    // Output bins
    List<?> outputBins = caps.getOutputBinList();
    assertEquals(3, outputBins.size());
    assertEquals("DEFAULT", outputBins.get(0).toString());
    assertEquals("OUTPUT_BIN_1", outputBins.get(1).toString());
    assertEquals("OUTPUT_BIN_2", outputBins.get(2).toString());

    // Finishings
    List<?> finishings = caps.getFinishingsList();
    assertEquals(3, finishings.size());
    assertEquals("DEFAULT", finishings.get(0).toString());
    assertEquals("SADDLE_STITCH", finishings.get(1).toString());
    assertEquals("FOLD", finishings.get(2).toString());
    }

    @Test
    public void empty_lists_still_present() throws Exception {
        PrintAttributesCaps caps = parser.fromJson(load("/json/printer/PrintAttributesCaps_empty_lists.json"), PrintAttributesCaps.class);
        assertNotNull(caps);
        assertEquals(1, caps.getMaxCopies());
        assertEquals(1, caps.getColorModeList().size());
        assertEquals(1, caps.getDuplexList().size());
    }

    @Test
    public void invalid_values_lenient_mapping() throws Exception {
        // With lenient enums, unknown tokens become null entries; we assert sizes and that known DEFAULT remains
        PrintAttributesCaps caps = parser.fromJson(load("/json/printer/PrintAttributesCaps_invalid_values.json"), PrintAttributesCaps.class);
        assertNotNull(caps);
        assertTrue(caps.getColorModeList().size() >= 2);
        assertEquals("DEFAULT", caps.getColorModeList().get(0).toString());
        // Max copies over stated limit should just reflect raw number (no validation layer here)
        assertEquals(99999, caps.getMaxCopies());
    }

    @Test
    public void round_trip_subset() throws Exception {
        String json = load("/json/printer/PrintAttributesCaps_normal.json");
        PrintAttributesCaps caps = parser.fromJson(json, PrintAttributesCaps.class);
        String out = parser.toJson(caps);
        Map inMap = parser.fromJson(json, Map.class); Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.containsKey("mCapsCreator"), outMap.containsKey("mCapsCreator"));
    }
}
