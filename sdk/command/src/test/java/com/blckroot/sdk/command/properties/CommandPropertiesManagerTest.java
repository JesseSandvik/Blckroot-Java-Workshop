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
    private Command command = CommandFactory.create("echo");
    private Properties commandProperties;

    @BeforeEach
    void getCommandProperties() {
        FileSystemService fileSystemService = new FileSystemService();
        commandProperties = fileSystemService.getPropertiesFromFile(COMMAND_PROPERTIES_FILE_PATH);
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
}
