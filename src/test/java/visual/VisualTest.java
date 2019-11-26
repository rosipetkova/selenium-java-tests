package visual;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import lib.BaseTest;
import lib.Helper;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.ChartPage;
import pages.LoginPage;
import pages.TablePage;

public class VisualTest extends BaseTest {
    private static BatchInfo batch;
    private EyesRunner runner;
    private Eyes eyes;
    private static final String username = "test";
    private static final String password = "qwerty123";
    private static final Integer viewportWidth = 800;
    private static final Integer viewportHeight = 600;

    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("Hackaton");
    }

    @Override
    public void setUp() {
        helper = new Helper();

        // Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK
        eyes = new Eyes(runner);

        eyes.setForceFullPageScreenshot(true);

        // Set your personal Applitols API Key from your environment variables.
        eyes.setApiKey(Helper.getConfigProperty("APPLITOOLS_API_KEY"));

        // set batch name
        eyes.setBatch(batch);
    }

    @Test
    public void loginPageUiTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Login Page UI Test", new RectangleSize(viewportWidth, viewportHeight));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void dataDrivenTestLoginWithoutCredentials() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 1", new RectangleSize(viewportWidth, viewportHeight));

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
    public void dataDrivenTestLoginWithUsernameOnly() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 2", new RectangleSize(viewportWidth, viewportHeight));

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
    public void dataDrivenTestLoginWithPasswordOnly() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 3", new RectangleSize(viewportWidth, viewportHeight));

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
    public void dataDrivenTestLoginSuccessful() {
        eyes.open(helper.getDriver(), "Hackaton App", "Data Driven Test 4", new RectangleSize(viewportWidth, viewportHeight));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

    @Test
    public void tableSortTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Table Sort Test", new RectangleSize(viewportWidth, viewportHeight));

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

    @Test
    public void canvasChartTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Canvas Chart Test", new RectangleSize(viewportWidth, viewportHeight));

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

    @Test
    public void dynamicContentTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Dynamic Content Test", new RectangleSize(viewportWidth, viewportHeight));

        helper.getDriver().get(baseUrl + "?showAd=true");

        eyes.checkWindow("Step 1");

        LoginPage loginPage = new LoginPage(helper);
        loginPage.login(username, password);

        eyes.checkWindow("Step 2");

        // End the test.
        eyes.closeAsync();
    }

    @Override
    public void tearDown() {
        super.tearDown();

        // If the test was aborted before eyes.close was called, ends the test as
        // aborted.
        eyes.abortIfNotClosed();

        // Wait and collect all test results
        TestResultsSummary allTestResults = runner.getAllTestResults();

        // Print results
        System.out.println(allTestResults);
    }
}
