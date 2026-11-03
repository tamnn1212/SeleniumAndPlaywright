package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_Topic_25_CustomDropdown {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @Test
    public void TC_01() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectDropdownItem("span#speed-button","ul#speed-menu div","Medium");
        Assert.assertEquals(driver
                .findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
                "Medium"
        );
        selectDropdownItem("span#speed-button","ul#speed-menu div","Slower");
        Assert.assertEquals(driver
                        .findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
                "Slower"
        );
    }
    @Test
    public void TC_02() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectDropdownItem("div.dropdown","div.item span.text","Elliot Fu");
        Assert.assertEquals(driver
                        .findElement(By.cssSelector("div.divider")).getText(),
                "Elliot Fu"
        );
    }
    @Test
    public void TC_03_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        editableDropdownItem("input.search","div.item span.text","Afghanistan");
        Assert.assertEquals(driver
                        .findElement(By.cssSelector("div.divider")).getText(),
                "Afghanistan"
        );
    }
    @Test
    public void TC_04_Huawei() {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        driver.findElement(By.cssSelector("div.hwid-dropInpunt")).click();
        editableDropdownItem("input[ht='input_emailregister_search']","span.list-item-text","Argentina");
        Assert.assertEquals(driver
                        .findElement(By.cssSelector("div.divider")).getText(),
                "Argentina"
        );
    }
    private void selectDropdownItem(String parentLct, String childLct, String textItem) {
        // tim locator de mo ra dropdown
        driver.findElement(By.cssSelector(parentLct)).click();
        //driver.findElement(By.cssSelector("ul#speed-menu div"));
        //wait
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions
//                        .presenceOfAllElementsLocatedBy(By.cssSelector(childLct))); // cac element deu duoc load/render trong dom
        // invisibilityOfAllElements : Cac element deu duoc hien thi tren UI
        // 3. Duyet qua cac item
        //List<WebElement> lstOption =  driver.findElements(By.cssSelector(childLct));
        List<WebElement> lstOption =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions
                       .presenceOfAllElementsLocatedBy(By.cssSelector(childLct))); // presenceOfAllElementsLocatedBy: da tra lst Element can duyet
        boolean found = false;
        for (WebElement i:lstOption) {
            // Kiem tra , neu dung thi chon
            String dropDownText = i.getText();
            if (dropDownText.equals(textItem)) {
                i.click();
                found = true;
                break;
            }
        }
        if (found!=true) {
            System.out.println("Khong tim thay option" + textItem);
        }
    }
    private void editableDropdownItem(String parentLct, String childLct, String textItem) {
        // tim locator de mo ra dropdown
        driver.findElement(By.cssSelector(parentLct)).clear(); driver.findElement(By.cssSelector(parentLct)).click();
        List<WebElement> lstOption =  new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.cssSelector(childLct)));
        boolean found = false;
        for (WebElement i:lstOption) {
            String dropDownText = i.getText();
            if (dropDownText.equals(textItem)) {i.click();found = true;break;}
        }
        if (found!=true) {System.out.println("Khong tim thay option" + textItem);}
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}