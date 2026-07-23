package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HtmlPage extends BasePage {

    public HtmlPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='HTML Introduction']")
    private WebElement htmlIntroduction;

    @FindBy(xpath = "//a[normalize-space()='HTML HOME']")
    private WebElement htmlHome;

    @FindBy(xpath = "//a[@title='SQL Tutorial']")
    private WebElement sqlTutorial;

    @FindBy(xpath = "//a[@title='CSS Tutorial']")
    private WebElement cssTutorial;

    @FindBy(xpath = "//button[contains(@class,'profile')]")
    private WebElement profileIcon;

    @FindBy(css = "a.logout")
    private WebElement logout;
    
    
    public void clickHTMLIntroduction() {

        waitUtil.waitForClickable(htmlIntroduction).click();
    }

    public void clickHTMLHome() {

        waitUtil.waitForClickable(htmlHome).click();
    }

    public void clickSQLTutorial() {

        waitUtil.waitForClickable(sqlTutorial).click();
    }

    public void clickCSSTutorial() {

        waitUtil.waitForClickable(cssTutorial).click();
    }
    
    public void logout() {

        waitUtil.waitForClickable(profileIcon).click();

        waitUtil.waitForVisibility(logout);

        jsUtil.scrollIntoView(logout);

        jsUtil.clickElement(logout);
    }
}