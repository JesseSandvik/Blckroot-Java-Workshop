package com.blckroot.sdk.filesystem.executor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemExecutorTest {
    private final String EXECUTABLE_FILE_PATH = "src/test/resources/echo";
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        outputStream.reset();
        errorStream.reset();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void FILE_SYSTEM_EXECUTOR__execute_command__exit_code_0_on_success() {
        int expected = 0;
        List<String> command = new ArrayList<>();

        command.add(EXECUTABLE_FILE_PATH);
        command.add("Hello, World!");

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        int actual = fileSystemExecutor.executeCommand(command);
        assertEquals(expected, actual);
    }

    @Test
    void FILE_SYSTEM_EXECUTOR__execute_command__prints_to_system_out_on_success_by_default() {
        String expected = "Hello, World!";
        List<String> command = new ArrayList<>();

        command.add(EXECUTABLE_FILE_PATH);
        command.add(expected);

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        fileSystemExecutor.executeCommand(command);

        assertTrue(outputStream.toString().contains(expected));
    }

    @Test
    void FILE_SYSTEM_EXECUTOR__execute_command__prints_to_system_err_on_success_with_updated_print_stream() {
        String expected = "Hello, World!";
        List<String> command = new ArrayList<>();

        command.add(EXECUTABLE_FILE_PATH);
        command.add(expected);

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        fileSystemExecutor.setPrintStream(System.err);
        fileSystemExecutor.executeCommand(command);

        assertTrue(errorStream.toString().contains(expected));
    }
}
