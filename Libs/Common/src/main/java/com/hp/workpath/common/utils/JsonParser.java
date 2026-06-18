// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
package com.hp.workpath.common.utils;

import com.hp.workpath.common.utils.json.EmptyStringAsNull;
import com.hp.workpath.common.utils.json.SerializedName;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Internal JSON parsing utility.
 * <p>
 * Provides lightweight serialization/deserialization with configurable leniency flags.
 * Not part of the public Device API surface.
 * </p>
 * @hide
 */
public class JsonParser {
    private static JsonParser instance;
    // ---- configuration flags ----
    private volatile boolean detectCycles = true;
    private volatile boolean failOnUnknown = false;          // strict unknown field handling
    private volatile boolean lenientNumbers = true;          // allow quoted numbers / numeric coercion
    private volatile boolean lenientEnum = true;             // unknown enum -> null (else throw)
    private volatile boolean lenientDate = true;             // bad date -> null (else throw)
    private volatile String datePattern = DEFAULT_DATE_PATTERN;
    private volatile TimeZone dateTimeZone = TimeZone.getTimeZone("UTC");
    // Unlike Gson we originally swallowed individual field errors; flag added to control Gson compatibility.
    private volatile boolean swallowFieldErrors = true;      // true: swallow individual field errors (legacy), false: fail fast for first field error
    // Gson default: omit null fields (serializeNulls disabled) -> when false we omit null valued fields / entries
    private volatile boolean serializeNulls = false;
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private JsonParser() {
    }

    // double-checked singleton
    public static JsonParser getInstance() {
        if (instance == null) {
            synchronized (JsonParser.class) {
                if (instance == null) {
                    instance = new JsonParser();
                }
            }
        }
        return instance;
    }

    // fluent config
    public JsonParser setDetectCycles(boolean v) {
        this.detectCycles = v;
        return this;
    }

    public JsonParser setFailOnUnknown(boolean v) {
        this.failOnUnknown = v;
        return this;
    }

    public JsonParser setLenientNumbers(boolean v) {
        this.lenientNumbers = v;
        return this;
    }

    public JsonParser setLenientEnum(boolean v) {
        this.lenientEnum = v;
        return this;
    }

    public JsonParser setLenientDate(boolean v) {
        this.lenientDate = v;
        return this;
    }

    public JsonParser setSwallowFieldErrors(boolean v) {
        this.swallowFieldErrors = v;
        return this;
    }

    /**
     * Enable Gson compatibility mode: configure flags to closely mimic Gson defaults.
     * - Unknown fields: ignored (failOnUnknown=false)
     * - Numbers: disallow quoted numbers (lenientNumbers=false)
     * - Enum: unknown token -> exception (lenientEnum=false)
     * - Date: bad format -> exception (lenientDate=false)
     * - Cycles: Gson does not detect cycles (we disable detection here)
     * - Field errors: fail fast (swallowFieldErrors=false)
     */
    public JsonParser setGsonCompatibilityMode() {
        this.detectCycles = false;
        this.failOnUnknown = false;
        this.lenientNumbers = false;
        this.lenientEnum = false;
        this.lenientDate = false;
        this.swallowFieldErrors = false;
    this.serializeNulls = false; // Gson default: omit null fields
        return this;
    }

    public JsonParser setSerializeNulls(boolean v) {
        this.serializeNulls = v;
        return this;
    }

    // Fallback setters removed (no-op stubs kept briefly for binary compat if already referenced elsewhere)
    @Deprecated
    public JsonParser setUseGsonFallback(boolean v) {
        return this;
    }

    @Deprecated
    public JsonParser disableGsonFallback() {
        return this;
    }

    public JsonParser setDateFormat(String pattern) {
        if (pattern != null && !pattern.isEmpty()) this.datePattern = pattern;
        return this;
    }

    public JsonParser setDateTimeZone(TimeZone tz) {
        if (tz != null) this.dateTimeZone = tz;
        return this;
    }

    // custom serializers / deserializers
    public interface CustomSerializer<T> {
        void serialize(T value, StringBuilder out, JsonParser ctx);
    }

    public interface CustomDeserializer<T> {
        T deserialize(JsonValue node, JsonParser ctx, Type targetType) throws Exception;
    }

    private final Map<Class<?>, CustomSerializer<Object>> customSerializers = new ConcurrentHashMap<>();
    private final Map<Class<?>, CustomDeserializer<Object>> customDeserializers = new ConcurrentHashMap<>();

    public <T> JsonParser registerSerializer(Class<T> type, CustomSerializer<T> s) {
        if (type != null && s != null) customSerializers.put(type, (CustomSerializer<Object>) s);
        return this;
    }

    public <T> JsonParser registerDeserializer(Class<T> type, CustomDeserializer<T> d) {
        if (type != null && d != null)
            customDeserializers.put(type, (CustomDeserializer<Object>) d);
        return this;
    }

    // ThreadLocal date format (rebuilt if pattern/timezone changed)
    private final ThreadLocal<SimpleDateFormat> dateFormatTL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat f = new SimpleDateFormat(datePattern, Locale.US);
            f.setTimeZone(dateTimeZone);
            return f;
        }
    };

    private SimpleDateFormat currentDateFormat() {
        SimpleDateFormat df = dateFormatTL.get();
        if (!df.toPattern().equals(datePattern) || !df.getTimeZone().equals(dateTimeZone)) {
            df = new SimpleDateFormat(datePattern, Locale.US);
            df.setTimeZone(dateTimeZone);
            dateFormatTL.set(df);
        }
    // Align SimpleDateFormat leniency with parser's lenientDate flag so strict mode rejects malformed dates
    try { df.setLenient(lenientDate); } catch (Throwable ignore) { }
        return df;
    }

    // ---- PUBLIC API ----
    public <T> T fromJson(String json, Class<T> clazz) {
        return fromJsonInternal(json, clazz);
    }

    public <T> T fromJson(String json, Type type) {
        return fromJsonInternal(json, type);
    }

    public String toJson(Object value) {
        StringBuilder sb = new StringBuilder(128);
        IdentityHashMap<Object, Boolean> stack = detectCycles ? new IdentityHashMap<Object, Boolean>() : null;
        appendValue(sb, value, stack, 0);
        return sb.toString();
    }

    // ---- Serialization ----
    private void appendValue(StringBuilder sb, Object val, IdentityHashMap<Object, Boolean> stack, int depth) {
        if (val == null) {
            sb.append("null");
            return;
        }
        if (depth > 256) throw new IllegalStateException("Max depth");
        if (val instanceof Number) {
            appendNumber(sb, (Number) val);
            return;
        }
        if (val instanceof Boolean) {
            sb.append(((Boolean) val) ? "true" : "false");
            return;
        }
        if (val instanceof String) {
            appendString(sb, (String) val);
            return;
        }
        if (val instanceof Character) {
            appendString(sb, val.toString());
            return;
        }
        if (val instanceof Enum) {
            appendEnum(sb, (Enum<?>) val);
            return;
        }
        if (val instanceof Date) {
            appendString(sb, currentDateFormat().format((Date) val));
            return;
        }
        Class<?> cls = val.getClass();
        if (cls.isArray()) {
            appendArray(sb, val, stack, depth);
            return;
        }
        if (val instanceof Collection) {
            appendCollection(sb, (Collection<?>) val, stack, depth);
            return;
        }
        if (val instanceof Map) {
            appendMap(sb, (Map<?, ?>) val, stack, depth);
            return;
        }
        CustomSerializer<Object> cs = customSerializers.get(cls);
        if (cs != null) {
            cs.serialize(val, sb, this);
            return;
        }
        if (stack != null) {
            if (stack.containsKey(val)) throw new IllegalStateException("Cycle: " + cls.getName());
            stack.put(val, Boolean.TRUE);
        }
        appendObject(sb, val, stack, depth);
        if (stack != null) stack.remove(val);
    }

    private static void appendNumber(StringBuilder sb, Number n) {
        if (n instanceof Double || n instanceof Float) {
            double d = n.doubleValue();
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                sb.append("null");
                return;
            }
        }
        sb.append(n.toString());
    }

    private static void appendString(StringBuilder sb, String s) {
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20) sb.append(String.format(Locale.US, "\\u%04x", (int) c));
                    else sb.append(c);
            }
        }
        sb.append('"');
    }

    private void appendArray(StringBuilder sb, Object arr, IdentityHashMap<Object, Boolean> stack, int depth) {
        sb.append('[');
        int len = Array.getLength(arr);
        for (int i = 0; i < len; i++) {
            if (i > 0) sb.append(',');
            appendValue(sb, Array.get(arr, i), stack, depth + 1);
        }
        sb.append(']');
    }

    private void appendCollection(StringBuilder sb, Collection<?> col, IdentityHashMap<Object, Boolean> stack, int depth) {
        sb.append('[');
        Iterator<?> it = col.iterator();
        boolean first = true;
        while (it.hasNext()) {
            if (!first) sb.append(',');
            appendValue(sb, it.next(), stack, depth + 1);
            first = false;
        }
        sb.append(']');
    }

    private void appendMap(StringBuilder sb, Map<?, ?> map, IdentityHashMap<Object, Boolean> stack, int depth) {
        sb.append('{');
        boolean first = true;
        for (Map.Entry<?, ?> e : map.entrySet()) {
            if (e == null) continue;
            if (!first) sb.append(',');
            appendString(sb, e.getKey() == null ? "null" : String.valueOf(e.getKey()));
            sb.append(':');
            appendValue(sb, e.getValue(), stack, depth + 1);
            first = false;
        }
        sb.append('}');
    }

    private void appendObject(StringBuilder sb, Object obj, IdentityHashMap<Object, Boolean> stack, int depth) {
        sb.append('{');
        boolean first = true;
        Class<?> c = obj.getClass();
        while (c != null && c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                int mod = f.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isTransient(mod) || f.isSynthetic()) continue;
                try {
                    try { f.setAccessible(true); } catch (Throwable t) { }
                    Object v = f.get(obj);
                    if (v == null && !serializeNulls) continue;
                    if (!first) sb.append(',');
                    String name = f.getName();
                    try {
                        SerializedName sn = f.getAnnotation(SerializedName.class);
                        if (sn != null && sn.value() != null && !sn.value().isEmpty()) name = sn.value();
                    } catch (Throwable ignore) { }
                    appendString(sb, name);
                    sb.append(':');
                    appendValue(sb, v, stack, depth + 1);
                    first = false;
                } catch (IllegalStateException cyc) {
                    throw cyc;
                } catch (Throwable ignore) { }
            }
            c = c.getSuperclass();
        }
        sb.append('}');
    }

    private static void appendEnum(StringBuilder sb, Enum<?> e) {
        try {
            Field f = e.getDeclaringClass().getField(e.name());
            SerializedName sn = f.getAnnotation(SerializedName.class);
            if (sn != null && sn.value() != null && !sn.value().isEmpty()) {
                appendString(sb, sn.value());
                return;
            }
        } catch (Throwable ignore) {
        }
        appendString(sb, e.name());
    }

    // ---- Parsing (simple recursive descent -> mini DOM) ----
    private interface JsonValue {
    }

    private static final class JsonNull implements JsonValue {
        static final JsonNull INSTANCE = new JsonNull();

        private JsonNull() {
        }
    }

    private static final class JsonPrimitive implements JsonValue {
        final Object value;

        JsonPrimitive(Object v) {
            this.value = v;
        }
    }

    private static final class JsonObject implements JsonValue {
        final Map<String, JsonValue> map = new LinkedHashMap<>();
    }

    private static final class JsonArray implements JsonValue {
        final List<JsonValue> list = new ArrayList<>();
    }

    private static final class JsonParseException extends RuntimeException {
        JsonParseException(String m) {
            super(m);
        }
    }

    private static final class Index {
        final char[] c;
        int pos = 0;

        Index(char[] c) {
            this.c = c;
        }

        boolean eof() {
            return pos >= c.length;
        }

        char ch() {
            return c[pos];
        }
    }

    private JsonValue parse(String json) {
        if (json == null) throw new JsonParseException("Input json null");
        char[] chars = json.toCharArray();
        Index i = new Index(chars);
        JsonValue v = readValue(i);
        skipWs(i);
        if (!i.eof()) throw new JsonParseException("Trailing data at " + i.pos);
        return v;
    }

    private JsonValue readValue(Index i) {
        skipWs(i);
        if (i.eof()) throw new JsonParseException("EOF");
        char ch = i.ch();
        switch (ch) {
            case 'n':
                return readNull(i);
            case 't':
                return readTrue(i);
            case 'f':
                return readFalse(i);
            case '"':
                return new JsonPrimitive(readString(i));
            case '{':
                return readObject(i);
            case '[':
                return readArray(i);
            default:
                return readNumber(i);
        }
    }

    private void skipWs(Index i) {
        while (!i.eof()) {
            char c = i.ch();
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') i.pos++;
            else break;
        }
    }

    private void expect(Index i, char expected) {
        if (i.eof() || i.ch() != expected)
            throw new JsonParseException("Expected '" + expected + "' at " + i.pos);
        i.pos++;
    }

    private JsonValue readNull(Index i) {
        expect(i, 'n');
        expect(i, 'u');
        expect(i, 'l');
        expect(i, 'l');
        return JsonNull.INSTANCE;
    }

    private JsonValue readTrue(Index i) {
        expect(i, 't');
        expect(i, 'r');
        expect(i, 'u');
        expect(i, 'e');
        return new JsonPrimitive(Boolean.TRUE);
    }

    private JsonValue readFalse(Index i) {
        expect(i, 'f');
        expect(i, 'a');
        expect(i, 'l');
        expect(i, 's');
        expect(i, 'e');
        return new JsonPrimitive(Boolean.FALSE);
    }

    private String readString(Index i) {
        expect(i, '"');
        StringBuilder sb = new StringBuilder();
        while (!i.eof()) {
            char c = i.ch();
            i.pos++;
            if (c == '"') break;
            if (c == '\\') {
                if (i.eof()) throw new JsonParseException("Bad escape");
                char e = i.ch();
                i.pos++;
                switch (e) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        if (i.pos + 4 > i.c.length) throw new JsonParseException("Bad unicode");
                        int code = 0;
                        for (int k = 0; k < 4; k++) {
                            code = (code << 4) + hex(i.c[i.pos + k]);
                        }
                        sb.append((char) code);
                        i.pos += 4;
                        break;
                    default:
                        throw new JsonParseException("Bad escape " + e);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int hex(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'a' && c <= 'f') return 10 + (c - 'a');
        if (c >= 'A' && c <= 'F') return 10 + (c - 'A');
        throw new JsonParseException("Hex");
    }

    private JsonValue readNumber(Index i) {
        int start = i.pos;
        boolean hasDot = false, hasExp = false;
        if (i.ch() == '-') {
            i.pos++;
        }
        while (!i.eof()) {
            char c = i.ch();
            if (c >= '0' && c <= '9') {
                i.pos++;
                continue;
            }
            if (c == '.') {
                if (hasDot) break;
                hasDot = true;
                i.pos++;
                continue;
            }
            if (c == 'e' || c == 'E') {
                if (hasExp) break;
                hasExp = true;
                i.pos++;
                if (!i.eof()) {
                    char s = i.ch();
                    if (s == '+' || s == '-') i.pos++;
                }
                continue;
            }
            break;
        }
        String num = new String(i.c, start, i.pos - start);
        try {
            if (hasDot || hasExp) return new JsonPrimitive(Double.parseDouble(num));
            long lv = Long.parseLong(num);
            if (lv >= Integer.MIN_VALUE && lv <= Integer.MAX_VALUE)
                return new JsonPrimitive((int) lv);
            return new JsonPrimitive(lv);
        } catch (NumberFormatException ex) {
            throw new JsonParseException("Bad number " + num);
        }
    }

    private JsonObject readObject(Index i) {
        expect(i, '{');
        JsonObject o = new JsonObject();
        skipWs(i);
        if (!i.eof() && i.ch() == '}') {
            i.pos++;
            return o;
        }
        while (true) {
            skipWs(i);
            if (i.eof() || i.ch() != '"') throw new JsonParseException("Expected key");
            String k = readString(i);
            skipWs(i);
            expect(i, ':');
            JsonValue v = readValue(i);
            o.map.put(k, v);
            skipWs(i);
            if (i.eof()) throw new JsonParseException("Unterminated object");
            char c = i.ch();
            i.pos++;
            if (c == '}') break;
            if (c != ',') throw new JsonParseException("Expected , or }");
        }
        return o;
    }

    private JsonArray readArray(Index i) {
        expect(i, '[');
        JsonArray a = new JsonArray();
        skipWs(i);
        if (!i.eof() && i.ch() == ']') {
            i.pos++;
            return a;
        }
        while (true) {
            JsonValue v = readValue(i);
            a.list.add(v);
            skipWs(i);
            if (i.eof()) throw new JsonParseException("Unterminated array");
            char c = i.ch();
            i.pos++;
            if (c == ']') break;
            if (c != ',') throw new JsonParseException("Expected , or ]");
        }
        return a;
    }

    // ---- Field binding (with alternate names) ----
    private static class FieldBinding {
        final Field field;
        final String[] allNames;
        final Type genericType;
        final boolean emptyStringAsNull;

        FieldBinding(Field f, String primary, String[] all, Type gt, boolean empty) {
            this.field = f;
            this.allNames = all;
            this.genericType = gt;
            this.emptyStringAsNull = empty;
        }
    }

    private final Map<Class<?>, FieldBinding[]> fieldCache = new ConcurrentHashMap<>();

    private FieldBinding[] bindingsFor(Class<?> raw) {
        Field[] fields = raw.getDeclaredFields();
        List<FieldBinding> list = new ArrayList<>();
        for (Field f : fields) {
            int mod = f.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isTransient(mod) || f.isSynthetic()) continue;
            try {
                try {
                    f.setAccessible(true);
                } catch (Throwable t) {
                }
            } catch (Throwable ignore) {
                continue;
            }
            boolean empty = false;
            String primary = f.getName();
            List<String> all = new ArrayList<>();
            all.add(primary);
            try {
                SerializedName hpSer = f.getAnnotation(SerializedName.class);
                if (hpSer != null) {
                    String originalName = primary;
                    if (hpSer.value() != null && !hpSer.value().isEmpty()) {
                        primary = hpSer.value();
                        all.set(0, primary);
                        // ensure original Java field name is also accepted unless explicitly duplicated
                        if (!all.contains(originalName)) all.add(originalName);
                    }
                    String[] alts = hpSer.alternate();
                    if (alts != null) for (String alt : alts)
                        if (alt != null && !alt.isEmpty() && !all.contains(alt)) all.add(alt);
                }
                if (f.isAnnotationPresent(EmptyStringAsNull.class)) empty = true;
            } catch (Throwable ignore) {
            }
            list.add(new FieldBinding(f, primary, all.toArray(new String[0]), f.getGenericType(), empty));
        }
        FieldBinding[] arr = list.toArray(new FieldBinding[0]);
        fieldCache.put(raw, arr);
        return arr;
    }

    // ---- Deserialization ----
    private <T> T fromJsonInternal(String json, Type t) {
        JsonValue root = parse(json);
        return (T) convert(root, t);
    }

    private Object convert(JsonValue node, Type type) {
        if (type instanceof Class) {
            return convertClass(node, (Class<?>) type);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Class<?> raw = (Class<?>) pt.getRawType();
            if (Collection.class.isAssignableFrom(raw)) {
                Type elem = pt.getActualTypeArguments()[0];
                return asCollectionGeneric(node, raw, elem);
            }
            if (Map.class.isAssignableFrom(raw)) {
                Type kt = pt.getActualTypeArguments()[0];
                Type vt = pt.getActualTypeArguments()[1];
                return asMapGeneric(node, raw, kt, vt);
            }
            return convertClass(node, raw);
        }
        return convertClass(node, Object.class);
    }

    private Object convertClass(JsonValue node, Class<?> cls) {
        CustomDeserializer<Object> cd = customDeserializers.get(cls);
        if (cd != null) try {
            return cd.deserialize(node, this, cls);
        } catch (Exception e) {
            throw new JsonParseException("Custom deserializer: " + e.getMessage());
        }
        if (cls == String.class) return asString(node);
        if (cls == Object.class) return convertToJava(node);
        if (cls == Boolean.class || cls == boolean.class) return asBoolean(node);
        if (Number.class.isAssignableFrom(cls) || cls.isPrimitive()) return coerceNumber(node, cls);
        if (Date.class.isAssignableFrom(cls)) return asDate(node);
        if (cls.isEnum()) return asEnum(node, cls);
        if (cls.isArray()) return asArray(node, cls.getComponentType());
        if (Collection.class.isAssignableFrom(cls)) return asCollection(node, cls, Object.class);
        if (Map.class.isAssignableFrom(cls)) return asMap(node, cls, String.class, Object.class);
        return asPojo(node, cls);
    }

    private Object convertToJava(JsonValue n) {
        if (n instanceof JsonNull) return null;
        if (n instanceof JsonPrimitive) return ((JsonPrimitive) n).value;
        if (n instanceof JsonArray) {
            List<Object> l = new ArrayList<>();
            for (JsonValue v : ((JsonArray) n).list) l.add(convertToJava(v));
            return l;
        }
        if (n instanceof JsonObject) {
            Map<String, Object> m = new LinkedHashMap<>();
            for (Map.Entry<String, JsonValue> e : ((JsonObject) n).map.entrySet())
                m.put(e.getKey(), convertToJava(e.getValue()));
            return m;
        }
        return null;
    }

    private String asString(JsonValue n) {
        if (n instanceof JsonPrimitive) {
            Object v = ((JsonPrimitive) n).value;
            return v == null ? null : String.valueOf(v);
        }
        if (n instanceof JsonNull) return null;
        throw new JsonParseException("Expected string");
    }

    private Boolean asBoolean(JsonValue n) {
        if (n instanceof JsonPrimitive) {
            Object v = ((JsonPrimitive) n).value;
            if (v instanceof Boolean) return (Boolean) v;
        }
        throw new JsonParseException("Expected boolean");
    }

    private Object coerceNumber(JsonValue n, Class<?> target) {
        if (n instanceof JsonPrimitive) {
            Object v = ((JsonPrimitive) n).value;
            if (v instanceof Number) {
                return castNumber((Number) v, target);
            }
            if (lenientNumbers && v instanceof String) {
                try {
                    String s = (String) v;
                    if (s.isEmpty()) return null;
                    if (s.indexOf('.') >= 0 || s.indexOf('e') >= 0 || s.indexOf('E') >= 0)
                        return castNumber(Double.parseDouble(s), target);
                    long lv = Long.parseLong(s);
                    Number num = (lv >= Integer.MIN_VALUE && lv <= Integer.MAX_VALUE) ? (Number) (int) lv : lv;
                    return castNumber(num, target);
                } catch (Exception ignore) {
                }
            }
        }
        throw new JsonParseException("Expected number");
    }

    private Object castNumber(Number n, Class<?> target) {
        if (target == int.class || target == Integer.class) return n.intValue();
        if (target == long.class || target == Long.class) return n.longValue();
        if (target == double.class || target == Double.class) return n.doubleValue();
        if (target == float.class || target == Float.class) return n.floatValue();
        if (target == short.class || target == Short.class) return n.shortValue();
        if (target == byte.class || target == Byte.class) return n.byteValue();
        if (target == char.class || target == Character.class) return (char) n.intValue();
        return n;
    }

    private Date asDate(JsonValue n) {
        String s = asString(n);
        if (s == null) return null;
        try {
            // Strict 모드에서는 패턴 길이 및 필수 구분자/숫자 형태를 간단 검증하여 조기 실패 (SimpleDateFormat 부분 파싱/관대한 동작 보완)
            if (!lenientDate) {
                // 기대 패턴: yyyy-MM-dd'T'HH:mm:ss.SSS'Z' (길이 24)
                if (s.length() != 24 || s.charAt(4) != '-' || s.charAt(7) != '-' || s.charAt(10) != 'T'
                        || s.charAt(13) != ':' || s.charAt(16) != ':' || s.charAt(19) != '.' || s.charAt(23) != 'Z') {
                    throw new JsonParseException("Bad date: " + s);
                }
                // 숫자 자리 간단 체크 (연-월-일-시-분-초-밀리초)
                int[] ranges = {0,1,2,3,5,6,8,9,11,12,14,15,17,18,20,21,22};
                for (int idx : ranges) {
                    char c = s.charAt(idx);
                    if (c < '0' || c > '9') {
                        throw new JsonParseException("Bad date: " + s);
                    }
                }
            }
            return currentDateFormat().parse(s);
        } catch (Exception e) {
            if (lenientDate) return null;
            throw new JsonParseException("Bad date: " + s);
        }
    }

    private Object asEnum(JsonValue n, Class<?> enumCls) {
        String s = asString(n);
        if (s == null) return null;
        for (Object c : enumCls.getEnumConstants()) {
            try {
                Field f = enumCls.getField(((Enum<?>) c).name());
                SerializedName sn = f.getAnnotation(SerializedName.class);
                if (sn != null) {
                    if (sn.value().equals(s)) return c;
                    try {
                        java.lang.reflect.Method m = SerializedName.class.getDeclaredMethod("alternate");
                        Object alt = m.invoke(sn);
                        if (alt instanceof String[]) {
                            for (String a : (String[]) alt) if (s.equals(a)) return c;
                        }
                    } catch (Throwable ignore) {
                    }
                }
                if (((Enum<?>) c).name().equals(s)) return c;
            } catch (Exception ignore) {
            }
        }
        if (lenientEnum) return null;
        throw new JsonParseException("Unknown enum: " + s + " for " + enumCls.getSimpleName());
    }

    private Object asArray(JsonValue n, Class<?> comp) {
        if (!(n instanceof JsonArray)) throw new JsonParseException("Expected array");
        List<JsonValue> lst = ((JsonArray) n).list;
        Object arr = Array.newInstance(comp, lst.size());
        for (int i = 0; i < lst.size(); i++) Array.set(arr, i, convert(lst.get(i), comp));
        return arr;
    }

    private Object asCollection(JsonValue n, Class<?> collCls, Class<?> elem) {
        if (!(n instanceof JsonArray)) throw new JsonParseException("Expected array");
        Collection<Object> c = instantiateCollection(collCls);
        for (JsonValue v : ((JsonArray) n).list) c.add(convert(v, elem));
        return c;
    }

    private Object asCollectionGeneric(JsonValue n, Class<?> collCls, Type elemType) {
        if (!(n instanceof JsonArray)) throw new JsonParseException("Expected array");
        Collection<Object> c = instantiateCollection(collCls);
    for (JsonValue v : ((JsonArray) n).list) c.add(convert(v, elemType));
        return c;
    }

    private Collection<Object> instantiateCollection(Class<?> collCls) {
        if (collCls.isInterface()) {
            // Provide more suitable defaults per interface to avoid type assignment mismatch (e.g. Set field getting a List instance)
            if (java.util.Set.class.isAssignableFrom(collCls)) return new java.util.LinkedHashSet<>();
            if (java.util.Queue.class.isAssignableFrom(collCls)) return new java.util.LinkedList<>();
            return new ArrayList<>(); // List / Collection fallback
        }
        try {
            return (Collection<Object>) collCls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Fallback again choose based on assignable nature
            if (java.util.Set.class.isAssignableFrom(collCls)) return new java.util.LinkedHashSet<>();
            return new ArrayList<>();
        }
    }

    private Object asMap(JsonValue n, Class<?> mapCls, Class<?> keyCls, Class<?> valCls) {
        if (!(n instanceof JsonObject)) throw new JsonParseException("Expected object");
        Map<Object, Object> out = instantiateMap(mapCls);
        for (Map.Entry<String, JsonValue> e : ((JsonObject) n).map.entrySet()) {
            Object k = e.getKey();
            if (keyCls.isEnum()) k = asEnum(new JsonPrimitive(e.getKey()), keyCls);
            out.put(k, convert(e.getValue(), valCls));
        }
        return out;
    }

    private Object asMapGeneric(JsonValue n, Class<?> mapCls, Type keyType, Type valType) {
        if (!(n instanceof JsonObject)) throw new JsonParseException("Expected object");
        Map<Object, Object> out = instantiateMap(mapCls);
        for (Map.Entry<String, JsonValue> e : ((JsonObject) n).map.entrySet()) {
            Object k = e.getKey();
            if (keyType instanceof Class && ((Class<?>) keyType).isEnum())
                k = asEnum(new JsonPrimitive(e.getKey()), (Class<?>) keyType);
            out.put(k, convert(e.getValue(), valType));
        }
        return out;
    }

    private Map<Object, Object> instantiateMap(Class<?> mapCls) {
        if (mapCls.isInterface()) return new LinkedHashMap<>();
        try {
            return (Map<Object, Object>) mapCls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return new LinkedHashMap<>();
        }
    }

    private Object asPojo(JsonValue n, Class<?> cls) {
        if (!(n instanceof JsonObject))
            throw new JsonParseException("Expected object for " + cls.getSimpleName());
        Object inst;
        try {
            java.lang.reflect.Constructor<?> ctor = cls.getDeclaredConstructor();
            try {
                ctor.setAccessible(true);
            } catch (Throwable ignore) {
            }
            inst = ctor.newInstance();
        } catch (Exception e) {
            // Fallback strategy: attempt to use a non-default constructor with default parameter values
            inst = tryInstantiateWithoutDefaultCtor(cls);
            if (inst == null) {
                // Last resort: attempt allocation without invoking any user constructor (for classes like DeviceToken without no-arg ctor)
                inst = allocateInstanceNoCtor(cls);
            }
            if (inst == null) throw new JsonParseException("No default ctor for " + cls.getName());
        }
        Map<String, JsonValue> map = ((JsonObject) n).map;
        FieldBinding[] fbs = bindingsFor(cls);
        if (failOnUnknown) {
            Set<String> allowed = new HashSet<>();
            for (FieldBinding fb : fbs) for (String nm : fb.allNames) allowed.add(nm);
            for (String key : map.keySet())
                if (!allowed.contains(key))
                    throw new JsonParseException("Unknown field '" + key + "' for " + cls.getSimpleName());
        }
        for (FieldBinding fb : fbs) {
            JsonValue v = null;
            for (String candidate : fb.allNames) {
                v = map.get(candidate);
                if (v != null) break;
            }
            if (v == null || v instanceof JsonNull) continue;
            if (fb.emptyStringAsNull && v instanceof JsonPrimitive) {
                Object pv = ((JsonPrimitive) v).value;
                if (pv instanceof String && ((String) pv).isEmpty()) continue;
            }
            try {
                Object fieldVal;
                Type gType = fb.genericType;
                if (gType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) gType;
                    Class<?> raw = (Class<?>) pt.getRawType();
                    try {
                        if (Collection.class.isAssignableFrom(raw)) {
                            Type elemT = pt.getActualTypeArguments()[0];
                            fieldVal = buildCollectionFromParameterized(v, raw, elemT);
                        } else if (Map.class.isAssignableFrom(raw)) {
                            Type keyT = pt.getActualTypeArguments()[0];
                            Type valT = pt.getActualTypeArguments()[1];
                            fieldVal = buildMapFromParameterized(v, raw, keyT, valT);
                        } else {
                            fieldVal = convert(v, gType);
                        }
                    } catch (Throwable inner) {
                        fieldVal = convert(v, gType); // fallback generic conversion
                    }
                } else {
                    fieldVal = convert(v, gType);
                }
                fb.field.set(inst, fieldVal);
            } catch (Throwable ex) {
                // Fail-fast mode: propagate immediately
                if (!swallowFieldErrors) {
                    if (ex instanceof RuntimeException) throw (RuntimeException) ex;
                    throw new RuntimeException(ex);
                }
                // In strict date mode (swallowFieldErrors=true) rethrow date parsing errors
                if (!lenientDate) {
                    Class<?> ft = fb.field.getType();
                    if (ft == Date.class || Date.class.isAssignableFrom(ft)) {
                        if (ex instanceof RuntimeException) throw (RuntimeException) ex;
                        throw new RuntimeException(ex);
                    }
                }
                // Otherwise: silently ignore (compatibility)
            }
        }
        // Fallback: ensure every @SerializedName field has been considered (in case earlier mapping missed due to constructor issues or name array mismatch)
        try {
            Field[] rawFields = cls.getDeclaredFields();
            for (Field rf : rawFields) {
                if (Modifier.isStatic(rf.getModifiers()) || rf.isSynthetic()) continue;
                SerializedName sn = null;
                try { sn = rf.getAnnotation(SerializedName.class); } catch (Throwable ignore) {}
                if (sn == null) continue;
                try { rf.setAccessible(true); } catch (Throwable ignore) {}
                Object current = null;
                try { current = rf.get(inst); } catch (Throwable ignore) {}
                if (current != null) continue; // already set
                // Try primary annotation value
                String primaryName = sn.value();
                JsonValue jv = primaryName == null ? null : map.get(primaryName);
                if (jv == null) {
                    // Try alternates
                    String[] alts = sn.alternate();
                    if (alts != null) {
                        for (String alt : alts) {
                            if (alt == null || alt.isEmpty()) continue;
                            jv = map.get(alt);
                            if (jv != null) break;
                        }
                    }
                    // Try original field name if still null
                    if (jv == null) jv = map.get(rf.getName());
                }
                if (jv != null && !(jv instanceof JsonNull)) {
                    try {
                        Object val = convert(jv, rf.getGenericType());
                        rf.set(inst, val);
                    } catch (Throwable ignore) { }
                }
            }
        } catch (Throwable ignore) { }
        return inst;
    }

    // Helper: build collection with element type
    private Object buildCollectionFromParameterized(JsonValue v, Class<?> raw, Type elemT) {
        if (!(v instanceof JsonArray)) throw new JsonParseException("Expected array");
        Collection<Object> c = instantiateCollection(raw);
        for (JsonValue jv : ((JsonArray) v).list) c.add(convert(jv, elemT));
        return c;
    }

    // Helper: build map with key/value types
    private Object buildMapFromParameterized(JsonValue v, Class<?> raw, Type keyT, Type valT) {
        if (!(v instanceof JsonObject)) throw new JsonParseException("Expected object");
        Map<Object,Object> m = instantiateMap(raw);
        for (Map.Entry<String, JsonValue> e : ((JsonObject) v).map.entrySet()) {
            Object k = e.getKey();
            if (keyT instanceof Class && ((Class<?>) keyT).isEnum()) {
                k = asEnum(new JsonPrimitive(e.getKey()), (Class<?>) keyT);
            } else if (keyT != String.class) {
                // Attempt conversion if user supplied non-String key type (we treat JSON object keys as strings originally)
                try { k = convert(new JsonPrimitive(e.getKey()), keyT); } catch (Throwable ignore) { }
            }
            Object val = convert(e.getValue(), valT);
            m.put(k, val);
        }
        return m;
    }

    // Attempt to allocate an instance bypassing constructors (unsafe but useful for legacy classes lacking a no-arg ctor)
    private Object allocateInstanceNoCtor(Class<?> cls) {
        // Use reflection strings to avoid compile-time dependency on internal JDK classes
        try {
            java.lang.reflect.Constructor<Object> objCtor = Object.class.getDeclaredConstructor();
            objCtor.setAccessible(true);
            Class<?> rfClass = Class.forName("sun.reflect.ReflectionFactory");
            java.lang.reflect.Method getRF = rfClass.getDeclaredMethod("getReflectionFactory");
            Object rf = getRF.invoke(null);
            java.lang.reflect.Method newCtor = rfClass.getDeclaredMethod("newConstructorForSerialization", Class.class, java.lang.reflect.Constructor.class);
            java.lang.reflect.Constructor<?> intCtor = (java.lang.reflect.Constructor<?>) newCtor.invoke(rf, cls, objCtor);
            intCtor.setAccessible(true);
            return intCtor.newInstance();
        } catch (Throwable ignore) {
            try {
                Class<?> unsafeCls = Class.forName("sun.misc.Unsafe");
                java.lang.reflect.Field f = unsafeCls.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                Object unsafe = f.get(null);
                java.lang.reflect.Method alloc = unsafeCls.getMethod("allocateInstance", Class.class);
                return alloc.invoke(unsafe, cls);
            } catch (Throwable ignore2) {
                return null;
            }
        }
    }

    private Object tryInstantiateWithoutDefaultCtor(Class<?> cls) {
        // 1. Try the constructor with the fewest parameters using default values
        java.lang.reflect.Constructor<?>[] ctors = cls.getDeclaredConstructors();
        java.lang.reflect.Constructor<?> best = null;
        for (int i = 0; i < ctors.length; i++) {
            java.lang.reflect.Constructor<?> c = ctors[i];
            // Generalized heuristic: avoid invoking single-arg constructors whose parameter is a framework / runtime object
            // (e.g., android.os.Parcel, org.json.JSONObject) that we cannot meaningfully synthesize here.
            // This prevents side effects or failures and pushes us toward reflective / unsafe allocation instead.
            if (c.getParameterTypes().length == 1) {
                Class<?> p = c.getParameterTypes()[0];
                String pName = p.getName();
                if (pName.endsWith("Parcel") || pName.endsWith("JSONObject") ||
                        pName.equals("android.os.Parcel") || pName.equals("org.json.JSONObject")) {
                    continue; // skip to allow allocateInstance route
                }
            }
            if (best == null || c.getParameterTypes().length < best.getParameterTypes().length)
                best = c;
        }
        if (best != null) {
            try {
                Class<?>[] ptypes = best.getParameterTypes();
                Object[] args = new Object[ptypes.length];
                // Special case: single parameter creator-style constructor
                if (ptypes.length == 1) {
                    Class<?> p = ptypes[0];
                    // Generic builder pattern detection: look for nested static Builder with no-arg ctor and build() returning param type
                    try {
                        String builderName = p.getName() + "$Builder";
                        Class<?> builderCls = Class.forName(builderName);
                        java.lang.reflect.Constructor<?>[] bctors = builderCls.getDeclaredConstructors();
                        java.lang.reflect.Constructor<?> bctor = null;
                        for (int bi = 0; bi < bctors.length; bi++) {
                            if (bctors[bi].getParameterTypes().length == 0) {
                                bctor = bctors[bi];
                                break;
                            }
                        }
                        if (bctor != null) {
                            try {
                                bctor.setAccessible(true);
                            } catch (Throwable ignore) {
                            }
                            Object builder = bctor.newInstance();
                            java.lang.reflect.Method buildM = builderCls.getDeclaredMethod("build");
                            try {
                                buildM.setAccessible(true);
                            } catch (Throwable ignore) {
                            }
                            Object built = buildM.invoke(builder);
                            if (p.isInstance(built)) args[0] = built;
                        }
                    } catch (Throwable ignore) {
                    }
                }
                for (int i = 0; i < ptypes.length; i++) 
                    if (args[i] == null) args[i] = defaultValue(ptypes[i]);
                try {
                    best.setAccessible(true);
                } catch (Throwable ignore) {
                }
                return best.newInstance(args);
            } catch (Throwable ignore) {
            }
        }
        // 2. Try Unsafe.allocateInstance (may not be available on all Android versions)
        try {
            Class<?> unsafeCls = Class.forName("sun.misc.Unsafe");
            java.lang.reflect.Field f = unsafeCls.getDeclaredField("theUnsafe");
            try {
                f.setAccessible(true);
            } catch (Throwable ignore) {
            }
            Object unsafe = f.get(null);
            java.lang.reflect.Method m = unsafeCls.getMethod("allocateInstance", Class.class);
            return m.invoke(unsafe, cls);
        } catch (Throwable ignore) {
        }
        return null;
    }

    private Object defaultValue(Class<?> t) {
        if (!t.isPrimitive()) return null;
        if (t == boolean.class) return Boolean.FALSE;
        if (t == byte.class) return Byte.valueOf((byte) 0);
        if (t == char.class) return Character.valueOf((char) 0);
        if (t == short.class) return Short.valueOf((short) 0);
        if (t == int.class) return Integer.valueOf(0);
        if (t == long.class) return Long.valueOf(0L);
        if (t == float.class) return Float.valueOf(0f);
        if (t == double.class) return Double.valueOf(0d);
        return null;
    }

    // ---- Utility ----
    // (Removed unused asRuntime)

    // ---- ParameterizedType helper (replaces Gson TypeToken) ----
    public static ParameterizedType type(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args == null ? new Type[0] : args.clone();
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(raw.getName());
                if (args != null && args.length > 0) {
                    sb.append('<');
                    for (int i = 0; i < args.length; i++) {
                        if (i > 0) sb.append(',');
                        sb.append(args[i] instanceof Class ? ((Class) args[i]).getName() : args[i].toString());
                    }
                    sb.append('>');
                }
                return sb.toString();
            }
        };
    }
}
