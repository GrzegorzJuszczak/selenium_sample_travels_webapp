import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.*;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;


    @BeforeSuite
    public static void beforeSuite() {
        ExtentUtils.createReporter(System.getProperty("user.dir") + "\\reports\\TestReport"+ Date.getCurrentDate()+".html");
    }



    @BeforeMethod
    public void setup() {
        DriverFactory.createInstance();
        driver = DriverFactory.getDriver();

    }

    @AfterMethod
    public void tearDown()
    {
        if(driver!=null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }

    }

    public WebDriver getDriver(){
        try {
            driver.get(PropertiesLoader.loadProperty("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.debug("Thread id = " + Thread.currentThread().getId());
        Log.debug("Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
        return driver;
    }

}
