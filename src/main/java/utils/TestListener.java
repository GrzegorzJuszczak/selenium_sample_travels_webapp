package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {

        ExtentUtils.createTest(result.getMethod().getMethodName());
        Log.startTest(result.getMethod().getMethodName());
        String[] categories = result.getMethod().getGroups();
        for(String category: categories) {
            ExtentUtils.fetchTest().assignCategory(category);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test passed");
        ExtentUtils.fetchTest().log(Status.PASS, MarkupHelper.createLabel("*************** TEST PASSED ***************", ExtentColor.GREEN));
        Log.endTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("Test failed");
        ExtentUtils.fetchTest().log(Status.FAIL, MarkupHelper.createLabel("*************** TEST FAILED ***************", ExtentColor.RED));
        Log.endTest(result.getMethod().getMethodName());

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentUtils.saveReporter();

    }
}
