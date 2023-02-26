package com.kitchen.god.sdk.log4j.or;

class DefaultRenderer implements ObjectRenderer {
  public String doRender(Object o) {
    try {
      return o.toString();
    } catch (Exception ex) {
      return ex.toString();
    } 
  }
}
