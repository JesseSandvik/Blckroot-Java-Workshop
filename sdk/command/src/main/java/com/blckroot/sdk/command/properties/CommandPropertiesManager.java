package com.blckroot.sdk.command.properties;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import com.blckroot.sdk.command.model.Option;
import com.blckroot.sdk.command.model.PositionalParameter;
import com.blckroot.sdk.filesystem.service.FileSystemService;

import java.util.Properties;

public class CommandPropertiesManager {
    public static void setPropertiesFromFile(Command command, String filePath) {
        FileSystemService fileSystemService = new FileSystemService();
        Properties properties = fileSystemService.getPropertiesFromFile(filePath);
        command.setProperties(properties);
    }

    public static void setAttributesFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String COMMAND_VERSION_PROPERTY_KEY="version";
        final String COMMAND_SYNOPSIS_PROPERTY_KEY="synopsis";
        final String COMMAND_DESCRIPTION_PROPERTY_KEY="description";
        final String COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY="execute.without.arguments";

        if (properties.getProperty(COMMAND_VERSION_PROPERTY_KEY) != null) {
            command.setVersion(properties.getProperty(COMMAND_VERSION_PROPERTY_KEY));
        }

        if (properties.getProperty(COMMAND_SYNOPSIS_PROPERTY_KEY) != null) {
            command.setSynopsis(properties.getProperty(COMMAND_SYNOPSIS_PROPERTY_KEY));
        }

        if (properties.getProperty(COMMAND_DESCRIPTION_PROPERTY_KEY) != null) {
            command.setDescription(properties.getProperty(COMMAND_DESCRIPTION_PROPERTY_KEY));
        }

        if (properties.getProperty(COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY) != null) {
            command.setExecutesWithoutArguments(Boolean.valueOf(
                    properties.getProperty(COMMAND_EXECUTES_WITHOUT_ARGUMENTS_PROPERTY_KEY)));
        }
    }

    public static void setPositionalParametersFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY="positional.parameter.count";

        if (properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY) != null) {
            int positionalParameterCount =
                    Integer.parseInt(properties.getProperty(POSITIONAL_PARAMETER_COUNT_PROPERTY_KEY));
            final String POSITIONAL_PARAMETER_LABEL_PROPERTY_KEY="positional.parameter.label";
            final String POSITIONAL_PARAMETER_SYNOPSIS_PROPERTY_KEY="positional.parameter.synopsis";

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

                command.addPositionalParameter(positionalParameter);
            }
        }
    }

    public static void setOptionsFromProperties(Command command) {
        Properties properties = command.getProperties();
        final String OPTION_COUNT_PROPERTY_KEY="option.count";

        if (properties.getProperty(OPTION_COUNT_PROPERTY_KEY) != null) {
            int optionCount = Integer.parseInt(properties.getProperty(OPTION_COUNT_PROPERTY_KEY));
            final String OPTION_LONG_NAME_PROPERTY_KEY="option.long.name";
            final String OPTION_SHORT_NAME_PROPERTY_KEY="option.short.name";
            final String OPTION_SYNOPSIS_PROPERTY_KEY="option.synopsis";
            final String OPTION_LABEL_PROPERTY_KEY="option.parameter.label";

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

                command.addOption(option);
            }
        }
    }

    public static void setSubcommandProperties(Command command) {
        Properties properties = command.getProperties();
        final String SUBCOMMANDS_PROPERTY_KEY="subcommands";

        if (properties.getProperty(SUBCOMMANDS_PROPERTY_KEY) != null) {
            String[] subcommandNames = properties.getProperty(SUBCOMMANDS_PROPERTY_KEY).split(",");

            for (String subcommandName : subcommandNames) {
                Command subcommand = CommandFactory.create(subcommandName);
//                TODO: add properties file filepath as argument for subcommand
//                CommandPropertiesManager.setPropertiesFromFile(subcommand, "");
//                CommandPropertiesManager.setAttributesFromProperties(subcommand);
//                CommandPropertiesManager.setPositionalParametersFromProperties(subcommand);
//                CommandPropertiesManager.setOptionsFromProperties(subcommand);
//                CommandPropertiesManager.setSubcommandProperties(subcommand);
                command.addSubcommand(subcommand);
            }
        }
    }
}
