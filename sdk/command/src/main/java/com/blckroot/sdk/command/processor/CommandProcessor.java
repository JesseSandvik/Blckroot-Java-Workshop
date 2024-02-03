package com.blckroot.sdk.command.processor;

import com.blckroot.sdk.command.Command;

public class CommandProcessor {
    private CommandProcessorState commandProcessorState;
    private final Command rootCommand;
    private Integer exitCode = 0;

    public CommandProcessor(Command rootCommand) {
        this.commandProcessorState = CommandProcessorState.INITIAL;
        this.rootCommand = rootCommand;
    }

    public Integer execute() {
        while (!commandProcessorState.name().equalsIgnoreCase(CommandProcessorState.FINISHED.name())) {
            exitCode = commandProcessorState.processCurrentState(rootCommand);
            commandProcessorState = commandProcessorState.transitionToNextState();
        }
        return exitCode;
    }
}
