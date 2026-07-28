package listeners;

import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import reports.ExtentManager;
import utilities.ConfigReader;
import utilities.ExecutionSummary;
import utilities.ExcelReportManager;

public class SuiteListener implements ISuiteListener {

    private static final Logger LOGGER =
            LogManager.getLogger(SuiteListener.class);

    @Override
    public void onStart(ISuite suite) {

        LOGGER.info("=================================================");
        LOGGER.info("Suite Started : {}", suite.getName());
        LOGGER.info("=================================================");

        // Initialize Extent Report
        ExtentManager.getInstance();

        LOGGER.info("Extent Report initialized successfully.");

        // Capture Suite Start Time
        ExecutionSummary.getInstance().setStartTime(LocalDateTime.now());
    }

    @Override
    public void onFinish(ISuite suite) {

        ExecutionSummary summary = ExecutionSummary.getInstance();

        // End Time
        summary.setEndTime(LocalDateTime.now());

        // Calculate Duration
        Duration duration = Duration.between(
                summary.getStartTime(),
                summary.getEndTime());

        summary.setExecutionTimeInSeconds(duration.getSeconds());

        // Total Tests
        summary.setTotalTests(
                summary.getPassedTests()
                + summary.getFailedTests()
                + summary.getSkippedTests());

        // Read Framework Configuration
        ConfigReader config = ConfigReader.getInstance();

        summary.setBrowser(config.getBrowser().name());
        summary.setEnvironment(config.getEnvironment());
        summary.setExecutionMode(config.getTestExecutionMode());
        summary.setDriverMode(config.getDriverExecutionMode());

        // Write Excel Dashboard
        ExcelReportManager.createSummarySheet();
      
        ExcelReportManager.writeExecutionSummary(summary);

        LOGGER.info("Flushing Extent Report...");

        ExtentManager.flushReport();

        LOGGER.info("Extent Report flushed successfully.");
        LOGGER.info("Suite Finished : {}", suite.getName());
        LOGGER.info("=================================================");
    }
}