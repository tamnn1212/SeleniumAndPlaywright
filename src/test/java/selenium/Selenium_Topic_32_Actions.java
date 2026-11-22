package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Selenium_Topic_32_Actions {
    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // Khoi tao
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }
    @Test
    public void TC_01_Hover() {
        // Click
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        WebElement tooltip =  new WebDriverWait(driver, Duration.ofSeconds(8)).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ui-tooltip-content")));
        //action.pause(Duration.ofSeconds(3)).perform(); // Cho
        // hover
        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() {
        driver.get("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        action.pause(Duration.ofSeconds(2)).perform();
        //driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        //verify chuyển trang
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03_HoverAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> lstNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
        Keys keys;
        if (osName.startsWith("Mac")) {
            keys = Keys.COMMAND;
        } else {
            keys = Keys.CONTROL;
        }
        action.clickAndHold(lstNumber.get(12))
                .pause(Duration.ofSeconds(2))
                .moveToElement(lstNumber.get(19))
                .pause(Duration.ofSeconds(2))
                .release().perform();
        Assert.assertEquals(driver.findElements(By.cssSelector("li.ui-selected")).size(),8);
        // Chon 1 vai option

        // 1. nhan ctrl
        action.keyDown(keys).perform();
        // chon cac do can click
        action.click(lstNumber.get(1)).pause(Duration.ofSeconds(1))
                .click(lstNumber.get(3)).pause(Duration.ofSeconds(1))
                .perform();
        // nha ctrl
        action.keyDown(keys).perform();
     }
    @Test
    public void TC_04_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Fire fox can cuon xuong de thao tac
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//button[text()='Double click me']")));
        action.pause(Duration.ofSeconds(2));
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}