package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBrowser {

    public static void main(String[] args) {

        System.setProperty(
                "webdriver.edge.driver",
                "C:\\Users\\ASUS\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        driver.get("https://www.google.com");

        System.out.println(driver.getTitle());

        driver.quit();
    }
}