package seleniumpracticeset2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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


public class Test5ScrollingDropDown {

    WebDriver driver;

    @Test
    public void test1() throws IOException, InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement comboBox =
                driver.findElement(By.id("comboBox"));

        scrollToElement(comboBox);

        comboBox.click();
        
        List<WebElement> options =
                driver.findElements(By.xpath("//div[@id='dropdown']/div"));
        
        for (WebElement option : options) {

            if (option.getText().equals("Item 25")) {

                option.click();

                break;
            }
        }
        
        takeScreenshot("Test5_ScrollingDropDown");
        Thread.sleep(5000);
        driver.quit();
        
        /*IF Item is not Visible we can use Scrolling Option
         * WebElement item50 =
        driver.findElement(By.xpath("//div[text()='Item 50']"));

JavascriptExecutor js =
        (JavascriptExecutor) driver;

js.executeScript(
        "arguments[0].scrollIntoView(true);",
        item50);

item50.click();
         */
                       
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