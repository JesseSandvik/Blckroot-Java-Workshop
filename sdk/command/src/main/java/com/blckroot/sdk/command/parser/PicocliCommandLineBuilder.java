package com.blckroot.sdk.command.parser;

import com.blckroot.sdk.command.Command;
import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Model.OptionSpec;

public class PicocliCommandLineBuilder {
    private final Command command;
    private final CommandSpec commandSpec;

    public PicocliCommandLineBuilder(Command command) {
        this.command = command;
        this.commandSpec = CommandSpec.create().name(command.getName());
    }

    public PicocliCommandLineBuilder addCustomUsageHelp() {
        commandSpec.usageMessage()
                .abbreviateSynopsis(true)
                .parameterListHeading("%nPositional Parameters:%n")
                .optionListHeading("%nOptions:%n")
                .commandListHeading("%nCommands:%n");
        return this;
    }

    public PicocliCommandLineBuilder addStandardUsageHelpOption() {
        commandSpec.addOption(OptionSpec
                .builder("-h", "--help")
                .description("Show this help message and exit.")
                .usageHelp(true)
                .build()
        );
        return this;
    }

    public PicocliCommandLineBuilder setUsageHelpDescriptionAsCommandDescription() {
        if (command.getDescription() != null) {
            commandSpec.usageMessage().footer("%n" + command.getDescription() + "%n");
        }
        return this;
    }

    public PicocliCommandLineBuilder setUsageHelpSynopsisAsCommandSynopsis() {
        if (command.getSynopsis() != null) {
            commandSpec.usageMessage().description(command.getSynopsis());
        }
        return this;
    }

    public CommandLine build() {
        return new CommandLine(commandSpec);
    }
}
