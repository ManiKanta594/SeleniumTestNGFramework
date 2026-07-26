package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enums.BrowserType;

public final class ConfigReader {

    private static final Logger LOGGER =
            LogManager.getLogger(ConfigReader.class);

    private static volatile ConfigReader instance;

    private final Properties frameworkProperties;
    private final Properties environmentProperties;

    private ConfigReader() {

        frameworkProperties = new Properties();
        environmentProperties = new Properties();

        LOGGER.info("Initializing ConfigReader...");

        loadFrameworkProperties();
        loadEnvironmentProperties();

        LOGGER.info("Configuration loaded successfully.");
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

        LOGGER.info("Loading framework.properties...");

        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/framework.properties")) {

            if (inputStream == null) {

                LOGGER.error("framework.properties not found in classpath.");

                throw new RuntimeException(
                        "framework.properties not found in classpath.");
            }

            frameworkProperties.load(inputStream);

            LOGGER.info("framework.properties loaded successfully.");

        } catch (IOException e) {

            LOGGER.error("Unable to load framework.properties", e);

            throw new RuntimeException(
                    "Unable to load framework.properties", e);
        }
    }

    // ==========================
    // Load Environment Properties
    // ==========================

    private void loadEnvironmentProperties() {

        String environment = getEnvironment().toLowerCase();

        LOGGER.info("Loading {}.properties...", environment);

        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/" + environment + ".properties")) {

            if (inputStream == null) {

                LOGGER.error("{}.properties not found in classpath.", environment);

                throw new RuntimeException(
                        environment + ".properties not found in classpath.");
            }

            environmentProperties.load(inputStream);

            LOGGER.info("{}.properties loaded successfully.", environment);

        } catch (IOException e) {

            LOGGER.error("Unable to load {}.properties", environment, e);

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

    /**
     * Priority:
     * 1. System Property (-D)
     * 2. framework.properties
     */
    private String getProperty(String key) {

        String systemValue = System.getProperty(key);

        if (systemValue != null && !systemValue.isBlank()) {

            LOGGER.debug("Reading '{}' from System Property.", key);

            return systemValue.trim();
        }

        return getFrameworkProperty(key);
    }

    // ==========================
    // Framework Configuration
    // ==========================

    public BrowserType getBrowser() {

        return BrowserType.valueOf(
                getProperty("browser").toUpperCase());
    }

    public boolean isHeadless() {

        return Boolean.parseBoolean(
                getProperty("headless"));
    }

    public String getExecutionMode() {

        return getProperty("execution.mode");
    }

    public boolean isGridExecution() {

        return getExecutionMode()
                .equalsIgnoreCase("GRID");
    }

    public String getGridUrl() {

        return getProperty("grid.url");
    }

    public String getEnvironment() {

        return getProperty("environment");
    }

    public int getImplicitWait() {

        return Integer.parseInt(
                getProperty("implicit.wait"));
    }

    public int getExplicitWait() {

        return Integer.parseInt(
                getProperty("explicit.wait"));
    }

    public int getPageLoadTimeout() {

        return Integer.parseInt(
                getProperty("page.load.timeout"));
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