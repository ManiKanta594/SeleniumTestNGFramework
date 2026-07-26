package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CssPage;
import pages.HomePage;
import pages.HtmlPage;
import reports.ReportManager;
import utilities.AllureUtil;

public class HtmlToCssNavigationTest2 extends BaseTest {

    @Test(
            priority = 7,
            groups = { "sanity" },
            description = "Navigate from HTML directly to CSS"
    )
    public void verifyHtmlToCssNavigation() {

        ReportManager.logStep("User launches the browser");

        ReportManager.logStep("User opens W3Schools login page");

        pageObjectManager.getLoginPage()
                .login(config.getUsername(), config.getPassword());

        HomePage homePage = pageObjectManager.getHomePage();

        ReportManager.logStep("User opens Tutorials menu");

        homePage.clickTutorials();

        ReportManager.logStep("User navigates to HTML Tutorial");

        pageObjectManager.getTutorialsPage()
                .selectTutorial("HTML");

        HtmlPage htmlPage = pageObjectManager.getHtmlPage();

        ReportManager.logStep("User navigates through HTML pages");

        htmlPage.clickHTMLIntroduction();
        htmlPage.clickHTMLHome();

        ReportManager.logStep("User navigates to CSS Tutorial from HTML");

        htmlPage.clickCSSTutorial();

        CssPage cssPage = pageObjectManager.getCssPage();

        ReportManager.logStep("User navigates through CSS pages");

        cssPage.clickCSSIntroduction();
        cssPage.clickCSSHome();

        // Attach directly to Allure
        AllureUtil.attachScreenshot();
        AllureUtil.attachText("Message", "Before failure");

        // Intentional failure
        Assert.fail("Intentional failure for screenshot testing");

        // This code will never execute because Assert.fail() throws an exception.
        // Keep it commented or remove it while testing.
        // homePage.logout();
        // ReportManager.logPass("HTML -> CSS Navigation completed successfully.");
    }
}