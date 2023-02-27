package com.kitchen.sdk.log4j.spi;

import com.kitchen.sdk.log4j.Level;
import com.kitchen.sdk.log4j.Logger;
import com.kitchen.sdk.log4j.helpers.LogLog;

public final class RootLogger extends Logger {
    public RootLogger(Level level) {
        super("root");
        setLevel(level);
    }

    public final Level getChainedLevel() {
        return this.level;
    }

    @Override
    public final void setLevel(Level level) {
        if (level == null) {
            LogLog.error("You have tried to set a null level to root.", new Throwable());
        } else {
            this.level = level;
        }
    }
}