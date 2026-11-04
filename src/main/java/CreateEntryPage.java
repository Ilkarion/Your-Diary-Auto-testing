import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class CreateEntryPage {
    private WebDriver driver;

    // Locators (оставлены пустыми для заполнения)
    private final By new_entry_button_address = By.xpath("//div[@class=\"cardOption entry\"]");
    private final By title_field_address = By.xpath("//input[@class=\"titleField\"]");
    private final By date_field_1_address = By.xpath("//div[@class=\"date-picker\"]/input"); // format: Sunday, November 1, 2026
    private final By date_field_2_address = By.xpath("//div[@class=\"date-picker\"]/p"); // format: 01.11.2026
    private final By feeling_checkbox_address = By.xpath("//div[@class=\"feel \"]");
    private final By big_text_field_address = By.cssSelector(".ql-editor[contenteditable='true']");
    private final By tag_field_address = By.xpath("//input[@class=\"tagsField\"]");
    private final By tag_suggestion_address = By.xpath("//div[@class=\"suggestItem\"]");
    private final By color_tag_input_address = By.xpath("//div[@class=\"textFieldColor\"]/input");
    private final By add_color_tag_button_address = By.xpath("//div[@class=\"task-creator\"]/button");
    private final By color_tag_dropdown_button_address = By.xpath("//div[@class=\"textFieldColor\"]/div");
    private final By color_tag_dropdown_option_address = By.xpath("//div[@class=\"quick-tags\"]/button[1]");
    private final By save_button_address = By.xpath("//button[@class=\"save-btn\"]");
    private final By success_message_address = By.xpath("//div[@class=\"wrapperErrorMsgs\"]/div/span");

    // Constructor
    public CreateEntryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to get elements
    public WebElement getNewEntryButton() {
        return driver.findElement(new_entry_button_address);
    }

    public WebElement getTitleField() {
        return driver.findElement(title_field_address);
    }

    public WebElement getDateField1() {
        return driver.findElement(date_field_1_address);
    }

    public WebElement getDateField2() {
        return driver.findElement(date_field_2_address);
    }

    public WebElement getBigTextField() {
        return driver.findElement(big_text_field_address);
    }

    public WebElement getTagField() {
        return driver.findElement(tag_field_address);
    }

    public WebElement getTagSuggestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='suggestItem']")));
        return driver.findElement(tag_suggestion_address);
    }

    public WebElement getColorTagInput() {
        return driver.findElement(color_tag_input_address);
    }

    public WebElement getAddColorTagButton() {
        return driver.findElement(add_color_tag_button_address);
    }

    public WebElement getColorTagDropdownButton() {
        return driver.findElement(color_tag_dropdown_button_address);
    }

    public WebElement getColorTagDropdownOption() {
        return driver.findElement(color_tag_dropdown_option_address);
    }

    public WebElement getSaveButton() {
        return driver.findElement(save_button_address);
    }

    public WebElement getSuccessMessage() {
        return driver.findElement(success_message_address);
    }

    // Actions & Check methods
    public void clickNewEntryButton() {
        getNewEntryButton().click();
    }

    public void enterTitle(String title) {
        WebElement titleField = getTitleField();
        titleField.clear();
        titleField.sendKeys(title);
    }

    public void enterBigText(String text) {
        WebElement editor = getBigTextField();
        editor.click();
        editor.sendKeys(Keys.CONTROL, "a");
        editor.sendKeys(text);

        //to let frontend editor update its state and save sending text from selenium
        try { Thread.sleep(600); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void selectFeeling() {
        driver.findElement(feeling_checkbox_address).click();
    }

    public void enterTag(String tag) {
        WebElement tagField = getTagField();
        tagField.clear();
        tagField.sendKeys(tag);
        tagField.sendKeys(Keys.ENTER);
    }

    public void selectTagFromSuggestion(String letters) {
        WebElement tagField = getTagField();
        tagField.clear();
        tagField.sendKeys(letters);
        getTagSuggestion().click();
    }

    public void addColorTag(String colorTag) {
        WebElement colorInput = getColorTagInput();
        colorInput.clear();
        colorInput.sendKeys(colorTag);
        getAddColorTagButton().click();
    }


    public void selectColorTagFromDropdown() {
        getColorTagDropdownButton().click();
        getColorTagDropdownOption().click();
    }

    public void clickSaveButton() {
        getSaveButton().click();
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement msg = getSuccessMessage();
        return msg.isDisplayed() && Objects.equals(msg.getText(), "New record created");
    }

    // Date Validation: We retrieve text from two fields and compare it to the generated current date in two formats
    public boolean verifyDefaultDates() {

        String uiDate1 = getDateField1().getAttribute("value");
        String uiDate2 = getDateField2().getText().trim();

        // Get current date from browser
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String browserToday = (String) js.executeScript(
                "const d = new Date();" +
                        "return d.getFullYear() + '-' +" +
                        "String(d.getMonth() + 1).padStart(2, '0') + '-' +" +
                        "String(d.getDate()).padStart(2, '0');"
        );

        // Expected date in input
        boolean isMatch1 = browserToday.equals(uiDate1);

        // Expected date in readable format
        String currentLanguage = driver.getCurrentUrl().contains("/ru") ? "ru" : "en";

        Locale locale = Locale.forLanguageTag(
                currentLanguage.equals("ru") ? "ru-RU" : "en-US"
        );

        LocalDate today = LocalDate.parse(browserToday);

        DateTimeFormatter formatter = currentLanguage.equals("ru")
                ? DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'г.'", locale)
                : DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", locale);

        String expectedDateText = today.format(formatter);
        boolean isMatch2 = expectedDateText.equalsIgnoreCase(uiDate2);

        return isMatch1 && isMatch2;
    }
}