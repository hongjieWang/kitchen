package com.kitchen.sdk.task;

import com.kitchen.sdk.common.Type;
import com.kitchen.sdk.kv.KeysObject;
import com.kitchen.sdk.kv.KvHelpers;
import com.kitchen.sdk.kv.Val;
import com.kitchen.sdk.kv.ValuesObject;
import com.kitchen.sdk.log4j.Layout;
import com.kitchen.sdk.loggers.MetricsLogger;
import com.kitchen.sdk.mq.pulsar.MetricsPulsar;
import com.kitchen.sdk.mq.pulsar.MetricsPulsarFactory;
import com.kitchen.sdk.util.LogFormatter;
import com.kitchen.sdk.util.StringUtil;
import com.kitchen.sdk.util.Utils;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/**
 * 记录QPS相关任务
 *
 * @author wanghongjie
 */
public class KvInfo extends TimerTask {
    private static final String KV_LOG_TEMPLATE = ("{`time`:`{}`,`appName`:`{}`,`environment`:`{}`,`key`:`{}`,`hostname`:`" + Utils.HOST_NAME + "`,`logtype`:`{}`,`v1`:{},`v2`:{},`min`:{},`max`:{}}").replace('`', '"');
    private static String appName;
    /**
     * 运行环境
     */

    public KvInfo(String appName) {
        KvInfo.appName = appName;
    }

    @Override
    public void run() {
        try {
            build();
        } catch (RuntimeException ignore) {
        }
    }

    /**
     * 写日志
     *
     * @param message 数据信息
     */
    private static void writeLog(String message) {
        if (StringUtil.isNotBlank(message)) {
            MetricsLogger.KV_LOGGER.info(message);
        }
    }

    /**
     * 发送pulsar
     *
     * @param message 数据信息
     */
    private static void sendPulsar(String message) {
        if (StringUtil.isNotBlank(message)) {
            try {
                if (MetricsPulsarFactory.getAutoEnable()) {
                    MetricsPulsar.KV_PULSAR.send(message);
                }
            } catch (PulsarClientException ignored) {
            }
        }
    }

    private static void build() {
        String nowTime = Utils.getNowTime();
        StringBuilder logs = new StringBuilder(1024);
        Map<KeysObject, ValuesObject> cached = KvHelpers.cached;
        Set<KeysObject> keysObjects = cached.keySet();
        Map<KeysObject, ValuesObject> tmpMap = new HashMap<>(keysObjects.size());
        for (KeysObject ko : keysObjects) {
            Val v = cached.get(ko).getValues();
            if (v.v1() == 0L && v.v2() == 0L) {
                continue;
            }
            if (ko.getType().isPercent() && v.v1() > v.v2()) {
                v = new Val(v.v2(), v.v2(), v.min(), v.max());
            }
            String log = LogFormatter.format(KV_LOG_TEMPLATE, nowTime, KvInfo.appName, ko.getEnvironment(), ko, ko.getType(), v.v1(), v.v2(), v.min(), v.max());
            if (logs.length() > 0) {
                logs.append(Layout.LINE_SEP);
            }
            logs.append(log);
            tmpMap.put(ko, new ValuesObject(ko.getType(), v.v1(), v.v2()));
        }
        writeLog(logs.toString());
        sendPulsar(logs.toString());
        for (KeysObject ko : tmpMap.keySet()) {
            ValuesObject vo = tmpMap.get(ko);
            if (vo == null) {
                continue;
            }
            if (ko.getType() == Type.CUR) {
                cached.remove(ko);
                continue;
            }
            Val v = vo.getValues();
            ValuesObject cachedVal = cached.get(ko);
            if (cachedVal != null) {
                cachedVal.deductCount(v.v1(), v.v2());
            }
        }
    }
}
