package com.blckroot.sdk.command.properties;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import com.blckroot.sdk.filesystem.service.FileSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__synopsis() {
        String expected = commandProperties.getProperty("synopsis");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("synopsis");

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__description() {
        String expected = commandProperties.getProperty("description");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("description");

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

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PROPERTIES_MANAGER__set_properties_from_file__positional_parameter__synopsis() {
        String expected = commandProperties.getProperty("1.positional.parameter.synopsis");
        CommandPropertiesManager.setPropertiesFromFile(command, COMMAND_PROPERTIES_FILE_PATH);
        String actual = command.getProperties().getProperty("1.positional.parameter.synopsis");

        assertEquals(expected, actual);
    }
}
