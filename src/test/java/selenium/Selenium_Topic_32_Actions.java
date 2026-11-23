package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
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
        if (driver.toString().contains("Firefox")) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.xpath("//button[text()='Double click me']")));
        } else {
            driver.findElement(By.xpath("//button[text()='Double click me']")).click();
        }
        action.pause(Duration.ofSeconds(2));
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }
    // Topic 33 Continue Actions
     @Test
     public void TC_05_jQueryRightClick() {
             driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
             // contextClick : click chuot phai
             action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

             Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))
                     .isDisplayed());
             action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform(); action.pause(1).perform();
             Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
                     .isDisplayed());
             driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
             driver.switchTo().alert().accept();
             // accept alert
             Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))
                 .isDisplayed());
      }
      @Test
      public void TC_06_DragandDropHTML4() {
              driver.get("https://automationfc.github.io/kendo-drag-drop/");
              WebElement source,target;
              source = driver.findElement(By.cssSelector("div#draggable"));
              target = driver.findElement(By.cssSelector("div#droptarget"));
              Assert.assertEquals(target.getText(),"Drag the small circle here.");
              action.dragAndDrop(source,target).pause(Duration.ofSeconds(2)).perform();
              Assert.assertEquals(target.getText(),"You did great!");
              Assert.assertEquals(Color.fromString(
                      target.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
       }
       @Test
       public void TC_07_DragandDropHTML6() throws IOException {
               driver.get("https://automationfc.github.io/drag-drop-html5/");
//               WebElement source,target;
//               source = driver.findElement(By.cssSelector("div#column-a"));
//               target = driver.findElement(By.cssSelector("div#column-b"));
//               action.clickAndHold(source)
//                       .pause(Duration.ofSeconds(1))
//                               .moveToElement(target).release().perform();
//               // Hien tai k ho tro va khuyen khich keo tha voi html5
           String js = getContentFile(System.getProperty("user.dir") +"\\DragnDrop\\DragnDrop.js");
           //String jsContent = js +  "$(\"" + "div#column-a" + "\").simulateDragDrop({ dropTarget: \"" + "div#column-b" + "\"});";
           jsExecutor.executeScript(js);
           Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        }
     @AfterClass
     public void afterClass() {
            driver.quit();
     }
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
}
