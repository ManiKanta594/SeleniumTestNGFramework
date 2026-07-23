package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import reports.ReportManager;

public class LoginTest extends BaseTest {

    @Test(
            priority = 1,
            groups = {"smoke", "regression"},
            description = "Verify user login and logout"
    )
    public void verifyLogin() {

        ReportManager.logStep("User launches the browser");

        ReportManager.logStep("User opens W3Schools login page");

        ReportManager.logStep("User logs in using configured credentials");

        pageObjectManager.getLoginPage()
                .login(config.getUsername(), config.getPassword());

        HomePage homePage = pageObjectManager.getHomePage();

        // Verification using your original framework approach
        Assert.assertNotNull(homePage,
                "Home Page object is not created after login.");

        ReportManager.logStep("User logs out successfully");

        homePage.logout();

        ReportManager.logPass("Login Test Completed Successfully");
    }
}