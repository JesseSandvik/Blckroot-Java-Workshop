package com.blckroot.sdk.filesystem.executor;

import java.io.PrintStream;
import java.util.List;

public interface Executor {
    PrintStream getPrintStream();
    void setPrintStream(PrintStream printStream);
    Integer executeCommand(List<String> command);
}
