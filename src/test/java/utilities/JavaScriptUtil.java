package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private static final Logger LOGGER =
            LogManager.getLogger(JavaScriptUtil.class);

    private final JavascriptExecutor jsExecutor;

    public JavaScriptUtil(WebDriver driver) {

        this.jsExecutor = (JavascriptExecutor) driver;

        LOGGER.info("JavaScriptUtil initialized.");
    }

    public void clickElement(WebElement element) {

        LOGGER.debug("Clicking element using JavaScript.");

        jsExecutor.executeScript("arguments[0].click();", element);

        LOGGER.debug("Element clicked successfully using JavaScript.");
    }

    public void scrollIntoView(WebElement element) {

        LOGGER.debug("Scrolling element into view.");

        jsExecutor.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);

        LOGGER.debug("Element scrolled into view.");
    }

    public void scrollToTop() {

        LOGGER.debug("Scrolling to the top of the page.");

        jsExecutor.executeScript("window.scrollTo(0,0);");

        LOGGER.debug("Page scrolled to the top.");
    }

    public void scrollToBottom() {

        LOGGER.debug("Scrolling to the bottom of the page.");

        jsExecutor.executeScript(
                "window.scrollTo(0,document.body.scrollHeight);");

        LOGGER.debug("Page scrolled to the bottom.");
    }

}