package com.blckroot.sdk.command.processor;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.properties.CommandPropertiesManager;

import java.nio.file.FileSystems;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public enum CommandProcessorState {
    INITIAL {
        @Override
        public CommandProcessorState transitionToNextState() {
            LOGGER.log(
                    Level.TRACE,
                    "transitioning command processor state from "
                            + INITIAL.name() + " to " + ASSEMBLE_COMMANDS.name()
            );
            return ASSEMBLE_COMMANDS;
        }

        @Override
        public Integer processCurrentState(Command command) {
            LOGGER.log(Level.TRACE, "executing command processor state: " + INITIAL.name());
            return 0;
        }
    },
    ASSEMBLE_COMMANDS {
        @Override
        public CommandProcessorState transitionToNextState() {
            LOGGER.log(
                    Level.TRACE,
                    "transitioning command processor state from "
                            + ASSEMBLE_COMMANDS.name() + " to " + CALL_COMMAND.name()
            );
            return CALL_COMMAND;
        }
        @Override
        public Integer processCurrentState(Command command) {
            LOGGER.log(Level.TRACE, "executing command processor state: " + ASSEMBLE_COMMANDS.name());

            String commandPropertiesFilePath = getCommandPropertiesFilePath(command);
            LOGGER.log(Level.TRACE, "setting command properties from file for command: " + command);
            CommandPropertiesManager.setPropertiesFromFile(command, commandPropertiesFilePath);

            LOGGER.log(Level.TRACE, "setting command attributes from properties for command: " + command);
            CommandPropertiesManager.setAttributesFromProperties(command);

            LOGGER.log(Level.TRACE, "setting positional parameters from properties for command: " + command);
            CommandPropertiesManager.setPositionalParametersFromProperties(command);

            LOGGER.log(Level.TRACE, "setting options from properties for command: " + command);
            CommandPropertiesManager.setOptionsFromProperties(command);

            LOGGER.log(Level.TRACE, "setting options from properties for command: " + command);
            CommandPropertiesManager.setSubcommandsFromProperties(command);

            LOGGER.log(Level.TRACE, "assembling subcommands for command: " + command);
            Queue<Command> commandQueue = new ArrayDeque<>(command.getSubcommands());
            while (!commandQueue.isEmpty()) {
                Command subcommand = commandQueue.remove();
                LOGGER.log(Level.DEBUG, "assembling subcommand: " + subcommand);
                processCurrentState(subcommand);

                if (!subcommand.getSubcommands().isEmpty()) {
                    LOGGER.log(
                            Level.TRACE,
                            "adding nested subcommands to assembly queue for subcommand: " + subcommand
                    );
                    commandQueue.addAll(subcommand.getSubcommands());
                }

                LOGGER.log(Level.DEBUG, "adding subcommand: " + subcommand + " to command: " + command);
                command.addSubcommand(subcommand);
            }
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

    private static String getCommandPropertiesFilePath(Command command) {
        String applicationRootDirectory = System.getProperty("blckroot.app.root.dir");
        LOGGER.log(Level.DEBUG, "application root directory: " + applicationRootDirectory);

        String fileSeparator = FileSystems.getDefault().getSeparator();
        String commandPropertiesFilePath =
                applicationRootDirectory + fileSeparator + "etc" + fileSeparator + command.getName() + ".properties";
        LOGGER.log(Level.DEBUG, "command properties file path: " + commandPropertiesFilePath);
        return commandPropertiesFilePath;
    }

    public abstract CommandProcessorState transitionToNextState();
    public abstract Integer processCurrentState(Command command);
}
