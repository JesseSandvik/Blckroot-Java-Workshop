package com.blckroot.sdk.command.properties;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import com.blckroot.sdk.command.model.Option;
import com.blckroot.sdk.command.model.PositionalParameter;
import com.blckroot.sdk.filesystem.service.FileSystemService;
import com.blckroot.sdk.filesystem.validator.FileSystemValidator;

import java.util.Properties;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public class CommandPropertiesManager {
    private final static Logger LOGGER = System.getLogger(CommandPropertiesManager.class.getName());
    public static void setPropertiesFromFile(Command command, String filePath) {
        LOGGER.log(Level.TRACE, "setting command properties from file: " + filePath);
        FileSystemValidator fileSystemValidator = new FileSystemValidator();

        LOGGER.log(Level.TRACE, "validating file exists: " + filePath);
        if (fileSystemValidator.fileExists(filePath)) {
            LOGGER.log(Level.TRACE, "file exists: " + filePath);
            FileSystemService fileSystemService = new FileSystemService();

            LOGGER.log(Level.TRACE, "getting properties from file: " + filePath);
            Properties properties = fileSystemService.getPropertiesFromFile(filePath);

            LOGGER.log(Level.TRACE, "setting properties for command: " + command);
            command.setProperties(properties);
        } else {
            LOGGER.log(Level.TRACE, "command properties not set. File does not exist: " + filePath);
        }
    }

    public static void setAttributesFromProperties(Command command) {
        LOGGER.log(Level.TRACE, "setting command attributes from properties for command: " + command);
        Properties properties = command.getProperties();

        final String COMMAND_VERSION_PROPERTY_KEY = "version";
        LOGGER.log(Level.TRACE, "COMMAND_VERSION_PROPERTY_KEY: " + COMMAND_VERSION_PROPERTY_KEY);

        final String COMMAND_SYNOPSIS_PROPERTY_KEY = "synopsis";
        LOGGER.log(Level.TRACE, "COMMAND_SYNOPSIS_PROPERTY_KEY: " + COMMAND_SYNOPSIS_PROPERTY_KEY);

        final String COMMAND_DESCRIPTION_PROPERTY_KEY = "description";
        LOGGER.log(Level.TRACE, "COMMAND_DESCRIPTION_PROPERTY_KEY: " + COMMAND_DESCRIPTION_PROPERTY_KEY);

        final String COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY = "execute.without.arguments";
        LOGGER.log(Level.TRACE,
                "COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY: " + COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY);

        if (properties.getProperty(COMMAND_VERSION_PROPERTY_KEY) != null) {
            String commandVersion = properties.getProperty(COMMAND_VERSION_PROPERTY_KEY);
            LOGGER.log(Level.TRACE,
                    "command version found in properties. Setting command version: " + commandVersion);
            command.setVersion(commandVersion);
        }

        if (properties.getProperty(COMMAND_SYNOPSIS_PROPERTY_KEY) != null) {
            String commandSynopsis = properties.getProperty(COMMAND_SYNOPSIS_PROPERTY_KEY);
            LOGGER.log(Level.TRACE,
                    "command synopsis found in properties. Setting command synopsis: " + commandSynopsis);
            command.setSynopsis(commandSynopsis);
        }

        if (properties.getProperty(COMMAND_DESCRIPTION_PROPERTY_KEY) != null) {
            String commandDescription = properties.getProperty(COMMAND_DESCRIPTION_PROPERTY_KEY);
            LOGGER.log(Level.TRACE,
                    "command description found in properties. Setting command description: " + commandDescription);
            command.setDescription(commandDescription);
        }

        if (properties.getProperty(COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY) != null) {
            boolean commandExecutesWithoutArguments = Boolean.parseBoolean(
                    properties.getProperty(COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY));
            LOGGER.log(
                    Level.TRACE,
                    "command executes without arguments value found in properties. " +
                            "Setting command executes without arguments value: " + commandExecutesWithoutArguments);
            command.setExecutesWithoutArguments(commandExecutesWithoutArguments);
        }
    }

    public static void setPositionalParametersFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY = "positional.parameter.count";

        if (properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY) != null) {
            int positionalParameterCount =
                    Integer.parseInt(properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY));
            final String POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY = "positional.parameter.label";
            final String POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY = "positional.parameter.synopsis";
            final String POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY = "positional.parameter.value";

            for (int i = 1; i <= positionalParameterCount; i+=1) {
                PositionalParameter positionalParameter = new PositionalParameter();

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY) != null) {
                    positionalParameter.setLabel(
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY) != null) {
                    positionalParameter.setSynopsis(
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY) != null) {
                    positionalParameter.setValue(
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY));
                }
                command.addPositionalParameter(positionalParameter);
            }
        }
    }

    public static void setOptionsFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String OPTION_COUNT_PROPERTY_KEY = "option.count";

        if (properties.getProperty(OPTION_COUNT_PROPERTY_KEY) != null) {
            int optionCount = Integer.parseInt(properties.getProperty(OPTION_COUNT_PROPERTY_KEY));
            final String OPTION_LONG_NAME_PROPERTY_KEY = "option.long.name";
            final String OPTION_SHORT_NAME_PROPERTY_KEY = "option.short.name";
            final String OPTION_SYNOPSIS_PROPERTY_KEY = "option.synopsis";
            final String OPTION_LABEL_PROPERTY_KEY = "option.parameter.label";
            final String OPTION_VALUE_PROPERTY_KEY = "option.value";

            for (int i = 1; i <= optionCount; i+=1) {
                Option option = new Option();

                if (properties.getProperty(i + "." + OPTION_LONG_NAME_PROPERTY_KEY) != null) {
                    option.setLongName(properties.getProperty(i + "." + OPTION_LONG_NAME_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + OPTION_SHORT_NAME_PROPERTY_KEY) != null) {
                    option.setShortName(properties.getProperty(i + "." + OPTION_SHORT_NAME_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + OPTION_SYNOPSIS_PROPERTY_KEY) != null) {
                    option.setSynopsis(properties.getProperty(i + "." + OPTION_SYNOPSIS_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + OPTION_LABEL_PROPERTY_KEY) != null) {
                    option.setParameterLabel(properties.getProperty(i + "." + OPTION_LABEL_PROPERTY_KEY));
                }

                if (properties.getProperty(i + "." + OPTION_VALUE_PROPERTY_KEY) != null) {
                    option.setValue(properties.getProperty(i + "." + OPTION_VALUE_PROPERTY_KEY));
                }
                command.addOption(option);
            }
        }
    }

    public static void setSubcommandsFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String SUBCOMMAND_COUNT_PROPERTY_KEY = "subcommand.count";

        if (properties.getProperty(SUBCOMMAND_COUNT_PROPERTY_KEY) != null) {
            int subcommandCount = Integer.parseInt(properties.getProperty(SUBCOMMAND_COUNT_PROPERTY_KEY));

            for (int i = 1; i <= subcommandCount; i+=1) {
                final String SUBCOMMAND_NAME_PROPERTY_KEY = "subcommand.name";

                if (properties.getProperty(i + "." + SUBCOMMAND_NAME_PROPERTY_KEY) != null) {
                    Command subcommand =
                            CommandFactory.create(properties.getProperty(i + "." + SUBCOMMAND_NAME_PROPERTY_KEY));
                    command.addSubcommand(subcommand);
                }
            }
        }
    }
}
