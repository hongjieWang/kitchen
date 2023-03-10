package com.kitchen.sdk.metrics;

import com.kitchen.sdk.metrics.common.Conts;
import com.kitchen.sdk.metrics.task.HeartBeats;
import com.kitchen.sdk.metrics.task.JvmEnvironmentInfo;
import com.kitchen.sdk.metrics.task.JvmRuntimeInfo;
import com.kitchen.sdk.metrics.task.KvInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * 监控注册器
 *
 * @author wanghongjie
 */
public class MetricsRegister {
    private static ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);

    private static volatile List<ScheduledFuture> futures = new LinkedList<>();

    public static volatile Boolean SYSTEM_HEART_INIT = Boolean.FALSE;

    public static volatile Boolean JVM_MONITOR_INIT = Boolean.FALSE;

    public static volatile Boolean KV_MONITOR_INIT = Boolean.FALSE;

    public static volatile Boolean DRUID_MONITOR_INIT = Boolean.FALSE;

    public static synchronized void unReisterAll() {
        for (ScheduledFuture future : futures) {
            future.cancel(true);
        }
        futures.clear();
        SYSTEM_HEART_INIT = Boolean.FALSE;
        JVM_MONITOR_INIT = Boolean.FALSE;
        KV_MONITOR_INIT = Boolean.FALSE;
        DRUID_MONITOR_INIT = Boolean.FALSE;
    }

    public static synchronized void registerHeartBeats(String key) {
        if (key == null || "".equals(key.trim())) {
            return;
        }
        try {
            if (!SYSTEM_HEART_INIT) {
                futures.add(schedule.scheduleAtFixedRate(new HeartBeats(key), 1000L, Conts.TIME_HEARTBEATS, TimeUnit.MILLISECONDS));
                SYSTEM_HEART_INIT = Boolean.TRUE;
            }
        } catch (Throwable t) {
        }
    }

    public static synchronized void registerJVMInfo(String key) {
        if (key == null || "".equals(key.trim())) {
            return;
        }
        try {
            if (!JVM_MONITOR_INIT) {
                futures.add(schedule.scheduleAtFixedRate(new JvmRuntimeInfo(key), 1000L, Conts.JVMTIME_R, TimeUnit.MILLISECONDS));
                futures.add(schedule.scheduleAtFixedRate(new JvmEnvironmentInfo(key), 0, Conts.JVMTIME_E, TimeUnit.MILLISECONDS));
                JVM_MONITOR_INIT = Boolean.TRUE;
            }
        } catch (Throwable t) {
        }
    }


    /**
     * 注册KV模式
     *
     * @param appName 应用名称
     */
    public static synchronized void registerKVInfo(String appName) {
        try {
            if (!KV_MONITOR_INIT) {
                futures.add(schedule.scheduleAtFixedRate(new KvInfo(appName), 1000L, Conts.KV_PERIOD, TimeUnit.MILLISECONDS));
                KV_MONITOR_INIT = Boolean.TRUE;
            }
        } catch (Throwable t) {
        }
    }

    public static synchronized void registerDruidMonitor(String dbKey, String sqlKey) {
        try {
            Class.forName("com.alibaba.druid.VERSION");
            if (!DRUID_MONITOR_INIT) {
//                futures.add(schedule.scheduleAtFixedRate((Runnable) new DruidInfo(dbKey, sqlKey), 1000L, Conts.DB_PERIOD, TimeUnit.MILLISECONDS));
                DRUID_MONITOR_INIT = Boolean.TRUE;
            }
        } catch (Throwable t) {
        }
    }
}
