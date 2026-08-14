package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

public class Topic_09_FindElement_Opencart {
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
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        // top -left
        Locator top_nav_lef = page.locator("xpath=//span[text()='Currency']"); top_nav_lef.click();

        //page.getByText("Currency").click();
        page.locator("//button[contains(text(),'Euro')]");
        page.locator("//button[contains(text(),'Pound')]");
        page.locator("//button[contains(text(),'Dollar')]");

        // Icon phone contact . click()
        page.locator("//div[@id='top-links']//a[contains(@href,'contact')]").click();
        // Phone contact information .textContent()
        page.locator("//div[@id='top-links']//a[contains(@href,'contact')]/following-sibling::span").textContent();

        // My account .click()
        page.locator("//a[@title='My Account']").click();
        // My account > register
        page.locator("//a[@title='My Account']/following-sibling::ul//a[contains(@href,'register')]");
        // My account > Login
        page.locator("//a[@title='My Account']/following-sibling::ul//a[contains(@href,'login')]");

        // wishlist
        page.locator("//div[@id='top-links']//a[contains(@href,'wishlist')]");


        // Shopping cart . click()
        page.locator("//div[@id='top-links']//a[contains(@href,'checkout/cart')]").click();
        page.locator("//span[text()='Shopping Cart']");

        // checkout
        page.locator("//span[text()='Checkout']");

        // logo ( can lay thong tin logo va click() )

        page.locator("//div[@id='logo']");

        //navbar

        page.locator("//a[text()='Desktops']").hover();
            // menu con
            //page.locator("//a[text()='Desktops']/following-sibling::div//a[contains(text(),'PC')]").click();
            page.locator("//a[contains(text(),'PC')]").click();
            page.locator("//a[contains(text(),'Mac')]");

            page.locator("//a[contains(text(),'Show All Desktops')]");

        page.locator("//a[text()='Laptops & Notebooks']");
        page.locator("///a[text()='Components']");
        page.locator("Cameras");

        // breadcrumb
        page.locator("//ul[@class='breadcrumb']//a[contains(@href,\"home\")]");

        // content

        page.locator("//div[@id='content']/h1");
        // notice
        page.locator("//div[@id='content']//p").textContent();
        // direct to loginPage
        page.locator("//a[text()='login page']");

        // form register
        //title
        page.locator("//fieldset[@id='account']/legend").textContent();
        // field Personal Details
        page.locator("//input[@id='input-firstname']").fill("Tammm");
        page.locator("//input[@id='input-lastname']").fill("Nguyen");
        page.locator("//input[@id='input-email']").fill(" ");
        page.locator("//input[@id='input-telephone']").fill(" ");
        // Your Password
        page.locator("//*[@id=\"content\"]/form/fieldset[2]/legend").textContent();
        // field password
        page.locator("//input[@id='input-password']");
        page.locator("//input[@id='input-confirm']");
        // New letter
        page.locator("//label[text()='Subscribe']");
        page.locator("//input[@name = 'newsletter' and @value='0']");
        page.locator("//input[@name = 'newsletter' and @value='1']");

        //--
        page.locator("//div[@class='list-group']//a[text()='Login']");
        page.locator("//div[@class='list-group']//a[text()='Register']");
        page.locator("//div[@class='list-group']//a[text()='Forgotten Password']");
        page.locator("//div[@class='list-group']//a[text()='My Account']");
        page.locator("//div[@class='list-group']//a[text()='Address Book']");
        page.locator("//div[@class='list-group']//a[text()='Wish List']");
        page.locator("//div[@class='list-group']//a[text()='Order History']");

        // --
        page.locator("//a[text()='Privacy Policy']");
        page.locator("//input[@name='agree']");
        page.locator("//input[@value='Continue']");

        //-- footer
        page.locator("//footer//a[text()='About Us']");
        page.locator("//footer//a[text()='Delivery Information']");

        page.locator("//footer//p").textContent();
    }
    @Test
    public void Test_locator() throws InterruptedException {
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