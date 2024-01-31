package com.blckroot.sdk.command.execution;

public enum CommandExecutionState {
    INITIAL {
        @Override
        public CommandExecutionState transitionToNextState() {
            return null;
        }

        @Override
        public void execute() {

        }
    },
    SET_ROOT_COMMAND_ATTRIBUTES {
        @Override
        public CommandExecutionState transitionToNextState() {
            return null;
        }

        @Override
        public void execute() {

        }
    },
    SET_SUBCOMMAND_ATTRIBUTES {
        @Override
        public CommandExecutionState transitionToNextState() {
            return null;
        }

        @Override
        public void execute() {

        }
    },
    CALL_COMMAND {
        @Override
        public CommandExecutionState transitionToNextState() {
            return null;
        }

        @Override
        public void execute() {

        }
    },
    COMPLETE {
        @Override
        public CommandExecutionState transitionToNextState() {
            return null;
        }

        @Override
        public void execute() {

        }
    };

    public abstract CommandExecutionState transitionToNextState();
    public abstract void execute();
}
