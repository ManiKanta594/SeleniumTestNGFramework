package seleniumpractice;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Test2ChecBox {
	
	//Practice check boxes 

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
        
        WebElement sundayCheckbox =
                driver.findElement(By.id("wednesday"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            sundayCheckbox
        );

        if (!sundayCheckbox.isSelected()) {
            sundayCheckbox.click();
        }
        
   List<WebElement> checkboxes= driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
   
   // XPATH= input.form-check-input[type='checkbox']  
   for (WebElement checkbox : checkboxes) {
	   System.out.println(checkbox.getAttribute("value"));
	    String day = checkbox.getAttribute("value");

	   
	    if (day.equalsIgnoreCase("sunday")
	            || day.equalsIgnoreCase("monday")) {

	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
   }

   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test2Checkbox.png");

   FileUtils.copyFile(source, destination);
   
   
        //Thread.sleep(5000);
  
        driver.quit();
	}
}
