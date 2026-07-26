package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CssPage;
import pages.HomePage;
import pages.HtmlPage;
import reports.ReportManager;

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

        // Intentional failure for testing automatic Allure screenshot capture
        org.testng.Assert.fail("Intentional failure for automatic Allure screenshot testing");

        // homePage.logout();
        // ReportManager.logPass("HTML -> CSS Navigation completed successfully.");
    }
}