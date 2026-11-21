package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_30_Button {
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
    public void TC_01_HandleBtn() {
        page.navigate("https://www.fahasa.com/customer/account/create");
        page.locator("li.popup-login-tab-login").click();
        // verify button dang ky disable
        Locator btn = page.locator("button.fhs-btn-login");
        assertThat(btn).hasText("Đăng nhập");
        assertThat(btn).isDisabled();
        assertThat(btn).hasCSS("background-color","rgba(0, 0, 0, 0)");
          // Dien thong tin hop le
         //page.locator("input#login_username").fill("0358892524");
         // page.locator("input#login_password").fill("123456Aa@");
        page.locator("input#login_username").pressSequentially("0358892524",
                new Locator.PressSequentiallyOptions().setDelay(100));
        page.locator("input#login_password").pressSequentially("123456Aa@",
                new Locator.PressSequentiallyOptions().setDelay(100));
        assertThat(btn).isEnabled();
        assertThat(btn).hasCSS("background-color","rgb(201, 33, 39)");
    }

    @Test
    public void TC_02_DefaultCheckBoxAndRdBtn() {
        page.navigate("https://demos.telerik.com/kendo-ui/checkbox/index");
        Locator DualOption = page.locator("//label[normalize-space()='Dual-zone air conditioning']/preceding-sibling::span/input");
        if (!DualOption.isChecked()) {
            DualOption.click();
        }
        assertThat(DualOption).isChecked();
        // un-check
        DualOption.click(); assertThat(DualOption).not().isChecked();
        // radioBtn
        page.navigate("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Locator petrol2 = page.locator("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        if (!petrol2.isChecked()) {
            petrol2.click();
        }
        assertThat(petrol2).isChecked();
    }

    @Test
    public void TC_03_SelectAll() {
        page.navigate("https://automationfc.github.io/multiple-fields/");
        List<Locator> lst = page.locator("input.form-checkbox").all();
        for (Locator i:lst) {
            if (!i.isChecked()){
                i.click();
                assertThat(i).isChecked();
            }
        }
        // un-check
        for (Locator i:lst) {
            if (i.isChecked()){
                i.click();
                assertThat(i).not().isChecked();
            }
        }
        // Chon 1 so option
        for (Locator i:lst) {
            if (!i.isChecked() && i.getAttribute("value").equals("Gallstones")){
                i.click();
                assertThat(i).isChecked();
            }
        }
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}