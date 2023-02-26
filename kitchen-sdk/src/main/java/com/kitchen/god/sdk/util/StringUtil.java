package com.kitchen.god.sdk.util;

public class StringUtil {
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean isBlank(String s) {
        return s == null || ((s.length() == 0));
    }

    public static String abbreviate(String str, int maxWidth) {
        if (str == null || str.length() <= maxWidth) {
            return str;
        }
        return str.substring(0, maxWidth);
    }
}
