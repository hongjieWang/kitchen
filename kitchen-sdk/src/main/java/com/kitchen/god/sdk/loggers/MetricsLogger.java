package com.kitchen.god.sdk.loggers;

import com.kitchen.god.sdk.log4j.Logger;

public class MetricsLogger {
    public static final MetricsLogger KV_LOGGER = new MetricsLogger(MetricsLoggerFactory.getLogger("key"));

    public static final MetricsLogger HB_LOGGER = new MetricsLogger(MetricsLoggerFactory.getLogger("hb"));

    public static final MetricsLogger JVM_LOGGER = new MetricsLogger(MetricsLoggerFactory.getLogger("jvm"));

    public static final MetricsLogger DB_LOGGER = new MetricsLogger(MetricsLoggerFactory.getLogger("db"));

    public static final MetricsLogger SQL_LOGGER = new MetricsLogger(MetricsLoggerFactory.getLogger("slowsql"));

    private Logger logger;

    public MetricsLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        this.logger.info(message);
    }

    public Logger getLogger() {
        return this.logger;
    }
}