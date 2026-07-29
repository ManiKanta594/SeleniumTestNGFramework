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

public class ZTest11PaginationTable {
	
	//Practice Paagination 

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
        
        WebElement pagination =
                driver.findElement(By.id("productTable"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            pagination
        );

        //Count Number of Pages
        List<WebElement> pages =
        		driver.findElements(By.xpath("//ul[@id='pagination']//li"));

        		System.out.println(pages.size());
        		int totalPages = pages.size();
                       	
        		//Loop Through Every Page
        		for(int p=1; p<=totalPages; p++)
        		{
        		    driver.findElement(
        		        By.xpath("//ul[@id='pagination']//li["+p+"]"))
        		        .click();

        		    System.out.println("Page : "+p);

        		    List<WebElement> rows =
        		    driver.findElements(
        		        By.xpath("//table[@id='productTable']//tbody/tr"));

        		    for(WebElement row : rows)
        		    {
        		        System.out.println(row.getText());
        		    }
        		}
        		
        //Select CheckBoxes	
        		List<String> productsToSelect =
        		        List.of("Router", "Soundbar", "Laptop");

        		for (int p = 1; p <= totalPages; p++) {

        		    // Click page
        		    driver.findElement(
        		            By.xpath("//ul[@id='pagination']/li[" + p + "]"))
        		            .click();

        		    // Get rows on current page
        		    int rows = driver.findElements(
        		            By.xpath("//table[@id='productTable']/tbody/tr"))
        		            .size();

        		    for (int r = 1; r <= rows; r++) {

        		        String product = driver.findElement(
        		                By.xpath("//table[@id='productTable']/tbody/tr[" + r + "]/td[2]"))
        		                .getText();

        		        if (productsToSelect.contains(product)) {

        		            driver.findElement(
        		                    By.xpath("//table[@id='productTable']/tbody/tr[" + r + "]/td[4]/input"))
        		                    .click();

        		            System.out.println(product + " selected");
        		        }
        		    }
        		}
        		
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/ZTest11Pagination.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
