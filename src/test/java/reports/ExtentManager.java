package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FrameworkConstants;
import utilities.ReportDirectoryManager;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
        throw new IllegalStateException("Utility class");
    }

    public static ExtentReports getInstance() {

        if (extentReports == null) {

            // Prepare Latest & Previous folders
            ReportDirectoryManager.prepareReportDirectories();

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            FrameworkConstants.LATEST_REPORT_PATH
                                    + FrameworkConstants.REPORT_FILE_NAME);

            sparkReporter.config().setReportName(
                    "Selenium TestNG Automation Report");

            sparkReporter.config().setDocumentTitle(
                    "Automation Execution Report");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework", "Selenium TestNG");
            extentReports.setSystemInfo("Language", "Java");
            extentReports.setSystemInfo("Tester", "Manikanta");
        }

        return extentReports;
    }

    public static void flushReport() {

        if (extentReports != null) {

            extentReports.flush();

            File report = new File(
                    FrameworkConstants.LATEST_REPORT_PATH
                            + FrameworkConstants.REPORT_FILE_NAME);

            System.out.println("Extent Report Generated : "
                    + report.getAbsolutePath());

            // Open report only if Desktop is supported
            if (Desktop.isDesktopSupported()) {

                try {

                    Desktop desktop = Desktop.getDesktop();

                    if (desktop.isSupported(Desktop.Action.BROWSE)) {

                        desktop.browse(report.toURI());

                    }

                } catch (IOException e) {

                    System.out.println(
                            "Unable to open report automatically : "
                                    + e.getMessage());
                }

            } else {

                System.out.println(
                        "Desktop mode not available. Report generated successfully.");
            }
        }
    }
}