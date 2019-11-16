package traditional.tests;

import nu.pattern.OpenCV;
import org.junit.Assert;
import org.junit.Test;
import traditional.lib.BaseTest;
import traditional.enums.HsvColors;
import traditional.pages.ChartPage;
import traditional.pages.LoginPage;
import traditional.pages.TablePage;
import traditional.pojos.ChartBarPojo;
import java.util.List;

public class CanvasChartTest extends BaseTest {
    private static final String username = "test";
    private static final String password = "qwerty123";
    private ChartPage chartPage;

    @Override
    public void setUp() {
        super.setUp();
        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        TablePage tablePage = new TablePage(helper);
        Assert.assertNotNull("Table not found!", tablePage.getTransactionsTable());
        tablePage.clickShowExpensesChartBtn();

        chartPage = new ChartPage(helper);

        OpenCV.loadLocally();
    }

    @Test
    public void validateChartData() throws InterruptedException {
        Assert.assertNotNull("Canvas not found!", chartPage.getCanvas());

        String chartBefore = chartPage.makeCanvasScreenshot();

        chartPage.clickAddDatasetButton();

        // Not a good idea, but could not figure out a way to wait for canvas animation to finish
        Thread.sleep(2000);

        String chartAfter = chartPage.makeCanvasScreenshot();

        List<ChartBarPojo> blueBarsBefore = helper.getBarInfoByColor(chartBefore, HsvColors.BLUE);
        List<ChartBarPojo> blueBarsAfter = helper.getBarInfoByColor(chartAfter, HsvColors.BLUE);
        // Check if number of blue bars is the same before and after
        Assert.assertNotEquals(0, blueBarsBefore.size());
        Assert.assertEquals(blueBarsBefore.size(), blueBarsAfter.size());

        // Verify blue bar sizes are not changed
        for (int i = 0; i < blueBarsBefore.size(); i++) {
            Assert.assertTrue(helper.isHeightTheSame(blueBarsBefore.get(i), blueBarsAfter.get(i)));
        }

        List<ChartBarPojo> redBarsBefore = helper.getBarInfoByColor(chartBefore, HsvColors.RED);
        List<ChartBarPojo> redBarsAfter = helper.getBarInfoByColor(chartAfter, HsvColors.RED);
        // Check if number of red bars is the same before and after
        Assert.assertNotEquals(0, redBarsBefore.size());
        Assert.assertEquals(redBarsBefore.size(), redBarsAfter.size());

        // Verify red bar sizes are not changed
        for (int i = 0; i < redBarsBefore.size(); i++) {
            Assert.assertTrue(helper.isHeightTheSame(redBarsBefore.get(i), redBarsAfter.get(i)));
        }

        List<ChartBarPojo> yellowBarsBefore = helper.getBarInfoByColor(chartBefore, HsvColors.YELLOW);
        List<ChartBarPojo> yellowBarsAfter = helper.getBarInfoByColor(chartAfter, HsvColors.YELLOW);
        // Check if number of yellow bars is zero before and the same as blue bars after
        Assert.assertEquals(0, yellowBarsBefore.size());
        Assert.assertNotEquals(yellowBarsBefore.size(), yellowBarsAfter.size());
        Assert.assertEquals(blueBarsAfter.size(), yellowBarsAfter.size());
    }

}
