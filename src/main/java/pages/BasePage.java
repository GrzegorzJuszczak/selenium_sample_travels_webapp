package pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Log;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    protected void waitUntilElementIsVisible(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    protected void waitUntilElementIsClickable(WebElement el) {
        waitUntilElementIsVisible(el);
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }

    protected void moveToElement(WebElement el) {
        Actions actions = new Actions(driver);
        actions.moveToElement(el);
        actions.build().perform();
    }

    protected void clickElementByJavaScript(WebElement el) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", el);
    }

    protected void textAssertion(String webText, String expText) {
        Log.info("Verifying if text displayed on website is as expected.");
        Assert.assertEquals(webText, expText);
        Log.info("Text from website '"+webText+"' is the same as expected '"+expText+"'.");
    }



}