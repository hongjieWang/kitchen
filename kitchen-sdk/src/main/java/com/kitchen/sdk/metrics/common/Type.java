package com.kitchen.sdk.metrics.common;

public enum Type {
    /**
     * 响应时间
     */
    RT(false, true),
    /**
     * 每秒查询
     */
    QPS(false, false),
    /**
     *成功率
     */
    SUCCESS_RATE(true, false),
    /**
     * 失败率
     */
    FAIL_RATE(true, false),
    /**
     * 命中率
     */
    HIT_RATE(true, false),
    CUR(false, false),
    DEFAULT(false, false);

    private boolean isPercent;

    private boolean useCurrentValue4MinMax;

    Type(boolean isPercent, boolean useCurrentValue4MinMax) {
        this.isPercent = isPercent;
        this.useCurrentValue4MinMax = useCurrentValue4MinMax;
    }

    public boolean isPercent() {
        return this.isPercent;
    }

    public boolean isUseCurrentValue4MinMax() {
        return this.useCurrentValue4MinMax;
    }
}