package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class Playwright_Topic_17_Commands_01 {
    Browser browser;
    Page page;
    Playwright playwright;
    BrowserType browserType;
    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        //
        browserType = playwright.firefox();
        browserType = playwright.chromium();
        browserType = playwright.webkit();
        playwright.close();
        // handle api testing
        APIRequest apiRequest  = playwright.request();
        Selectors selectors = playwright.selectors();

        // browserType
        String projectPath = System.getProperty("user.dir");
        browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50)
                .setDownloadsPath(Path.of(projectPath + "downloadFile"))
                .setExecutablePath(Path.of("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"))
                .setProxy("")
        );
        // chay voi profile - Chi dung chrome / edge
        browserType.launchPersistentContext(Path.of(""));
        // lay ra duong dan cai dat cua trinh duyet
        browserType.executablePath();
        // Cloud Testing
        browserType.connect("");
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}