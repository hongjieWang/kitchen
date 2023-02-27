package com.kitchen.sdk.log4j;

import com.kitchen.sdk.log4j.spi.ErrorHandler;
import com.kitchen.sdk.log4j.spi.Filter;
import com.kitchen.sdk.log4j.spi.LoggingEvent;

public interface Appender {
    void addFilter(Filter paramFilter);

    Filter getFilter();

    void clearFilters();

    void close();

    void doAppend(LoggingEvent paramLoggingEvent);

    String getName();

    void setErrorHandler(ErrorHandler paramErrorHandler);

    ErrorHandler getErrorHandler();

    void setLayout(Layout paramLayout);

    Layout getLayout();

    void setName(String paramString);

    boolean requiresLayout();
}