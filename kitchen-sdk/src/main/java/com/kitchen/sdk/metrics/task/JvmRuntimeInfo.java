package com.kitchen.sdk.metrics.task;

import com.kitchen.sdk.metrics.loggers.MetricsLogger;
import com.kitchen.sdk.metrics.util.Utils;

public class JvmRuntimeInfo extends AbstractJvmInfo {
    private String key;

    public JvmRuntimeInfo(String key) {
        this.key = key;
    }

    public void run() {
        try {
            MetricsLogger.JVM_LOGGER.info("{\"logtype\":\"" + logType + "\"" + ",\"key\":" + "\"" + this.key + "\"" + ",\"hostname\":" + "\"" + Utils.HOST_NAME + "\"" + ",\"time\":" + "\"" + Utils.getNowTime() + "\"" + ",\"datatype\":" + "\"" + "2" + "\"" + ",\"instancecode\":" + "\"" + instanceCode + "\"" + ",\"userdata\":" + localJvm.pickJvmRumtimeInfo() + "}");
        } catch (Throwable e) {
        }
    }
}
