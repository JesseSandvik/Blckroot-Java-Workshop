package com.blckroot.sdk.filesystem.validator;

import java.io.File;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public class FileSystemValidator {
    private static final Logger LOGGER = System.getLogger(FileSystemValidator.class.getName());
    public Boolean fileCanExecute(String file) {
        if (file == null) {
            LOGGER.log(Level.TRACE, "provided file path is null for file can execute validation");
            return false;
        }

        File potentialExecutableFile = new File(file);
        LOGGER.log(Level.TRACE, "validating file can execute: " + file);
        return potentialExecutableFile.canExecute() && potentialExecutableFile.isFile();
    }

    public Boolean fileExists(String file) {
        if (file == null) {
            LOGGER.log(Level.TRACE, "provided file path is null for file exists validation");
            return false;
        }
        LOGGER.log(Level.TRACE, "validating file exists: " + file);
        return new File(file).isFile();
    }
}