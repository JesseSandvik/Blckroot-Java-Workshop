package com.blckroot.sdk.command.parser;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    private final Command command = CommandFactory.create("test");
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        outputStream.reset();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void COMMAND_PARSER__prints_usage_help_for_standard_help_short_option() {
        String[] arguments = new String[]{"-h"};
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
    }

    @Test
    void COMMAND_PARSER__prints_usage_help_for_standard_help_long_option() {
        String[] arguments = new String[]{"--help"};
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
    }

    @Test
    void COMMAND_PARSER__returns_success_exit_code_for_successful_execution() {
        int expected = 0;

        String[] arguments = new String[]{"-h"};
        command.setOriginalArguments(arguments);
        int actual = CommandParser.parse(command);

        assertEquals(expected, actual);
    }

    @Test
    void COMMAND_PARSER__prints_command_synopsis_as_usage_help_synopsis() {
        String expected = "Synopsis for the test command";
        command.setSynopsis(expected);

        String[] arguments = new String[]{"-h"};
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains(expected));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
    }

    @Test
    void COMMAND_PARSER__prints_command_description_as_usage_help_description() {
        String expected = "A detailed description for the test command including examples.";
        command.setDescription(expected);

        String[] arguments = new String[]{"-h"};
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains(expected));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
    }

    @Test
    void COMMAND_PARSER__usage_help_does_not_include_standard_version_help_for_command_without_version() {
        String[] arguments = new String[]{"--help"};
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
        assertFalse(outputStream.toString().contains("-v"));
        assertFalse(outputStream.toString().contains("--version"));
    }

    @Test
    void COMMAND_PARSER__usage_help_includes_standard_version_help_for_command_with_version() {
        String[] arguments = new String[]{"--help"};
        command.setVersion("test command version 1.0.0");
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(command.getName()));
        assertTrue(outputStream.toString().contains("-h"));
        assertTrue(outputStream.toString().contains("--help"));
        assertTrue(outputStream.toString().contains("-v"));
        assertTrue(outputStream.toString().contains("--version"));
    }

    @Test
    void COMMAND_PARSER__prints_version_help_for_standard_version_short_option() {
        String expected = "test command version 1.0.0";
        String[] arguments = new String[]{"-v"};
        command.setVersion(expected);
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(expected));
    }

    @Test
    void COMMAND_PARSER__prints_version_help_for_standard_version_long_option() {
        String expected = "test command version 1.0.0";
        String[] arguments = new String[]{"--version"};
        command.setVersion(expected);
        command.setOriginalArguments(arguments);
        CommandParser.parse(command);

        assertTrue(outputStream.toString().contains(expected));
    }
}
