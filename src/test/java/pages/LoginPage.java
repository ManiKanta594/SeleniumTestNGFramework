package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

        waitUtil.waitForClickable(signInButton).click();

        waitUtil.waitForPageLoad();
    }
}