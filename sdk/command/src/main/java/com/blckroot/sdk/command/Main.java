package com.blckroot.sdk.command;

import com.blckroot.sdk.command.processor.CommandProcessor;
import com.blckroot.sdk.logger.configurator.BlckrootLoggerConfigurator;
import com.blckroot.sdk.logger.configurator.LoggerConfigurator;

public class Main {
    public static void main(String[] args) {
        System.setProperty("blckroot.app.root.dir", "sdk/command/src/test/resources");
        LoggerConfigurator loggerConfigurator = new BlckrootLoggerConfigurator();
        loggerConfigurator.setLevel(System.Logger.Level.ERROR);

        Command command = CommandFactory.create("echo");
        command.setOriginalArguments(args);

        CommandProcessor commandProcessor = new CommandProcessor(command);
        int exitCode = commandProcessor.execute();
        System.exit(exitCode);
    }
}
