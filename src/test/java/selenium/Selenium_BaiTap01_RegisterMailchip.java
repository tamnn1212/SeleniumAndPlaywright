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
                new WebDriverWait(driver, Duration.ofSeconds(15));

        By emailLocator = By.id("email");
        By usernameLocator = By.id("new_username");
        By passwordLocator = By.id("new_password");

        email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailLocator)
        );

        username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameLocator)
        );

        password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordLocator)
        );
    }
    @Test
    public void TC_01_passwordOnlyNumber() throws InterruptedException {
        email.click();
        email.sendKeys("ngthitamth@gmail.com");
        email.sendKeys(Keys.TAB);
        password.sendKeys("123");
        //Verify theo yeu cau la doi mau text
        driver.findElement(By.cssSelector("div#slot-main")).click();
        Thread.sleep(3000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(driver->driver.findElement(By.cssSelector("li.number-char")).getAttribute("class").contains("completed"));
        //WebElement mess = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
        Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char")).getCssValue("color"),"rgb(0, 133, 71)");
        //Verify = isDisplayed
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
    }
    @Test
    public void TC_02_addLowerChar() throws InterruptedException {
        email.click();
        email.sendKeys("ngthitamth@gmail.com");
        email.sendKeys(Keys.TAB);
        password.sendKeys("123abc");
        //Verify theo yeu cau la doi mau text

        driver.findElement(By.cssSelector("div#slot-main")).click();
        Thread.sleep(3000);
        //Verify theo yeu cau la doi mau text
        Assert.assertEquals(driver.findElement(By.cssSelector("li.lowercase-char.completed"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char.completed"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertFalse(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
    }
    @Test
    void TC_03_addUpperChar() throws InterruptedException {
        email.click();
        email.sendKeys("ngthitamth@gmail.com");
        email.sendKeys(Keys.TAB);
        password.sendKeys("123abcA");
        //Verify theo yeu cau la doi mau text

        driver.findElement(By.cssSelector("div#slot-main")).click();
        Thread.sleep(3000);
        //Verify theo yeu cau la doi mau text
        Assert.assertEquals(driver.findElement(By.cssSelector("li.lowercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.uppercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertFalse(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
    }
    @Test
    void TC_04_nhapThemKyTuDacBiet() throws InterruptedException {
        email.click();
        email.sendKeys("ngthitamth@gmail.com");
        email.sendKeys(Keys.TAB);
        password.sendKeys("123abcA@");
        //Verify theo yeu cau la doi mau text

        driver.findElement(By.cssSelector("div#slot-main")).click();
        Thread.sleep(3000);
        //Verify theo yeu cau la doi mau text
        Assert.assertEquals(driver.findElement(By.cssSelector("li.lowercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.uppercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.special-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
         Assert.assertFalse(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
    }
    @Test
    void TC_05_addMoreThan8Char() throws InterruptedException {
        email.click();
        email.sendKeys("ngthitamth@gmail.com");
        email.sendKeys(Keys.TAB);
        password.sendKeys("123abcA@");
        Thread.sleep(3000);
        //Verify theo yeu cau la doi mau text
        Assert.assertEquals(driver.findElement(By.cssSelector("li.lowercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.uppercase-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.special-char"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertEquals(driver.findElement(By.xpath("//li[contains(@class,'8-char')]"))
                .getCssValue("color"),"rgb(0, 133, 71)","Fail!");
        Assert.assertTrue(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
    }
    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}