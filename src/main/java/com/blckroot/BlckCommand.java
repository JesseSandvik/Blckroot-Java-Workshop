package com.blckroot;

import picocli.CommandLine;

@CommandLine.Command(name = "blck")
public class BlckCommand implements Runnable {
    @CommandLine.Parameters(index = "0", description = "Executable Name")
    static String executableName;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    static void PrintStatus() {
        String[] machineData = {
                System.getProperty("user.name"),
                System.getProperty("os.name"),
                System.getProperty("os.version")
        };

        for (int i = 0; i < machineData.length; i++) {
            System.out.printf("[%s]      |      %s",);
        }
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
        System.exit(0);
    }
    public static void main(String[] args) {
        int cmd = new CommandLine(new BlckCommand()).execute(args);
        System.exit(cmd);
    }
}
