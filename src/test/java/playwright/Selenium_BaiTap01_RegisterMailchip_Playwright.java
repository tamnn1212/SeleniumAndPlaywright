package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Selenium_BaiTap01_RegisterMailchip_Playwright {
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
    public void TC_01_onlyNumber() {
        page.navigate("https://login.mailchimp.com/signup/");
        page.getByText("Business email").fill("ngthitamth@gmail.com");
        page.getByText("Password", new Page.GetByTextOptions().setExact(true)).fill("123");
        assertThat(page.getByText("One number")).hasCSS("color","rgb(0, 133, 71)");
    }
    @Test
    void TC_02_addLowerChar() {
        page.navigate("https://login.mailchimp.com/signup/");
        page.getByText("Business email").fill("ngthitamth@gmail.com");
        page.getByText("Password", new Page.GetByTextOptions().setExact(true)).fill("123abc");
        assertThat(page.getByText("One number")).hasCSS("color","rgb(0, 133, 71)");
        assertThat(page.getByText("One lowercase character")).hasCSS("color","rgb(0, 133, 71)");
    }
    @AfterClass
    public void afterClass(){
        //browser.close();
        //playwright.close();
    }

}