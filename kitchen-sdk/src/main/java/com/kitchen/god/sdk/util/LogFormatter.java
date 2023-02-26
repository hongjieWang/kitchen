package com.kitchen.god.sdk.util;

public class LogFormatter {
  public static String format(String messagePattern, Object... args) {
    return MessageFormatter.arrayFormat(messagePattern, args);
  }
}
