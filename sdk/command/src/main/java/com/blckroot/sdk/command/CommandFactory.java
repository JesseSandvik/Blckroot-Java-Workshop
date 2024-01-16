package com.blckroot.sdk.command;

public class CommandFactory {
    public static Command create(String name) {
        return CommandType.EXECUTOR.create(name);
    }
}
