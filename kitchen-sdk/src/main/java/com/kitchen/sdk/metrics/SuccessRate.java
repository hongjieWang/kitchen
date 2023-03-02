package com.kitchen.sdk.metrics;

import com.kitchen.sdk.metrics.common.Type;

public class SuccessRate extends AbstractMetrics {
    public SuccessRate(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    public SuccessRate(String key1, String key2, String key3, String ev) {
        super(key1, key2, key3, ev);
    }

    public void incrTotal() {
        addValues(0L, 1L);
    }

    public void incrSuccess() {
        addValues(1L, 0L);
    }

    @Override
    public Type getMonitorType() {
        return Type.SUCCESS_RATE;
    }
}

