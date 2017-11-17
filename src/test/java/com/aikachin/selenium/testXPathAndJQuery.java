package com.aikachin.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.aikachin.bakcup.testVariablesSetting.CHROME_DRIVER_PATH;


/**
 * Created by Administrator on 2017/10/11 0011.
 */
public class testXPathAndJQuery {
    WebDriver driver;
    JavascriptExecutor jse;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        jse =(JavascriptExecutor) driver;
    }

    @Test
    public void testXPath() throws Exception {
        driver.get("https://www.douban.com/");
        driver.manage().window().maximize();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='lzform']/fieldset/div[3]/a")));
        driver.findElement(By.xpath(".//*[@id='lzform']/fieldset/div[3]/a")).click();
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String text = driver.getTitle();
        System.out.println(text);
        Assert.assertEquals("欢迎加入豆瓣", text);
    }

    //@Test
    //失败！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    public void testJQuery() throws InterruptedException {
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        driver.get("https://jquery.org/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitForAjaxLoad(jse);
        //load jQuery dynamically if needed
        //injectjQueryIfNeeded();

        driver.manage().window().maximize();
        List<WebElement> list = (List<WebElement>)jse.executeScript("return JQuery.find('main-top')");
        Assert.assertEquals(11, list.size());
        Assert.assertEquals("Members", list.get(3).getText());

    }

    //from stackFlow
    public void waitForAjaxLoad(JavascriptExecutor jse) throws InterruptedException{
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        if((Boolean) jse.executeScript("return window.jQuery != undefined")){
            while(!(Boolean) jse.executeScript("return jQuery.active == 0")){
                Thread.sleep(1000);
            }
        }
        return;
    }

    //from the book
    private void injectjQueryIfNeeded() {
        if (! jQueryLoaded()) {
            injectjQuery();
        }
    }
    public Boolean jQueryLoaded() {
        Boolean loaded = true;
        try {
            loaded = (Boolean) jse.executeScript("return jQuery() != null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }
    public void injectjQuery() {
        //load jQuery dynamically in the head of web page
        jse.executeScript(
                "var menuID = document.getElementById('menu-top');"+
                        "var newScript = document.createElement('script');"+
                        "newScript.type = 'text/javascript';"+
                        "newScript.src = 'https://code.jquery.com/jquery-3.2.1.min.js';"+
                        "menuID.appendChild(newScript)");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }
}
