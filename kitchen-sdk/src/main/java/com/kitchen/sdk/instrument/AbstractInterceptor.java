package com.kitchen.sdk.instrument;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghongjie
 */
public abstract class AbstractInterceptor {
    private Method method;

    private final String methodName;

    private final String className;

    private Class<?> aClass;

    private Object[] params;

    public AbstractInterceptor(String methodName, String className, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

    private Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }

    private Method getMethod(String name, Class<?> aClass, Object[] params) {
        try {
            if (params.length == 0) {
                return aClass.getDeclaredMethod(name);
            }
            List<Method> methods = new ArrayList<>();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method cur : declaredMethods) {
                if (cur.getName().equals(name) && (cur.getParameterTypes()).length == params.length) {
                    methods.add(cur);
                }
            }
            if (methods.size() == 1) {
                return methods.get(0);
            }
            Method realMethod = null;
            for (Method method : methods) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                boolean same = true;
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> tmp = parameterTypes[i];
                    boolean compare = (tmp.equals(params[i].getClass()) || tmp.isAssignableFrom(params[i].getClass()));
                    if (!compare) {
                        same = false;
                        break;
                    }
                }
                if (same && adjust(method)) {
                    realMethod = method;
                    break;
                }
            }
            return realMethod;
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    protected abstract boolean adjust(Method paramMethod);

    public Method getMethod() {
        return this.method;
    }

    public Class<?> getaClass() {
        return this.aClass;
    }

    public Object[] getParams() {
        return this.params;
    }

    public AbstractInterceptor start() {
        try {
            this.aClass = getClass(this.className);
            this.method = getMethod(this.methodName, this.aClass, this.params);
        } catch (Throwable e) {
        }
        return this;
    }

    public abstract void finish(Object paramObject);

    public abstract void onException(Throwable paramThrowable);
}
