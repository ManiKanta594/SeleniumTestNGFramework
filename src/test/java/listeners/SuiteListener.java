package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import reports.ExtentManager;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {

        System.out.println("========== SUITE STARTED ==========");

        // Initialize Extent Report once for the entire suite
        ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {

        System.out.println("========== SUITE FINISHED ==========");

        // Flush report only once after all tests complete
        ExtentManager.flushReport();
    }
}