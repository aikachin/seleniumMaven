package com.aikachin.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.aikachin.bakcup.testVariablesSetting.URL_OF_DOUBAN;
import static com.aikachin.bakcup.testVariablesSetting.URL_OF_TEST_PAGE;

/**
 * @Description: Testng
 * @param:
 * @return:
 * @Author: Aikachin
 * @Date: 2017/10/19 0019
 */
public class testAction {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        //System.setProperty("webdriver.firefox.bin", testVariablesSetting.FirefoxDriverPath);
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    // @Test
    //调用时间监听MyEventListener类测试监听结果
    public void testName() throws Exception {
        MyEventListener listener = new MyEventListener();

        driver.get(URL_OF_DOUBAN);
        listener.afterNavigateTo(driver.getCurrentUrl(), driver);

        WebElement ele1 = driver.findElement(By.name("q"));

        ele1.sendKeys(Keys.chord(Keys.SHIFT, "selenium"));
        listener.afterClickOn(driver.findElement(By.xpath(".//*[@id='anony-nav']/div[2]/form/span[2]/input")), driver);
        driver.findElement(By.xpath(".//*[@id='anony-nav']/div[2]/form/span[2]/input")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        listener.afterNavigateBack(driver);
    }

    @Test
    public void testKeysAction() throws Exception {
        driver.get(URL_OF_TEST_PAGE);
        WebElement ele1 = driver.findElement(By.id("UserName"));
        ele1.sendKeys("aikachin");
        ele1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        ele1.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        driver.findElement(By.id("UserEmail")).sendKeys(Keys.chord(Keys.CONTROL, "v"));
        Thread.sleep(3000);
    }
}
