package com.aikachin.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Aikachin
 * @Description: 登录页面
 *                       根据输入的用户名、密码，点击登录按钮来登录账号
 *                       或用cookies来获取登录信息
 * @Date: Created in 9:55 2017/10/23 0023.
 * @Modified by :
 */
public class LoginPage {
    WebDriver driver;
    WebElement username;
    WebElement password;
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        username = driver.findElement(By.id("input1"));
        password = driver.findElement(By.id("input2"));
        loginBtn = driver.findElement(By.id("signin"));
    }

    public void login(String userName, String passWord) {
        username.sendKeys(userName);
        password.sendKeys(passWord);

        loginBtn.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


}
