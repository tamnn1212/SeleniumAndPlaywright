package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
        Locator RainSensorOption = page.locator("//label[normalize-space()='Dual-zone air conditioning']/preceding-sibling::span/input");
        Locator TowbarPreparationOption = page.locator("//label[normalize-space()='Dual-zone air conditioning']/preceding-sibling::span/input");

        DualOption.check();
        RainSensorOption.setChecked(true); // check
        Assert.assertTrue(RainSensorOption.isChecked());
        assertThat(DualOption).isChecked();
        Assert.assertTrue(DualOption.isChecked());
        // un-check
        DualOption.uncheck(); assertThat(DualOption).not().isChecked();
    }

    @Test
    public void TC_03_SelectAll() {
        page.navigate("https://automationfc.github.io/multiple-fields/");
        List<Locator> lst = page.locator("input.form-checkbox").all();
        // Kiem tra chua duoc chon
        for (Locator i:lst) {
                assertThat(i).not().isChecked();
        }
        // check toan bo va kiem tra da chọn
        for (Locator i:lst) {
            i.check();
            assertThat(i).isChecked();

        }
        // un-check
        for (Locator i:lst) {
            i.setChecked(false);
            assertThat(i).not().isChecked();
        }
        // Chon 1 so option
        for (Locator i:lst) {
            if (!i.isChecked() && i.getAttribute("value").equals("Gallstones")){
                i.check();
                assertThat(i).isChecked();
            }
        }
    }
    // RadioBtn

    @Test
    public void TC_04_RadioBtn() {
        page.navigate("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Locator petrol2 = page.locator("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        petrol2.check();
        assertThat(petrol2).isChecked();
    }

    // Custom Radio Btn

    @Test
    public void TC_05_Ubuntu_CustomRadiBtn() {
        page.navigate("https://login.ubuntu.com/");
        Locator idNewUser = page.locator("input#id_new_user");
        Locator idAcceptTos = page.locator("input#id_new_user");

        if(!idNewUser.isChecked()) {
            idNewUser.dispatchEvent("click");
        }
        assertThat(idNewUser).isChecked();
        if (!idAcceptTos.isChecked()){
            idAcceptTos.evaluate("el => el.click()");
        }
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}