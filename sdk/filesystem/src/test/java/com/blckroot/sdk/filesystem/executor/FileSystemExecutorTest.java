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
    void FILE_SYSTEM_EXECUTOR__execute_command__exit_code__success() {
        int expected = 0;
        List<String> command = new ArrayList<>();

        command.add(EXECUTABLE_FILE_PATH);
        command.add("Hello, World!");

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        int actual = fileSystemExecutor.executeCommand(command);
        assertEquals(expected, actual);
    }

    @Test
    void FILE_SYSTEM_EXECUTOR__execute_command__output__success() {
        String expected = "Hello, World!";
        List<String> command = new ArrayList<>();

        command.add(EXECUTABLE_FILE_PATH);
        command.add(expected);

        FileSystemExecutor fileSystemExecutor = new FileSystemExecutor();
        fileSystemExecutor.executeCommand(command);

        assertTrue(outputStream.toString().contains(expected));
    }
}