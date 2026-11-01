package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_25_DefaultDropdown {
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
        page.navigate("https://www.saucedemo.com/");
        page.locator("input#user-name").fill("standard_user");
        page.locator("input#password").fill("secret_sauce");
        page.locator("input#login-button").click();

        Locator prdDropdown = page.locator("select.product_sort_container");
//        page.selectOption("select.product_sort_container","za");
        // value or text
        prdDropdown.selectOption("za");
        // Verify
//        Assert.assertEquals(prdDropdown.inputValue(),"Name (Z to A)");
        Assert.assertEquals(page.locator("span.active_option").textContent(),"Name (Z to A)");
        prdDropdown.selectOption("Price (low to high)");
//        Assert.assertEquals(prdDropdown.inputValue(),"Price (low to high)");
        Assert.assertEquals(page.locator("option:checked").textContent(),"Price (low to high)");
            // index
        prdDropdown.selectOption(new SelectOption().setIndex(0));
//        Assert.assertEquals(prdDropdown.inputValue(),"Name (A to Z)");
        Assert.assertEquals(page.locator("option:checked").textContent(),"Name (A to Z)");
        // only value
        prdDropdown.selectOption(new SelectOption().setValue("za"));
        Assert.assertEquals(page.locator("option:checked").textContent(),"Name (Z to A)");
        // is Multiple
        Assert.assertNull(prdDropdown.getAttribute("multiple"));
        // item
        Locator drOption = prdDropdown.locator("option");
        //Assert.assertEquals(drOption.count(),4);
        assertThat(drOption).hasCount(4);
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}