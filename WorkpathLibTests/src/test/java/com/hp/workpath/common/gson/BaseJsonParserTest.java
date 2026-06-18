package com.hp.workpath.common.gson;

import com.hp.workpath.common.utils.JsonParser;
import org.junit.After;import org.junit.Before;
import java.util.TimeZone;

/** Common setup/teardown for JsonParser tests (gson-style suite). */
public abstract class BaseJsonParserTest {
    protected JsonParser parser;
    @Before public void baseSetUp(){ parser = JsonParser.getInstance(); resetDefaults(); }
    @After public void baseTearDown(){ resetDefaults(); }
    protected void resetDefaults(){ parser.setDetectCycles(true)
            .setFailOnUnknown(false)
            .setLenientNumbers(true)
            .setLenientEnum(true)
            .setLenientDate(true)
            .setSwallowFieldErrors(true)
            .setSerializeNulls(false)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setDateTimeZone(TimeZone.getTimeZone("UTC")); }
}
