package com.kitchen.sdk.log4j.spi;

public interface ThrowableRenderer {
  String[] doRender(Throwable paramThrowable);
}
