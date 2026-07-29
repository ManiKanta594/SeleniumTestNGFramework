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

public class ZTest10DynamicTable {
	
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
        
        WebElement dynamictable =
                driver.findElement(By.id("taskTable"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            dynamictable
        );

        
        //Get CPU Usage of Chrome
        int rows =
        		driver.findElements(By.xpath("//table[@id='taskTable']/tbody/tr")).size();

        		for(int r=1;r<=rows;r++)
        		{
        		    String process =
        		driver.findElement(
        		By.xpath("//table[@id='taskTable']/tbody/tr["+r+"]/td[1]"))
        		.getText();

        		    if(process.equals("Chrome"))
        		    {
        		        String cpu =
        		driver.findElement(
        		By.xpath("//table[@id='taskTable']/tbody/tr["+r+"]/td[2]"))
        		.getText();

        		        System.out.println(cpu);

        		        break;
        		    }
        		}
                	
        		//Dynamic XPath (Recommended)

        		//Instead of using row numbers, locate the required cell based on the process name.
        		
        		String cpu = driver.findElement(
        			    By.xpath("//table[@id='taskTable']//td[text()='Chrome']/following-sibling::td[1]")
        			).getText();

        			System.out.println(cpu);
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/ZTest10dynamicTable.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
