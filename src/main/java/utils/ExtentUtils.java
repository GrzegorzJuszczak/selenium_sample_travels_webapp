package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentUtils {

    private static ExtentReports reporter;
    private static Map<Integer, ExtentTest>  extentTestMap = new HashMap<>();

    public static ExtentReports createReporter(String fileName) {

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName("PHPTRAVELS tests");
        extentSparkReporter.config().setDocumentTitle("Test Results");
        extentSparkReporter.config().setTheme(Theme.DARK);

        reporter = new ExtentReports();
        reporter.attachReporter(extentSparkReporter);

        reporter.setSystemInfo("Test Automation Engineer", "Grzegorz Juszczak");

        return reporter;

    }

    public static void saveReporter(){
        if(reporter !=null){
            reporter.flush();
        }
    }

    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest extentTest = reporter.createTest(testName);
        extentTestMap.put((int) (Thread.currentThread().getId()), extentTest);
        return extentTest;
    }

    public static synchronized ExtentTest fetchTest() {
        return  extentTestMap.get((int)  (Thread.currentThread().getId()));
    }




}
