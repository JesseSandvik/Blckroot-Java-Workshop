package com.blckroot.sdk.command.properties;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import com.blckroot.sdk.filesystem.service.FileSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetCommandPropertiesTest {
    private Command command = CommandFactory.create("echo");
    private Properties commandProperties;

    @BeforeEach
    void setRequiredEnvironmentVariable() {
        System.setProperty("blckroot." + command.getName() + ".base.dir", "src/test/resources");
    }

    @BeforeEach
    void getCommandProperties() {
        FileSystemService fileSystemService = new FileSystemService();
        commandProperties = fileSystemService.getPropertiesFromFile("src/test/resources/etc/echo.properties");
    }

    @Test
    void SET_COMMAND_PROPERTIES__set_command_attributes__version() {
        String expected = commandProperties.getProperty("version");
        SetCommandProperties.setProperties(command);
        String actual = command.getVersion();

        assertEquals(expected, actual);
    }

    @Test
    void SET_COMMAND_PROPERTIES__set_command_attributes__synopsis() {
        String expected = commandProperties.getProperty("synopsis");
        SetCommandProperties.setProperties(command);
        String actual = command.getSynopsis();

        assertEquals(expected, actual);
    }

    @Test
    void SET_COMMAND_PROPERTIES__set_command_attributes__description() {
        String expected = commandProperties.getProperty("description");
        SetCommandProperties.setProperties(command);
        String actual = command.getDescription();

        assertEquals(expected, actual);
    }

    @Test
    void SET_COMMAND_PROPERTIES__set_command_attributes__executes_without_arguments() {
        boolean expected = Boolean.parseBoolean(commandProperties.getProperty("execute.without.arguments"));
        SetCommandProperties.setProperties(command);
        boolean actual = command.getExecutesWithoutArguments();

        assertEquals(expected, actual);
    }
}
