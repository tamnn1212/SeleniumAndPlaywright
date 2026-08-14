package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_Topic_08_Locator {
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
    public void TC_01_GetByRole() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        // Get by role
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account"));
        // new Page.GetByRoleOptions(): Tạo object chua cac heading
        // Chỉ tìm heading có accessible name là "Account". Khong phai text hien thi
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Yes"));
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("No"));
        int soluong = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).count();
        System.out.println("so luong btn contine tim duoc: " + soluong);
    }
    @Test
    public void TC_02_GetByText() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        int i = page.getByText("Login").count(); System.out.println(i);
    }
    @Test
    public void TC_03_GetByLabel() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByLabel("First Name");
        page.getByLabel("Last Name");page.getByLabel("Email");
        page.getByLabel("Password");
    }
    @Test
    public void TC_04_GetByPlaceHolder() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByPlaceholder("First Name").fill("Tâmm");
        page.getByPlaceholder("Last Name").fill("Smith");
    }
    @Test
    public void TC_05_GetByAltText() {
        page.navigate("https://opencart.abstracta.us/index.php?route=product/category&path=20_27");
        page.getByAltText("iMac").click();
    }
    @Test
    public void TC_06_GetByTitle() {
        page.navigate("https://opencart.abstracta.us/index.php?route=product/category&path=20_27");
        page.getByTitle("My Account").click();
    }
    @Test
    public void TC_07_CssAndXpath() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.locator("css=input#input-firstname").fill("Tam");
        page.locator("xpath=//input[@id='input-lastname']").fill("Nguyen");
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}