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
        LOGGER.log(Level.DEBUG, "setting command properties from file: " + filePath);
        FileSystemValidator fileSystemValidator = new FileSystemValidator();

        LOGGER.log(Level.TRACE, "validating file exists: " + filePath);
        if (fileSystemValidator.fileExists(filePath)) {
            LOGGER.log(Level.TRACE, "file exists: " + filePath);
            FileSystemService fileSystemService = new FileSystemService();

            LOGGER.log(Level.DEBUG, "getting properties from file: " + filePath);
            Properties properties = fileSystemService.getPropertiesFromFile(filePath);

            LOGGER.log(Level.DEBUG, "setting properties for command: " + command);
            command.setProperties(properties);
        } else {
            LOGGER.log(Level.WARNING, "command properties not set. File does not exist: " + filePath);
        }
    }

    public static void setAttributesFromProperties(Command command) {
        LOGGER.log(Level.DEBUG, "setting command attributes from properties for command: " + command);
        Properties properties = command.getProperties();

        final String COMMAND_VERSION_PROPERTY_KEY = "version";
        LOGGER.log(Level.DEBUG, "COMMAND_VERSION_PROPERTY_KEY: " + COMMAND_VERSION_PROPERTY_KEY);

        final String COMMAND_SYNOPSIS_PROPERTY_KEY = "synopsis";
        LOGGER.log(Level.DEBUG, "COMMAND_SYNOPSIS_PROPERTY_KEY: " + COMMAND_SYNOPSIS_PROPERTY_KEY);

        final String COMMAND_DESCRIPTION_PROPERTY_KEY = "description";
        LOGGER.log(Level.DEBUG, "COMMAND_DESCRIPTION_PROPERTY_KEY: " + COMMAND_DESCRIPTION_PROPERTY_KEY);

        final String COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY = "execute.without.arguments";
        LOGGER.log(Level.DEBUG,
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
        LOGGER.log(Level.DEBUG, "setting positional parameters from properties for command: " + command);
        Properties properties = command.getProperties();

        final String POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY = "positional.parameter.count";
        LOGGER.log(Level.DEBUG,
                "POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY: " + POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY);

        if (properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY) != null) {
            int positionalParameterCount =
                    Integer.parseInt(properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY));
            LOGGER.log(Level.TRACE,
                    "positional parameter count found in properties. " + positionalParameterCount);

            final String POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY = "positional.parameter.label";
            LOGGER.log(Level.DEBUG, "POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY: " +
                    POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY);

            final String POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY = "positional.parameter.synopsis";
            LOGGER.log(Level.DEBUG, "POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY: " +
                    POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY);

            final String POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY = "positional.parameter.value";
            LOGGER.log(Level.DEBUG, "POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY: " +
                    POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY);

            for (int i = 1; i <= positionalParameterCount; i+=1) {
                PositionalParameter positionalParameter = new PositionalParameter();

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY) != null) {
                    String positionalParameterLabel =
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "positional parameter label found in properties. " +
                                    "Setting positional parameter label: " + positionalParameterLabel);
                    positionalParameter.setLabel(positionalParameterLabel);
                }

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY) != null) {
                    String positionalParameterSynopsis =
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "positional parameter synopsis found in properties. " +
                                    "Setting positional parameter synopsis: " + positionalParameterSynopsis);
                    positionalParameter.setSynopsis(positionalParameterSynopsis);
                }

                if (properties.getProperty(i + "." + POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY) != null) {
                    String positionalParameterValue =
                            properties.getProperty(i + "." + POSITIONAL_PARAMETER_VALUE_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "positional parameter value found in properties. " +
                                    "Setting positional parameter value: " + positionalParameterValue);
                    positionalParameter.setValue(positionalParameterValue);
                }
                LOGGER.log(Level.DEBUG, "adding positional parameter to command: " + command);
                command.addPositionalParameter(positionalParameter);
            }
        }
    }

    public static void setOptionsFromProperties(Command command) {
        LOGGER.log(Level.DEBUG, "setting options from properties for command: " + command);
        Properties properties = command.getProperties();

        final String OPTION_COUNT_PROPERTY_KEY = "option.count";
        LOGGER.log(Level.DEBUG, "OPTION_COUNT_PROPERTY_KEY: " + OPTION_COUNT_PROPERTY_KEY);

        if (properties.getProperty(OPTION_COUNT_PROPERTY_KEY) != null) {
            int optionCount = Integer.parseInt(properties.getProperty(OPTION_COUNT_PROPERTY_KEY));
            LOGGER.log(Level.TRACE, "option count found in properties. " + optionCount);

            final String OPTION_LONG_NAME_PROPERTY_KEY = "option.long.name";
            LOGGER.log(Level.DEBUG, "OPTION_LONG_NAME_PROPERTY_KEY: " + OPTION_LONG_NAME_PROPERTY_KEY);

            final String OPTION_SHORT_NAME_PROPERTY_KEY = "option.short.name";
            LOGGER.log(Level.DEBUG, "OPTION_SHORT_NAME_PROPERTY_KEY: " + OPTION_SHORT_NAME_PROPERTY_KEY);

            final String OPTION_SYNOPSIS_PROPERTY_KEY = "option.synopsis";
            LOGGER.log(Level.DEBUG, "OPTION_SYNOPSIS_PROPERTY_KEY: " + OPTION_SYNOPSIS_PROPERTY_KEY);

            final String OPTION_LABEL_PROPERTY_KEY = "option.parameter.label";
            LOGGER.log(Level.DEBUG, "OPTION_LABEL_PROPERTY_KEY: " + OPTION_LABEL_PROPERTY_KEY);

            final String OPTION_VALUE_PROPERTY_KEY = "option.value";
            LOGGER.log(Level.DEBUG, "OPTION_VALUE_PROPERTY_KEY: " + OPTION_VALUE_PROPERTY_KEY);

            for (int i = 1; i <= optionCount; i+=1) {
                Option option = new Option();

                if (properties.getProperty(i + "." + OPTION_LONG_NAME_PROPERTY_KEY) != null) {
                    String optionLongName = properties.getProperty(i + "." + OPTION_LONG_NAME_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "option long name found in properties. Setting option long name: " + optionLongName);
                    option.setLongName(optionLongName);
                }

                if (properties.getProperty(i + "." + OPTION_SHORT_NAME_PROPERTY_KEY) != null) {
                    String optionShortName = properties.getProperty(i + "." + OPTION_SHORT_NAME_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "option short name found in properties. " +
                                    "Setting option short name: " + optionShortName);
                    option.setShortName(optionShortName);
                }

                if (properties.getProperty(i + "." + OPTION_SYNOPSIS_PROPERTY_KEY) != null) {
                    String optionSynopsis = properties.getProperty(i + "." + OPTION_SYNOPSIS_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "option synopsis found in properties. Setting option synopsis: " + optionSynopsis);
                    option.setSynopsis(optionSynopsis);
                }

                if (properties.getProperty(i + "." + OPTION_LABEL_PROPERTY_KEY) != null) {
                    String optionLabel = properties.getProperty(i + "." + OPTION_LABEL_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "option label found in properties. Setting option label: " + optionLabel);
                    option.setParameterLabel(optionLabel);
                }

                if (properties.getProperty(i + "." + OPTION_VALUE_PROPERTY_KEY) != null) {
                    String optionValue = properties.getProperty(i + "." + OPTION_VALUE_PROPERTY_KEY);
                    LOGGER.log(Level.TRACE,
                            "option value found in properties. Setting option value: " + optionValue);
                    option.setValue(optionValue);
                }
                LOGGER.log(Level.DEBUG, "adding option to command: " + command);
                command.addOption(option);
            }
        }
    }

    public static void setSubcommandsFromProperties(Command command) {
        LOGGER.log(Level.DEBUG, "setting subcommands from properties for command: " + command);
        Properties properties = command.getProperties();

        final String SUBCOMMANDS_PROPERTY_KEY = "subcommands";
        LOGGER.log(Level.DEBUG, "SUBCOMMANDS_PROPERTY_KEY: " + SUBCOMMANDS_PROPERTY_KEY);

        if (properties.getProperty(SUBCOMMANDS_PROPERTY_KEY) != null) {
            String[] subcommandNames = properties.getProperty(SUBCOMMANDS_PROPERTY_KEY).split(",");

            for (String subcommandName : subcommandNames) {
                Command subcommand = CommandFactory.create(subcommandName);
                command.addSubcommand(subcommand);
            }
        }
    }
}
