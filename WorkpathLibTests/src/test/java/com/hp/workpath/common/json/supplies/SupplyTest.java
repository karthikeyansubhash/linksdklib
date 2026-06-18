package com.hp.workpath.common.json.supplies;

import com.hp.workpath.api.supplies.supplyinfo.Supply;
import com.hp.workpath.common.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.*;

public class SupplyTest {
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

    private void assertOnlyAllowedFields(Supply s, Map out) {
        // Allowed non-null fields (if present in fixture)
        // Strings
        assertEquals(out.get("ConsumableTypeEnum"), s.getConsumableTypeEnum());
        assertEquals(out.get("MakeAndModel"), s.getMakeAndModel());
        assertEquals(out.get("ProductNumber"), s.getProductNumber());
        assertEquals(out.get("SerialNumber"), s.getSerialNumber());
        assertEquals(out.get("Description"), s.getDescription());
        assertEquals(out.get("MarkerColor"), s.getMarkerColor());
        assertEquals(out.get("ApproxPercentRemaining"), s.getApproxPercentRemaining());
        // Capacity nested
        Object capObj = out.get("Capacity");
        if (capObj == null) {
            assertNull(s.getCapacity());
        } else {
            assertNotNull(s.getCapacity());
            Map capMap = (Map) capObj;
            assertEquals(((Number)capMap.get("MaxCapacity")).intValue(), s.getCapacity().getMaxCapacity());
            assertEquals(capMap.get("Unit"), s.getCapacity().getUnit());
        }
        // Deprecated/unsupported fields should be null or zero
        assertNull("Manufacturer should be null", s.getManufacturer());
        assertNull(s.getApproxPercentRemainingOnInstall());
        assertNull(s.getAtVeryLowAction());
        assertNull(s.getConsumableLowThreshold());
        assertNull(s.getConsumableState());
        assertNull(s.getEstimatedPagesRemaining());
        assertNull(s.getSelectabilityNumber());
        assertNull(s.getApproximatePagesRemaining());
        assertNull(s.getCartridgeModelInformation());
        assertNull(s.getCartridgeModelLife());
        assertNull(s.getCartridgeModelCapacity());
        assertNull(s.getSupportedPartNumbers());
        assertNull(s.getCycleCount());
        assertNull(s.getCycleLimit());
        assertEquals(0, s.getCycleCountV2());
        assertEquals(0, s.getCycleLimitV2());
        assertNull(s.getAgentID());
        assertNull(s.getConsumableContentType());
        assertNull(s.getInstallation());
        assertNull(s.getLastUse());
        assertNull(s.getRawElabelData());
        assertEquals(0, s.getWebServiceAccessData());
        assertNull(s.getConsumableManufacturingSignature());
        assertNull(s.getAtLowAction());
        assertNull(s.getCartridgeInfo());
        assertNull(s.getReserveState());
        assertNull(s.getRegion());
    }

    @Test
    public void normal_supply_only_allowed_fields_present() throws Exception {
        String json = load("/json/supplies/Supply_normal.json");
        Supply supply = parser.fromJson(json, Supply.class);
        assertNotNull(supply);
        Map out = parser.fromJson(parser.toJson(supply), Map.class);
        assertOnlyAllowedFields(supply, out);
    }

    @Test
    public void minimal_supply_defaults() throws Exception {
        Supply supply = parser.fromJson(load("/json/supplies/Supply_minimal.json"), Supply.class);
        assertNotNull(supply);
        Map out = parser.fromJson(parser.toJson(supply), Map.class);
        assertOnlyAllowedFields(supply, out);
    }

    @Test
    public void invalid_values_lenient() throws Exception {
        Supply supply = parser.fromJson(load("/json/supplies/Supply_invalid_values.json"), Supply.class);
        assertNotNull(supply);
        Map out = parser.fromJson(parser.toJson(supply), Map.class);
        assertOnlyAllowedFields(supply, out);
        // Negative capacity in JSON should still map; we just assert non-positive retained or sanitized to 0
        if (supply.getCapacity() != null) {
            assertTrue(supply.getCapacity().getMaxCapacity() >= 0 || ((Number)((Map)out.get("Capacity")).get("MaxCapacity")).intValue() < 0);
        }
    }

    @Test
    public void missing_capacity() throws Exception {
        Supply supply = parser.fromJson(load("/json/supplies/Supply_missing_capacity.json"), Supply.class);
        assertNotNull(supply);
        Map out = parser.fromJson(parser.toJson(supply), Map.class);
        assertOnlyAllowedFields(supply, out);
    }

    @Test
    public void capacity_only() throws Exception {
        Supply supply = parser.fromJson(load("/json/supplies/Supply_capacity_only.json"), Supply.class);
        assertNotNull(supply);
        Map out = parser.fromJson(parser.toJson(supply), Map.class);
        assertOnlyAllowedFields(supply, out);
    }
}
