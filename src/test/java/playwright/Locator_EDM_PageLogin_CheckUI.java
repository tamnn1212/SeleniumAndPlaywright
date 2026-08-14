package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Locator_EDM_PageLogin_CheckUI {
    Playwright playwright;
    Browser browser;
    Page page;
    Locator inputUserName, inputPassword, btnLogin ;
    @BeforeTest
    public void beforeTest() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
        page = browser.newPage();
        page.navigate("https://test-aicongvu.elcomlab.com/dangnhap/");
        inputUserName =  page.locator("xpath=//input[@placeholder='Nhập tên đăng nhập...']");
        inputPassword =  page.locator("//input[@placeholder='Nhập mật khẩu...']");
        btnLogin =  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ĐĂNG NHẬP").setExact(true));
    }
    @Test
    public void TC_01_Verify_the_Login_page_is_displayed_correctly(){
        inputUserName.isVisible();
        inputPassword.isVisible();
        btnLogin.click();
    }
    @Test
    public void TC_02_Verify_the_page_title_is_displayed_correctly() {
        page.getByTitle("ĐĂNG NHẬP HỆ THỐNG").isVisible();
        page.getByTitle("Vui lòng nhập thông tin để truy cập").isVisible();

        Locator logoName = page.locator("css=span.logo-name");
        assertThat(logoName).containsText("GovAssist");

        Locator logoSubtitle = page.locator("css=span.logo-subtitle");
        assertThat(logoSubtitle).hasText("Nền tảng AI hỗ trợ công chức");
    }
    @AfterClass
    public void afterClass(){
        page.close();
        playwright.close();
    }
}
