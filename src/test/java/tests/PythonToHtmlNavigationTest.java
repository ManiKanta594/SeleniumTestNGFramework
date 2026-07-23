package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CssPage;
import pages.HomePage;
import pages.HtmlPage;
import pages.PythonPage;
import pages.SqlPage;
import reports.ReportManager;

public class PythonToHtmlNavigationTest extends BaseTest {

    @Test(
            priority = 6,
            groups = { "regression" },
            description = "Complete Python -> HTML -> SQL -> CSS Navigation"
    )
    public void verifyPythonToHtmlNavigation() {

        ReportManager.logStep("User launches the browser");

        ReportManager.logStep("User opens W3Schools login page");

        pageObjectManager.getLoginPage()
                .login(config.getUsername(), config.getPassword());

        HomePage homePage = pageObjectManager.getHomePage();

        ReportManager.logStep("User opens Tutorials menu");

        homePage.clickTutorials();

        ReportManager.logStep("User navigates to Python Tutorial");

        pageObjectManager.getTutorialsPage()
                .selectTutorial("Python");

        PythonPage pythonPage = pageObjectManager.getPythonPage();

        ReportManager.logStep("User navigates through Python pages");

        pythonPage.clickPythonIntro();
        pythonPage.clickPythonHome();

        ReportManager.logStep("User navigates to HTML Tutorial from Python");

        pythonPage.clickHTMLTutorial();

        HtmlPage htmlPage = pageObjectManager.getHtmlPage();

        ReportManager.logStep("User navigates through HTML pages");

        htmlPage.clickHTMLIntroduction();
        htmlPage.clickHTMLHome();

        ReportManager.logStep("User navigates to SQL Tutorial from HTML");

        htmlPage.clickSQLTutorial();

        SqlPage sqlPage = pageObjectManager.getSqlPage();

        ReportManager.logStep("User navigates through SQL pages");

        sqlPage.clickSQLIntro();
        sqlPage.clickSQLHome();

        ReportManager.logStep("User navigates to CSS Tutorial");

        sqlPage.clickCSSTutorial();

        CssPage cssPage = pageObjectManager.getCssPage();

        ReportManager.logStep("User navigates through CSS pages");

        cssPage.clickCSSIntroduction();
        cssPage.clickCSSHome();

        ReportManager.logStep("User logs out successfully");

        homePage.logout();

        ReportManager.logPass(
                "Python -> HTML -> SQL -> CSS Navigation completed successfully.");
    }
}