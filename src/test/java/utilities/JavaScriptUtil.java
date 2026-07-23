package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private final JavascriptExecutor jsExecutor;

    public JavaScriptUtil(WebDriver driver) {
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void clickElement(WebElement element) {

        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView(WebElement element) {

        jsExecutor.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }

    public void scrollToTop() {

        jsExecutor.executeScript(
                "window.scrollTo(0,0);");
    }

    public void scrollToBottom() {

        jsExecutor.executeScript(
                "window.scrollTo(0,document.body.scrollHeight);");
    }

}