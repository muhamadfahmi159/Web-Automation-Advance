package Automation;

import org.openqa.selenium.WebDriver;

public interface IDriverManager {

    void openBrowser();

    void quitBrowser();

    WebDriver getDriver();
}