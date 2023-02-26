package com.kitchen.god.sdk.log4j.helpers;

import com.kitchen.god.sdk.log4j.spi.ErrorHandler;

import java.io.IOException;
import java.io.Writer;

public class CountingQuietWriter extends QuietWriter {
    protected long count;

    public CountingQuietWriter(Writer writer, ErrorHandler eh) {
        super(writer, eh);
    }

    @Override
    public void write(String string) {
        try {
            this.out.write(string);
            this.count += string.length();
        } catch (IOException e) {
            this.errorHandler.error("Write failure.", e, 1);
        }
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}