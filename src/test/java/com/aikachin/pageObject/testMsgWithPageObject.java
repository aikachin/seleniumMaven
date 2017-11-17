package com.aikachin.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.aikachin.bakcup.testVariablesSetting.URL_OF_BOKEYUAN;
import static com.aikachin.bakcup.testVariablesSetting.URL_OF_BOKEYUAN_COOKIE;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 15:09 2017/10/23 0023.
 * @Modified by :
 */
public class testMsgWithPageObject {

    public static void main(String...args) throws InterruptedException{
        WebDriver driver = new FirefoxDriver();

        MainPage mainPage = new MainPage(driver);
        MessagePage msgPage = new MessagePage(driver);

        // 打开页面
        System.out.println("1.登录博客园...");
        mainPage.openMainPage(URL_OF_BOKEYUAN);

        // 使用账号密码登录
        // mainPage.login("aikachin", "aptx");

        // 读取cookies进行登录
        LoginByCookie loginByCookie = new LoginByCookie(driver);
        loginByCookie.loadCookies(URL_OF_BOKEYUAN_COOKIE);
        mainPage.openMainPage(URL_OF_BOKEYUAN);

        // 发送短消息
        System.out.println("2.发送短消息...");
        msgPage.sendMessage("fredlic", "testPageObject", "content");
        msgPage.sendMessage("seleniumpageobject", "testPageObject", "content");
        Thread.sleep(3000);
        // 登出
        System.out.println("3.退出账号...");
        mainPage.logout();
        Thread.sleep(3000);

        // 重新登录
        System.out.println("4.重新登录...");
        mainPage.openMainPage(URL_OF_BOKEYUAN);
        loginByCookie.loadCookies(URL_OF_BOKEYUAN_COOKIE);
        mainPage.openMainPage(URL_OF_BOKEYUAN);
        // 再次发送消息
        System.out.println("5.再次发送短消息...");
        msgPage.sendMessage("fredlic", "testPageObject2", "content2");
        // 删除消息
        System.out.println("6.删除所有短消息...");
        msgPage.deleteAllMsg();
        // 再次推出
        System.out.println("7.再次退出账号...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        driver.quit();
    }
}
