package com.restassured.utils;

import com.restassured.constants.Constants;
import com.restassured.enums.ConfigProperties;
import com.restassured.exceptions.InvalidPathForPropertyFileException;
import com.restassured.exceptions.PropertyFileUsageException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class ConfigReaderUtil {

    private static Properties properties = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();
    private ConfigReaderUtil() {

    }

    static {
        try( FileInputStream fileInputStream = new FileInputStream(Constants.CONFIGFILEPATH)) {
            properties.load(fileInputStream);
            for(Map.Entry<Object, Object> entry : properties.entrySet()) {
                CONFIGMAP.put(entry.getKey().toString(), entry.getValue().toString().trim());
            }
        }
        catch (FileNotFoundException e) {
            throw new InvalidPathForPropertyFileException("Config File you trying to read is not found");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(ConfigProperties key)  {
        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }
}
