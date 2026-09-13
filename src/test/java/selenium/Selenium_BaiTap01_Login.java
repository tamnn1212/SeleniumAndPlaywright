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

public class Selenium_BaiTap01_Login {
    WebDriver driver;
    WebElement myAcc;
    @BeforeTest
    void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        driver.manage().window().maximize();

        myAcc = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
    }
    @Test
    public void TC_01_LoginWithEmptyEmailAndPass() {
       driver.findElement(By.id("button#send2")).click();
      try {
          Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']")).isDisplayed());
          Assert.assertTrue(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@class='validation-advice']")).isDisplayed());

          System.out.println("Da xuat hien thong bao loi. Pass");
      } catch (Exception e) {
          System.out.println("Fail");
          throw new RuntimeException(e);
      }
    }
    @Test
    public void TC_01_LoginWithInvalidEmail() {

        driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("123@123");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123@123456");
        driver.findElement(By.id("button#send2")).click();

        try {
            Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']")).getText()
                    ,"Please enter a valid email address. For example johndoe@domain.com.");
            System.out.println("Da xuat hien thong bao loi. Pass");
        } catch (Exception e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_01_LoginWithPassLessThan6() {

        driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.id("button#send2")).click();

        try {
            Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']")).getText()
                    ,"Please enter 6 or more characters without leading or trailing spaces.");
            System.out.println("Da xuat hien thong bao loi. Pass");
        } catch (Exception e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_01_LoginWithIncorrectInfo() {

        driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.id("button#send2")).click();

        try {
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).isDisplayed());
            System.out.println("Da xuat hien thong bao loi. Pass");
        } catch (Exception e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    void close() {
        driver.quit();
    }
}