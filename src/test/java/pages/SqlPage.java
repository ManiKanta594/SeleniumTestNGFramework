package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SqlPage extends BasePage {

    public SqlPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='SQL Intro']")
    private WebElement sqlIntro;

    @FindBy(xpath = "//a[normalize-space()='SQL HOME']")
    private WebElement sqlHome;

    @FindBy(xpath = "//a[@title='CSS Tutorial']")
    private WebElement cssTutorial;

    @FindBy(xpath = "//a[@title='Java Tutorial']")
    private WebElement javaTutorial;

    public void clickSQLIntro() {

        waitUtil.waitForClickable(sqlIntro).click();
    }

    public void clickSQLHome() {

        waitUtil.waitForClickable(sqlHome).click();
    }

    public void clickCSSTutorial() {

        waitUtil.waitForClickable(cssTutorial).click();
    }

    public void clickJavaTutorial() {

        waitUtil.waitForClickable(javaTutorial).click();
    }
}