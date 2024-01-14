package com.blckroot.sdk.filesystem;

import com.blckroot.sdk.filesystem.executor.FileSystemExecutor;
import com.blckroot.sdk.logger.configurator.BlckrootLoggerConfigurator;
import com.blckroot.sdk.logger.configurator.LoggerConfigurator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoggerConfigurator loggerConfigurator = new BlckrootLoggerConfigurator();
        loggerConfigurator.setLevel(System.Logger.Level.ALL);
        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        List<String> command = new ArrayList<>();
        command.add("sdk/filesystem/src/test/resources/echo");
        command.add("HELLO THERE");

        fileSystemExecutor.executeCommand(command);
        System.exit(0);
    }
}
