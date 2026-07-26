package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import utilities.AllureUtil;

public class AllureListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {

        if (!method.isTestMethod()) {
            return;
        }

        if (result.getStatus() == ITestResult.FAILURE) {

            AllureUtil.attachScreenshot();

            if (result.getThrowable() != null) {
                AllureUtil.attachText(
                        "Exception",
                        result.getThrowable().toString());
            }
        }
    }
}