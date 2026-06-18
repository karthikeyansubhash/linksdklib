package com.hp.workpath.common.gson;

import com.hp.workpath.common.utils.JsonParser;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Phase 2: Collections & Generics.
 */
public class CollectionsGenericsTest extends BaseJsonParserTest {
    static class Item {
        int id;
        String name;
    }

    static class Holder {
        List<Item> list;
        Set<Integer> nums;
        Map<String, Item> map;
    }

    @Test
    public void listSetMapRoundTrip() {
        Holder h = new Holder();
        Item a = new Item();
        a.id = 1;
        a.name = "a";
        Item b = new Item();
        b.id = 2;
        b.name = "b";
        h.list = Arrays.asList(a, b);
        h.nums = new LinkedHashSet<Integer>(Arrays.asList(3, 4, 5));
        h.map = new LinkedHashMap<>();
        h.map.put("first", a);
        h.map.put("second", b);
        String json = parser.toJson(h);
        Holder back = parser.fromJson(json, Holder.class);
        assertEquals(2, back.list.size());
        assertEquals(3, back.nums.size());
        assertEquals("b", back.map.get("second").name);
    }

    @Test
    public void nestedParameterizedType() {
        Map<String, Map<String, List<Integer>>> complex = new LinkedHashMap<>();
        Map<String, List<Integer>> inner = new LinkedHashMap<>();
        inner.put("ints", Arrays.asList(1, 2, 3));
        complex.put("wrap", inner);
        String json = parser.toJson(complex);
        Object parsed = parser.fromJson(json, JsonParser.type(Map.class, String.class, JsonParser.type(Map.class, String.class, JsonParser.type(List.class, Integer.class))));
        assertTrue(parsed instanceof Map);
        Map root = (Map) parsed;
        Map wrap = (Map) root.get("wrap");
        List ints = (List) wrap.get("ints");
        assertEquals(3, ints.size());
    }

    @Test
    public void emptyArrayAndObject() {
        List<String> emptyList = parser.fromJson("[]", JsonParser.type(List.class, String.class));
        assertTrue(emptyList.isEmpty());
        Map m = parser.fromJson("{}", Map.class);
        assertTrue(m.isEmpty());
    }
}
