package traditional.tests;

import traditional.lib.BaseTest;
import traditional.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginPageUiTest extends BaseTest {
    @Test
    public void loginPage() {
        LoginPage page = new LoginPage(helper);
        Assert.assertNotNull("Heading not found!", page.getHeading());
        Assert.assertEquals("Login Form", page.getHeadingText());

        Assert.assertNotNull("Username field not found!", page.getUsernameField());
        Assert.assertEquals("Enter your username", page.getUsernameFieldPlaceholder());
        Assert.assertNotNull("User icon not found!", page.getUserIcon());
        Assert.assertNotNull("Username label not found!", page.getUsernameLabel());

        Assert.assertNotNull("Password field not found!", page.getPasswordField());
        Assert.assertEquals("Enter your password", page.getPasswordFieldPlaceholder());
        Assert.assertNotNull("Fingerprint icon not found!", page.getFingerprintIcon());
        Assert.assertNotNull("Password label not found!", page.getPasswordLabel());

        Assert.assertNotNull("Login button field not found!", page.getLoginButton());
        Assert.assertEquals("Log In", page.getLoginButtonText());

        Assert.assertNotNull("Remember me checkbox not found!", page.getRememberMe());

        Assert.assertNotNull("Twitter button not found!", page.getTwitterButton());
        Assert.assertNotNull("Facebook button not found!", page.getFacebookButton());
        Assert.assertNotNull("Linkedin button not found!", page.getLinkedinButton());
    }
}
