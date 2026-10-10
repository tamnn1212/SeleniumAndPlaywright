package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_05_Ex_Commands {
    WebDriver driver;
    Browser browser;
    Page page;
    Playwright playwright;
    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        page = browser.newPage();
    }
    @Test
    void TC_01_URL() {
        page.navigate("http://live.techpanda.org/");
        Assert.assertEquals(page.url(),"http://live.techpanda.org/index.php/customer/account/login/");
        page.locator("//div[@class='footer']//a[text()='My Account']").click();
        Assert.assertEquals(page.url(),"http://live.techpanda.org/index.php/customer/account/create/");
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}