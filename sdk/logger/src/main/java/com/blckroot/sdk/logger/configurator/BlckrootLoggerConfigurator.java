package com.blckroot.sdk.logger.configurator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class BlckrootLoggerConfigurator implements LoggerConfigurator {
    public void setLevel(System.Logger.Level level) {
        if (level.getName().equalsIgnoreCase("warning")) {
            Configurator.setLevel(LogManager.getRootLogger(), Level.WARN);
            return;
        }

        Configurator.setLevel(LogManager.getRootLogger(), Level.valueOf(level.getName()));
    }
}
