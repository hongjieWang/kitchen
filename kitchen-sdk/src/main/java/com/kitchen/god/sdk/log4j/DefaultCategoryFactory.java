package com.kitchen.god.sdk.log4j;


import com.kitchen.god.sdk.log4j.spi.LoggerFactory;

class DefaultCategoryFactory implements LoggerFactory {
    @Override
    public Logger makeNewLoggerInstance(String name) {
        return new Logger(name);
    }
}