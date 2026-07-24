package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "tnb-login-btn")
    private WebElement loginButton;

    @FindBy(id = "tnb-login-dropdown-email")
    private WebElement emailTextBox;

    @FindBy(id = "tnb-login-dropdown-password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {

        waitUtil.waitForClickable(loginButton).click();

        waitUtil.waitForVisibility(emailTextBox).clear();
        emailTextBox.sendKeys(username);

        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);

        waitUtil.waitForClickable(signInButton);

        jsUtil.clickElement(signInButton);

        waitUtil.waitForPageLoad();
        System.out.println("Cookies Count : " +
                driver.manage().getCookies().size());

        System.out.println(driver.manage().getCookies());
        
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        System.out.println(
            "Sign In Button Count : "
            + driver.findElements(By.xpath("//a[normalize-space()='Sign In']")).size());

        System.out.println(
            "Profile Button Count : "
            + driver.findElements(By.xpath("//button[contains(@class,'profile')]")).size());
    }
}