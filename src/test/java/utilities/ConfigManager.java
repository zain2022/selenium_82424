package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        try {
            FileInputStream fileInput = new FileInputStream("config/config.properties");
            properties = new Properties();
            if (fileInput == null) {
                throw new FileNotFoundException("Property file not found in the defined path");
            }
            properties.load(fileInput);
            fileInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}