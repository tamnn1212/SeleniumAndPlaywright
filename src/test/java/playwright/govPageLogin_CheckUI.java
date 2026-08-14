package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class govPageLogin_CheckUI {
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
    public void TC_01_FindElement_Default() {
        page.navigate("https://test-aicongvu.elcomlab.com/dangnhap/");
        // Get by role
        page.getByPlaceholder("Nhập tên đăng nhập..."); // input Ten dang nhap
        page.getByPlaceholder("Nhập mật khẩu...");
        //int soluong = page.getByPlaceholder("Nhập mật khẩu...").count();// input Mat khau
        //System.out.println(soluong);
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Nhớ mật khẩu"));
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Đăng nhập"));
    }
    @Test
    public void TC_02_FindElement_LoginSuccess() {
        page.navigate("https://test-aicongvu.elcomlab.com/dangnhap/");

        page.getByPlaceholder("Nhập tên đăng nhập...").fill("tamnt"); // input Ten dang nhap
        page.getByPlaceholder("Nhập mật khẩu...").fill("Pass123$");// input Mat khau

        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Đăng nhập")).click();
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}