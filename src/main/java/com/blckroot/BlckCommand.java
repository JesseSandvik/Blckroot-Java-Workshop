package com.blckroot;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import java.util.HashMap;
import java.util.Map;

@Command(name = "blck")
public class BlckCommand implements Runnable {
    @Parameters(index = "0", description = "Executable Name")
    static String executableName;

    static HashMap<String, String> machineData = new HashMap<>();

    static Integer getLongestMachineDataKey() {
        int longestLength = 0;
        for (Map.Entry<String, String> set: machineData.entrySet()) {
            if (set.getKey().length() > longestLength)
                longestLength = set.getKey().length();
        }
        return longestLength;
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

    @Override
    public void run() {
        setMachineData();
        printMachineStatus();
        System.exit(0);
    }
    public static void main(String[] args) {
        executableName = args[0];
        final CommandSpec rootspec = CommandSpec.create();
        rootspec.name("blck");

        rootspec.addSubcommand(
                executableName,
                CommandSpec.wrapWithoutInspection(
                        new Runnable() {
                            @Override
                            public void run() {
                                CommandLine subcommand = rootspec.subcommands().get(executableName);
                                System.out.printf("Running %s...%n", executableName);

                                CommandSpec spec = subcommand.getCommandSpec();
                                for (OptionSpec option : spec.options()) {
                                    System.out.printf("%s='%s'%n", option.longestName(), option.getValue());
                                }
                                subcommand.usage(System.out);
                            }
                        }
                )
                        .addOption(OptionSpec.builder("runtime-option-a").build())
        );

        System.exit(new CommandLine(rootspec).execute(executableName));
    }
}
