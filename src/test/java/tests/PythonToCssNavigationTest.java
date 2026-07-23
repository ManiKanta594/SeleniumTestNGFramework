package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.PythonPage;
import pages.SqlPage;
import pages.CssPage;
import reports.ReportManager;

public class PythonToCssNavigationTest extends BaseTest {

    @Test(
            priority = 2,
            groups = { "regression" },
            description = "Complete Python -> SQL -> CSS Navigation"
    )
    public void verifyPythonToCssNavigation() {

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

        ReportManager.logStep("User navigates to SQL Tutorial");

        pythonPage.clickSQLTutorial();

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
                "Python -> SQL -> CSS Navigation completed successfully.");
    }
}