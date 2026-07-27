package PracticeTopics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dataproviders.FormExcelDataProvider;
import utilities.ExcelUtil;

public class ZDataDrivenExcelUpdateAll {
	
		
		@Test(dataProvider = "RegistrationData",
			      dataProviderClass = FormExcelDataProvider.class)
			public void datadriven(int rowNumber,
			                       String name,
			                       String email,
			                       String phone,
			                       String address) throws InterruptedException {

			    WebDriver driver = new ChromeDriver();

			    driver.manage().window().maximize();
			    driver.get("https://testautomationpractice.blogspot.com/");

			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			    WebElement txtName = driver.findElement(By.id("name"));

			    wait.until(ExpectedConditions.visibilityOf(txtName));

			    txtName.sendKeys(name);

			    driver.findElement(By.id("email")).sendKeys(email);

			    driver.findElement(By.id("phone")).sendKeys(phone);

			    driver.findElement(By.id("textarea")).sendKeys(address);

			    Thread.sleep(2000);

			    // Generate random values
			    String status = "PASS";
			    String remarks = "Execution_" + System.currentTimeMillis();
			    String orderId = "ORD" + (100000 + (int)(Math.random() * 900000));

			    // Update the same Excel row
			    ExcelUtil.updateCell("Registration", rowNumber, "Status", status);
			    ExcelUtil.updateCell("Registration", rowNumber, "Remarks", remarks);
			    ExcelUtil.updateCell("Registration", rowNumber, "Order ID", orderId);

			    driver.quit();
			}
		
	

}
