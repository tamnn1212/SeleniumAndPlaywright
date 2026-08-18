package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_FindElement_Techpanda {
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
        page.navigate("https://live.techpanda.org/index.php/checkout/cart/");
        page.locator("//p[@class='welcome-msg']");
        page.locator("//header//span[text()='Account']");
        page.locator("//header//span[text()='Cart']");
// Search
        page.locator("//input[@id='search']");
        // icon search
        page.locator("//button[@title='Search']");
        // menu
        page.locator("//nav[@id='nav']//a[text()='Mobile']");
        page.locator("//nav[@id='nav']//a[text()='TV']");
        // Footer
        page.locator("//div[@class='footer']//span[text()='Company']");
        page.locator("//div[@class='footer']//a[text()='About Us']");
        page.locator("//div[@class='footer']//a[text()='Contact Us']");
        page.locator("//div[@class='footer']//a[text()='Customer Service']");
        page.locator("//div[@class='footer']//a[text()='Privacy Policy']");
        page.locator("//input[@id='newsletter']");
        page.locator("//button[@title='Subscribe']");
        page.locator("//address[@class='copyright']");
    }
    @Test
    public void Test_locator() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        String text = page.locator("//div[@id='content']//h1").textContent();
        System.out.println(text);

    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}