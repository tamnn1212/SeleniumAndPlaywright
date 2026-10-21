package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_Topic_23_TextBox_TextArea {
    WebDriver driver;
    Browser browser;
    Page page;
    Playwright playwright;
    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50)

        );
        page = browser.newPage();
    }
    @Test
    public void TC_01() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Deprecated
        page.locator("input[name='username']").fill("Admin");
        page.locator("input[name='password']").fill("admin123");
        page.locator("button.orangehrm-login-button").click();
       // page.locator("button.orangehrm-login-button").click();
        page.getByText("PIM").click();
        page.getByText("Add Employee").click();
        // Nhap thong tin hop le
        page.getByPlaceholder("First Name").fill("Rebecca");
        page.getByPlaceholder("Last Name").fill("Armstrong");
        // Lay thong tin EmployeeId tu dong
        String EmployeeId = page.locator("//label[text()='Employee Id']/parent::div/following-sibling::div/input").inputValue();
        // save
        page.getByText("Save").click();
        // Verify
        assertThat(page.locator("input[name='firstName']")).hasValue("Rebecca");
        assertThat(page.locator("input[name='lastName']")).hasValue("Armstrong");
        assertThat(page.locator("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .hasValue(EmployeeId);
        page.getByText("Immigration").click();
        page.locator("//h6[text()='Assigned Immigration Records']/following-sibling::button")
                .click();
        page.locator("//label[text()='Number']/parent::div/following-sibling::div/input")
                .fill("032514587");
//        page.locator("//label[text()='Comments']/parent::div/following-sibling::div/textarea")
//                .click();
        page.getByPlaceholder("Type Comments here").fill("Armstrong");
        }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}