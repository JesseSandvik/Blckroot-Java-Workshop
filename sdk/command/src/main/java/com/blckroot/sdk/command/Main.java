package com.blckroot.sdk.command;

import com.blckroot.sdk.command.model.PositionalParameter;
import com.blckroot.sdk.command.processor.CommandProcessor;
import com.blckroot.sdk.logger.configurator.BlckrootLoggerConfigurator;
import com.blckroot.sdk.logger.configurator.LoggerConfigurator;

public class Main {
    public static void main(String[] args) {
        System.setProperty("blckroot.app.root.dir", "sdk/command/src/test/resources");
        LoggerConfigurator loggerConfigurator = new BlckrootLoggerConfigurator();
        loggerConfigurator.setLevel(System.Logger.Level.DEBUG);

        Command command = CommandFactory.create("echo");
        PositionalParameter positionalParameter = new PositionalParameter();
        positionalParameter.setLabel("text");
        positionalParameter.setValue("Hello, world!");
        command.addPositionalParameter(positionalParameter);

        CommandProcessor commandProcessor = new CommandProcessor(command);
        int exitCode = commandProcessor.execute();
        System.exit(exitCode);
    }
}
