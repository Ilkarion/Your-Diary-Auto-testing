import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;


public class IntroPageTests extends BaseTest {


    @Test
    public void changeAppTheme() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        IntroPage introPage = new IntroPage(driver);
        WebElement theme_btn = introPage.getBtn_change_theme();
        // Check if theme is dark by default.
        String theme_default = introPage.getThemeText();
        Assert.assertEquals(theme_default, "dark", "Default theme should be dark");

        // Switch theme from Dark to Light. Check to see if the theme has changed.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"toggle-theme\"]")));
        theme_btn.click();
        String theme_light = introPage.getThemeText();
        String lightColor_h1 = introPage.getCssTextColor_h1();
        Assert.assertEquals(theme_light, "light", "Theme must be changed to light");
        Assert.assertEquals(lightColor_h1, "rgba(221, 5, 135, 1)");


        // Switch theme from Light to Dark. Check to see if the theme has changed.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"toggle-theme\"]")));
        theme_btn.click();
        String theme_dark = introPage.getThemeText();
        String darkColor_h1 = introPage.getCssTextColor_h1();
        Assert.assertEquals(theme_dark, "dark", "Theme must be changed to dark");
        Assert.assertEquals(darkColor_h1, "rgba(247, 103, 187, 0.847)");
    }

    @Test(priority = 1)
    public void change_application_language() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        IntroPage introPage = new IntroPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"language\"]")));
        introPage.getBtn_change_lang().click();


        // Switch language from English to Russian. Verify that the English language is applied correctly.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"language\"]")));
        introPage.getBtn_lang_en().click();
        wait.until(ExpectedConditions.urlContains("/en"));

        String text_en_h1 = introPage.getText_h1();
        String lang_en = introPage.getLanguage();

        Assert.assertEquals(lang_en, "en");
        Assert.assertEquals(text_en_h1, "Cosmic Diary");

        // Switch language from Russian to English. Verify that the Russian language is applied correctly
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"language\"]")));
        introPage.getBtn_change_lang().click();
        introPage.getBtn_lang_ru().click();
        wait.until(ExpectedConditions.urlContains("/ru"));

        String text_ru_h1 = introPage.getText_h1();
        String lang_ru = introPage.getLanguage();

        Assert.assertEquals(lang_ru, "ru");
        Assert.assertEquals(text_ru_h1, "Космический дневник");

    }
    @Test(priority = 2)
    public void Redirect_unauthenticated_user_to_Sign_Page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(65));
        IntroPage introPage = new IntroPage(driver);

        //  through 'checkAuthUser' page.
        introPage.getBtn_getStarted().click();
        boolean status = wait.until(ExpectedConditions.urlContains("/checkAuthUser"));
        Assert.assertTrue(status);

        //  redirected to the Sign Page
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("/sign")));

    }

}




