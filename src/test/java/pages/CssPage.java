package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CssPage extends BasePage {

    public CssPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='CSS Introduction']")
    private WebElement cssIntroduction;

    @FindBy(xpath = "//a[normalize-space()='CSS HOME']")
    private WebElement cssHome;

    public void clickCSSIntroduction() {

        waitUtil.waitForClickable(cssIntroduction).click();
    }

    public void clickCSSHome() {

        waitUtil.waitForClickable(cssHome).click();
    }
}