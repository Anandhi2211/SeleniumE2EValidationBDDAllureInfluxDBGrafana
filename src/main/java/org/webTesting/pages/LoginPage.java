package org.webTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage  {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
// Page Object Locators
    @FindBy(css = "#user-name")
    private WebElement userName;
    @FindBy(css = "#password")
    private WebElement password;
    public WebElement getUserName() {
        return userName;
    }
    public WebElement getPassword() {
        return password;
    }
    public WebElement getLoginBtn() {
        return loginBtn;
    }
    @FindBy(css ="#login-button" )
    private WebElement loginBtn;
    @FindBy(xpath = "//button[@class = \"error-button\"]//parent::h3[@data-test=\"error\"]")
    private WebElement errorMessage;

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void enterUsername(String username){
        userName.sendKeys(username);
    }
    public void enterPassword(String password){
        this.password.sendKeys(password);
    }
    public void clickLoginBtn(){
        loginBtn.click();
    }
    public void open(String url) {
        this.driver.get(url);
    }
}
