package com.kitchen.demo;

import com.kitchen.sdk.MetricsClient;

/**
 * @author wanghongjie
 */
public class KitchenController {

//    @GetMapping("/test1")
    public String test1() {
        MetricsClient metricsClient = MetricsClient.newInstance("demo", "test", "test1");
        metricsClient.sr_incrSuccess().sr_incrTotal().qps().rt();
        return "test1";
    }
}
