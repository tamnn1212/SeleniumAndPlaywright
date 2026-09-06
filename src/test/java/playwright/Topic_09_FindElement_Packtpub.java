package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_FindElement_Packtpub {
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
    public void TC_01() {
        page.navigate("https://www.packtpub.com/en-us/search?q=Playwright&country=us&language=en&format=eBook");

    }
    @Test
    public void Test_locator() throws InterruptedException {
        page.navigate("https://www.packtpub.com/en-us/search?q=Playwright&country=us&language=en&format=eBook");
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}