package com.kitchen.system.consumer;

import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Pulsar 消费者
 *
 * @author wanghongjie
 */
public class PulsarFactory {
    private static final Logger logger = LoggerFactory.getLogger(PulsarFactory.class);
    public static final String METRICS_UMP_PROPERTIES = "metrics_ump.properties";
    /**
     * 是否开启pulsar消息队列
     */
    public static final String P_METRICS_PULSAR_AUTO_ENABLE = "metrics.pulsar.autoEnable";
    /**
     * 消息队列服务地址
     */
    public static final String P_METRICS_PULSAR_SERVICE_URL = "metrics.pulsar.serviceUrl";
    /**
     * 消息队列token
     */
    public static final String P_METRICS_PULSAR_TOKEN = "metrics.pulsar.token";
    /**
     * 消息队列集群ID
     */
    public static final String P_METRICS_PULSAR_CLUSTER_ID = "metrics.pulsar.clusterId";
    /**
     * 命名空间ID
     */
    public static final String P_METRICS_PULSAR_ENVIRONMENT_ID = "metrics.pulsar.environmentId";
    /**
     * 数据发送队列
     */
    public static final String P_METRICS_PULSAR_TOPIC = "metrics.pulsar.topic";

    /**
     * mq服务url
     */
    private static String SERVICE_URL = "";
    private static String TOKEN = "";

    private static String CLUSTER_ID = "";

    private static String ENVIRONMENT_ID = "";

    private static String TOPIC = "";

    /**
     * 是否开启pulsar
     */
    private static Boolean AUTO_ENABLE = Boolean.FALSE;


    static {
        doInit();
    }

    private static void doInit() {
        Properties properties = new Properties();
        InputStream inputStream = new BufferedInputStream(Objects.requireNonNull(PulsarFactory.class.getClassLoader().getResourceAsStream(METRICS_UMP_PROPERTIES)));
        try {
            properties.load(inputStream);
            AUTO_ENABLE = Boolean.parseBoolean(properties.getProperty(P_METRICS_PULSAR_AUTO_ENABLE, "false"));
            SERVICE_URL = properties.getProperty(P_METRICS_PULSAR_SERVICE_URL, "");
            TOKEN = properties.getProperty(P_METRICS_PULSAR_TOKEN, "");
            CLUSTER_ID = properties.getProperty(P_METRICS_PULSAR_CLUSTER_ID, "");
            ENVIRONMENT_ID = properties.getProperty(P_METRICS_PULSAR_ENVIRONMENT_ID, "");
            TOPIC = properties.getProperty(P_METRICS_PULSAR_TOPIC, "");
        } catch (IOException e) {
            logger.error("metrics_ump.properties not exists in classpath.");
        }
    }

    /**
     * 获取Client
     *
     * @return client
     */
    public static PulsarClient getPulsarClient() {
        try {
            return PulsarClient.builder().serviceUrl(SERVICE_URL).authentication(AuthenticationFactory.token(TOKEN)).build();
        } catch (PulsarClientException ignored) {
            return null;
        }
    }


    /**
     * 构建队列的topic
     *
     * @return topic
     */
    public static String buildTopicUrl() {
        return CLUSTER_ID + "/" + ENVIRONMENT_ID + "/" + TOPIC;
    }

    public static String subscriptionName() {
        return TOPIC.concat("_subscription");
    }

    /**
     * 是否开启Pulsar
     *
     * @return 是否开启
     */
    public static Boolean autoEnable() {
        return AUTO_ENABLE;
    }
}
