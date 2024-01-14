package com.blckroot.sdk.filesystem.executor;

import java.io.*;
import java.util.List;

public class FileSystemExecutor implements Executor {
    private PrintStream printStream = System.out;
    @Override
    public PrintStream getPrintStream() {
        return printStream;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public Integer executeCommand(List<String> command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(command);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printStream.println(line);
            }
            process.waitFor();
            return process.exitValue();

        } catch (RuntimeException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
