package seleniumpractice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test1 {
	
	//Practice Text fields and Radio Buttons

	@Test
	public void test1() throws InterruptedException {
		
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

        driver.findElement(By.id("email")).sendKeys("Mani123@gmail.com");

        driver.findElement(By.id("phone")).sendKeys("765435690");

        driver.findElement(By.id("textarea")).sendKeys("Vizag");

        
        driver.findElement(By.id("male")).click();
        
        Thread.sleep(5000);
        
        driver.quit();
	}
}
