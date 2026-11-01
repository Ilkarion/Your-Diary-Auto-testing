import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignPageTests extends BaseTest {


    @Test
    public void verify_Sign_Page_UI_elements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        SignPage signPage = new SignPage(driver);
        openPage("/sign");
        wait.until(ExpectedConditions.urlContains("/sign"));

        // Check if the "Welcome Back" header is displayed.
        Assert.assertTrue(signPage.isHeaderDisplayed(), "Header 'Welcome Back' should be displayed");

        // Check if the Email and Password input fields are displayed.
        Assert.assertTrue(signPage.isEmailFieldDisplayed(), "Email field should be displayed");
        Assert.assertTrue(signPage.isPasswordFieldDisplayed(), "Password field should be displayed");

        // Check if the "Launch Into Space" button is displayed.
        Assert.assertTrue(signPage.isSubmitButtonDisplayed(), "Submit button should be displayed");

        // Check if the "Need an account? Sign up" link is displayed.
        Assert.assertTrue(signPage.isSignUpLinkDisplayed(), "Sign up link should be displayed");
    }

    @Test(priority = 1)
    public void empty_fields_validation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        SignPage signPage = new SignPage(driver);
        final By error_address = By.xpath("//div[@class=\"wrapperErrorMsgs\"]/div/span");

        openPage("/sign");
        wait.until(ExpectedConditions.urlContains("/sign"));

        // Leave Email and Password fields empty.
        signPage.clearEmailField();
        signPage.clearPasswordField();

        // Click the "Launch Into Space" button.
        signPage.clickSubmitButton();

        // Verify that the form is not submitted and validation errors are triggered.
        wait.until(ExpectedConditions.visibilityOfElementLocated(error_address));
        Assert.assertTrue(signPage.isValidationErrorDisplayed(""), "Validation error should be triggered for empty fields");
        Assert.assertTrue(driver.getCurrentUrl().contains("/sign"), "User should remain on the Sign page");
    }

    @Test(priority = 2)
    public void invalid_email_format_validation() {
        SignPage signPage = new SignPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        openPage("/sign");


        // Enter an invalid email format into the Email field.
        signPage.enterEmail("invalid-email-format");

        // Enter a valid password into the Password field.
        signPage.enterPassword("passworD_2006");

        // Click the "Launch Into Space" button.
        signPage.clickSubmitButton();

        // Verify that an appropriate email error message appears.
        Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", signPage.getEmailField());
        Assert.assertFalse(isValid, "Error message should appear for invalid email format");
    }

    @Test(priority = 3)
    public void invalid_password_format_validation() {
        SignPage signPage = new SignPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        openPage("/sign");
        final By error_address = By.xpath("//div[@class=\"wrapperErrorMsgs\"]/div/span");


        // Enter valid Email format.
        signPage.enterEmail("mastercomponents6@gmail.com");

        // Enter an invalid password.
        signPage.enterPassword("12356D_204");

        // Click the "Launch Into Space" button.
        signPage.clickSubmitButton();

        // Verify that an appropriate email error message appears.
        wait.until(ExpectedConditions.visibilityOfElementLocated(error_address));
        Assert.assertTrue(signPage.isValidationErrorDisplayed("password"), "Error message should appear for invalid password format");
    }

    @Test(priority = 4)
    public void Navigate_to_Sign_up_page() {
        SignPage signPage = new SignPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final By usernameInput_address = By.xpath("//input[@placeholder=\"Choose a username\"]");
        openPage("/sign");

        // Click the "Sign up" link at the bottom of the form.
        signPage.clickSignUpLink();

        // Verify that the user is redirected to the Sign-Up page.
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput_address));
        WebElement usernameInput = driver.findElement(usernameInput_address);
        boolean isRedirected = wait.until(ExpectedConditions.urlContains("/sign")) && usernameInput.isDisplayed();
        Assert.assertTrue(isRedirected, "User should be redirected to the Sign-Up page");
    }
}