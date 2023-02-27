package com.kitchen.sdk.log4j.spi;

import com.kitchen.sdk.log4j.Appender;
import com.kitchen.sdk.log4j.Category;

public interface HierarchyEventListener {
    void addAppenderEvent(Category paramCategory, Appender paramAppender);

    void removeAppenderEvent(Category paramCategory, Appender paramAppender);
}
