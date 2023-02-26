package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;

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
}
