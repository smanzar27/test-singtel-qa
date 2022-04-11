package com.singtel.qa.test.configs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    public ConfigReader() { }

    public Properties getProperties(String fileName) {

        String propertyFilePath= Paths.get(System.getProperty("user.dir"),"src","test","resources").toString();
        String propertyFileName=Paths.get(propertyFilePath,fileName).toString();
        BufferedReader reader;
        Properties properties;
        try {
            reader = new BufferedReader(new FileReader(propertyFileName));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + propertyFileName);
        }
        return properties;
    }
}
