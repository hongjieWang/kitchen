
package com.kitchen.sdk.metrics;

import com.kitchen.sdk.metrics.common.Type;

public class Cur extends AbstractMetrics {
    public Cur(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    public Cur(String key1, String key2, String key3, String ev) {
        super(key1, key2, key3, ev);
    }

    @Override
    public Type getMonitorType() {
        return Type.CUR;
    }

    public void setValue(long v1) {
        updateValues(v1, 0L);
    }
}