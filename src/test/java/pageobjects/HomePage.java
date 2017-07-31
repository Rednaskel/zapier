package pageobjects;

import constants.Paths;
import constants.Urls;
import driver.wrapper.DriverWrapper;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class HomePage {

    private DriverWrapper wrapper;
    private final By LOGO_BY = By.className("header__logo");
    private final String BENEFIT_CLASS_NAME = "benefit__icon--%s";
    private final String LOGO_PICTURE_PATH = Paths.ELEMENT_IMAGES_FOLDER + "%s/logo.png";

    public HomePage(DriverWrapper driver) throws Exception {
        this.wrapper = driver;
    }

    public boolean isLogoClassPresent() {
        return wrapper.isElementVisible(LOGO_BY);
    }

    public boolean isLogoImagePresent() {
        try {
            //TODO this should be in ScreenshotHelper
            File logoElemImage = wrapper.takeScreenshotOfElement(LOGO_BY);
            String browser = System.getProperty("browser");
            File requiredLogoImage = new File(String.format(LOGO_PICTURE_PATH, browser));
            BufferedImage logoImage = ImageIO.read(logoElemImage);
            BufferedImage requiredImage = ImageIO.read(requiredLogoImage);
            if (logoImage.getData().getHeight() != requiredImage.getData().getHeight()
                || logoImage.getData().getWidth() != requiredImage.getData().getWidth()) {
                System.err.println("Logo element dimensions are incorrect");
                return false;
            }
            DataBuffer logoDataBuffer = logoImage.getData().getDataBuffer();
            DataBuffer requiredDataBuffer = requiredImage.getData().getDataBuffer();
            for(int i = 0; i< logoDataBuffer.getSize(); i++) {
                if (logoDataBuffer.getElem(i) != requiredDataBuffer.getElem(i)) {
                    System.err.println("Images are not the same");
                    return false;
                }
            }
            return true;




        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean isIntegrateIconPresent() {
        return isIconVisible("integrate");
    }

    public boolean isAutomateIconPresent() {
        return isIconVisible("automate");
    }

    public boolean isInnovateIconPresent() {
        return isIconVisible("innovate");
    }

    private boolean isIconVisible(String iconName) {
        By iconBy = By.className(String.format(BENEFIT_CLASS_NAME, iconName));
        return wrapper.isElementVisible(iconBy);
    }

    public void goTo() {
        wrapper.get(Urls.HOME_PAGE);
    }
}
