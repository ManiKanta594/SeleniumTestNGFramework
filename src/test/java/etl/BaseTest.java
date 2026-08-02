package etl;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * ==========================================================
 * Base Test Class
 * ==========================================================
 *
 * Responsibilities:
 *
 * 1. Initialize Extent Report
 * 2. Open Database Connection
 * 3. Create Test in Extent Report
 * 4. Update Test Status Automatically
 * 5. Close Database Connection
 * 6. Flush Extent Report
 * 7. Open Latest Report Automatically
 *
 * ==========================================================
 */
public class BaseTest {

    /**
     * Executes once before Suite execution.
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        System.out.println("=======================================");
        System.out.println("Starting ETL Automation Framework");
        System.out.println("=======================================");

        // Initialize Extent Report
        ExtentManager.getExtentReport();

        // Open Database Connection
        DatabaseUtil.connect();

    }

    /**
     * Executes before every Test Method.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {

        ReportManager.startTest(
                method.getName(),
                "Executing ETL Validation");

    }

    /**
     * Executes after every Test Method.
     * Automatically updates Extent Report
     * based on actual TestNG execution result.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:

                ReportManager.pass("Test Passed Successfully.");

                break;

            case ITestResult.FAILURE:

                if (result.getThrowable() != null) {

                    ReportManager.fail(
                            result.getThrowable().getMessage());

                } else {

                    ReportManager.fail("Test Failed.");

                }

                break;

            case ITestResult.SKIP:

                ReportManager.skip("Test Skipped.");

                break;

            default:
                break;

        }

    }

    /**
     * Executes once after Suite execution.
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        // Close Database Connection
        DatabaseUtil.closeConnection();

        // Flush Extent Report
        ReportManager.flushReport();

        // Open Latest Report Automatically
        openLatestExtentReport();

        System.out.println("=======================================");
        System.out.println("ETL Automation Execution Completed");
        System.out.println("=======================================");

    }

    /**
     * Opens the latest generated Extent Report.
     */
    private void openLatestExtentReport() {

        try {

            File reportFolder = new File("test-output");

            File[] reports = reportFolder.listFiles((dir, name) ->
                    name.startsWith("ETL_Report_")
                            && name.endsWith(".html"));

            if (reports == null || reports.length == 0) {

                return;

            }

            File latestReport = reports[0];

            for (File report : reports) {

                if (report.lastModified() >
                        latestReport.lastModified()) {

                    latestReport = report;

                }

            }

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().browse(latestReport.toURI());

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}