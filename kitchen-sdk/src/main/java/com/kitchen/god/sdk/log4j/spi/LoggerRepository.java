package com.kitchen.god.sdk.log4j.spi;

import com.kitchen.god.sdk.log4j.Appender;
import com.kitchen.god.sdk.log4j.Category;
import com.kitchen.god.sdk.log4j.Level;
import com.kitchen.god.sdk.log4j.Logger;

import java.util.Enumeration;

public interface LoggerRepository {
    void addHierarchyEventListener(HierarchyEventListener paramHierarchyEventListener);

    boolean isDisabled(int paramInt);

    void setThreshold(Level paramLevel);

    void setThreshold(String paramString);

    void emitNoAppenderWarning(Category paramCategory);

    Level getThreshold();

    Logger getLogger(String paramString);

    Logger getLogger(String paramString, LoggerFactory paramLoggerFactory);

    Logger getRootLogger();

    Logger exists(String paramString);

    void shutdown();

    Enumeration getCurrentLoggers();

    void fireAddAppenderEvent(Category paramCategory, Appender paramAppender);

    void resetConfiguration();
}
