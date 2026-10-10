package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.Geolocation;
import com.microsoft.playwright.options.Margin;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playwright_Topic_18_Commands_Page {
    Browser browser;
    Page page;
    Playwright playwright;
    BrowserType browserType;
    BrowserContext browserContext;
    @BeforeClass
    public void beforeClass(){
        // Page tuong duong ( selenium web-element)
        playwright = Playwright.create();
        //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
        page = browser.newPage();
    }
    @Test
    void TC_01() {
        // navigation
        page.navigate("");
        page.reload();
        page.goBack();
        page.goForward();
        page.goBack(new Page.GoBackOptions()); // optional khi go back
        page.url(); // tra ve url cua page hien tai
        page.waitForURL("**//dashboard"); // doi url khop pattern - su dung sau khi submit form
        page.waitForLoadState(); // doi trang thai tai trang

        // Locator
            // role
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("")); // web component ( textbox, button, link ...)
            // label
        page.getByLabel("Textbox");
            // Place holder
        page.getByPlaceholder("Nhap ho ten");
            // text
        page.getByText("Nhap ho ten");
            // alt Text (use for image)
        page.getByAltText("");
            // title
        page.getByTitle("Nhap ho ten"); // thuoc tinh title
            // Dung Css or Xpath
        page.locator("");

        // Action
        page.click("");
            // khuyen khich click = locator
            Locator locator = page.locator("Nhap ho ten");
            locator.click();
        page.dblclick("");
        page.hover("");
        page.fill("","");
        page.check(""); // use for radio
        page.uncheck("");
        page.selectOption("",""); // Tinh da hinh
        page.focus("");

        page.dragAndDrop("div#column-a","div#column-b"); // keo tha element
        // selenium thong qua action
        //        new Actions(driver).dragAndDrop(driver.findElement(By.xpath("//*[@id='content']")), webElement).perform();

        String projectPath = System.getProperty("user.dir");
        page.setInputFiles("", Paths.get(projectPath + "Upload/Test"));
        page.setInputFiles("", Path.of(projectPath + "Upload/UploadTest"));

        // upload nhieu file
        page.setInputFiles("", new Path[]{
                Paths.get(projectPath + "Upload/Test"),
                Paths.get(projectPath + "Upload/Test2")
        });
        // get text
        page.textContent("div#column-a");
        page.innerText("div#column-b");
        page.innerHTML("div#column-b");

        page.getAttribute("div#column-a","placeholder");
        // Verify
    }
    @Test
    void TC_02() {
        page.navigate("https://www.fahasa.com/");
        // isVisible: co the nhin thay
        page.isVisible("");
        page.isEditable("");
        page.isDisabled("");
        page.isChecked("");
        page.isEditable("");

        page.isClosed();
    }
    @Test
    void TC_04() {
        page.navigate("https://www.fahasa.com/");
        // wait
        page.waitForSelector("div#column-b");

        page.waitForFunction("jsFunction");
        page.waitForTimeout(5000); // Cho 5s ~ Thread.sleep?
        //page.waitForEvent();

        String domainName = (String) page.evaluate("document.domain"); // Ep kieu tuong minh
        // page.evaluate("document.domain") => object
        // (String) page.evaluate("document.domain") => ep qua string
    }
    @Test
    void TC_05_pageScreenShot() {
        page.navigate("https://live.techpanda.org/");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/screenshots/desktops.png"))
                .setFullPage(true));
    }
    @Test
    void TC_06_pagePdf() {
        // Chi chay dc voi chrome / edge
        page.navigate("https://live.techpanda.org/");
        page.pdf(new Page.PdfOptions()
             .setPath(Paths.get("target/pdf/desktops.pdf"))
                .setFormat("A4")
                .setPrintBackground(true) // Giu mau nen
                .setLandscape(true)
                .setMargin(new Margin().setTop("1cm").setBottom("1cm").setLeft("1cm").setRight("1cm"))
        );
    }
    @Test
    void TC_07_pageVideo() {
        page.navigate("https://live.techpanda.org/");
        page.video();
    }
    @Test
    void TC_08_pageAlert() {
        page.navigate("https://live.techpanda.org/");
    }
    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }
}