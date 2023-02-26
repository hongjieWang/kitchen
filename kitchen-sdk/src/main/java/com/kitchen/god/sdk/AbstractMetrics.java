package com.kitchen.god.sdk;

import com.kitchen.god.sdk.common.Type;
import com.kitchen.god.sdk.kv.KeysObject;
import com.kitchen.god.sdk.kv.KvHelpers;
import com.kitchen.god.sdk.loggers.MetricsLoggerFactory;
import com.kitchen.god.sdk.util.StringUtil;

/**
 * @author wanghongjie
 */
public abstract class AbstractMetrics {
    private final KeysObject keysObject;

    static {
        MetricsLoggerFactory.init();
    }

    protected AbstractMetrics(String key1, String key2, String key3) {
        this.keysObject = new KeysObject(normal(key1), normal(key2), normal(key3), getMonitorType());
    }

    protected void updateValues(long v1, long v2) {
        KvHelpers.updateKeysValues(this.keysObject, v1, v2);
    }

    protected void addValues(long v1, long v2) {
        KvHelpers.addKeysValues(this.keysObject, v1, v2);
    }

    protected String normal(String key) {
        String k = StringUtil.isNotBlank(key) ? key.replaceAll("[\\\\.|,|#\r\t\n\"]", "").trim() : key;
        return StringUtil.abbreviate(k, 100);
    }

    protected abstract Type getMonitorType();
}
