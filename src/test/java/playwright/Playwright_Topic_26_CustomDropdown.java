package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_26_CustomDropdown {
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
        page.navigate("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectDropdownItem("span#speed-button","ul#speed-menu div","Medium");
        assertThat(page.locator("span#speed-button span.ui-selectmenu-text")).hasText("Medium");
        selectDropdownItem2("span#speed-button","ul#speed-menu div","Slow");
        assertThat(page.locator("span#speed-button span.ui-selectmenu-text")).hasText("Slow");
    }
    @Test
    public void TC_02() {
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectDropdownItem2("div.dropdown","div.item span.text","Elliot Fu");
        assertThat(page.locator("div.divider")).hasText("Elliot Fu");
    }
    @Test
    public void TC_03_Editable() {
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        editDropdownItem("input.search","div.item span.text","Afghanistan");
        assertThat(page.locator("div.divider")).hasText("Afghanistan");
    }
    @Test
    public void TC_04_Huawei() {
        page.navigate("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        page.locator("div.hwid-dropInpunt").click();
        editDropdownItem("input[ht='input_emailregister_search']","span.list-item-text","Argentina");
        assertThat(page.locator("div.hwid-dropInpunt span.hwid-select-text")).hasText("Argentina");
    }
    private void selectDropdownItem(String parentLct, String childLct, String textItem) {
        page.locator(parentLct).click();
        Locator item = page.locator(childLct);
        boolean found = false;
        for (int i=0 ; i<=page.locator(childLct).count(); i++) {
            Locator itemi = item.nth(i);
            String dropDownText = itemi.textContent();
            if (dropDownText.equals(textItem)) {
                itemi.click();
                found = true;
                break;
            }
        }
        if (found!=true) {
            System.out.println("Khong tim thay option: " + textItem);
        }
    }
    private void selectDropdownItem2(String parentLct, String childLct, String textItem) {
        page.locator(parentLct).click();
        page.locator(childLct).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();
    }
    private void editDropdownItem(String parentLct, String childLct, String textItem) {
        page.locator(parentLct).fill(textItem);
        page.locator(childLct).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}