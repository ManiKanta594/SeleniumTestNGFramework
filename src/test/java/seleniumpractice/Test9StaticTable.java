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

public class Test9StaticTable {
	
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
        
        WebElement statictable =
                driver.findElement(By.cssSelector("table[name=\"BookTable\"]"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            statictable
        );

        //Print Complete Table
        List<WebElement> rows =
        		driver.findElements(By.xpath("//table[@name='BookTable']//tr"));

        		for(WebElement row : rows)
        		{
        		    System.out.println(row.getText());
        		}
       		
        		
        //Row Size and Column Size 
        	System.out.println(rows.size()-1);
        	List<WebElement> columns =
        			driver.findElements(By.xpath("//table[@name='BookTable']//th"));

        			System.out.println(columns.size());
        			
        //Read Specifi Cell It is applicable for only Staic Table
        			String data =
        					driver.findElement(
        					By.xpath("//table[@name='BookTable']//tr[3]/td[2]"))
        					.getText();

        					System.out.println(data);
        			
        			
        //Read Entire Table
        			int rowsize =
        					driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();

        					int cols =
        					driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();

        					for(int r=2; r<=rowsize; r++)
        					{
        					    for(int c=1; c<=cols; c++)
        					    {
        					        String value =
        					driver.findElement(
        					By.xpath("//table[@name='BookTable']//tr["+r+"]/td["+c+"]"))
        					.getText();

        					        System.out.print(value+"  ");
        					    }

        					    System.out.println();
        					}
        					
        					
        	/*Get Price of Learn Java OR same will applicable to click Links also
        					//driver.findElement(
        					By.xpath("//table[@id='table']//tr["+r+"]/td[3]/button"))
        					.click(;
        					*/
      
        							for(int r=2; r<=rowsize; r++)
        							{
        							    String book =
        							driver.findElement(
        							By.xpath("//table[@name='BookTable']//tr["+r+"]/td[1]"))
        							.getText();

        							    if(book.equals("Learn Java"))
        							    {
        							        String price =
        							driver.findElement(
        							By.xpath("//table[@name='BookTable']//tr["+r+"]/td[4]"))
        							.getText();

        							        System.out.println(price);

        							        break;
        							    }
        							}
        	
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test9StaticTable.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}
