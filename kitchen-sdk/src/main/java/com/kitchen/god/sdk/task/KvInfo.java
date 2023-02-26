package com.kitchen.god.sdk.task;

import com.kitchen.god.sdk.common.Type;
import com.kitchen.god.sdk.kv.KeysObject;
import com.kitchen.god.sdk.kv.KvHelpers;
import com.kitchen.god.sdk.kv.Val;
import com.kitchen.god.sdk.kv.ValuesObject;
import com.kitchen.god.sdk.log4j.Layout;
import com.kitchen.god.sdk.loggers.MetricsLogger;
import com.kitchen.god.sdk.util.LogFormatter;
import com.kitchen.god.sdk.util.Utils;

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
    private static final String KV_LOG_TEMPLATE = ("{`time`:`{}`,`key`:`{}`,`hostname`:`" + Utils.HOST_NAME + "`,`logtype`:`{}`,`v1`:{},`v2`:{},`min`:{},`max`:{}}").replace('`', '"');

    @Override
    public void run() {
        try {
            writeLog();
        } catch (RuntimeException ignore) {
            ignore.printStackTrace();
        }
    }

    private static void writeLog() {
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
            String log = LogFormatter.format(KV_LOG_TEMPLATE, nowTime, ko, ko.getType(), v.v1(), v.v2(), v.min(), v.max());
            if (logs.length() > 0) {
                logs.append(Layout.LINE_SEP);
            }
            logs.append(log);
            tmpMap.put(ko, new ValuesObject(ko.getType(), v.v1(), v.v2()));
        }
        if (logs.length() > 0) {
            MetricsLogger.KV_LOGGER.info(logs.toString());
        }
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
