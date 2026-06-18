package com.hp.workpath.common.json.scanner;

import com.hp.workpath.api.scanner.ScanAttributes;
import com.hp.workpath.api.scanner.ScanAttributesCaps;
import com.hp.workpath.common.utils.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ScanAttributesCapsTest {
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
    public void normal_caps_parses_and_orders_defaults_first() throws Exception {
        String json = load("/json/scanner/ScanAttributesCaps_normal.json");
        ScanAttributesCaps caps = parser.fromJson(json, ScanAttributesCaps.class);
        assertNotNull(caps);
        List<ScanAttributes.ColorMode> colorModes = caps.getColorModeList();
        assertEquals("DEFAULT", colorModes.get(0).name());
        assertTrue(colorModes.contains(ScanAttributes.ColorMode.COLOR));
        // Resolution ordering check (we only asserted subset of provided list)
        List<ScanAttributes.Resolution> resolutions = caps.getResolutionList();
        assertEquals("DEFAULT", resolutions.get(0).name());
        assertTrue(resolutions.contains(ScanAttributes.Resolution.DPI_300));
        // DocumentFormatsByColorMode map subset
        Map<ScanAttributes.ColorMode, List<ScanAttributes.DocumentFormat>> byColor = caps.getDocumentFormatsByColorMode();
        assertTrue(byColor.containsKey(ScanAttributes.ColorMode.DEFAULT));
        assertTrue(byColor.get(ScanAttributes.ColorMode.DEFAULT).contains(ScanAttributes.DocumentFormat.JPEG));
    }

    @Test
    public void minimal_caps_only_defaults() throws Exception {
        String json = load("/json/scanner/ScanAttributesCaps_minimal.json");
        ScanAttributesCaps caps = parser.fromJson(json, ScanAttributesCaps.class);
        assertNotNull(caps);
        assertEquals(1, caps.getColorModeList().size());
        assertEquals(ScanAttributes.ColorMode.DEFAULT, caps.getColorModeList().get(0));
        assertEquals(1, caps.getDuplexList().size());
        assertEquals(ScanAttributes.Duplex.DEFAULT, caps.getDuplexList().get(0));
        assertEquals(1, caps.getDestinationList().size());
        assertEquals(ScanAttributes.Destination.ME, caps.getDestinationList().get(0));
    }

    @Test
    public void invalid_enums_lenient_become_null_or_removed() throws Exception {
        String json = load("/json/scanner/ScanAttributesCaps_invalid_enum.json");
        ScanAttributesCaps caps = parser.fromJson(json, ScanAttributesCaps.class);
        assertNotNull(caps);
        // Collect present enum names for easy membership checks
        Set<String> colorModes = caps.getColorModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> duplexes = caps.getDuplexList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> destinations = caps.getDestinationList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> orientations = caps.getOrientationList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> scanPreviews = caps.getScanPreviewList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> resolutions = caps.getResolutionList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> backgroundCleanups = caps.getBackgroundCleanupList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> contrastAdjustments = caps.getContrastAdjustmentList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> darknessAdjustments = caps.getDarknessAdjustmentList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> blankImageRemovalModes = caps.getBlankImageRemovalModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> colorDropoutModes = caps.getColorDropoutModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> cropModes = caps.getCropModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> outputQualities = caps.getOutputQualityList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> progressDialogModes = caps.getProgressDialogModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> transmissionModes = caps.getTransmissionModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> jobAssemblyModes = caps.getJobAssemblyModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> sharpnessAdjustments = caps.getSharpnessAdjustmentList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> mediaWeightAdjustments = caps.getMediaWeightAdjustmentList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> textPhotoOptimizations = caps.getTextPhotoOptimizationList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> mediaSources = caps.getMediaSourceList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> misfeedDetectionModes = caps.getMisfeedDetectionModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> splitAttachmentByPage = caps.getSplitAttachmentByPageList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> eraseMarginUnits = caps.getEraseMarginUnitList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> captureModes = caps.getCaptureModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> automaticToneModes = caps.getAutomaticToneModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());
        Set<String> automaticStraightenModes = caps.getAutomaticStraightenModeList().stream().filter(e -> e != null).map(Enum::name).collect(Collectors.toSet());

        // Explicit invalid tokens from fixture should be absent
        assertFalse(colorModes.contains("ULTRA_COLOR"));
        assertFalse(duplexes.contains("INSIDE_OUT"));
        assertFalse(destinations.contains("DROPBOX"));
        assertFalse(orientations.contains("UPSIDE"));
        assertFalse(scanPreviews.contains("FASTEST"));
        assertFalse(resolutions.contains("DPI_42"));
        assertFalse(backgroundCleanups.contains("ULTRA"));
        assertFalse(contrastAdjustments.contains("PLUS_11"));
        assertFalse(darknessAdjustments.contains("DARKER_9"));
        assertFalse(blankImageRemovalModes.contains("MAGIC"));
        assertFalse(colorDropoutModes.contains("CYAN"));
        assertFalse(cropModes.contains("SMART"));
        assertFalse(outputQualities.contains("ULTRA"));
        assertFalse(progressDialogModes.contains("QUIET"));
        assertFalse(transmissionModes.contains("SATELLITE"));
        assertFalse(jobAssemblyModes.contains("SUPER_SET"));
        assertFalse(sharpnessAdjustments.contains("SHARPEST"));
        assertFalse(mediaWeightAdjustments.contains("ULTRA_HEAVY"));
        assertFalse(textPhotoOptimizations.contains("HYPER"));
        assertFalse(mediaSources.contains("PHONE_CAM"));
        assertFalse(misfeedDetectionModes.contains("SENSITIVE"));
        assertFalse(splitAttachmentByPage.contains("EACH_9999"));
        assertFalse(eraseMarginUnits.contains("PIXELS"));
        assertFalse(captureModes.contains("ULTRA_FAST"));
        assertFalse(automaticToneModes.contains("NEON"));
        assertFalse(automaticStraightenModes.contains("SUPER_STRAIGHT"));

        // Document formats map: ensure invalid formats from fixture (WEBP, DOCX) not present in any list
        for (Map.Entry<ScanAttributes.ColorMode, List<ScanAttributes.DocumentFormat>> e : caps.getDocumentFormatsByColorMode().entrySet()) {
            for (ScanAttributes.DocumentFormat f : e.getValue()) {
                if (f == null) continue; // lenient parse produced null for invalid token
                String name = f.name();
                assertNotEquals("WEBP should be dropped", "WEBP", name);
                assertNotEquals("DOCX should be dropped", "DOCX", name);
            }
        }

        // Basic sanity: each list still contains at least one valid value (DEFAULT expected)
        assertTrue(colorModes.contains("DEFAULT"));
        assertTrue(duplexes.contains("DEFAULT"));
        assertTrue(destinations.size() >= 1); // ME or others
        // Range sanity: lower <= upper even if source had negatives
        assertTrue(caps.getCustomLengthRange().getLowerBound() <= caps.getCustomLengthRange().getUpperBound());
        assertTrue(caps.getCustomWidthRange().getLowerBound() <= caps.getCustomWidthRange().getUpperBound());
    }

    @Test
    public void empty_lists_supported_as_not_supported() throws Exception {
        String json = load("/json/scanner/ScanAttributesCaps_empty_lists.json");
        ScanAttributesCaps caps = parser.fromJson(json, ScanAttributesCaps.class);
        assertNotNull(caps);
        // Example: color modes empty means not supported -> we expect getColorModeList() returns empty (unmodifiable) or maybe default inserted; accept either but no NPE
        try {
            caps.getColorModeList().size();
        } catch (Exception e) {
            fail("ColorMode list access should not throw even if empty: " + e);
        }
        assertEquals(0, caps.getColorModeList().size());
        assertEquals(0, caps.getDestinationList().size());
    }
}
