package com.kitchen.sdk.log4j.spi;

import com.kitchen.sdk.log4j.or.ObjectRenderer;
import com.kitchen.sdk.log4j.or.RendererMap;

public interface RendererSupport {
  RendererMap getRendererMap();
  
  void setRenderer(Class paramClass, ObjectRenderer paramObjectRenderer);
}
