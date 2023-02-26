package com.kitchen.god.sdk.util;

public class KeyUtils {
  public static String replace(String key) {
    return StringUtil.isNotBlank(key) ? key.replaceAll("[\\\\.|,|#\r\t\n\"]", "").trim() : key;
  }
}
