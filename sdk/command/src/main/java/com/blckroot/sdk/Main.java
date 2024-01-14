package com.blckroot.sdk;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.ExecutorCommand;
import com.blckroot.sdk.command.model.PositionalParameter;
import com.blckroot.sdk.logger.configurator.BlckrootLoggerConfigurator;
import com.blckroot.sdk.logger.configurator.LoggerConfigurator;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        LoggerConfigurator loggerConfigurator = new BlckrootLoggerConfigurator();
        loggerConfigurator.setLevel(System.Logger.Level.DEBUG);

        Properties properties = new Properties();
        properties.setProperty("executable.file.path", "sdk/filesystem/src/test/resources/echo");

        Command command = new ExecutorCommand();

        PositionalParameter positionalParameter = new PositionalParameter();
        positionalParameter.setLabel("TEXT");
        positionalParameter.setValue("HELLO, WORLD!");
        command.addPositionalParameter(positionalParameter);

        command.setProperties(properties);
        command.setName("echo");

        command.call();
    }
}
