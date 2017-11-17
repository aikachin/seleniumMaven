package com.aikachin.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

import static com.aikachin.bakcup.testVariablesSetting.*;

/**
 * @Author: AIkachin
 * @Description: Junit
 * @Date: Created in 15:39 2017/10/18 0018.
 * @Modified by :
 */
public class testH5 {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        // driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    // @Test
    public void testHTML5() throws Exception {
        FirefoxProfile profile = new ProfilesIni().getProfile("geolocation");
        profile.setPreference("geo.wifi.url", "./src/test/Selenium 2/location.json");
        driver = new FirefoxDriver(profile);
        driver.get(URL_OF_W3SCHOOLS);

        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/p[2]/button")).click();
        Thread.sleep(3000);
    }

    // @Test
    public void testWebDriverEvent() throws Exception {
        driver = new FirefoxDriver();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        MyEventListener myEventListener = new MyEventListener();
        eventFiringWebDriver.register(myEventListener);

        eventFiringWebDriver.get(URL_OF_DOUBAN);
        Thread.sleep(1000);
        // WebElement ele = eventFiringWebDriver.findElement(By.xpath(".//*[@id='anony-nav']/div[2]/form/span[2]/input"));
        List<WebElement> list = eventFiringWebDriver.findElements(By.xpath(".//*[@id='anony-nav']/div[2]/form/span[2]/input"));
        System.out.println(list);
        eventFiringWebDriver.get(URL_OF_BAIDU);
        eventFiringWebDriver.navigate().back();
        Thread.sleep(1000);
        WebElement ele = eventFiringWebDriver.findElement(By.xpath(".//*[@id='anony-nav']/div[2]/form/span[2]/input"));
        list.add(ele);
        System.out.println(list);
        // ele.click();
        // eventFiringWebDriver.findElement(By.name("q")).click();
    }

    @Test
    public void testPage() throws Exception {
        driver = new FirefoxDriver();
        driver.get(URL_OF_TEST_PAGE);
        driver.get(URL_OF_TEST_PAGE2);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        System.out.println(driver.findElement(By.id("submit")).getAttribute("type"));

    }
}
