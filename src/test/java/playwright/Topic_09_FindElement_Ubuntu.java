package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_FindElement_Ubuntu {
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
// Header
        page.navigate("https://login.ubuntu.com/");

        page.locator("//header//a[@id='u1-logo']");
        page.locator("//header//a[@id='login-link']");
        page.locator("//h2[contains(@class,'login-title')]//span[1]");
        page.locator("//h2[contains(@class,'login-title')]//span[2]");

        page.locator("//div[@class='login-form']//input[@id='id_email']");

        // have account
        page.locator("//span[contains(text(),'I have an Ubuntu One account')]");
        page.locator("//input[@placeholder='Password']");

        // do not have account

        page.locator("//input[@id='id_new_user']");
        page.locator("//input[@placeholder='Your full name']");

        page.locator("//input[@placeholder='Username']");
        //help pass => get content
        page.locator("//ul[contains(@class,'form-help-text')]");

        //password

        page.locator("//input[contains(@placeholder,'password')]").fill("123456");
        page.locator("//div[contains(@class,'create-form ')]//input[@id='id_password']");

        page.locator("//div[contains(@class,'create-form ')]//input[@id='id_passwordconfirm']");

        page.locator("//input[@type='checkbox' and @name='accept_tos']");
        page.locator("//span[text()='Create account']");

        page.locator("//span[text()='Log in']");

        // forgot password
        page.locator("//input[@name='forgot_password']");

    }
    @Test
    public void TC_01_forgotPassword(){
        page.navigate("https://login.ubuntu.com/");
        page.locator("//input[@name='forgot_password']").click();
    }
    @Test
    public void TC_02_clickLoginSuccess() {
        page.navigate("https://login.ubuntu.com/");
        page.locator("//span[contains(text(),'I have an Ubuntu One account')]").click();
        page.locator("//div[@class='login-form']//input[@id='id_email']").fill("tam123@gmail.com");
        page.locator("//input[@placeholder='Password']").fill("123456");
        page.locator("//span[text()='Log in']").click();
    }
    @Test
    public void TC_03_getMessage() {
        page.navigate("https://login.ubuntu.com/");
        page.locator("//span[contains(text(),'I have an Ubuntu One account')]").click();
        page.locator("//div[@class='login-form']//input[@id='id_email']").fill("tam123@gmail.com");
        page.locator("//input[@placeholder='Password']").fill("123456");
        page.locator("//span[text()='Log in']").click();

        // Lay mess
        String message = page.locator("//p[contains(@class,'notification')]").textContent();
        System.out.println(message);
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}