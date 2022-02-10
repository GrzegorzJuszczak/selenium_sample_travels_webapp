package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebEventListener implements WebDriverListener {

    @Override
    public void beforeGet(WebDriver driver, String url) {
        Log.info("Before navigating to: '" +url+ "'.");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        Log.info("Navigated to: '" +url+ "'.");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        Log.debug("Trying to find element by: " + locator.toString());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        Log.debug("Found element by: " + locator.toString());
    }

    @Override
    public void beforeClick(WebElement element) {
        Log.debug("Trying to click on element: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
        Log.debug("Clicked on element: " + element);
    }



}
