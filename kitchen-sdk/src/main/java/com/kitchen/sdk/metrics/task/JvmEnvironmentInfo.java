package com.kitchen.sdk.metrics.task;

import com.kitchen.sdk.metrics.loggers.MetricsLogger;
import com.kitchen.sdk.metrics.util.Utils;

public class JvmEnvironmentInfo extends AbstractJvmInfo {
    private String key;

    public JvmEnvironmentInfo(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            MetricsLogger.JVM_LOGGER.info("{\"logtype\":\"" + logType + "\"" + ",\"key\":" + "\"" + this.key + "\"" + ",\"hostname\":" + "\"" + Utils.HOST_NAME + "\"" + ",\"time\":" + "\"" + Utils.getNowTime() + "\"" + ",\"datatype\":" + "\"" + "1" + "\"" + ",\"instancecode\":" + "\"" + instanceCode + "\"" + ",\"userdata\":" + localJvm.pickJvmEnvironmentInfo() + "}");
        } catch (Throwable e) {
        }
    }
}