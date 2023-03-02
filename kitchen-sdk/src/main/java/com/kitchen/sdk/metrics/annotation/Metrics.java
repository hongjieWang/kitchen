package com.kitchen.sdk.metrics.annotation;

import com.kitchen.sdk.metrics.common.Type;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Metrics {
    String[] value() default {};

    Type[] types() default {Type.QPS, Type.RT};

    Class<?>[] exclude() default {};
}
