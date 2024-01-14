package com.blckroot.sdk.logger;

import com.blckroot.sdk.logger.configurator.BlckrootLoggerConfigurator;
import com.blckroot.sdk.logger.configurator.LoggerConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {
    LoggerConfigurator loggerConfigurator = new BlckrootLoggerConfigurator();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setupStreams() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @BeforeEach
    void resetLoggerConfiguration() {
        loggerConfigurator.setLevel(Level.ERROR);
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
        boolean actual = logger.isLoggable(Level.OFF);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__OFF__true_for_log_level_OFF() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.OFF);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.OFF);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__TRACE__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.TRACE);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__TRACE__true_for_log_level_TRACE() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.TRACE);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.TRACE);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__DEBUG__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.DEBUG);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__DEBUG__true_for_log_level_DEBUG() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.DEBUG);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.DEBUG);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__INFO__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.INFO);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__INFO__true_for_log_level_INFO() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.INFO);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.INFO);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__WARNING__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.WARNING);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__WARNING__true_for_log_level_WARNING() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.WARNING);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.WARNING);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ERROR__true_for_log_level_default() {
        boolean expected = true;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ERROR);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ERROR__true_for_log_level_ERROR() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.ERROR);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ERROR);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__false_for_log_level_default() {
        boolean expected = false;
        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__false_for_log_level_OFF() {
        boolean expected = false;
        loggerConfigurator.setLevel(Level.OFF);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__true_for_log_level_TRACE() {
        boolean expected = true;
        loggerConfigurator.setLevel(Level.TRACE);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__true_for_log_level_DEBUG() {
        boolean expected = false;
        loggerConfigurator.setLevel(Level.DEBUG);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__false_for_log_level_INFO() {
        boolean expected = false;
        loggerConfigurator.setLevel(Level.INFO);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }

    @Test
    void LOGGER__isLoggable__ALL__false_for_log_level_WARNING() {
        boolean expected = false;
        loggerConfigurator.setLevel(Level.WARNING);

        Logger logger = System.getLogger(LoggerTest.class.getName());
        boolean actual = logger.isLoggable(Level.ALL);
        assertEquals(expected, actual);
    }
}
