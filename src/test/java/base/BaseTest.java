package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import drivers.DriverFactory;
import enums.BrowserType;
import listeners.SuiteListener;
import listeners.TestListener;
import managers.PageObjectManager;
import utilities.ConfigReader;

@Listeners({
        TestListener.class,
        SuiteListener.class
})
public class BaseTest {

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

        System.out.println("\n==========================================");
        System.out.println("Thread ID   : " + Thread.currentThread().getId());
        System.out.println("Browser     : " + browserType);
        System.out.println("Test Class  : " + this.getClass().getSimpleName());
        System.out.println("==========================================");

        DriverFactory.initializeDriver(browserType);

        System.out.println("Driver Created : " + DriverFactory.getDriver());

        // DriverFactory already maximizes the window and deletes cookies.
        DriverFactory.getDriver().get(config.getApplicationUrl());

        pageObjectManager = new PageObjectManager(DriverFactory.getDriver());

        System.out.println("Application Launched");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println("Closing Driver : " + DriverFactory.getDriver());

        DriverFactory.quitDriver();

        System.out.println("Driver Closed");
    }
}