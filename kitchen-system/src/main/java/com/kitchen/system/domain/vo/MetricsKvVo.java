package com.kitchen.system.domain.vo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.kitchen.common.utils.bean.BeanUtils;
import com.kitchen.system.domain.MetricsKv;

import java.io.Serializable;
import java.util.Date;

/**
 * 转换对象
 *
 * @author wanghongjie
 */
public class MetricsKvVo implements Serializable {
    @JSONField(name = "appName")
    private String appName;
    @JSONField(name = "environment")
    private String environment;
    @JSONField(name = "hostname")
    private String hostName;
    @JSONField(name = "key")
    private String keyValue;
    @JSONField(name = "logtype")
    private String logType;
    @JSONField(name = "max")
    private Long maxValue;
    @JSONField(name = "min")
    private Long minValue;
    @JSONField(name = "time")
    private Date time;
    @JSONField(name = "v1")
    private Long v1;
    @JSONField(name = "v2")
    private Long v2;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getV1() {
        return v1;
    }

    public void setV1(Long v1) {
        this.v1 = v1;
    }

    public Long getV2() {
        return v2;
    }

    public void setV2(Long v2) {
        this.v2 = v2;
    }

    public static void main(String[] args) {
        String json = "{\"time\":\"20230403111730090\",\"appName\":\"kitchen-demo\",\"environment\":\"default\",\"key\":\"XX服务#key翻译#列表查询\",\"hostname\":\"wanghonjiedeMBP.lan\",\"logtype\":\"RT\",\"v1\":39,\"v2\":4,\"min\":4,\"max\":189}";
        MetricsKvVo kv = JSON.parseObject(json, MetricsKvVo.class);
        System.out.println(JSON.toJSONString(kv));
        MetricsKv metricsKv = new MetricsKv();
        BeanUtils.copyProperties(kv, metricsKv);
        System.out.println(JSON.toJSONString(metricsKv));
    }
}
