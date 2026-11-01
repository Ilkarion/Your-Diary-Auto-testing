import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class SignPage {
    private WebDriver driver;

    // Locators
    private final By header_welcome_address = By.xpath("//h2");
    private final By email_field_address = By.xpath("//input[@type=\"email\"]");
    private final By password_field_address = By.xpath("//input[@type=\"password\"]");
    private final By submit_button_address = By.xpath("//button[@type=\"submit\"]");
    private final By signup_link_address = By.xpath("//span[text()=\"Sign up\"]");
    private final By validation_error_address = By.xpath("//div[@class=\"wrapperErrorMsgs\"]/div/span");

    // Constructor
    public SignPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public WebElement getHeaderWelcome() {
        return driver.findElement(header_welcome_address);
    }

    public WebElement getEmailField() {
        return driver.findElement(email_field_address);
    }

    public WebElement getPasswordField() {
        return driver.findElement(password_field_address);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submit_button_address);
    }

    public WebElement getSignupLink() {
        return driver.findElement(signup_link_address);
    }

    // Actions & Check methods
    public boolean isHeaderDisplayed() {
        return getHeaderWelcome().isDisplayed();
    }

    public boolean isEmailFieldDisplayed() {
        return getEmailField().isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return getPasswordField().isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return getSubmitButton().isDisplayed();
    }

    public boolean isSignUpLinkDisplayed() {
        return getSignupLink().isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement emailField = getEmailField();
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = getPasswordField();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void clickSubmitButton() {
        getSubmitButton().click();
    }

    public void clickSignUpLink() {
        getSignupLink().click();
    }

    public boolean isValidationErrorDisplayed(String type) {
        if (Objects.equals(type, "email")) {
            WebElement errorMsg = driver.findElement(validation_error_address);
            return Objects.equals(errorMsg.getText(), "Email is required");
        }else if(Objects.equals(type, "password")){
            WebElement errorMsg = driver.findElement(validation_error_address);
            return Objects.equals(errorMsg.getText(), "Register or invalid email/password. Or database was temporary stopped.");
        }else {
            return driver.findElement(validation_error_address).isDisplayed();
        }
    }
}