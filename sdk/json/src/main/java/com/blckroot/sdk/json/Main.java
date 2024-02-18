package com.blckroot.sdk.json;

import com.blckroot.sdk.json.service.JsonService;
import com.blckroot.sdk.json.service.JsonServiceImpl;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties systemProperties = System.getProperties();
        String jsonFilePath = systemProperties.getProperty("user.dir") +
                systemProperties.getProperty("file.separator") +
                "sdk" +
                systemProperties.getProperty("file.separator") +
                "json" +
                systemProperties.getProperty("file.separator") +
                "src" +
                systemProperties.getProperty("file.separator") +
                "main" +
                systemProperties.getProperty("file.separator") +
                "resources" +
                systemProperties.getProperty("file.separator") +
                "test.json";
        JsonService jsonService = new JsonServiceImpl();
        Properties jsonProperties = jsonService.getPropertiesFromJsonFile(jsonFilePath);
        System.out.println(jsonProperties);
    }
}