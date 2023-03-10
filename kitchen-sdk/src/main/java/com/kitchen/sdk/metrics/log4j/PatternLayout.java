package com.kitchen.sdk.metrics.log4j;

import com.kitchen.sdk.metrics.log4j.helpers.PatternConverter;
import com.kitchen.sdk.metrics.log4j.helpers.PatternParser;

import com.kitchen.sdk.metrics.log4j.spi.LoggingEvent;

public class PatternLayout extends Layout {
    public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";

    public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";

    protected final int BUF_SIZE = 256;

    protected final int MAX_CAPACITY = 1024;

    private StringBuffer sbuf = new StringBuffer(256);

    private String pattern;

    private PatternConverter head;

    public PatternLayout() {
        this("%m%n");
    }

    public PatternLayout(String pattern) {
        this.pattern = pattern;
        this.head = createPatternParser((pattern == null) ? "%m%n" : pattern).parse();
    }

    public void setConversionPattern(String conversionPattern) {
        this.pattern = conversionPattern;
        this.head = createPatternParser(conversionPattern).parse();
    }

    public String getConversionPattern() {
        return this.pattern;
    }

    public void activateOptions() {
    }

    public boolean ignoresThrowable() {
        return true;
    }

    protected PatternParser createPatternParser(String pattern) {
        return new PatternParser(pattern);
    }

    @Override
    public String format(LoggingEvent event) {
        if (this.sbuf.capacity() > 1024) {
            this.sbuf = new StringBuffer(256);
        } else {
            this.sbuf.setLength(0);
        }
        PatternConverter c = this.head;
        while (c != null) {
            c.format(this.sbuf, event);
            c = c.next;
        }
        return this.sbuf.toString();
    }
}