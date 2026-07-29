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

public class ZTest17TabsHandle {

    WebDriver driver;

    @Test
    public void test1() throws InterruptedException, IOException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate New Tab Button
        WebElement tab =
                driver.findElement(By.xpath("//button[text()='New Tab']"));

        // Scroll and Screenshot
        scrollToElement(tab);
        takeScreenshot("ZTest17_Tab1");

        // Click New Tab
        tab.click();

        // Store Parent Window
        String parentWindow = driver.getWindowHandle();

        // Wait for Child Window
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windows = driver.getWindowHandles();

        // Switch to Child Window
        for (String window : windows) {

            if (!window.equals(parentWindow)) {

                driver.switchTo().window(window);

                break;
            }
        }

        // Wait for ETL Link
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='ETL Testing videos']")));

        WebElement etl =
                driver.findElement(By.xpath("//a[text()='ETL Testing videos']"));

        // Scroll and Screenshot
        scrollToElement(etl);
        takeScreenshot("ZTest17_ETLTab2");

        // Click ETL Link
        etl.click();

        /*More Than 2
         * driver.findElement(
                By.xpath("//a[text()='ETL Testing videos']"))
                .click();

        // Wait until ETL tab opens
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));

        String etlWindow = "";

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            if (!window.equals(parentWindow)
                    && !window.equals(childWindow)) {

                etlWindow = window;
                 driver.switchTo().window(etlWindow);

                break;
            }
        }

        System.out.println("ETL Title : " + driver.getTitle());

        // Close ETL Tab
        driver.close();

        // Switch back to Child Window
        driver.switchTo().window(childWindow);

        // Close Child Window
        driver.close();
         */
        // Close Current Tab
        driver.close();

        // Switch Back
        driver.switchTo().window(parentWindow);

        System.out.println(driver.getTitle());

        Thread.sleep(3000);

        driver.quit();
        
    }

    //we can declare methods only completion of one method inside method we can't create another one
    private void scrollToElement(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);
    }

    private void takeScreenshot(String fileName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File("./Screenshots/" + fileName + ".png");

        FileUtils.copyFile(source, destination);

        System.out.println(fileName + " Screenshot Saved");
    }
}