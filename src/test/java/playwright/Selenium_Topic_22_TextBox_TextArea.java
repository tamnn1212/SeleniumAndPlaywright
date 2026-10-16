package playwright;

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

public class Selenium_Topic_22_TextBox_TextArea {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void TC_01() {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // wait
//      /**  Assert.assertTrue(
//        new WebDriverWait(driver,Duration.ofSeconds(10)).
//                until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("oxd-loading-spinner")))) // cho cho tat ca icon loading bien mat
//        )
        // Cần wait nhieu lan nen extract thanh function. 
        // Khong Assert trong hham
        //isWaiting(); *//
        Assert.assertTrue(isWaiting());
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isWaiting());
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Assert.assertTrue(isWaiting());

        // step 05: Nhap cac thong tin hop le
        driver.findElement(By.cssSelector("input.orangehrm-firstname")).sendKeys("");
        driver.findElement(By.cssSelector("input.orangehrm-middlename")).sendKeys("");
        driver.findElement(By.cssSelector("input.orangehrm-lastname")).sendKeys("");
        driver.findElement(By.cssSelector("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys("");
    }

    // function
    private boolean isWaiting() { // tra ve kieu du lieu ham invisibilityOfAllElements
         return  new WebDriverWait(driver,Duration.ofSeconds(10)).
                        until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("oxd-loading-spinner")))); // cho cho tat ca icon loading bien mat
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}