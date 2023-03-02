package com.kitchen.sdk;

import com.kitchen.sdk.common.Type;

public class QPS extends AbstractMetrics {
    public void record() {
        addValues(1L, 0L);
    }

    public void record(int times) {
        addValues(times, 0L);
    }

    @Override
    public Type getMonitorType() {
        return Type.QPS;
    }

    public QPS(String key1) {
        super(key1, null, null);
    }

    public QPS(String key1, String key2) {
        super(key1, key2, null);
    }

    public QPS(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    /**
     * @param key1
     * @param key2
     * @param key3
     * @param environment 运行环境 生产、测试
     */
    public QPS(String key1, String key2, String key3, String environment) {
        super(key1, key2, key3, environment);
    }
}
