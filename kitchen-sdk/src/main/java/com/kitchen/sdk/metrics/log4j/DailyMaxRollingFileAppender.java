package com.kitchen.sdk.metrics.log4j;

import com.kitchen.sdk.metrics.log4j.helpers.LogLog;
import com.kitchen.sdk.metrics.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DailyMaxRollingFileAppender extends FileAppender {
    static final int TOP_OF_TROUBLE = -1;

    static final int TOP_OF_MINUTE = 0;

    static final int TOP_OF_HOUR = 1;

    static final int HALF_DAY = 2;

    static final int TOP_OF_DAY = 3;

    static final int TOP_OF_WEEK = 4;

    static final int TOP_OF_MONTH = 5;

    private String datePattern = "'.'yyyy-MM-dd";

    private int maxBackupIndex = 1;

    private String scheduledFilename;

    private long nextCheck = System.currentTimeMillis() - 1L;

    Date now = new Date();

    RollingPastCalendar rpc = new RollingPastCalendar();

    int checkPeriod = -1;

    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

    public DailyMaxRollingFileAppender() {
    }

    public DailyMaxRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, true);
        this.datePattern = datePattern;
        activateOptions();
    }

    public void setDatePattern(String pattern) {
        this.datePattern = pattern;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public void setMaxBackupIndex(int maxBackups) {
        this.maxBackupIndex = maxBackups;
    }

    public int getMaxBackupIndex() {
        return this.maxBackupIndex;
    }

    @Override
    public void activateOptions() {
        super.activateOptions();
        try {
            LogLog.debug("Max backup file kept: " + this.maxBackupIndex + ".");
            if (this.datePattern != null && this.fileName != null) {
                this.now.setTime(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern);
                int type = computeCheckPeriod();
                printPeriodicity(type);
                this.rpc.setType(type);
                File file = new File(this.fileName);
                this.scheduledFilename = this.fileName + sdf.format(new Date(file.lastModified()));
            } else {
                LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    void printPeriodicity(int type) {
        switch (type) {
            case 0:
                LogLog.debug("Appender [[+name+]] to be rolled every minute.");
                return;
            case 1:
                LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
                return;
            case 2:
                LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
                return;
            case 3:
                LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
                return;
            case 4:
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
                return;
            case 5:
                LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
                return;
        }
        LogLog.warn("Unknown periodicity for appender [[+name+]].");
    }

    int computeCheckPeriod() {
        RollingPastCalendar rollingPastCalendar = new RollingPastCalendar(gmtTimeZone, Locale.ENGLISH);
        Date epoch = new Date(0L);
        if (this.datePattern != null)
            for (int i = 0; i <= 5; i++) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
                simpleDateFormat.setTimeZone(gmtTimeZone);
                String r0 = simpleDateFormat.format(epoch);
                rollingPastCalendar.setType(i);
                Date next = new Date(rollingPastCalendar.getNextCheckMillis(epoch));
                String r1 = simpleDateFormat.format(next);
                if (r0 != null && r1 != null && !r0.equals(r1))
                    return i;
            }
        return -1;
    }

    void rollOver() throws IOException {
        if (this.datePattern == null) {
            this.errorHandler.error("Missing DatePattern option in rollOver().");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern);
        String datedFilename = this.fileName + sdf.format(this.now);
        if (this.scheduledFilename.equals(datedFilename))
            return;
        closeFile();
        File target = new File(this.scheduledFilename);
        if (target.exists())
            target.delete();
        File file = new File(this.fileName);
        boolean result = file.renameTo(target);
        if (result) {
            LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
            if (this.maxBackupIndex > 0) {
                file = new File(this.fileName + dateBefore());
                if (file.exists())
                    file.delete();
            }
        } else {
            LogLog.error("Failed to rename [[+fileName+]] to [[+scheduledFilename+]].");
        }
        try {
            setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            this.errorHandler.error("setFile(" + this.fileName + ", false) call failed.");
        }
        this.scheduledFilename = datedFilename;
    }

    private String dateBefore() {
        String dataAnte = "";
        if (this.datePattern != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
            dataAnte = simpleDateFormat.format(new Date(this.rpc.getPastCheckMillis(new Date(), this.maxBackupIndex)));
        }
        return dataAnte;
    }

    @Override
    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= this.nextCheck) {
            this.now.setTime(n);
            this.nextCheck = this.rpc.getNextCheckMillis(this.now);
            try {
                rollOver();
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        }
        super.subAppend(event);
    }

//    public static void main(String[] args) {
//        DailyMaxRollingFileAppender dmrfa = new DailyMaxRollingFileAppender();
//        dmrfa.setDatePattern("'.'yyyy-MM-dd-HH-mm");
//        dmrfa.setFile("prova");
//        System.out.println("dmrfa.getMaxBackupIndex():" + dmrfa.getMaxBackupIndex());
//        dmrfa.activateOptions();
//        for (int i = 0; i < 5; i++) {
//            dmrfa.subAppend((LoggingEvent) null);
//            try {
//                Thread.sleep(60000L);
//            } catch (InterruptedException ex) {
//            }
//            System.out.println("Fine attesa");
//        }
//    }
}