package com.blckroot.sdk.command.parser;

import com.blckroot.sdk.command.Command;
import picocli.CommandLine;

public class CommandParser {
    public static Integer parse(Command command) {
        CommandLine commandLine = new PicocliCommandLineBuilder(command)
                .addStandardUsageHelpOption()
                .setUsageHelpSynopsisAsCommandSynopsis()
                .setUsageHelpDescriptionAsCommandDescription()
                .addCustomUsageHelp()
                .build();

        return commandLine.execute(command.getOriginalArguments());
    }
}
