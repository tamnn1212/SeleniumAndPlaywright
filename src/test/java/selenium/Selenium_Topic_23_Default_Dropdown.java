package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.util.Elements;
import javax.swing.text.Element;
import java.time.Duration;
import java.util.List;

public class Selenium_Topic_23_Default_Dropdown {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @Test
    public void TC_01() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();
        // the select co thuoc tinh multiple khong
//        new Select(driver.findElement(By.cssSelector("select.product_sort_container")));
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select.product_sort_container")));
        Assert.assertFalse(sortDropdown.isMultiple());
        // Chon
        // by index start 0
//        sortDropdown.selectByIndex(0);
//        sortDropdown.selectByValue("lohi");
//        sortDropdown.selectByContainsVisibleText("high to low");
        //sortDropdown.selectByVisibleText("Name (A to Z)");
        // bo chon
//        sortDropdown.deselectByIndex(0);
//        sortDropdown.deselectByValue("lohi");
//        sortDropdown.deSelectByContainsVisibleText("high to low");
//        sortDropdown.deselectByVisibleText("Name (A to Z)");
        // Kiem tra chon thanh cong
        //Assert.assertEquals(sortDropdown.getFirstSelectedOption().getText(),"Name (A to Z)");
        // Kiem tra so luong item
        //Assert.assertEquals(sortDropdown.getOptions().size(),4);
        By sortDropdown2 = By.cssSelector("select.product_sort_container");
        new Select(driver.findElement(sortDropdown2)).selectByVisibleText("Name (Z to A)");
        Assert.assertEquals(new Select(driver.findElement(sortDropdown2)).getFirstSelectedOption()
                .getText(),"Name (Z to A)"
        );
    }
    @Test
    public void TC_02_Role() {
        driver.get("https://rode.com/en-au/support/where-to-buy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> lstData =  new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"),16
        )); // numberOfElementsToBe : bat buoc phai bang
        Assert.assertEquals(lstData.size(),16);
        // print
        for (WebElement i:lstData) {
            System.out.println("Dealer name: " + i.getText());
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}