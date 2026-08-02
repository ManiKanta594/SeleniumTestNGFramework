package databaseTesting;

import java.sql.ResultSet;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

//import utilities.DatabaseUtil;

public class Test5DatabaseUIMerge {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {

            // ==========================
            // Open Application
            // ==========================
            driver.get("https://testautomationpractice.blogspot.com/");

            // ==========================
            // Test Data
            // ==========================
            String name = "Manikanta";
            String email = "mani@gmail.com";
            String phone = "9876543210";
            String address = "Hyderabad";

            // ==========================
            // Enter Details in UI
            // ==========================
            driver.findElement(By.id("name")).sendKeys(name);

            driver.findElement(By.id("email")).sendKeys(email);

            driver.findElement(By.id("phone")).sendKeys(phone);

            driver.findElement(By.id("textarea")).sendKeys(address);

            System.out.println("UI Data Entered Successfully");

            // ==========================
            // Connect to Database
            // ==========================
            DatabaseUtil.connect();

            // ==========================
            // Insert into Database
            // ==========================
            int rows = DatabaseUtil.executeUpdate(

                    "INSERT INTO CUSTOMER(NAME,EMAIL,PHONE,ADDRESS) VALUES(?,?,?,?)",

                    name,
                    email,
                    phone,
                    address);

            System.out.println("Rows Inserted : " + rows);

            // ==========================
            // Read Data from Database
            // ==========================
            ResultSet rs = DatabaseUtil.executeQuery(

                    "SELECT * FROM CUSTOMER WHERE EMAIL=?",

                    email);

            String dbName = "";
            String dbEmail = "";
            String dbPhone = "";
            String dbAddress = "";

            if (rs.next()) {

                dbName = rs.getString("NAME");
                dbEmail = rs.getString("EMAIL");
                dbPhone = rs.getString("PHONE");
                dbAddress = rs.getString("ADDRESS");

            }

            // ==========================
            // Compare UI vs Database
            // ==========================
            Assert.assertEquals(dbName, name);
            Assert.assertEquals(dbEmail, email);
            Assert.assertEquals(dbPhone, phone);
            Assert.assertEquals(dbAddress, address);

            System.out.println("================================");
            System.out.println("UI Data Matches Database Data");
            System.out.println("TEST PASSED");
            System.out.println("================================");

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            DatabaseUtil.closeConnection();

            driver.quit();

        }

    }

}