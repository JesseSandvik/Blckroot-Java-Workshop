package com.blckroot.sdk.filesystem;

import com.blckroot.sdk.filesystem.executor.FileSystemExecutor;
import com.blckroot.sdk.filesystem.validator.FileSystemValidator;
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
        String path = "sdk/filesystem/src/test/resources/echo";
        FileSystemValidator fileSystemValidator = new FileSystemValidator();

        if (fileSystemValidator.fileExists(path) && fileSystemValidator.fileCanExecute(path)) {
            command.add(path);
        }
        command.add("HELLO THERE");

        fileSystemExecutor.executeCommand(command);
        System.exit(0);
    }
}
