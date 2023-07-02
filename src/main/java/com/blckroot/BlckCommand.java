package com.blckroot;

import picocli.CommandLine;

import java.util.HashMap;
import java.util.Map;

@CommandLine.Command(name = "blck")
public class BlckCommand implements Runnable {
    @CommandLine.Parameters(index = "0", description = "Executable Name")
    static String executableName;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

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
        machineData.forEach((key, value) -> System.out.printf("[ %-" + justifyByLength +"S ]\t%s%n", key, value));
    }

    @Override
    public void run() {
        setMachineData();
        printMachineStatus();
        System.exit(0);
    }
    public static void main(String[] args) {
        int cmd = new CommandLine(new BlckCommand()).execute(args);
        System.exit(cmd);
    }
}
