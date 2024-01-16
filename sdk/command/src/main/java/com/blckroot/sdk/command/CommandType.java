package com.blckroot.sdk.command;

public enum CommandType {
    EXECUTOR {
        @Override
        public Command create(String name) {
            Command command = new ExecutorCommand();
            command.setName(name);
            return command;
        }
    };

    public abstract Command create(String name);
}
