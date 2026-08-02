package seleniumpracticeset2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import org.testng.asserts.SoftAssert;

import org.testng.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
public class ZTest16UIExcelVerification {
	
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
        					
        
        				//Read Excel
        					String filePath =
        					        System.getProperty("user.home")
        					        + "\\Downloads\\Books.xlsx";

        					FileInputStream fis = new FileInputStream(filePath);

        					Workbook workbook = new XSSFWorkbook(fis);

        					Sheet sheet = workbook.getSheetAt(0);  							
        					
        					
        					//Print Excel Data
        					System.out.println("================================");
        					System.out.println("Reading Excel Data");
        					System.out.println("================================");

        					int excelRows = sheet.getLastRowNum();

        					for(int r = 1; r <= excelRows; r++)
        					{
        					    Row row = sheet.getRow(r);

        					    for(int c = 0; c < 4; c++)
        					    {
        					        System.out.print(row.getCell(c).toString() + "  ");
        					    }

        					    System.out.println();
        					}
        					
        		
        					//==============================================================
        					// Compare UI Table Data with Downloaded Excel Data
        					//==============================================================

        					System.out.println("================================");
        					System.out.println("Comparing UI Data with Excel Data");
        					System.out.println("================================");

        					// Flag to track whether all UI values match Excel values
        					boolean status = true;

        					// Loop through all UI table rows
        					// UI starts from row index 2 because row 1 is the header
        					for(int r = 2; r <= rowsize; r++)
        					{
        					    // Read corresponding Excel row
        					    // Excel starts from row index 1 because row 0 contains headers
        					    Row excelRow = sheet.getRow(r - 1);

        					    // Loop through all columns
        					    for(int c = 1; c <= cols; c++)
        					    {
        					        //==========================================================
        					        // Read UI Cell Value
        					        //==========================================================
        					        String uiValue = driver.findElement(
        					                By.xpath("//table[@name='BookTable']//tr[" + r + "]/td[" + c + "]"))
        					                .getText();

        					        //==========================================================
        					        // Read Excel Cell
        					        //==========================================================
        					        Cell cell = excelRow.getCell(c - 1);

        					        String excelValue = "";

        					        //==========================================================
        					        // Check Cell Data Type
        					        //
        					        // Numeric values in Excel are stored as Double.
        					        // Example:
        					        // UI     : 300
        					        // Excel  : 300.0
        					        //
        					        // Convert numeric values to integer string for comparison.
        					        //==========================================================
        					        if(cell.getCellType() == CellType.NUMERIC)
        					        {
        					            excelValue =
        					                    String.valueOf((int) cell.getNumericCellValue());
        					        }
        					        else
        					        {
        					            // Read String values
        					            excelValue =
        					                    cell.getStringCellValue();
        					        }

        					        //==========================================================
        					        // Compare UI Value with Excel Value
        					        //==========================================================
        					        System.out.println("----------------------------------------");
        					        System.out.println("Row : " + (r - 1));
        					        System.out.println("Column : " + c);

        					        System.out.println("UI Value    : " + uiValue);
        					        System.out.println("Excel Value : " + excelValue);

        					        // Assertion
        					        Assert.assertEquals(
        					                uiValue,
        					                excelValue,
        					                "Mismatch Found at Row " + (r - 1) + " Column " + c);

        					        System.out.println("Verification : PASS");
        					    }
        					}

        					//==============================================================
        					// Print Final Verification Result
        					//==============================================================

        					System.out.println("================================");

        					if(status)
        					{
        					    System.out.println("UI DATA MATCHES EXCEL DATA");
        					}
        					else
        					{
        					    System.out.println("DATA MISMATCH FOUND");
        					}

        					System.out.println("================================");

        					//==============================================================
        					// Close Workbook and File Stream
        					//
        					// Always close resources after use to avoid memory leaks.
        					//==============================================================
        					workbook.close();
        					fis.close();				
     
        					
        	
   TakesScreenshot ts = (TakesScreenshot) driver;

   File source = ts.getScreenshotAs(OutputType.FILE);

   File destination = new File("./Screenshots/Test9StaticTable.png");

   FileUtils.copyFile(source, destination);
   
   
        Thread.sleep(5000);
  
        driver.quit();
	}
}



