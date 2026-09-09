package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

public class Selenium_BaiTap01_RegisterMailchip {
    WebDriver driver;
    WebElement username,password,email,mess1,mess2,mess3,mess4,mess5,mess6,mess7,btnSignUp;
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();

        email = driver.findElement(By.id("email"));
        username = driver.findElement(By.id("new_username"));
        password = driver.findElement(By.id("new_password"));
        mess1 = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
        mess2 = driver.findElement(By.xpath("//span[text()='One uppercase character']"));
        mess3 = driver.findElement(By.xpath("//span[text()='One number']"));
        mess4 = driver.findElement(By.xpath("//span[text()='One special character']"));
        mess5 = driver.findElement(By.xpath("//span[text()='8 characters minimum']"));
        mess6 = driver.findElement(By.xpath("//span[text()='Must not contain username']"));
        mess7 = driver.findElement(By.xpath("//span[text()='50 characters maximum']"));

        btnSignUp = driver.findElement(By.xpath("//button[text()='Sign up']"));
    }
    @Test
    public void TC_01_passwordOnlyNumber() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        email.sendKeys("ngthitamth@gmail.com");
        username.click();
        password.sendKeys("123");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("div#slot-main")).click();
        //Verify
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement mess3Li = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        WebElement mess1Li = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));

        // Chờ class completed xuất hiện trước
        wait.until(driver -> Objects.requireNonNull(mess3Li.getAttribute("class")).contains("completed"));

        // Lấy màu SAU KHI đã completed để biết giá trị đúng
        wait.until(driver -> mess1.getCssValue("color").equals("rgb(0, 133, 71)"));
        Assert.assertEquals(mess1.getCssValue("color"), "rgb(0, 133, 71)");

    }

    @AfterMethod
    void close() {
        //driver.quit();
    }
}