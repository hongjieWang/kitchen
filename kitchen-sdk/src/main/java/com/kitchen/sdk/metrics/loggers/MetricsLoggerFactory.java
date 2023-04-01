package com.kitchen.sdk.metrics.loggers;


import com.kitchen.sdk.metrics.MetricsRegister;
import com.kitchen.sdk.metrics.log4j.*;
import com.kitchen.sdk.metrics.log4j.helpers.LogLog;
import com.kitchen.sdk.metrics.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.TRUE;


/**
 * 日志工厂类
 *
 * @author wanghongjie
 */
public class MetricsLoggerFactory {
    private static final String PARTTERN = "'.'yyyy-MM-dd";

    public static final String MONITOR_FILE_PATH_DEFAULT = "/var/log/footstone/metrics-logs";

    public static final String MONITOR_FILE_NAME_DEFAULT = "metrics";

    public static final String METRICS_UMP_PROPERTIES = "metrics_ump.properties";

    public static final String METRICS_UMP_ENABLE = "metrics.enable";

    public static final String P_FILE_PATH = "metrics.file.path";

    public static final String P_MAXBACKUPINDEX = "metrics.file.maxbackupindex";

    public static final String P_APP_NAME = "metrics.appname";

    public static final String P_JVM_ENABLE = "metrics.jvm.autoenable";

    public static final String P_HB_ENABLE = "metrics.hb.autoenable";

    public static final String P_DB_ENABLE = "metrics.db.autoenable";

    public static final String P_DB_SLOW_SQL = "metrics.db.sql.threshold";

    private static String MONITOR_FILE_PATH = "";

    private static String APP_NAME = "";

    private static int MAX_BACKUP_INDEX = 7;

    public static int SLOW_SQL_THRESHOLD;

    public static volatile boolean ENABLE;

    private static final MetricsLoggerFactory instance = new MetricsLoggerFactory();

    public static MetricsLoggerFactory init() {
        return instance;
    }

    public static void destory() {
        if (!ENABLE)
            return;
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
        InputStream inputStream = new BufferedInputStream(MetricsLoggerFactory.class.getClassLoader().getResourceAsStream(METRICS_UMP_PROPERTIES));
        try {
            properties.load(inputStream);
            APP_NAME = properties.getProperty(P_APP_NAME, APP_NAME);
            MONITOR_FILE_PATH = properties.getProperty(P_FILE_PATH, MONITOR_FILE_PATH_DEFAULT);
            MAX_BACKUP_INDEX = Integer.parseInt(properties.getProperty(P_MAXBACKUPINDEX, String.valueOf(MAX_BACKUP_INDEX)));
            SLOW_SQL_THRESHOLD = Integer.parseInt(properties.getProperty(P_DB_SLOW_SQL, "3000"));
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
        } catch (IOException e) {
            LogLog.error("metrics_ump.properties not exists in classpath.");
            LogLog.error("", e);
        }
    }

    private static class MonitorLogAppender extends DailyMaxRollingFileAppender {
        public MonitorLogAppender(Layout layout, String filename, String datePattern) throws IOException {
            super(layout, filename, datePattern);
        }
    }

    public static Logger getLogger(String loggerName) {
        Logger logger = Logger.getLogger(loggerName);
        logger.removeAllAppenders();
        try {
            PatternLayout patternLayout = new PatternLayout();
            String fileName = createFileName(loggerName);
            MonitorLogAppender appender = new MonitorLogAppender(patternLayout, fileName, PARTTERN);
            appender.setMaxBackupIndex(MAX_BACKUP_INDEX);
            appender.setBufferedIO(false);
            logger.addAppender(appender);
            logger.setLevel(Level.INFO);
            logger.setAdditivity(true);
        } catch (IOException e) {
            LogLog.error("init IOException exception.", e);
        }
        return logger;
    }

    private static String createFileName(String name) {
        String appNamePostFix = StringUtil.isNotBlank(APP_NAME) ? ("." + APP_NAME) : "";
        return MONITOR_FILE_PATH + "/" + MONITOR_FILE_NAME_DEFAULT + "-" + name + appNamePostFix + ".log";
    }
}