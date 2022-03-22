import data.HomePageData;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class HotelsTests extends Base {

    private HomePage homePage;
    private HotelsPage hotelsPage;


    @Test
    public void verifyStarsFilter() {

        homePage = new HomePage(getDriver());
        hotelsPage = new HotelsPage(driver);


        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToTopMenuTab(HomePageData.HOTELS_TAB);

        hotelsPage.verifyIfHotelsPageIsOpen();

        String[] cities = {"Dubai", "London", "Berlin"};

        for (String city : cities) {

            homePage.goToTopMenuTab(HomePageData.HOTELS_TAB);
            hotelsPage.verifyIfHotelsPageIsOpen();
            hotelsPage.insertHotelAndClickOnSearchButton(city);

            for (int j = 1; j <= 5; j++) {
                hotelsPage.clickOnStarsFilterAndVerifyResults(j);
            }
        }

    }

    @Test
    public void verifyPriceRangeFilter() {

        homePage = new HomePage(getDriver());
        hotelsPage = new HotelsPage(driver);


        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToTopMenuTab(HomePageData.HOTELS_TAB);

        hotelsPage.verifyIfHotelsPageIsOpen();

        homePage.goToTopMenuTab(HomePageData.HOTELS_TAB);
        hotelsPage.verifyIfHotelsPageIsOpen();
        hotelsPage.insertHotelAndClickOnSearchButton("Dubai");

        hotelsPage.narrowPriceRange(50);
        double[] priceRange = hotelsPage.getNarrowedPriceRange();
        List<Double> prices = hotelsPage.getAllPrices();
        hotelsPage.verifyPricesRange(prices, priceRange[0], priceRange[1]);





    }



}
