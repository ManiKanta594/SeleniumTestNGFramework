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


public class ZdataDrivenTestExcel {
	
	@Test(dataProvider = "RegistrationData",
		      dataProviderClass = FormExcelDataProvider.class)
		public void datadriven(String name,
		                       String email,
		                       String phone,
		                       String address) throws InterruptedException {

		    WebDriver driver = new ChromeDriver();

		    driver.manage().window().maximize();

		    driver.get("https://testautomationpractice.blogspot.com/");

		    WebDriverWait wait =
		            new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement txtName =
		            driver.findElement(By.id("name"));

		    wait.until(ExpectedConditions.visibilityOf(txtName));

		    txtName.sendKeys(name);

		    driver.findElement(By.id("email"))
		            .sendKeys(email);

		    driver.findElement(By.id("phone"))
		            .sendKeys(phone);

		    driver.findElement(By.id("textarea"))
		            .sendKeys(address);
		    Thread.sleep(2000);

		    driver.quit();
		}
}

	