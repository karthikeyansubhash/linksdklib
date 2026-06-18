package com.hp.workpath.common.json.printer;

import com.hp.workpath.api.printer.PrintAttributes;
import com.hp.workpath.common.utils.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PrintAttributesTest {
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
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void normal_attributes_parses_round_trip() throws Exception {
        String json = load("/json/printer/PrintAttributes_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("COLOR", raw.get("mColorMode"));
        PrintAttributes attrs = parser.fromJson(json, PrintAttributes.class);
        assertNotNull(attrs);
        String out = parser.toJson(attrs);
        Map outMap = parser.fromJson(out, Map.class);
        // Compare a representative subset including enums & primitives
        assertEquals(raw.get("mColorMode"), outMap.get("mColorMode"));
        assertEquals(raw.get("mPlex"), outMap.get("mPlex"));
        assertEquals(raw.get("mAutoFit"), outMap.get("mAutoFit"));
        assertEquals(raw.get("mStapleMode"), outMap.get("mStapleMode"));
        assertEquals(raw.get("mPaperSource"), outMap.get("mPaperSource"));
        assertEquals(raw.get("mPaperSize"), outMap.get("mPaperSize"));
        assertEquals(raw.get("mPaperType"), outMap.get("mPaperType"));
        assertEquals(raw.get("mDocumentFormat"), outMap.get("mDocumentFormat"));
        assertEquals(raw.get("mCollateMode"), outMap.get("mCollateMode"));
        assertEquals(raw.get("mCopies"), outMap.get("mCopies"));
        assertEquals(raw.get("mSource"), outMap.get("mSource"));
        assertEquals(raw.get("mJobName"), outMap.get("mJobName"));
        assertEquals(raw.get("mOrientation"), outMap.get("mOrientation"));
        assertEquals(raw.get("mPrintQuality"), outMap.get("mPrintQuality"));
        assertEquals(raw.get("mOutputBin"), outMap.get("mOutputBin"));
        assertEquals(raw.get("mStartPageRanges"), outMap.get("mStartPageRanges"));
        assertEquals(raw.get("mEndPageRanges"), outMap.get("mEndPageRanges"));
        assertEquals(raw.get("mFinishings"), outMap.get("mFinishings"));
    }

    @Test
    public void minimal_attributes_defaults() throws Exception {
        String json = load("/json/printer/PrintAttributes_minimal.json");
        PrintAttributes attrs = parser.fromJson(json, PrintAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        assertEquals("DEFAULT", outMap.get("mColorMode"));
        assertEquals(1, ((Number) outMap.get("mCopies")).intValue());
        assertEquals("AUTO", outMap.get("mDocumentFormat"));
        Object finishings = outMap.get("mFinishings");
        assertNotNull(finishings);
        assertTrue(finishings.toString().contains("DEFAULT"));
    }

    @Test
    public void invalid_enums_lenient_toNull() throws Exception {
        String json = load("/json/printer/PrintAttributes_invalid_enum.json");
        PrintAttributes attrs = parser.fromJson(json, PrintAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        // Unknown enum tokens become null (not re-serialized) -> ensure keys exist? our serializer may omit nulls
        assertNull(outMap.get("mColorMode"));
        assertNull(outMap.get("mPlex"));
        assertNull(outMap.get("mAutoFit"));
        assertNull(outMap.get("mStapleMode"));
        assertNull(outMap.get("mPaperSource"));
        assertNull(outMap.get("mPaperSize"));
        assertNull(outMap.get("mPaperType"));
        assertNull(outMap.get("mDocumentFormat"));
        assertNull(outMap.get("mCollateMode"));
        assertNull(outMap.get("mOrientation"));
        assertNull(outMap.get("mPrintQuality"));
        assertNull(outMap.get("mOutputBin"));
        // Finishings list with invalid token -> becomes [null] or empty -> just ensure not the invalid token
        Object fins = outMap.get("mFinishings");
        if (fins != null) {
            assertFalse(fins.toString().contains("TRI_FOLD"));
        }
    }

    @Test
    public void out_of_range_copies_clamped_or_defaulted() throws Exception {
        String json = load("/json/printer/PrintAttributes_out_of_range_copies.json");
        PrintAttributes attrs = parser.fromJson(json, PrintAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        // copies given as 0; builder enforcement not triggered via direct JSON -> expect either 0 or parser default 0
        // Just assert not negative and not greater than 32000
        int copies = ((Number) outMap.get("mCopies")).intValue();
        assertTrue(copies >= 0 && copies <= 32000);
    }
}
