package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Selenium_Topic_02_Templete {
    WebDriver driver;

    @Test
    public void TC_01() {
        driver = new FirefoxDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.quit();
    }

    @Test
    public void TC_02() {
        driver = new ChromeDriver();
        driver.get("");
        driver.quit();
    }

    @Test
    public void TC_03() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
}