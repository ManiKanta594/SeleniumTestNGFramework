package databaseTesting;

import java.sql.ResultSet;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

//import utilities.DatabaseUtil;

public class Test6DatabaseToUIValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        String name = "";
        String email = "";
        String phone = "";
        String address = "";

        try {

            ResultSet rs = DatabaseUtil.executeQuery(

                    "SELECT * FROM CUSTOMER WHERE NAME=?",

                    "David");

            if (rs.next()) {

                name = rs.getString("NAME");
                email = rs.getString("EMAIL");
                phone = rs.getString("PHONE");
                address = rs.getString("ADDRESS");
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        DatabaseUtil.closeConnection();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("textarea")).sendKeys(address);

        Assert.assertEquals(driver.findElement(By.id("name")).getAttribute("value"), name);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.id("phone")).getAttribute("value"), phone);
        Assert.assertEquals(driver.findElement(By.id("textarea")).getAttribute("value"), address);

        System.out.println("Database Data Successfully Loaded into UI");

        driver.quit();
    }
}