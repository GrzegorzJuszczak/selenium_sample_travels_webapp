package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class*='alert-success signup']")
    private WebElement alertSuccessSignup;

    @FindBy(name = "email")
    private WebElement inpEmail;

    @FindBy(name = "password")
    private WebElement inpPassword;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement btnLogin;




    public void verifySuccessfulSignupAlert(String expText) {
        Log.info("Verifying if successful signup alert is present with expected text.");
        waitUntilElementIsVisible(alertSuccessSignup);
        textAssertion(alertSuccessSignup.getText(), expText);
        Log.info("Successful signup alert is present with expected text.");
    }

    public void loginToAccount(String email, String password) {
        Log.info("Login into account with email '"+email+"'.");
        waitUntilElementIsVisible(inpEmail);
        inpEmail.sendKeys(email);
        waitUntilElementIsVisible(inpPassword);
        inpPassword.sendKeys(password);
        Log.info("Data was entered, clicking on 'Login' button.");
        waitUntilElementIsClickable(btnLogin);
        btnLogin.click();
        Log.info("Clicked on 'Login' button.");
    }

}
