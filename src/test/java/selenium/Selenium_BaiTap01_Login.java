package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

public class Selenium_BaiTap01_Login {
    WebDriver driver;
    WebElement myAcc;
    @BeforeMethod
    void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        driver.manage().window().maximize();
        myAcc = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
    }
    @Test
    public void TC_01_LoginWithEmptyEmailAndPass() {
        myAcc.click();
        driver.findElement(By.cssSelector("button#send2")).click();
        WebElement emailErr = driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']"));
        WebElement passErr = driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@class='validation-advice']"));
        String emailErrMess = emailErr.getText();
        String passErrMess = passErr.getText();

//        if (emailErrMess.equals("This is a required field.")
//                && emailErr.isDisplayed() && passErr.isDisplayed()
//                && passErrMess.equals("This is a required field.")) {
//            System.out.println("Pass");
//        } else {
//            System.out.println("Fail");
//        }
        Assert.assertEquals(emailErrMess, "This is a required field.");
        Assert.assertEquals(passErrMess, "This is a required field.");
        System.out.println("Pass");
    }
    @Test
    public void TC_02_LoginWithInvalidEmail() {
        myAcc.click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@123");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement emailErr = driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']"));
        String emailErrMess = emailErr.getText();
        Assert.assertEquals(emailErrMess, "Please enter a valid email address. For example johndoe@domain.com.");
        System.out.println("Pass");

    }
    @Test
    public void TC_03_LoginWithPassLessThan6() {
        myAcc.click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement passErr = driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@class='validation-advice']"));
        Assert.assertEquals(passErr.getText(), "Please enter 6 or more characters without leading or trailing spaces.");
        System.out.println("Pass");

    }
    @Test
    public void TC_04_LoginWithIncorrectInfo() {
        myAcc.click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement err = driver.findElement(By.xpath("//li[@class='error-msg']"));
        String errMess = err.getText();

        Assert.assertEquals(errMess,"Invalid login or password.");
        System.out.println("Pass");

    }
    @AfterMethod
    void close() {
        driver.quit();
    }
}