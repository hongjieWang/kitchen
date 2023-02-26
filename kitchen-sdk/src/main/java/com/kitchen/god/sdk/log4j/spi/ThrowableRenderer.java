package com.kitchen.god.sdk.log4j.spi;

public interface ThrowableRenderer {
  String[] doRender(Throwable paramThrowable);
}
