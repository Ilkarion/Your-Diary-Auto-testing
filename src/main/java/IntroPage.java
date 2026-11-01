import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URI;

public class IntroPage {
    private WebDriver driver;

    // Locators
    private final By btn_getStarted_address = By.xpath("//a[@href=\"/checkAuthUser\" and @class=\"homePageBtn\"]");
    private final By btn_change_lang_address = By.xpath("//button[@aria-label=\"language\"]");
    private final By lang_ru_address = By.xpath("//a[text()=\"RU\"]");
    private final By lang_en_address = By.xpath("//a[text()=\"EN\"]");
    private final By btn_change_theme_address = By.xpath("//button[@aria-label=\"toggle-theme\"]");
    private final String header1_address = "//h1[@class=\"headerHomePage\"]";

    //Constructor
    public IntroPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public WebElement getBtn_getStarted() {
        return driver.findElement(btn_getStarted_address);
    }

    public WebElement getBtn_change_lang() {
        return driver.findElement(btn_change_lang_address);
    }

    public WebElement getBtn_lang_ru() {
        return driver.findElement(lang_ru_address);
    }

    public WebElement getBtn_lang_en() {
        return driver.findElement(lang_en_address);
    }

    public WebElement getBtn_change_theme() {
        return driver.findElement(btn_change_theme_address);
    }


    //Checks methods
    public String getThemeText(){
        return driver.findElement(By
                        .xpath("//html"))
                        .getAttribute("data-theme");
    }
    public String getCssTextColor_h1() {
        WebElement header_1 = driver.findElement(By.xpath(header1_address));
        return header_1.getCssValue("color");
    }

    public String getLanguage() {
        String currentURL = driver.getCurrentUrl();

        URI currentURI = URI.create(currentURL);
        String path = currentURI.getPath();

        String[] segments = path.split("/");
        return segments[1];
    }
    public String getText_h1() {
        WebElement header1 = driver.findElement(By.xpath(header1_address));
        return header1.getText();
    }
}