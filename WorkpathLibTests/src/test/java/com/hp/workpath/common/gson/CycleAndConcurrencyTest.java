package com.hp.workpath.common.gson;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Phase 9: cycle detection and simple concurrency smoke.
 */
public class CycleAndConcurrencyTest extends BaseJsonParserTest {
    static class Node {
        Node next;
        int v;
    }

    @Test
    public void cycleDetectionOn() {
        parser.setDetectCycles(true);
        Node a = new Node();
        Node b = new Node();
        a.v = 1;
        b.v = 2;
        a.next = b;
        b.next = a;
        try {
            parser.toJson(a);
            fail();
        } catch (IllegalStateException expected) {
        }
    }

    @Test
    public void cycleDetectionOff() {
        parser.setDetectCycles(false);
        Node a = new Node();
        Node b = new Node();
        a.v = 1;
        b.v = 2;
        a.next = b;
        b.next = a;
        try {
            parser.toJson(a);
            fail();
        } catch (IllegalStateException expected) { /* Max depth or cycle path */ }
    }

    static class Simple {
        int i;
        String s;
    }

    @Test
    public void concurrencySmoke() throws Exception {
        int threads = 8;
        ExecutorService es = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        for (int t = 0; t < threads; t++) {
            es.submit(() -> {
                try {
                    for (int i = 0; i < 100; i++) {
                        Simple sm = parser.fromJson("{\"i\":" + i + ",\"s\":\"x\"}", Simple.class);
                        if (sm.i != i) throw new AssertionError();
                        parser.toJson(sm);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(10, TimeUnit.SECONDS);
        es.shutdownNow();
        assertEquals(0, latch.getCount());
    }
}
