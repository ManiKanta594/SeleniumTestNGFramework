package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.HtmlPage;
import pages.JavaPage;
import pages.SqlPage;
import reports.ReportManager;

public class SqlToJavaNavigationTest extends BaseTest {

    @Test(
            priority = 8,
            groups = { "regression" },
            description = "Navigate from SQL to Java and HTML"
    )
    public void verifySqlToJavaNavigation() {

        ReportManager.logStep("User launches the browser");

        ReportManager.logStep("User opens W3Schools login page");

        pageObjectManager.getLoginPage()
                .login(config.getUsername(), config.getPassword());

        HomePage homePage = pageObjectManager.getHomePage();

        ReportManager.logStep("User opens Tutorials menu");

        homePage.clickTutorials();

        ReportManager.logStep("User navigates to SQL Tutorial");

        pageObjectManager.getTutorialsPage()
                .selectTutorial("SQL");

        SqlPage sqlPage = pageObjectManager.getSqlPage();

        ReportManager.logStep("User navigates through SQL pages");

        sqlPage.clickSQLIntro();
        sqlPage.clickSQLHome();

        ReportManager.logStep("User navigates to Java Tutorial from SQL");

        sqlPage.clickJavaTutorial();

        JavaPage javaPage = pageObjectManager.getJavaPage();

        ReportManager.logStep("User navigates through Java pages");

        javaPage.clickJavaIntro();
        javaPage.clickJavaHome();

        ReportManager.logStep("User navigates to HTML Tutorial from Java");

        javaPage.clickHTMLTutorial();

        HtmlPage htmlPage = pageObjectManager.getHtmlPage();

        ReportManager.logStep("User navigates through HTML pages");

        htmlPage.clickHTMLIntroduction();
        htmlPage.clickHTMLHome();

        ReportManager.logStep("User logs out successfully");

        homePage.logout();

        ReportManager.logPass(
                "SQL -> Java -> HTML Navigation completed successfully.");
    }
}