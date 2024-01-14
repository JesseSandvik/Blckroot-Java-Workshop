package com.blckroot.sdk.command;

import com.blckroot.sdk.command.model.Option;
import com.blckroot.sdk.command.model.PositionalParameter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExecutorCommandTest {
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
    void EXECUTOR_COMMAND__call__exit_code_0_for_successful_execution() {
        int expected = 0;

        Properties properties = new Properties();
        properties.setProperty("executable.file.path", "src/test/resources/echo");

        Command command = new ExecutorCommand();
        command.setProperties(properties);

        int actual = command.call();
        assertEquals(expected, actual);
    }

    @Test
    void EXECUTOR_COMMAND__call__executes_file_for_property_executable_file_path_with_positional_parameter() {
        String expected = "Hello, world!";

        Properties properties = new Properties();
        properties.setProperty("executable.file.path", "src/test/resources/echo");

        Command command = new ExecutorCommand();
        command.setProperties(properties);

        PositionalParameter positionalParameter = new PositionalParameter();
        positionalParameter.setValue(expected);
        command.addPositionalParameter(positionalParameter);

        command.call();
        assertTrue(outputStream.toString().contains(expected));
    }

    @Test
    void EXECUTOR_COMMAND__call__executes_file_for_property_executable_file_path_with_option_long_name() {
        String expected = "--text";
        Properties properties = new Properties();
        properties.setProperty("executable.file.path", "src/test/resources/echo");

        Command command = new ExecutorCommand();
        command.setProperties(properties);

        Option option = new Option();
        option.setLongName("--text");
        option.setValue("true");
        command.addOption(option);

        command.call();
        assertTrue(outputStream.toString().contains(expected));
    }
}
