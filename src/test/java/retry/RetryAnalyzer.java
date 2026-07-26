package retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger LOGGER =
            LogManager.getLogger(RetryAnalyzer.class);

    private static final int MAX_RETRY_COUNT = 2;

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            LOGGER.warn(
                    "Retrying test '{}' - Attempt {}/{}",
                    result.getMethod().getMethodName(),
                    retryCount,
                    MAX_RETRY_COUNT);

            return true;
        }

        LOGGER.error(
                "Maximum retry attempts reached for test '{}'.",
                result.getMethod().getMethodName());

        return false;
    }

}