package com.blckroot;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

import java.util.*;

public class BlckCommand {
    static List<String> subcommand = new ArrayList<>();
    static HashMap<String, String> machineData = new HashMap<>();
    static Integer getLongestMachineDataKey() {
        int longestLength = 0;
        for (Map.Entry<String, String> set: machineData.entrySet()) {
            if (set.getKey().length() > longestLength)
                longestLength = set.getKey().length();
        }
        return longestLength;
    }
    public static void main(String[] args) {
        final CommandSpec rootspec = CommandSpec.create();
        rootspec.name("blck");

        if (args.length < 1) {
            System.exit(1);
        }

        Collections.addAll(subcommand, args);

        rootspec.addSubcommand(
                subcommand.get(0),
                CommandSpec.wrapWithoutInspection((Runnable) () -> {
                    CommandLine currentSubcommand = rootspec.subcommands().get(subcommand.get(0));
                    currentSubcommand.usage(System.out);
                })
                        .addOption(OptionSpec
                                .builder("-v", "--verbose")
                                .description("Print verbose")
                                .build())
                        .addOption(OptionSpec
                                .builder("-h", "--help")
                                .description("Print this help text")
                                .build())
        );
        setMachineData();
        printMachineStatus();
        System.exit(new CommandLine(rootspec).execute(subcommand.get(0)));
    }
    static void setMachineData() {
        machineData.put("username", System.getProperty("user.name"));
        machineData.put("operating system", System.getProperty("os.name"));
        machineData.put("operating system version", System.getProperty("os.version"));
    }
    static void printMachineStatus() {
        int justifyByLength = getLongestMachineDataKey();
        machineData.forEach((key, value) -> System.out.printf("[INFO]\t%-" + justifyByLength + "S\t%s%n", key, value));
    }
}
