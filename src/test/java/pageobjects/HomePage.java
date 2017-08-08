package pageobjects;

import constants.Paths;
import constants.Urls;
import driver.wrapper.DriverWrapper;
import fixtures.ScreenshotHelper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

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

    public boolean isLogoImagePresent() throws IOException {
        File logoElemImage = wrapper.takeScreenshotOfElement(LOGO_BY);
        String browser = System.getProperty("browser");
        File requiredLogoImage = new File(String.format(LOGO_PICTURE_PATH, browser));
        return ScreenshotHelper.compareScreenshots(logoElemImage, requiredLogoImage);
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
