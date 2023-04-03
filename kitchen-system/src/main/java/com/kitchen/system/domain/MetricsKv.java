package com.kitchen.system.domain;

import com.kitchen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 数据监控键值对象 metrics_kv
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
public class MetricsKv extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 时间戳
     */
    private Date time;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 埋点key
     */
    private String keyValue;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 数据类型
     */
    private String logType;

    /**
     * 次数
     */
    private Long v1;

    /**
     * 数值2
     */
    private Long v2;

    /**
     * 最小值
     */
    private Long minValue;

    /**
     * 最大值
     */
    private Long maxValue;

    /**
     * 采集环境
     */
    private String environment;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public void setV1(Long v1) {
        this.v1 = v1;
    }

    public Long getV1() {
        return v1;
    }

    public void setV2(Long v2) {
        this.v2 = v2;
    }

    public Long getV2() {
        return v2;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("time", getTime())
                .append("appName", getAppName())
                .append("keys", getKeyValue())
                .append("hostName", getHostName())
                .append("logType", getLogType())
                .append("v1", getV1())
                .append("v2", getV2())
                .append("minValue", getMinValue())
                .append("maxValue", getMaxValue())
                .append("environment", getEnvironment())
                .toString();
    }
}
