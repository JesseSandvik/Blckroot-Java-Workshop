package com.blckroot.sdk.filesystem.service;

import com.blckroot.sdk.filesystem.validator.FileSystemValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.Logger;
import static java.lang.System.Logger.Level;

public class FileSystemService {
    private static final Logger LOGGER = System.getLogger(FileSystemService.class.getName());
    public Properties getPropertiesFromFile(String filePath) {
        LOGGER.log(Level.TRACE, "getting properties from file: " + filePath);
        FileSystemValidator fileSystemValidator = new FileSystemValidator();

        LOGGER.log(Level.TRACE, "validating file exists: " + filePath);
        if (!fileSystemValidator.fileExists(filePath)) {
            LOGGER.log(Level.TRACE, "file failed validation. File does not exist: " + filePath);
            return null;
        }
        LOGGER.log(Level.TRACE, "file passed validation. File exists: " + filePath);
        LOGGER.log(Level.TRACE, "start file read process");
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            LOGGER.log(Level.TRACE, "loading properties from file: " + filePath);
            properties.load(inputStream);
            LOGGER.log(Level.TRACE, "end file read process");
            return properties;
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "an exception occurred while reading properties from file: " + filePath);
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
    }
}
