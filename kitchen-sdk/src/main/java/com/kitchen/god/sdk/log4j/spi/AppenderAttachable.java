package com.kitchen.god.sdk.log4j.spi;

import com.kitchen.god.sdk.log4j.Appender;

import java.util.Enumeration;

public interface AppenderAttachable {
    void addAppender(Appender paramAppender);

    Enumeration getAllAppenders();

    Appender getAppender(String paramString);

    boolean isAttached(Appender paramAppender);

    void removeAllAppenders();

    void removeAppender(Appender paramAppender);

    void removeAppender(String paramString);
}