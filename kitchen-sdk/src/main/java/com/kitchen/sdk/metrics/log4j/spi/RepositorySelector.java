package com.kitchen.sdk.metrics.log4j.spi;

public interface RepositorySelector {
  LoggerRepository getLoggerRepository();
}
