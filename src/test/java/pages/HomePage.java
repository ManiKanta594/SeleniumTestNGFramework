package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ScreenshotUtil;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "navbtn_tutorials")
    private WebElement tutorials;

    @FindBy(xpath = "//button[contains(@class,'profile')]")
    private WebElement profileIcon;

    @FindBy(css = "a.logout")
    private WebElement logout;

    /**
     * Click Tutorials menu.
     */
    public void clickTutorials() {

        closeTrialPopupIfPresent();

        waitUtil.waitForClickable(tutorials).click();
    }

    /**
     * Close Trial Popup if displayed.
     */
    private void closeTrialPopupIfPresent() {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[normalize-space()='Close']")));

            List<WebElement> closeButtons = driver.findElements(
                    By.xpath("//button[normalize-space()='Close']"));

            if (closeButtons.size() > 0) {

                closeButtons.get(0).click();
            }

        } catch (TimeoutException e) {

            // Popup not displayed. Continue execution.
        }
    }

    /**
     * Logout from W3Schools.
     */
    public void logout() {

        waitUtil.waitForVisibility(profileIcon);

        jsUtil.scrollIntoView(profileIcon);

        jsUtil.clickElement(profileIcon);

        waitUtil.waitForVisibility(logout);

        jsUtil.scrollIntoView(logout);

        jsUtil.clickElement(logout);
    }
}