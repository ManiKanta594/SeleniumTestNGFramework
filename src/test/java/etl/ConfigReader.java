package etl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads all framework configuration values
 * from config.properties.
 */
public final class ConfigReader {

    private static final Properties properties = new Properties();

    // Load properties file only once when class is loaded
    static {

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties file not found.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    // Private constructor to prevent object creation
    private ConfigReader() {

    }

    /**
     * Returns property value based on key.
     *
     * Example:
     * ConfigReader.getProperty("db.url");
     */
    public static String getProperty(String key) {

        return properties.getProperty(key);

    }

}