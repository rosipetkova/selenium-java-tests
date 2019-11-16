package traditional.tests;

import traditional.lib.BaseTest;
import traditional.pages.LoginPage;
import traditional.pages.TablePage;
import traditional.pojos.TableRowPojo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TableSortTest extends BaseTest {
    private static final String username = "test";
    private static final String password = "qwerty123";
    private TablePage tablePage;

    @Override
    public void setUp() {
        super.setUp();
        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        tablePage = new TablePage(helper);
    }

    @Test
    public void checkSorting() {
        ArrayList<Float> amountsBefore = new ArrayList<>();
        ArrayList<Float> amountsAfter = new ArrayList<>();

        Assert.assertNotNull("Table not found!", tablePage.getTransactionsTable());

        List<TableRowPojo> pojosBeforeSort = tablePage.convertRowsToPojo();
        tablePage.sortByAmount();
        List<TableRowPojo> pojosAfterSort = tablePage.convertRowsToPojo();

        // Check if data is consistent
        for (TableRowPojo pojo1 : pojosAfterSort) {
            amountsAfter.add(pojo1.getCastedAmount());
            amountsBefore.clear();
            for (TableRowPojo pojo2 : pojosBeforeSort) {
                amountsBefore.add(pojo2.getCastedAmount());
                if (pojo1.getCastedAmount().equals(pojo2.getCastedAmount())) {
                    Assert.assertEquals(pojo1.getCategory(), pojo2.getCategory());
                    Assert.assertEquals(pojo1.getDescription(), pojo2.getDescription());
                    Assert.assertEquals(pojo1.getStatus(), pojo2.getStatus());
                    Assert.assertEquals(pojo1.getDate(), pojo2.getDate());
                    Assert.assertEquals(pojo1.getAmount(), pojo2.getAmount());
                }
            }
        }

        // Check if table is not sorted before sort
        Assert.assertFalse(helper.checkSorting(amountsBefore));

        // Check if sorting is OK after sort
        Assert.assertTrue(helper.checkSorting(amountsAfter));
    }
}
