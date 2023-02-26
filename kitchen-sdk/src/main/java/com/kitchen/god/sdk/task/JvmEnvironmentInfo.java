package com.kitchen.god.sdk.task;

import com.kitchen.god.sdk.loggers.MetricsLogger;
import com.kitchen.god.sdk.util.Utils;

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