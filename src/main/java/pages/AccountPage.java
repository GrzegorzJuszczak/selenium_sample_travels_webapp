package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css ="h2 span")
    private WebElement txtHeaderWithName;


    public void verifySuccessfulLogin(String name) {
        Log.info("Verifying if login was successful and welcome message is displayed.");
        waitUntilElementIsVisible(txtHeaderWithName);
        textAssertion(txtHeaderWithName.getText(), name);
        Log.info("Login was successful and welcome message is displayed.");
    }
}
