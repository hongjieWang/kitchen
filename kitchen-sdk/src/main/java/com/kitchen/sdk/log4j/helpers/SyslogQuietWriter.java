package com.kitchen.sdk.log4j.helpers;

import com.kitchen.sdk.log4j.spi.ErrorHandler;

import java.io.Writer;

public class SyslogQuietWriter extends QuietWriter {
    int syslogFacility;

    int level;

    public SyslogQuietWriter(Writer writer, int syslogFacility, ErrorHandler eh) {
        super(writer, eh);
        this.syslogFacility = syslogFacility;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSyslogFacility(int syslogFacility) {
        this.syslogFacility = syslogFacility;
    }

    @Override
    public void write(String string) {
        super.write("<" + (this.syslogFacility | this.level) + ">" + string);
    }
}
