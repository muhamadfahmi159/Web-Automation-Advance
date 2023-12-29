package Automation;

import com.google.common.annotations.VisibleForTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.Test;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {

    private WebDriver driver;
    private IDriverManager driverManager = new ChromeDriverManager();

    @BeforeMethod
    public void setUp() {
        driverManager.openBrowser();
        driver = driverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitBrowser();
    }

    @Test
    public void makeMyTripTest() {
        driver.get("https://www.makemytrip.com");

        try {
            Thread.sleep(1000); // Menunggu sesaat sebelum mencari elemen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Masukkan kota asal (Kulon Progo Regency, Indonesia)
        WebElement originInput = driver.findElement(By.xpath("//input[@id='fromCity']"));
        originInput.sendKeys("Kulon Progo Regency, Indonesia");
        // Tunggu beberapa saat agar dropdown muncul
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Auto select drop down pertama
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement originDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-autosuggest__suggestion--first")));
        originDropdown.click();

        // Masukkan kota tujuan (Barcelona, Spain)
        WebElement destinationInput = driver.findElement(By.xpath("//input[@id='toCity']"));
        destinationInput.sendKeys("Barcelona, Spain");
        // Tunggu beberapa saat agar dropdown muncul
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Auto select drop down pertama
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement destinationDropdown = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-autosuggest__suggestion--first")));
        destinationDropdown.click();

        // Klik tanggal
        WebElement departure = driver.findElement(By.cssSelector("[aria-label='Mon Jan 01 2024']"));
        departure.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Klik button Search
        WebElement searchButton = driver.findElement(By.cssSelector(".primaryBtn"));
        searchButton.click();

        // Tunggu beberapa detik agar hasil pencarian muncul
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Klik button Okay Got It!
        WebElement okayButton = driver.findElement(By.cssSelector(".buttonBig"));
        okayButton.click();

        // Ekstrak value dari harga teratas
        WebElement topPriceElement = driver.findElement(By.cssSelector(".clusterViewPrice"));
        ////div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice']
        String topPrice = topPriceElement.getText();

        System.out.println("Top Price: " + topPrice);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Menutup browser
        driver.quit();
        // Perform actions and assertions
    }
}
