package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import utils.Log;

import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//h3[text()='Star Grade']")
    private WebElement headerStarGrade;

    @FindBy(xpath = "//strong[contains(text(),'No results found')]")
    private List<WebElement> txtNoResults;

    @FindBy(css = "div[class*='sidebar-price-range'] span[class*='irs-handle from']")
    private WebElement handleFrom;

    @FindBy(css = "div[class*='sidebar-price-range'] span[class*='irs-handle to']")
    private WebElement handleTo;

    @FindBy(css = "span[class='irs-from']")
    private WebElement narrowedPriceFrom;

    @FindBy(css = "span[class='irs-to']")
    private WebElement narrowedPriceTo;

    @FindBy(css = "li:not([style*='none']) span[class='price__num'] strong")
    private List<WebElement> prices;

    SoftAssert softAssert = new SoftAssert();


    public void verifyIfHotelsPageIsOpen() {
        Log.info("Verifying if hotels page is open.");
        waitUntilElementIsVisible(headerHotelsPage);
        Log.info("Hotels page is open.");
    }

    public void insertHotelAndClickOnSearchButton(String city) {
        Log.info("Entering city '"+city+"' into search input.");
        waitUntilElementIsClickable(spanSearch);
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
        WebElement starsFilter = driver.findElement(By.cssSelector("label[for='stars_" + stars + "']"));
        waitUntilElementIsClickable(starsFilter);
        starsFilter.click();
        verifyStarsAmountInResultsAfterFilter(stars);
        Log.info("Clearing filter.");
        waitUntilElementIsClickable(starsFilter);
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

    public void narrowPriceRange(int x) {
        Log.info("Narrowing price with website slider mechanism.");
        waitUntilElementIsVisible(handleFrom);
        dragAndDrop(handleFrom, x, 0);
        dragAndDrop(handleTo, -x, 0);
        Log.info("Narrowed from and to with '"+x+"' pixels.");
    }

    public double[] getNarrowedPriceRange() {
        double [] priceRange = new double[2];
        Log.info("Getting narrowed price values from slider mechanism.");
        waitUntilElementIsVisible(narrowedPriceFrom);
        String priceFrom = narrowedPriceFrom.getText();
        waitUntilElementIsVisible(narrowedPriceTo);
        String priceTo = narrowedPriceTo.getText();
        Log.info("Price range was set from '"+priceFrom+"' to '"+priceTo+"'.");
        priceRange[0] = Double.parseDouble(priceFrom);
        priceRange[1] = Double.parseDouble(priceTo);
        return priceRange;
    }

    public List<Double> getAllPrices() {
        Log.info("Getting all prices from narrowed prices results.");
        List<Double> txtPrices = prices.stream().map(WebElement::getText).map(Double::parseDouble).collect(Collectors.toList());
        Log.info("All narrowed prices results: " + txtPrices);
        return txtPrices;
    }

    public void verifyPricesRange(List<Double> prices, double min, double max) {
        Log.info("Verifying if all price results are between min '"+min+"' and max '"+max+"' range.");
        for(Double price: prices){
            Log.info("Verifying if price '"+price+"' is between min '"+min+"' and max '"+max+"' range.");
            softAssert.assertTrue(price >= min && price <= max, "Price '"+price+"' is out of range.");
            Log.info("Price '"+price+"' is between min '"+min+"' and max '"+max+"' range.");
            softAssert.assertAll();
        }

    }
}
