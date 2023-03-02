package com.kitchen.sdk.metrics.log4j.pattern;

public final class FileDatePatternConverter {
    public static DatePatternConverter newInstance(String[] options) {
        if (options == null || options.length == 0)
            return DatePatternConverter.newInstance(new String[]{"yyyy-MM-dd"});
        return DatePatternConverter.newInstance(options);
    }
}