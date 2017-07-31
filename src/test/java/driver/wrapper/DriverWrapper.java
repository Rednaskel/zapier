package driver.wrapper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverWrapper {

    private WebDriver driver;
    private final int TIMEOUT = 10;
    private final String CHROME_DRIVER_PATH = "src/test/resources/chromedriver/chromedriver";
    private final String FIREFOX_DRIVER_PATH = "src/test/resources/geckodriver/geckodriver";

    public DriverWrapper() throws Exception {
        String browserName = System.getProperty("browser");
        if(browserName == null) {
            throw new Exception("Browser not specified");
        }
        browserName = browserName.toLowerCase();

        DesiredCapabilities capability = null;
        switch (browserName) {
            case "chrome_local":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                driver.manage().window().setSize(new Dimension(1024,768));

                break;
            case "firefox_local":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                driver = new FirefoxDriver();
                break;
            case "chrome":
                capability = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                break;
            case "firefox":
                capability = DesiredCapabilities.firefox();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                break;
            default:
                throw new Exception("Browser not supported");
        }

    }

    public boolean isElementVisible(By locator) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public void get(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void takeScreenshotOfElement(By locatorBy) throws IOException {
        WebElement logoElem = driver.findElement(By.className("header__logo"));
        Point location = logoElem.getLocation();
        int height = logoElem.getSize().getHeight();
        int width = logoElem.getSize().getWidth();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        String naturalWidth = logoElem.getAttribute("naturalWidth");
        String naturalHeight = logoElem.getAttribute("naturalHeight");
        BufferedImage logoImage = fullImg.getSubimage(location.getY(), location.getX(), width, height);
        System.out.println("locationX: " + location.getX() );
        System.out.println("locationY: " + location.getY());
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("fullImagewidth: " + fullImg.getWidth());
        System.out.println("fullimageHeight: " + fullImg.getHeight());
        System.out.println(naturalWidth);
        System.out.println(naturalHeight);

        ImageIO.write(logoImage, "png", screenshot);

        File screenshotLocation = new File("./bum.png");
        FileUtils.copyFile(screenshot, screenshotLocation);

    }
}
