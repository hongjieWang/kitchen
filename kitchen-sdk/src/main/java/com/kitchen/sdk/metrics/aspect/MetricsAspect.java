package com.kitchen.sdk.metrics.aspect;

import com.kitchen.sdk.metrics.MetricsClient;
import com.kitchen.sdk.metrics.annotation.Metrics;
import com.kitchen.sdk.metrics.common.Type;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * AOP 方式实现拦截
 *
 * @author wanghongjie
 */
@Aspect
@Component
public class MetricsAspect {
    @Pointcut("@annotation(com.kitchen.sdk.metrics.annotation.Metrics)")
    public void auditRecord() {

    }

    @Around(value = "auditRecord()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            // 获取访问的方法
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            // 获取自定义参数
            Metrics metrics = method.getAnnotation(Metrics.class);
            String[] keys = metrics.value();
            MetricsClient client;
            checkKeys(keys);
            if (keys.length == 1) {
                client = MetricsClient.newInstance(keys[0], "", "", metrics.environment());
            } else if (keys.length == 2) {
                client = MetricsClient.newInstance(keys[0], keys[1], "", metrics.environment());
            } else {
                client = MetricsClient.newInstance(keys[0], keys[1], keys[2], metrics.environment());
            }

            result = joinPoint.proceed();

            Type[] types = metrics.types();
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
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(16);
                String key = parameters[i].getName();
                if (StringUtils.hasText(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
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
}
