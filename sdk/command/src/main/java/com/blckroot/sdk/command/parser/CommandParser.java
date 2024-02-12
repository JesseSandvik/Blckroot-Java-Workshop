package com.blckroot.sdk.command.parser;

import com.blckroot.sdk.command.Command;
import picocli.CommandLine;

public class CommandParser {
    public static Integer parse(Command command) {
        PicocliCommandLineBuilder commandLineBuilder = new PicocliCommandLineBuilder(command)
                .addStandardUsageHelpOption()
                .setUsageHelpSynopsisAsCommandSynopsis()
                .setUsageHelpDescriptionAsCommandDescription()
                .addCustomUsageHelp();

        if (command.getVersion() != null) {
            commandLineBuilder
                    .setUsageHelpVersionAsCommandVersion()
                    .addStandardVersionHelpOption();
        }

        CommandLine commandLine = commandLineBuilder.build();
        return commandLine.execute(command.getOriginalArguments());
    }
}
