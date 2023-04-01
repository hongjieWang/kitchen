package com.kitchen.sdk.metrics.annotation;

import com.kitchen.sdk.metrics.common.Type;

import java.lang.annotation.*;

/**
 * @author wanghongjie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Metrics {
    /**
     * keys
     *
     * @return
     */
    String[] value() default {};

    Type[] types() default {Type.QPS, Type.RT};

    Class<?>[] exclude() default {};

    /**
     * environment
     *
     * @return
     */
    String environment() default "default";
}
