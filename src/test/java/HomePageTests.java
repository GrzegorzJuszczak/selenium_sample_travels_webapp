import org.testng.annotations.Test;
import pages.HomePage;

import java.io.IOException;

import static data.HomePageData.*;

public class HomePageTests extends Base {

    private HomePage homePage;

    @Test
    public void verifyHomePageIsOpen() {
        homePage = new HomePage(getDriver());

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.verifyContactDetailsAreCorrect(CONTACT_PHONE, CONTACT_EMAIL);
        homePage.verifyFooterContactDetailsAndRightsReservedText(CONTACT_PHONE, CONTACT_EMAIL, FOOTER_RIGHTS_RESERVED_TEXT);

    }

    @Test
    public void verifyIfImagesOnHomePageAreLoaded() {
        homePage = new HomePage(getDriver());

        homePage.verifyIfAllDisplayedImagesAreLoaded();
    }

    @Test
    public void verifyIfFooterLinksAreNotBroken() throws IOException {
        homePage = new HomePage(getDriver());

        homePage.verifyIfFooterLinksAreNotBroken();
    }
}
