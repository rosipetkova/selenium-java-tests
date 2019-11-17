package visual;

import com.applitools.eyes.RectangleSize;
import lib.VisualTest;
import pages.LoginPage;
import org.junit.Test;

public class DataDrivenTest extends VisualTest {
    private static final String username = "test";
    private static final String password = "qwerty123";

    @Test
    public void loginWithoutCredentials() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 1", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.fillUsername("");
        loginPage.fillPassword("");
        loginPage.clickLoginButton();

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void loginWithUsernameOnly() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 2", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.fillUsername(username);
        loginPage.fillPassword("");
        loginPage.clickLoginButton();

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void loginWithPasswordOnly() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 3", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.fillUsername("");
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void loginSuccessful() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 4", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

}
