package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;

public class RT extends AbstractMetrics {
    private final long startTime;

    public void record(long start) {
        addValues(System.currentTimeMillis() - start, 1L);
    }

    public void record() {
        addValues(System.currentTimeMillis() - this.startTime, 1L);
    }

    public RT(String key1) {
        this(key1, null, null);
    }

    public RT(String key1, String key2) {
        this(key1, key2, null);
    }

    public RT(String key1, String key2, String key3) {
        super(key1, key2, key3);
        this.startTime = System.currentTimeMillis();
    }

    public Type getMonitorType() {
        return Type.RT;
    }
}
