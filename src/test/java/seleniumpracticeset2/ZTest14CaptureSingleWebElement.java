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


public class ZTest14CaptureSingleWebElement {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
                           
     
        WebElement searchBox =
        		driver.findElement(By.cssSelector("input.wikipedia-search-input"));
        
        searchBox.sendKeys("Selenium");
        

        		File src = searchBox.getScreenshotAs(OutputType.FILE);

        		File dest = new File("./ScreenshotsSet2/WikipediaSearchBox.png");

        		FileUtils.copyFile(src, dest);

        		System.out.println("Element Screenshot Captured");
       
       
       Thread.sleep(5000);
       
        driver.quit();
              
    }

}
