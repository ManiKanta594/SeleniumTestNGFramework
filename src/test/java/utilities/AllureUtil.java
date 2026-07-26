package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import drivers.DriverFactory;

import java.io.ByteArrayInputStream;

public final class AllureUtil {

    private AllureUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Attach screenshot to Allure Report.
     */
    public static void attachScreenshot() {

        if (DriverFactory.getDriver() == null) {
            return;
        }

        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png");
    }

    /**
     * Attach text information to Allure.
     */
    public static void attachText(String title, String message) {

        Allure.addAttachment(title, message);
    }
}