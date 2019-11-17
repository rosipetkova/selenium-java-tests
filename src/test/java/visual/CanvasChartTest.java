package visual;

import com.applitools.eyes.RectangleSize;
import org.junit.Test;
import lib.VisualTest;
import pages.ChartPage;
import pages.LoginPage;
import pages.TablePage;

public class CanvasChartTest extends VisualTest {
    private static final String username = "test";
    private static final String password = "qwerty123";

    @Test
    public void basicTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Canvas Chart Test", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        TablePage tablePage = new TablePage(helper);
        tablePage.clickShowExpensesChartBtn();

        eyes.checkWindow("Step 3");

        ChartPage chartPage = new ChartPage(helper);
        chartPage.clickAddDatasetButton();

        eyes.checkWindow("Step 4");

        // End the test.
        eyes.closeAsync();
    }

}
