package pages;

import lib.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private Helper helper;
    private By usernameField = By.id("username");
    private By usernameLabel = By.xpath("//label[contains(text(),'Username')]");
    private By passwordField = By.id("password");
    private By passwordLabel = By.xpath("//label[contains(text(),'Password')]");
    private By loginBtn = By.id("log-in");
    private By rememberMe = By.cssSelector("input.form-check-input");
    private By twitterBtn = By.cssSelector("img[src$=\"twitter.png\"]");
    private By facebookBtn = By.cssSelector("img[src$=\"facebook.png\"]");
    private By linkedinBtn = By.cssSelector("img[src$=\"linkedin.png\"]");
    private By heading = By.cssSelector(".auth-header");
    private By alertBox = By.cssSelector(".alert-warning[role=\"alert\"]");

    public LoginPage(Helper helper) {
        this.helper = helper;
    }

    public WebElement getUsernameLabel() {
        return helper.waitForElementVisible(usernameLabel);
    }

    public WebElement getUsernameField() {
        return helper.waitForElementVisible(usernameField);
    }

    public String getUsernameFieldPlaceholder() {
        WebElement field = getUsernameField();
        return field.getAttribute("placeholder");
    }

    public WebElement getPasswordLabel() {
        return helper.waitForElementVisible(passwordLabel);
    }

    public WebElement getPasswordField() {
        return helper.waitForElementVisible(passwordField);
    }

    public String getPasswordFieldPlaceholder() {
        WebElement field = getPasswordField();
        return field.getAttribute("placeholder");
    }

    public WebElement getRememberMe() {
        return helper.waitForElementVisible(rememberMe);
    }

    public WebElement getLoginButton() {
        return helper.waitForElementVisible(loginBtn);
    }

    public String getLoginButtonText() {
        WebElement element = getLoginButton();
        return element.getText();
    }

    public WebElement getTwitterButton() {
        return helper.waitForElementVisible(twitterBtn);
    }

    public WebElement getFacebookButton() {
        return helper.waitForElementVisible(facebookBtn);
    }

    public WebElement getLinkedinButton() {
        return helper.waitForElementVisible(linkedinBtn);
    }

    public WebElement getHeading() {
        return helper.waitForElementVisible(heading);
    }

    public String getHeadingText() {
        WebElement element = getHeading();
        return element.getText();
    }

    public void fillUsername(String value) {
        WebElement field = getUsernameField();
        field.sendKeys(value);
    }

    public void fillPassword(String value) {
        WebElement field = getPasswordField();
        field.sendKeys(value);
    }

    public void clickLoginButton() {
        WebElement btn = getLoginButton();
        btn.click();
    }

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
    }

    public WebElement getAlertBox() {
        return helper.waitForElementVisible(alertBox);
    }

    public String getAlertBoxText() {
        WebElement element = getAlertBox();
        return element.getText();
    }
}
