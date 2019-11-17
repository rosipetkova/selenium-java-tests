package visual;

import com.applitools.eyes.RectangleSize;
import org.junit.Test;
import lib.VisualTest;
import pages.LoginPage;

public class DynamicContentTest extends VisualTest {

    private static final String username = "test";
    private static final String password = "qwerty123";

    @Test
    public void basicTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Dynamic Content Test", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl + "?showAd=true");

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }
}
