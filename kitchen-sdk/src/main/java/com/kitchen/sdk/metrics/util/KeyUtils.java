package com.kitchen.sdk.metrics.util;

public class KeyUtils {
  public static String replace(String key) {
    return StringUtil.isNotBlank(key) ? key.replaceAll("[\\\\.|,|#\r\t\n\"]", "").trim() : key;
  }
}
