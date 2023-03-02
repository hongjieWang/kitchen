package com.kitchen.sdk.metrics.log4j.spi;

public interface ThrowableRenderer {
  String[] doRender(Throwable paramThrowable);
}
