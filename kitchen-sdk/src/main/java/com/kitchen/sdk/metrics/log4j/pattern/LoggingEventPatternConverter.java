package com.kitchen.sdk.metrics.log4j.pattern;

import com.kitchen.sdk.metrics.log4j.spi.LoggingEvent;

public abstract class LoggingEventPatternConverter extends PatternConverter {
    protected LoggingEventPatternConverter(String name, String style) {
        super(name, style);
    }

    public abstract void format(LoggingEvent paramLoggingEvent, StringBuffer paramStringBuffer);

    @Override
    public void format(Object obj, StringBuffer output) {
        if (obj instanceof LoggingEvent)
            format((LoggingEvent) obj, output);
    }

    public boolean handlesThrowable() {
        return false;
    }
}
