package com.blckroot.sdk.command.parser;

import com.blckroot.sdk.command.Command;
import com.blckroot.sdk.command.CommandFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
