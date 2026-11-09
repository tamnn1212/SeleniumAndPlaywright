package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_Topic_27_Button {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }
    @Test
    public void TC_01_HandleBtn() {
        // status: enble, disbale
        // background-color
        // Text
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        // verify button dang ky disable
        By btnDangKyBy = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(
                driver.findElement(btnDangKyBy).isEnabled());
        Assert.assertEquals(
                Color.fromString(
                driver.findElement(btnDangKyBy).getCssValue("background-color")).asHex().toUpperCase(),
                "#000000");
        // Dien tt hop le
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0358892524");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456Aa@");
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnDangKyBy));
        Assert.assertEquals(
                Color.fromString(
                        driver.findElement(btnDangKyBy).getCssValue("background-color")).asHex().toUpperCase(),
                "#C92127");
        Assert.assertTrue(
                driver.findElement(btnDangKyBy).isEnabled());
    }
    @Test
    public void TC_02_DefaultCheckBoxAndRdBtn() {
        // Checkbox
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By Dual_zone_air_conditioningOption = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        if(!driver.findElement(Dual_zone_air_conditioningOption).isSelected()) {
            driver.findElement(Dual_zone_air_conditioningOption).click();
        }
        Assert.assertTrue(driver.findElement(Dual_zone_air_conditioningOption).isSelected());
        driver.findElement(Dual_zone_air_conditioningOption).click();
        Assert.assertFalse(driver.findElement(Dual_zone_air_conditioningOption).isSelected());
        // radiobtn
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By petrol2 = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        if(!driver.findElement(petrol2).isSelected()) {
            driver.findElement(petrol2).click();
        }
        Assert.assertTrue(driver.findElement(petrol2).isSelected());
    }
    @Test
    public void TC_03_SelectAll() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        // Chon tat ca
        List<WebElement> lst = driver.findElements(By.cssSelector("input.form-checkbox"));
        for (WebElement i:lst) {
            if (!i.isSelected()) {
            i.click(); }
            Assert.assertTrue(i.isSelected());
        }
        // Un-check
        for (WebElement i:lst) {
            if (i.isSelected()) {
                i.click(); }
            Assert.assertFalse(i.isSelected());
        }
        // chon 1 so option
        for (WebElement i: lst) {
            if(!i.isSelected()&&i.getDomProperty("value").equals("Gallstones")) {
                i.click();
            }
        }
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}