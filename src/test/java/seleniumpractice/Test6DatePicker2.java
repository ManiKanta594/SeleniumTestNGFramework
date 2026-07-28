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


public class Test6DatePicker2 {
	
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
        
        WebElement datepicker2 =
        		driver.findElement(By.id("txtDate"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            datepicker2
        );
        
        String targetDay = "15";
        String targetMonth = "Jun";
        String targetYear = "2024";
        
        datepicker2.click();
        
        WebElement month =
        		driver.findElement(By.className("ui-datepicker-month"));
        
        Select monthDropdown = new Select(month);
        
       //if month provides as Fullname like January it accepts both Jan and January
        try {
            monthDropdown.selectByVisibleText(targetMonth);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            monthDropdown.selectByVisibleText(targetMonth.substring(0, 3));
        }
        WebElement year =
        		driver.findElement(By.className("ui-datepicker-year"));
        
        Select yearDropdown = new Select(year);
        yearDropdown.selectByVisibleText(targetYear);
        
        driver.findElement(
        	    By.xpath("//table[contains(@class,'ui-datepicker-calendar')]//a[text()='" + targetDay + "']")
        	).click();
        
       
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test6DatePicker2.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
