package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;
import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }


    public static WebDriver createInstance() {
        WebDriver dv;
        String name = null;
        try {
            name = PropertiesLoader.loadProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

            switch (name) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    dv = new FirefoxDriver();
                case "ie":
                    WebDriverManager.iedriver().setup();
                    dv = new InternetExplorerDriver();
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    dv = new EdgeDriver();
                default:
                    WebDriverManager.chromedriver().setup();
                    dv = new ChromeDriver();
            }
        dv.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        dv.manage().window().maximize();

        WebDriverListener listener = new WebEventListener();
        WebDriver driver = new EventFiringDecorator(listener).decorate(dv);
        setDriver(driver);
        return driver;

    }







}