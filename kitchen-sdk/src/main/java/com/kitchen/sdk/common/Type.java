package com.kitchen.sdk.common;

public enum Type {
    RT(false, true),
    QPS(false, false),
    SUCCESS_RATE(true, false),
    FAIL_RATE(true, false),
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