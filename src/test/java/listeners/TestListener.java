package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utilities.ScreenshotUtil;

import utilities.ExecutionSummary;
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
        LOGGER.info("Test Started : {}", result.getMethod().getMethodName());
        LOGGER.info("=================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.PASS, "Test Passed");

        ExecutionSummary summary = ExecutionSummary.getInstance();

        summary.incrementPassedTests();
        summary.incrementExecutedTests();
    }
    
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.FAIL, result.getThrowable());

        try {

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            result.getMethod().getMethodName());

            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(screenshotPath);

            LOGGER.info("Screenshot attached to Extent Report.");

        } catch (Exception e) {

            LOGGER.error("Unable to capture screenshot.", e);

            ExtentTestManager.getTest()
                    .warning("Unable to attach screenshot.");
        }
        
        ExecutionSummary summary = ExecutionSummary.getInstance();

        summary.incrementFailedTests();
        summary.incrementExecutedTests();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test Skipped");

        ExecutionSummary summary = ExecutionSummary.getInstance();

        summary.incrementSkippedTests();
        summary.incrementExecutedTests();
    }
}