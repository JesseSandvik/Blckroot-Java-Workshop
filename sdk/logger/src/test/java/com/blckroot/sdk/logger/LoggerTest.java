package com.blckroot.sdk.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import static java.lang.System.Logger;

public class LoggerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setupStreams() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void LOGGER__system_logger_provides_instance_of_Logger() {
        Logger logger = System.getLogger(LoggerTest.class.getName());
        assertInstanceOf(Logger.class, logger);
    }

    @Test
    void LOGGER__logger_name_matches_value_provided_as_getLogger_argument() {
        String expected = LoggerTest.class.getName();
        Logger logger = System.getLogger(expected);

        String actual = logger.getName();
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__OFF__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.OFF);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__TRACE__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.TRACE);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__DEBUG__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.DEBUG);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__INFO__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.INFO);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__WARNING__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.WARNING);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ERROR__true_for_log_level_default() {
        boolean expected = true;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.ERROR);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(System.Logger.Level.ALL);
        assertEquals(expected, actual);
    }
}
