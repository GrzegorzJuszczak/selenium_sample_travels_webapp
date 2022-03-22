package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement headerLogin;

    @FindBy(xpath = "//p[text()='Please enter your account credentials below']")
    private WebElement txtUnderHeader;

    @FindBy(css = "div[class*='alert-success signup']")
    private WebElement alertSuccessSignup;

    @FindBy(name = "email")
    private WebElement inpEmail;

    @FindBy(name = "password")
    private WebElement inpPassword;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement btnLogin;

    @FindBy(css = "div[class*='alert']")
    private WebElement alertInvalidData;




    public void verifySuccessfulSignupAlert(String expText) {
        Log.info("Verifying if successful signup alert is present with expected text.");
        waitUntilElementIsVisible(alertSuccessSignup);
        textAssertion(alertSuccessSignup.getText(), expText);
        Log.info("Successful signup alert is present with expected text.");
    }

    public void verifyIfLoginPageIsDisplayed() {
        Log.info("Verifying if login page is displayed.");
        waitUntilElementIsVisible(headerLogin);
        waitUntilElementIsVisible(txtUnderHeader);
        Log.info("Login page is displayed.");
    }

    public void clickOnLoginButton() {
        Log.info("Clicking on login button");
        waitUntilElementIsClickable(btnLogin);
        btnLogin.click();
        Log.info("Clicked on 'Login' button.");
    }

    public void loginToAccount(String email, String password) {
        Log.info("Login into account with email '"+email+"'.");
        waitUntilElementIsVisible(inpEmail);
        inpEmail.sendKeys(email);
        waitUntilElementIsVisible(inpPassword);
        inpPassword.sendKeys(password);
        Log.info("Data was entered.");
        clickOnLoginButton();
    }

    public void verifyIfFillFieldAlertIsDisplayed(String expTooltip) {
        Log.info("Verifying if fill field tooltip is displayed.");
        textAssertion(inpEmail.getAttribute("validationMessage"), expTooltip);
        Log.info("Fill field tooltip is displayed.");
    }

    public void verifyIfInvalidDataAlertIsDisplayed(String expAlert) {
        Log.info("Verifying if invalid login data alert is displayed.");
        waitUntilElementIsVisible(alertInvalidData);
        textAssertion(alertInvalidData.getText(), expAlert);
        Log.info("Invalid login data alert is displayed.");
    }

}
