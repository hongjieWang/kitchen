package com.kitchen.sdk.mq.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;

import java.nio.charset.StandardCharsets;

/**
 * 采集日志通过Pulsar发送
 *
 * @author wanghongjie
 */
public class MetricsPulsar {
    public static final MetricsPulsar KV_PULSAR = new MetricsPulsar(MetricsPulsarFactory.getProducer());

    private Producer<byte[]> producer;

    public MetricsPulsar(Producer<byte[]> producer) {
        this.producer = producer;
    }

    public void send(String message) throws PulsarClientException {
        this.producer.send(message.getBytes(StandardCharsets.UTF_8));
    }

    public Producer<byte[]> getProducer() {
        return this.producer;
    }
}
