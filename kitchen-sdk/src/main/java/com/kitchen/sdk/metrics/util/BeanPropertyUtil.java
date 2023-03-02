package com.kitchen.sdk.metrics.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class BeanPropertyUtil {
    public static Object getProperty(String m, Object obj) {
        if (obj instanceof Map)
            return ((Map) obj).get(m);
        if (obj.getClass().isArray())
            return null;
        String firstUpperMethod = getFirstUpper(m);
        Method method = getMethodByName("get" + firstUpperMethod, obj);
        if (method == null)
            method = getMethodByName("is" + firstUpperMethod, obj);
        if (method == null)
            return getField(m, obj);
        try {
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException ignored) {

        } catch (InvocationTargetException ignored) {
        }
        return null;
    }

    private static Object getField(String m, Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField(m);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException ignored) {

        } catch (IllegalAccessException ignored) {
        }
        return null;
    }

    private static Method getMethodByName(String name, Object obj) {
        try {
            return obj.getClass().getMethod(name, new Class[0]);
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    private static String getFirstUpper(String method) {
        String substring = method.substring(0, 1);
        return substring.toUpperCase() + method.substring(1);
    }
}