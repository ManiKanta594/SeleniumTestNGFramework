package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import drivers.DriverFactory;
import enums.BrowserType;
import listeners.AllureListener;
import listeners.SuiteListener;
import listeners.TestListener;
import managers.PageObjectManager;
import utilities.ConfigReader;

@Listeners({
        TestListener.class,
        SuiteListener.class,
        AllureListener.class
})
public class BaseTest {

    private static final Logger LOGGER =
            LogManager.getLogger(BaseTest.class);

    protected ConfigReader config;
    protected PageObjectManager pageObjectManager;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("") String browser) {

        config = ConfigReader.getInstance();

        BrowserType browserType;

        if (browser == null || browser.isBlank()) {
            browserType = config.getBrowser();
        } else {
            browserType = BrowserType.valueOf(browser.toUpperCase());
        }

        LOGGER.info("=================================================");
        LOGGER.info("Starting Test Execution");
        LOGGER.info("Thread ID      : {}", Thread.currentThread().getId());
        LOGGER.info("Test Class     : {}", this.getClass().getSimpleName());
        LOGGER.info("Browser        : {}", browserType);
        LOGGER.info("Environment    : {}", config.getEnvironment());
        LOGGER.info("Application URL: {}", config.getApplicationUrl());
        LOGGER.info("=================================================");

        DriverFactory.initializeDriver(browserType);

        LOGGER.info("Driver Created : {}", DriverFactory.getDriver());

        DriverFactory.getDriver().get(config.getApplicationUrl());

        pageObjectManager = new PageObjectManager(DriverFactory.getDriver());

        LOGGER.info("Page Object Manager initialized successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        LOGGER.info("Closing Driver : {}", DriverFactory.getDriver());

        DriverFactory.quitDriver();

        LOGGER.info("Driver closed successfully.");
        LOGGER.info("=================================================");
    }
}