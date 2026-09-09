package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class Selenium_BaiTap01_Element {
    WebDriver driver;

    @BeforeTest
    void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_VerifyIsDisplay() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='email']"));
        Assert.assertTrue(inputEmail.isDisplayed());
        driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed();
        WebElement edu = driver.findElement(By.xpath("//textarea[@id='edu']"));
        Assert.assertTrue(edu.isDisplayed());
        //driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed();
        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
        //
        if (inputEmail.isDisplayed() && edu.isDisplayed()) {
            inputEmail.sendKeys("Automation Testing");
            edu.sendKeys("Automation Testing");
            System.out.println( inputEmail + "and" + edu + " Element is displayed" );
        } else  {
            System.out.println("Element is not displayed");
        }
    }
    @Test
    public void TC_02_IsEnabled() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='email']")); inputEmail.isEnabled();
        driver.findElement(By.xpath("//label[text()='Under 18']")).isEnabled();
        WebElement edu = driver.findElement(By.xpath("//textarea[@id='edu']")); edu.isEnabled();

        driver.findElement(By.xpath("//select[@id='job1']")).isEnabled();
        driver.findElement(By.xpath("//select[@id='job2']")).isEnabled();
        driver.findElement(By.xpath("//input[@id='development']")).isEnabled();
        driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled();

        // Kiem tra phan tu disable tren trang
        //Assert.assertFalse(driver.findElement(By.xpath("//input[@id='disable_password']")).isEnabled());

        // hoac
        WebElement disabledPass = driver.findElement(By.xpath("//input[@id='disable_password']"));
        if (!disabledPass.isEnabled()) {
            System.out.println( "Element is not enabled");
        };
        WebElement radioButtonDisabled = driver.findElement(By.xpath("//input[@name='user_interest' and @value='check-disbaled']"));
        if (!radioButtonDisabled.isEnabled()) {
            System.out.println("Element Radio is not enabled");
        }
        //
        Assert.assertFalse(driver.findElement(By.cssSelector("textarea#bio")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//select[@id='job3']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@name='user_interest' and @value='check-disbaled']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled());

    }
    @Test
    public void TC_03_isSelected() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        // Click chọn age (under 18)
        WebElement inputUnder18 = driver.findElement(By.cssSelector("input#under_18"));
        inputUnder18.click();
        WebElement inputJava = driver.findElement(By.cssSelector("input#java"));
        inputJava.click();
        if (inputUnder18.isSelected() && inputJava.isSelected()) {
            System.out.println("Element is selected");
        } else {
            System.out.println("Element is de-selected");
        }
        //Bo chon
        inputJava.click();
        if (inputJava.isSelected()) {
            System.out.println("Element is selected");
        } else {
            System.out.println("Element is de-selected");
        }
    }
    @AfterClass
    void close() {
        driver.quit();
    }
}