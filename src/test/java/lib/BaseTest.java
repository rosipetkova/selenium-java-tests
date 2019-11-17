package lib;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
abstract public class BaseTest {
    protected Helper helper;
    protected static final String baseUrl = Helper.getConfigProperty("TEST_APP_URL");

    @Before
    public void setUp() {
        helper = new Helper();
        helper.getDriver().get(baseUrl);
    }

    @After
    public void tearDown() {
        helper.shutDownDriver();
    }
}
