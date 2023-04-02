package com.kitchen.sdk.metrics;

import com.kitchen.sdk.metrics.common.Type;

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

    /**
     * @param key1
     * @param key2
     * @param key3
     * @param environment 运行环境 生产、测试
     */
    public RT(String key1, String key2, String key3, String environment) {
        super(key1, key2, key3, environment);
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public Type getMonitorType() {
        return Type.RT;
    }
}
