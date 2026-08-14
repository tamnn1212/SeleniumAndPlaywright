package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Selenium_Topic_07_Locator {
    WebDriver driver;
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://test-dx.elcomlab.com/dangnhap/");
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_ID() {
        driver.findElement(By.cssSelector(""));
    }

    @Test
    public void TC_02_Class() {
        driver = new ChromeDriver();
        driver.get("");
        driver.quit();
    }

    @Test
    public void TC_04_TagName() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
    @Test
    public void TC_06_PartialLink() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
    @Test
    public void TC_06_Css() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
    @Test
    public void TC_08_Xpath() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
    public void afterClass(){
        driver.quit();
    }
}