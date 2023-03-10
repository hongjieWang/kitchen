package com.kitchen.sdk.metrics.log4j;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

class RollingCalendar extends GregorianCalendar {
    private static final long serialVersionUID = -3560331770601814177L;

    int type = -1;

    RollingCalendar() {
    }

    RollingCalendar(TimeZone tz, Locale locale) {
        super(tz, locale);
    }

    void setType(int type) {
        this.type = type;
    }

    public long getNextCheckMillis(Date now) {
        return getNextCheckDate(now).getTime();
    }

    public Date getNextCheckDate(Date now) {
        int hour;
        setTime(now);
        switch (this.type) {
            case 0:
                set(13, 0);
                set(14, 0);
                add(12, 1);
                return getTime();
            case 1:
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(11, 1);
                return getTime();
            case 2:
                set(12, 0);
                set(13, 0);
                set(14, 0);
                hour = get(11);
                if (hour < 12) {
                    set(11, 12);
                } else {
                    set(11, 0);
                    add(5, 1);
                }
                return getTime();
            case 3:
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(5, 1);
                return getTime();
            case 4:
                set(7, getFirstDayOfWeek());
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(3, 1);
                return getTime();
            case 5:
                set(5, 1);
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(2, 1);
                return getTime();
        }
        throw new IllegalStateException("Unknown periodicity type.");
    }
}
