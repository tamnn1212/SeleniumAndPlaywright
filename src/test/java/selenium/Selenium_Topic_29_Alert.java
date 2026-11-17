package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_Topic_29_Alert {
    WebDriver driver;
    Alert alert;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @Test
    public void TC_01() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@title='IPhone']/following-sibling::div//a[text()='Add to Compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']"))
                .isDisplayed());
        // clear
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        alert =  new WebDriverWait(driver,Duration.ofSeconds(2))
                .until(ExpectedConditions.alertIsPresent());
        // switch vao alert
        // = driver.switchTo().alert();
        // verify text
        Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");
        // accept
        alert.accept();
        // Verify mgs
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The comparison list was cleared.']"))
                .isDisplayed());
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}