package pageobjects;

import driver.wrapper.DriverWrapper;
import org.openqa.selenium.By;

public class LoginForm {

    private DriverWrapper wrapper;
    private final String loginFormXpath = "//form[@class='signup-form']";
    private final String loginFieldXpathTemplate = "//input[@name='%s']";
    //[1] so it takes first validation message. Even if the first one is not from checked field, assertion
    //should fail on comparison of text
    private final String validationMessageXpath = "/following-sibling::p[contains(@class,'error')][1]";
    private final String firstNameXpath =
            String.format(loginFormXpath+loginFieldXpathTemplate, "first_name");
    private final String lastNameXpath =
            String.format(loginFormXpath+loginFieldXpathTemplate, "last_name");
    private final String emailXpath =
            String.format(loginFormXpath+loginFieldXpathTemplate, "email");
    private final String passwordXpath =
            String.format(loginFormXpath+loginFieldXpathTemplate, "password1");

    public LoginForm(DriverWrapper driver) throws Exception {
        this.wrapper = driver;
    }

    public void typeFirstName(String firstName) {
        wrapper.type(By.xpath(firstNameXpath), firstName);
    }

    public void typeLastName(String lastName) {
        wrapper.type(By.xpath(lastNameXpath), lastName);
    }

    public void typeEmail(String email) {
        wrapper.type(By.xpath(emailXpath), email);
    }

    public void typePassword(String password) {
        wrapper.type(By.xpath(passwordXpath), password);
    }

    public String getFirstNameValidationMessage() {
        return wrapper.getElementText(By.xpath(firstNameXpath + validationMessageXpath));
    }

    public String getLastNameValidationMessage() {
        return wrapper.getElementText(By.xpath(lastNameXpath + validationMessageXpath));
    }

    public String getEmailValidationMessage() {
        return wrapper.getElementText(By.xpath(emailXpath + validationMessageXpath));
    }

    public String getPasswordValidationMessage() {
        return wrapper.getElementText(By.xpath(passwordXpath + validationMessageXpath));
    }

    public void clickSignUpButton() {
        wrapper
    }

}
