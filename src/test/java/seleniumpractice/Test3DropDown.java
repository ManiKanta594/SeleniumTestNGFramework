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

public class Test3DropDown {
	
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
        
        WebElement dropdown =
                driver.findElement(By.cssSelector("select.form-control[id=country]"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            dropdown
        );

        Select select = new Select(dropdown);

        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {

            System.out.println(option.getText());
            
            String color = option.getText();


            if (option.getText().equalsIgnoreCase("India")) {

                option.click();

                break;
            }
        }
       
        /*Other Options
         * select.selectByVisibleText("India");
         * select.selectByValue("india");
         * select.selectByIndex(9);
         */

   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test3DropDown.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
