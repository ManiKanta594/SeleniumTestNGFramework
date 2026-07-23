package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CssPage;
import pages.HomePage;
import pages.JavaPage;
import pages.SqlPage;
import reports.ReportManager;

public class JavaNavigationTest extends BaseTest {

    @Test(
            priority = 3,
            groups = { "regression" },
            description = "Complete Java -> SQL -> CSS Navigation"
    )
    public void verifyJavaNavigation() {

        ReportManager.logStep("User launches the browser");

        ReportManager.logStep("User opens W3Schools login page");

        pageObjectManager.getLoginPage()
                .login(config.getUsername(), config.getPassword());

        HomePage homePage = pageObjectManager.getHomePage();

        ReportManager.logStep("User opens Tutorials menu");

        homePage.clickTutorials();

        ReportManager.logStep("User navigates to Java Tutorial");

        pageObjectManager.getTutorialsPage()
                .selectTutorial("Java");

        JavaPage javaPage = pageObjectManager.getJavaPage();

        ReportManager.logStep("User navigates through Java pages");

        javaPage.clickJavaIntro();
        javaPage.clickJavaHome();

        ReportManager.logStep("User navigates to SQL Tutorial from Java");

        javaPage.clickSQLTutorial();

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
                "Java -> SQL -> CSS Navigation completed successfully.");
    }
}