package traditional.lib;

import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected Helper helper;
    protected static final String baseUrl = System.getenv("TEST_APP_URL");

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
