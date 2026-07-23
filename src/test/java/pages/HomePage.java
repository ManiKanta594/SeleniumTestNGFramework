package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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