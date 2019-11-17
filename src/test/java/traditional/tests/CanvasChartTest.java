package traditional.tests;

import nu.pattern.OpenCV;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import lib.BaseTest;
import traditional.enums.HsvColors;
import pages.ChartPage;
import pages.LoginPage;
import pages.TablePage;
import traditional.pojos.ChartBarPojo;

import java.io.File;
import java.util.List;

public class CanvasChartTest extends BaseTest {
    private static final String username = "test";
    private static final String password = "qwerty123";
    private String chartBefore;
    private String chartAfter;

    @BeforeClass
    public static void loadOpenCvLibrary() {
        OpenCV.loadLocally();
    }

    @Override
    public void setUp()  {
        super.setUp();
        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        TablePage tablePage = new TablePage(helper);
        Assert.assertNotNull("Table not found!", tablePage.getTransactionsTable());
        tablePage.clickShowExpensesChartBtn();

        ChartPage chartPage = new ChartPage(helper);
        Assert.assertNotNull("Canvas not found!", chartPage.getCanvas());

        chartBefore = chartPage.makeCanvasScreenshot();

        chartPage.clickAddDatasetButton();

        chartAfter = chartPage.makeCanvasScreenshot();
    }

    @Test
    public void verifyBlueBarsCount()  {
        verifyBarsCount(HsvColors.BLUE);
    }

    @Test
    public void verifyBlueBarsSize()  {
        verifyBarsSize(HsvColors.BLUE);
    }

    @Test
    public void verifyRedBarsCount()  {
        verifyBarsCount(HsvColors.RED);
    }

    @Test
    public void verifyRedBarsSize()  {
        verifyBarsSize(HsvColors.RED);
    }

    @Test
    public void verifyYellowBarsCount()  {
        List<ChartBarPojo> yellowBarsBefore = helper.getBarInfoByColor(chartBefore, HsvColors.YELLOW);
        List<ChartBarPojo> yellowBarsAfter = helper.getBarInfoByColor(chartAfter, HsvColors.YELLOW);

        // Check if number of yellow bars is zero before and the different after
        Assert.assertEquals(0, yellowBarsBefore.size());
        Assert.assertNotEquals(yellowBarsBefore.size(), yellowBarsAfter.size());
    }

    @Override
    public void tearDown() {
        super.tearDown();
        File file1 = new File(chartBefore);
        Assert.assertTrue(file1.delete());

        File file2 = new File(chartAfter);
        Assert.assertTrue(file2.delete());
    }

    private void verifyBarsCount(HsvColors color)  {
        List<ChartBarPojo> barsBefore = helper.getBarInfoByColor(chartBefore, color);
        List<ChartBarPojo> barsAfter = helper.getBarInfoByColor(chartAfter, color);

        // Check if number of bars is the same before and after
        Assert.assertNotEquals(0, barsBefore.size());
        Assert.assertEquals(barsBefore.size(), barsAfter.size());
    }

    private void verifyBarsSize(HsvColors color)  {
        List<ChartBarPojo> barsBefore = helper.getBarInfoByColor(chartBefore, color);
        List<ChartBarPojo> barsAfter = helper.getBarInfoByColor(chartAfter, color);

        // Verify bar sizes are not changed
        for (int i = 0; i < barsBefore.size(); i++) {
            Assert.assertTrue(helper.isHeightTheSame(barsBefore.get(i), barsAfter.get(i)));
        }
    }

}
