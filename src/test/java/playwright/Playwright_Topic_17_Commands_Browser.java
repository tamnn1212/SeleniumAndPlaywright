package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.Geolocation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playwright_Topic_17_Commands_Browser {
    Browser browser;
    Page page;
    Playwright playwright;
    BrowserType browserType;
    BrowserContext browserContext;
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
        browserType.connectOverCDP("");

        // Browser
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        // kiem tra
        browser.isConnected(); // kiem tra broser co dang chay k
        browser.contexts(); // danh sanh browser dang mo
        browser.browserType(); // tra ve type cua browser// chrome, firefox. webkit
        if (browser.browserType().name().equals("chrome")) {
            //action
        }
        browser.close();// close browser
        browser.version(); // tra ve version cua browser ;
        browser.newPage();//tra ve page de thao tac voi element
        browser.newContext();// tra ve context de thao tac voi window/tab

        // browserContext
        browserContext = playwright.firefox().launchPersistentContext(Path.of(""));
        browserContext.newPage();
            // get Cookie
        List<Cookie> cookies = browserContext.cookies();
            // Gan cookies
        browserContext.addCookies(cookies);

        browserContext.setGeolocation(new Geolocation(12324,54545));// set vi tri
        browserContext.setOffline(true); // gia lap k co mang
        browserContext.grantPermissions(new ArrayList<>());
        browserContext.grantPermissions(Arrays.asList("geolocation","camera"));

        // Page

    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}