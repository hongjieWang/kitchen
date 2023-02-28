package com.kitchen.demo;

import com.kitchen.sdk.MetricsClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghongjie
 */
@RestController
@RequestMapping("/demo")
public class KitchenController {

    @GetMapping("/test1")
    public String test1() {
        MetricsClient metricsClient = MetricsClient.newInstance("demo", "test", "test1");
        metricsClient.sr_incrSuccess().sr_incrTotal().qps().rt();
        return "test1";
    }
}
