package com.kitchen.sdk.metrics.log4j;

import com.kitchen.sdk.metrics.log4j.helpers.FileWatchdog;

class PropertyWatchdog extends FileWatchdog {
    PropertyWatchdog(String filename) {
        super(filename);
    }

    @Override
    public void doOnChange() {
        (new PropertyConfigurator()).doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}