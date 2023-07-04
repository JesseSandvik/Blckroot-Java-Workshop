package com.blckroot;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

import java.io.*;
import java.util.*;

public class BlckCommand {
    static List<String> subcommand = new ArrayList<>();
    static HashMap<String, String> machineData = new HashMap<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        final CommandSpec rootspec = CommandSpec.create();
        rootspec.name("blck");

        if (args.length < 1) {
            rootspec.commandLine().usage(System.out);
            System.exit(1);
        }

        final String rootCommandExecutableDir = System.getProperty("user.dir");
        final String rootDir = rootCommandExecutableDir.substring(0, rootCommandExecutableDir.lastIndexOf("/"));
        final String appLibDir = String.format("%s/lib", rootDir);
        final String rootCommandConfigFile = String.format("%s/%s.properties", appLibDir, "blck");

        final String subcommandPluginDir = String.format("%s/%s", appLibDir, args[0]);
        final String subcommandConfigFile = String.format("%s/%s.properties", subcommandPluginDir, args[0]);
        final String subcommandExecutableFile = String.format("%s/%s", subcommandPluginDir, args[0]);

        subcommand.add(subcommandExecutableFile);
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                subcommand.add(args[i]);
            }
        }

        InputStream input = new FileInputStream(subcommandConfigFile);
        Properties properties = new Properties();
        properties.load(input);

        final String subCommandName = properties.getProperty("command.name");
        final String[] optionsShort = properties.getProperty("command.options.short").split(",");
        final String[] optionsLong = properties.getProperty("command.options.long").split(",");
        final String[] optionsDescription = properties.getProperty("command.options.description").split(",");

        rootspec.addSubcommand(
                subcommand.get(0),
                CommandSpec.wrapWithoutInspection((Runnable) () -> {
                    CommandLine currentSubcommand = rootspec.subcommands().get(subcommand.get(0));
                    currentSubcommand.usage(System.out);
                })
        );

        CommandLine currentSubcommand = rootspec.subcommands().get(subcommand.get(0));
        CommandSpec currentSubcommandSpec = currentSubcommand.getCommandSpec();

//        Add subcommand for each option from file

        setMachineData();
        printMachineStatus();

        final ProcessBuilder build = new ProcessBuilder();
        build.command(subcommand);
        final Process process = build.start();

        final InputStream inputStream = process.getInputStream();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = reader.readLine()) != null)
            System.out.println(line);

        process.waitFor();
        System.exit(new CommandLine(rootspec).execute(subCommandName));
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
    static Integer getLongestMachineDataKey() {
        int longestLength = 0;
        for (Map.Entry<String, String> set: machineData.entrySet()) {
            if (set.getKey().length() > longestLength)
                longestLength = set.getKey().length();
        }
        return longestLength;
    }
}
