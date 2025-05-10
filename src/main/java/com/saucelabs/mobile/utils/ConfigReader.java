package com.saucelabs.mobile.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    public static void loadProperties(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}