package traditional.tests;

import org.junit.Assert;
import org.junit.Test;
import traditional.lib.BaseTest;
import traditional.lib.Helper;
import traditional.pages.LoginPage;
import traditional.pages.TablePage;

public class DynamicContentTest extends BaseTest {
    private static final String username = "test";
    private static final String password = "qwerty123";
    private TablePage tablePage;

    @Override
    public void setUp() {
        helper = new Helper();
        helper.getDriver().get(baseUrl + "?showAd=true");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        tablePage = new TablePage(helper);
    }

    @Test
    public void verifyAds() {
        Assert.assertNotNull("First banner not found!", tablePage.getFirstBanner());
        Assert.assertNotNull("Second banner not found!", tablePage.getSecondBanner());
    }
}
