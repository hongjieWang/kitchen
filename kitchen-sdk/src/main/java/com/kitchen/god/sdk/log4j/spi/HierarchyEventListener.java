package com.kitchen.god.sdk.log4j.spi;

import com.kitchen.god.sdk.log4j.Appender;
import com.kitchen.god.sdk.log4j.Category;

public interface HierarchyEventListener {
    void addAppenderEvent(Category paramCategory, Appender paramAppender);

    void removeAppenderEvent(Category paramCategory, Appender paramAppender);
}
