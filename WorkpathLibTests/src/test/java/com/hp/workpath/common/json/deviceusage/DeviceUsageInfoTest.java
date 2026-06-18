package com.hp.workpath.common.json.deviceusage;

import com.hp.workpath.api.deviceusage.DeviceUsageInfo;
import com.hp.workpath.api.deviceusage.SubUnitInfo;
import com.hp.workpath.api.deviceusage.printer.PrinterInfo;
import com.hp.workpath.api.deviceusage.scanner.ScannerInfo;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class DeviceUsageInfoTest {
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = JsonParser.getInstance();
        parser.setLenientEnum(true)
                .setLenientDate(true)
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
    public void normal_usage_parses() throws Exception {
        DeviceUsageInfo info = parser.fromJson(load("/json/deviceusage/DeviceUsageInfo_normal.json"), DeviceUsageInfo.class);
        assertNotNull(info);
        PrinterInfo p = info.getPrinter(); assertNotNull(p);
        assertTrue(p.getSheets() >= 0);
        assertNotNull(p.getByPrintPlex()); assertEquals(2, p.getByPrintPlex().length);
        assertNotNull(p.getByJobCategory()); assertTrue(p.getByJobCategory().length >= 2);
        assertNotNull(p.getPrintByMediaSize()); assertEquals(1, p.getPrintByMediaSize().length);
        ScannerInfo s = info.getScanner(); assertNotNull(s);
        assertNotNull(s.getByJobCategory()); assertEquals(2, s.getByJobCategory().length);
        assertEquals(SubUnitInfo.JobCategory.DIGITALSEND, s.getByJobCategory()[0].getJobCategoryType());
    }

    @Test
    public void missing_scanner_allowed() throws Exception {
        DeviceUsageInfo info = parser.fromJson(load("/json/deviceusage/DeviceUsageInfo_missing_scanner.json"), DeviceUsageInfo.class);
        assertNotNull(info);
        assertNotNull(info.getPrinter());
        assertNull("scanner should be null when omitted", info.getScanner());
    }

    @Test
    public void empty_arrays() throws Exception {
        DeviceUsageInfo info = parser.fromJson(load("/json/deviceusage/DeviceUsageInfo_empty_arrays.json"), DeviceUsageInfo.class);
        assertNotNull(info);
        assertNotNull(info.getPrinter());
        assertEquals(0, info.getPrinter().getSheets());
        assertNotNull(info.getPrinter().getByPrintPlex()); assertEquals(0, info.getPrinter().getByPrintPlex().length);
        assertNotNull(info.getScanner());
        assertNotNull(info.getScanner().getByScanPlex()); assertEquals(0, info.getScanner().getByScanPlex().length);
    }

    @Test
    public void invalid_jobCategory_falls_back_to_OTHER() throws Exception {
        DeviceUsageInfo info = parser.fromJson(load("/json/deviceusage/DeviceUsageInfo_invalid_jobCategory.json"), DeviceUsageInfo.class);
        assertNotNull(info);
        assertNotNull(info.getPrinter().getByJobCategory());
        SubUnitInfo.JobCategory cat = info.getPrinter().getByJobCategory()[0].getJobCategoryType();
        assertEquals(SubUnitInfo.JobCategory.OTHER, cat);
        assertNotNull(info.getScanner().getByJobCategory());
        assertEquals(SubUnitInfo.JobCategory.OTHER, info.getScanner().getByJobCategory()[0].getJobCategoryType());
    }

    @Test
    public void invalid_mediaSize_falls_back_to_OTHER() throws Exception {
        DeviceUsageInfo info = parser.fromJson(load("/json/deviceusage/DeviceUsageInfo_invalid_mediaSize.json"), DeviceUsageInfo.class);
        assertNotNull(info);
        // printer byJobCategoryAndMediaSize invalid mediaSize should map to OTHER via helper if method exists; we just ensure raw object parsed
        PrinterInfo p = info.getPrinter(); assertNotNull(p);
        assertNotNull(p.getByJobCategoryAndMediaSize());
        assertEquals(1, p.getByJobCategoryAndMediaSize().length);
        // scanner invalid media size -> OTHER
        ScannerInfo s = info.getScanner(); assertNotNull(s);
        assertNotNull(s.getByMediaSize()); assertEquals(1, s.getByMediaSize().length);
        assertEquals(SubUnitInfo.MediaSize.OTHER, s.getByMediaSize()[0].getMediaSizeType());
    }

    @Test
    public void round_trip_subset() throws Exception {
        String json = load("/json/deviceusage/DeviceUsageInfo_normal.json");
        DeviceUsageInfo info = parser.fromJson(json, DeviceUsageInfo.class);
        String out = parser.toJson(info);
        Map inMap = parser.fromJson(json, Map.class);
        Map outMap = parser.fromJson(out, Map.class);
        assertEquals(inMap.containsKey("printer"), outMap.containsKey("printer"));
        assertEquals(inMap.containsKey("scanner"), outMap.containsKey("scanner"));
    }
}
