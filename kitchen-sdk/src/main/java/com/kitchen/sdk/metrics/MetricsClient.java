package com.kitchen.sdk.metrics;

public class MetricsClient {
    private SuccessRate sr;

    private RT rt;

    private QPS qps;

    private Hit hit;

    private String ev;

    private MetricsClient(SuccessRate sr, RT rt, QPS qps, Hit hit) {
        this.sr = sr;
        this.rt = rt;
        this.qps = qps;
        this.hit = hit;
    }

    private MetricsClient(String k1, String k2, String k3) {
        this.qps = Metrics.QPS(k1, k2, k3);
        this.rt = Metrics.RT(k1, k2, k3);
        this.sr = Metrics.SR(k1, k2, k3);
        this.hit = Metrics.HIT(k1, k2, k3);
    }

    private MetricsClient(String k1, String k2, String k3, String environment) {
        this.qps = Metrics.QPS(k1, k2, k3, environment);
        this.rt = Metrics.RT(k1, k2, k3, environment);
        this.sr = Metrics.SR(k1, k2, k3, environment);
        this.hit = Metrics.HIT(k1, k2, k3, environment);
    }

    public static MetricsClient newInstance(String k1, String k2, String k3) {
        return new MetricsClient(k1, k2, k3);
    }

    public static MetricsClient newInstance(String k1, String k2, String k3, String environment) {
        return new MetricsClient(k1, k2, k3, environment);
    }

    public static MetricsClient newInstance(String k1, String k2) {
        return newInstance(k1, k2, "");
    }

    public static MetricsClient newInstance(String k1) {
        return newInstance(k1, "", "");
    }

    public MetricsClient qps() {
        this.qps.record();
        return this;
    }

    public MetricsClient sr_incrTotal() {
        this.sr.incrTotal();
        return this;
    }

    public MetricsClient sr_incrSuccess() {
        this.sr.incrSuccess();
        return this;
    }

    public MetricsClient rt(Long starTime) {
        this.rt.record(starTime);
        return this;
    }

    public MetricsClient rt() {
        this.rt.record();
        return this;
    }

    public MetricsClient hit_incrTotal() {
        this.hit.incrTotal();
        return this;
    }

    public MetricsClient hit_incrSuccess() {
        this.hit.incrHit();
        return this;
    }

    public RT getRt() {
        return this.rt;
    }

    public QPS getQps() {
        return this.qps;
    }

    public SuccessRate getSr() {
        return this.sr;
    }

    public Hit getHit() {
        return this.hit;
    }

    public static class Builder {
        private String k1;

        private String k2;

        private String k3;

        private SuccessRate sr;

        private RT rt;

        private QPS qps;

        private Hit hit;

        public Builder(String k1) {
            this.k1 = k1;
        }

        public Builder(String k1, String k2) {
            this.k1 = k1;
            this.k2 = k2;
        }

        public Builder(String k1, String k2, String k3) {
            this.k1 = k1;
            this.k2 = k2;
            this.k3 = k3;
        }

        public Builder qps() {
            this.qps = Metrics.QPS(this.k1, this.k2, this.k3);
            return this;
        }

        public Builder rt() {
            this.rt = Metrics.RT(this.k1, this.k2, this.k3);
            return this;
        }

        public Builder hit() {
            this.hit = Metrics.HIT(this.k1, this.k2, this.k3);
            return this;
        }

        public Builder sr() {
            this.sr = Metrics.SR(this.k1, this.k2, this.k3);
            return this;
        }

        public MetricsClient build() {
            return new MetricsClient(this.sr, this.rt, this.qps, this.hit);
        }

        public MetricsClient buildAll() {
            this.qps = Metrics.QPS(this.k1, this.k2, this.k3);
            this.rt = Metrics.RT(this.k1, this.k2, this.k3);
            this.sr = Metrics.SR(this.k1, this.k2, this.k3);
            this.hit = Metrics.HIT(this.k1, this.k2, this.k3);
            return build();
        }
    }
}