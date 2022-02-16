package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Log {


    public static void info(String message) {
        ExtentUtils.fetchTest().log(Status.INFO, MarkupHelper.createLabel("*************** " + message + " ***************", ExtentColor.BLUE));
        System.out.println("[INFO] " + message);


    }

    public static void warn(String message) {
        ExtentUtils.fetchTest().log(Status.WARNING, MarkupHelper.createLabel("*************** " + message + " ***************", ExtentColor.PURPLE));
        System.out.println("[WARN] " + message);

    }

    public static void error(String message) {
        ExtentUtils.fetchTest().log(Status.WARNING, MarkupHelper.createLabel("*************** " + message + " ***************", ExtentColor.PURPLE));
        System.out.println("[ERROR] " + message);


    }

    public static void fatal(String message) {
        ExtentUtils.fetchTest().log(Status.FAIL, MarkupHelper.createLabel("*************** " + message + " ***************", ExtentColor.RED));
        System.out.println("[FATAL] " + message);


    }

    public static void debug(String message) {
        ExtentUtils.fetchTest().log(Status.INFO, message);
        System.out.println("[DEBUG] " + message);
    }

    public static void startTest(String testName){

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

        Log.info("*************************************TEST START*****************************************\n");

        Log.info("                      TEST NAME: "+testName+"                                           \n");

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

    }

    public static void endTest(String testName){

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************\n");

        Log.info("                      TEST NAME: "+testName+"                                           \n");

        Log.info("*************************************TEST END*******************************************");

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

    }


}
