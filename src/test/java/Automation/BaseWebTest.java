package Automation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseWebTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();
    private IDriverManager driverManager = new ChromeDriverManager();

    @BeforeMethod
    public void openBrowser() {
        driverManager.openBrowser();
        driver.set(driverManager.getDriver());
        explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));
    }

    @AfterMethod
    public void closeBrowser() {
        driverManager.quitBrowser();
    }
}