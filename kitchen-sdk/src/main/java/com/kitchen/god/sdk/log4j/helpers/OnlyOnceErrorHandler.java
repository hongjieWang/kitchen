package com.kitchen.god.sdk.log4j.helpers;

import com.kitchen.god.sdk.log4j.Appender;
import com.kitchen.god.sdk.log4j.Logger;
import com.kitchen.god.sdk.log4j.spi.ErrorHandler;
import com.kitchen.god.sdk.log4j.spi.LoggingEvent;

public class OnlyOnceErrorHandler implements ErrorHandler {
    final String WARN_PREFIX = "log4j warning: ";

    final String ERROR_PREFIX = "log4j error: ";

    boolean firstTime = true;

    @Override
    public void setLogger(Logger logger) {
    }

    @Override
    public void activateOptions() {
    }

    @Override
    public void error(String message, Exception e, int errorCode) {
        error(message, e, errorCode, null);
    }

    @Override
    public void error(String message, Exception e, int errorCode, LoggingEvent event) {
        if (e instanceof java.io.InterruptedIOException || e instanceof InterruptedException)
            Thread.currentThread().interrupt();
        if (this.firstTime) {
            LogLog.error(message, e);
            this.firstTime = false;
        }
    }

    @Override
    public void error(String message) {
        if (this.firstTime) {
            LogLog.error(message);
            this.firstTime = false;
        }
    }

    @Override
    public void setAppender(Appender appender) {
    }

    @Override
    public void setBackupAppender(Appender appender) {
    }
}
