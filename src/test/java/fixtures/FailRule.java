package fixtures;

import driver.wrapper.DriverWrapper;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.*;

public class FailRule extends TestWatcher {


    DriverWrapper driver;

    public FailRule(DriverWrapper driverWrapper) {
        this.driver = driverWrapper;
    }

    @Override
    protected void failed(Throwable e, Description description) {

        String folderName = "screenshots";
        createScreenshotsFolder(folderName);

        byte[] screenshot = driver.takeScreenshot();
        String screenshotName = description.getClassName() + "_" + description.getMethodName() + ".png";
        try {
            saveScreenshot(screenshot, folderName + "/" + screenshotName);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void saveScreenshot(byte[] screenshot, String screenshotPath) throws IOException {
        FileOutputStream out = new FileOutputStream(screenshotPath);
        out.write(screenshot);
    }

    private void createScreenshotsFolder(String folderName) {
        File folderForScreenshots = new File(folderName);
        folderForScreenshots.mkdir();
    }

}