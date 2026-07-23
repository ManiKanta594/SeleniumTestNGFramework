package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.JavaScriptUtil;
import utilities.WaitUtil;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtil waitUtil;
    protected JavaScriptUtil jsUtil;

    protected BasePage(WebDriver driver) {

        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.jsUtil = new JavaScriptUtil(driver);

        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}