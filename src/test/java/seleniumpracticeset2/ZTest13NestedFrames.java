package seleniumpracticeset2;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class ZTest13NestedFrames {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://ui.vision/demo/webtest/frames/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
                           
     // Switch to Frame 3
        WebElement frame3 =
        driver.findElement(By.xpath("//frame[@src='frame_3.html']"));

        driver.switchTo().frame(frame3);

        // Switch to Google Form
        WebElement googleFrame =
        driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame(googleFrame);

        // Now Selenium is inside Google Form
        
        WebElement radio = driver.findElement(
                By.xpath("//div[@role='radio' and @aria-label='I am a human']")
        );
        scrollToElement(radio);

        radio.click();
        
        takeScreenshot("ZTest13_NestedFrame");
        //switch to Parent Frame
        
        driver.switchTo().parentFrame();
        
        //Switch to default Content
        driver.switchTo().defaultContent();
        
        driver.switchTo().parentFrame();
        
        
       
        
     
       
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