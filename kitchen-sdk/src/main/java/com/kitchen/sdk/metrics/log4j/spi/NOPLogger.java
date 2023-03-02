package com.kitchen.sdk.metrics.log4j.spi;

import com.kitchen.sdk.log4j.*;
import com.kitchen.sdk.metrics.log4j.*;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

public final class NOPLogger extends Logger {
    public NOPLogger(NOPLoggerRepository repo, String name) {
        super(name);
        this.repository = repo;
        this.level = Level.OFF;
        this.parent = (Category) this;
    }

    @Override
    public void addAppender(Appender newAppender) {
    }

    @Override
    public void assertLog(boolean assertion, String msg) {
    }

    public void callAppenders(LoggingEvent event) {
    }

    void closeNestedAppenders() {
    }

    @Override
    public void debug(Object message) {
    }

    @Override
    public void debug(Object message, Throwable t) {
    }

    @Override
    public void error(Object message) {
    }

    @Override
    public void error(Object message, Throwable t) {
    }

    @Override
    public void fatal(Object message) {
    }

    @Override
    public void fatal(Object message, Throwable t) {
    }

    @Override
    public Enumeration getAllAppenders() {
        return (new Vector()).elements();
    }

    @Override
    public Appender getAppender(String name) {
        return null;
    }

    @Override
    public Level getEffectiveLevel() {
        return Level.OFF;
    }

    @Override
    public Priority getChainedPriority() {
        return (Priority) getEffectiveLevel();
    }

    public ResourceBundle getResourceBundle() {
        return null;
    }

    public void info(Object message) {
    }

    public void info(Object message, Throwable t) {
    }

    public boolean isAttached(Appender appender) {
        return false;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isEnabledFor(Priority level) {
        return false;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public void l7dlog(Priority priority, String key, Throwable t) {
    }

    public void l7dlog(Priority priority, String key, Object[] params, Throwable t) {
    }

    public void log(Priority priority, Object message, Throwable t) {
    }

    public void log(Priority priority, Object message) {
    }

    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
    }

    public void removeAllAppenders() {
    }

    public void removeAppender(Appender appender) {
    }

    public void removeAppender(String name) {
    }

    public void setLevel(Level level) {
    }

    public void setPriority(Priority priority) {
    }

    public void setResourceBundle(ResourceBundle bundle) {
    }

    public void warn(Object message) {
    }

    public void warn(Object message, Throwable t) {
    }

    public void trace(Object message) {
    }

    public void trace(Object message, Throwable t) {
    }

    public boolean isTraceEnabled() {
        return false;
    }
}
