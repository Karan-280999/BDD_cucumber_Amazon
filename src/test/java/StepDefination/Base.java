package StepDefination;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

    public static WebDriver driver;
    public static ExtentReports extent = ExtentReportmanager.getReportObject();
    public static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        if (scenario.isFailed()) {
            test.fail("Failed").addScreenCaptureFromBase64String(screenshot);
        } else {
            test.pass("Passed").addScreenCaptureFromBase64String(screenshot);
        }

        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}