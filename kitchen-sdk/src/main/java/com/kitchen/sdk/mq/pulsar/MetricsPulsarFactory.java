package com.kitchen.sdk.mq.pulsar;

import com.kitchen.sdk.metrics.MetricsRegister;
import com.kitchen.sdk.metrics.log4j.helpers.LogLog;
import com.kitchen.sdk.metrics.loggers.MetricsLoggerFactory;
import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.kitchen.sdk.metrics.loggers.MetricsLoggerFactory.P_HB_ENABLE;
import static com.kitchen.sdk.metrics.loggers.MetricsLoggerFactory.P_JVM_ENABLE;
import static java.lang.Boolean.TRUE;

/**
 * 监控数据Mq-pulsar
 *
 * @author wanghongjie
 */
public class MetricsPulsarFactory {
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

    public static final String METRICS_UMP_ENABLE = "metrics.enable";

    private static String APP_NAME = "";

    public static final String P_APP_NAME = "metrics.appname";

    public static volatile boolean ENABLE;
    /**
     * 是否开启pulsar
     */
    private static Boolean AUTO_ENABLE = Boolean.FALSE;
    /**
     * mq服务url
     */
    private static String SERVICE_URL = "";
    private static String TOKEN = "";

    private static String CLUSTER_ID = "";

    private static String ENVIRONMENT_ID = "";

    private static String TOPIC = "";
    private static final MetricsPulsarFactory instance = new MetricsPulsarFactory();

    public static MetricsPulsarFactory init() {
        return instance;
    }

    public static void destory() {
        if (!ENABLE) {
            return;
        }
        ENABLE = false;
        MetricsRegister.unReisterAll();
    }

    public static void start() {
        if (ENABLE) {
            return;
        }
        ENABLE = true;
        doInit(true);
    }

    static {
        doInit(false);
    }

    private static void doInit(boolean start) {
        Properties properties = new Properties();
        InputStream inputStream = new BufferedInputStream(Objects.requireNonNull(MetricsLoggerFactory.class.getClassLoader().getResourceAsStream(METRICS_UMP_PROPERTIES)));
        try {
            properties.load(inputStream);
            APP_NAME = properties.getProperty(P_APP_NAME, APP_NAME);
            AUTO_ENABLE = Boolean.parseBoolean(properties.getProperty(P_METRICS_PULSAR_AUTO_ENABLE, "false"));
            SERVICE_URL = properties.getProperty(P_METRICS_PULSAR_SERVICE_URL, "");
            TOKEN = properties.getProperty(P_METRICS_PULSAR_TOKEN, "");
            CLUSTER_ID = properties.getProperty(P_METRICS_PULSAR_CLUSTER_ID, "");
            ENVIRONMENT_ID = properties.getProperty(P_METRICS_PULSAR_ENVIRONMENT_ID, "");
            TOPIC = properties.getProperty(P_METRICS_PULSAR_TOPIC, "");
            if (AUTO_ENABLE) {
                if (!start) {
                    ENABLE = Boolean.parseBoolean(properties.getProperty(METRICS_UMP_ENABLE, "true"));
                }
                boolean isJvmEnable = Boolean.parseBoolean(properties.getProperty(P_JVM_ENABLE, TRUE.toString()));
                boolean isHbEnable = Boolean.parseBoolean(properties.getProperty(P_HB_ENABLE, TRUE.toString()));
                if (isJvmEnable && ENABLE) {
                    MetricsRegister.registerJVMInfo(APP_NAME + ".jvm");
                }
                if (isHbEnable && ENABLE) {
                    MetricsRegister.registerHeartBeats(APP_NAME + ".hb");
                }
                if (ENABLE) {
                    MetricsRegister.registerKVInfo(APP_NAME);
                }
            }
        } catch (IOException e) {
            LogLog.error("metrics_ump.properties not exists in classpath.");
            LogLog.error("", e);
        }
    }


    public static Producer<byte[]> getProducer() {
        try {
            PulsarClient build = PulsarClient.builder().serviceUrl(SERVICE_URL).authentication(AuthenticationFactory.token(TOKEN)).build();
            return Objects.requireNonNull(build).newProducer().topic(buildTopicUrl()).create();
        } catch (PulsarClientException e) {
            LogLog.error("pulsarClient build producer is error.");
            LogLog.error("", e);
        }
        return null;
    }

    /**
     * 构建队列的topic
     *
     * @return topic
     */
    public static String buildTopicUrl() {
        return CLUSTER_ID + "/" + ENVIRONMENT_ID + "/" + TOPIC;
    }

    /**
     * 是否开启pulsar
     *
     * @return 开启结果
     */
    public static Boolean getAutoEnable() {
        return AUTO_ENABLE;
    }
}
