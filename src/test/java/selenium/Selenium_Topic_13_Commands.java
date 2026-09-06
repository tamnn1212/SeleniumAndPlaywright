package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;
import java.time.Duration;

public class Selenium_Topic_13_Commands {
    WebDriver driver;

    @Test
    public void TC_01_WebBrowser() {
        driver = new FirefoxDriver();
        // mo url
        driver.get("");
        driver.manage().window().maximize();
        driver.quit(); // dong trinh duyet
        driver.close();// dong tab
        driver.findElement(By.id(""));
        driver.findElements(By.id(""));
        driver.getTitle();
        driver.getPageSource();
        driver.getCurrentUrl();
        // Thao tac truc tiep
        driver.manage().deleteAllCookies();
        Option option = (Option) driver.manage();
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().deleteCookie(new Cookie("cookie","eruyieurwe"));

        //wait ngam dinh de tim element

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //wait ngam dinh de thuc thi JS

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(6));
        driver.manage().timeouts().getScriptTimeout();

        // wait ngam dinh de load trang

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

    }

    @Test
    public void TC_02_WebElements() {
        driver = new ChromeDriver();
        driver.get("");
        driver.quit();
    }

    @Test
    public void TC_03() {
        driver = new EdgeDriver();
        driver.get("");
        driver.quit();
    }
}