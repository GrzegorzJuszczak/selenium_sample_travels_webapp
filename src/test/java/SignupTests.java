import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import static data.LoginPageData.ALERT_SUCCESS_SIGNUP_TXT;
import static data.SignupPageData.*;
import static generators.DataGenerator.*;

public class SignupTests extends Base {

    private HomePage homePage;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @Test(dataProvider = "accountType")
    public void signUpAndLoginWithValidDataAllAccountTypesTest(String accountType) {

        String firstName = getFirstName();
        String lastName = getLastName();
        String phone = getPhone();
        String email = getEmail(firstName.toLowerCase() + "." + lastName.toLowerCase());
        String password = getPassword();

        homePage = new HomePage(getDriver());
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToSignupPage();
        signupPage.verifyIfSignupPageIsDisplayed();
        signupPage.enterDataToRegisterForm(FormField.FIRST_NAME, firstName);
        signupPage.enterDataToRegisterForm(FormField.LAST_NAME, lastName);
        signupPage.enterDataToRegisterForm(FormField.PHONE, phone);
        signupPage.enterDataToRegisterForm(FormField.EMAIL, email);
        signupPage.enterDataToRegisterForm(FormField.PASSWORD, password);
        signupPage.enterDataToRegisterForm(FormField.ACCOUNT_TYPE, accountType);
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(ALERT_SUCCESS_SIGNUP_TXT);
        loginPage.loginToAccount(email, password);
        accountPage.verifySuccessfulLogin(firstName);

    }

    @Test
    public void signUpWithEmailExistInDataBaseTest() {

        String firstName = getFirstName();
        String lastName = getLastName();
        String phone = getPhone();
        String email = getEmail(firstName.toLowerCase() + "." + lastName.toLowerCase());
        String password = getPassword();


        homePage = new HomePage(getDriver());
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToSignupPage();
        signupPage.verifyIfSignupPageIsDisplayed();
        signupPage.enterDataToRegisterForm(FormField.FIRST_NAME, firstName);
        signupPage.enterDataToRegisterForm(FormField.LAST_NAME, lastName);
        signupPage.enterDataToRegisterForm(FormField.PHONE, phone);
        signupPage.enterDataToRegisterForm(FormField.EMAIL, email);
        signupPage.enterDataToRegisterForm(FormField.PASSWORD, password);
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(ALERT_SUCCESS_SIGNUP_TXT);

        homePage.goToSignupPage();
        signupPage.enterDataToRegisterForm(FormField.FIRST_NAME, firstName);
        signupPage.enterDataToRegisterForm(FormField.LAST_NAME, lastName);
        signupPage.enterDataToRegisterForm(FormField.PHONE, phone);
        signupPage.enterDataToRegisterForm(FormField.EMAIL, email);
        signupPage.enterDataToRegisterForm(FormField.PASSWORD, password);
        signupPage.clickOnSignupButton();
        signupPage.verifyIfEmailExistAlertIsDisplayed(ALERT_EMAIL_EXIST);

    }

    @DataProvider(name = "accountType")
    public Object[][] accountType() {
        return new Object[][]{{CUSTOMER}, {SUPPLIER}, {AGENT}};
    }


}
