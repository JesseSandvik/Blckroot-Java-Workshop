package com.blckroot.sdk.command;

import com.blckroot.sdk.filesystem.executor.FileSystemExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ExecutorCommand extends Command {
    private final String APP_BASE_DIRECTORY_PROPERTY_KEY = "app.base.dir";
    private final String EXECUTABLE_FILE_PATH_PROPERTY_KEY = "executable.file.path";

    @Override
    public Integer call() {
        Properties properties = this.getProperties();

        String executableFilePath = properties.getProperty(EXECUTABLE_FILE_PATH_PROPERTY_KEY);
        String applicationBaseDirectory = System.getProperty(APP_BASE_DIRECTORY_PROPERTY_KEY);
        List<String> command = new ArrayList<>();

        command.add(executableFilePath);

        this.getPositionalParameters().forEach(positionalParameter -> {
            if (positionalParameter.getValue() != null) {
                command.add(positionalParameter.getValue().toString());
            }
        });

        this.getOptions().forEach(option -> {
            if (option.getValue() != null) {
                command.add(option.getLongName());
                command.add(option.getValue().toString());
            }
        });

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        return fileSystemExecutor.executeCommand(command);
    }
}
