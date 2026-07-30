package seleniumpracticeset2;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test1DoubleClick {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement field1 = driver.findElement(By.id("field1"));
        WebElement field2 = driver.findElement(By.id("field2"));
        WebElement copyBtn = driver.findElement(By.xpath("//button[text()='Copy Text']"));

        scrollToElement(copyBtn);

        Actions actions = new Actions(driver);

        // Double click
        actions.doubleClick(copyBtn).perform();

        // Verify
        String text1 = field1.getAttribute("value");
        String text2 = field2.getAttribute("value");

        System.out.println(text1);
        System.out.println(text2);

        if (text1.equals(text2)) {
            System.out.println("Text Copied Successfully");
        } else {
            System.out.println("Text Copy Failed");
        }

        takeScreenshot("Test1_DoubleClick");
        
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

        File destination = new File("./ScreenshotsSet2/" + fileName + ".png");

        FileUtils.copyFile(source, destination);

        System.out.println(fileName + " Screenshot Saved");
    }
    

}