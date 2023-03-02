package com.kitchen.sdk.metrics.log4j.spi;

import com.kitchen.sdk.metrics.log4j.Logger;

public interface LoggerFactory {
    Logger makeNewLoggerInstance(String paramString);
}
