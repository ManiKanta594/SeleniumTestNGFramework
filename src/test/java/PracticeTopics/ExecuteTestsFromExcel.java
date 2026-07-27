package PracticeTopics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.ExcelDataProviderAllTests;
import utilities.ExcelUtil;

public class ExecuteTestsFromExcel {

    @Test(dataProvider = "RegistrationData",
            dataProviderClass = ExcelDataProviderAllTests.class)
    public void registrationTest(String tcId,
                                 String name,
                                 String email,
                                 String phone,
                                 String address) throws InterruptedException {

        WebDriver driver = null;

        String status = "FAIL";
        String remarks = "";
        String orderId = "";

        try {

        	

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://testautomationpractice.blogspot.com/");

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement txtName =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

            txtName.sendKeys(name);

            driver.findElement(By.id("email")).sendKeys(email);

            driver.findElement(By.id("phone")).sendKeys(phone);

            driver.findElement(By.id("textarea")).sendKeys(address);

            Thread.sleep(2000);

            // Generate execution details

            orderId = "ORD" + (100000 + (int) (Math.random() * 900000));

            status = "PASS";

            remarks = "Execution Successful";

        }

        catch (Exception e) {

            status = "FAIL";

            remarks = e.getClass().getSimpleName() + " : " + e.getMessage();

            Assert.fail(e.getMessage());
        }

        finally {

            ExcelUtil.updateCell(tcId, "Status", status);

            ExcelUtil.updateCell(tcId, "Remarks", remarks);

            ExcelUtil.updateCell(tcId, "Order ID", orderId);

            System.out.println("Excel Updated Successfully for : " + tcId);

            if (driver != null) {
            	Thread.sleep(3000);

                driver.quit();
            }
        }
    }
}