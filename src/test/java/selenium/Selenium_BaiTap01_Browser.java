package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

public class Selenium_BaiTap01_Browser {
    WebDriver driver;

    @BeforeClass
    void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    }
    @Test
    public void TC_01_VerifyUrl() {
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin,"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        String urlRegister = driver.getCurrentUrl();
        Assert.assertEquals(urlRegister,"http://live.techpanda.org/index.php/customer/account/create/");
    }
    @Test
    public void TC_02_VerifyTitle() {
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_03_NavigateFunction() {
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //Verify url cua RegisterPage
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        //back
        driver.navigate().back();
        //Verify
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        //forward
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_04_getPageSourceCode() {
        Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("My Account"));
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Create New Customer Account"));
    }
    @AfterClass
    void close() {
        driver.quit();
    }
}