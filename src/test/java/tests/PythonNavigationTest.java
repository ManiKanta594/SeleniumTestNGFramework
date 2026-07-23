package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CssPage;
import pages.HomePage;
import pages.PythonPage;
import reports.ReportManager;

public class PythonNavigationTest extends BaseTest {

    @Test(
            priority = 9,
            groups = { "smoke" },
            description = "Navigate from Python directly to CSS"
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

        ReportManager.logStep("User navigates to CSS Tutorial from Python");

        pythonPage.clickCSSTutorial();

        CssPage cssPage = pageObjectManager.getCssPage();

        ReportManager.logStep("User navigates through CSS pages");

        cssPage.clickCSSIntroduction();
        cssPage.clickCSSHome();

        ReportManager.logStep("User logs out successfully");

        homePage.logout();

        ReportManager.logPass(
                "Python -> CSS Navigation completed successfully.");
    }
}