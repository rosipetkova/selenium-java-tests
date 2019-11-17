package traditional.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import traditional.lib.Helper;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChartPage {
    private Helper helper;
    private By addDatasetBtn = By.id("addDataset");
    private By canvas = By.id("canvas");

    public ChartPage(Helper helper) {
        this.helper = helper;
    }

    public WebElement getCanvas() {
        return helper.waitForElementVisible(canvas);
    }

    public String makeCanvasScreenshot() {
        WebElement img = getCanvas();
        String path;

        // Not a good idea, but could not figure out a way to wait for canvas animation to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            path = System.getProperty("user.dir") + "/tmp/chart_" + System.currentTimeMillis() + ".png";
            File screenshot = ((TakesScreenshot)helper.getDriver()).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            // Get the location of element on the page
            Point point = img.getLocation();

            // Get width and height of the element
            int eleWidth = img.getSize().getWidth();
            int eleHeight = img.getSize().getHeight();

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(path);
            FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (IOException e) {
            e.printStackTrace();
            path = null;
        }

        return path;
    }

    public WebElement getAddDatasetButton() {
        return helper.waitForElementVisible(addDatasetBtn);
    }

    public void clickAddDatasetButton() {
        WebElement btn = getAddDatasetButton();
        btn.click();
    }
}
