package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JavaPage extends BasePage {

    public JavaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Java Intro']")
    private WebElement javaIntro;

    @FindBy(xpath = "//a[normalize-space()='Java HOME']")
    private WebElement javaHome;

    @FindBy(xpath = "//a[@title='SQL Tutorial']")
    private WebElement sqlTutorial;

    @FindBy(xpath = "//a[@title='HTML Tutorial']")
    private WebElement htmlTutorial;

    public void clickJavaIntro() {

        waitUtil.waitForClickable(javaIntro).click();
    }

    public void clickJavaHome() {

        waitUtil.waitForClickable(javaHome).click();
    }

    public void clickSQLTutorial() {

        waitUtil.waitForClickable(sqlTutorial).click();
    }

    public void clickHTMLTutorial() {

        waitUtil.waitForClickable(htmlTutorial).click();
    }
}