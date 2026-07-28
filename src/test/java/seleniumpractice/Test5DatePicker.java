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


public class Test5DatePicker {
	
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
        
        WebElement datepicker1 =
        		driver.findElement(By.id("datepicker"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            datepicker1
        );
        
        String targetDay = "15";
        String targetMonth = "December";
        String targetYear = "2027";
        
        datepicker1.click();
        
        String monthYear =
        		driver.findElement(By.className("ui-datepicker-title")).getText();
        

        		System.out.println(monthYear);//OP July 2026 we can split to find present month and year
        		
        		String currentMonth = monthYear.split(" ")[0];
        		String currentYear = monthYear.split(" ")[1];
        		
        		DateTimeFormatter formatter =
        		        DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

        		YearMonth target =
        		        YearMonth.parse(targetMonth + " " + targetYear, formatter);
        		
        		while (true) {

        		    String displayed =
        		            driver.findElement(By.className("ui-datepicker-title")).getText();

        		    YearMonth current =
        		            YearMonth.parse(displayed, formatter);

        		    if (current.equals(target)) {
        		        break;
        		    }

        		    if (current.isBefore(target)) {

        		        driver.findElement(
        		                By.xpath("//a[@data-handler='next']")
        		        ).click();

        		    } else {

        		        driver.findElement(
        		                By.xpath("//a[@data-handler='prev']")
        		        ).click();
        		    }
        		}
        		
       /*
        * If your target date is:

		December 2027

		click Next until you reach it.

		If your target date is:

		January 2024

		click Previous until you reach it.		
        */
        	
        		 // Select Day
                List<WebElement> dates =
                        driver.findElements(
                                By.xpath("//td[@data-handler='selectDay']/a"));

                for (WebElement date : dates) {

                    if (date.getText().equals(targetDay)) {

                        date.click();
                        break;
                    }
                }
        		
       
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test5DatePicker1.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
