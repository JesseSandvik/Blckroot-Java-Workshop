package com.blckroot.sdk.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CommandFactoryTest {

    @Test
    void COMMAND_FACTORY__default__creates_executor_command() {
        Command command = CommandFactory.create("test");
        assertInstanceOf(ExecutorCommand.class, command);
    }
}
