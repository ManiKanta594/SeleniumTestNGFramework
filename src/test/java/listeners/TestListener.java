package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import reports.ExtentManager;
import reports.ExtentTestManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.setTest(
                ExtentManager.getInstance().createTest(
                        result.getTestClass().getRealClass().getSimpleName()
                        + " - "
                        + result.getMethod().getMethodName()));

        System.out.println("Started : "
                + result.getTestClass().getRealClass().getSimpleName()
                + " | Thread : "
                + Thread.currentThread().getId());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");

        System.out.println("Passed : "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.FAIL, result.getThrowable());

        try {

            String screenshot =
                    ScreenshotUtil.captureScreenshot(
                            result.getMethod().getMethodName());

            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(screenshot);

        } catch (Exception e) {

            ExtentTestManager.getTest()
                    .warning(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test Skipped");
    }
}