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


public class ZTest11DownloadFiles {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
                            
      

       WebElement downloadfiles =driver.findElement(By.xpath("//a[text()='Download Files']"));
       
       scrollToElement(downloadfiles);
       
       downloadfiles.click();
       
       String downloadPath = "C:\\Users\\ASUS\\Downloads\\info.txt";

       File file = new File(downloadPath);

       // Delete old file
       if (file.exists()) {

           file.delete();

           System.out.println("Old file deleted.");
       }

       // Generate new file
       driver.findElement(By.id("inputText"))
             .sendKeys("Hello Selenium");

       driver.findElement(By.id("generateTxt"))
             .click();

       driver.findElement(By.id("txtDownloadLink"))
             .click();

       // Wait for download
       int count = 0;

       while (!file.exists() && count < 10) {

           Thread.sleep(1000);

           count++;
       }

       // Verify
       if (file.exists()) {

           System.out.println("Download Successful");

       } else {

           System.out.println("Download Failed");
       }
       
       takeScreenshot("ZTest12_DownloadFiles");
       
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