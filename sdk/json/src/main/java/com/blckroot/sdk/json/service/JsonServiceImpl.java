package com.blckroot.sdk.json.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JsonServiceImpl implements JsonService {
    @Override
    public Properties getPropertiesFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object object = objectMapper.readValue(new FileReader(filePath), Object.class);

            JavaPropsMapper javaPropsMapper = new JavaPropsMapper();
            return javaPropsMapper.writeValueAsProperties(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
