package com.kitchen.sdk.task;

import com.kitchen.sdk.loggers.MetricsLogger;
import com.kitchen.sdk.util.Utils;

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
