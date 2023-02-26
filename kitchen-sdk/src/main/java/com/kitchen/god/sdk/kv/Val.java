package com.kitchen.god.sdk.kv;

import java.util.Arrays;
import java.util.List;

public class Val {
    private final long v1;

    private final long v2;

    private final long min;

    private final long max;

    public Val() {
        this(0L, 0L, -1L, -1L);
    }

    public Val(long v1, long v2, long min, long max) {
        this.v1 = v1;
        this.v2 = v2;
        this.min = min;
        this.max = max;
    }

    public long v1() {
        return this.v1;
    }

    public long v2() {
        return this.v2;
    }

    public long min() {
        return this.min;
    }

    public long max() {
        return this.max;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return asList().equals(((Val) o).asList());
    }

    public int hashCode() {
        return asList().hashCode();
    }

    private List<Long> asList() {
        return Arrays.asList(new Long[]{Long.valueOf(this.v1), Long.valueOf(this.v2), Long.valueOf(this.min), Long.valueOf(this.max)});
    }
}
