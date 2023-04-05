package com.kitchen.system.domain.vo;

import com.kitchen.common.annotation.Log;

import java.io.Serializable;

/**
 * 计算平均响应时间
 *
 * @author wanghongjie
 */
public class RtVo implements Serializable {
    public RtVo(Long sumTimes, Long number, Long min, Long max) {
        this.sumTimes = sumTimes;
        this.number = number;
        this.min = min;
        this.max = max;
    }

    public RtVo() {
    }

    /**
     * 总时间
     */
    private Long sumTimes;
    /**
     * 次数
     */
    private Long number;
    /**
     * 最小值
     */
    private Long min;
    /**
     * 最大值
     */
    private Long max;

    public Long getSumTimes() {
        return sumTimes;
    }

    public void setSumTimes(Long sumTimes) {
        this.sumTimes = sumTimes;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
}
