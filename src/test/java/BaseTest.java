import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

public class BaseTest {
    protected WebDriver driver;
    private final String url = "https://diary-cosmic-liard.vercel.app";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--lang=ru-RU");
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Если тест завершился с ошибкой, делаем скриншот ДО вызова driver.quit()
            if (ITestResult.FAILURE == result.getStatus()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshot), "png");
                    System.out.println("[BaseTest] Скриншот успешно добавлен в Allure для упавшего теста: " + result.getName());
                } catch (Exception e) {
                    System.err.println("[BaseTest] Ошибка при создании скриншота: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            driver.quit();
        }
    }

    public void openPage(String endpoint) {
        driver.get(url + endpoint);
    }

    public WebDriver getDriver() {
        return driver;
    }
}