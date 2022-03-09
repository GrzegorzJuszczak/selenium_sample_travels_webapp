package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import static data.SignupPageData.*;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//h5[text()='Signup']")
    private WebElement headerSignup;

    @FindBy(xpath="//p[text()='Please enter all credentials to signup']")
    private WebElement txtUnderHeader;

    @FindBy(css="input[name='first_name']")
    private WebElement inpFirstName;

    @FindBy(css="input[name='last_name']")
    private WebElement inpLastName;

    @FindBy(css="input[name='phone']")
    private WebElement inpPhone;

    @FindBy(css="input[name='email']")
    private WebElement inpEmail;

    @FindBy(css="input[name='password']")
    private WebElement inpPassword;

    @FindBy(id="select2-account_type-container")
    private WebElement inpAccountType;

    @FindBy(xpath="//span[text()='Signup']")
    private WebElement btnSignup;



    public void verifyIfSignupPageIsDisplayed() {
        Log.info("Verifying if signup page is displayed.");
        waitUntilElementIsVisible(headerSignup);
        waitUntilElementIsVisible(txtUnderHeader);
        Log.info("Signup page is displayed.");
    }

    public void enterData(String field, WebElement inp, String value) {
        Log.info("Entering '" + field + "' with value '" + value + "' to register form.");
        waitUntilElementIsVisible(inp);
        inp.sendKeys(value);
        Log.info("Entered '" + value + "' into '" + field + "' field.");
    }

    public void enterDataToRegisterForm(String field, String data) {
        switch (field) {
            case FIRST_NAME:
                enterData(field, inpFirstName, data);
                break;
            case LAST_NAME:
                enterData(field, inpLastName, data);
                break;
            case PHONE:
                enterData(field, inpPhone, data);
                break;
            case EMAIL:
                enterData(field, inpEmail, data);
                break;
            case PASSWORD:
                enterData(field, inpPassword, data);
                break;
            case ACCOUNT_TYPE:
                chooseValueFromSelectList(field, inpAccountType, data);
                break;
            default:
                Log.warn("Insert correct data.");
        }
    }

    public void chooseValueFromSelectList(String field, WebElement inp, String value) {
        Log.info("Choosing '"+field+"' with value '" + value + "' from select list.");
        waitUntilElementIsClickable(inp);
        moveToElement(inp);
        inp.click();
        WebElement el = driver.findElement(By.xpath("//li[text()='"+value+"']"));
        waitUntilElementIsClickable(el);
        el.click();
        Log.info("'"+field+"' with value '" + value + "' was chosen from list.");
    }

    public void clickOnSignupButton() {
        waitUntilElementIsClickable(btnSignup);
        moveToElement(btnSignup);
        btnSignup.click();
    }



}
