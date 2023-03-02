package com.kitchen.sdk.metrics.log4j;

import com.kitchen.sdk.metrics.log4j.spi.LoggingEvent;
import com.kitchen.sdk.metrics.log4j.spi.OptionHandler;

/**
 * @author wanghongjie
 */
public abstract class Layout implements OptionHandler {
    public static final String LINE_SEP = System.getProperty("line.separator");

    public static final int LINE_SEP_LEN = LINE_SEP.length();

    public abstract String format(LoggingEvent paramLoggingEvent);

    public String getContentType() {
        return "text/plain";
    }

    public String getHeader() {
        return null;
    }

    public String getFooter() {
        return null;
    }

    public abstract boolean ignoresThrowable();
}
