import data.LoginPageData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import static generators.DataGenerator.*;

public class SignupTests extends BaseTest {

    HomePage homePage;
    SignupPage signupPage;
    LoginPage loginPage;

    @Test (dataProvider = "accountType")
    public void signUpWithValidDataAllAccountTypesTest(String accountType) {

        String firstName = getFirstName();
        String lastName = getLastName();
        String phone = getPhone();
        String email = getEmail(firstName.toLowerCase()+"."+lastName.toLowerCase());
        String password = getPassword();

        homePage = new HomePage(getDriver());
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);

        homePage.verifyTopPanelElementsAreDisplayed();
        homePage.goToSignupPage();
        signupPage.verifyIfSignupPageIsDisplayed();
        signupPage.enterDataToRegisterForm("First Name", firstName);
        signupPage.enterDataToRegisterForm("Last Name", lastName);
        signupPage.enterDataToRegisterForm("Phone", phone);
        signupPage.enterDataToRegisterForm("Email", email);
        signupPage.enterDataToRegisterForm("Password", password);
        signupPage.scrollWindowByJavaScript(500);
        signupPage.enterDataToRegisterForm("Account Type", accountType);
        signupPage.clickOnSignupButton();
        loginPage.verifySuccessfulSignupAlert(LoginPageData.ALERT_SUCCESS_SIGNUP_TXT);

    }

    @DataProvider (name = "accountType")
    public Object[][] accountType() {
        return new Object[][] {{"Customer"},{"Supplier"}, {"Agent"}};
    }



}
