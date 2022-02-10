package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshots {

    public static void getScreenshot(String testName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String fileName = testName+Date.getCurrentDate()+".png";
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "\\reports\\screenshots\\"+fileName));
        ExtentUtils.fetchTest().info("Screenshot done in test method: " + testName + " ", MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "\\reports\\screenshots\\"+fileName).build());
        ExtentUtils.fetchTest().addScreenCaptureFromPath(System.getProperty("user.dir") + "\\reports\\screenshots\\"+fileName);

    }


}
