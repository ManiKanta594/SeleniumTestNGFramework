package etl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Creates and manages the Extent Report instance.
 * Only one report is created for the complete TestNG Suite.
 */
public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {

    }

    /**
     * Returns singleton Extent Report instance.
     */
    public static ExtentReports getExtentReport() {

        if (extent == null) {

            String timeStamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));

            String reportPath =
                    System.getProperty("user.dir")
                    + "/test-output/ETL_Report_"
                    + timeStamp
                    + ".html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setReportName("ETL Automation Report");
            sparkReporter.config().setDocumentTitle("ETL Validation Report");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Framework", "ETL Automation");
            extent.setSystemInfo("Language", "Java 21");
            extent.setSystemInfo("Execution", "TestNG");

        }

        return extent;

    }

}