package com.kitchen.god.sdk.common;

import java.util.concurrent.TimeUnit;

public interface Conts {
    public static final String QUOTATION = "\"";

    public static final String COLON = ":";

    public static final String COMMA = ",";

    public static final String NULL = "NULL";

    public static final String LINE_SEP = System.getProperty("line.separator");

    public static final long TIME_HEARTBEATS = TimeUnit.SECONDS.toMillis(20L);

    public static final long JVMTIME_R = TimeUnit.SECONDS.toMillis(60L);

    public static final long JVMTIME_E = TimeUnit.HOURS.toMillis(4L);

    public static final long KV_PERIOD = TimeUnit.SECONDS.toMillis(60L);

    public static final long DB_PERIOD = TimeUnit.SECONDS.toMillis(60L);
}
