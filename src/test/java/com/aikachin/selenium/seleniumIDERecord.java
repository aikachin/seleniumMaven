package com.aikachin.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 14:59 2017/10/19 0019.
 * @Modified by :
 */
public class seleniumIDERecord {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private BufferedReader br;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.cnblogs.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @Test
    public void testSeleniumIDERecord() throws Exception {

        driver.get(baseUrl + "");
        driver.findElement(By.linkText("短消息")).click();
        driver.findElement(By.linkText("撰写新短消息")).click();
        driver.findElement(By.id("txtIncept")).clear();
        driver.findElement(By.id("txtIncept")).sendKeys("fredlic");
        driver.findElement(By.id("txtTitle")).clear();
        driver.findElement(By.id("txtTitle")).sendKeys("hello");
        driver.findElement(By.id("txtContent")).clear();
        driver.findElement(By.id("txtContent")).sendKeys("as u wish");
        driver.findElement(By.id("btnSend")).click();
        driver.findElement(By.cssSelector("ul.quick_link > li > a")).click();
        driver.findElement(By.id("txtIncept")).clear();
        driver.findElement(By.id("txtIncept")).sendKeys("seleniumpageobject");
        driver.findElement(By.id("txtTitle")).clear();
        driver.findElement(By.id("txtTitle")).sendKeys("thks");
        driver.findElement(By.id("txtContent")).clear();
        driver.findElement(By.id("txtContent")).sendKeys("xiexie");
        driver.findElement(By.id("btnSend")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
