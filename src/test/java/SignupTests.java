import data.LoginPageData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utils.Log;

import static data.SignupPageData.*;
import static generators.DataGenerator.*;

public class SignupTests extends BaseTest {

    HomePage homePage;
    SignupPage signupPage;
    LoginPage loginPage;
    AccountPage accountPage;

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
        signupPage.enterDataToRegisterForm(FIRST_NAME, firstName);
        signupPage.enterDataToRegisterForm(LAST_NAME, lastName);
        signupPage.enterDataToRegisterForm(PHONE, phone);
        signupPage.enterDataToRegisterForm(EMAIL, email);
        signupPage.enterDataToRegisterForm(PASSWORD, password);
        signupPage.enterDataToRegisterForm(ACCOUNT_TYPE, accountType);
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(LoginPageData.ALERT_SUCCESS_SIGNUP_TXT);
        loginPage.loginToAccount(email, password);
        accountPage.verifySuccessfulLogin(firstName);

    }

    @Test
    public void signUpWithInvalidDataTest() {

        homePage = new HomePage(getDriver());
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToSignupPage();
        signupPage.verifyIfSignupPageIsDisplayed();
        signupPage.enterDataToRegisterForm(FIRST_NAME, "%#$#");
        signupPage.enterDataToRegisterForm(LAST_NAME, "$%^$%^");
        signupPage.enterDataToRegisterForm(PHONE, "ghgh");
        signupPage.enterDataToRegisterForm(EMAIL, "%%^^6g");
        signupPage.enterDataToRegisterForm(PASSWORD, "1");
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(LoginPageData.ALERT_SUCCESS_SIGNUP_TXT);
        Log.fatal("Form is not secured. Invalid data can be entered.");

    }

    @DataProvider(name = "accountType")
    public Object[][] accountType() {
        return new Object[][]{{CUSTOMER}, {SUPPLIER}, {AGENT}};
    }


}
