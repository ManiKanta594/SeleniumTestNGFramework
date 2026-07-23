package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import constants.FrameworkConstants;
import enums.BrowserType;

public final class ConfigReader {

    private static volatile ConfigReader instance;

    private final Properties frameworkProperties;
    private final Properties environmentProperties;

    private ConfigReader() {

        frameworkProperties = new Properties();
        environmentProperties = new Properties();

        loadFrameworkProperties();
        loadEnvironmentProperties();
    }

    public static ConfigReader getInstance() {

        if (instance == null) {

            synchronized (ConfigReader.class) {

                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }

        return instance;
    }

    // ==========================
    // Load Framework Properties
    // ==========================

    private void loadFrameworkProperties() {

        try (FileInputStream file = new FileInputStream(
                FrameworkConstants.CONFIG_PATH + "framework.properties")) {

            frameworkProperties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load framework.properties", e);
        }
    }

    // ==========================
    // Load Environment Properties
    // ==========================

    private void loadEnvironmentProperties() {

        String environment = getFrameworkProperty("environment").toLowerCase();

        try (FileInputStream file = new FileInputStream(
                FrameworkConstants.CONFIG_PATH + environment + ".properties")) {

            environmentProperties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load " + environment + ".properties", e);
        }
    }

    // ==========================
    // Helper Methods
    // ==========================

    private String getFrameworkProperty(String key) {

        return frameworkProperties
                .getProperty(key)
                .trim();
    }

    private String getEnvironmentProperty(String key) {

        return environmentProperties
                .getProperty(key)
                .trim();
    }

    // ==========================
    // Framework Configuration
    // ==========================

    public BrowserType getBrowser() {

        return BrowserType.valueOf(
                getFrameworkProperty("browser").toUpperCase());
    }

    public boolean isHeadless() {

        // First check if Jenkins/Maven passed -Dheadless
        String headless = System.getProperty("headless");

        if (headless != null) {
            return Boolean.parseBoolean(headless);
        }

        // Otherwise use framework.properties
        return Boolean.parseBoolean(
                frameworkProperties.getProperty("headless"));
    }

    public int getImplicitWait() {

        return Integer.parseInt(
                getFrameworkProperty("implicit.wait"));
    }

    public int getExplicitWait() {

        return Integer.parseInt(
                getFrameworkProperty("explicit.wait"));
    }

    public int getPageLoadTimeout() {

        return Integer.parseInt(
                getFrameworkProperty("page.load.timeout"));
    }

    // ==========================
    // Environment Configuration
    // ==========================

    public String getApplicationUrl() {

        return getEnvironmentProperty("application.url");
    }

    public String getUsername() {

        return getEnvironmentProperty("username");
    }

    public String getPassword() {

        return getEnvironmentProperty("password");
    }

}