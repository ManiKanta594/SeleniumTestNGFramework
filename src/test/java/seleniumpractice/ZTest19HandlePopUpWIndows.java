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

public class ZTest19HandlePopUpWIndows {

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
        WebElement pop =
                driver.findElement(By.id("PopUp"));

        scrollToElement(pop);

        takeScreenshot("ZTest19_pop1");

        String parentWindow = driver.getWindowHandle();
        
        pop.click();
            
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));
        

        for (String window : driver.getWindowHandles()) {

            if (!window.equals(parentWindow)) {

                // Switch to child window
                driver.switchTo().window(window);

                // Maximize
                driver.manage().window().maximize();

                // Print Title
                System.out.println(driver.getTitle());

              

                // Perform actions based on the window
                if (driver.getTitle().contains("Selenium")) {

                    System.out.println("Performing Selenium Actions");

                    driver.findElement(By.xpath("//span[text()='Downloads']")).click();

                    takeScreenshot("Ztest19_2Selenium");

                } else if (driver.getTitle().contains("Playwright")) {

                    System.out.println("Performing Playwright Actions");

                    driver.findElement(By.xpath("//a[@class=\'getStarted_Sjon\']")).click();

                    takeScreenshot("ZTest19_3Playwright");
                }

                // Close current child window
                driver.close();
            }
        }

        // Switch back to Parent
        driver.switchTo().window(parentWindow);

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());       
        
        Thread.sleep(5000);

        driver.quit();
    

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
    

}