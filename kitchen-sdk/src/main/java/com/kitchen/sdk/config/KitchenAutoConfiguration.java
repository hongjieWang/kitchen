package com.kitchen.sdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wanghongjie
 */
@Configuration
@Import(value = com.kitchen.sdk.metrics.aspect.MetricsAspect.class)
public class KitchenAutoConfiguration {
}
