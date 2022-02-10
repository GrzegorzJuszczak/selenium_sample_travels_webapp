package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import utils.Log;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

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


    public void verifyTopPanelElementsAreDisplayed() {
        Log.info("Verifying if top panel elements are displayed.");
        SoftAssert softAssert = new SoftAssert();
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

}
