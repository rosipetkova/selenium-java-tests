package traditional.tests;

import traditional.lib.BaseTest;
import traditional.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginPageUiTest extends BaseTest {
    private LoginPage loginPage;

    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(helper);
    }

    @Test
    public void verifyHeading() {
        Assert.assertNotNull("Heading not found!", loginPage.getHeading());
        Assert.assertEquals("Login Form", loginPage.getHeadingText());
    }

    @Test
    public void verifyLoginButton() {
        Assert.assertNotNull("Login button field not found!", loginPage.getLoginButton());
        Assert.assertEquals("Log In", loginPage.getLoginButtonText());
    }

    @Test
    public void verifyRememberMe() {
        Assert.assertNotNull("Remember me checkbox not found!", loginPage.getRememberMe());
    }

    @Test
    public void verifyTwitterButton() {
        Assert.assertNotNull("Twitter button not found!", loginPage.getTwitterButton());
    }

    @Test
    public void verifyFacebookButton() {
        Assert.assertNotNull("Facebook button not found!", loginPage.getFacebookButton());
    }

    @Test
    public void verifyLinkedinButton() {
        Assert.assertNotNull("Linkedin button not found!", loginPage.getLinkedinButton());
    }

    @Test
    public void verifyUsernameField() {
        Assert.assertNotNull("Username field not found!", loginPage.getUsernameField());
    }

    @Test
    public void verifyUsernamePlaceholder() {
        Assert.assertEquals("Enter your username", loginPage.getUsernameFieldPlaceholder());
    }

    @Test
    public void verifyUsernameLabel() {
        Assert.assertNotNull("Username label not found!", loginPage.getUsernameLabel());
    }

    @Test
    public void verifyPasswordField() {
        Assert.assertNotNull("Password field not found!", loginPage.getPasswordField());
    }

    @Test
    public void verifyPasswordPlaceholder() {
        Assert.assertEquals("Enter your password", loginPage.getPasswordFieldPlaceholder());
    }

    @Test
    public void verifyPasswordLabel() {
        Assert.assertNotNull("Password label not found!", loginPage.getPasswordLabel());
    }
}
