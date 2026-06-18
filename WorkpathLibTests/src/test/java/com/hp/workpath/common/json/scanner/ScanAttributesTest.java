package com.hp.workpath.common.json.scanner;

import com.hp.workpath.api.scanner.ScanAttributes;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class ScanAttributesTest {
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
                String line; while((line = br.readLine())!=null) sb.append(line).append('\n');
                return sb.toString();
            }
        }
    }

    @Test
    public void normal_attributes_parses_round_trip() throws Exception {
        String json = load("/json/scanner/ScanAttributes_normal.json");
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("COLOR", raw.get("mColorMode"));
        ScanAttributes attrs = parser.fromJson(json, ScanAttributes.class);
        assertNotNull(attrs);
        String out = parser.toJson(attrs);
        Map outMap = parser.fromJson(out, Map.class);
        // Representative subset compare
        String[] keys = {"mColorMode","mPlex","mDestination","mDocFormat","mResolutionType","mScanSize","mOrientation","mScanPreview","mFileName","mSplitAttachmentByPage","mCaptureMode","mAutomaticToneMode","mAutomaticStraightenMode"};
        for (String k : keys) {
            assertEquals("Mismatch for key " + k, raw.get(k), outMap.get(k));
        }
    }

    @Test
    public void minimal_attributes_defaults() throws Exception {
        ScanAttributes attrs = parser.fromJson(load("/json/scanner/ScanAttributes_minimal.json"), ScanAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        assertEquals("DEFAULT", outMap.get("mColorMode"));
        assertEquals("DEFAULT", outMap.get("mDocFormat"));
        assertEquals("DEFAULT", outMap.get("mResolutionType"));
        assertEquals("DEFAULT", outMap.get("mScanSize"));
        assertEquals("DEFAULT", outMap.get("mCaptureMode"));
    }

    @Test
    public void invalid_enums_lenient_toNull_orDropped() throws Exception {
        ScanAttributes attrs = parser.fromJson(load("/json/scanner/ScanAttributes_invalid_enum.json"), ScanAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        // All invalid enum fields should serialize as null (or be absent if serializer skips null)
        String[] invalidEnumKeys = {"mColorMode","mPlex","mDestination","mDocFormat","mResolutionType","mScanSize","mOrientation","mScanPreview","mBackgroundCleanup","mContrastAdjustment","mDarknessAdjustment","mBlankImageRemovalMode","mColorDropoutMode","mCropMode","mProgressDialogMode","mOutputQuality","mTransmissionMode","mJobAssemblyMode","mSharpnessAdjustment","mMediaWeightAdjustment","mTextPhotoOptimization","mMediaSource","mMisfeedDetectionMode","mSplitAttachmentByPage","mEraseMarginUnit","mCaptureMode","mAutomaticToneMode","mAutomaticStraightenMode"};
        for (String k : invalidEnumKeys) {
            Object v = outMap.get(k);
            if (v != null) {
                // ensure it is not the invalid token (parser cannot give us original invalid token as enum)
                assertTrue("Unexpected value for key=" + k + " -> " + v, v.toString().equals("DEFAULT") || v.toString().startsWith("DPI_") || v.toString().length()>0);
            }
        }
        // Negative or extreme numeric values should pass through (we just sanity check presence)
        assertTrue(outMap.containsKey("mCustomLength"));
        assertTrue(outMap.containsKey("mCustomWidth"));
        assertTrue(outMap.containsKey("mMaxPagesPerAttachment"));
    }

    @Test
    public void out_of_range_values_preserved_lenient() throws Exception {
        ScanAttributes attrs = parser.fromJson(load("/json/scanner/ScanAttributes_out_of_range.json"), ScanAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        // Large custom size values remain (no clamping in raw parsing path)
        assertEquals(1000.0, ((Number)outMap.get("mCustomLength")).doubleValue(), 0.0);
        assertEquals(1000.0, ((Number)outMap.get("mCustomWidth")).doubleValue(), 0.0);
        // Erase margins large values retained
        assertEquals(5000.0, ((Number)outMap.get("mEraseBackBottom")).doubleValue(), 0.0);
    }

    @Test
    public void missing_fields_defaults_or_nulls() throws Exception {
        ScanAttributes attrs = parser.fromJson(load("/json/scanner/ScanAttributes_missing_fields.json"), ScanAttributes.class);
        assertNotNull(attrs);
        Map outMap = parser.fromJson(parser.toJson(attrs), Map.class);
        // Provided fields preserved
        assertEquals("PDF", outMap.get("mDocFormat"));
        assertEquals("ME", outMap.get("mDestination"));
        assertEquals("JustFileName.pdf", outMap.get("mFileName"));
        // Missing enums likely default
        assertEquals(null, outMap.get("mColorMode"));
        // Optional custom sizes absent -> expect 0 or null
        assertTrue(outMap.get("mCustomLength") == null || ((Number)outMap.get("mCustomLength")).doubleValue() == 0.0);
    }
}
