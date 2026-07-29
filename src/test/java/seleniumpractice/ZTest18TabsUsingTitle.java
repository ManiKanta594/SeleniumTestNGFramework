package seleniumpractice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ZTest18TabsUsingTitle {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll to New Tab button
        WebElement tab =
                driver.findElement(By.xpath("//button[text()='New Tab']"));

        scrollToElement(tab);

        takeScreenshot("ZTest18_HomePage1");

        // Click New Tab
        tab.click();

        // Wait for second window
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch based on Title
        switchToWindowByTitle("SDET-QA Blog");

        System.out.println("Current Title : " + driver.getTitle());

        // Locate ETL Link
        WebElement etl =
                driver.findElement(By.xpath("//a[text()='ETL Testing videos']"));

        scrollToElement(etl);
        
        etl.click();

        takeScreenshot("ZTest18_NewTab2");
        
        System.out.println("Current Title : " + driver.getTitle());
        
        closeWindowByTitle("SDET");
        Thread.sleep(5000);

        driver.quit();
    }

    // ===========================
    // Switch Window By Title
    // ===========================
    private void switchToWindowByTitle(String expectedTitle) {

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            driver.switchTo().window(window);

            System.out.println(driver.getTitle());

            if (driver.getTitle().contains(expectedTitle)) {

                System.out.println("Switched To : " + driver.getTitle());

                break;
            }
        }
    }

    // ===========================
    // Scroll Method
    // ===========================
    private void scrollToElement(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }

    // ===========================
    // Screenshot Method
    // ===========================
    private void takeScreenshot(String fileName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File("./Screenshots/" + fileName + ".png");

        FileUtils.copyFile(source, destination);

        System.out.println(fileName + " Screenshot Saved");
    }
    
    //Close Title Based on The Title
    private void closeWindowByTitle(String expectedTitle) {

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            driver.switchTo().window(window);

            if (driver.getTitle().contains(expectedTitle)) {

                System.out.println("Closing : " + driver.getTitle());

                driver.close();

                break;
            }
        }
    }
}