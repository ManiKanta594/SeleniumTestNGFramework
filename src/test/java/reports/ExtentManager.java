package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FrameworkConstants;
import utilities.ReportDirectoryManager;

public final class ExtentManager {

    private static final Logger LOGGER =
            LogManager.getLogger(ExtentManager.class);

    private static ExtentReports extentReports;

    private ExtentManager() {
        throw new IllegalStateException("Utility class");
    }

    public static ExtentReports getInstance() {

        if (extentReports == null) {

            LOGGER.info("Initializing Extent Report...");

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

            LOGGER.info("Extent Report initialized successfully.");
        }

        return extentReports;
    }

    public static void flushReport() {

        if (extentReports != null) {

            LOGGER.info("Flushing Extent Report...");

            extentReports.flush();

            File report = new File(
                    FrameworkConstants.LATEST_REPORT_PATH
                            + FrameworkConstants.REPORT_FILE_NAME);

            LOGGER.info("Extent Report Generated : {}",
                    report.getAbsolutePath());

            // Open report only if Desktop is supported
            if (Desktop.isDesktopSupported()) {

                try {

                    Desktop desktop = Desktop.getDesktop();

                    if (desktop.isSupported(Desktop.Action.BROWSE)) {

                        desktop.browse(report.toURI());

                        LOGGER.info("Extent Report opened automatically.");

                    }

                } catch (IOException e) {

                    LOGGER.error("Unable to open report automatically.", e);
                }

            } else {

                LOGGER.warn(
                        "Desktop mode not available. Report generated successfully.");
            }
        }
    }
}