import data.HomePageData;
import org.testng.annotations.Test;
import pages.FlightsPage;
import pages.HomePage;

public class FlightsTests extends Base {

    private HomePage homePage;
    private FlightsPage flightsPage;

    @Test
    public void selectDepartureDateByCalendarAndVerifyIfIsSelected() {

        homePage = new HomePage(getDriver());
        flightsPage = new FlightsPage(driver);


        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToTopMenuTab(HomePageData.FLIGHTS_TAB);

        flightsPage.verifyIfFlightsPageIsOpen();
        flightsPage.selectDepartureDateByCalendar("3", "December", "2022");
        flightsPage.verifyIfDateByCalendarWasSelected("03-12-2022");

    }

}
