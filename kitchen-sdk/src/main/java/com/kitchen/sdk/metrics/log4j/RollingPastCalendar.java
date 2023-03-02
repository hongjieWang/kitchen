package com.kitchen.sdk.metrics.log4j;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class RollingPastCalendar extends RollingCalendar {
    RollingPastCalendar() {
    }

    RollingPastCalendar(TimeZone tz, Locale locale) {
        super(tz, locale);
    }

    public long getPastCheckMillis(Date now, int maxBackupIndex) {
        return getPastDate(now, maxBackupIndex).getTime();
    }

    public Date getPastDate(Date now, int maxBackupIndex) {
        int hour;
        setTime(now);
        switch (this.type) {
            case 0:
                set(13, get(13));
                set(14, get(14));
                set(12, get(12) - maxBackupIndex);
                return getTime();
            case 1:
                set(12, get(12));
                set(13, get(13));
                set(14, get(14));
                set(11, get(11) - maxBackupIndex);
                return getTime();
            case 2:
                set(12, get(12));
                set(13, get(13));
                set(14, get(14));
                hour = get(11);
                if (hour < 12) {
                    set(11, 12);
                } else {
                    set(11, 0);
                }
                set(5, get(5) - maxBackupIndex);
                return getTime();
            case 3:
                set(11, get(11));
                set(12, get(12));
                set(13, get(13));
                set(14, get(14));
                set(5, get(5) - maxBackupIndex);
                return getTime();
            case 4:
                set(7, getFirstDayOfWeek());
                set(11, get(11));
                set(12, get(12));
                set(13, get(13));
                set(14, get(14));
                set(3, get(3) - maxBackupIndex);
                return getTime();
            case 5:
                set(5, get(5));
                set(11, get(11));
                set(12, get(12));
                set(13, get(13));
                set(14, get(14));
                set(2, get(2) - maxBackupIndex);
                return getTime();
        }
        throw new IllegalStateException("Unknown periodicity type.");
    }
}
