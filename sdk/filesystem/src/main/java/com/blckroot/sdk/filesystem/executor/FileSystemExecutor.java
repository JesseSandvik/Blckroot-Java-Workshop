package com.blckroot.sdk.filesystem.executor;

import java.io.*;
import java.util.List;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public class FileSystemExecutor implements Executor {
    private static final Logger LOGGER = System.getLogger(FileSystemExecutor.class.getName());
    private PrintStream printStream = System.out;

    @Override
    public PrintStream getPrintStream() {
        return printStream;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        LOGGER.log(Level.TRACE, "original file system executor print stream: " + getPrintStream());
        this.printStream = printStream;
        LOGGER.log(Level.TRACE, "updated file system executor print stream: " + printStream);
    }

    @Override
    public Integer executeCommand(List<String> command) {
        try {
            LOGGER.log(Level.TRACE, "executing command: " + command);

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(command);

            LOGGER.log(Level.TRACE, "initializing command execution process");
            Process process = processBuilder.start();

            LOGGER.log(Level.TRACE, "extracting input stream from command execution process");
            InputStream inputStream = process.getInputStream();

            LOGGER.log(Level.TRACE, "reading input stream from command execution process");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            LOGGER.log(Level.TRACE, "preparing to print input stream from command execution to print stream");
            while ((line = bufferedReader.readLine()) != null) {
                LOGGER.log(Level.TRACE, "printing input stream line from command execution to print stream");
                printStream.println(line);
            }

            LOGGER.log(Level.TRACE, "waiting for command execution process to terminate");
            process.waitFor();
            LOGGER.log(Level.TRACE, "command execution process terminated, returning exit code");
            LOGGER.log(Level.TRACE, "executed command: "  + command + " resulted in exit code: " + process.exitValue());
            return process.exitValue();

        } catch (RuntimeException | InterruptedException | IOException e) {
            LOGGER.log(Level.ERROR, "an exception occurred while executing command: " + command);
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
    }
}
