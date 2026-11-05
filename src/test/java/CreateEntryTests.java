import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class CreateEntryTests extends BaseTest {
    public static final String MY_GMAIL = System.getenv("MY_GMAIL");
    public static final String MY_GMAIL_PASSWORD = System.getenv("MY_GMAIL_PASSWORD");

    //Helpers for fast sign in
    public void visibilityOfElementLocated(String address, String assertMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(address)));
        Assert.assertTrue(element.isDisplayed(), assertMessage);
    }

    @Test
    public void sign_in_and_navigate_to_user_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        SignPage signPage = new SignPage(driver);
        openPage("/sign");
        wait.until(ExpectedConditions.urlContains("/sign"));

        // Sign in with valid email and password
        signPage.enterEmail(MY_GMAIL);
        signPage.enterPassword(MY_GMAIL_PASSWORD);
        signPage.clickSubmitButton();

        // Verify correct redirection through /checkAuthUser page to the /user page
        wait.until(ExpectedConditions.urlContains("/checkAuthUser"));
        wait.until(ExpectedConditions.urlContains("/user"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/user"), "User should be redirected to /user page");
    }

    @Test(priority = 1)
    public void navigate_to_new_entry_page() {
        sign_in_and_navigate_to_user_page();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        CreateEntryPage createEntryPage = new CreateEntryPage(driver);

        // Verify that the "New Entry" clickable option is displayed and click on it
        WebElement newEntryBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"cardOption entry\"]")));
        Assert.assertTrue(newEntryBtn.isDisplayed(), "'New Entry' option should be displayed");
        createEntryPage.clickNewEntryButton();
    }

    @Test(priority = 2)
    public void verify_new_entry_ui_elements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        navigate_to_new_entry_page();

        // Check correct URL
        wait.until(ExpectedConditions.urlContains("/new-entry?mode=create"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/new-entry?mode=create"), "URL should contain /new-entry");

        // Check all important UI elements
        String[][] elementsToCheck = {
                {"//input[@class=\"titleField\"]", "Title field should be displayed"},
                {"//div[@class=\"date-picker\"]/input", "Date field 1 should be displayed"},
                {"//div[@class=\"date-picker\"]/p", "Date field 2 should be displayed"},
                {"//div[@class=\"feel \"]", "Feeling checkbox should be displayed"},
                {"//div[@class=\"editor-wrapper\"]/div/div", "Big text field should be displayed"},
                {"//input[@class=\"tagsField\"]", "Tag field should be displayed"},
                {"//div[@class=\"textFieldColor\"]/input", "Color tag input should be displayed"},
                {"//div[@class=\"task-creator\"]/button", "Add color tag button should be displayed"},
                {"//div[@class=\"textFieldColor\"]/div", "Color tag dropdown button should be displayed"},
                {"//button[@class=\"save-btn\"]", "Save button should be displayed"}
        };

        for (String[] item : elementsToCheck) {
            visibilityOfElementLocated(item[0], item[1]);
        }
    }


    @Test(priority = 4)
    public void create_new_entry_with_valid_data() {
        navigate_to_new_entry_page();
        CreateEntryPage createEntryPage = new CreateEntryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));

        // Check title and type
        visibilityOfElementLocated("//input[@class=\"titleField\"]", "Title field should be displayed");
        createEntryPage.enterTitle("Test Diary Title");

        // Check dates
        Assert.assertTrue(createEntryPage.verifyDefaultDates(), "Default dates in UI fields must match today's date formats");

        // Check tag-input field is displayed. Add new tag.
        visibilityOfElementLocated("//input[@class=\"tagsField\"]", "Tag field should be displayed");
        createEntryPage.enterTag("test");

        // Check tag-color-input field is displayed. Add new color tag.
        visibilityOfElementLocated("//div[@class=\"textFieldColor\"]/input", "Color tag input should be displayed");
        visibilityOfElementLocated("//div[@class=\"task-creator\"]/button", "Add color tag button should be displayed");
        createEntryPage.addColorTag("color_test");

        // Check feel checkbox is displayed. Select 1.
        visibilityOfElementLocated("//div[@class=\"feel \"]", "Feeling checkbox should be displayed");
        createEntryPage.selectFeeling();

        // Check the large text field is displayed and enter a description
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ql-editor[contenteditable='true']")));
        createEntryPage.enterBigText("This is a valid long entry text description.");
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".ql-editor[contenteditable='true']"), "This is a valid long entry text description."));

        // Check the Save button is displayed. Click it.
        visibilityOfElementLocated("//button[@class=\"save-btn\"]", "Save button should be displayed");
        createEntryPage.clickSaveButton();

        // Check message about successful created Entry.
        visibilityOfElementLocated("//div[@class=\"wrapperErrorMsgs\"]/div/span", "Success message should be displayed");
        Assert.assertTrue(createEntryPage.isSuccessMessageDisplayed(), "Success message 'New record created' should appear");

        // Verify that the user must stay at the same page after creating new Entry
        Assert.assertTrue(driver.getCurrentUrl().contains("/new-entry?mode=create"), "User should remain on the same page");
    }

    @Test(priority = 5)
    public void check_quick_select_suggestions() {
        navigate_to_new_entry_page();
        CreateEntryPage createEntryPage = new CreateEntryPage(driver);

        // Check the tag field is displayed and select a tooltip. Also, check to see if this tag has been added to the list
        visibilityOfElementLocated("//input[@class=\"tagsField\"]", "Tag field should be displayed for suggestions");
        createEntryPage.selectTagFromSuggestion("tes");
        visibilityOfElementLocated("//div[@class=\"tagsShow\"]/div", "Added tags should be displayed.");

        // Check the color-tag field is displayed and select a tooltip. Also, check to see if this tag has been added to the list
        visibilityOfElementLocated("//div[@class=\"textFieldColor\"]/input", "Tag-color field should be displayed for suggestions");
        createEntryPage.selectColorTagFromDropdown();
        visibilityOfElementLocated("//div[@class=\"colorsPick\"]/button", "Added tags should be displayed.");

    }



}
