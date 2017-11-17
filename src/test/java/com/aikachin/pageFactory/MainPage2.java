package com.aikachin.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 11:08 2017/10/24 0024.
 * @Modified by :
 */
public class MainPage2 {
    WebDriver driver;

/*    @FindBy(how = How.ID, id = "")
    WebElement loginLink;
    */
    @FindBy(how = How.ID, linkText = "退出")
    WebElement logoutLink;

    public MainPage2(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
    }

    /**
     * @Description: 登录页面
     * @Param: [userName, passWord]
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void login2(String userName, String passWord) {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginForm_username")));

        LoginPage2 loginPage2 = PageFactory.initElements(driver, LoginPage2.class);
        loginPage2.login(userName, passWord);

    }

    /**
     * @Description: 退出登录
     * @Param: []
     * @return: void
     * @Author: Aikachin
     * @Date: 2017/10/26 0026
     */
    public void logout2() {
        logoutLink.click();
        LogoutPage2 logoutPage2 = PageFactory.initElements(driver, LogoutPage2.class);
        logoutPage2.logout();

    }
}
