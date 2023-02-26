package com.kitchen.god.sdk.annotation;

import com.kitchen.god.sdk.common.Type;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Metrics {
    String[] value() default {};

    Type[] types() default {Type.QPS, Type.RT};

    Class<?>[] exclude() default {};
}
