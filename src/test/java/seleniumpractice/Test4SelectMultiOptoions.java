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

public class Test4SelectMultiOptoions {
	
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
        
        WebElement multioption =
                driver.findElement(By.cssSelector("select.form-control[id=colors]"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            multioption
        );
        
        Select select = new Select(multioption);

        List<WebElement> options = select.getOptions();
        List<String> colorsToSelect = List.of("Red", "White", "Yellow");

        for (WebElement option : options) {

            for (String color : colorsToSelect) {

                if (option.getText().equalsIgnoreCase(color)) {
                    option.click();
                    break; // No need to check the remaining colors
                }
            }
        }
        		
        /*
         * color.equalsIgnoreCase("Red")
		|| color.equalsIgnoreCase("White")
		|| color.equalsIgnoreCase("Yellow")
         */
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test4MultiOption.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
