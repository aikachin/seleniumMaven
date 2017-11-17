package com.aikachin.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @Author: Aikachin
 * @Description: 退出登录
 * @Date: Created in 9:45 2017/10/24 0024.
 * @Modified by :
 */
public class LogoutPage2 {
    WebDriver driver;

    public LogoutPage2(WebDriver driver) {
        this.driver = driver;
    }

    // 切换到alert框确定退出
    public void logout() {
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.alertIsPresent());
        // driver.switchTo().alert().accept();
        System.out.println("退出成功");
    }
}
