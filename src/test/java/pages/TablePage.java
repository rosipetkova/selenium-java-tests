package pages;

import lib.Helper;
import traditional.pojos.TableRowPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage {
    private Helper helper;
    private By transactionsTable = By.id("transactionsTable");
    private By tableRows = By.cssSelector("tbody tr");
    private By rowCells = By.cssSelector("td");
    private By amountHeader = By.id("amount");
    private By showExpensesChart = By.id("showExpensesChart");
    private By banner1 = By.cssSelector("#flashSale img");
    private By banner2 = By.cssSelector("#flashSale2 img");

    public TablePage(Helper helper) {
        this.helper = helper;
    }

    public WebElement getTransactionsTable() {
        return helper.waitForElementVisible(transactionsTable);
    }

    private List<WebElement> getTransactionsTableRows() {
        WebElement table = getTransactionsTable();
        return table.findElements(tableRows);
    }

    public List<TableRowPojo> convertRowsToPojo() {
        List<TableRowPojo> result = new ArrayList<>();
        List<WebElement> rows = getTransactionsTableRows();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(rowCells);
            result.add(new TableRowPojo(cells.get(0).getText(), cells.get(1).getText(), cells.get(2).getText(), cells.get(3).getText(), cells.get(4).getText()));
        }
        return result;
    }

    public void sortByAmount() {
        WebElement table = getTransactionsTable();
        WebElement header = table.findElement(amountHeader);
        header.click();
    }

    public WebElement getShowExpensesChartBtn() {
        return helper.waitForElementVisible(showExpensesChart);
    }

    public void clickShowExpensesChartBtn() {
        WebElement btn = getShowExpensesChartBtn();
        btn.click();
    }

    public WebElement getFirstBanner() {
        return helper.waitForElementVisible(banner1);
    }

    public WebElement getSecondBanner() {
        return helper.waitForElementVisible(banner2);
    }
}
