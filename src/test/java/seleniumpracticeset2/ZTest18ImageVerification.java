package seleniumpracticeset2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZTest18ImageVerification {

    @Test
    public void verifyWikipediaLogo() {

        //=========================================================
        // Launch Browser
        //=========================================================

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        //=========================================================
        // Open Application
        //=========================================================

        driver.get("https://testautomationpractice.blogspot.com/");

        //=========================================================
        // Locate Image Element
        //=========================================================

        WebElement wikiLogo =
                driver.findElement(
                        By.cssSelector("img.wikipedia-icon"));

        System.out.println("==========================================");
        System.out.println("IMAGE VERIFICATION STARTED");
        System.out.println("==========================================");

        //=========================================================
        // STEP 1
        // Verify Image is Displayed on UI
        //=========================================================

        System.out.println();
        System.out.println("Step 1 : Verify Image Displayed");

        Assert.assertTrue(
                wikiLogo.isDisplayed(),
                "Wikipedia Logo is NOT displayed.");

        System.out.println("PASS : Image is displayed.");

        //=========================================================
        // STEP 2
        // Verify Image is Properly Loaded (Not Broken)
        //=========================================================

        System.out.println();
        System.out.println("Step 2 : Verify Image Loaded");

        Boolean imageLoaded =
                (Boolean)((JavascriptExecutor)driver)
                .executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                wikiLogo);

        Assert.assertTrue(
                imageLoaded,
                "Image is broken.");

        System.out.println("PASS : Image loaded successfully.");

        //=========================================================
        // STEP 3
        // Verify Correct Image using SRC Attribute
        //=========================================================

        System.out.println();
        System.out.println("Step 3 : Verify Image Source");

        String imageSource =
                wikiLogo.getAttribute("src");

        System.out.println("Image Source :");
        System.out.println(imageSource);

        Assert.assertTrue(
                imageSource.contains("wikipedia"),
                "Incorrect image displayed.");

        System.out.println("PASS : Correct image is displayed.");

        //=========================================================
        // Print Image Width & Height
        //=========================================================

        System.out.println();
        System.out.println("Image Width  : "
                + wikiLogo.getSize().getWidth());

        System.out.println("Image Height : "
                + wikiLogo.getSize().getHeight());

        //=========================================================
        // Final Result
        //=========================================================

        System.out.println();
        System.out.println("==========================================");
        System.out.println("ALL IMAGE VALIDATIONS PASSED");
        System.out.println("==========================================");

        driver.quit();

    }

}