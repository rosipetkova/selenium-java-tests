package visual;

import com.applitools.eyes.RectangleSize;
import lib.VisualTest;
import pages.LoginPage;
import pages.TablePage;
import org.junit.Test;

public class TableSortTest extends VisualTest {
    private static final String username = "test";
    private static final String password = "qwerty123";

    @Test
    public void basicTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Table Sort Test", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        TablePage tablePage = new TablePage(helper);
        tablePage.sortByAmount();

        eyes.checkWindow("Step 3");

        // End the test.
        eyes.closeAsync();
    }
}
