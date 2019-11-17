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
    private List<TableRowPojo> pojosBeforeSort;
    private List<TableRowPojo> pojosAfterSort;

    @Override
    public void setUp() {
        super.setUp();
        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        TablePage tablePage = new TablePage(helper);
        Assert.assertNotNull("Table not found!", tablePage.getTransactionsTable());

        pojosBeforeSort = tablePage.convertRowsToPojo();
        tablePage.sortByAmount();
        pojosAfterSort = tablePage.convertRowsToPojo();
    }

    @Test
    public void verifySorting() {
        ArrayList<Float> amountsBefore = new ArrayList<>();
        for (TableRowPojo pojo : pojosBeforeSort) {
            amountsBefore.add(pojo.getCastedAmount());
        }

        // Check if table is not sorted before sort
        Assert.assertFalse(helper.checkSorting(amountsBefore));

        ArrayList<Float> amountsAfter = new ArrayList<>();
        for (TableRowPojo pojo : pojosAfterSort) {
            amountsAfter.add(pojo.getCastedAmount());
        }

        // Check if sorting is OK after sort
        Assert.assertTrue(helper.checkSorting(amountsAfter));
    }

    @Test
    public void verifyData() {
        // Check if data is consistent
        for (TableRowPojo pojo1 : pojosAfterSort) {
            for (TableRowPojo pojo2 : pojosBeforeSort) {
                if (pojo1.getCastedAmount().equals(pojo2.getCastedAmount())) {
                    Assert.assertEquals(pojo1.getCategory(), pojo2.getCategory());
                    Assert.assertEquals(pojo1.getDescription(), pojo2.getDescription());
                    Assert.assertEquals(pojo1.getStatus(), pojo2.getStatus());
                    Assert.assertEquals(pojo1.getDate(), pojo2.getDate());
                    Assert.assertEquals(pojo1.getAmount(), pojo2.getAmount());
                }
            }
        }
    }
}
