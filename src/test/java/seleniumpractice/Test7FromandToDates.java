package seleniumpractice;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Test7FromandToDates {
	
	//Practice Drop Downs

	@Test
	public void test1() throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement txtName =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        
        txtName.sendKeys("Mani");
        
        WebElement datepicker3 =
        		driver.findElement(By.id("start-date"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            datepicker3
        );
        
        WebElement startDate = driver.findElement(By.id("start-date"));
        WebElement endDate = driver.findElement(By.id("end-date"));

        startDate.sendKeys("01-07-2026");
        endDate.sendKeys("15-07-2026");
        
        driver.findElement(By.xpath("//button[@class='submit-btn']")).click();
       
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test7DatePicker3.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
