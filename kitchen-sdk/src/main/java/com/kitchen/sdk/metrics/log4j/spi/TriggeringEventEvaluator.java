package com.kitchen.sdk.metrics.log4j.spi;

public interface TriggeringEventEvaluator {
    boolean isTriggeringEvent(LoggingEvent paramLoggingEvent);
}
