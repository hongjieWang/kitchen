package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;

public class Hit extends AbstractMetrics {
    public Hit(String key1) {
        super(key1, null, null);
    }

    public Hit(String key1, String key2) {
        super(key1, key2, null);
    }

    public Hit(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    @Override
    public Type getMonitorType() {
        return Type.HIT_RATE;
    }

    public void incrTotal() {
        addValues(0L, 1L);
    }

    public void incrHit() {
        addValues(1L, 0L);
    }
}
