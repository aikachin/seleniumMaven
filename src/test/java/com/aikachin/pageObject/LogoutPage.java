package com.aikachin.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author: Aikachin
 * @Description: 退出账号的登录状态
 * @Date: Created in 10:03 2017/10/23 0023.
 * @Modified by :
 */
public class LogoutPage {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
