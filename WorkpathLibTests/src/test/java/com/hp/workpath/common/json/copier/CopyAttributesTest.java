package com.hp.workpath.common.json.copier;

import com.hp.workpath.api.copier.CopyAttributes;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

/** Tests for CopyAttributes JSON (direct field mapping). */
public class CopyAttributesTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setLenientEnum(true).setFailOnUnknown(false).setSwallowFieldErrors(false);
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
    public void parse_normal_attributes() throws Exception {
        String json = load("/json/copier/CopyAttributes_normal.json");
        // Sanity check raw JSON
        Map raw = parser.fromJson(json, Map.class);
        assertEquals("COLOR", raw.get("mColorMode"));
        assertNotNull(((Map)raw.get("mStampOptionMap")).get("TOP_LEFT"));

        // Parse object
        CopyAttributes attrs = parser.fromJson(json, CopyAttributes.class);
        assertNotNull(attrs);
        // Since getters not exposed for all fields, validate via re-serialization round-trip on subset
        String out = parser.toJson(attrs);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(raw.get("mColorMode"), outMap.get("mColorMode"));
        assertEquals(raw.get("mScanSize"), outMap.get("mScanSize"));
        assertEquals(raw.get("mCopies"), outMap.get("mCopies"));
        assertEquals(raw.get("mScalePercent"), outMap.get("mScalePercent"));
    // Added explicit assertion for copy preview (was null previously due to invalid enum token 'ON')
    assertEquals("TRUE", raw.get("mCopyPreview"));
    assertEquals(raw.get("mCopyPreview"), outMap.get("mCopyPreview"));
        // Extended field coverage comparisons
        String[] enumStringFields = new String[]{
                "mOrientation","mScanDuplex","mScanSource","mCopyPreview","mBackgroundCleanup",
                "mContrastAdjustment","mDarknessAdjustment","mSharpnessAdjustment","mPrintDuplex","mPrintSize",
                "mCollateMode","mPaperSource","mPaperType","mScaleMode","mTextGraphicsOptimization",
                "mJobAssemblyMode","mJobExecutionMode","mNumberUpMode","mNumberUpDirection","mOutputBin",
                "mProgressDialogMode","mEraseMarginUnit","mCaptureMode","mImageShiftReduceToFit","mImageShiftUnits",
                "mBookletBordersEachPage","mBookletFinishingOption","mBookletFormat","mStapleOption","mPunchMode",
                "mFoldMode","mWatermarkOnlyFirstPage","mWatermarkRotate45","mWatermarkType","mWatermarkMessageType",
                "mWatermarkBackgroundPattern"
        };
        for (String k : enumStringFields) {
            if (raw.containsKey(k)) {
                assertEquals("Mismatch for field " + k, raw.get(k), outMap.get(k));
            }
        }
        String[] numericFields = new String[]{
                "mScanCustomLength","mScanCustomWidth","mPrintCustomLength","mPrintCustomWidth",
                "mEraseBackBottom","mEraseBackLeft","mEraseBackRight","mEraseBackTop",
                "mEraseFrontBottom","mEraseFrontLeft","mEraseFrontRight","mEraseFrontTop",
                "mImageShiftXFront","mImageShiftYFront","mImageShiftXBack","mImageShiftYBack",
                "mWatermarkTextSize","mWatermarkTransparency","mWatermarkDarkness"
        };
        for (String k : numericFields) {
            if (raw.containsKey(k)) {
                assertEquals("Mismatch for field " + k, raw.get(k), outMap.get(k));
            }
        }
        String[] stringFields = new String[]{
                "mWatermarkBackgroundColor","mWatermarkFont","mWatermarkTextColor","mWatermarkText"
        };
        for (String k : stringFields) {
            if (raw.containsKey(k)) {
                assertEquals("Mismatch for field " + k, raw.get(k), outMap.get(k));
            }
        }

        // Stamp map presence (structure only)
        Map stampMap = (Map) raw.get("mStampOptionMap");
        assertTrue(stampMap.containsKey("TOP_LEFT"));
        // If stamp option map serialized back, ensure key still present
        if (outMap.containsKey("mStampOptionMap")) {
            Map outStamp = (Map) outMap.get("mStampOptionMap");
            assertTrue(outStamp.containsKey("TOP_LEFT"));
        }
    }
}
