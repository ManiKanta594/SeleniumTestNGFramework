package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import constants.FrameworkConstants;
import drivers.DriverFactory;

public final class ScreenshotUtil {

    private static final Logger LOGGER =
            LogManager.getLogger(ScreenshotUtil.class);

    private ScreenshotUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String captureScreenshot(String screenshotName) {

        try {

            Path screenshotDirectory =
                    Paths.get(FrameworkConstants.SCREENSHOT_PATH);

            if (Files.notExists(screenshotDirectory)) {

                Files.createDirectories(screenshotDirectory);

                LOGGER.info("Created screenshot directory: {}",
                        screenshotDirectory.toAbsolutePath());
            }

            String fileName = screenshotName
                    .replaceAll("[^a-zA-Z0-9_-]", "_")
                    + ".png";

            File source = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(
                    FrameworkConstants.SCREENSHOT_PATH + fileName);

            FileUtils.copyFile(source, destination);

            LOGGER.info("Screenshot captured successfully: {}",
                    destination.getAbsolutePath());

            // Relative path for Extent Report
            return "Screenshots/" + fileName;

        } catch (IOException e) {

            LOGGER.error("Unable to capture screenshot: {}", screenshotName, e);

            throw new RuntimeException(
                    "Unable to capture screenshot.", e);
        }
    }
}