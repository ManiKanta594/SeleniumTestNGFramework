package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import reports.ExtentManager;

public class SuiteListener implements ISuiteListener {

    private static final Logger LOGGER =
            LogManager.getLogger(SuiteListener.class);

    @Override
    public void onStart(ISuite suite) {

        LOGGER.info("=================================================");
        LOGGER.info("Suite Started : {}", suite.getName());
        LOGGER.info("=================================================");

        // Initialize Extent Report once for the entire suite
        ExtentManager.getInstance();

        LOGGER.info("Extent Report initialized successfully.");
    }

    @Override
    public void onFinish(ISuite suite) {

        LOGGER.info("Flushing Extent Report...");

        // Flush report only once after all tests complete
        ExtentManager.flushReport();

        LOGGER.info("Extent Report flushed successfully.");
        LOGGER.info("Suite Finished : {}", suite.getName());
        LOGGER.info("=================================================");
    }
}