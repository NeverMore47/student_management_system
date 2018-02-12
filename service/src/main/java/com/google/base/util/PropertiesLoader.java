package com.google.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zengxiangyuan on 2018/2/8.
 */
public class PropertiesLoader {

    private Properties getProperties(String propertyFilename) {
        final Properties properties = new Properties();
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(propertyFilename);
            properties.load(new InputStreamReader(resourceAsStream, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return properties;
    }

    public static Properties loadProperties(String propertyFilename) {
        return new PropertiesLoader().getProperties(propertyFilename);
    }
}
