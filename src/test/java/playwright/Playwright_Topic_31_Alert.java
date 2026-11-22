package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_31_Alert {
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
    public void TC_01_TechPanda() {
        page.navigate("https://live.techpanda.org/");
        page.locator("//a[text()='Mobile']").click();
        page.locator("//a[@title='Xperia']/following-sibling::div//a[text()='Add to Compare']")
                .click();
        assertThat(page.locator("//span[text()='The product Sony Xperia has been added to comparison list.']"))
                .isVisible();
        page.locator("//a[@title='IPhone']/following-sibling::div//a[text()='Add to Compare']")
                .click();
        assertThat(page.locator("//span[text()='The product IPhone has been added to comparison list.']"))
                .isVisible();
        page.locator("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']")
                .click();
        assertThat(page.locator("//span[text()='The product Samsung Galaxy has been added to comparison list.']"))
                .isVisible();
        // clearAll
        // can xu ly alert truoc moi click de cho alert xuat hien
        page.onceDialog(dialog -> {
            Assert.assertEquals(dialog.type(),"confirm");
            Assert.assertEquals(dialog.message(),"Are you sure you would like to remove all products from your comparison?");
            dialog.accept();
             });
        page.locator("//a[text()='Clear All']").click();
        assertThat(page.locator("//li[@class='success-msg']//span[text()='The comparison list was cleared.']"))
                .isVisible();
    }

    @Test
    public void TC_02_BT09_PromptAlert_GitHub() {
        page.navigate("https://automationfc.github.io/basic-form/index.html");
        String keyword = "Automation";
        page.onDialog(dialog -> {
            Assert.assertEquals(dialog.message(),"I am a JS prompt");
            dialog.accept(keyword);
        });
        page.locator("//button[text()='Click for JS Prompt']").click();
        Assert.assertEquals(page.locator("p#result").textContent(),"You entered: " + keyword);
    }

    @Test
    public void TC_03_AuthenticationAlert() {
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        BrowserContext authText = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin","admin"));
        page = authText.newPage();
        page.navigate("http://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(page.locator("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")
                .isVisible());
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}