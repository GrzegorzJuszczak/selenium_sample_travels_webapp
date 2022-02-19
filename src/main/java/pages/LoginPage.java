package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="div[class*='alert-success signup']")
    private WebElement alertSuccessSignup;


    public void verifySuccessfulSignupAlert(String expText) {
        Log.info("Verifying if successful signup alert is present with expected text.");
        waitUntilElementIsVisible(alertSuccessSignup);
        textAssertion(alertSuccessSignup.getText(), expText);
        Log.info("Successful signup alert is present with expected text.");
    }

}
