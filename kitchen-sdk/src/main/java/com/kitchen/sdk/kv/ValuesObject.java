package com.kitchen.sdk.kv;

import com.kitchen.sdk.common.Type;

import java.util.concurrent.atomic.AtomicReference;

public class ValuesObject {
    private final AtomicReference<Val> values = new AtomicReference<Val>();

    private final Type type;

    public ValuesObject(Type type) {
        this.values.set(new Val());
        this.type = type;
    }

    public ValuesObject(Type type, long v1, long v2) {
        this(type);
        addCount(v1, v2);
    }

    public void update(long v1, long v2) {
        Val cur;
        Val upd;
        do {
            cur = this.values.get();
            upd = new Val(v1, v2, cur.min(), cur.max());
        } while (!this.values.compareAndSet(cur, upd));
    }

    public void addCount(long v1, long v2) {
        Val cur;
        Val upd;
        do {
            cur = this.values.get();
            upd = updateMinMax(v1, v2, cur);
        } while (!this.values.compareAndSet(cur, upd));
    }

    private Val updateMinMax(long v1, long v2, Val cur) {
        long uv1 = cur.v1() + v1;
        long uv2 = cur.v2() + v2;
        long curMin = cur.min();
        long curMax = cur.max();
        if (v2 <= 0L)
            return new Val(uv1, uv2, curMin, curMax);
        if (this.type.isPercent() && uv1 > uv2)
            return new Val(uv1, uv2, curMin, curMax);
        boolean useCurVal4MinMax = this.type.isUseCurrentValue4MinMax();
        int percent = this.type.isPercent() ? 100 : 1;
        long ratio = useCurVal4MinMax ? (percent * v1 / v2) : (percent * uv1 / uv2);
        long min = (curMin < 0L || ratio < curMin) ? ratio : curMin;
        long max = (curMax < 0L || curMax < ratio) ? ratio : curMax;
        return new Val(uv1, uv2, min, max);
    }

    public void deductCount(long v1, long v2) {
        Val cur;
        Val upd;
        do {
            cur = this.values.get();
            upd = new Val(cur.v1() - v1, cur.v2() - v2, cur.min(), cur.max());
        } while (!this.values.compareAndSet(cur, upd));
    }

    public Val getValues() {
        return this.values.get();
    }
}
