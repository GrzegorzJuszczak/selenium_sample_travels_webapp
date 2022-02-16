package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import utils.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //TOP PANEL
    @FindBy(css = "div[class='logo'] img")
    private WebElement imgLogo;

    @FindBy(css = "div[class*='header-left'] li:last-of-type a")
    private WebElement txtContactMail;

    @FindBy(css = "div[class*='header-left'] li:first-of-type a")
    private WebElement txtContactPhone;

    @FindBy(linkText = "Home")
    private WebElement btnHome;

    @FindBy(linkText = "Signup")
    private WebElement btnSignUp;

    @FindBy(linkText = "Login")
    private WebElement btnLogin;

    //FOOTER
    @FindBy(css = "div[class*='box footer-item'] ul")
    private WebElement txtRightsReservedFooter;

    @FindBy(xpath = "//div//ul//li[1]//strong")
    private WebElement txtContactPhoneFooter;

    @FindBy(xpath = "//div//ul//li[2]//strong")
    private WebElement txtContactEmailFooter;

    @FindBy(tagName = "img")
    private List<WebElement> images;

    @FindBy(css = "section[class*='footer'] ul.foot_menu.w-100")
    private WebElement footerDriver;



    SoftAssert softAssert = new SoftAssert();

    public void verifyTopPanelElementsAreDisplayed() {
        Log.info("Verifying if top panel elements are displayed.");

        softAssert.assertTrue(imgLogo.isDisplayed());
        softAssert.assertTrue(btnHome.isDisplayed());
        softAssert.assertTrue(btnSignUp.isDisplayed());
        softAssert.assertAll();
        Log.info("Top panel elements are displayed.");
    }

    public void verifyContactDetailsAreCorrect(String expPhone, String expEmail) {
        Log.info("Verifying if phone number and email displayed on website are as expected.");
        waitUntilElementIsVisible(txtContactPhone);
        textAssertion(txtContactPhone.getText().trim(), expPhone);
        waitUntilElementIsVisible(txtContactMail);
        textAssertion(txtContactMail.getText(), expEmail);
        Log.info("Phone number and email displayed on website are as expected.");

    }

    public void verifyFooterContactDetailsAndRightsReservedText(String expPhone, String expEmail, String expectedRightsText) {
        Log.info("Verifying if contact details and rights reserved text in footer are as expected.");
        waitUntilElementIsVisible(txtContactPhoneFooter);
        textAssertion(txtContactPhoneFooter.getText().trim(), expPhone);
        waitUntilElementIsVisible(txtContactEmailFooter);
        textAssertion(txtContactEmailFooter.getText(), expEmail);
        waitUntilElementIsVisible(txtRightsReservedFooter);
        textAssertion(txtRightsReservedFooter.getText(), expectedRightsText);
        Log.info("Contact details and rights reserved text in footer are as expected.");
    }

    public void verifyIfAllDisplayedImagesAreLoaded() {
        Log.info("Verifying if all images displayed on website are loaded correctly.");
        List<WebElement> displayedImages = images.stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
        for(WebElement img:displayedImages) {
            int width = Integer.parseInt(img.getAttribute("naturalWidth"));
            String src = img.getAttribute("src");
            Log.info("The image with link '"+src+"' has width '" +width+ "'.");
            softAssert.assertTrue(width>0);
        }
        softAssert.assertAll();
        Log.info("All images are loaded correctly.");
    }

    public void verifyIfFooterLinksAreNotBroken() throws IOException {
        Log.info("Verifying if footer links are working.");
        List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
        for(WebElement link:footerLinks) {
            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int respCode = connection.getResponseCode();
            String message = "The link with text '"+link.getText()+"' is with code '" +respCode+ "'.";
            Log.info(message);
            softAssert.assertTrue(respCode < 400);
        }
        softAssert.assertAll();
        Log.info("All links are working fine.");
    }

}
