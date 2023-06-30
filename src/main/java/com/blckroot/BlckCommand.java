package com.blckroot;

import picocli.CommandLine;

@CommandLine.Command(name = "blck")
public class BlckCommand implements Runnable {
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
