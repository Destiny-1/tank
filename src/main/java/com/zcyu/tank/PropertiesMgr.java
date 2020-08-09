package com.zcyu.tank;

import java.io.IOException;
import java.util.Properties;

public enum PropertiesMgr {
    INSTANCE;

    private static class PropertiesHandler{
        private static final Properties properties = new Properties();
    }

    public String getConfigValue(String configName) {
        Properties properties = PropertiesHandler.properties;
        String configValue = "";
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("tankConfig"));
            configValue = properties.getProperty(configName);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return configValue;
        }
    }

}
