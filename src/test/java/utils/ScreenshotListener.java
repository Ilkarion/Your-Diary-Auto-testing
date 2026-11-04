package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshot), "png");
                    System.out.println("[Listener] The screenshot has been successfully attached to the test body: " + result.getName());
                } catch (Exception e) {
                    System.err.println("[Listener] Error creating a screenshot: " + e.getMessage());
                }
            }
        }
    }
}