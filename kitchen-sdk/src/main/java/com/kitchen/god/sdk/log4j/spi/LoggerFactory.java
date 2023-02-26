package com.kitchen.god.sdk.log4j.spi;

import com.kitchen.god.sdk.log4j.Logger;

public interface LoggerFactory {
    Logger makeNewLoggerInstance(String paramString);
}
