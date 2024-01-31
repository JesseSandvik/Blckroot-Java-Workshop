package com.blckroot.sdk.command.properties;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import com.blckroot.sdk.filesystem.service.FileSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class CommandPropertiesManagerTest {
    private final String COMMAND_PROPERTIES_FILE_PATH = "src/test/resources/etc/echo.properties";
    private final Command command = CommandFactory.create("echo");
    private Properties commandProperties;

    @BeforeEach
    void getCommandProperties() {
        FileSystemService fileSystemService = new FileSystemService();
        commandProperties = fileSystemService.getPropertiesFromFile(COMMAND_PROPERTIES_FILE_PATH);
    }

    // === PROPERTIES SET FROM FILE =========

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__file_path_null() {
        CommandPropertiesManager.setPropertiesFromFile(command, null);
        assertNull(command.getProperties());
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__version() {
        String expected = commandProperties.getProperty("version");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("version");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__synopsis() {
        String expected = commandProperties.getProperty("synopsis");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("synopsis");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__description() {
        String expected = commandProperties.getProperty("description");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("description");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__execute_without_arguments() {
        boolean expected = Boolean.parseBoolean(commandProperties.getProperty("execute.without.arguments"));
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        boolean actual = Boolean.parseBoolean(command.getProperties().getProperty("execute.without.arguments"));

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__positional_parameter__count() {
        int expected = Integer.parseInt(commandProperties.getProperty("positional.parameter.count"));
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        int actual = Integer.parseInt(command.getProperties().getProperty("positional.parameter.count"));

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__positional_parameter__label() {
        String expected = commandProperties.getProperty("1.positional.parameter.label");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.positional.parameter.label");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__positional_parameter__synopsis() {
        String expected = commandProperties.getProperty("1.positional.parameter.synopsis");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.positional.parameter.synopsis");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__option_long_name() {
        String expected = commandProperties.getProperty("1.option.long.name");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.option.long.name");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__option_short_name() {
        String expected = commandProperties.getProperty("1.option.short.name");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.option.short.name");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__option_parameter_label() {
        String expected = commandProperties.getProperty("1.option.parameter.label");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.option.parameter.label");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__option_synopsis() {
        String expected = commandProperties.getProperty("1.option.synopsis");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.option.synopsis");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__subcommands() {
        String expected = commandProperties.getProperty("subcommands");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("subcommands");

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    // === ATTRIBUTES FROM PROPERTIES =========

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_attributes_from_properties__version() {
        String expected = "1.2.3";
        Properties properties = new Properties();
        properties.setProperty("version", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setAttributesFromProperties(command);
        String actual = command.getVersion();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_attributes_from_properties__synopsis() {
        String expected = "Brief synopsis for command";
        Properties properties = new Properties();
        properties.setProperty("synopsis", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setAttributesFromProperties(command);
        String actual = command.getSynopsis();

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_attributes_from_properties__description() {
        String expected = "A detailed description for command with usage examples.";
        Properties properties = new Properties();
        properties.setProperty("description", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setAttributesFromProperties(command);
        String actual = command.getDescription();

        assertNotNull(expected);
        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_attributes_from_properties__execute_without_arguments() {
        boolean expected = true;
        Properties properties = new Properties();
        properties.setProperty("execute.without.arguments", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setAttributesFromProperties(command);
        boolean actual = command.getExecutesWithoutArguments();

        assertEquals(expected, actual);
    }

    // === POSITIONAL PARAMETERS FROM PROPERTIES =========

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__count() {
        int expected = 2;
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        int actual = command.getPositionalParameters().size();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__label() {
        String expected = "text";
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(1));
        properties.setProperty("1.positional.parameter.label", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        String actual = command.getPositionalParameters().get(0).getLabel();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__synopsis() {
        String expected = "The text to output to the console";
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(1));
        properties.setProperty("1.positional.parameter.synopsis", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        String actual = command.getPositionalParameters().get(0).getSynopsis();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__value() {
        String expected = "true";
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(1));
        properties.setProperty("1.positional.parameter.value", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        String actual = (String) command.getPositionalParameters().get(0).getValue();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__value__boolean() {
        boolean expected = true;
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(1));
        properties.setProperty("1.positional.parameter.value", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        boolean actual = Boolean.parseBoolean((String) command.getPositionalParameters().get(0).getValue());

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_positional_parameters_from_properties__value__integer() {
        int expected = 5;
        Properties properties = new Properties();
        properties.setProperty("positional.parameter.count", String.valueOf(1));
        properties.setProperty("1.positional.parameter.value", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setPositionalParametersFromProperties(command);
        int actual = Integer.parseInt((String) command.getPositionalParameters().get(0).getValue());

        assertEquals(expected, actual);
    }

    // === OPTIONS FROM PROPERTIES =========

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__count() {
        int expected = 2;
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        int actual = command.getOptions().size();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__long_name() {
        String expected = "--optionA";
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.long.name", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        String actual = command.getOptions().get(0).getLongName();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__short_name() {
        String expected = "-a";
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.short.name", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        String actual = command.getOptions().get(0).getShortName();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__synopsis() {
        String expected = "A brief description of '--optionA'";
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.synopsis", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        String actual = command.getOptions().get(0).getSynopsis();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__parameter_label() {
        String expected = "<label>";
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.parameter.label", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        String actual = command.getOptions().get(0).getParameterLabel();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__parameter_value() {
        String expected = "true";
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.value", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        String actual = (String) command.getOptions().get(0).getValue();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__value__boolean() {
        boolean expected = true;
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.value", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        boolean actual = Boolean.parseBoolean((String) command.getOptions().get(0).getValue());

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_options_from_properties__value__integer() {
        int expected = 5;
        Properties properties = new Properties();
        properties.setProperty("option.count", String.valueOf(1));
        properties.setProperty("1.option.value", String.valueOf(expected));
        command.setProperties(properties);

        CommandPropertiesManager.setOptionsFromProperties(command);
        int actual = Integer.parseInt((String) command.getOptions().get(0).getValue());

        assertEquals(expected, actual);
    }

    // === SUBCOMMANDS FROM PROPERTIES =========

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_subcommands_from_properties__count() {
        int expected = 2;
        Properties properties = new Properties();
        properties.setProperty("subcommand.count", String.valueOf(expected));
        properties.setProperty("1.subcommand.name", "subcommandA");
        properties.setProperty("2.subcommand.name", "subcommandB");
        command.setProperties(properties);

        CommandPropertiesManager.setSubcommandsFromProperties(command);
        int actual = command.getSubcommands().size();

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_subcommands_from_properties__name() {
        String expected = "subcommandA";
        Properties properties = new Properties();
        properties.setProperty("subcommand.count", String.valueOf(1));
        properties.setProperty("1.subcommand.name", expected);
        command.setProperties(properties);

        CommandPropertiesManager.setSubcommandsFromProperties(command);
        String actual = command.getSubcommands().get(0).getName();

        assertEquals(expected, actual);
    }
}
