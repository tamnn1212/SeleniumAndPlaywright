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
                ExpectedConditions.elementToBeClickable(new By.ByCssSelector("input#email"))
        );

        username = wait.until(
                ExpectedConditions.elementToBeClickable(new By.ByCssSelector("input#new_username"))
        );

        password = wait.until(
                ExpectedConditions.elementToBeClickable(new By.ByCssSelector("input#new_password"))
        );
    }

    @Test
    public void TC_01_onlyNumber() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");
        username.click();
        password.click();
        password.clear();
        password.sendKeys("123");
        //Verify bang class (completed)
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.attributeContains(
//                By.xpath("//li[contains(@class,'number-char')]"), "class", "completed"
//        ));
//        Assert.assertTrue(mess.getAttribute("class").contains("completed"));

        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        Thread.sleep(1000);
        Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
    }
    @Test
    public void TC_02_addLowerChar() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("123abc");

        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        Thread.sleep(2000);
        Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
    }
    @Test
    void TC_03_addUpperChar() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("1Aa");

        Thread.sleep(2000);

        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));

        Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
    }
    @Test
    void TC_04_addSpecialChar() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("1Aa@");

        Thread.sleep(2000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
        WebElement mess4 = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));


        Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess4.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
    }
    @Test
    void TC_05_addMoreThan8() throws InterruptedException {
        email.click();
        email.clear();
        email.sendKeys("ngthitamth@gmail.com");

        username.click();
        password.clear();
        password.sendKeys("ABC123abc@");

        Thread.sleep(6000);
        WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
        WebElement mess2 = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        WebElement mess3 = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
        WebElement mess4 = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
        WebElement mess5 = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));

        Assert.assertEquals(mess.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess2.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess3.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess4.getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(mess5.getCssValue("color"),"rgb(0, 133, 71)","Fail!");

    }
    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}