package drivers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public final class DriverFactory {

    private DriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private static final ConfigReader CONFIG =
            ConfigReader.getInstance();

    public static void initializeDriver(BrowserType browser) {

        boolean headless = CONFIG.isHeadless();

        System.out.println("======================================");
        System.out.println("Browser Parameter  : " + browser);
        System.out.println("Headless Parameter : " + headless);
        System.out.println("======================================");

        switch (browser) {

            case CHROME -> {

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                if (headless) {

                    System.out.println("Launching Chrome in HEADLESS mode");

                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--start-maximized");

                } else {

                    System.out.println("Launching Chrome in HEADED mode");
                }

                DRIVER.set(new ChromeDriver(options));
            }

            case EDGE -> {

                System.setProperty(
                        "webdriver.edge.driver",
                        "C:\\Users\\ASUS\\Downloads\\edgedriver_win64\\msedgedriver.exe");

                EdgeOptions options = new EdgeOptions();

                if (headless) {

                    System.out.println("Launching Edge in HEADLESS mode");

                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");

                } else {

                    System.out.println("Launching Edge in HEADED mode");
                }

                DRIVER.set(new EdgeDriver(options));
            }

            case FIREFOX -> {

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();

                if (headless) {

                    System.out.println("Launching Firefox in HEADLESS mode");

                    options.addArguments("-headless");
                    options.addArguments("--width=1920");
                    options.addArguments("--height=1080");

                } else {

                    System.out.println("Launching Firefox in HEADED mode");
                }

                DRIVER.set(new FirefoxDriver(options));
            }

            default ->
                throw new IllegalArgumentException(
                        "Unsupported Browser : " + browser);
        }

        // Force desktop size for all browsers
        getDriver().manage().window().setSize(new Dimension(1920, 1080));

        System.out.println("Browser Size : "
                + getDriver().manage().window().getSize());

        getDriver().manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {

        if (DRIVER.get() != null) {

            DRIVER.get().quit();

            DRIVER.remove();
        }
    }
}