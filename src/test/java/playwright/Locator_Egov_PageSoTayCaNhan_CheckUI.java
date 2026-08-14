package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Locator_Egov_PageSoTayCaNhan_CheckUI {
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
        btnLogin =  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Đăng nhập").setExact(true));
    }
    @Test
    public void TC_01_Verify_the_page_title_is_displayed_correctly(){
        inputUserName.fill("tamnt1");
        inputPassword.fill("Pass123$");
        page.locator("body").click();
        btnLogin.click();

        page.waitForTimeout(2000);

        //get title
        Locator tiltlePage = page.locator("css=div.actions-left>span");
        tiltlePage.textContent(); System.out.println(tiltlePage.textContent());
    }
    @Test public void TC_02() {

        inputUserName.fill("tamnt1");
        inputPassword.fill("Pass123$");
        page.locator("body").click();
        btnLogin.click();

        page.waitForTimeout(2000);

        //page.getByRole(AriaRole.MENUBAR, new Page.GetByRoleOptions().setName("Quản trị danh mục")).click();
        //page.getByRole(AriaRole.MENU,new Page.GetByRoleOptions().setName("Quản trị danh mục")).click();
        page.getByText("Quản trị danh mục").click();
        page.getByText("Loại văn bản").click();

    }
    @AfterClass
    public void afterClass(){
        page.close();
        playwright.close();
    }
}
