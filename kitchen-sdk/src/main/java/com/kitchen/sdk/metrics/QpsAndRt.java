package com.kitchen.sdk.metrics;

import java.io.Closeable;
import java.io.IOException;

public class QpsAndRt {
    private String k1;

    private String k2;

    private String k3;

    public QpsAndRt(String k1) {
        this.k1 = k1;
    }

    public QpsAndRt(String k1, String k2) {
        this.k1 = k1;
        this.k2 = k2;
    }

    public QpsAndRt(String k1, String k2, String k3) {
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
    }

    public Context start() {
        return new Context(this);
    }

    public static class Context implements Closeable {
        private RT rt;

        private QPS qps;

        private Context(QpsAndRt qpsAndRt) {
            this.rt = Metrics.RT(qpsAndRt.k1, qpsAndRt.k2, qpsAndRt.k3);
            this.qps = Metrics.QPS(qpsAndRt.k1, qpsAndRt.k2, qpsAndRt.k3);
        }

        public void stop() {
            this.qps.record();
            this.rt.record();
        }

        public void close() throws IOException {
            stop();
        }
    }
}
