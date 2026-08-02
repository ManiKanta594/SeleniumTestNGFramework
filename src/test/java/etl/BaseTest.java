package etl;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

/**
 * Base class for all ETL TestNG Tests.
 *
 * Responsibilities:
 * ----------------------------------
 * 1. Open Database Connection
 * 2. Start Extent Report
 * 3. Create Test in Report
 * 4. Close Database Connection
 * 5. Flush Extent Report
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
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        // Reserved for future use
        // Example:
        // Capture execution time
        // Capture failed SQL
        // Capture screenshots (if UI involved)

    }

    /**
     * Executes once after Suite execution.
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        DatabaseUtil.closeConnection();

        ReportManager.flushReport();

        System.out.println("=======================================");
        System.out.println("ETL Automation Execution Completed");
        System.out.println("=======================================");

    }

}