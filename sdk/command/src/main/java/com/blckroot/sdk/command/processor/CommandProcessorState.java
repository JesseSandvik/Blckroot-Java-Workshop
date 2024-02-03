package com.blckroot.sdk.command.processor;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.properties.CommandPropertiesManager;

import java.nio.file.FileSystems;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public enum CommandProcessorState {
    INITIAL {
        @Override
        public CommandProcessorState transitionToNextState() {
            LOGGER.log(
                    Level.TRACE,
                    "transitioning command processor state from "
                            + INITIAL.name() + " to " + ASSEMBLE_ROOT_COMMAND.name()
            );
            return ASSEMBLE_ROOT_COMMAND;
        }

        @Override
        public Integer processCurrentState(Command command) {
            LOGGER.log(Level.TRACE, "executing command processor state: " + INITIAL.name());
            return 0;
        }
    },
    ASSEMBLE_ROOT_COMMAND {
        @Override
        public CommandProcessorState transitionToNextState() {
            LOGGER.log(
                    Level.TRACE,
                    "transitioning command processor state from "
                            + ASSEMBLE_ROOT_COMMAND.name() + " to " + CALL_COMMAND.name()
            );
            return CALL_COMMAND;
        }
        @Override
        public Integer processCurrentState(Command command) {
            LOGGER.log(Level.TRACE, "executing command processor state: " + ASSEMBLE_ROOT_COMMAND.name());
            String applicationRootDirectory = System.getProperty("blckroot.app.root.dir");
            LOGGER.log(Level.DEBUG, "application root directory: " + applicationRootDirectory);

            String fileSeparator = FileSystems.getDefault().getSeparator();
            String commandPropertiesFilePath =
                    applicationRootDirectory + fileSeparator + "etc" + fileSeparator + command.getName() + ".properties";
            LOGGER.log(Level.DEBUG, "command properties file path: " + commandPropertiesFilePath);

            LOGGER.log(Level.TRACE, "setting command properties from file for command: " + command);
            CommandPropertiesManager.setPropertiesFromFile(command, commandPropertiesFilePath);

            LOGGER.log(Level.TRACE, "setting command attributes from properties for command: " + command);
            CommandPropertiesManager.setAttributesFromProperties(command);

            LOGGER.log(Level.TRACE, "setting positional parameters from properties for command: " + command);
            CommandPropertiesManager.setPositionalParametersFromProperties(command);

            LOGGER.log(Level.TRACE, "setting options from properties for command: " + command);
            CommandPropertiesManager.setOptionsFromProperties(command);
            return 0;
        }
    },
    CALL_COMMAND {
        @Override
        public CommandProcessorState transitionToNextState() {
            LOGGER.log(
                    Level.TRACE,
                    "transitioning command processor state from " + CALL_COMMAND.name() + " to " + FINISHED.name()
            );
            return FINISHED;
        }

        @Override
        public Integer processCurrentState(Command command) {
            LOGGER.log(Level.TRACE, "executing command processor state: " + CALL_COMMAND.name());
            LOGGER.log(Level.DEBUG, "calling command: " + command);
            return command.call();
        }
    },
    FINISHED {
        @Override
        public CommandProcessorState transitionToNextState() {
            return null;
        }

        @Override
        public Integer processCurrentState(Command command) {
            return null;
        }
    };

    private final static Logger LOGGER = System.getLogger(CommandProcessorState.class.getName());

    public abstract CommandProcessorState transitionToNextState();
    public abstract Integer processCurrentState(Command command);
}
