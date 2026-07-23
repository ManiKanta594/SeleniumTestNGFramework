package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPopUp {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.w3schools.com/");

        driver.findElement(By.id("tnb-login-btn")).click();

        driver.findElement(By.id("tnb-login-dropdown-email"))
                .sendKeys("mandalamanikanta123@gmail.com");

        driver.findElement(By.id("tnb-login-dropdown-password"))
                .sendKeys("123456@Mani");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        TestPopUp popup = new TestPopUp();

        popup.closeTrialPopupIfPresent(driver);

        // Continue execution
        driver.findElement(By.xpath("//a[@data-tnb-nav-id='tutorials']")).click();

        // driver.quit();
    }

    public void closeTrialPopupIfPresent(WebDriver driver) {

        List<WebElement> popupTitle = driver.findElements(
                By.xpath("//p[normalize-space()='Your trial has expired']"));

        if (popupTitle.size() > 0) {

            System.out.println("Trial popup is displayed.");

            driver.findElement(
                    By.xpath("//button[normalize-space()='Close']"))
                    .click();

            System.out.println("Trial popup closed.");

        } else {

            System.out.println("Trial popup not displayed.");
        }
    }
}