package utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private static final Logger LOGGER =
            LogManager.getLogger(WaitUtil.class);

    private final WebDriverWait wait;
    private final WebDriver driver;

    public WaitUtil(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInstance().getExplicitWait()));

        LOGGER.info("WaitUtil initialized with timeout: {} seconds",
                ConfigReader.getInstance().getExplicitWait());
    }

    public WebElement waitForVisibility(WebElement element) {

        LOGGER.debug("Waiting for element visibility.");

        WebElement visibleElement = wait.until(
                ExpectedConditions.visibilityOf(element));

        LOGGER.debug("Element is visible.");

        return visibleElement;
    }

    public WebElement waitForClickable(WebElement element) {

        LOGGER.debug("Waiting for element to become clickable.");

        WebElement clickableElement = wait.until(
                ExpectedConditions.elementToBeClickable(element));

        LOGGER.debug("Element is clickable.");

        return clickableElement;
    }

    public boolean waitForInvisibility(WebElement element) {

        LOGGER.debug("Waiting for element to become invisible.");

        boolean invisible = wait.until(
                ExpectedConditions.invisibilityOf(element));

        LOGGER.debug("Element is invisible.");

        return invisible;
    }

    public void waitForPageLoad() {

        LOGGER.debug("Waiting for page to load completely.");

        wait.until((ExpectedCondition<Boolean>) driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        LOGGER.debug("Page loaded successfully.");
    }
}