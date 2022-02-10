import data.HomePageData;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @Test
    public void verifyHomePageIsOpen(){
        homePage = new HomePage(getDriver());

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.verifyContactDetailsAreCorrect(HomePageData.CONTACT_PHONE, HomePageData.CONTACT_EMAIL);
    }
}
