package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utilities.AllureUtil;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static final Logger LOGGER =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.setTest(
                ExtentManager.getInstance().createTest(
                        result.getTestClass().getRealClass().getSimpleName()
                        + " - "
                        + result.getMethod().getMethodName()));

        LOGGER.info("=================================================");
        LOGGER.info("Test Started : {}",
                result.getMethod().getMethodName());
        LOGGER.info("Test Class   : {}",
                result.getTestClass().getRealClass().getSimpleName());
        LOGGER.info("Thread ID    : {}",
                Thread.currentThread().getId());
        LOGGER.info("=================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");

        LOGGER.info("Test Passed : {}",
                result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.FAIL, result.getThrowable());

        LOGGER.error("Test Failed : {}",
                result.getMethod().getMethodName(),
                result.getThrowable());

        try {

            String screenshot =
                    ScreenshotUtil.captureScreenshot(
                            result.getMethod().getMethodName());

            // Attach screenshot to Extent Report
            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(screenshot);

            // Attach screenshot to Allure Report
            AllureUtil.attachScreenshot();

            // Attach exception to Allure
            AllureUtil.attachText(
                    "Exception",
                    result.getThrowable().toString());

            LOGGER.info("Screenshot attached to Extent and Allure Report.");

        } catch (Exception e) {

            LOGGER.error("Failed to capture screenshot.", e);

            ExtentTestManager.getTest()
                    .warning(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test Skipped");

        LOGGER.warn("Test Skipped : {}",
                result.getMethod().getMethodName());
    }
}