package seleniumpracticeset2;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ZTest15MultpleTabs {

	@Test
	
    public void multiTabs() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Parent Tab
        driver.get("https://testautomationpractice.blogspot.com/");

        String parent = driver.getWindowHandle();

        // New Tab 1
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.google.com");

        System.out.println(driver.getTitle());

        // New Tab 2
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.wikipedia.org");

        System.out.println(driver.getTitle());

        // Return to Parent
        driver.switchTo().window(parent);

        System.out.println(driver.getTitle());
        
        Thread.sleep(5000);

        driver.quit();
        
    }
}