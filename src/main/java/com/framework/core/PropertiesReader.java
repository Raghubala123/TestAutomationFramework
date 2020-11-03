package com.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader{
	
    private static PropertiesReader instance = null;
    private Properties properties;


    protected PropertiesReader() throws Exception{

        properties = new Properties();
        properties.load(new FileInputStream(new File("Settings.properties")));

    }

    public static PropertiesReader getInstance() {
        if(instance == null) {
            try {
                instance = new PropertiesReader();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }


}
