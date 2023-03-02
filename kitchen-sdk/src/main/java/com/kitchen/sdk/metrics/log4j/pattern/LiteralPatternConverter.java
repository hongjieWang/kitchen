package com.kitchen.sdk.metrics.log4j.pattern;

import com.kitchen.sdk.metrics.log4j.spi.LoggingEvent;

public final class LiteralPatternConverter extends LoggingEventPatternConverter {
    private final String literal;

    public LiteralPatternConverter(String literal) {
        super("Literal", "literal");
        this.literal = literal;
    }

    @Override
    public void format(LoggingEvent event, StringBuffer toAppendTo) {
        toAppendTo.append(this.literal);
    }

    @Override
    public void format(Object obj, StringBuffer toAppendTo) {
        toAppendTo.append(this.literal);
    }
}
