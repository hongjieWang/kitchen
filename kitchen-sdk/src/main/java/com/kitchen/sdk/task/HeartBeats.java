package com.kitchen.sdk.task;

import com.kitchen.sdk.loggers.MetricsLogger;
import com.kitchen.sdk.util.Utils;

import java.util.TimerTask;

public class HeartBeats extends TimerTask {
    protected static String logType = "HB";

    private String key;

    public HeartBeats(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            MetricsLogger.HB_LOGGER.info("{\"logtype\":\"" + logType + "\"" + ",\"key\":" + "\"" + this.key + "\"" + ",\"hostname\":" + "\"" + Utils.HOST_NAME + "\"" + ",\"time\":" + "\"" + Utils.getNowTime() + "\",\"v1\":1,\"v2\":0}");
        } catch (Exception e) {
        }
    }
}
