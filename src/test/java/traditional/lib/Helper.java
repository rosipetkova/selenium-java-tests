package traditional.lib;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opencv.core.Point;
import traditional.enums.HsvColors;
import traditional.pojos.ChartBarPojo;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    private WebDriver webDriver;
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final int waitTimeout = 5;

    public Helper() {
        String chromeDriverPath = System.getProperty("user.dir") + "/bin/";
        if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 ) {
            // Linux
            chromeDriverPath += "linux/chromedriver";
        } else if (OS.contains("win")) {
            // Win
            chromeDriverPath += "win/chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void shutDownDriver() {
        webDriver.quit();
    }

    public WebElement waitForElementVisible(By locator) {
        WebElement element;
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, waitTimeout);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            element = null;
        }
        return element;
    }

    public boolean checkSorting(ArrayList<Float> arr) {
        boolean isSorted = true;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i - 1) > arr.get(i)) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public List<ChartBarPojo> getBarInfoByColor(String imgPath, HsvColors color) {
        Mat img = Imgcodecs.imread(imgPath, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2HSV);
        Mat tmp = img.clone();
        Core.inRange(img, color.getLower(), color.getUpper(), tmp);
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(tmp, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        List<ChartBarPojo> result = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            List<Point> shape = contour.toList();
            result.add(new ChartBarPojo(shape, calculateHeight(shape), color));
        }
        return result;
    }

    private double calculateHeight(List<Point> shape) {
        double minY = 999999;
        double maxY = 0;
        for (Point pt : shape) {
            if (minY > pt.y) {
                minY = pt.y;
            }
            if (maxY < pt.y) {
                maxY = pt.y;
            }
        }
        return maxY - minY;
    }

    public boolean isHeightTheSame(ChartBarPojo before, ChartBarPojo after) {
        // I allow 3 pixels tolerance as opencv shape detection is not accurate enough
        System.out.println(before.getHeight() + " -- " + after.getHeight());
        return before.getHeight() >= (after.getHeight() - 3) && before.getHeight() <= (after.getHeight() + 3);
    }
}
