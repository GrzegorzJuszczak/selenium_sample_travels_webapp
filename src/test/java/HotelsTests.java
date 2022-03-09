import data.HomePageData;
import data.LoginPageData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import static generators.DataGenerator.*;

public class HotelsTests extends BaseTest {

    private HomePage homePage;
    private HotelsPage hotelsPage;


    @Test
    public void verifyStarsFilter() {


        homePage = new HomePage(getDriver());
        hotelsPage = new HotelsPage(driver);


        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToTopMenuTab(HomePageData.HOTELS_TAB);

        hotelsPage.verifyIfHotelsPageIsOpen();

        String[] cities = {"Dubai", "London", "Sydney", "Berlin"};

        for (String city : cities) {

            hotelsPage.insertHotelAndClickOnSearchButton(city);

            for (int j = 1; j <= 5; j++) {
                hotelsPage.clickOnStarsFilterAndVerifyResults(j); //TODO poprawic pętle z miastami
            }
        }



    }



}
