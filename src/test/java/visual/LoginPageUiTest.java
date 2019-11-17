package visual;

import com.applitools.eyes.RectangleSize;
import lib.VisualTest;
import org.junit.Test;

public class LoginPageUiTest extends VisualTest {

    @Test
    public void basicTest() {
        eyes.open(helper.getDriver(), "Hackaton App", "Login Page UI Test", new RectangleSize(1024, 800));

        helper.getDriver().get(baseUrl);

        eyes.checkWindow("Step 1");

        // End the test.
        eyes.closeAsync();
    }
}
