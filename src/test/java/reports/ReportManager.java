package reports;

import com.aventstack.extentreports.MediaEntityBuilder;

import utilities.ScreenshotUtil;

public final class ReportManager {

    private ReportManager() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Logs an informational message.
     */
    public static void logInfo(String message) {
        ExtentTestManager.getTest().info(message);
    }

    /**
     * Logs a successful message.
     */
    public static void logPass(String message) {
        ExtentTestManager.getTest().pass(message);
    }

    /**
     * Logs a failed message.
     */
    public static void logFail(String message) {
        ExtentTestManager.getTest().fail(message);
    }

    /**
     * Logs a warning message.
     */
    public static void logWarning(String message) {
        ExtentTestManager.getTest().warning(message);
    }

    /**
     * Logs a business step with screenshot.
     */
    public static void logStep(String stepDescription) {

        try {

            String screenshotPath = ScreenshotUtil.captureScreenshot(
                    stepDescription.replace(" ", "_"));

            ExtentTestManager.getTest().info(
                    stepDescription,
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(screenshotPath)
                            .build());

        } catch (Exception e) {

            ExtentTestManager.getTest().warning(
                    "Unable to capture screenshot : " + e.getMessage());

            ExtentTestManager.getTest().info(stepDescription);
        }
    }
}