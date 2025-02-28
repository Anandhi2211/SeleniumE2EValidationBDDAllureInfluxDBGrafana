package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.webTesting.pages.LoginPage;

public class LoginPageStepDefinition {
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("Open the url for Login Page")
    public void open_the_url_for_login_page() {
        this.loginPage.open("https://www.saucedemo.com/v1/");
        System.out.println("Open the url for Login Page");
    }
    @Then("Verify username fields is present")
    public void verify_username_fields_is_present() {
        Assert.assertTrue("UserName Field is not there",this.loginPage.getUserName().isDisplayed());
        System.out.println("verify_username_fields_is_present");
    }
    @And("Verify password field is present")
    public void verify_password_field_is_present() {
        Assert.assertTrue("Password Field is not there",this.loginPage.getPassword().isDisplayed());
        System.out.println("Verify password field is present");
    }
    @And("Verify Login Button is present")
    public void verifyLoginButtonIsPresent() {
        Assert.assertTrue("Password Field is not there",this.loginPage.getLoginBtn().isDisplayed());
        System.out.println("Verify Login Button is present");
    }
    @When("Enter the {string} and {string}")
    public void enterTheAnd(String username, String password) {
        this.loginPage.enterUsername(username);
        this.loginPage.enterPassword(password);
    }
    @And("Click login Button")
    public void clickLoginButton() {
        this.loginPage.clickLoginBtn();
    }
    @Then("Verify the user is directed to product Page")
    public void verifyTheUserIsDirectedToProductPage() {

        String currentUrl = this.driver.getCurrentUrl();
        String title = this.driver.getTitle();
        Assert.assertTrue("Not Product Page",currentUrl.toLowerCase().contains("inventory"));
        Assert.assertTrue("Not the Correct Title",title.contains("Swag Labs"));
    }

    @Then("Verify the error message")
    public void verifyTheErrorMessage() {
        String error = this.loginPage.getErrorMessage().getText();
        Assert.assertTrue("Not Correct Error Message",error.contains("Username and password do not match"));
        System.out.println("Error Message "+ error);
    }
}
