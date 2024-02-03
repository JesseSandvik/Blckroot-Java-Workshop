package com.blckroot.sdk.command;

import com.blckroot.sdk.filesystem.executor.FileSystemExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public class ExecutorCommand extends Command {
    private final static Logger LOGGER = System.getLogger(ExecutorCommand.class.getName());

    @Override
    public Integer call() {
        LOGGER.log(Level.TRACE, "executing call for executor command: " + this);
        Properties properties = this.getProperties();

        final String EXECUTABLE_FILE_PATH_PROPERTY_KEY = "executable.file.path";
        String executableFilePath = properties.getProperty(EXECUTABLE_FILE_PATH_PROPERTY_KEY);
        LOGGER.log(Level.TRACE, "executable.file.path property value: " + executableFilePath);

        List<String> command = new ArrayList<>();

        command.add(executableFilePath);
        LOGGER.log(Level.TRACE, "added to command: " + executableFilePath);

        if (!this.getPositionalParameters().isEmpty()) {
            LOGGER.log(Level.TRACE, "parsing positional parameters list of length: " + this.getPositionalParameters().size());
            this.getPositionalParameters().forEach(positionalParameter -> {
                LOGGER.log(Level.TRACE, "validating positional parameter: " + positionalParameter.getLabel());
                if (positionalParameter.getValue() != null) {
                    LOGGER.log(Level.TRACE, "value detected for positional parameter: " + positionalParameter.getLabel());

                    command.add(positionalParameter.getValue().toString());
                    LOGGER.log(Level.TRACE, "positional parameter value added to command: " +
                            positionalParameter.getValue().toString());
                }
            });
        }

        if (!this.getOptions().isEmpty()) {
            LOGGER.log(Level.TRACE, "parsing options list of length: " + this.getOptions().size());
            this.getOptions().forEach(option -> {
                LOGGER.log(Level.TRACE, "validating option: " + option.getLongName());
                if (option.getValue() != null) {
                    LOGGER.log(Level.TRACE, "value detected for option: " + option.getLongName());

                    command.add(option.getLongName());
                    LOGGER.log(Level.TRACE, "option long name added to command: " + option.getLongName());

                    command.add(option.getValue().toString());
                    LOGGER.log(Level.TRACE, "option value added to command: " + option.getValue().toString());
                }
            });
        }

        LOGGER.log(Level.TRACE, "initializing file system executor");
        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();

        LOGGER.log(Level.TRACE, "utilizing file system executor to execute command: " + command);
        return fileSystemExecutor.executeCommand(command);
    }
}
