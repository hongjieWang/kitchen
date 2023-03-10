package com.kitchen.sdk.metrics.log4j.spi;

import com.kitchen.sdk.metrics.log4j.Appender;
import com.kitchen.sdk.metrics.log4j.Category;
import com.kitchen.sdk.metrics.log4j.Level;
import com.kitchen.sdk.metrics.log4j.Logger;

import java.util.Enumeration;
import java.util.Vector;

public final class NOPLoggerRepository implements LoggerRepository {
    public void addHierarchyEventListener(HierarchyEventListener listener) {
    }

    public boolean isDisabled(int level) {
        return true;
    }

    public void setThreshold(Level level) {
    }

    public void setThreshold(String val) {
    }

    public void emitNoAppenderWarning(Category cat) {
    }

    public Level getThreshold() {
        return Level.OFF;
    }

    public Logger getLogger(String name) {
        return new NOPLogger(this, name);
    }

    public Logger getLogger(String name, LoggerFactory factory) {
        return new NOPLogger(this, name);
    }

    public Logger getRootLogger() {
        return new NOPLogger(this, "root");
    }

    public Logger exists(String name) {
        return null;
    }

    public void shutdown() {
    }

    public Enumeration getCurrentLoggers() {
        return (new Vector()).elements();
    }

    public Enumeration getCurrentCategories() {
        return getCurrentLoggers();
    }

    public void fireAddAppenderEvent(Category logger, Appender appender) {
    }

    public void resetConfiguration() {
    }
}