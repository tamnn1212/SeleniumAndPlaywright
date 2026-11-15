package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_Topic_27_Custom_Button {
    WebDriver driver;
    JavascriptExecutor jssExcuter;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jssExcuter = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @Test
    public void TC_01_CustomRadioBtn() {
        // Click by Selenium check element clickable truoc
        // JavascriptException - Ep kieu
        driver.get("https://login.ubuntu.com/");
        By rdNewUser = By.cssSelector("input#id_new_user");
        if (!driver.findElement(rdNewUser).isSelected()) {
            jssExcuter.executeScript("arguments[0].click();",driver.findElement(rdNewUser));
        }
        Assert.assertTrue(driver.findElement(rdNewUser).isSelected());
    }
    @Test
    public void TC_02_BT06_GGDocs() {
        // Click by Selenium check element clickable truoc
        // JavascriptException - Ep kieu
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        driver.findElement(By.cssSelector("div[data-value='Cần Thơ']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[data-value='Cần Thơ']")).getDomAttribute("aria-checked"),"true");
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}