package com.hp.workpath.common.json;

import com.hp.workpath.common.utils.JsonParser;
import com.hp.workpath.common.utils.json.EmptyStringAsNull;
import com.hp.workpath.common.utils.json.SerializedName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class JsonParserTest {

	private JsonParser parser;

	@Before
	public void setUp() {
		parser = JsonParser.getInstance();
		resetParser();
	}

	@After
	public void tearDown() {
		resetParser();
	}

	private void resetParser() {
		parser.setDetectCycles(true)
				.setFailOnUnknown(false)
				.setLenientNumbers(true)
				.setLenientEnum(true)
				.setLenientDate(true)
				.setSwallowFieldErrors(true)
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.setDateTimeZone(TimeZone.getTimeZone("UTC"));
	}

	// Helper test classes
	static class BasicPojo {
		int id;
		String name;
		boolean active;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof BasicPojo)) return false;
			BasicPojo b = (BasicPojo) o;
			return id == b.id && active == b.active && (name == null ? b.name == null : name.equals(b.name));
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name, active);
		}
	}

	enum AltEnum {
		@SerializedName(value = "PRIMARY", alternate = {"p", "main"}) PRIMARY,
		@SerializedName(value = "SECOND", alternate = {"s", "two"}) SECOND
	}

	static class EnumHolder { AltEnum mode; }

	static class EmptyStringHolder { @EmptyStringAsNull String note; String other; }

	// Builder-only target (no default ctor)
	static class BuilderOnlyTarget {
		final InnerCreator creator; // to exercise builder path
		String name;
		public BuilderOnlyTarget(InnerCreator c) { this.creator = c; }
		static class InnerCreator { static class Builder { InnerCreator build() { return new InnerCreator(); } } }
	}

	static class NumberHolder { int i; double d; }
	static class DateHolder { Date when; }
	static class Complex { BasicPojo root; List<BasicPojo> list; Map<String,BasicPojo> map; }
	static class Node { Node next; int v; }

	// --- toJson basic ---
	@Test public void toJson_basicPojo() {
		BasicPojo p = new BasicPojo(); p.id = 7; p.name = "alpha"; p.active = true;
		String json = parser.toJson(p);
		assertTrue(json.contains("\"id\":7"));
		assertTrue(json.contains("\"name\":"));
		assertTrue(json.contains("\"active\":true"));
	}

	@Test public void fromJson_basicPojo() {
		BasicPojo p = parser.fromJson("{\"id\":42,\"name\":\"zeta\",\"active\":false}", BasicPojo.class);
		assertEquals(42, p.id); assertEquals("zeta", p.name); assertFalse(p.active);
	}

	@Test public void fromJson_collection() {
		List<BasicPojo> list = parser.fromJson("[{\"id\":1,\"name\":\"a\",\"active\":true},{\"id\":2,\"name\":\"b\",\"active\":false}]", JsonParser.type(List.class, BasicPojo.class));
		assertEquals(2, list.size()); assertEquals(1, list.get(0).id); assertEquals("b", list.get(1).name);
	}

	@Test public void fromJson_enumAlternate() {
		EnumHolder h = parser.fromJson("{\"mode\":\"p\"}", EnumHolder.class); assertEquals(AltEnum.PRIMARY, h.mode);
		h = parser.fromJson("{\"mode\":\"two\"}", EnumHolder.class); assertEquals(AltEnum.SECOND, h.mode);
	}

	@Test public void fromJson_emptyStringAsNull() {
		EmptyStringHolder h = parser.fromJson("{\"note\":\"\",\"other\":\"x\"}", EmptyStringHolder.class);
		assertNull(h.note); assertEquals("x", h.other);
	}

	@Test public void fromJson_builderOnlyCtor() {
		BuilderOnlyTarget t = parser.fromJson("{\"name\":\"builderTarget\"}", BuilderOnlyTarget.class);
		assertNotNull(t); assertEquals("builderTarget", t.name); assertNotNull(t.creator);
	}

	@Test public void fromJson_failOnUnknown_enforced() {
		parser.setFailOnUnknown(true);
		try { parser.fromJson("{\"id\":1,\"unknownField\":123}", BasicPojo.class); fail("Expected exception"); }
		catch (RuntimeException expected) { assertTrue(expected.getMessage().contains("Unknown field")); }
		finally { parser.setFailOnUnknown(false); }
	}

	@Test public void toJson_cycleDetection() {
		parser.setDetectCycles(true);
		Node n = new Node(); n.v=1; Node m = new Node(); m.v=2; n.next=m; m.next=n;
		try { parser.toJson(n); fail("Expected cycle exception"); }
		catch (IllegalStateException ex) { assertTrue(ex.getMessage().contains("Cycle")); }
	}

	@Test public void fromJson_numberLeniency() {
		NumberHolder h = parser.fromJson("{\"i\":\"42\",\"d\":\"3.14\"}", NumberHolder.class);
		assertEquals(42, h.i); assertEquals(3.14, h.d, 0.0001);
		NumberHolder bad = parser.fromJson("{\"i\":\"xx\"}", NumberHolder.class);
		assertEquals(0, bad.i);
	}

	@Test public void fromJson_dateLeniency() {
		String ts = "2025-09-01T10:20:30.123Z";
		DateHolder d = parser.fromJson("{\"when\":\""+ts+"\"}", DateHolder.class); assertNotNull(d.when);
		d = parser.fromJson("{\"when\":\"bad-date\"}", DateHolder.class); assertNull(d.when);
		parser.setLenientDate(false);
		try { parser.fromJson("{\"when\":\"bad-date\"}", DateHolder.class); fail(); }
		catch (RuntimeException expected) { }
		finally { parser.setLenientDate(true); }
	}

	@Test public void roundTrip_complex() {
		Complex c = new Complex(); c.root = new BasicPojo(); c.root.id=9; c.root.name="root"; c.root.active=true;
		BasicPojo a = new BasicPojo(); a.id=1; a.name="a"; a.active=false;
		BasicPojo b = new BasicPojo(); b.id=2; b.name="b"; b.active=true;
		c.list = Arrays.asList(a,b); c.map = new LinkedHashMap<String,BasicPojo>(); c.map.put("first", a); c.map.put("second", b);
		String json = parser.toJson(c); Complex back = parser.fromJson(json, Complex.class);
		assertNotNull(back); assertEquals(2, back.list.size()); assertEquals("root", back.root.name); assertEquals(2, back.map.get("second").id);
	}

	@Test public void gsonCompatibility_failFast() {
		parser.setGsonCompatibilityMode();
		BasicPojo ok = parser.fromJson("{\"id\":1,\"name\":\"a\",\"active\":true}", BasicPojo.class); assertEquals(1, ok.id);
		try { parser.fromJson("{\"id\":\"2\"}", BasicPojo.class); fail("Expected number format failure"); } catch (RuntimeException expected) {}
		try { parser.fromJson("{\"when\":\"bad-date\"}", DateHolder.class); fail("Expected bad date failure"); } catch (RuntimeException expected) {}
		try { parser.fromJson("{\"mode\":\"zzz\"}", EnumHolder.class); fail("Expected enum failure"); } catch (RuntimeException expected) {}
	}

	// --- complex sample JSON resource parsing ---
	@Test public void parse_sample_more_complex1_structure() throws Exception {
		String json = loadResource("/json/sample_more_complex1.json");
		Map root = parser.fromJson(json, Map.class); assertNotNull(root);
		Map meta = (Map) root.get("meta"); assertEquals(3, ((Number) meta.get("version")).intValue());
		List tags = (List) meta.get("tags"); assertEquals(3, tags.size());
		Map list0 = (Map) ((List) root.get("list")).get(0); assertEquals("alpha", list0.get("name"));
		List hetero = (List) root.get("heteroArray"); assertEquals(6, hetero.size());
		Map sparse = (Map) root.get("sparseObj"); assertTrue(sparse.containsKey("b"));
	}

	@Test public void parse_sample_more_complex2_deep() throws Exception {
		String json = loadResource("/json/sample_more_complex2.json");
		Map root = parser.fromJson(json, Map.class); assertNotNull(root);
		Map meta = (Map) root.get("meta"); assertEquals(5, ((Number) meta.get("version")).intValue());
		List history = (List) meta.get("history"); assertEquals(3, history.size());
		Map nested = (Map) ((Map) ((Map) ((Map) root.get("nested")).get("level1")).get("level2")).get("level3");
		List arr = (List) nested.get("array"); assertEquals(2, arr.size());
		Map second = (Map) arr.get(1); List vals = (List) second.get("vals");
		List moreList = (List) ((Map) vals.get(1)).get("more"); assertEquals(4, moreList.size());
		Map last = (Map) moreList.get(3); assertEquals(Boolean.TRUE, last.get("final"));
		List bigArray = (List) root.get("bigArray"); assertEquals(30, bigArray.size());
		Map mixedMap = (Map) root.get("mixedMap"); List array = (List) mixedMap.get("array"); assertEquals(5, array.size());
		List deepHetero = (List) root.get("deepHetero"); assertEquals(3, deepHetero.size());
	}

	private String loadResource(String path) throws Exception {
		java.io.InputStream in = getClass().getResourceAsStream(path); assertNotNull("Resource not found: " + path, in);
		try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(in, java.nio.charset.StandardCharsets.UTF_8))) {
			StringBuilder sb = new StringBuilder(); String line; while ((line = br.readLine()) != null) sb.append(line).append('\n'); return sb.toString(); }
	}
}
