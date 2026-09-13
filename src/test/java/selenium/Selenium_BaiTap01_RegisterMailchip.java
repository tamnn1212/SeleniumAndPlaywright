package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Selenium_BaiTap01_RegisterMailchip {
    WebDriver driver;
    WebElement username,password,email;
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://login.mailchimp.com/signup/");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        email = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("myAcc"))
        );

        username = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("new_username"))
        );

        password = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("new_password"))
        );
    }

    @Test
    public void TC_01_passwordOnlyNumber() throws InterruptedException {
        Thread.sleep(5000);
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");
        username.click();
        password.click();
        password.clear();
        password.sendKeys("123");
        //Verify
        //1. Cho cho lay dc phan tu complete
        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'number-char') and contains(@class,'completed')]"));
       // Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Pass!");
        try {
            Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)");
            System.out.println("TC_01: Pass!");
        } catch (AssertionError e) {
            System.out.println("TC_01: Fail!");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_02_onlyLowerChar() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("123abc");

        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char') and contains(@class,'completed')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char') and contains(@class,'completed')]"));

        // Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Pass!");
        try {
            Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)");
            System.out.println("TC_02: Pass!");
        } catch (AssertionError e) {
            System.out.println("TC_02: Fail!");
            throw new RuntimeException(e);
        }
    }
    @Test
    void TC_03_nhapThemChuHoa() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("1Aa");

        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char') and contains(@class,'completed')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char') and contains(@class,'completed')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char') and contains(@class,'completed')]"));
        // Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Pass!");
        try {
            Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)");
            System.out.println("TC_03: Pass!");
        } catch (AssertionError e) {
            System.out.println("TC_03: Fail!");
            throw new RuntimeException(e);
        }
    }
    @Test
    void TC_04_nhapThemKyTuDacBiet() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("1Aa@");

        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char') and contains(@class,'completed')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char') and contains(@class,'completed')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char') and contains(@class,'completed')]"));

        WebElement mess4 = driver.findElement(By.xpath("//li[contains(@class,'special-char') and contains(@class,'completed')]"));


        // Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Pass!");
        try {
            Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess4.getCssValue("color"),"rgb(0, 133, 71)");

            System.out.println("TC_04: Pass!");
        } catch (AssertionError e) {
            System.out.println("TC_04: Fail!");
            throw new RuntimeException(e);
        }
    }
    @Test
    void TC_05_ThemDKLonHon8KT() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("ABC123abc@");

        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char') and contains(@class,'completed')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char') and contains(@class,'completed')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char') and contains(@class,'completed')]"));

        WebElement mess4 = driver.findElement(By.xpath("//li[contains(@class,'special-char') and contains(@class,'completed')]"));
        WebElement mess5 = driver.findElement(By.xpath("//li[contains(@class,'8-char') and contains(@class,'completed')]"));


        // Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Pass!");
        try {
            Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess4.getCssValue("color"),"rgb(0, 133, 71)");
            Assert.assertEquals(mess5.getCssValue("color"),"rgb(0, 133, 71)");
            System.out.println("TC_04: Pass!");
        } catch (AssertionError e) {
            System.out.println("TC_04: Fail!");
            throw new RuntimeException(e);
        }
    }
    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}