package com.kitchen.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kitchen.common.annotation.Excel;
import com.kitchen.common.core.domain.BaseEntity;

/**
 * 数据监控键值对象 metrics_kv
 *
 * @author ruoyi
 * @date 2023-03-01
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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;

    /**
     * 应用名称
     */
    @Excel(name = "应用名称")
    private String appName;
    /**
     * 采集环境
     */
    private String environment;
    /**
     * 埋点key
     */
    @Excel(name = "埋点key")
    private String key;

    /**
     * 主机名称
     */
    @Excel(name = "主机名称")
    private String hostName;

    /**
     * 数据类型
     */
    @Excel(name = "数据类型")
    private String logType;

    /**
     * 次数
     */
    @Excel(name = "次数")
    private String v1;

    /**
     * 数值2
     */
    @Excel(name = "数值2")
    private String v2;

    /**
     * 最小值
     */
    @Excel(name = "最小值")
    private String min;

    /**
     * 最大值
     */
    @Excel(name = "最大值")
    private String max;

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

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
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

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV1() {
        return v1;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV2() {
        return v2;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMin() {
        return min;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMax() {
        return max;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("time", getTime())
                .append("appName", getAppName())
                .append("key", getKey())
                .append("hostName", getHostName())
                .append("logType", getLogType())
                .append("v1", getV1())
                .append("v2", getV2())
                .append("min", getMin())
                .append("max", getMax())
                .toString();
    }
}
