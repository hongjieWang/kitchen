package com.kitchen.god.sdk.log4j;

import com.kitchen.god.sdk.log4j.helpers.FileWatchdog;

class PropertyWatchdog extends FileWatchdog {
    PropertyWatchdog(String filename) {
        super(filename);
    }

    @Override
    public void doOnChange() {
        (new PropertyConfigurator()).doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}