package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PythonPage extends BasePage {

    public PythonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Python Intro']")
    private WebElement pythonIntro;

    @FindBy(xpath = "//a[normalize-space()='Python HOME']")
    private WebElement pythonHome;

    @FindBy(xpath = "//a[@title='SQL Tutorial']")
    private WebElement sqlTutorial;

    @FindBy(xpath = "//a[@title='HTML Tutorial']")
    private WebElement htmlTutorial;

    @FindBy(xpath = "//a[@title='CSS Tutorial']")
    private WebElement cssTutorial;

    public void clickPythonIntro() {

        waitUtil.waitForClickable(pythonIntro).click();
    }

    public void clickPythonHome() {

        waitUtil.waitForClickable(pythonHome).click();
    }

    public void clickSQLTutorial() {

        waitUtil.waitForClickable(sqlTutorial).click();
    }

    public void clickHTMLTutorial() {

        waitUtil.waitForClickable(htmlTutorial).click();
    }

    public void clickCSSTutorial() {

        waitUtil.waitForClickable(cssTutorial).click();
    }
}