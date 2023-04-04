package com.kitchen.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kitchen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 应用埋点对象 metrics_qps_day
 *
 * @author wanghongjie
 * @date 2023-04-03
 */
@TableName("metrics_qps_day")
public class MetricsQpsDay extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
     * 昨日次数
     */
    @TableField(exist = false)
    private Long v2;

    /**
     * 采集环境
     */
    private String environment;

    /**
     * Key1
     */
    private String keyOne;

    /**
     * Key2
     */
    private String keyTwo;

    /**
     * Key3
     */
    private String keyThird;

    @TableField(exist = false)
    private Long min;
    @TableField(exist = false)
    private Long max;

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

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyValue() {
        return keyValue;
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

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setKeyOne(String keyOne) {
        this.keyOne = keyOne;
    }

    public String getKeyOne() {
        return keyOne;
    }

    public void setKeyTwo(String keyTwo) {
        this.keyTwo = keyTwo;
    }

    public String getKeyTwo() {
        return keyTwo;
    }

    public void setKeyThird(String keyThird) {
        this.keyThird = keyThird;
    }

    public String getKeyThird() {
        return keyThird;
    }

    public Long getV2() {
        return v2;
    }

    public void setV2(Long v2) {
        this.v2 = v2;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("time", getTime())
                .append("appName", getAppName())
                .append("keyValue", getKeyValue())
                .append("hostName", getHostName())
                .append("logType", getLogType())
                .append("v1", getV1())
                .append("environment", getEnvironment())
                .append("keyOne", getKeyOne())
                .append("keyTwo", getKeyTwo())
                .append("keyThird", getKeyThird())
                .toString();
    }
}
