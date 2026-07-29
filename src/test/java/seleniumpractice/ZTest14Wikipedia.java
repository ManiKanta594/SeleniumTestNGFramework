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

public class ZTest14Wikipedia {
	
	//Practice searchBar 

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
        
        WebElement search =
                driver.findElement(By.id("Wikipedia1_wikipedia-search-input"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            search
        );  
        		
        search.sendKeys("W3schools");
        
        WebElement searchicon=driver.findElement(By.cssSelector("input.wikipedia-search-button"));
        searchicon.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='wikipedia-search-result-link']//a")));
        
        
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/ZTest14w3wikipedia.png");

   FileUtils.copyFile(source, destination);
   
   List<WebElement> Results= driver.findElements(By.xpath(("//div[@id='wikipedia-search-result-link']//a")));
   
   for (WebElement result :Results) {
   	if(result.getText().equalsIgnoreCase("W3Schools")){
   		result.click();
   		break;
   	}
   	
   }
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
