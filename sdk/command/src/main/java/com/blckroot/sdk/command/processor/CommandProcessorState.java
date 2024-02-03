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
            String currentDir = System.getProperty("user.dir");
            String fileSeparator = FileSystems.getDefault().getSeparator();
            String commandPropertiesFilePath = currentDir + fileSeparator + "sdk/command/src/test/resources/etc/echo.properties";
            CommandPropertiesManager.setPropertiesFromFile(command, commandPropertiesFilePath);
            CommandPropertiesManager.setAttributesFromProperties(command);
            CommandPropertiesManager.setPositionalParametersFromProperties(command);
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
