package com.kitchen.sdk.metrics.log4j.spi;

public interface ThrowableRendererSupport {
  ThrowableRenderer getThrowableRenderer();
  
  void setThrowableRenderer(ThrowableRenderer paramThrowableRenderer);
}
