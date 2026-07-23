package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import constants.FrameworkConstants;
import drivers.DriverFactory;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String captureScreenshot(String screenshotName) {

        try {

            Path screenshotDirectory =
                    Paths.get(FrameworkConstants.SCREENSHOT_PATH);

            if (Files.notExists(screenshotDirectory)) {

                Files.createDirectories(screenshotDirectory);
            }

            String fileName = screenshotName
                    .replaceAll("[^a-zA-Z0-9_-]", "_")
                    + ".png";

            File source = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(
                    FrameworkConstants.SCREENSHOT_PATH + fileName);

            FileUtils.copyFile(source, destination);

            // Relative path for Extent Report
            return "Screenshots/" + fileName;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to capture screenshot.", e);
        }
    }
}