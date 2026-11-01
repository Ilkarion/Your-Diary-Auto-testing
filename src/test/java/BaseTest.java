import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    private String url = "https://diary-cosmic-liard.vercel.app";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void openPage(String endpoint) {
        driver.get(url + endpoint);
    }
}