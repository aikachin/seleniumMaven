package com.aikachin.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Aikachin
 * @Description: 以登录页面为对象
 * @Date: Created in 9:25 2017/10/24 0024.
 * @Modified by :
 */
public class LoginPage2 {
    WebDriver driver;

    @FindBy(how = How.CSS, css = "#LoginForm_username")
    WebElement username;
    @FindBy(how = How.CSS, css = "#LoginForm_password")
    WebElement password;
    @FindBy(how = How.CSS, css = "#SubmitLoginBTN")
    WebElement loginbtn;

    public LoginPage2(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userName, String passWord) {
        username.sendKeys(userName);
        password.sendKeys(passWord);
        loginbtn.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
