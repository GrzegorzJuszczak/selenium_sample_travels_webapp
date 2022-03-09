package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import utils.Log;

import java.util.List;

public class HotelsPage extends BasePage {

    public HotelsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='SEARCH FOR BEST HOTELS']")
    private WebElement headerHotelsPage;

    @FindBy(id = "select2-hotels_city-container")
    private WebElement spanSearch;

    @FindBy(css = "input[type='search']")
    private WebElement inpSearch;

    @FindBy(xpath = "//span[contains(text(),'Search')]")
    private WebElement btnSearch;

    SoftAssert softAssert = new SoftAssert();


    public void verifyIfHotelsPageIsOpen() {
        Log.info("Verifying if hotels page is open.");
        waitUntilElementIsVisible(headerHotelsPage);
        Log.info("Hotels page is open.");
    }

    public void insertHotelAndClickOnSearchButton(String city) {
        Log.info("Entering city '"+city+"' into search input.");
        waitUntilElementIsClickable(spanSearch);
        scrollWindowByJavaScript(0);
        spanSearch.click();
        waitUntilElementIsVisible(inpSearch);
        inpSearch.clear();
        inpSearch.sendKeys(city);
        Log.info("Choosing city from displayed list.");
        driver.findElement(By.xpath("//li[contains(text(),'"+city+"')]")).click();
        waitUntilElementIsClickable(btnSearch);
        btnSearch.click();
        Log.info("Clicking on search button.");
    }

    public void clickOnStarsFilterAndVerifyResults (int stars) {
        Log.info("Clicking on star grade filter in left menu. Choosing '"+stars+"' stars.");
        WebElement starsFilter = driver.findElement(By.cssSelector("label[for='stars_"+stars+"']"));
        starsFilter.click();
        verifyStarsAmountInResultsAfterFilter(stars);
        Log.info("Clearing filter.");
        starsFilter.click();
    }


    public void verifyStarsAmountInResultsAfterFilter(int starsFilter) {
        Log.info("Verifying if only hotels with '"+starsFilter+"' star/stars are displayed.");
        List<WebElement> hotelsWithStars = driver.findElements(By.xpath("//li[contains(@class, 'stars_" + starsFilter + "')]"));
        Log.info("Amount of hotels with '"+starsFilter+"' star/stars equals to '"+hotelsWithStars.size()+"'.");
        for (int i = 1; i <= hotelsWithStars.size(); i++) {
            Log.info("Verifying if every single presented hotel has '"+starsFilter+"' stars icons displayed.");
            List<WebElement> stars = driver.findElements(By.xpath("//li[contains(@class, 'stars_"+starsFilter+"')][" + i + "]//div[contains(@class, 'stars')]"));
            Log.info("Hotel with number '" + i + "' has '" +stars.size() +"' star/stars.");
            softAssert.assertTrue(stars.size() == starsFilter);
        }
        softAssert.assertAll();
    }
}
