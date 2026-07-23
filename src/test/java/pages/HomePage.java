package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ScreenshotUtil;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@data-tnb-nav-id='tutorials']")
    private WebElement tutorials;

    @FindBy(xpath = "//button[contains(@class,'profile')]")
    private WebElement profileIcon;

    @FindBy(css = "a.logout")
    private WebElement logout;

    /**
     * Click Tutorials menu.
     */
    public void clickTutorials() {

        System.out.println("========== HOME PAGE DEBUG ==========");
        System.out.println("Current URL : " + driver.getCurrentUrl());
        System.out.println("Page Title  : " + driver.getTitle());

        List<WebElement> popup = driver.findElements(
                By.xpath("//p[contains(text(),'Your trial has expired')]"));

        System.out.println("Popup count : " + popup.size());

        ScreenshotUtil.captureScreenshot("BeforeTutorialClick");

        if (popup.size() > 0) {
            driver.findElement(
                    By.xpath("//button[normalize-space()='Close']")).click();
        }

        waitUtil.waitForClickable(tutorials).click();
    }
    /**
     * Logout from W3Schools.
     */
    public void logout() {

        waitUtil.waitForClickable(profileIcon).click();

        waitUtil.waitForVisibility(logout);

        jsUtil.scrollIntoView(logout);

        jsUtil.clickElement(logout);
    }
}