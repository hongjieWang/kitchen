package com.kitchen.sdk.metrics;

import com.kitchen.sdk.metrics.MetricsClient;
import com.kitchen.sdk.metrics.annotation.Metrics;
import com.kitchen.sdk.metrics.common.Type;
import com.kitchen.sdk.metrics.util.BeanPropertyUtil;
import com.kitchen.sdk.metrics.util.StringUtil;

import java.lang.reflect.Method;

public abstract class MetricsUtil {
    public static void doMetrics(MetricsClient client, Method method) {
        if (client == null) {
            return;
        }
        try {
            Metrics annotation = method.getAnnotation(Metrics.class);
            Type[] types = annotation.types();
            for (Type type : types) {
                if (type == Type.SUCCESS_RATE) {
                    client.sr_incrTotal();
                }
                if (type == Type.QPS) {
                    client.qps();
                }
                if (type == Type.RT) {
                    client.rt();
                }
            }
        } catch (Throwable e) {
        }
    }

    public static void doSuccess(MetricsClient client, Method method) {
        if (client == null) {
            return;
        }
        try {
            Metrics annotation = method.getAnnotation(Metrics.class);
            Type[] types = annotation.types();
            for (Type type : types) {
                if (type == Type.SUCCESS_RATE) {
                    client.sr_incrSuccess();
                }
            }
        } catch (Throwable e) {
        }
    }

    public static MetricsClient createClient(Class obj, Object[] allArguments, Method method) {
        MetricsClient client;
        Metrics annotation = method.getAnnotation(Metrics.class);
        String[] keys = null;
        if ((annotation.value()).length == 0) {
            keys = new String[]{obj.getSimpleName(), method.getName()};
        } else {
            String[] value = annotation.value();
            keys = new String[value.length];
            for (int i = 0; i < value.length; i++) {
                if (value[i].startsWith("#")) {
                    keys[i] = getKey(value[i], allArguments);
                } else {
                    keys[i] = value[i];
                }
            }
        }
        String environment = "default";
        if (StringUtil.isNotBlank(annotation.environment())) {
            environment = annotation.environment();
        }
        checkKeys(keys);
        if (keys.length == 1) {
            client = MetricsClient.newInstance(keys[0], "", "", environment);
        } else if (keys.length == 2) {
            client = MetricsClient.newInstance(keys[0], keys[1], "", environment);
        } else {
            client = MetricsClient.newInstance(keys[0], keys[1], keys[2], environment);
        }
        return client;
    }

    private static void checkKeys(String[] keys) {
        if (keys == null) {
            throw new IllegalArgumentException();
        }
        for (String k : keys) {
            if (k == null || k.length() == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static String getKey(String key, Object[] allArguments) {
        key = key.substring(1);
        String[] split = key.split("\\.");
        if (!split[0].startsWith("$")) {
            throw new IllegalArgumentException();
        }
        int argIndex = Integer.parseInt(split[0].substring(1));
        Object allArgument = allArguments[argIndex];
        return getKeyValue(split, allArgument);
    }

    private static String getKeyValue(String[] split, Object arg) {
        if (split.length == 1) {
            return toString(arg);
        }
        Object obj = arg;
        for (int i = 1; i < split.length; i++) {
            obj = BeanPropertyUtil.getProperty(split[i], obj);
            if (obj == null) {
                return null;
            }
        }
        return toString(obj);
    }

    public static boolean isRealSuccess(Method method, Throwable e) {
        Class<?>[] exclude = method.getAnnotation(Metrics.class).exclude();
        for (Class<?> c : exclude) {
            if (c.equals(e.getClass())) {
                return true;
            }
        }
        return false;
    }

    private static String toString(Object arg) {
        return String.valueOf(arg);
    }
}
