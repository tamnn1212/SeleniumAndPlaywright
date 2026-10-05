package playwright;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class Playwright_Topic_17_Commands_Demo {
    Browser browser;
    Page page;
    Playwright playwright;
    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        BrowserType browserType = playwright.chromium();
        System.out.println("chrome path: " + browserType.executablePath());
    }
    @Test
    void TestDemo() {

    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}