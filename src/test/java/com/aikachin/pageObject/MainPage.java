package com.aikachin.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author: Aikachin
 * @Description: 打开主页
 * @Date: Created in 10:07 2017/10/23 0023.
 * @Modified by :
 */
public class MainPage {
    WebDriver driver;
    WebElement loginLink;
    WebElement logoutLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage(String url) {
        driver.get(url);
    }

    // 使用用户名、密码登录
    public void login(String userName, String passWord) {
        loginLink = driver.findElement(By.linkText("登录"));
        loginLink.click();
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input1")));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, passWord);
    }

    // 登出账号
    public void logout() {
        logoutLink = driver.findElement(By.linkText("退出"));
        logoutLink.click();

        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();
    }
}
