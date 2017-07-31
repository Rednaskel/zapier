package driver.wrapper;

import fixtures.ScreenshotHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public File takeScreenshotOfElement(By locatorBy) throws IOException {
        WebElement logoElem = driver.findElement(locatorBy);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return ScreenshotHelper.getElementPicture(logoElem, screenshot);
    }

}
