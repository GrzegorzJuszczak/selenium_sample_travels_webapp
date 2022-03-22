import data.SignupPageData;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import static data.LoginPageData.*;
import static generators.DataGenerator.*;

public class LoginTests extends Base {

    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private AccountPage accountPage;

    @Test
    public void loginWithNoData() {
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToLoginPage();

        loginPage.verifyIfLoginPageIsDisplayed();
        loginPage.clickOnLoginButton();
        loginPage.verifyIfFillFieldAlertIsDisplayed(FILL_FIELD_MESSAGE);


    }

    @Test
    public void loginWithInvalidData() {
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToLoginPage();

        loginPage.verifyIfLoginPageIsDisplayed();
        loginPage.loginToAccount("$$%%@sadad.com", "sdfsdf");
        loginPage.verifyIfInvalidDataAlertIsDisplayed(WRONG_CREDENTIALS_MESSAGE);

    }

    @Test
    public void loginWithValidData() {
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        accountPage = new AccountPage(driver);

        String firstName = getFirstName();
        String lastName = getLastName();
        String phone = getPhone();
        String email = getEmail(firstName.toLowerCase() + "." + lastName.toLowerCase());
        String password = getPassword();

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToSignupPage();                                     //signup every time is necessary because this is test page, and database is cleared
        signupPage.verifyIfSignupPageIsDisplayed();
        signupPage.enterDataToRegisterForm(SignupPageData.FormField.FIRST_NAME, firstName);
        signupPage.enterDataToRegisterForm(SignupPageData.FormField.LAST_NAME, lastName);
        signupPage.enterDataToRegisterForm(SignupPageData.FormField.PHONE, phone);
        signupPage.enterDataToRegisterForm(SignupPageData.FormField.EMAIL, email);
        signupPage.enterDataToRegisterForm(SignupPageData.FormField.PASSWORD, password);
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(ALERT_SUCCESS_SIGNUP_TXT);

        homePage.goToLoginPage();
        loginPage.verifyIfLoginPageIsDisplayed();
        loginPage.loginToAccount(email, password);
        accountPage.verifySuccessfulLogin(firstName);

    }




}
