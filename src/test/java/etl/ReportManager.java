package etl;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Handles Extent Report logging.
 *
 * Responsibilities:
 * 1. Create Test
 * 2. Log PASS
 * 3. Log FAIL
 * 4. Log INFO
 * 5. Flush Report
 */
public final class ReportManager {

    private static final ExtentReports extent =
            ExtentManager.getExtentReport();

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    private ReportManager() {

    }

    /**
     * Creates a new test in the Extent Report.
     */
    public static void startTest(String testName,
                                 String description) {

        ExtentTest extentTest =
                extent.createTest(testName, description);

        test.set(extentTest);

    }

    /**
     * Logs Information.
     */
    public static void info(String message) {

        test.get().log(Status.INFO, message);

    }

    /**
     * Logs Pass.
     */
    public static void pass(String message) {

        test.get().log(Status.PASS, message);

    }

    /**
     * Logs Fail.
     */
    public static void fail(String message) {

        test.get().log(Status.FAIL, message);

    }

    /**
     * Logs Warning.
     */
    public static void warning(String message) {

        test.get().log(Status.WARNING, message);

    }

    /**
     * Logs Skip.
     */
    public static void skip(String message) {

        test.get().log(Status.SKIP, message);

    }

    /**
     * Returns current Extent Test.
     */
    public static ExtentTest getTest() {

        return test.get();

    }

    /**
     * Flushes report to disk.
     */
    public static void flushReport() {

        extent.flush();

    }

}