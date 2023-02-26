package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;

public class FailRate extends AbstractMetrics {
    public FailRate(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    @Override
    public Type getMonitorType() {
        return Type.FAIL_RATE;
    }

    public void incrTotal() {
        addValues(0L, 1L);
    }

    public void incrFail() {
        addValues(1L, 0L);
    }
}