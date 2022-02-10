package utils;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

public class WebDriverListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {

            WebDriver driver = DriverFactory.getDriver();
            if (testResult.getStatus() == ITestResult.FAILURE) {
                Log.debug("Test failed, making screen");
                try {
                    Screenshots.getScreenshot(testResult.getMethod().getMethodName(), driver);
                } catch (IOException e) {
                    Log.info("Error in making screen");
                    e.printStackTrace();
                }

            }
        }
    }

}
