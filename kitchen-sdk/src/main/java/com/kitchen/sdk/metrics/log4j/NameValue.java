package com.kitchen.sdk.metrics.log4j;

class NameValue {
  String key;
  
  String value;
  
  public NameValue(String key, String value) {
    this.key = key;
    this.value = value;
  }
  
  public String toString() {
    return this.key + "=" + this.value;
  }
}
