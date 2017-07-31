package pageobjects;

import constants.Urls;
import driver.wrapper.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage {

    private DriverWrapper wrapper;
    private final By LOGO_BY = By.className("header__logo");
    private final String BENEFIT_CLASS_NAME = "benefit__icon--%s";

    public HomePage(DriverWrapper driver) throws Exception {
        this.wrapper = driver;
    }

    public boolean isLogoClassPresent() {
        try {
            wrapper.takeScreenshotOfElement(By.xpath("xxx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wrapper.isElementVisible(LOGO_BY);
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
