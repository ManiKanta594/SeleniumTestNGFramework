package drivers;

import java.net.MalformedURLException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public final class DriverFactory {

    private DriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER =
            LogManager.getLogger(DriverFactory.class);

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private static final ConfigReader CONFIG =
            ConfigReader.getInstance();

    public static void initializeDriver(BrowserType browser) {

        boolean headless = CONFIG.isHeadless();
        boolean gridExecution = CONFIG.isGridExecution();

        LOGGER.info("======================================");
        LOGGER.info("Execution Mode : {}", gridExecution ? "GRID" : "LOCAL");
        LOGGER.info("Browser        : {}", browser);
        LOGGER.info("Headless       : {}", headless);
        LOGGER.info("======================================");

        try {

            switch (browser) {

                case CHROME -> {

                    ChromeOptions options = new ChromeOptions();

                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080");
                    }

                    if (gridExecution) {

                        LOGGER.info("Launching Chrome on Selenium Grid");

                        DRIVER.set(new RemoteWebDriver(
                                URI.create(CONFIG.getGridUrl()).toURL(),
                                options));

                    } else {

                        LOGGER.info("Launching Chrome Locally");

                        WebDriverManager.chromedriver().setup();
                        DRIVER.set(new ChromeDriver(options));
                    }
                }

                case EDGE -> {

                    EdgeOptions options = new EdgeOptions();

                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080");
                    }

                    if (gridExecution) {

                        LOGGER.info("Launching Edge on Selenium Grid");

                        DRIVER.set(new RemoteWebDriver(
                                URI.create(CONFIG.getGridUrl()).toURL(),
                                options));

                    } else {

                        LOGGER.info("Launching Edge Locally");

                        WebDriverManager.edgedriver().setup();
                        DRIVER.set(new EdgeDriver(options));
                    }
                }

                case FIREFOX -> {

                    FirefoxOptions options = new FirefoxOptions();

                    if (headless) {
                        options.addArguments("-headless");
                        options.addArguments("--width=1920");
                        options.addArguments("--height=1080");
                    }

                    if (gridExecution) {

                        LOGGER.info("Launching Firefox on Selenium Grid");

                        DRIVER.set(new RemoteWebDriver(
                                URI.create(CONFIG.getGridUrl()).toURL(),
                                options));

                    } else {

                        LOGGER.info("Launching Firefox Locally");

                        WebDriverManager.firefoxdriver().setup();
                        DRIVER.set(new FirefoxDriver(options));
                    }
                }

                default ->
                        throw new IllegalArgumentException(
                                "Unsupported Browser : " + browser);
            }

        } catch (MalformedURLException e) {

            LOGGER.error("Invalid Selenium Grid URL : {}", CONFIG.getGridUrl(), e);

            throw new RuntimeException(
                    "Invalid Selenium Grid URL : "
                            + CONFIG.getGridUrl(), e);
        }

        if (headless) {

            getDriver().manage().window()
                    .setSize(new Dimension(1920, 1080));

        } else {

            getDriver().manage().window().maximize();
        }

        LOGGER.info("Browser Size : {}",
                getDriver().manage().window().getSize());

        getDriver().manage().deleteAllCookies();

        LOGGER.info("Browser initialized successfully.");
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {

        if (DRIVER.get() != null) {

            LOGGER.info("Closing browser.");

            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}