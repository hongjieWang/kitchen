package com.kitchen.god.sdk.log4j.spi;

public interface TriggeringEventEvaluator {
    boolean isTriggeringEvent(LoggingEvent paramLoggingEvent);
}
