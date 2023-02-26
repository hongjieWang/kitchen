package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;

public class Common extends AbstractMetrics {
    public Common(String key1, String key2, String key3) {
        super(key1, key2, key3);
    }

    @Override
    public Type getMonitorType() {
        return Type.DEFAULT;
    }

    public void setValue1(long v1) {
        addValues(v1, 0L);
    }

    public void setValue2(long v2) {
        addValues(0L, v2);
    }

    public void setValue1AndValue2(long v1, long v2) {
        addValues(v1, v2);
    }
}
