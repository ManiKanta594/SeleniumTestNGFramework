package utilities;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import drivers.DriverFactory;
import io.qameta.allure.Allure;

public final class AllureUtil {

    private AllureUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void attachScreenshot() {

        if (DriverFactory.getDriver() == null) {
            return;
        }

        byte[] screenshot =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png");
    }

    public static void attachText(String title, String message) {

        Allure.addAttachment(
                title,
                "text/plain",
                new ByteArrayInputStream(
                        message.getBytes(StandardCharsets.UTF_8)),
                ".txt");
    }
}