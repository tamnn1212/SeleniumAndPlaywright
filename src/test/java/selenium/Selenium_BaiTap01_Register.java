package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium_BaiTap01_Register {
    WebDriver driver;
    WebElement btnSubmit;
    By email,password,phoneNumber, CEmail, CPass, fullName;
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        email = By.cssSelector("input#txtEmail");
        password = By.cssSelector("input#txtPassword");
        fullName = By.cssSelector("input#txtFirstname");
        CPass = By.cssSelector("input#txtCPassword");
        CEmail = By.cssSelector("input#txtCEmail");
        phoneNumber = By.cssSelector("input#txtPhone");
    }
    @Test
    public void TC_01_RegisterWithEmptyData() {
        btnSubmit.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }
    @Test
    void TC_02_RegisterInvalidEmail() {
        driver.findElement(fullName).sendKeys("Automation");
        driver.findElement(email).sendKeys("Automation@dt.@gmail.com");
        driver.findElement(CEmail).sendKeys("Automation@dt.@gmail.com");
        driver.findElement(password).sendKeys("123456");
        driver.findElement(CPass).sendKeys("123456");
        driver.findElement(phoneNumber).sendKeys("0351235468");
        btnSubmit.click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
    }
    @Test
    void TC_03_RegisterIncorrectConfirmEmail() {
        driver.findElement(fullName).sendKeys("Automation");
        driver.findElement(email).sendKeys("Automation@gmail.com");
        driver.findElement(CEmail).sendKeys("Automation1@gmail.com");
        driver.findElement(password).sendKeys("123456");
        driver.findElement(CPass).sendKeys("123456");
        driver.findElement(phoneNumber).sendKeys("0351235468");
        btnSubmit.click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    void TC_04_RegisterInvalidPassword() {
        driver.findElement(fullName).sendKeys("Automation");
        driver.findElement(email).sendKeys("Automation@gmail.com");
        driver.findElement(CEmail).sendKeys("Automation@gmail.com");
        driver.findElement(password).sendKeys("123");
        driver.findElement(CPass).sendKeys("123");
        driver.findElement(phoneNumber).sendKeys("0351235468");
        btnSubmit.click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }
    @Test
    void TC_05_RegisterIncorrectConfirmPassword() {
        driver.findElement(fullName).sendKeys("Automation");
        driver.findElement(email).sendKeys("Automation@gmail.com");
        driver.findElement(CEmail).sendKeys("Automation@gmail.com");
        driver.findElement(password).sendKeys("123456");
        driver.findElement(CPass).sendKeys("654321");
        driver.findElement(phoneNumber).sendKeys("0351235468");
        btnSubmit.click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");

    }
    @Test
    void TC_06_RegisterInvalidPhoneNumber() {
        driver.findElement(fullName).sendKeys("Automation");
        driver.findElement(email).sendKeys("Automation@gmail.com");
        driver.findElement(CEmail).sendKeys("Automation@gmail.com");
        driver.findElement(password).sendKeys("123456");
        driver.findElement(CPass).sendKeys("654321");
        driver.findElement(phoneNumber).sendKeys("2124");
        btnSubmit.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error"))
                .getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}