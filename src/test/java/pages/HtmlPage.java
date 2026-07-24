package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ScreenshotUtil;

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

    	ScreenshotUtil.captureScreenshot("HtmlPage_Headless");
    	System.out.println(driver.manage().window().getSize());
        waitUtil.waitForVisibility(htmlIntroduction);

        jsUtil.scrollIntoView(htmlIntroduction);

        jsUtil.clickElement(htmlIntroduction);
    }

    public void clickHTMLHome() {

        waitUtil.waitForVisibility(htmlHome);

        jsUtil.scrollIntoView(htmlHome);

        jsUtil.clickElement(htmlHome);
    }

    public void clickSQLTutorial() {

        waitUtil.waitForVisibility(sqlTutorial);

        jsUtil.scrollIntoView(sqlTutorial);

        jsUtil.clickElement(sqlTutorial);
    }

    public void clickCSSTutorial() {

        waitUtil.waitForVisibility(cssTutorial);

        jsUtil.scrollIntoView(cssTutorial);

        jsUtil.clickElement(cssTutorial);
    }

    public void logout() {

        waitUtil.waitForClickable(profileIcon).click();

        waitUtil.waitForVisibility(logout);

        jsUtil.scrollIntoView(logout);

        jsUtil.clickElement(logout);
    }
}