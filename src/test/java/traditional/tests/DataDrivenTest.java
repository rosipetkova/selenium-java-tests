package traditional.tests;

import traditional.lib.BaseTest;
import traditional.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

public class DataDrivenTest extends BaseTest {
    private static final String username = "test";
    private static final String password = "qwerty123";
    private LoginPage loginPage;

    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(helper);
    }

    @Test
    public void loginWithoutCredentials() {
        loginPage.fillUsername("");
        loginPage.fillPassword("");
        loginPage.clickLoginButton();

        Assert.assertNotNull("Alert box not found!", loginPage.getAlertBox());
        Assert.assertEquals("Both Username and Password must be present", loginPage.getAlertBoxText());
    }

    @Test
    public void loginWithUsernameOnly() {
        loginPage.fillUsername(username);
        loginPage.fillPassword("");
        loginPage.clickLoginButton();

        Assert.assertNotNull("Alert box not found!", loginPage.getAlertBox());
        Assert.assertEquals("Password must be present", loginPage.getAlertBoxText());
    }

    @Test
    public void loginWithPasswordOnly() {
        loginPage.fillUsername("");
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertNotNull("Alert box not found!", loginPage.getAlertBox());
        Assert.assertEquals("Username must be present", loginPage.getAlertBoxText());
    }

    @Test
    public void loginSuccessful() {
        loginPage.login(username, password);

        Assert.assertNull(loginPage.getAlertBox());
        Assert.assertNotEquals(baseUrl, helper.getDriver().getCurrentUrl());
    }

}
