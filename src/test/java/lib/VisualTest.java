package lib;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.junit.BeforeClass;

abstract public class VisualTest extends BaseTest {
    protected static BatchInfo batch;
    protected EyesRunner runner;
    protected Eyes eyes;

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
