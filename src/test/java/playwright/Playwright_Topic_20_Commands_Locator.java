package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.Margin;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_20_Commands_Locator {
    Browser browser;
    Page page;
    Playwright playwright;
    BrowserType browserType;
    BrowserContext browserContext;
    Locator locator,row, dataRow, checkedRow, lastToast,inputText,button,checkbox, textarea;
    @BeforeClass
    public void beforeClass(){
        // Page tuong duong ( selenium web-element)
        playwright = Playwright.create();
        //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
        page = browser.newPage();
    }
    @Test
    void TC_01_Commands() {
        //driver.findElement(By.cssSelector("div.footer")).findElement(By.xpath("//a[@title='My Account']"));
        locator = page.locator("div.footer");
        locator.getByTitle("My Account");
        // Chaining
    }
    @Test
    void TC_02_Chaining() {
        //driver.findElement(By.cssSelector("div.footer")).findElement(By.xpath("//a[@title='My Account']"));
        locator = page.locator("div.footer");
        locator.locator("//a[@title='My Account']");
        // locator.filter
        // Loc theo text: Tim dong chua ten, sau do click vao edit
        row = page.locator("div.?").filter(new Locator.FilterOptions().setHasText("Jonh"));
        row.getByRole(AriaRole.BUTTON,new Locator.GetByRoleOptions().setName("Edit")).click();
        // Loc theo khong chua text
        dataRow = page.locator("").filter(new Locator.FilterOptions().setHasNotText("No record"));

        // Loc theo dong co chua phan tu cu the ( ex: da tich chon)
        checkedRow = page.locator("").filter(new Locator.FilterOptions().setHas(page.locator("input[type='checkbox']:checked")));

        // Lay phan tu thu n trong danh sach
        row.nth(2); // Lay phan tu thu 3 trong th filter tren co nhieu ket qua
        // Lay phan tu dau cuoi
        page.locator("").first().click();

        lastToast = page.locator("toast").last(); // lay toast cuoi cung neu co nhieu toast chong len nhau
        assertThat(lastToast).hasText("");

        // locator.all
        List<Locator> list = page.locator("?").all();
        for (Locator i:list){
            System.out.println(i.textContent());
        }
        // and or
        Locator btn = page.locator("button").and(page.getByText("save")); btn.click();
       // Locator save = page.locator("button").or(page.getByText("save")); btn.click();
    }
    @Test
    void TC_02_Actions() {
        locator = page.locator("div.footer"); locator.click();
        locator.dblclick();
        inputText.fill(""); // xoa noi dung cu va nhap noi dung moi
        inputText.clear(); // xoa noi dung
        inputText.pressSequentially("join" , new Locator.PressSequentiallyOptions().setDelay(100)); // delay 100 ms sau moi ky tu
        button.press("ENTER");

        checkbox.setChecked(true); // Luon luon check phan tu
        inputText.selectOption("Value 1");
        button.hover();
        inputText.focus();
        inputText.blur(); // out ra 1 sk vua focus

        inputText.dragTo(row);

        textarea.inputValue();
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}